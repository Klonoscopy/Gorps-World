package world.gorps.gorp.effect;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import world.gorps.gorp.network.basic.JorpEffectPayload;

class JorpMobEffect extends MobEffect {
    protected JorpMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xe9b8b3);
    }

    @Override
    public boolean applyEffectTick(final ServerLevel level, final LivingEntity mob, final int amplifier) {
        float jorpPower = (amplifier * 0.08F) + (!mob.isInWater() ? 0.44F : 0.2F);
        if (mob instanceof ServerPlayer player) {
            ServerPlayNetworking.send(player, new JorpEffectPayload(jorpPower));
        } else {
            mob.setDeltaMovement(mob.getDeltaMovement().with(Direction.Axis.Y, jorpPower));
            mob.hurtMarked = true;
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(final int tickCount, final int amplification) {
        return true;
    }
}
