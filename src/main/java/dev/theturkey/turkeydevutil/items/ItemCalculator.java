/**
 * Thanks BlakeBr0
 */
package dev.theturkey.turkeydevutil.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DigDurabilityEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Random;

public class ItemCalculator extends TDUBaseItem
{
	private static final Random RANDOM = new Random();

	public ItemCalculator()
	{
		super((new Properties()).durability(128), "calculator");
	}

	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack)
	{
		ItemStack copy = stack.copy();

		copy.setCount(1);

		int unbreakingLvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack);

		for(int i = 0; i < unbreakingLvl; i++)
			if(DigDurabilityEnchantment.shouldIgnoreDurabilityDrop(stack, unbreakingLvl, RANDOM))
				return copy;

		copy.setDamageValue(stack.getDamageValue() + 1);

		if(copy.getDamageValue() > stack.getMaxDamage())
			return ItemStack.EMPTY;

		return copy;
	}
}
