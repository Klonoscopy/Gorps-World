package net.mcreator.gorp.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.procedures.JorpingEffectStartedappliedProcedure;

public class JorpingMobEffect extends MobEffect {
	public JorpingMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -6695909);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		JorpingEffectStartedappliedProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}