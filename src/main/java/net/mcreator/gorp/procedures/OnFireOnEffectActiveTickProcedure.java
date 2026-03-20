package net.mcreator.gorp.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class OnFireOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 5, 1.5, 2, 1.5, 1);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.FLAME, x, y, z, 3, 1.5, 2, 1.5, 1);
		if (!entity.isOnFire()) {
			entity.setSecondsOnFire(1);
		}
		entity.setDeltaMovement(new Vec3((Math.sin(entity.getYRot() * (-1) * 0.017453292) * Math.cos(entity.getXRot() * 0.017453292) * 0.32 + entity.getDeltaMovement().x()), (entity.getDeltaMovement().y()),
				(Math.cos(entity.getYRot() * (-1) * 0.017453292) * Math.cos(entity.getXRot() * 0.017453292) * 0.32 + entity.getDeltaMovement().z())));
		if (Blocks.FIRE.defaultBlockState().canSurvive(world, BlockPos.containing(x, y, z))) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.FIRE.defaultBlockState(), 3);
		}
	}
}