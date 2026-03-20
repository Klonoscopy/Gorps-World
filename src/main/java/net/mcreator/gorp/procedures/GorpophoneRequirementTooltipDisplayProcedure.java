package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;

public class GorpophoneRequirementTooltipDisplayProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).gorpophone_slot_count == 0;
	}
}