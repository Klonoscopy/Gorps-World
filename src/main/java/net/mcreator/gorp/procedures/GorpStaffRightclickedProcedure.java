package net.mcreator.gorp.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.gorp.init.GorpModEnchantments;

import java.util.Comparator;

public class GorpStaffRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double Knockback = 0;
		if (EnchantmentHelper.getItemEnchantmentLevel(GorpModEnchantments.JORP_DRAFT.get(), itemstack) != 0) {
			Knockback = (-2) - itemstack.getEnchantmentLevel(GorpModEnchantments.JORP_DRAFT.get()) / 2;
		} else {
			Knockback = -2;
		}
		SoundChompProcedure.execute(world, entity);
		world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, x, (y + 2), z, 0, 2, 0);
		{
			final Vec3 _center = new Vec3(x, (y + 1.5), z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (entityiterator instanceof Phantom) {
					if (entityiterator instanceof LivingEntity _entity)
						_entity.setHealth(-1);
				}
				if (!(entityiterator == entity) && entityiterator instanceof Mob && !(entity instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false)) {
					if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, (int) Double.POSITIVE_INFINITY, -10, false, false));
					entityiterator.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())));
					entityiterator.setDeltaMovement(new Vec3((Math.sin(entityiterator.getYRot() * (-1) * 0.017453292) * Math.cos(entityiterator.getXRot() * 0.017453292) * Knockback),
							(Math.sin(entityiterator.getXRot() * 0.017453292) * (-1) * Knockback), (Math.cos(entityiterator.getYRot() * (-1) * 0.017453292) * Math.cos(entityiterator.getXRot() * 0.017453292) * Knockback)));
				}
			}
		}
		if (entity instanceof Player _player)
			_player.getCooldowns().addCooldown(itemstack.getItem(), 60);
	}
}