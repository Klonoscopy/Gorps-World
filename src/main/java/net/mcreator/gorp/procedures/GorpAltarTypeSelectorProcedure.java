package net.mcreator.gorp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.gorp.init.GorpModBlocks;

public class GorpAltarTypeSelectorProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == GorpModBlocks.GORP_ALTAR_FULL.get();
	}
}