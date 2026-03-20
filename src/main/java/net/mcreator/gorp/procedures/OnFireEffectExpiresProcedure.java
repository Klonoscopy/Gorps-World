package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

public class OnFireEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.clearFire();
	}
}