/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.gorp.item.*;
import net.mcreator.gorp.GorpMod;

public class GorpModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, GorpMod.MODID);
	public static final RegistryObject<Item> GORP;
	public static final RegistryObject<Item> BITTEN_GORP;
	public static final RegistryObject<Item> GORP_SEED;
	public static final RegistryObject<Item> GORP_PLANT_STAGE_1;
	public static final RegistryObject<Item> GORP_PLANT_STAGE_2;
	public static final RegistryObject<Item> GORP_PLANT_STAGE_3;
	public static final RegistryObject<Item> GORP_SPROUT;
	public static final RegistryObject<Item> GORP_HAMMER;
	public static final RegistryObject<Item> GORP_STAFF;
	public static final RegistryObject<Item> JORP_JUICE;
	public static final RegistryObject<Item> GORP_ALTAR;
	public static final RegistryObject<Item> GORP_ALTAR_FULL;
	public static final RegistryObject<Item> GORP_SPEAR;
	public static final RegistryObject<Item> GORP_MERCH_HELMET;
	public static final RegistryObject<Item> GORP_MERCH_CHESTPLATE;
	public static final RegistryObject<Item> GORP_MERCH_LEGGINGS;
	public static final RegistryObject<Item> GORP_MERCH_BOOTS;
	public static final RegistryObject<Item> GORP_FABRIC;
	public static final RegistryObject<Item> CONSOLIDATED_GORP_CUBE;
	public static final RegistryObject<Item> AREA_EFFECT_CLOUD_2;
	public static final RegistryObject<Item> ROASTED_GORP_SEED;
	public static final RegistryObject<Item> NETHER_GORP_SPROUT;
	public static final RegistryObject<Item> SPICY_GORP;
	public static final RegistryObject<Item> ICY_GORP;
	public static final RegistryObject<Item> DICEY_GORP;
	public static final RegistryObject<Item> ICY_GORP_SPROUT;
	public static final RegistryObject<Item> GORP_ALTAR_FULL_S_PICY;
	public static final RegistryObject<Item> GORP_ALTAR_FULL_ICY;
	public static final RegistryObject<Item> GORPOPHONE;
	public static final RegistryObject<Item> MURDLER;
	public static final RegistryObject<Item> GORP_RADIO_DISC;
	public static final RegistryObject<Item> DANGEROUS_ENVIRONMENTS_DISC;
	public static final RegistryObject<Item> GORP_COIN;
	public static final RegistryObject<Item> DICEY_GORP_UPGRADE_SMITHING_TEMPLATE;
	public static final RegistryObject<Item> UPGRADED_DICEY_GORP;
	public static final RegistryObject<Item> GORP_RIFT_BIOCONDUIT;
	public static final RegistryObject<Item> SPENT_GORP_RIFT_BIOCONTUIT;
	public static final RegistryObject<Item> GORPOSITE;
	public static final RegistryObject<Item> GORPOLITE;
	public static final RegistryObject<Item> GORPLING_SPAWN_EGG;
	public static final RegistryObject<Item> GORPIUM;
	public static final RegistryObject<Item> GORPIUM_ORE;
	public static final RegistryObject<Item> UNSTABLE_GORPIUM_BAR;
	public static final RegistryObject<Item> STABLE_GORPIUM_BAR;
	public static final RegistryObject<Item> GORPASOL;
	public static final RegistryObject<Item> HOSTILE_BREAD_SPAWN_EGG;
	public static final RegistryObject<Item> GLINGSHOT;
	public static final RegistryObject<Item> GORP_NEW_WORLD_DISC;
	public static final RegistryObject<Item> GORP_RIFT_BIOCONDUIT_SMITHING_TEMPLATE;
	public static final RegistryObject<Item> REFINED_GORPIUM;
	public static final RegistryObject<Item> SUSPICIOUS_BREAD;
	public static final RegistryObject<Item> GLOOE_BLOCK;
	public static final RegistryObject<Item> GLOOE;
	static {
		GORP = REGISTRY.register("gorp", GorpItem::new);
		BITTEN_GORP = REGISTRY.register("bitten_gorp", BittenGorpItem::new);
		GORP_SEED = block(GorpModBlocks.GORP_SEED);
		GORP_PLANT_STAGE_1 = block(GorpModBlocks.GORP_PLANT_STAGE_1);
		GORP_PLANT_STAGE_2 = block(GorpModBlocks.GORP_PLANT_STAGE_2);
		GORP_PLANT_STAGE_3 = block(GorpModBlocks.GORP_PLANT_STAGE_3);
		GORP_SPROUT = block(GorpModBlocks.GORP_SPROUT);
		GORP_HAMMER = REGISTRY.register("gorp_hammer", GorpHammerItem::new);
		GORP_STAFF = REGISTRY.register("gorp_staff", GorpStaffItem::new);
		JORP_JUICE = REGISTRY.register("jorp_juice", GorpJuiceItem::new);
		GORP_ALTAR = block(GorpModBlocks.GORP_ALTAR);
		GORP_ALTAR_FULL = block(GorpModBlocks.GORP_ALTAR_FULL);
		GORP_SPEAR = REGISTRY.register("gorp_spear", GorpSpearItem::new);
		GORP_MERCH_HELMET = REGISTRY.register("gorp_merch_helmet", GorpShirtItem.Helmet::new);
		GORP_MERCH_CHESTPLATE = REGISTRY.register("gorp_merch_chestplate", GorpShirtItem.Chestplate::new);
		GORP_MERCH_LEGGINGS = REGISTRY.register("gorp_merch_leggings", GorpShirtItem.Leggings::new);
		GORP_MERCH_BOOTS = REGISTRY.register("gorp_merch_boots", GorpShirtItem.Boots::new);
		GORP_FABRIC = REGISTRY.register("gorp_fabric", GorpFabricItem::new);
		CONSOLIDATED_GORP_CUBE = REGISTRY.register("consolidated_gorp_cube", ConsolidatedGorpCubeItem::new);
		AREA_EFFECT_CLOUD_2 = REGISTRY.register("area_effect_cloud_2", GorpeenItem::new);
		ROASTED_GORP_SEED = REGISTRY.register("roasted_gorp_seed", RoastedGorpSeedItem::new);
		NETHER_GORP_SPROUT = block(GorpModBlocks.NETHER_GORP_SPROUT);
		SPICY_GORP = REGISTRY.register("spicy_gorp", SpicyGorpItem::new);
		ICY_GORP = REGISTRY.register("icy_gorp", IcyGorpItem::new);
		DICEY_GORP = REGISTRY.register("dicey_gorp", DiceyGorpItem::new);
		ICY_GORP_SPROUT = block(GorpModBlocks.ICY_GORP_SPROUT);
		GORP_ALTAR_FULL_S_PICY = block(GorpModBlocks.GORP_ALTAR_FULL_S_PICY);
		GORP_ALTAR_FULL_ICY = block(GorpModBlocks.GORP_ALTAR_FULL_ICY);
		GORPOPHONE = REGISTRY.register("gorpophone", GorpophoneItem::new);
		MURDLER = REGISTRY.register("murdler", MurdlerItem::new);
		GORP_RADIO_DISC = REGISTRY.register("gorp_radio_disc", GorpRadioDiscItem::new);
		DANGEROUS_ENVIRONMENTS_DISC = REGISTRY.register("dangerous_environments_disc", DangerousEnvironmentsDiscItem::new);
		GORP_COIN = REGISTRY.register("gorp_coin", GorpCoinItem::new);
		DICEY_GORP_UPGRADE_SMITHING_TEMPLATE = REGISTRY.register("dicey_gorp_upgrade_smithing_template", DiceyGorpUpgradeSmithingTemplateItem::new);
		UPGRADED_DICEY_GORP = REGISTRY.register("upgraded_dicey_gorp", UpgradedDiceyGorpItem::new);
		GORP_RIFT_BIOCONDUIT = REGISTRY.register("gorp_rift_bioconduit", GorpRiftBioconduitItem::new);
		SPENT_GORP_RIFT_BIOCONTUIT = REGISTRY.register("spent_gorp_rift_biocontuit", SpentGorpRiftBiocontuitItem::new);
		GORPOSITE = block(GorpModBlocks.GORPOSITE);
		GORPOLITE = block(GorpModBlocks.GORPOLITE);
		GORPLING_SPAWN_EGG = REGISTRY.register("gorpling_spawn_egg", () -> new ForgeSpawnEggItem(GorpModEntities.GORPLING, -3355648, -13395712, new Item.Properties()));
		GORPIUM = REGISTRY.register("gorpium", GorpiumItem::new);
		GORPIUM_ORE = block(GorpModBlocks.GORPIUM_ORE);
		UNSTABLE_GORPIUM_BAR = REGISTRY.register("unstable_gorpium_bar", UnstableGorpiumBarItem::new);
		STABLE_GORPIUM_BAR = REGISTRY.register("stable_gorpium_bar", StableGorpiumBarItem::new);
		GORPASOL = REGISTRY.register("gorpasol", GorpasolItem::new);
		HOSTILE_BREAD_SPAWN_EGG = REGISTRY.register("hostile_bread_spawn_egg", () -> new ForgeSpawnEggItem(GorpModEntities.HOSTILE_BREAD, -5611725, -535408, new Item.Properties()));
		GLINGSHOT = REGISTRY.register("glingshot", GlingshotItem::new);
		GORP_NEW_WORLD_DISC = REGISTRY.register("gorp_new_world_disc", GorpNewWorldRecordItem::new);
		GORP_RIFT_BIOCONDUIT_SMITHING_TEMPLATE = REGISTRY.register("gorp_rift_bioconduit_smithing_template", GorpRiftBioconduitSmithingTemplateItem::new);
		REFINED_GORPIUM = REGISTRY.register("refined_gorpium", RefinedGorpiumItem::new);
		SUSPICIOUS_BREAD = REGISTRY.register("suspicious_bread", SuspiciousBreadItem::new);
		GLOOE_BLOCK = block(GorpModBlocks.GLOOE_BLOCK);
		GLOOE = REGISTRY.register("glooe", GlooeItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}