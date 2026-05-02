package world.gorps.gorp.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import world.gorps.gorp.effect.GorpsWorldMobEffects;

public class GorpsWorldConsumables extends Consumables {
    public static final Consumable GORP = defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(GorpsWorldMobEffects.JORP, 1, 0, false, false, false), 1.0F))
            .build();
    public static final Consumable BITTEN_GORP = defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(GorpsWorldMobEffects.JORP, 1, 1, false, false, false), 1.0F))
            .build();
    public static final Consumable JORP_JUICE = defaultDrink()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(GorpsWorldMobEffects.JORP, 1, 1, false, false, false), 1.0F))
            .build();
}
