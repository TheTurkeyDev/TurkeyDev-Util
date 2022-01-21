package dev.theturkey.turkeydevutil.items;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.entities.TDUEntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TDUCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TDUItems
{
	public static Item LIME;
	public static Item ROCK_SOUP;

	@SubscribeEvent
	public static void onItemRegistry(RegistryEvent.Register<Item> e)
	{
		e.getRegistry().register(LIME = new ItemLime());
		e.getRegistry().register(ROCK_SOUP = new ItemRockSoup());
		e.getRegistry().register(new TDUSpawnEgg(() -> TDUEntityType.TURKEY, "turkey", 0x522900, 0xE68A00));
		e.getRegistry().register(new TDUSpawnEgg(() -> TDUEntityType.DUCK, "duck", 0xA65300, 0x005300));
	}
}
