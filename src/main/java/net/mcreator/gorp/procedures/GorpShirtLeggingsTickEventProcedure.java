package net.mcreator.gorp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.gorp.network.GorpModVariables;
import net.mcreator.gorp.init.GorpModMobEffects;
import net.mcreator.gorp.init.GorpModItems;

public class GorpShirtLeggingsTickEventProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		boolean isoverlayon = false;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(GorpModMobEffects.JORP_FOOTED.get(), 50, 0, false, false));
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_BOOTS.get()) {
			if (!(entity instanceof LivingEntity _livEnt7 && _livEnt7.hasEffect(GorpModMobEffects.BLOOD_PUMP.get()))) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(GorpModMobEffects.BLOOD_PUMP.get(), 100, 0, false, false));
			}
			isoverlayon = true;
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.OutOfCombatCooldown = Math.max(entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).OutOfCombatCooldown - 1, 0);
					capability.markSyncDirty();
				});
			}
		} else {
			isoverlayon = false;
		}
		return isoverlayon;
	}
}