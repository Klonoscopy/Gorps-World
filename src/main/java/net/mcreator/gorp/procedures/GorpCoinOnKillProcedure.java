package net.mcreator.gorp.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.gorp.init.GorpModItems;
import net.mcreator.gorp.init.GorpModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GorpCoinOnKillProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity, Entity sourceentity) {
		execute(null, world, damagesource, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity, Entity sourceentity) {
		if (damagesource == null || entity == null || sourceentity == null)
			return;
		if (!world.getLevelData().getGameRules().getBoolean(GorpModGameRules.DISABLE_GORP_COIN_DROPS) && sourceentity instanceof ServerPlayer _plr1 && _plr1.level() instanceof ServerLevel _serverLevel1
				&& _plr1.getAdvancements().getOrStartProgress(_serverLevel1.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:permission_gorped"))).isDone()) {
			if (damagesource.is(DamageTypes.PLAYER_ATTACK)) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, (entity.getX()), (entity.getY()), (entity.getZ()), new ItemStack(GorpModItems.GORP_COIN.get()));
					entityToSpawn.setPickUpDelay(0);
					_level.addFreshEntity(entityToSpawn);
				}
			}
		}
	}
}