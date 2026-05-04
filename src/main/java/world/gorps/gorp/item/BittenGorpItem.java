package world.gorps.gorp.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import world.gorps.gorp.sound.CustomSounds;
import world.gorps.gorp.util.TickScheduler;

public class BittenGorpItem extends Item {
    public BittenGorpItem(final Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), CustomSounds.GULP, entity.getSoundSource(), 1.0F, 1.0F);
            TickScheduler.queueServerWork(15, () -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), CustomSounds.BELCH, entity.getSoundSource(), 1.0F, 1.0F));
        }

        stack.shrink(1);

        return stack;
    }
}