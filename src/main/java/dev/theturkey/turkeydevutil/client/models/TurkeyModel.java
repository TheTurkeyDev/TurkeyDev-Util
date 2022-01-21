// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports
package dev.theturkey.turkeydevutil.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class TurkeyModel<T extends Entity> extends EntityModel<T>
{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart main_Body;
	private final ModelPart right_Wing;
	private final ModelPart left_Wing;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart back_Wing;
	private final ModelPart lower_Top_Body;
	private final ModelPart beak;
	private final ModelPart tail;
	private final ModelPart right_Leg;
	private final ModelPart left_Leg;
	private final ModelPart right_Foot;
	private final ModelPart left_Foot;

	public TurkeyModel(ModelPart root)
	{
		this.main_Body = root.getChild("main_Body");
		this.right_Wing = root.getChild("right_Wing");
		this.left_Wing = root.getChild("left_Wing");
		this.neck = root.getChild("neck");
		this.head = root.getChild("head");
		this.back_Wing = root.getChild("back_Wing");
		this.lower_Top_Body = root.getChild("lower_Top_Body");
		this.beak = root.getChild("beak");
		this.tail = root.getChild("tail");
		this.right_Leg = root.getChild("right_Leg");
		this.left_Leg = root.getChild("left_Leg");
		this.right_Foot = root.getChild("right_Foot");
		this.left_Foot = root.getChild("left_Foot");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("main_Body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 20.0F, 9.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 11.0F, -6.0F));
		partdefinition.addOrReplaceChild("right_Wing", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 12.0F, -7.0F));
		partdefinition.addOrReplaceChild("left_Wing", CubeListBuilder.create().texOffs(66, 7).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, 12.0F, 6.8667F));
		partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 45).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 8.0F, -1.0F, 0.0F, 0.0F, 0.2603F));
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(10, 45).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 7.0F, 1.0F));
		partdefinition.addOrReplaceChild("back_Wing", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(0.0F, 0.0F, 0.0F, 15.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 10.0F, -5.0F));
		partdefinition.addOrReplaceChild("lower_Top_Body", CubeListBuilder.create().texOffs(0, 34).mirror().addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 8.0F, -4.0F));
		partdefinition.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-5.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 7.0F, 1.0F));
		partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(88, 30).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 14.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(15.0F, 3.0F, -9.0F, 0.0F, 0.0F, 0.4833F));
		partdefinition.addOrReplaceChild("right_Leg", CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 20.0F, -3.0F));
		partdefinition.addOrReplaceChild("left_Leg", CubeListBuilder.create().texOffs(2, 55).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 20.0F, 3.0F));
		partdefinition.addOrReplaceChild("right_Foot", CubeListBuilder.create().texOffs(0, 60).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 24.0F, -3.0F));
		partdefinition.addOrReplaceChild("left_Foot", CubeListBuilder.create().texOffs(0, 61).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 24.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.zRot = headPitch / (180F / (float) Math.PI);
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.beak.zRot = this.head.zRot;
		this.beak.yRot = this.head.yRot;
		this.right_Leg.zRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_Foot.zRot = this.right_Leg.zRot;
		this.left_Leg.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		//this.left_Foot.zRot = this.left_Foot.zRot;
		this.right_Wing.xRot = netHeadYaw;
		this.left_Wing.xRot = -netHeadYaw;
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		main_Body.render(poseStack, buffer, packedLight, packedOverlay);
		right_Wing.render(poseStack, buffer, packedLight, packedOverlay);
		left_Wing.render(poseStack, buffer, packedLight, packedOverlay);
		neck.render(poseStack, buffer, packedLight, packedOverlay);
		head.render(poseStack, buffer, packedLight, packedOverlay);
		back_Wing.render(poseStack, buffer, packedLight, packedOverlay);
		lower_Top_Body.render(poseStack, buffer, packedLight, packedOverlay);
		beak.render(poseStack, buffer, packedLight, packedOverlay);
		tail.render(poseStack, buffer, packedLight, packedOverlay);
		right_Leg.render(poseStack, buffer, packedLight, packedOverlay);
		left_Leg.render(poseStack, buffer, packedLight, packedOverlay);
		right_Foot.render(poseStack, buffer, packedLight, packedOverlay);
		left_Foot.render(poseStack, buffer, packedLight, packedOverlay);
	}
}