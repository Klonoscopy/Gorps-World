package net.mcreator.gorp.procedures;

import net.minecraft.world.entity.Entity;

public class FrozenEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setTicksFrozen(0);
	}
}