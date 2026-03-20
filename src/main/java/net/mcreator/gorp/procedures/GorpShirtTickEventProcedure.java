package net.mcreator.gorp.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GorpShirtTickEventProcedure {
	@SubscribeEvent
	public static void onEntityFall(LivingFallEvent event) {
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
		if (entity.getCapability(GorpModVariables.PLAYER_VARIABLES).orElseGet(GorpModVariables.PlayerVariables::new).let_jorp != 0) {
			if (entity.onGround()) {
				{
					entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.let_jorp = 0;
						capability.markSyncDirty();
					});
				}
			}
		}
	}
}