// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 5.0.3 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class gorp_buddyAnimation {
	public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("bone",
					new AnimationChannel(AnimationChannel.Targets.SCALE,
							new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.8F, 1.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1667F, KeyframeAnimations.scaleVec(1.0F, 0.5958F, 1.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4167F, KeyframeAnimations.scaleVec(1.0F, 0.8F, 1.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(0.6667F).looping()
			.addAnimation("bone",
					new AnimationChannel(AnimationChannel.Targets.POSITION,
							new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2083F, KeyframeAnimations.posVec(0.0F, 0.67F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, 11.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.375F, KeyframeAnimations.posVec(0.0F, 14.23F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4583F, KeyframeAnimations.posVec(0.0F, 13.11F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5417F, KeyframeAnimations.posVec(0.0F, 7.86F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone",
					new AnimationChannel(AnimationChannel.Targets.SCALE,
							new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.0833F, KeyframeAnimations.scaleVec(1.4F, 0.2F, 1.4F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2083F, KeyframeAnimations.scaleVec(1.04F, 0.652F, 1.04F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.scaleVec(0.8F, 1.52F, 0.8F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4167F, KeyframeAnimations.scaleVec(0.975F, 0.88F, 0.975F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.scaleVec(0.9F, 1.32F, 0.9F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();
}