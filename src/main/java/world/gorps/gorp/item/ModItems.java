package world.gorps.gorp.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.AttackRange;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.ArmorType;
import world.gorps.gorp.GorpsWorld;

import java.util.function.Function;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class ModItems {
    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, name));
        T item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }
    public static Item BITTEN_GORP = register(
            "bitten_gorp", BittenGorpItem::new, new Item.Properties()
                    .food(GorpsWorldFoods.BITTEN_GORP, GorpsWorldConsumables.BITTEN_GORP)
                    .component(DataComponents.CONSUMABLE, defaultFood()
                            .consumeSeconds(1.6F)
                            .build())
    );
    public static final Item GORP = register(
            "gorp", GorpItem::new, new Item.Properties()
                    .food(GorpsWorldFoods.GORP, GorpsWorldConsumables.GORP)
                    .component(DataComponents.CONSUMABLE, defaultFood()
                            .consumeSeconds(0.52F)
                            .build())
                    .usingConvertsTo(BITTEN_GORP)
                    .useCooldown(0.5F)
    );
    public static final Item JORP_JUICE = register(
            "jorp_juice", JorpJuiceItem::new, new Item.Properties().food(GorpsWorldFoods.JORP_JUICE, GorpsWorldConsumables.JORP_JUICE)
    );
    public static final Item GORP_HAMMER = register(
            "gorp_hammer", GorpHammerItem::new,
            new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .durability(500)
                    .component(DataComponents.TOOL, GorpHammerItem.createToolProperties())
                    .component(DataComponents.ATTACK_RANGE, new AttackRange(2.0F, 8.5F, 2.0F, 8.5F, 1.2F, 0.5F))
                    .attributes(GorpHammerItem.createAttributes())
                    .enchantable(15)
                    .component(DataComponents.WEAPON, new Weapon(1))
    );
    public static final Item GORP_SPEAR = register(
            "gorp_spear", GorpSpearItem::new,
            new Item.Properties()
                    .durability(250)
                    .attributes(GorpSpearItem.createAttributes())
                    .component(DataComponents.TOOL, GorpSpearItem.createToolProperties())
                    .enchantable(15)
                    .component(DataComponents.WEAPON, new Weapon(1))
    );
    public static final Item GORP_STAFF = register(
            "gorp_staff", GorpStaffItem::new,
            new Item.Properties()
                    .durability(238)
                    .enchantable(15)
                    .component(DataComponents.TOOL, GorpStaffItem.createToolProperties())
    );
    public static final Item GORP_RESPIRATOR = register(
            "gorp_respirator", Item::new,
            new Item.Properties().humanoidArmor(GorpArmorMaterial.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(GorpArmorMaterial.BASE_DURABILITY))
    );
    public static final Item GORP_SHIRT = register(
            "gorp_shirt", Item::new,
            new Item.Properties().humanoidArmor(GorpArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(GorpArmorMaterial.BASE_DURABILITY))
    );

    public static final Item GORP_PAJAMAS = register(
            "gorp_pajamas", Item::new,
            new Item.Properties().humanoidArmor(GorpArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(GorpArmorMaterial.BASE_DURABILITY))
    );

    public static final Item GORP_FEET = register(
            "gorp_feet", Item::new,
            new Item.Properties().humanoidArmor(GorpArmorMaterial.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(GorpArmorMaterial.BASE_DURABILITY))
    );
    public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, "creative_tab")
    );
    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(GORP))
            .title(Component.translatable("creativeTab.gorp"))
            .displayItems((_, output) -> {
                output.accept(GORP);
                output.accept(BITTEN_GORP);
                output.accept(JORP_JUICE);
                output.accept(GORP_HAMMER);
                output.accept(GORP_SPEAR);
                output.accept(GORP_STAFF);
                output.accept(GORP_RESPIRATOR);
                output.accept(GORP_SHIRT);
                output.accept(GORP_PAJAMAS);
                output.accept(GORP_FEET);
            })
            .build();

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(BITTEN_GORP));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((itemGroup) -> itemGroup.accept(GORP));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((itemGroup) -> itemGroup.accept(JORP_JUICE));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(GORP_RESPIRATOR));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(GORP_SHIRT));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(GORP_PAJAMAS));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(GORP_FEET));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(GORP_HAMMER));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(GORP_SPEAR));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup) -> itemGroup.accept(GORP_STAFF));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
    }
}