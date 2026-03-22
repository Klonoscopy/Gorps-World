package world.gorps.gorp.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import world.gorps.gorp.GorpsWorld;

public class GorpToolMaterial {
    public static final ToolMaterial GORP_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            455,
            5.0F,
            1.5F,
            22,
            GorpArmorMaterial.REPAIRS_GORP_ARMOR
    );
    public static final TagKey<Item> REPAIRS_GORP_ARMOR = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, "repairs_gorp_armor"));
}
