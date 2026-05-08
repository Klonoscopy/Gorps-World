package world.gorps.gorp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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
	private static void removeModifiers(Player player) {
		removeModifier(player, Attributes.FRICTION_MODIFIER,  "friction");
		removeModifier(player, Attributes.AIR_DRAG_MODIFIER,   "air_drag");
	}
	private static void removeModifier(Player player, Holder<Attribute> attribute, String name) {
		AttributeInstance instance = player.getAttribute(attribute);
		if (instance == null) return;
		instance.removeModifier(Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, name));
	}
	@Override
	public void onInitialize() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerLevel level : server.getAllLevels()) {
				for (ServerPlayer player : level.players()) {
					if (player.onGround()) {
						removeModifiers(player);
					}
				}
			}
		});
		TickScheduler.register();
		ModItems.initialize();
		CustomSounds.initialize();
		GorpsWorldMobEffects.initialize();
		ModEnchantmentEffects.registerModEnchantmentEffects();
		PayloadTypeRegistry.clientboundPlay().register(JorpEffectPayload.TYPE, JorpEffectPayload.CODEC);
	}
}