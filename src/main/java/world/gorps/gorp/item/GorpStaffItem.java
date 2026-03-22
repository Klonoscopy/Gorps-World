package world.gorps.gorp.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class GorpStaffItem extends Item {
    public GorpStaffItem(final Item.Properties properties) {
        super(properties);
    }
    public static Tool createToolProperties() {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return new Tool(
                List.of(
                        Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                        Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.LEAVES), 15.0F),
                        Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.WOOL), 5.0F),
                        Tool.Rule.overrideSpeed(HolderSet.direct(Blocks.VINE.builtInRegistryHolder(), Blocks.GLOW_LICHEN.builtInRegistryHolder()), 2.0F)
                ),
                1.0F,
                1,
                true
        );
    }
    @Override
    public boolean mineBlock(final ItemStack itemStack, final Level level, final BlockState state, final BlockPos pos, final LivingEntity miner) {
        Tool tool = itemStack.get(DataComponents.TOOL);
        if (tool == null) {
            return false;
        } else {
            if (!level.isClientSide() && !state.is(BlockTags.FIRE) && tool.damagePerBlock() > 0) {
                itemStack.hurtAndBreak(tool.damagePerBlock(), miner, EquipmentSlot.MAINHAND);
            }

            return true;
        }
    }
    public InteractionResult use(final Level level, final Player player, final LivingEntity mob, final InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.nextDamageWillBreak()) {
            return InteractionResult.FAIL;
        } else {
            knockback(level, player, mob);
            player.startUsingItem(hand);
            return InteractionResult.SUCCESS;
        }
    }
    private static Predicate<LivingEntity> knockbackPredicate(final Entity attacker, final Entity entity) {
        return nearby -> {
            boolean notSpectator = !nearby.isSpectator();
            boolean notPlayer = nearby != attacker && nearby != entity;
            boolean notAlliedToPlayer = !attacker.isAlliedTo(nearby);
            boolean notTamedByPlayer = !(
                    nearby instanceof TamableAnimal animal && entity instanceof LivingEntity livingAttacker && animal.isTame() && animal.isOwnedBy(livingAttacker)
            );
            boolean notArmorStand = !(nearby instanceof ArmorStand armorStand && armorStand.isMarker());
            boolean withinRange = entity.distanceToSqr(nearby) <= Math.pow(3.5, 2.0);
            boolean notFlyingInCreative = !(nearby instanceof Player player && player.isCreative() && player.getAbilities().flying);
            return notSpectator && notPlayer && notAlliedToPlayer && notTamedByPlayer && notArmorStand && withinRange && notFlyingInCreative;
        };
    }
    private static double getKnockbackPower(final Entity attacker, final LivingEntity nearby, final Vec3 direction) {
        return (3.5 - direction.length()) * 0.7F * (attacker.fallDistance > 5.0 ? 2 : 1) * (1.0 - nearby.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
    }
    private static void knockback(final Level level, final Entity attacker, final Entity entity) {
        level.levelEvent(2013, entity.getOnPos(), 750);
        level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(3.5), knockbackPredicate(attacker, entity)).forEach(nearby -> {
            Vec3 direction = nearby.position().subtract(entity.position());
            double knockbackPower = getKnockbackPower(attacker, nearby, direction);
            Vec3 knockbackVector = direction.normalize().scale(knockbackPower);
            if (knockbackPower > 0.0) {
                nearby.push(knockbackVector.x, 0.7F, knockbackVector.z);
                if (nearby instanceof ServerPlayer otherPlayer) {
                    otherPlayer.connection.send(new ClientboundSetEntityMotionPacket(otherPlayer));
                }
            }
        });
    }
}
