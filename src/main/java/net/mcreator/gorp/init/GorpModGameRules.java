/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GorpModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_GORP_COIN_DROPS = GameRules.register("disableGorpCoinDrops", GameRules.Category.DROPS, GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_GORP_COIN_ON_ADVANCEMENT = GameRules.register("disableGorpCoinOnAdvancement", GameRules.Category.UPDATES, GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_GLINGSHOT_TRADE = GameRules.register("disableGlingshotTrade", GameRules.Category.MISC, GameRules.BooleanValue.create(false));
}