package dev.theturkey.turkeydevutil.entities.ai;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.level.Level;

public class FollowOwnerDimensionGoal extends FollowOwnerGoal
{
	private final TamableAnimal tamable;

	public FollowOwnerDimensionGoal(TamableAnimal tamable, double p_25295_, float p_25296_, float p_25297_, boolean p_25298_)
	{
		super(tamable, p_25295_, p_25296_, p_25297_, p_25298_);
		this.tamable = tamable;
	}

	@Override
	public void tick()
	{
		LivingEntity owner = this.tamable.getOwner();
		if(owner == null)
		{
			super.tick();
			return;
		}
		Level ownerLevel = owner.level;
		if(!ownerLevel.isClientSide && this.tamable.getLevel().dimension().equals(ownerLevel.dimension()))
			tamable.changeDimension((ServerLevel) ownerLevel);
		super.tick();
	}
}
