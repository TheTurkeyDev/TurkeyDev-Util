package dev.theturkey.turkeydevutil.items;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ItemRockSoup extends TDUBaseItem
{
	public ItemRockSoup()
	{
		super((new Properties()).food(TDUFoods.ROCK_SOUP), "rock_soup");
		super.addLore("MMMMM Tasty");
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, LivingEntity player) {
		player.hurt(DamageSource.STARVE, 2.0f);

		player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0));
		return super.finishUsingItem(stack, level, player);
	}
}
