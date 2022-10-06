package dev.theturkey.turkeydevutil.blocks;

import dev.theturkey.turkeydevutil.TDUCore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class BaseTDUBlock extends Block
{
	public BaseTDUBlock(String name)
	{
		super(getBuilder());
		this.setRegistryName(TDUCore.MOD_ID, name);
	}


	protected static Properties getBuilder()
	{
		return Properties.of(Material.STONE).strength(3f, 3f);
	}
}
