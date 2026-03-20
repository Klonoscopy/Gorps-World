package net.mcreator.gorp.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.gorp.init.GorpModItems;
import net.mcreator.gorp.init.GorpModEntities;

public class ConsolidatedGorpCubeRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.DOWN) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = GorpModEntities.GORP_BUDDY.get().spawn(_level, BlockPos.containing(x, y - 0.3, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
				}
			}
		} else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.UP) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = GorpModEntities.GORP_BUDDY.get().spawn(_level, BlockPos.containing(x, y + 1.2, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
				}
			}
		} else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.NORTH) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = GorpModEntities.GORP_BUDDY.get().spawn(_level, BlockPos.containing(x, y, z - 1), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
				}
			}
		} else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.SOUTH) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = GorpModEntities.GORP_BUDDY.get().spawn(_level, BlockPos.containing(x, y, z + 1.2), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
				}
			}
		} else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.WEST) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = GorpModEntities.GORP_BUDDY.get().spawn(_level, BlockPos.containing(x - 1, y, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
				}
			}
		} else if ((entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(10)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getDirection()) == Direction.EAST) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = GorpModEntities.GORP_BUDDY.get().spawn(_level, BlockPos.containing(x + 1, y, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
				}
			}
		}
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(GorpModItems.CONSOLIDATED_GORP_CUBE.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
		}
		if (!world.isClientSide()) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:gorp_summon")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:gorp_summon")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}