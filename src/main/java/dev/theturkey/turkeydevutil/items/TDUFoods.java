package dev.theturkey.turkeydevutil.items;

import net.minecraft.world.food.FoodProperties;

public class TDUFoods
{
	public static final FoodProperties LIME = getFoodProps(1, 0.2f);
	public static final FoodProperties ROCK_SOUP = getFoodProps(4, 0.25f);

	public static FoodProperties getFoodProps(int nutrition, float saturation)
	{
		return (new FoodProperties.Builder()).nutrition(nutrition).saturationMod(saturation).build();
	}
}
