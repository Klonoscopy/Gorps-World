package world.gorps.gorp.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import world.gorps.gorp.GorpsWorld;
import world.gorps.gorp.enchantment.GorpEnchantmentHelper;
import world.gorps.gorp.sound.CustomSounds;

import java.util.List;

public class GorpSpearItem extends Item {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float BASE_DAMAGE = 8.0F;

    public GorpSpearItem(final Item.Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, BASE_DAMAGE, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2, false);
    }

    @Override
    public ItemUseAnimation getUseAnimation(final ItemStack itemStack) {
        return ItemUseAnimation.TRIDENT;
    }

    @Override
    public int getUseDuration(final ItemStack itemStack, final LivingEntity user) {
        return 72000;
    }

    public static int enchantLevel(LivingEntity entity) {
        return GorpEnchantmentHelper.getJorpDraftLevel(entity);
    }

    public static final Identifier MY_MODIFIER_ID =
            Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, "my_modifier");

    private static void addModifier(Player player, Holder<Attribute> attribute, String name, double amount, AttributeModifier.Operation operation) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance == null) return;

        Identifier id = Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, name);
        instance.removeModifier(id);
        instance.addTransientModifier(new AttributeModifier(id, amount, operation));
    }
    private static void applyModifiers(Player player) {
        addModifier(player, Attributes.FRICTION_MODIFIER,  "friction",   -0.4,  AttributeModifier.Operation.ADD_VALUE);
        addModifier(player, Attributes.AIR_DRAG_MODIFIER,   "air_drag",  -0.45,  AttributeModifier.Operation.ADD_VALUE);
    }
    @Override
    public boolean releaseUsing(final ItemStack itemStack, final Level level, final LivingEntity entity, final int remainingTime) {
        if (entity instanceof Player player) {
            int timeHeld = this.getUseDuration(itemStack, entity) - remainingTime;
            if (timeHeld < THROW_THRESHOLD_TIME) {
                return false;
            } else {
                float jorpDraftLevel = enchantLevel(player);
                if (!player.isPassenger()) {
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
                        float power = (Mth.clamp(timeHeld, 10F, 25F) * 0.15F) * ((jorpDraftLevel * 0.18F) + 1F);
                        xd *= power / dist;
                        yd *= power / dist;
                        zd *= power / dist;
                        applyModifiers(player);
                        player.setDeltaMovement(xd, yd, zd);
//                        player.push(xd, yd, zd);
                        player.sendOverlayMessage(Component.literal("Power: " + power));
                        SoundEvent sound = timeHeld < 35F ? CustomSounds.FLING_SHORT : CustomSounds.FLING_NORMAL;
                        level.playPlayerSound(sound, SoundSource.PLAYERS, 1.0F, 1.0F);
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

    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

}