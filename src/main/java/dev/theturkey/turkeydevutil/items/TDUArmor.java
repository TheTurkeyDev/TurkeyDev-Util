package dev.theturkey.turkeydevutil.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TDUArmor extends ArmorItem
{
	//From ArmorItem
	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public TDUArmor(ArmorMaterial material, EquipmentSlot slot, Properties props)
	{
		super(material, slot, props);
		super.setRegistryName(material.getName() + "_" + getPartFromSlot(slot));
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.putAll(super.getDefaultAttributeModifiers(slot));
		UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
		builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor speed adjustment", -0.05f, AttributeModifier.Operation.MULTIPLY_BASE));
		this.defaultModifiers = builder.build();
	}

	private String getPartFromSlot(EquipmentSlot slot)
	{
		return switch(slot)
				{
					case HEAD -> "helmet";
					case CHEST -> "chestplate";
					case LEGS -> "leggings";
					default -> "boots";
				};
	}

	@Override
	public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot)
	{
		return slot == this.slot ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
}
