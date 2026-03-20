package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

public class GorpasolOnPlayerStoppedUsingProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setNoGravity(false);
	}
}