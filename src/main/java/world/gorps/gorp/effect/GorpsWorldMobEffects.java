package world.gorps.gorp.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

import world.gorps.gorp.GorpsWorld;

public class GorpsWorldMobEffects {
    private GorpsWorldMobEffects() {
    }

    public static final Holder<MobEffect> JORP = registerMobEffect("jorp");

    private static Holder<MobEffect> registerMobEffect(String id) {
        Identifier identifier = Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, id);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, identifier, new JorpMobEffect());
    }
    public static void initialize() {
    }
}