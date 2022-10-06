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
	public static BaseTDUBlock GREED_GOLD_ORE;
	public static BaseTDUBlock GREED_IRON_ORE;
	public static BaseTDUBlock GREED_LAPIS_ORE;
	public static BaseTDUBlock GREED_OIL_ORE;
	public static BaseTDUBlock GREED_RANDOMITE_ORE;
	public static BaseTDUBlock GREED_SILVER_ORE;
	public static BaseTDUBlock GREED_DIAMOND_ORE;
	public static BaseTDUBlock SUGAR_COPPER_ORE;
	public static BaseTDUBlock SUGAR_IRON_ORE;
	public static BaseTDUBlock SUGAR_REDSTONE_ORE;

	@SubscribeEvent

	public static void onBlockRegistry(RegistryEvent.Register<Block> e)
	{
		e.getRegistry().register(LIME_CROP = new LimeCropBlock());
		e.getRegistry().register(GREED_GOLD_ORE = new BaseTDUBlock("greed_gold_ore"));
		e.getRegistry().register(GREED_IRON_ORE = new BaseTDUBlock("greed_iron_ore"));
		e.getRegistry().register(GREED_LAPIS_ORE = new BaseTDUBlock("greed_lapis_ore"));
		e.getRegistry().register(GREED_OIL_ORE = new BaseTDUBlock("greed_oil_ore"));
		e.getRegistry().register(GREED_RANDOMITE_ORE = new BaseTDUBlock("greed_randomite_ore"));
		e.getRegistry().register(GREED_SILVER_ORE = new BaseTDUBlock("greed_silver_ore"));
		e.getRegistry().register(GREED_DIAMOND_ORE = new BaseTDUBlock("greed_diamond_ore"));
		e.getRegistry().register(SUGAR_COPPER_ORE = new BaseTDUBlock("sugar_copper_ore"));
		e.getRegistry().register(SUGAR_IRON_ORE = new BaseTDUBlock("sugar_iron_ore"));
		e.getRegistry().register(SUGAR_REDSTONE_ORE = new BaseTDUBlock("sugar_redstone_ore"));
	}
}
