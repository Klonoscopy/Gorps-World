package net.mcreator.gorp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.gorp.init.GorpModMobEffects;
import net.mcreator.gorp.init.GorpModItems;

public class BloodPumpEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_HELMET.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_CHESTPLATE.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_LEGGINGS.get()
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == GorpModItems.GORP_MERCH_BOOTS.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(GorpModMobEffects.BLOOD_PUMP.get(),
						(int) Math.round((Math.max(entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1, 4) / (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) * 40 + 3), 0, false, false));
		}
	}
}