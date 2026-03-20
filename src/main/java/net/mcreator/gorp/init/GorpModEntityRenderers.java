/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.gorp.client.renderer.HostileBreadRenderer;
import net.mcreator.gorp.client.renderer.GorplingRenderer;
import net.mcreator.gorp.client.renderer.GorpBuddyRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GorpModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(GorpModEntities.GORP_BUDDY.get(), GorpBuddyRenderer::new);
		event.registerEntityRenderer(GorpModEntities.GORPLING.get(), GorplingRenderer::new);
		event.registerEntityRenderer(GorpModEntities.HOSTILE_BREAD.get(), HostileBreadRenderer::new);
		event.registerEntityRenderer(GorpModEntities.GORP_COIN_PROJECTILE.get(), ThrownItemRenderer::new);
	}
}