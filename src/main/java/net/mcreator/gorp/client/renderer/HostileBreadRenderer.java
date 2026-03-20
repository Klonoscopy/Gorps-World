package net.mcreator.gorp.client.renderer;

import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.gorp.procedures.HostileBreadPBDisplayConditionProcedure;
import net.mcreator.gorp.procedures.HostileBreadJamDisplayConditionProcedure;
import net.mcreator.gorp.entity.HostileBreadEntity;
import net.mcreator.gorp.client.model.animations.hostile_breadAnimation;
import net.mcreator.gorp.client.model.Modelhostile_bread;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class HostileBreadRenderer extends MobRenderer<HostileBreadEntity, Modelhostile_bread<HostileBreadEntity>> {
	public HostileBreadRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelhostile_bread.LAYER_LOCATION)), 1f);
		this.addLayer(new RenderLayer<HostileBreadEntity, Modelhostile_bread<HostileBreadEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("gorp:textures/entities/hostile_bread_jam.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, HostileBreadEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (HostileBreadJamDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<HostileBreadEntity, Modelhostile_bread<HostileBreadEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("gorp:textures/entities/hostile_bread_pb.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, HostileBreadEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (HostileBreadPBDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
	}

	@Override
	protected void scale(HostileBreadEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getScale(), entity.getScale(), entity.getScale());
	}

	@Override
	public ResourceLocation getTextureLocation(HostileBreadEntity entity) {
		return new ResourceLocation("gorp:textures/entities/hostile_bread.png");
	}

	private static final class AnimatedModel extends Modelhostile_bread<HostileBreadEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<HostileBreadEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(HostileBreadEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, hostile_breadAnimation.death, ageInTicks, 1f);
				this.animateWalk(hostile_breadAnimation.walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, hostile_breadAnimation.idle, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(HostileBreadEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}