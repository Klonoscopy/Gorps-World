/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.gorp.client.gui.GorpophoneScreenScreen;
import net.mcreator.gorp.client.gui.GorpAltarUIFullScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GorpModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(GorpModMenus.GORP_ALTAR_UI_FULL.get(), GorpAltarUIFullScreen::new);
			MenuScreens.register(GorpModMenus.GORPOPHONE_SCREEN.get(), GorpophoneScreenScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}