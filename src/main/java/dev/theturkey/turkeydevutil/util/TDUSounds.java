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
	public static SoundEvent IM_A_DUCK;
	public static SoundEvent TURKEY_GOBBLE;
	public static SoundEvent TURKEY_GOBBLE_DARKOSTO;
	public static SoundEvent WELCOME;

	@SubscribeEvent
	public static void onSoundRegistry(RegistryEvent.Register<SoundEvent> e)
	{
		DUCK_QUACK = genSoundEvent("duck_quack");
		IM_A_DUCK = genSoundEvent("im_a_duck");
		TURKEY_GOBBLE = genSoundEvent("turkey_gobble");
		TURKEY_GOBBLE_DARKOSTO = genSoundEvent("turkey_gobble_darkosto");
		WELCOME = genSoundEvent("j2tc.welcome");

		e.getRegistry().registerAll(DUCK_QUACK, IM_A_DUCK, TURKEY_GOBBLE, TURKEY_GOBBLE_DARKOSTO, WELCOME);
	}

	public static SoundEvent genSoundEvent(String soundID){
		ResourceLocation res = new ResourceLocation(TDUCore.MOD_ID, soundID);
		return new SoundEvent(res).setRegistryName(res);
	}
}