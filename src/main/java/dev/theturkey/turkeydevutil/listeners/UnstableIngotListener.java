package dev.theturkey.turkeydevutil.listeners;

import dev.theturkey.turkeydevutil.items.ItemUnstableIngot;
import dev.theturkey.turkeydevutil.items.TDUItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class UnstableIngotListener
{
	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event)
	{
		Player player = event.player;
		Level level = player.level;
		AbstractContainerMenu menu = player.containerMenu;
		for(ItemStack stack : menu.getItems())
			if(stack.getItem().equals(TDUItems.UNSTABLE_INGOT))
				ItemUnstableIngot.tickStack(level, stack, player.blockPosition());
	}
}
