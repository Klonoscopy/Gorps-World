package world.gorps.gorp.enchantment;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import world.gorps.gorp.GorpsWorld;

import java.util.Optional;

public class GorpEnchantmentHelper {
    public static int getJorpDraftLevel(LivingEntity entity) {
        ItemStack held = entity.getMainHandItem();

        HolderLookup.RegistryLookup<Enchantment> enchantments =
                entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

        ResourceKey<Enchantment> key = ResourceKey.create(
                Registries.ENCHANTMENT,
                Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, "jorp_draft")
        );

        Optional<Holder.Reference<Enchantment>> jorpDraft = enchantments.get(key);

        return jorpDraft
                .map(holder -> EnchantmentHelper.getItemEnchantmentLevel(holder, held))
                .orElse(0);
    }
}
