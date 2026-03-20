package net.mcreator.gorp.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.gorp.procedures.JorpkeyOnKeyReleasedProcedure;
import net.mcreator.gorp.procedures.JorpkeyOnKeyPressedProcedure;
import net.mcreator.gorp.GorpMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JorpkeyMessage {
	int type, pressedms;

	public JorpkeyMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public JorpkeyMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(JorpkeyMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(JorpkeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			JorpkeyOnKeyPressedProcedure.execute(entity);
		}
		if (type == 1) {

			JorpkeyOnKeyReleasedProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		GorpMod.addNetworkMessage(JorpkeyMessage.class, JorpkeyMessage::buffer, JorpkeyMessage::new, JorpkeyMessage::handler);
	}
}