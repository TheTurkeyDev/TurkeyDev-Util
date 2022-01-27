package dev.theturkey.turkeydevutil.client.renderer;

import dev.theturkey.turkeydevutil.TDUCore;
import dev.theturkey.turkeydevutil.client.ClientHandler;
import dev.theturkey.turkeydevutil.entities.Duck;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DuckRenderer extends MobRenderer<Duck, ChickenModel<Duck>>
{
	private static final ResourceLocation DUCK_TEXTURES = new ResourceLocation(TDUCore.MOD_ID, "textures/entity/duck.png");

	public DuckRenderer(Context context)
	{
		super(context, new ChickenModel<>(context.bakeLayer(ClientHandler.DUCK)), 0.3F);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull Duck entity)
	{
		return DUCK_TEXTURES;
	}

	@Override
	protected float getBob(Duck duck, float val)
	{
		float f = Mth.lerp(val, duck.oFlap, duck.flap);
		float f1 = Mth.lerp(val, duck.oFlapSpeed, duck.flapSpeed);
		return (Mth.sin(f) + 1.0F) * f1;
	}
}