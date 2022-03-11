package dev.theturkey.turkeydevutil.util;

import dev.theturkey.turkeydevutil.TDUCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TDUCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TDUSounds
{
	public static SoundEvent DUCK_QUACK;
	public static SoundEvent TURKEY_GOBBLE;
	public static SoundEvent WELCOME;

	@SubscribeEvent
	public static void onSoundRegistry(RegistryEvent.Register<SoundEvent> e)
	{
		ResourceLocation res = new ResourceLocation(TDUCore.MOD_ID, "duck_quack");
		DUCK_QUACK = new SoundEvent(res).setRegistryName(res);
		res = new ResourceLocation(TDUCore.MOD_ID, "turkey_gobble");
		TURKEY_GOBBLE = new SoundEvent(res).setRegistryName(res);
		res = new ResourceLocation(TDUCore.MOD_ID, "j2tc.welcome");
		WELCOME = new SoundEvent(res).setRegistryName(res);

		e.getRegistry().registerAll(DUCK_QUACK, TURKEY_GOBBLE, WELCOME);
	}
}