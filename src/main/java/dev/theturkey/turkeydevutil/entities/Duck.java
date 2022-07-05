package dev.theturkey.turkeydevutil.entities;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.util.TDUSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Duck extends TamableAnimal
{
	private final int SHEAR_DELAY = 20 * 60 * 5;

	public float flap;
	public float flapSpeed;
	public float oFlapSpeed;
	public float oFlap;
	public float flapping = 1.0F;
	private float nextFlap = 1.0F;

	protected int shearTimer;

	protected Duck(EntityType<? extends Duck> entityType, Level level)
	{
		super(entityType, level);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));

		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 0.5D);
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

		if(!this.level.isClientSide() && this.isAlive() && this.shearTimer > 0)
			this.shearTimer--;

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

	@Override
	public boolean isInSittingPose()
	{
		return false;
	}

	@Override
	public boolean isOrderedToSit()
	{
		return false;
	}

	@Override
	public void setInSittingPose(boolean p_21838_)
	{
	}

	public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand)
	{
		ItemStack playerItem = player.getItemInHand(hand).copy();

		if(playerItem.is(TDUCore.SHEARS) && this.shearTimer == 0)
		{
			this.shearTimer = SHEAR_DELAY;
			ItemStack feathers = new ItemStack(Items.FEATHER, TDUCore.RAND.nextInt(3) + 1);
			ItemEntity ent = new ItemEntity(level, this.getX(), this.getY(), this.getZ(), feathers);
			level.addFreshEntity(ent);
		}

		if(!this.isOwnedBy(player))
			return InteractionResult.FAIL;

		ItemStack duckCopy = this.getMainHandItem().copy();
		if(!playerItem.isEmpty() || !duckCopy.isEmpty())
		{
			this.setItemInHand(InteractionHand.MAIN_HAND, playerItem);
			player.setItemInHand(hand, duckCopy);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	public boolean doHurtTarget(Entity p_30372_)
	{
		boolean flag = p_30372_.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if(flag)
		{
			this.doEnchantDamageEffects(this, p_30372_);
		}

		return flag;
	}

	public boolean isTame()
	{
		int flag = this.entityData.get(DATA_FLAGS_ID);
		return (flag & 4) != 0;
	}

	public boolean isSpecialDuck()
	{
		Component name = this.getCustomName();
		return name != null && name.getString().equals("Gertrud");
	}

	@Override
	public boolean removeWhenFarAway(double p_21542_)
	{
		return !isSpecialDuck();
	}

	@Override
	public boolean requiresCustomPersistence()
	{
		return this.isPassenger() || isSpecialDuck();
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag)
	{
		super.addAdditionalSaveData(tag);
		tag.putInt("ShearTimer", this.shearTimer);
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag)
	{
		super.readAdditionalSaveData(tag);
		this.shearTimer = tag.getInt("ShearTimer");
	}
}
