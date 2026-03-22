package world.gorps.gorp.network.basic;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import world.gorps.gorp.GorpsWorld;

public record JorpEffectPayload(float strength) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<JorpEffectPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, "jorp_effect"));

    public static final StreamCodec<ByteBuf, JorpEffectPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.FLOAT, JorpEffectPayload::strength, JorpEffectPayload::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}