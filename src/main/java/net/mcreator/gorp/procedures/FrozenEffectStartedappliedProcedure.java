package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.gorp.init.GorpModMobEffects;

public class FrozenEffectStartedappliedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GorpModMobEffects.FROZEN.get()) ? _livEnt.getEffect(GorpModMobEffects.FROZEN.get()).getDuration() : 0, 9,
					false, false));
	}
}