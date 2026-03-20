package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;

public class JorpkeyOnKeyReleasedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.onGround()) {
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.let_jorp = 0;
					capability.markSyncDirty();
				});
			}
		} else if (entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).let_jorp == 2) {
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.let_jorp = 2;
					capability.markSyncDirty();
				});
			}
		} else if (entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).let_jorp == 3) {
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.let_jorp = 3;
					capability.markSyncDirty();
				});
			}
		} else {
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.let_jorp = 1;
					capability.markSyncDirty();
				});
			}
		}
	}
}