package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;

public class GorpophoneScreenValueProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String txt = "";
		return entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).gorpophone_tooltip_text;
	}
}