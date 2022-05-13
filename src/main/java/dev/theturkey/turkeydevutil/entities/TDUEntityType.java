package dev.theturkey.turkeydevutil.entities;

import dev.theturkey.turkeydevutil.TDUCore;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TDUCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TDUEntityType
{
	public static EntityType<Turkey> TURKEY;
	public static EntityType<Duck> DUCK;

	@SubscribeEvent
	public static void onEntityRegistry(RegistryEvent.Register<EntityType<?>> e)
	{
		TURKEY = EntityType.Builder.of(Turkey::new, MobCategory.CREATURE).sized(0.7F, 0.8F).clientTrackingRange(10).build("turkey");
		TURKEY.setRegistryName(TDUCore.MOD_ID, "turkey");
		e.getRegistry().register(TURKEY);

		DUCK = EntityType.Builder.of(Duck::new, MobCategory.CREATURE).sized(0.4F, 0.7F).clientTrackingRange(10).build("duck");
		DUCK.setRegistryName(TDUCore.MOD_ID, "duck");
		e.getRegistry().register(DUCK);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event)
	{
		event.put(TURKEY, Turkey.createAttributes().build());
		event.put(DUCK, Duck.createAttributes().build());
	}
}
