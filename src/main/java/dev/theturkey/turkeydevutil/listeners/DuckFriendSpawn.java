package dev.theturkey.turkeydevutil.listeners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.entities.Duck;
import dev.theturkey.turkeydevutil.entities.TDUEntityType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
import java.util.List;

public class DuckFriendSpawn
{
	private static final List<String> ducksToSpawn = new ArrayList<>();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static File modPlayerFile;

	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.LoadFromFile event)
	{
		modPlayerFile = event.getPlayerFile("tdu");
		loadDuckFile(event.getPlayerUUID());
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
	{
		loadDuckFile(event.getPlayer().getStringUUID());
	}

	public static void loadDuckFile(String playerUUID)
	{
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
					Duck duck = TDUEntityType.DUCK.create(level);
					if(duck == null)
					{
						TDUCore.LOGGER.error("The duck was null....");
						return;
					}
					duck.setPos(p.position());
					duck.setInvulnerable(true);
					duck.setCustomNameVisible(true);
					duck.setCustomName(new TextComponent("Gertrud"));
					duck.setTame(true);
					duck.setOwnerUUID(p.getUUID());
					duck.setPersistenceRequired();
					duck.setHealth(20f);
					level.addFreshEntity(duck);
					ducksToSpawn.remove(i);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerDie(LivingDeathEvent event)
	{
		for(Duck d : getDucks(event.getEntityLiving()))
		{
			d.kill();
		}
	}

	@SubscribeEvent
	public void onPlayerTP(EntityTeleportEvent event)
	{
		for(Duck d : getDucks(event.getEntity()))
			d.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
	}

	@SubscribeEvent
	public void onPlayerDimChange(PlayerEvent.PlayerChangedDimensionEvent event)
	{
		Player player = event.getPlayer();
		for(Duck d : getDucks(player))
		{
			ServerLevel level = player.getServer().getLevel(event.getTo());
			d.changeDimension(level);
			d.teleportTo(player.position().x, player.position().y, player.position().z);
		}
	}

	/**
	 * Should only ever be 1 but....
	 *
	 * @param player the player
	 * @return the player's ducks
	 */
	public List<Duck> getDucks(Entity player)
	{
		List<Duck> ducks = new ArrayList<>();
		if(player.level.isClientSide())
			return ducks;

		if(player instanceof Player)
		{
			for(Duck d : ((ServerLevel) player.level).getEntities(TDUEntityType.DUCK, e -> true))
			{
				LivingEntity owner = d.getOwner();
				if(owner != null && owner.equals(player))
					ducks.add(d);
			}
		}

		return ducks;
	}

	public static void spawnDuckForPlayer(String playerUUID)
	{
		ducksToSpawn.add(playerUUID);
	}
}
