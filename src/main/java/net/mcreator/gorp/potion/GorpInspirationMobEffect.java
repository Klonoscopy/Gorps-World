package net.mcreator.gorp.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.procedures.GorpShirtTickEventProcedure;
import net.mcreator.gorp.procedures.GorpInspirationEffectStartedappliedProcedure;
import net.mcreator.gorp.procedures.GorpInspirationEffectExpiresProcedure;

public class GorpInspirationMobEffect extends MobEffect {
	public GorpInspirationMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3342490);
		this.addAttributeModifier(Attributes.JUMP_STRENGTH, "c1e4835a-bc07-395e-ab6d-9a6ee3cddae2", 0.1, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		GorpInspirationEffectStartedappliedProcedure.execute(entity);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		GorpShirtTickEventProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		GorpInspirationEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}