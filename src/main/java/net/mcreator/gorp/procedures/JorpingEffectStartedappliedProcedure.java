package net.mcreator.gorp.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.gorp.init.GorpModParticleTypes;
import net.mcreator.gorp.init.GorpModMobEffects;
import net.mcreator.gorp.init.GorpModItems;
import net.mcreator.gorp.init.GorpModEnchantments;

public class JorpingEffectStartedappliedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (EnchantmentHelper.getItemEnchantmentLevel(GorpModEnchantments.JORP.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == GorpModItems.GORP_HAMMER.get()) {
				entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (0.3 + ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(GorpModEnchantments.JORP.get())
						+ (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0)) / 10), (entity.getDeltaMovement().z())));
			} else {
				entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (0.2 + ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(GorpModEnchantments.JORP.get())
						+ (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0)) / 10), (entity.getDeltaMovement().z())));
			}
		} else {
			entity.setDeltaMovement(
					new Vec3((entity.getDeltaMovement().x()), (0.25 + ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GorpModMobEffects.JORPING.get()) ? _livEnt.getEffect(GorpModMobEffects.JORPING.get()).getAmplifier() : 0)
							+ (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0)) * 0.1), (entity.getDeltaMovement().z())));
		}
		for (int index0 = 0; index0 < 2; index0++) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (GorpModParticleTypes.GORP_SUDS.get()), x, y, z, 3, 1, 1, 1, 1);
		}
	}
}