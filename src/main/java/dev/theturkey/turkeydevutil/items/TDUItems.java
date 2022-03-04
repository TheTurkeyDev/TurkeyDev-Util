package dev.theturkey.turkeydevutil.items;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.blocks.TDUBlocks;
import dev.theturkey.turkeydevutil.entities.TDUEntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TDUCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TDUItems
{
	public static TDUBaseItem LANYARD;
	public static TDUBaseItem BOOK;

	public static TDUBaseItem LIME;
	public static TDUBaseItem ROCK_SOUP;
	public static TDUBaseItem RAMEN;
	public static TDUBaseItem ICE_CREAM;
	public static TDUBaseItem TOAST;

	public static TDUBaseBlockItem LIME_SEEDS;

	public static TDUArmor COBBLESTONE_HELMET;
	public static TDUArmor COBBLESTONE_CHESTPLATE;
	public static TDUArmor COBBLESTONE_LEGGINS;
	public static TDUArmor COBBLESTONE_BOOTS;

	@SubscribeEvent
	public static void onItemRegistry(RegistryEvent.Register<Item> e)
	{
		e.getRegistry().register(LANYARD = new TDUBaseItem(initProps(), "lanyard"));
		LANYARD.addLore("Coming Soon™");
		e.getRegistry().register(BOOK = new TDUBaseItem(initProps(), "book"));
		BOOK.addLore("How do I use this?");

		e.getRegistry().register(LIME = new TDUBaseItem(initFoodProps(TDUFoods.LIME), "lime"));
		LIME.addLore("What? Is that not how it works?");
		e.getRegistry().register(ICE_CREAM = new TDUBaseItem(initFoodProps(TDUFoods.ICE_CREAM), "ice_cream"));
		ICE_CREAM.addLore("Cones sold separately");
		e.getRegistry().register(ROCK_SOUP = new ItemRockSoup());
		e.getRegistry().register(RAMEN = new TDUBaseItem(initFoodProps(TDUFoods.RAMEN), "ramen"));
		e.getRegistry().register(LIME_SEEDS = new TDUBaseBlockItem(TDUBlocks.LIME_CROP, initProps(), "lime_seeds"));
		e.getRegistry().register(TOAST = new TDUBaseItem(initFoodProps(TDUFoods.TOAST), "toast"));
		//TOAST.addLore("DarkToasto");

		e.getRegistry().register(COBBLESTONE_HELMET = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.HEAD, initArmorProps()));
		e.getRegistry().register(COBBLESTONE_CHESTPLATE = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.CHEST, initArmorProps()));
		e.getRegistry().register(COBBLESTONE_LEGGINS = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.LEGS, initArmorProps()));
		e.getRegistry().register(COBBLESTONE_BOOTS = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.FEET, initArmorProps()));

		e.getRegistry().register(new TDUSpawnEgg(() -> TDUEntityType.TURKEY, "turkey", 0x522900, 0xE68A00));
		e.getRegistry().register(new TDUSpawnEgg(() -> TDUEntityType.DUCK, "duck", 0xA65300, 0x005300));
	}

	private static Item.Properties initArmorProps()
	{
		return initProps().tab(TDUCore.MOD_TAB);
	}

	private static Item.Properties initFoodProps(FoodProperties props)
	{
		return initProps().food(props);
	}

	private static Item.Properties initProps()
	{
		return new Item.Properties();
	}
}
