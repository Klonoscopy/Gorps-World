package net.mcreator.gorp.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.gorp.init.GorpModEntities;
import net.mcreator.gorp.entity.HostileBreadEntity;

import java.util.Comparator;

public class HostileBreadOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double ingredients = 0;
		Entity jam = null;
		Entity peanut_butter = null;
		ingredients = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (entityiterator instanceof HostileBreadEntity && (entityiterator instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false)
						&& !(entityiterator instanceof HostileBreadEntity _datEntL2 && _datEntL2.getEntityData().get(HostileBreadEntity.DATA_peanutbutter))) {
					jam = entityiterator;
					ingredients = 1;
				} else if (entityiterator instanceof HostileBreadEntity _datEntL3 && _datEntL3.getEntityData().get(HostileBreadEntity.DATA_peanutbutter) && ingredients == 1) {
					peanut_butter = entityiterator;
					ingredients = 2;
				}
			}
		}
		if (ingredients == 2) {
			if (!entity.level().isClientSide())
				entity.discard();
			if (!jam.level().isClientSide())
				jam.discard();
			if (!peanut_butter.level().isClientSide())
				peanut_butter.discard();
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = GorpModEntities.GORP_BUDDY.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setDeltaMovement(0, 0, 0);
				}
			}
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.place")), SoundSource.VOICE, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.place")), SoundSource.VOICE, 1, 1, false);
					}
				}
			}
		}
	}
}