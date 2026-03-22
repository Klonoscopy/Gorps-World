package world.gorps.gorp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import world.gorps.gorp.effect.GorpsWorldMobEffects;
import world.gorps.gorp.enchantment.ModEnchantmentEffects;
import world.gorps.gorp.item.ModItems;
import world.gorps.gorp.network.basic.JorpEffectPayload;
import world.gorps.gorp.sound.CustomSounds;

public class GorpsWorld implements ModInitializer {
	public static final String MOD_ID = "gorp";

	@Override
	public void onInitialize() {
		ModItems.initialize();
		CustomSounds.initialize();
		GorpsWorldMobEffects.initialize();
		ModEnchantmentEffects.registerModEnchantmentEffects();
		PayloadTypeRegistry.clientboundPlay().register(JorpEffectPayload.TYPE, JorpEffectPayload.CODEC);
	}
}