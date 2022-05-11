package dev.theturkey.turkeydevutil.entities;

import dev.theturkey.turkeydevutil.util.TDUSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Duck extends TamableAnimal
{
	public float flap;
	public float flapSpeed;
	public float oFlapSpeed;
	public float oFlap;
	public float flapping = 1.0F;
	private float nextFlap = 1.0F;

	protected Duck(EntityType<? extends Duck> entityType, Level level)
	{
		super(entityType, level);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		//this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
	}

	public float getBrightness()
	{
		return 1.0F;
	}

	@Nullable
	@Override
	public Duck getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob)
	{
		return TDUEntityType.DUCK.create(level);
	}

	protected SoundEvent getAmbientSound()
	{
		return TDUSounds.DUCK_QUACK;
	}

	protected SoundEvent getHurtSound(@NotNull DamageSource source)
	{
		return SoundEvents.CHICKEN_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.CHICKEN_DEATH;
	}

	protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dims)
	{
		return this.isBaby() ? dims.height * 0.85F : dims.height * 0.92F;
	}

	public void aiStep()
	{
		super.aiStep();
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
		this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
		if(!this.onGround && this.flapping < 1.0F)
			this.flapping = 1.0F;

		this.flapping *= 0.9F;
		Vec3 vec3 = this.getDeltaMovement();
		if(!this.onGround && vec3.y < 0.0D)
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));

		this.flap += this.flapping * 2.0F;
	}

	protected boolean isFlapping()
	{
		return this.flyDist > this.nextFlap;
	}

	protected void onFlap()
	{
		this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
	}

	public boolean causeFallDamage(float p_148875_, float p_148876_, @NotNull DamageSource damageSource)
	{
		return false;
	}

	protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState state)
	{
		this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
	}

	public boolean isFood(@NotNull ItemStack p_28271_)
	{
		return false;
		//return FOOD_ITEMS.test(p_28271_);
	}

	public void positionRider(@NotNull Entity p_28269_)
	{
		super.positionRider(p_28269_);
		float f = Mth.sin(this.yBodyRot * ((float) Math.PI / 180F));
		float f1 = Mth.cos(this.yBodyRot * ((float) Math.PI / 180F));
		p_28269_.setPos(this.getX() + (double) (0.1F * f), this.getY(0.5D) + p_28269_.getMyRidingOffset() + 0.0D, this.getZ() - (double) (0.1F * f1));
		if(p_28269_ instanceof LivingEntity)
			((LivingEntity) p_28269_).yBodyRot = this.yBodyRot;
	}

	public boolean isInSittingPose()
	{
		return false;
	}

	public void setInSittingPose(boolean p_21838_)
	{
	}
}
