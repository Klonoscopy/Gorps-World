package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

public class GorpasolRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!entity.onGround()) {
			entity.setNoGravity(true);
			entity.fallDistance = 4;
		}
	}
}