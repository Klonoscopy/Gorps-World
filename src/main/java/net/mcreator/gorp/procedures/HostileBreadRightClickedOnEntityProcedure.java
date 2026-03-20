package net.mcreator.gorp.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.gorp.entity.HostileBreadEntity;

public class HostileBreadRightClickedOnEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double peanutbutter = 0;
		if ((entity instanceof HostileBreadEntity _datEntL0 && _datEntL0.getEntityData().get(HostileBreadEntity.DATA_peanutbutter)) == false
				&& (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.GLOW_BERRIES && (entity instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false)) {
			if (entity instanceof HostileBreadEntity _datEntSetL)
				_datEntSetL.getEntityData().set(HostileBreadEntity.DATA_peanutbutter, true);
			if (sourceentity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(Items.GLOW_BERRIES);
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) ((entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) ? 0 : 1), _player.inventoryMenu.getCraftSlots());
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.FALLING_HONEY, x, y, z, 5, 3, 3, 3, 1);
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.slide")), SoundSource.NEUTRAL, 1,
								(float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.slide")), SoundSource.NEUTRAL, 1,
								(float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
					}
				}
			}
		} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.SWEET_BERRIES) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.FALLING_LAVA, x, y, z, 5, 3, 3, 3, 1);
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.slide")), SoundSource.NEUTRAL, 1,
								(float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.honey_block.slide")), SoundSource.NEUTRAL, 1,
								(float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
					}
				}
			}
		}
	}
}