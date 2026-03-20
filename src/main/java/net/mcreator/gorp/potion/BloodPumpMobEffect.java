package net.mcreator.gorp.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.procedures.BloodPumpEffectStartedappliedProcedure;
import net.mcreator.gorp.procedures.BloodPumpEffectExpiresProcedure;
import net.mcreator.gorp.procedures.BloodPumpEffectDisplayOverlayIngameProcedure;

public class BloodPumpMobEffect extends MobEffect {
	public BloodPumpMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3484642);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "c36ad63e-06ab-33a5-805d-274f1fe7a3a4", 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "60577f5b-07c9-3cbb-9b2b-80592594a01f", 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		BloodPumpEffectStartedappliedProcedure.execute(entity.level(), entity);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		BloodPumpEffectDisplayOverlayIngameProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		BloodPumpEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}