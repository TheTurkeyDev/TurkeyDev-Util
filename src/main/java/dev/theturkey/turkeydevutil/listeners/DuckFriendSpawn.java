package dev.theturkey.turkeydevutil.listeners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.entities.Duck;
import dev.theturkey.turkeydevutil.entities.TDUEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DuckFriendSpawn
{
	private static final List<String> ducksToSpawn = new ArrayList<>();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Map<String, File> modPlayerFiles = new HashMap<>();

	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.LoadFromFile event)
	{
		modPlayerFiles.put(event.getPlayerUUID(), event.getPlayerFile("tdu"));
		loadDuckFile(event.getPlayerUUID());
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
	{
		spawnDuck(event.getPlayer(), event.getPlayer().level);
	}

	public static void loadDuckFile(String playerUUID)
	{
		File modPlayerFile = modPlayerFiles.get(playerUUID);
		try
		{
			JsonObject json;
			if(!modPlayerFile.exists())
			{
				if(modPlayerFile.createNewFile())
				{
					json = generateDefaultJson(modPlayerFile);
				}
				else
				{
					TDUCore.LOGGER.error("FAILED TO MAKE TDU PLAYER FILE!!! Gertrud will not work! Poor Gertrud...");
					return;
				}
			}
			else
			{
				JsonElement elem = JsonParser.parseReader(new FileReader(modPlayerFile));
				if(elem.isJsonObject())
					json = elem.getAsJsonObject();
				else
					json = generateDefaultJson(modPlayerFile);
			}

			if(!json.get("spawned_gertrud").getAsBoolean())
			{
				ducksToSpawn.add(playerUUID);
				json.addProperty("spawned_gertrud", true);
				savePlayerFile(modPlayerFile, json);
			}

		} catch(Exception e)
		{
			e.printStackTrace();
			TDUCore.LOGGER.error("ERRORED TRYING TO MAKE TDU PLAYER FILE!!! Gertrud will not work! Poor Gertrud...");
		}
	}

	private static void saveDuck(String playerUUID, Duck duck)
	{
		try
		{
			File modPlayerFile = modPlayerFiles.get(playerUUID);
			JsonObject json = JsonParser.parseReader(new FileReader(modPlayerFile)).getAsJsonObject();
			ItemStack stack = duck.getItemInHand(InteractionHand.MAIN_HAND);
			CompoundTag tag = stack.save(new CompoundTag());
			json.addProperty("held_item", tag.toString());
			savePlayerFile(modPlayerFile, json);
		} catch(Exception e)
		{
			e.printStackTrace();
			TDUCore.LOGGER.error("ERRORED TRYING TO SAVE THE DUCK TO THE TDU PLAYER FILE!!! Gertrud will not work! Poor Gertrud...");
		}
	}

	public static void spawnDuck(Player player, Level level)
	{
		Duck duck = TDUEntityType.DUCK.create(level);
		if(duck == null)
		{
			TDUCore.LOGGER.error("The duck was null....");
			return;
		}
		duck.setPos(player.position());
		duck.setInvulnerable(true);
		duck.setCustomNameVisible(true);
		duck.setCustomName(new TextComponent("Gertrud"));
		duck.setTame(true);
		duck.setOwnerUUID(player.getUUID());
		duck.setPersistenceRequired();
		duck.setHealth(20f);
		try
		{
			File modPlayerFile = modPlayerFiles.get(player.getStringUUID());
			JsonObject json = JsonParser.parseReader(new FileReader(modPlayerFile)).getAsJsonObject();
			if(json.has("held_item"))
			{
				String s = json.get("held_item").getAsString();
				ItemStack stack = ItemStack.of(TagParser.parseTag(s));
				duck.setItemInHand(InteractionHand.MAIN_HAND, stack);
			}
		} catch(Exception e)
		{
			e.printStackTrace();
			TDUCore.LOGGER.error("ERRORED TRYING TO SPAWN THE DUCK FROM THE TDU PLAYER FILE!!! Gertrud will not work! Poor Gertrud...");
		}

		level.addFreshEntity(duck);
	}

	private static JsonObject generateDefaultJson(File file)
	{
		JsonObject json = new JsonObject();
		json.addProperty("spawned_gertrud", false);
		savePlayerFile(file, json);
		return json;
	}

	private static void savePlayerFile(File file, JsonObject json)
	{
		try(Writer writer = new FileWriter(file))
		{
			GSON.toJson(json, writer);
		} catch(IOException e)
		{
			e.printStackTrace();
			TDUCore.LOGGER.error("ERRORED TRYING TO SAVE TDU PLAYER FILE!!!");
		}
	}

	@SubscribeEvent
	public void onServerWorldTick(TickEvent.WorldTickEvent event)
	{
		if(event.side != LogicalSide.SERVER || event.phase != TickEvent.Phase.END)
			return;

		for(int i = ducksToSpawn.size() - 1; i >= 0; i--)
		{
			String uuid = ducksToSpawn.get(i);
			for(Player p : event.world.players())
			{
				if(p.getUUID().toString().equals(uuid))
				{
					Level level = event.world;
					spawnDuck(p, level);
					ducksToSpawn.remove(i);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerDie(LivingDeathEvent event)
	{
		if(event.getEntityLiving() instanceof Player player)
		{
			Optional<Duck> d = getDuck(event.getEntityLiving());
			if(d.isEmpty())
				return;
			saveDuck(player.getStringUUID(), d.get());
			d.get().remove(Entity.RemovalReason.DISCARDED);
		}
	}

	@SubscribeEvent
	public void onPlayerTP(EntityTeleportEvent event)
	{
		Optional<Duck> d = getDuck(event.getEntity());
		if(d.isEmpty())
			return;
		d.get().teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
	}

	@SubscribeEvent
	public void onPlayerDimChange(PlayerEvent.PlayerChangedDimensionEvent event)
	{
		Player player = event.getPlayer();
		Optional<Duck> d = getDuck(player);
		if(d.isEmpty())
			return;
		ServerLevel level = player.getServer().getLevel(event.getTo());
		d.get().changeDimension(level);
		d.get().teleportTo(player.position().x, player.position().y, player.position().z);
	}

	/**
	 * Should only ever be 1 but....
	 *
	 * @param player the player
	 * @return the player's ducks
	 */
	public static Optional<Duck> getDuck(Entity player)
	{
		if(player.level.isClientSide())
			return Optional.empty();

		if(player instanceof Player)
		{
			for(Duck d : ((ServerLevel) player.level).getEntities(TDUEntityType.DUCK, e -> true))
			{
				LivingEntity owner = d.getOwner();
				if(owner != null && owner.equals(player))
					return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static void spawnDuckForPlayer(String playerUUID)
	{
		ducksToSpawn.add(playerUUID);
	}
}
