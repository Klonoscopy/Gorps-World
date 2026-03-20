/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.entity.decoration.PaintingVariant;

import net.mcreator.gorp.GorpMod;

public class GorpModPaintings {
	public static final DeferredRegister<PaintingVariant> REGISTRY = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, GorpMod.MODID);
	public static final RegistryObject<PaintingVariant> MARCH_OF_THE_GORP = REGISTRY.register("march_of_the_gorp", () -> new PaintingVariant(32, 32));
	public static final RegistryObject<PaintingVariant> GORP_NIGHT_OUT = REGISTRY.register("gorp_night_out", () -> new PaintingVariant(16, 16));
	public static final RegistryObject<PaintingVariant> GORBET = REGISTRY.register("gorbet", () -> new PaintingVariant(32, 16));
	public static final RegistryObject<PaintingVariant> G_MYSTERY = REGISTRY.register("g_mystery", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> ECHOED_GORP = REGISTRY.register("echoed_gorp", () -> new PaintingVariant(64, 32));
	public static final RegistryObject<PaintingVariant> HE_IS_GORP = REGISTRY.register("he_is_gorp", () -> new PaintingVariant(64, 32));
	public static final RegistryObject<PaintingVariant> MAN_ABOVE_GORP_BELOW = REGISTRY.register("man_above_gorp_below", () -> new PaintingVariant(48, 48));
}