package net.mcreator.gorp.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.gorp.GorpMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GorpModVariables {
	public static String gorp_generator_player_pressed = "\"\"";

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		GorpMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handleData);
		GorpMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handleData);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> GorpMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> GorpMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> GorpMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerTickUpdateSyncPlayerVariables(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer player) {
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> {
					if (capability._syncDirty) {
						GorpMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability));
						capability._syncDirty = false;
					}
				});
			}
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			event.getOriginal().getCapability(PLAYER_VARIABLES).ifPresent(original -> {
				event.getEntity().getCapability(PLAYER_VARIABLES).ifPresent(clone -> {
					clone.gorp_count = original.gorp_count;
					clone.bitten_gorp_count = original.bitten_gorp_count;
					clone.let_jorp = original.let_jorp;
					clone.double_jorp_active = original.double_jorp_active;
					clone.gorp_bloodpump = original.gorp_bloodpump;
					clone.BloodPumpOverlayFade = original.BloodPumpOverlayFade;
					clone.OutOfCombatCooldown = original.OutOfCombatCooldown;
					clone.anon_cookie = original.anon_cookie;
					if (!event.isWasDeath()) {
						clone.gorpophone_slot_count = original.gorpophone_slot_count;
						clone.gorp_generator_prompt = original.gorp_generator_prompt;
						clone.generator_inv_slot = original.generator_inv_slot;
						clone.gorpophone_tooltip_text = original.gorpophone_tooltip_text;
					}
				});
			});
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData mapdata = MapVariables.get(player.level());
				SavedData worlddata = WorldVariables.get(player.level());
				if (mapdata != null)
					GorpMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					GorpMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData worlddata = WorldVariables.get(player.level());
				if (worlddata != null)
					GorpMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onWorldTick(TickEvent.LevelTickEvent event) {
			if (event.phase == TickEvent.Phase.END && event.level instanceof ServerLevel level) {
				WorldVariables worldVariables = WorldVariables.get(level);
				if (worldVariables._syncDirty) {
					GorpMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, worldVariables));
					worldVariables._syncDirty = false;
				}
				MapVariables mapVariables = MapVariables.get(level);
				if (mapVariables._syncDirty) {
					GorpMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, mapVariables));
					mapVariables._syncDirty = false;
				}
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "gorp_worldvars";
		boolean _syncDirty = false;
		public double gorphel_defeated = 0;

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			gorphel_defeated = nbt.getDouble("gorphel_defeated");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("gorphel_defeated", gorphel_defeated);
			return nbt;
		}

		public void markSyncDirty() {
			this.setDirty();
			this._syncDirty = true;
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "gorp_mapvars";
		boolean _syncDirty = false;
		public String gorp_talk = "\"\"";

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			gorp_talk = nbt.getString("gorp_talk");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putString("gorp_talk", gorp_talk);
			return nbt;
		}

		public void markSyncDirty() {
			this.setDirty();
			_syncDirty = true;
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int dataType;
		private final SavedData data;

		public SavedDataSyncMessage(int dataType, SavedData data) {
			this.dataType = dataType;
			this.data = data;
		}

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			int dataType = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			SavedData data = null;
			if (nbt != null) {
				data = dataType == 0 ? new MapVariables() : new WorldVariables();
				if (data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
			this.dataType = dataType;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.dataType);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handleData(final SavedDataSyncMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.dataType == 0)
						MapVariables.clientSide.read(message.data.save(new CompoundTag()));
					else
						WorldVariables.clientSide.read(message.data.save(new CompoundTag()));
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<CompoundTag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("gorp", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public CompoundTag serializeNBT() {
			return playerVariables.serializeNBT();
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			playerVariables.deserializeNBT(nbt);
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		boolean _syncDirty = false;
		public double gorp_count = 0;
		public double bitten_gorp_count = 0;
		public double let_jorp = 0.0;
		public boolean double_jorp_active = false;
		public double gorp_bloodpump = 150.0;
		public double BloodPumpOverlayFade = 0.0;
		public double OutOfCombatCooldown = 100.0;
		public boolean anon_cookie = false;
		public double gorpophone_slot_count = 0;
		public boolean gorp_generator_prompt = false;
		public double generator_inv_slot = 0;
		public String gorpophone_tooltip_text = "\"\"";

		@Override
		public CompoundTag serializeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("gorp_count", gorp_count);
			nbt.putDouble("bitten_gorp_count", bitten_gorp_count);
			nbt.putDouble("let_jorp", let_jorp);
			nbt.putBoolean("double_jorp_active", double_jorp_active);
			nbt.putDouble("gorp_bloodpump", gorp_bloodpump);
			nbt.putDouble("BloodPumpOverlayFade", BloodPumpOverlayFade);
			nbt.putDouble("OutOfCombatCooldown", OutOfCombatCooldown);
			nbt.putBoolean("anon_cookie", anon_cookie);
			nbt.putDouble("gorpophone_slot_count", gorpophone_slot_count);
			nbt.putBoolean("gorp_generator_prompt", gorp_generator_prompt);
			nbt.putDouble("generator_inv_slot", generator_inv_slot);
			nbt.putString("gorpophone_tooltip_text", gorpophone_tooltip_text);
			return nbt;
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			gorp_count = nbt.getDouble("gorp_count");
			bitten_gorp_count = nbt.getDouble("bitten_gorp_count");
			let_jorp = nbt.getDouble("let_jorp");
			double_jorp_active = nbt.getBoolean("double_jorp_active");
			gorp_bloodpump = nbt.getDouble("gorp_bloodpump");
			BloodPumpOverlayFade = nbt.getDouble("BloodPumpOverlayFade");
			OutOfCombatCooldown = nbt.getDouble("OutOfCombatCooldown");
			anon_cookie = nbt.getBoolean("anon_cookie");
			gorpophone_slot_count = nbt.getDouble("gorpophone_slot_count");
			gorp_generator_prompt = nbt.getBoolean("gorp_generator_prompt");
			generator_inv_slot = nbt.getDouble("generator_inv_slot");
			gorpophone_tooltip_text = nbt.getString("gorpophone_tooltip_text");
		}

		public void markSyncDirty() {
			_syncDirty = true;
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) {
		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this(new PlayerVariables());
			data.deserializeNBT(buffer.readNbt());
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt(message.data().serializeNBT());
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null)
					Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES).ifPresent(cap -> cap.deserializeNBT(message.data.serializeNBT()));
			});
			context.setPacketHandled(true);
		}
	}
}