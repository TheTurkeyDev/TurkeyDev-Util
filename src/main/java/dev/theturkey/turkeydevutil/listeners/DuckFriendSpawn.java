package dev.theturkey.turkeydevutil.listeners;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.entities.Duck;
import dev.theturkey.turkeydevutil.entities.TDUEntityType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class DuckFriendSpawn
{
	private static final Map<Player, Duck> DUCK_MAP = new HashMap<>();
	@SubscribeEvent
	public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
	{
		Player player = event.getPlayer();
		Level level = player.level;
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
		duck.setOwnerUUID(player.getUUID());
		duck.setPersistenceRequired();
		duck.setHealth(20f);
		level.addFreshEntity(duck);
		DUCK_MAP.put(player, duck);
	}

	@SubscribeEvent
	public void onPlayerJoin(PlayerEvent.PlayerLoggedOutEvent event)
	{
		Duck duck = DUCK_MAP.get(event.getPlayer());
		if(duck != null && duck.isAlive())
			duck.kill();
	}
}
