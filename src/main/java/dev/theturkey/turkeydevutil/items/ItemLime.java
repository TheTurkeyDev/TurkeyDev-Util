package dev.theturkey.turkeydevutil.items;

import net.minecraft.world.item.Item;

public class ItemLime extends TDUBaseItem
{
	public ItemLime()
	{
		super((new Item.Properties()).food(TDUFoods.LIME), "lime");
		super.addLore("What? Is that not how it works?");
	}
}
