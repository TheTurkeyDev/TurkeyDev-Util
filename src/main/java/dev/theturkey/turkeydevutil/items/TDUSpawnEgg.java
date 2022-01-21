package dev.theturkey.turkeydevutil.items;

import dev.theturkey.turkeydevutil.TDUCore;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.function.Supplier;

public class TDUSpawnEgg extends ForgeSpawnEggItem
{
	public TDUSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, String name, int backgroundColor, int highlightColor)
	{
		super(type, backgroundColor, highlightColor, (new Item.Properties()).tab(TDUCore.MOD_TAB));
		this.setRegistryName(TDUCore.MOD_ID, name + "_spawn_egg");
	}
}
