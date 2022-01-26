package dev.theturkey.turkeydevutil.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class TDUArmor extends ArmorItem
{
	public TDUArmor(ArmorMaterial material, EquipmentSlot slot, Properties props)
	{
		super(material, slot, props);
		super.setRegistryName(material.getName() + "_" + getPartFromSlot(slot));
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
}
