/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.gorp.client.model.Modelhostile_bread;
import net.mcreator.gorp.client.model.Modelgorpling;
import net.mcreator.gorp.client.model.Modelgorp_buddy;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GorpModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelgorp_buddy.LAYER_LOCATION, Modelgorp_buddy::createBodyLayer);
		event.registerLayerDefinition(Modelhostile_bread.LAYER_LOCATION, Modelhostile_bread::createBodyLayer);
		event.registerLayerDefinition(Modelgorpling.LAYER_LOCATION, Modelgorpling::createBodyLayer);
	}
}