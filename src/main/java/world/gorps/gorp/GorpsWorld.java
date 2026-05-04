package world.gorps.gorp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.gorps.gorp.effect.GorpsWorldMobEffects;
import world.gorps.gorp.enchantment.ModEnchantmentEffects;
import world.gorps.gorp.item.ModItems;
import world.gorps.gorp.network.basic.JorpEffectPayload;
import world.gorps.gorp.sound.CustomSounds;
import world.gorps.gorp.util.TickScheduler;

public class GorpsWorld implements ModInitializer {
	public static final String MOD_ID = "gorp";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		TickScheduler.register();
		ModItems.initialize();
		CustomSounds.initialize();
		GorpsWorldMobEffects.initialize();
		ModEnchantmentEffects.registerModEnchantmentEffects();
		PayloadTypeRegistry.clientboundPlay().register(JorpEffectPayload.TYPE, JorpEffectPayload.CODEC);
	}
}