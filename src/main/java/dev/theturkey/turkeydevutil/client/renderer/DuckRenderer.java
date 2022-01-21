package dev.theturkey.turkeydevutil.client.renderer;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.client.ClientHandler;
import dev.theturkey.turkeydevutil.entities.Duck;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DuckRenderer extends MobRenderer<Duck, ChickenModel<Duck>>
{
	private static final ResourceLocation DUCK_TEXTURES = new ResourceLocation(TDUCore.MOD_ID, "textures/entity/duck.png");

	public DuckRenderer(Context context)
	{
		super(context, new ChickenModel<>(context.bakeLayer(ClientHandler.DUCK)), 0.25F);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull Duck entity)
	{
		return DUCK_TEXTURES;
	}
}