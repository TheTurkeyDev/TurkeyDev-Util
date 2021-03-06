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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
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
}
