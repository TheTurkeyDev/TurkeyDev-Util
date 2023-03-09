package dev.theturkey.turkeydevutil.listeners;

import dev.theturkey.turkeydevutil.entities.Duck;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;

public class WaystoneListener
{
	@SubscribeEvent
	public void onPlayerWaystoneTP(WaystoneTeleportEvent.Pre event)
	{
		Optional<Duck> d = DuckFriendSpawn.getDuck(event.getContext().getEntity());
		if(d.isEmpty())
			return;
		event.getContext().addAdditionalEntity(d.get());
	}
}
