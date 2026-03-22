package world.gorps.gorp.network;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.phys.Vec3;
import world.gorps.gorp.network.basic.JorpEffectPayload;

public class ClientNetworking implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(JorpEffectPayload.TYPE, (payload, context) -> {
            ClientLevel level = context.client().level;
            if (level == null) {
                return;
            }
            context.client().execute(() -> {
                LocalPlayer player = context.client().player;
                if (player != null) {
                    Vec3 current = player.getDeltaMovement();
                    player.setDeltaMovement(current.x, payload.strength(), current.z);
                }
            });
        });
    }
}