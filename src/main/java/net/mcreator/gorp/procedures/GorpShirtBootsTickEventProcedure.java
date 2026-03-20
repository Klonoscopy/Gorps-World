package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

public class GorpShirtBootsTickEventProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.fallDistance = 2;
	}
}