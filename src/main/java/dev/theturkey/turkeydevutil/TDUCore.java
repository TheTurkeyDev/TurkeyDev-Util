package dev.theturkey.turkeydevutil;

import dev.theturkey.turkeydevutil.client.ClientHandler;
import dev.theturkey.turkeydevutil.commands.TDUCommands;
import dev.theturkey.turkeydevutil.entities.TDUEntityType;
import dev.theturkey.turkeydevutil.items.TDUItems;
import dev.theturkey.turkeydevutil.listeners.DuckFriendSpawn;
import dev.theturkey.turkeydevutil.listeners.UnstableIngotListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@Mod(TDUCore.MOD_ID)
public class TDUCore
{
	public static final String MOD_ID = "turkeydevutil";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final CreativeModeTab MOD_TAB = new CreativeModeTab(MOD_ID)
	{
		@Override
		public @NotNull ItemStack makeIcon()
		{
			return new ItemStack(TDUItems.LIME);
		}
	};
	public static final TagKey<Item> SHEARS = ItemTags.create(new ResourceLocation("forge", "shears"));
	public static final Random RAND = new Random();

	public TDUCore()
	{
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		eventBus.addListener(this::setup);

		MinecraftForge.EVENT_BUS.register(this);


		eventBus.addListener(TDUEntityType::registerEntityAttributes);
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
		{
			eventBus.addListener(ClientHandler::clientStart);
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		});
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new UnstableIngotListener());
		MinecraftForge.EVENT_BUS.register(new DuckFriendSpawn());
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event)
	{
		// do something when the server starts
	}

	@SubscribeEvent
	public void onCommandsRegister(RegisterCommandsEvent event)
	{
		new TDUCommands(event.getDispatcher());
	}
}
