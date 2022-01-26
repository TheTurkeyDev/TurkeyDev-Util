package dev.theturkey.turkeydevutil.items;

import java.util.function.Supplier;

import dev.theturkey.turkeydevutil.TDUCore;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public enum TDUArmorMaterials implements ArmorMaterial
{
	COBBLESTONE("cobblestone", 3, new int[]{1, 2, 3, 1}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 1.5F, 0.3F, () -> Ingredient.of(Tags.Items.COBBLESTONE));

	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	TDUArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient)
	{
		this.name = TDUCore.MOD_ID + ":" + name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantmentValue = enchantmentValue;
		this.sound = sound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
	}

	public int getDurabilityForSlot(EquipmentSlot p_40484_)
	{
		return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
	}

	public int getDefenseForSlot(EquipmentSlot p_40487_)
	{
		return this.slotProtections[p_40487_.getIndex()];
	}

	public int getEnchantmentValue()
	{
		return this.enchantmentValue;
	}

	public @NotNull SoundEvent getEquipSound()
	{
		return this.sound;
	}

	public @NotNull Ingredient getRepairIngredient()
	{
		return this.repairIngredient.get();
	}

	public @NotNull String getName()
	{
		return this.name;
	}

	public float getToughness()
	{
		return this.toughness;
	}

	public float getKnockbackResistance()
	{
		return this.knockbackResistance;
	}
}