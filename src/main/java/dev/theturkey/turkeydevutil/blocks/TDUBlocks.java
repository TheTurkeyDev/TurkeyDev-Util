package dev.theturkey.turkeydevutil.blocks;

import dev.theturkey.turkeydevutil.TDUCore;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TDUCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TDUBlocks
{
	public static LimeCropBlock LIME_CROP;

	@SubscribeEvent
	public static void onBlockRegistry(RegistryEvent.Register<Block> e)
	{
		e.getRegistry().register(LIME_CROP = new LimeCropBlock());
	}
}
