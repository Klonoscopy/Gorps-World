package world.gorps.gorp.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import world.gorps.gorp.enchantment.GorpEnchantmentHelper;
import world.gorps.gorp.sound.CustomSounds;

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
                level.playSound(null, miner.getX(), miner.getY(), miner.getZ(), CustomSounds.CHOMP, miner.getSoundSource(), 1.0F, 1.0F);
            }

            return true;
        }
    }

    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), CustomSounds.CHOMP, player.getSoundSource(), 1.0F, 1.0F);
            knockback(level, player, player);
            player.getCooldowns().addCooldown(itemStack, 20);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }
    @Override
    public int getUseDuration(final ItemStack itemStack, final LivingEntity user) {
        return 20;
    }

    @Override
    public ItemUseAnimation getUseAnimation(final ItemStack itemStack) { return ItemUseAnimation.TOOT_HORN; }

    public static int enchantLevel(LivingEntity entity) {
        return GorpEnchantmentHelper.getJorpDraftLevel(entity);
    }

    private static Predicate<LivingEntity> knockbackPredicate(final LivingEntity attacker, final LivingEntity entity) {
        return nearby -> {
            boolean notSpectator = !nearby.isSpectator();
            boolean notPlayer = nearby != attacker && nearby != entity;
            boolean notAlliedToPlayer = !attacker.isAlliedTo(nearby);
            boolean notTamedByPlayer = !(
                    nearby instanceof TamableAnimal animal && entity instanceof LivingEntity livingAttacker && animal.isTame() && animal.isOwnedBy(livingAttacker)
            );
            boolean withinRange = entity.distanceToSqr(nearby) <= Math.pow(KNOCKBACK_RANGE + (enchantLevel(entity) * 0.3F), 2.0);
            boolean notFlyingInCreative = !(nearby instanceof Player player && player.isCreative() && player.getAbilities().flying);
            return notSpectator && notPlayer && notAlliedToPlayer && notTamedByPlayer && withinRange && notFlyingInCreative;
        };
    }

    public static final float KNOCKBACK_POWER = 1.1F;
    public static final int KNOCKBACK_RANGE = 4;

    private static double getKnockbackPower(final LivingEntity nearby) {
        return KNOCKBACK_POWER * (1.0F - nearby.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) * 0.5F);
    }
    private static void knockback(final Level level, final LivingEntity attacker, final LivingEntity entity) {
        int enchantLevel = enchantLevel(attacker);
        level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(KNOCKBACK_RANGE + (enchantLevel * 0.3F)), knockbackPredicate(attacker, entity)).forEach(nearby -> {
            Vec3 direction = nearby.position().subtract(entity.position());
            double knockbackPower = getKnockbackPower(nearby);
            Vec3 knockbackVector = direction.normalize().scale(knockbackPower);
            nearby.push(knockbackVector.x + (enchantLevel * 0.3F), 0.7F + (enchantLevel * 0.1F), knockbackVector.z + (enchantLevel * 0.3F));
            if (nearby instanceof ServerPlayer otherPlayer) {
                otherPlayer.connection.send(new ClientboundSetEntityMotionPacket(otherPlayer));
            }
        });
    }
}