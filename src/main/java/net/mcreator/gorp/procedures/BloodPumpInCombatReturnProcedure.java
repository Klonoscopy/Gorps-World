package net.mcreator.gorp.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BloodPumpInCombatReturnProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.OutOfCombatCooldown = 100;
					capability.markSyncDirty();
				});
			}
		}
	}
}