package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;

public class GorpInspirationEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.double_jorp_active = false;
				capability.markSyncDirty();
			});
		}
	}
}