package dev.theturkey.turkeydevutil.entities;

import dev.theturkey.turkeydevutil.util.TDUSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Duck extends Chicken
{
	protected Duck(EntityType<? extends Chicken> entityType, Level level)
	{
		super(entityType, level);
	}

	@Nullable
	@Override
	public Duck getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob)
	{
		return TDUEntityType.DUCK.create(level);
	}

	protected SoundEvent getAmbientSound() {
		return TDUSounds.DUCK_QUACK;
	}
}
