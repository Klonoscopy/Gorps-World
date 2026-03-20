package net.mcreator.gorp.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.procedures.IronHDamageOnEffectActiveTickProcedure;
import net.mcreator.gorp.procedures.IronHDamageEffectStartedappliedProcedure;

public class IronHDamageMobEffect extends MobEffect {
	public IronHDamageMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -8647136);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		IronHDamageEffectStartedappliedProcedure.execute(entity.level(), entity);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		IronHDamageOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}