package dev.theturkey.turkeydevutil.client;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.blocks.TDUBlocks;
import dev.theturkey.turkeydevutil.client.models.TurkeyModel;
import dev.theturkey.turkeydevutil.client.renderer.DuckRenderer;
import dev.theturkey.turkeydevutil.client.renderer.TurkeyRenderer;
import dev.theturkey.turkeydevutil.entities.TDUEntityType;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler
{
	public static final ModelLayerLocation TURKEY = new ModelLayerLocation(new ResourceLocation(TDUCore.MOD_ID, "main"), "turkey");
	public static final ModelLayerLocation DUCK = new ModelLayerLocation(new ResourceLocation(TDUCore.MOD_ID, "main"), "duck");

	@SubscribeEvent
	public static void clientStart(FMLClientSetupEvent event)
	{
		ItemBlockRenderTypes.setRenderLayer(TDUBlocks.LIME_CROP, RenderType.cutoutMipped());
	}

	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(TDUEntityType.TURKEY, TurkeyRenderer::new);
		event.registerEntityRenderer(TDUEntityType.DUCK, DuckRenderer::new);
	}

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
	{
		event.registerLayerDefinition(TURKEY, TurkeyModel::createBodyLayer);
		event.registerLayerDefinition(DUCK, ChickenModel::createBodyLayer);
	}
}
