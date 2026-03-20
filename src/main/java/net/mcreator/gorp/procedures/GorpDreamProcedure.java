package net.mcreator.gorp.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GorpDreamProcedure {
	@SubscribeEvent
	public static void onEntityEndSleep(PlayerWakeUpEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double rng1 = 0;
		double rng2 = 0;
		if (entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel _serverLevel0
				&& _plr0.getAdvancements().getOrStartProgress(_serverLevel0.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:gorpcess_denied"))).isDone()) {
			if (!(entity instanceof ServerPlayer _plr1 && _plr1.level() instanceof ServerLevel _serverLevel1
					&& _plr1.getAdvancements().getOrStartProgress(_serverLevel1.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:mythical_gorpiphany"))).isDone())) {
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
										_ent.level().getServer(), _ent),
								("/tellraw @a [\"\",{\"text\":\"<" + "" + entity.getDisplayName().getString()
										+ "> I had the weirdest dream last night! The weird green thing was there, and he said something to me with such utmost importance... What could it mean?\",\"color\":\"white\"},{\"text\":\"\\n\"},{\"text\":\"The Gorp Altar is the key.\",\"italic\":true,\"color\":\"#84C949\"}]"));
					}
				}
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("gorp:mythical_gorpiphany"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
				if (entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("gorp:gorp_altar_recipe")});
			} else if (entity instanceof ServerPlayer _plr6 && _plr6.level() instanceof ServerLevel _serverLevel6
					&& _plr6.getAdvancements().getOrStartProgress(_serverLevel6.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:gorp_speaks"))).isDone() && !(entity instanceof ServerPlayer _plr7
							&& _plr7.level() instanceof ServerLevel _serverLevel7 && _plr7.getAdvancements().getOrStartProgress(_serverLevel7.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:permission_gorped"))).isDone())) {
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
										_ent.level().getServer(), _ent),
								("/tellraw @a [\"\",{\"text\":\"<" + "" + entity.getDisplayName().getString()
										+ "> \",\"color\":\"white\"},{\"text\":\"Gorp\",\"color\":\"green\",\"hoverEvent\":{\"action\":\"show_text\",\"contents\":\"The green little fella!\"}},{\"text\":\" appeared in my dream again! He seemed friendlier this time. I can still hear his words echoing in my head, as if it were a call to action... Is this what I think it means?\",\"color\":\"white\"},{\"text\":\"\\n\"},{\"text\":\"Let me eat your gorpy layer.\",\"italic\":true,\"color\":\"#84C949\"}]"));
					}
				}
			}
		}
	}
}