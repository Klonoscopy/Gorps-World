/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.potion.*;
import net.mcreator.gorp.GorpMod;

public class GorpModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GorpMod.MODID);
	public static final RegistryObject<MobEffect> JORPING = REGISTRY.register("jorping", () -> new JorpingMobEffect());
	public static final RegistryObject<MobEffect> IRON_H_DAMAGE = REGISTRY.register("iron_h_damage", () -> new IronHDamageMobEffect());
	public static final RegistryObject<MobEffect> GORP_INSPIRATION = REGISTRY.register("gorp_inspiration", () -> new GorpInspirationMobEffect());
	public static final RegistryObject<MobEffect> ON_FIRE = REGISTRY.register("on_fire", () -> new OnFireMobEffect());
	public static final RegistryObject<MobEffect> FROZEN = REGISTRY.register("frozen", () -> new FrozenMobEffect());
	public static final RegistryObject<MobEffect> BLOOD_PUMP = REGISTRY.register("blood_pump", () -> new BloodPumpMobEffect());
	public static final RegistryObject<MobEffect> JORP_FOOTED = REGISTRY.register("jorp_footed", () -> new JorpFootedMobEffect());
	public static final RegistryObject<MobEffect> GLOOE_SICKNESS = REGISTRY.register("glooe_sickness", () -> new GlooeSicknessMobEffect());
}