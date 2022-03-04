package dev.theturkey.turkeydevutil.items;

import net.minecraft.world.food.FoodProperties;

public class TDUFoods
{
	public static final FoodProperties LIME = getFoodProps(1, 0.2f);
	public static final FoodProperties RAMEN = getFoodProps(5, 0.6f);
	public static final FoodProperties ROCK_SOUP = getFoodProps(4, 0.25f);
	public static final FoodProperties ICE_CREAM = getFoodProps(2, 0.9f);
	public static final FoodProperties TOAST = getFoodProps(7, 0.7F);

	public static FoodProperties getFoodProps(int nutrition, float saturation)
	{
		return (new FoodProperties.Builder()).nutrition(nutrition).saturationMod(saturation).build();
	}
}
