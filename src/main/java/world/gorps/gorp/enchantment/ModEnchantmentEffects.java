package world.gorps.gorp.enchantment;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

import world.gorps.gorp.GorpsWorld;
import world.gorps.gorp.enchantment.effect.JorpDraftEnchantmentEffect;

//#entrypoint
public class ModEnchantmentEffects {
    public static MapCodec<JorpDraftEnchantmentEffect> JORP_DRAFT_EFFECT = register("jorp_draft", JorpDraftEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, id), codec);
    }

    public static void registerModEnchantmentEffects() {
    }
}
