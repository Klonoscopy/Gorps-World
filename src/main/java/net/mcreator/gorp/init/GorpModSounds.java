/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.gorp.GorpMod;

public class GorpModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GorpMod.MODID);
	public static final RegistryObject<SoundEvent> GULP = REGISTRY.register("gulp", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gulp")));
	public static final RegistryObject<SoundEvent> BELCH = REGISTRY.register("belch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "belch")));
	public static final RegistryObject<SoundEvent> CHOMP = REGISTRY.register("chomp", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "chomp")));
	public static final RegistryObject<SoundEvent> GORP_RADIO = REGISTRY.register("gorp_radio", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorp_radio")));
	public static final RegistryObject<SoundEvent> GORP_RADIO_DANGEROUS_ENVIRONMENTS = REGISTRY.register("gorp_radio_dangerous_environments",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorp_radio_dangerous_environments")));
	public static final RegistryObject<SoundEvent> GORP_AMBIENCE = REGISTRY.register("gorp_ambience", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorp_ambience")));
	public static final RegistryObject<SoundEvent> GORP_SUMMON = REGISTRY.register("gorp_summon", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorp_summon")));
	public static final RegistryObject<SoundEvent> BLOODPUMP_HEARTBEAT = REGISTRY.register("bloodpump_heartbeat", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "bloodpump_heartbeat")));
	public static final RegistryObject<SoundEvent> GORP_RADIO_NEW_WORLD = REGISTRY.register("gorp_radio_new_world", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorp_radio_new_world")));
	public static final RegistryObject<SoundEvent> GORP_BURN = REGISTRY.register("gorp_burn", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorp_burn")));
	public static final RegistryObject<SoundEvent> FLING_0 = REGISTRY.register("fling_0", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "fling_0")));
	public static final RegistryObject<SoundEvent> FLING_1 = REGISTRY.register("fling_1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "fling_1")));
	public static final RegistryObject<SoundEvent> FLING_2 = REGISTRY.register("fling_2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "fling_2")));
	public static final RegistryObject<SoundEvent> FLING_3 = REGISTRY.register("fling_3", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "fling_3")));
	public static final RegistryObject<SoundEvent> JORP_HIT = REGISTRY.register("jorp_hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "jorp_hit")));
	public static final RegistryObject<SoundEvent> GORPLING_DEATH = REGISTRY.register("gorpling_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorpling_death")));
	public static final RegistryObject<SoundEvent> GORPLING_HURT = REGISTRY.register("gorpling_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "gorpling_hurt")));
	public static final RegistryObject<SoundEvent> POP = REGISTRY.register("pop", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "pop")));
	public static final RegistryObject<SoundEvent> TOAST_CRUNCH = REGISTRY.register("toast_crunch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "toast_crunch")));
	public static final RegistryObject<SoundEvent> MURDLER_KILL = REGISTRY.register("murdler_kill", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("gorp", "murdler_kill")));
}