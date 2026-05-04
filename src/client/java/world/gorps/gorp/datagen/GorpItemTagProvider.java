package world.gorps.gorp.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import world.gorps.gorp.GorpsWorld;
import world.gorps.gorp.item.ModItems;

public class GorpItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public static final TagKey<Item> JORP_DRAFT_ENCHANTABLE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, "jorp_draft_enchantable"));
    public GorpItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(JORP_DRAFT_ENCHANTABLE)
                .add(ModItems.GORP_HAMMER)
                .add(ModItems.GORP_SPEAR)
                .add(ModItems.GORP_STAFF)
                .setReplace(true);
    }
}