/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.gorp.network.JorpkeyMessage;
import net.mcreator.gorp.GorpMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GorpModKeyMappings {
	public static final KeyMapping JORPKEY = new KeyMapping("key.gorp.jorpkey", GLFW.GLFW_KEY_SPACE, "key.categories.movement") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				GorpMod.PACKET_HANDLER.sendToServer(new JorpkeyMessage(0, 0));
				JorpkeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
				JORPKEY_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - JORPKEY_LASTPRESS);
				GorpMod.PACKET_HANDLER.sendToServer(new JorpkeyMessage(1, dt));
				JorpkeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	private static long JORPKEY_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(JORPKEY);
	}

	@Mod.EventBusSubscriber(Dist.CLIENT)
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				JORPKEY.consumeClick();
			}
		}
	}
}