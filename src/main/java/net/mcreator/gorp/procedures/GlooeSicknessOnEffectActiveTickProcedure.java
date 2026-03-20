package net.mcreator.gorp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.gorp.init.GorpModMobEffects;

public class GlooeSicknessOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		String key = "";
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GorpModMobEffects.GLOOE_SICKNESS.get()) ? _livEnt.getEffect(GorpModMobEffects.GLOOE_SICKNESS.get()).getDuration() : 0) % 200 == 0) {
			key = "dialogue.glooe." + Mth.nextInt(RandomSource.create(), 0, 6);
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("<" + entity.getDisplayName().getString() + "> " + Component.translatable(key).getString())), false);
			}
		}
	}
}