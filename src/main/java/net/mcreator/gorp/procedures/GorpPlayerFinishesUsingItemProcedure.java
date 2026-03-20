package net.mcreator.gorp.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.gorp.network.GorpModVariables;
import net.mcreator.gorp.init.GorpModParticleTypes;
import net.mcreator.gorp.GorpMod;

public class GorpPlayerFinishesUsingItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		{
			entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.gorp_count = entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).gorp_count + 1;
				capability.markSyncDirty();
			});
		}
		if (entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).bitten_gorp_count >= 100
				&& entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).gorp_count >= 100) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("gorp:gorpivore"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
		GorpMod.queueServerWork(5, () -> {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:gulp")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:gulp")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		});
		entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.5), (entity.getDeltaMovement().y() + 0.48), (entity.getDeltaMovement().z() * 1.5)));
		for (int index0 = 0; index0 < 3; index0++) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (GorpModParticleTypes.GORP_SUDS.get()), x, y, z, 4, 2, 2, 2, 1);
		}
		if (entity instanceof Player _player)
			_player.getCooldowns().addCooldown(itemstack.getItem(), 10);
	}
}