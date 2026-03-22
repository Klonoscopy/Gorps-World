package world.gorps.gorp.enchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

import world.gorps.gorp.GorpsWorld;

public class GorpEnchantments {
    public static final ResourceKey<Enchantment> JORP_DRAFT = key("jorp_draft");
    public static final ResourceKey<Enchantment> REPULSION_CURSE = key("repulsion_curse");

    private static ResourceKey<Enchantment> key(String path) {
        Identifier id = Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, path);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }
}
