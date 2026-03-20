/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.gorp.GorpMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GorpModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GorpMod.MODID);
	public static final RegistryObject<CreativeModeTab> GORP_CREATIVE_TAB = REGISTRY.register("gorp_creative_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.gorp.gorp_creative_tab")).icon(() -> new ItemStack(GorpModItems.GORP.get())).displayItems((parameters, tabData) -> {
				tabData.accept(GorpModItems.GORP.get());
				tabData.accept(GorpModItems.BITTEN_GORP.get());
				tabData.accept(GorpModBlocks.GORP_SEED.get().asItem());
				tabData.accept(GorpModItems.GORP_HAMMER.get());
				tabData.accept(GorpModItems.GORP_STAFF.get());
				tabData.accept(GorpModItems.JORP_JUICE.get());
				tabData.accept(GorpModBlocks.GORP_ALTAR.get().asItem());
				tabData.accept(GorpModItems.GORP_SPEAR.get());
				tabData.accept(GorpModItems.GORP_MERCH_HELMET.get());
				tabData.accept(GorpModItems.GORP_MERCH_CHESTPLATE.get());
				tabData.accept(GorpModItems.GORP_MERCH_LEGGINGS.get());
				tabData.accept(GorpModItems.GORP_MERCH_BOOTS.get());
				tabData.accept(GorpModItems.GORP_FABRIC.get());
				tabData.accept(GorpModItems.CONSOLIDATED_GORP_CUBE.get());
				tabData.accept(GorpModItems.ROASTED_GORP_SEED.get());
				tabData.accept(GorpModBlocks.NETHER_GORP_SPROUT.get().asItem());
				tabData.accept(GorpModItems.SPICY_GORP.get());
				tabData.accept(GorpModItems.ICY_GORP.get());
				tabData.accept(GorpModItems.DICEY_GORP.get());
				tabData.accept(GorpModBlocks.ICY_GORP_SPROUT.get().asItem());
				tabData.accept(GorpModItems.GORPOPHONE.get());
				tabData.accept(GorpModItems.MURDLER.get());
				tabData.accept(GorpModItems.GORP_COIN.get());
				tabData.accept(GorpModItems.DICEY_GORP_UPGRADE_SMITHING_TEMPLATE.get());
				tabData.accept(GorpModItems.UPGRADED_DICEY_GORP.get());
				tabData.accept(GorpModItems.GORP_RIFT_BIOCONDUIT.get());
				tabData.accept(GorpModBlocks.GORPOSITE.get().asItem());
				tabData.accept(GorpModBlocks.GORPOLITE.get().asItem());
				tabData.accept(GorpModItems.GORPIUM.get());
				tabData.accept(GorpModBlocks.GORPIUM_ORE.get().asItem());
				tabData.accept(GorpModItems.UNSTABLE_GORPIUM_BAR.get());
				tabData.accept(GorpModItems.STABLE_GORPIUM_BAR.get());
				tabData.accept(GorpModItems.GORPASOL.get());
				tabData.accept(GorpModItems.GLINGSHOT.get());
				tabData.accept(GorpModItems.GORP_RIFT_BIOCONDUIT_SMITHING_TEMPLATE.get());
				tabData.accept(GorpModItems.REFINED_GORPIUM.get());
				tabData.accept(GorpModBlocks.GLOOE_BLOCK.get().asItem());
				tabData.accept(GorpModItems.GLOOE.get());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(GorpModBlocks.GORP_SPROUT.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(GorpModItems.GORP_RADIO_DISC.get());
			tabData.accept(GorpModItems.DANGEROUS_ENVIRONMENTS_DISC.get());
			tabData.accept(GorpModItems.GORP_NEW_WORLD_DISC.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(GorpModItems.GORPLING_SPAWN_EGG.get());
			tabData.accept(GorpModItems.HOSTILE_BREAD_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(GorpModItems.SUSPICIOUS_BREAD.get());
		}
	}
}