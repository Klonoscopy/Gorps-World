package net.mcreator.gorp.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.procedures.FrozenOnEffectActiveTickProcedure;
import net.mcreator.gorp.procedures.FrozenEffectStartedappliedProcedure;
import net.mcreator.gorp.procedures.FrozenEffectExpiresProcedure;

public class FrozenMobEffect extends MobEffect {
	public FrozenMobEffect() {
		super(MobEffectCategory.HARMFUL, -3342337);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		FrozenEffectStartedappliedProcedure.execute(entity);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		FrozenOnEffectActiveTickProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		FrozenEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}