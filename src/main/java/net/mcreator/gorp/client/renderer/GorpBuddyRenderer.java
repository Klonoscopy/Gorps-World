package net.mcreator.gorp.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.gorp.entity.GorpBuddyEntity;
import net.mcreator.gorp.client.model.animations.gorp_buddyAnimation;
import net.mcreator.gorp.client.model.Modelgorp_buddy;

import com.mojang.blaze3d.vertex.PoseStack;

public class GorpBuddyRenderer extends MobRenderer<GorpBuddyEntity, Modelgorp_buddy<GorpBuddyEntity>> {
	public GorpBuddyRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelgorp_buddy.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(GorpBuddyEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(1.5f, 1.5f, 1.5f);
		poseStack.scale(entity.getScale(), entity.getScale(), entity.getScale());
	}

	@Override
	public ResourceLocation getTextureLocation(GorpBuddyEntity entity) {
		return new ResourceLocation("gorp:textures/entities/gorp_buddy.png");
	}

	private static final class AnimatedModel extends Modelgorp_buddy<GorpBuddyEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GorpBuddyEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GorpBuddyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(gorp_buddyAnimation.idle, limbSwing, limbSwingAmount, 2f, 3f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GorpBuddyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}