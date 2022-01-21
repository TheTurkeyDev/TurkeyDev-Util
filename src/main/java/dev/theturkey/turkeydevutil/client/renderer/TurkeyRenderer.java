package dev.theturkey.turkeydevutil.client.renderer;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.client.ClientHandler;
import dev.theturkey.turkeydevutil.client.models.TurkeyModel;
import dev.theturkey.turkeydevutil.entities.Turkey;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class TurkeyRenderer extends MobRenderer<Turkey, TurkeyModel<Turkey>>
{
	private static final ResourceLocation TURKEY_TEXTURES = new ResourceLocation(TDUCore.MOD_ID, "textures/entity/turkey.png");

	public TurkeyRenderer(Context context)
	{
		super(context, new TurkeyModel<>(context.bakeLayer(ClientHandler.TURKEY)), 0.25F);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull Turkey entity)
	{
		return TURKEY_TEXTURES;
	}
}