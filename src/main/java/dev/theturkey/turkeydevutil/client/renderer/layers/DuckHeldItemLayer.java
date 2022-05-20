package dev.theturkey.turkeydevutil.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import dev.theturkey.turkeydevutil.client.renderer.model.DuckModel;
import dev.theturkey.turkeydevutil.entities.Duck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DuckHeldItemLayer extends RenderLayer<Duck, DuckModel<Duck>>
{
	public DuckHeldItemLayer(RenderLayerParent<Duck, DuckModel<Duck>> parent)
	{
		super(parent);
	}

	@Override
	public void render(PoseStack pose, @NotNull MultiBufferSource bufferSrc, int p_117009_, Duck duck, float p_117011_, float p_117012_, float p_117013_, float p_117014_, float p_117015_, float p_117016_)
	{
		boolean flag1 = duck.isBaby();
		pose.pushPose();
		if(flag1)
		{
			float scale = 0.75F;
			pose.scale(scale, scale, scale);
			pose.translate(0.0D, 0.5D, 0.209375F);
		}

		pose.translate((this.getParentModel()).head.x / 16.0F, (this.getParentModel()).head.y / 16.0F, (this.getParentModel()).head.z / 16.0F);
		pose.mulPose(Vector3f.YP.rotationDegrees(p_117015_));
		pose.mulPose(Vector3f.XP.rotationDegrees(p_117016_));
		if(duck.isBaby())
			pose.translate(0.06F, 0.26F, -0.5D);
		else
			pose.translate(0.15F, -0.15F, -0.4D);

		pose.mulPose(Vector3f.YP.rotationDegrees(60.0F));
		pose.mulPose(Vector3f.XP.rotationDegrees(90.0F));

		ItemStack itemstack = duck.getItemBySlot(EquipmentSlot.MAINHAND);
		Minecraft.getInstance().getItemInHandRenderer().renderItem(duck, itemstack, ItemTransforms.TransformType.GROUND, false, pose, bufferSrc, p_117009_);
		pose.popPose();
	}
}