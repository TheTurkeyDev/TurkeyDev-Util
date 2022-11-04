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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
import java.util.UUID;

public class DuckFriendSpawn
{

	private static final List<String> ducksToSpawn = new ArrayList<>();
	private static final Map<UUID, ItemStack> duckHeldItem = new HashMap<>();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.LoadFromFile event)
	{
		File f = event.getPlayerFile("tdu");
		try
		{
			JsonObject json;
			if(!f.exists())
			{
				if(f.createNewFile())
				{
					json = generateDefaultJson(f);
				}
				else
				{
					TDUCore.LOGGER.error("FAILED TO MAKE TDU PLAYER FILE!!! Gertrud will not work! Poor Gertrud...");
					return;
				}
			}
			else
			{
				JsonElement elem = JsonParser.parseReader(new FileReader(f));
				if(elem.isJsonObject())
					json = elem.getAsJsonObject();
				else
					json = generateDefaultJson(f);
			}

			if(!json.get("spawned_gertrud").getAsBoolean())
			{
				ducksToSpawn.add(event.getPlayerUUID());
				json.addProperty("spawned_gertrud", true);
				savePlayerFile(f, json);
			}

		} catch(Exception e)
		{
			e.printStackTrace();
			TDUCore.LOGGER.error("ERRORED TRYING TO MAKE TDU PLAYER FILE!!! Gertrud will not work! Poor Gertrud...");
		}
	}

	private JsonObject generateDefaultJson(File file)
	{
		JsonObject json = new JsonObject();
		json.addProperty("spawned_gertrud", false);
		savePlayerFile(file, json);
		return json;
	}

	private void savePlayerFile(File file, JsonObject json)
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

	public Duck spawnDuck(Level level, Player p)
	{
		Duck duck = TDUEntityType.DUCK.create(level);
		if(duck == null)
		{
			TDUCore.LOGGER.error("The duck was null....");
			return null;
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
		return duck;
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
					spawnDuck(level, p);
					ducksToSpawn.remove(i);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
	{
		Duck duck = spawnDuck(event.getPlayer().getLevel(), event.getPlayer());
		duck.setItemInHand(InteractionHand.MAIN_HAND, duckHeldItem.remove(event.getPlayer().getUUID()));
	}

	@SubscribeEvent
	public void onPlayerDie(LivingDeathEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			for(Duck d : event.getEntity().level.getNearbyEntities(Duck.class, TargetingConditions.forNonCombat(), event.getEntityLiving(), AABB.ofSize(event.getEntity().getEyePosition(), 50, 50, 50)))
			{
				LivingEntity owner = d.getOwner();
				if(owner != null && owner.equals(event.getEntityLiving()))
				{
					duckHeldItem.put(owner.getUUID(), d.getItemInHand(InteractionHand.MAIN_HAND).copy());
					d.kill();
				}
			}
		}
	}
}
