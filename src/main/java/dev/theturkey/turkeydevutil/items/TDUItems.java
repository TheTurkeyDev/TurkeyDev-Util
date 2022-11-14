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
	public static TDUBaseItem UNSTABLE_INGOT;
	public static TDUBaseItem CONDENSED_CORE;
	public static TDUBaseItem CALCULATOR;
	public static TDUBaseItem UNIDENTIFIED_GEM;

	public static TDUBaseItem LIME;
	public static TDUBaseItem ROCK_SOUP;
	public static TDUBaseItem RAMEN;
	public static TDUBaseItem ICE_CREAM;
	public static TDUBaseItem TOAST;
	public static TDUBaseItem COOKIE;

	public static TDUBaseBlockItem LIME_SEEDS;
	public static TDUBaseBlockItem GREED_GOLD_ORE;
	public static TDUBaseBlockItem GREED_IRON_ORE;
	public static TDUBaseBlockItem GREED_LAPIS_ORE;
	public static TDUBaseBlockItem GREED_OIL_ORE;
	public static TDUBaseBlockItem GREED_RANDOMITE_ORE;
	public static TDUBaseBlockItem GREED_SILVER_ORE;
	public static TDUBaseBlockItem GREED_DIAMOND_ORE;
	public static TDUBaseBlockItem SUGAR_COPPER_ORE;
	public static TDUBaseBlockItem SUGAR_IRON_ORE;
	public static TDUBaseBlockItem SUGAR_REDSTONE_ORE;

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
		e.getRegistry().register(UNSTABLE_INGOT = new ItemUnstableIngot());
		e.getRegistry().register(CONDENSED_CORE = new TDUBaseItem(initProps(), "condensed_core"));
		e.getRegistry().register(CALCULATOR = new ItemCalculator());
		e.getRegistry().register(UNIDENTIFIED_GEM = new TDUBaseItem(initProps(), "unidentified_gem"));

		e.getRegistry().register(LIME = new TDUBaseItem(initFoodProps(TDUFoods.LIME), "lime"));
		LIME.addLore("What? Is that not how it works?");
		e.getRegistry().register(ICE_CREAM = new TDUBaseItem(initFoodProps(TDUFoods.ICE_CREAM), "ice_cream"));
		ICE_CREAM.addLore("Cones sold separately");
		e.getRegistry().register(ROCK_SOUP = new ItemRockSoup());
		e.getRegistry().register(RAMEN = new TDUBaseItem(initFoodProps(TDUFoods.RAMEN), "ramen"));
		e.getRegistry().register(LIME_SEEDS = new TDUBaseBlockItem(TDUBlocks.LIME_CROP, initProps(), "lime_seeds"));
		e.getRegistry().register(TOAST = new TDUBaseItem(initFoodProps(TDUFoods.TOAST), "toast"));
		e.getRegistry().register(COOKIE = new TDUBaseItem(initFoodProps(TDUFoods.COOKIE), "cookie"));

		e.getRegistry().register(COBBLESTONE_HELMET = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.HEAD, initArmorProps()));
		e.getRegistry().register(COBBLESTONE_CHESTPLATE = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.CHEST, initArmorProps()));
		e.getRegistry().register(COBBLESTONE_LEGGINS = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.LEGS, initArmorProps()));
		e.getRegistry().register(COBBLESTONE_BOOTS = new TDUArmor(TDUArmorMaterials.COBBLESTONE, EquipmentSlot.FEET, initArmorProps()));

		e.getRegistry().register(GREED_GOLD_ORE = new TDUBaseBlockItem(TDUBlocks.GREED_GOLD_ORE, initProps(), "greed_gold_ore"));
		e.getRegistry().register(GREED_IRON_ORE = new TDUBaseBlockItem(TDUBlocks.GREED_IRON_ORE, initProps(), "greed_iron_ore"));
		e.getRegistry().register(GREED_LAPIS_ORE = new TDUBaseBlockItem(TDUBlocks.GREED_LAPIS_ORE, initProps(), "greed_lapis_ore"));
		e.getRegistry().register(GREED_OIL_ORE = new TDUBaseBlockItem(TDUBlocks.GREED_OIL_ORE, initProps(), "greed_oil_ore"));
		e.getRegistry().register(GREED_RANDOMITE_ORE = new TDUBaseBlockItem(TDUBlocks.GREED_RANDOMITE_ORE, initProps(), "greed_randomite_ore"));
		e.getRegistry().register(GREED_SILVER_ORE = new TDUBaseBlockItem(TDUBlocks.GREED_SILVER_ORE, initProps(), "greed_silver_ore"));
		e.getRegistry().register(GREED_DIAMOND_ORE = new TDUBaseBlockItem(TDUBlocks.GREED_DIAMOND_ORE, initProps(), "greed_diamond_ore"));
		e.getRegistry().register(SUGAR_COPPER_ORE = new TDUBaseBlockItem(TDUBlocks.SUGAR_COPPER_ORE, initProps(), "sugar_copper_ore"));
		e.getRegistry().register(SUGAR_IRON_ORE = new TDUBaseBlockItem(TDUBlocks.SUGAR_IRON_ORE, initProps(), "sugar_iron_ore"));
		e.getRegistry().register(SUGAR_REDSTONE_ORE = new TDUBaseBlockItem(TDUBlocks.SUGAR_REDSTONE_ORE, initProps(), "sugar_redstone_ore"));

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
