package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.entity.HostileBreadEntity;

public class HostileBreadPBDisplayConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof HostileBreadEntity _datEntL0 && _datEntL0.getEntityData().get(HostileBreadEntity.DATA_peanutbutter);
	}
}