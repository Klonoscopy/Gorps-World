package world.gorps.gorp.item;

import java.util.List;

import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import world.gorps.gorp.effect.GorpsWorldMobEffects;
import world.gorps.gorp.enchantment.GorpEnchantmentHelper;
import world.gorps.gorp.sound.CustomSounds;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GorpHammerItem extends Item {

    public GorpHammerItem(final Item.Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2, false);
    }
    public static void makeEntityJorp(LivingEntity entity, final int power) {
        var instance = new MobEffectInstance(GorpsWorldMobEffects.JORP, 1, power, false, false, false);
        entity.addEffect(instance);
    }

    @Override
    public void hurtEnemy(final ItemStack itemStack, final LivingEntity mob, final LivingEntity attacker) {
        if (canSmashAttack(attacker, itemStack)) {
            ServerLevel level = (ServerLevel) attacker.level();
            int enchantLevel = GorpEnchantmentHelper.getJorpDraftLevel(attacker) + 1;
            makeEntityJorp(attacker, enchantLevel);
            makeEntityJorp(mob, enchantLevel);
            attacker.setIgnoreFallDamageFromCurrentImpulse(true, this.calculateImpactPosition(attacker));
            if (attacker instanceof ServerPlayer player) {
                MinecraftServer server = level.getServer();
                player.connection.send(new ClientboundSetEntityMotionPacket(player));
                player.setSpawnExtraParticlesOnFall(false);
                server.getCommands().performPrefixedCommand(
                    player.createCommandSourceStack()
                        .withPermission(PermissionSet.ALL_PERMISSIONS)
                        .withSuppressedOutput(),
                    "stopsound @s * minecraft:entity.player.attack.strong"
                );
            }
            SoundEvent sound = attacker.fallDistance >= 4.0 ? CustomSounds.CRITICAL_JONK : CustomSounds.JONK;
            level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), sound, attacker.getSoundSource(), 1.0F, 1.0F);
        }
    }

    private Vec3 calculateImpactPosition(final LivingEntity attacker) {
        return attacker.isIgnoringFallDamageFromCurrentImpulse() && attacker.currentImpulseImpactPos.y <= attacker.position().y
                ? attacker.currentImpulseImpactPos
                : attacker.position();
    }

    @Override
    public void postHurtEnemy(final ItemStack itemStack, final LivingEntity mob, final LivingEntity attacker) {
        if (canSmashAttack(attacker, itemStack)) {
            attacker.resetFallDistance();
        }
    }

    @Override
    public float getAttackDamageBonus(final Entity victim, final float ignoredDamage, final DamageSource damageSource) {
        if (damageSource.getDirectEntity() instanceof LivingEntity attacker) {
            if (attacker.onGround()) {
                return 0.0F;
            } else {
                double fallHeightThreshold1 = 3.0;
                double fallHeightThreshold2 = 8.0;
                double fallDistance = attacker.fallDistance;
                double damage;
                if (fallDistance <= fallHeightThreshold1) {
                    damage = 4.0 * fallDistance;
                } else if (fallDistance <= fallHeightThreshold2) {
                    damage = 12.0 + 2.0 * (fallDistance - fallHeightThreshold1);
                } else {
                    damage = 22.0 + fallDistance - fallHeightThreshold2;
                }

                return attacker.level() instanceof ServerLevel level
                        ? (float)(damage + EnchantmentHelper.modifyFallBasedDamage(level, attacker.getWeaponItem(), victim, damageSource, 0.0F) * fallDistance)
                        : (float)damage;
            }
        } else {
            return 0.0F;
        }
    }

    public static boolean canSmashAttack(final LivingEntity attacker, final ItemStack itemStack) {
        if (attacker instanceof Player player) {
            return (Math.abs(player.getDeltaMovement().y) > 1e-5)
                    && !player.onClimbable()
                    && !player.isPassenger()
                    && !(player.getCooldowns().isOnCooldown(itemStack))
                    || player.isSprinting()
                    && !player.isPassenger()
                    && !(player.getCooldowns().isOnCooldown(itemStack));
        } else return !attacker.onClimbable()
                && !attacker.isInWater()
                && !attacker.isPassenger();
    }

    @Override
    public DamageSource getItemDamageSource(final LivingEntity attacker) {
        return attacker.fallDistance > 0.0F ? attacker.damageSources().genericKill() : super.getItemDamageSource(attacker);
    }
}