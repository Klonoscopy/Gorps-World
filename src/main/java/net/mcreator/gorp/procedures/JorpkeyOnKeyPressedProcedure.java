package net.mcreator.gorp.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import net.mcreator.gorp.network.GorpModVariables;
import net.mcreator.gorp.init.GorpModMobEffects;
import net.mcreator.gorp.init.GorpModItems;

public class JorpkeyOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double jumps = 0;
		double JumpBoostValue = 0;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(GorpModMobEffects.JORP_FOOTED.get()) && entity.onGround()) {
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (JumpBoostValue + 2), (entity.getDeltaMovement().z())));
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_CHESTPLATE.get()
				|| entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).double_jorp_active == true) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0) == 0) {
				JumpBoostValue = 0.3;
			} else if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0) == 1) {
				JumpBoostValue = 0.5;
			} else if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0) == 2) {
				JumpBoostValue = 0.7;
			} else if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0) >= 3) {
				JumpBoostValue = (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0) * 0.5;
			} else {
				JumpBoostValue = 0.2;
			}
			if (entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).let_jorp != (entity instanceof LivingEntity _livEnt12 && _livEnt12.hasEffect(GorpModMobEffects.GORP_INSPIRATION.get())
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_CHESTPLATE.get() ? 3 : 2)) {
				if (entity.getDeltaMovement().y() != 0) {
					entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.2), (JumpBoostValue + 0.1), (entity.getDeltaMovement().z() * 1.2)));
					{
						entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
							capability.let_jorp = entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).let_jorp + 1;
							capability.markSyncDirty();
						});
					}
				}
			}
		}
	}
}