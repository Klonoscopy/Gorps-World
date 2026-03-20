package net.mcreator.gorp.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.gorp.entity.GorplingEntity;
import net.mcreator.gorp.client.model.animations.gorplingAnimation;
import net.mcreator.gorp.client.model.Modelgorpling;

import com.mojang.blaze3d.vertex.PoseStack;

public class GorplingRenderer extends MobRenderer<GorplingEntity, Modelgorpling<GorplingEntity>> {
	public GorplingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelgorpling.LAYER_LOCATION)), 0.3f);
	}

	@Override
	protected void scale(GorplingEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(1.5f, 1.5f, 1.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GorplingEntity entity) {
		return new ResourceLocation("gorp:textures/entities/gorpling.png");
	}

	private static final class AnimatedModel extends Modelgorpling<GorplingEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GorplingEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GorplingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(gorplingAnimation.walk, limbSwing, limbSwingAmount, 1f, 2f);
				this.animate(entity.animationState1, gorplingAnimation.idle, ageInTicks, 2f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GorplingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}