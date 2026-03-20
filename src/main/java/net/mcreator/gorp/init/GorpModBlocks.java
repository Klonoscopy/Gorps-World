/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.gorp.block.*;
import net.mcreator.gorp.GorpMod;

public class GorpModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, GorpMod.MODID);
	public static final RegistryObject<Block> GORP_SEED;
	public static final RegistryObject<Block> GORP_PLANT_STAGE_1;
	public static final RegistryObject<Block> GORP_PLANT_STAGE_2;
	public static final RegistryObject<Block> GORP_PLANT_STAGE_3;
	public static final RegistryObject<Block> GORP_SPROUT;
	public static final RegistryObject<Block> GORP_ALTAR;
	public static final RegistryObject<Block> GORP_ALTAR_FULL;
	public static final RegistryObject<Block> NETHER_GORP_SPROUT;
	public static final RegistryObject<Block> ICY_GORP_SPROUT;
	public static final RegistryObject<Block> GORP_ALTAR_FULL_S_PICY;
	public static final RegistryObject<Block> GORP_ALTAR_FULL_ICY;
	public static final RegistryObject<Block> GORPOSITE;
	public static final RegistryObject<Block> GORPOLITE;
	public static final RegistryObject<Block> GORPIUM_ORE;
	public static final RegistryObject<Block> GLOOE_BLOCK;
	static {
		GORP_SEED = REGISTRY.register("gorp_seed", GorpPlantStage0Block::new);
		GORP_PLANT_STAGE_1 = REGISTRY.register("gorp_plant_stage_1", GorpPlantStage1Block::new);
		GORP_PLANT_STAGE_2 = REGISTRY.register("gorp_plant_stage_2", GorpPlantStage2Block::new);
		GORP_PLANT_STAGE_3 = REGISTRY.register("gorp_plant_stage_3", GorpPlantStage3Block::new);
		GORP_SPROUT = REGISTRY.register("gorp_sprout", GorpSproutBlock::new);
		GORP_ALTAR = REGISTRY.register("gorp_altar", GorpAltarBlock::new);
		GORP_ALTAR_FULL = REGISTRY.register("gorp_altar_full", GorpAltarFullBlock::new);
		NETHER_GORP_SPROUT = REGISTRY.register("nether_gorp_sprout", NetherGorpSproutBlock::new);
		ICY_GORP_SPROUT = REGISTRY.register("icy_gorp_sprout", IcyGorpSproutBlock::new);
		GORP_ALTAR_FULL_S_PICY = REGISTRY.register("gorp_altar_full_s_picy", GorpAltarFullSPicyBlock::new);
		GORP_ALTAR_FULL_ICY = REGISTRY.register("gorp_altar_full_icy", GorpAltarFullIcyBlock::new);
		GORPOSITE = REGISTRY.register("gorposite", GorpositeBlock::new);
		GORPOLITE = REGISTRY.register("gorpolite", GorpoliteBlock::new);
		GORPIUM_ORE = REGISTRY.register("gorpium_ore", GorpiumOreBlock::new);
		GLOOE_BLOCK = REGISTRY.register("glooe_block", GlooeBlockBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}