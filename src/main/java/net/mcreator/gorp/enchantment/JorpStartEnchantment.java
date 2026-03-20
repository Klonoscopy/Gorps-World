package net.mcreator.gorp.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;

import net.mcreator.gorp.init.GorpModItems;
import net.mcreator.gorp.init.GorpModEnchantments;

import java.util.List;

public class JorpStartEnchantment extends Enchantment {
	private static final EnchantmentCategory ENCHANTMENT_CATEGORY = EnchantmentCategory.create("gorp_jorp_start", item -> Ingredient.of(new ItemStack(GorpModItems.GORP_SPEAR.get())).test(new ItemStack(item)));

	public JorpStartEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, ENCHANTMENT_CATEGORY, EquipmentSlot.values());
	}

	@Override
	public int getMinCost(int level) {
		return 1 + level * 10;
	}

	@Override
	public int getMaxCost(int level) {
		return 6 + level * 10;
	}

	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		return super.checkCompatibility(enchantment) && !List.of(GorpModEnchantments.JORP_DRAFT.get()).contains(enchantment);
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}
}