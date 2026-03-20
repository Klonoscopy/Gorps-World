package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;

public class BloodPumpEffectDisplayOverlayIngameProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double timescale = 0;
		if (entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).OutOfCombatCooldown == 0) {
			timescale = 0.04;
		} else {
			timescale = 0.018;
		}
		final double finalTimescale = timescale;
		{
			entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.BloodPumpOverlayFade = Math.max(entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).BloodPumpOverlayFade - finalTimescale, 0);
				capability.markSyncDirty();
			});
		}
	}
}