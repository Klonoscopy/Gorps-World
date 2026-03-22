package world.gorps.gorp.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import world.gorps.gorp.enchantment.GorpEnchantmentHelper;

import java.util.List;

public class GorpSpearItem extends Item {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float BASE_DAMAGE = 8.0F;
    public static final float PROJECTILE_SHOOT_POWER = 2.5F;
    private ItemStack itemStack;

    public GorpSpearItem(final Item.Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 8.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2, false);
    }

    public ItemUseAnimation getUseAnimation(final ItemStack itemStack) {
        this.itemStack = itemStack;
        return ItemUseAnimation.TRIDENT;
    }

    public int getUseDuration(final ItemStack itemStack, final LivingEntity user) {
        return 72000;
    }

    public boolean releaseUsing(final ItemStack itemStack, final Level level, final LivingEntity entity, final int remainingTime) {
        if (entity instanceof Player player) {
            int timeHeld = this.getUseDuration(itemStack, entity) - remainingTime;
            if (timeHeld < 10) {
                return false;
            } else {
                float jorpDraftLevel = GorpEnchantmentHelper.getJorpDraftLevel(player);
                if (!(jorpDraftLevel > 0) || !player.isPassenger()) {
                    if (itemStack.nextDamageWillBreak()) {
                        return false;
                    } else {
                        player.awardStat(Stats.ITEM_USED.get(this));
                        if (level instanceof ServerLevel serverLevel) {
                            itemStack.hurtWithoutBreaking(1, player);
                        }
                        float yRot = player.getYRot();
                        float xRot = player.getXRot();
                        float xd = -Mth.sin(yRot * (float) (Math.PI / 180.0)) * Mth.cos(xRot * (float) (Math.PI / 180.0));
                        float yd = -Mth.sin(xRot * (float) (Math.PI / 180.0));
                        float zd = Mth.cos(yRot * (float) (Math.PI / 180.0)) * Mth.cos(xRot * (float) (Math.PI / 180.0));
                        float dist = Mth.sqrt(xd * xd + yd * yd + zd * zd);
                        xd *= jorpDraftLevel / dist;
                        yd *= jorpDraftLevel / dist;
                        zd *= jorpDraftLevel / dist;
                        player.push(xd, yd, zd);
                        if (player.onGround()) {
                            float heightDifference = 1.1999999F;
                            player.move(MoverType.SELF, new Vec3(0.0, heightDifference, 0.0));
                        }
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.nextDamageWillBreak()) {
            return InteractionResult.FAIL;
        } else {
            player.startUsingItem(hand);
            return InteractionResult.SUCCESS;
        }
    }

}