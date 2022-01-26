package dev.theturkey.turkeydevutil.blocks;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.items.TDUItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LimeCropBlock extends CropBlock
{
	public LimeCropBlock()
	{
		super(BlockBehaviour.Properties.of(Material.PLANT).noOcclusion().noCollission().randomTicks().instabreak().sound(SoundType.CROP));
		super.setRegistryName(TDUCore.MOD_ID, "lime_crop");
	}

	@Override
	protected @NotNull ItemLike getBaseSeedId()
	{
		return TDUItems.LIME_SEEDS;
	}

	@Override
	public @NotNull List<ItemStack> getDrops(BlockState state, LootContext.@NotNull Builder builder)
	{
		List<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(TDUItems.LIME_SEEDS, 1));
		int age = state.getValue(AGE);

		if(age == this.getMaxAge())
			drops.add(new ItemStack(TDUItems.LIME, 2));

		return drops;
	}

}
