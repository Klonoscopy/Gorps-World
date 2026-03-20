package net.mcreator.gorp.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;

public class FrozenOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setTicksFrozen(140);
		entity.setDeltaMovement(new Vec3(0, (entity.getDeltaMovement().y()), 0));
	}
}