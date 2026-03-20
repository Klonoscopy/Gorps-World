/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.gorp.enchantment.PlurgeEnchantment;
import net.mcreator.gorp.enchantment.JorpingEnchantmentEnchantment;
import net.mcreator.gorp.enchantment.JorpStartEnchantment;
import net.mcreator.gorp.enchantment.FlingingEnchantment;
import net.mcreator.gorp.GorpMod;

public class GorpModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, GorpMod.MODID);
	public static final RegistryObject<Enchantment> PLURGE = REGISTRY.register("plurge", () -> new PlurgeEnchantment());
	public static final RegistryObject<Enchantment> JORP = REGISTRY.register("jorp", () -> new JorpingEnchantmentEnchantment());
	public static final RegistryObject<Enchantment> JORP_DRAFT = REGISTRY.register("jorp_draft", () -> new FlingingEnchantment());
	public static final RegistryObject<Enchantment> JORP_START = REGISTRY.register("jorp_start", () -> new JorpStartEnchantment());
}