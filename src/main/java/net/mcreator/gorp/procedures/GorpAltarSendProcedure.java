package net.mcreator.gorp.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.gorp.network.GorpModVariables;
import net.mcreator.gorp.init.GorpModMenus;
import net.mcreator.gorp.init.GorpModItems;
import net.mcreator.gorp.init.GorpModBlocks;

import java.util.Random;

public class GorpAltarSendProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double Random = 0;
		GorpModVariables.MapVariables.get(world).gorp_talk = (entity instanceof Player _entity0 && _entity0.containerMenu instanceof GorpModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "GorpMessage", "") : "";
		GorpModVariables.MapVariables.get(world).markSyncDirty();
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == GorpModBlocks.GORP_ALTAR_FULL_S_PICY.get()) {
			if (((entity instanceof Player _entity3 && _entity3.containerMenu instanceof GorpModMenus.MenuAccessor _menu3) ? _menu3.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("hello")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "YOU PISS ME OFF!!!!!", true);
			} else if (((entity instanceof Player _entity5 && _entity5.containerMenu instanceof GorpModMenus.MenuAccessor _menu5) ? _menu5.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("why")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "I HATE YOU!!!!!!", true);
			} else if (((entity instanceof Player _entity7 && _entity7.containerMenu instanceof GorpModMenus.MenuAccessor _menu7) ? _menu7.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("music")
					|| ((entity instanceof Player _entity8 && _entity8.containerMenu instanceof GorpModMenus.MenuAccessor _menu8) ? _menu8.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("song")) {
				if (!(entity instanceof ServerPlayer _plr9 && _plr9.level() instanceof ServerLevel _serverLevel9
						&& _plr9.getAdvancements().getOrStartProgress(_serverLevel9.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:gorpinominal_taste"))).isDone())) {
					if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
						_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "TAKE THIS AND PISS OFF!!!", true);
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(GorpModItems.DANGEROUS_ENVIRONMENTS_DISC.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("gorp:gorpinominal_taste"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				} else {
					if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
						_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "PISS OFF!!!!!!!!", true);
				}
			} else if (((entity instanceof Player _entity14 && _entity14.containerMenu instanceof GorpModMenus.MenuAccessor _menu14) ? _menu14.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("gorpy layer")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "I DON'T CARE!!!!!!!!", true);
			} else if (((entity instanceof Player _entity16 && _entity16.containerMenu instanceof GorpModMenus.MenuAccessor _menu16) ? _menu16.getMenuState(0, "GorpMessage", "") : "").length() > 0) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", (Component.translatable(("dialogue.spicygorp." + Mth.nextInt(RandomSource.create(), 0, 4))).getString()), true);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == GorpModBlocks.GORP_ALTAR_FULL_ICY.get()) {
			if (((entity instanceof Player _entity22 && _entity22.containerMenu instanceof GorpModMenus.MenuAccessor _menu22) ? _menu22.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("hello")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "do you have a blanket I could borrow?", true);
			} else if (((entity instanceof Player _entity24 && _entity24.containerMenu instanceof GorpModMenus.MenuAccessor _menu24) ? _menu24.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("why")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "i'm a bit chilly, heh.", true);
			} else if (((entity instanceof Player _entity26 && _entity26.containerMenu instanceof GorpModMenus.MenuAccessor _menu26) ? _menu26.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("no")
					|| ((entity instanceof Player _entity27 && _entity27.containerMenu instanceof GorpModMenus.MenuAccessor _menu27) ? _menu27.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("sorry")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "i'm so cold i am begging you please", true);
			} else if (((entity instanceof Player _entity29 && _entity29.containerMenu instanceof GorpModMenus.MenuAccessor _menu29) ? _menu29.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("gorpy layer")) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "i just really need a blanket right now", true);
			} else if (((entity instanceof Player _entity31 && _entity31.containerMenu instanceof GorpModMenus.MenuAccessor _menu31) ? _menu31.getMenuState(0, "GorpMessage", "") : "").length() > 0) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", (Component.translatable(("dialogue.icygorp." + Mth.nextInt(RandomSource.create(), 0, 4))).getString()), true);
			}
		} else if (((entity instanceof Player _entity35 && _entity35.containerMenu instanceof GorpModMenus.MenuAccessor _menu35) ? _menu35.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("hello")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Hi! I'm Gorp!", true);
		} else if (((entity instanceof Player _entity37 && _entity37.containerMenu instanceof GorpModMenus.MenuAccessor _menu37) ? _menu37.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("hi")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Hello! I am Gorp!", true);
		} else if (((entity instanceof Player _entity39 && _entity39.containerMenu instanceof GorpModMenus.MenuAccessor _menu39) ? _menu39.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("i have a boner")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Rightly so!", true);
		} else if (((entity instanceof Player _entity41 && _entity41.containerMenu instanceof GorpModMenus.MenuAccessor _menu41) ? _menu41.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("whats up")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Just keep on Jorpin' on...", true);
		} else if (((entity instanceof Player _entity43 && _entity43.containerMenu instanceof GorpModMenus.MenuAccessor _menu43) ? _menu43.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("music")
				|| ((entity instanceof Player _entity44 && _entity44.containerMenu instanceof GorpModMenus.MenuAccessor _menu44) ? _menu44.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("song")) {
			if (!(entity instanceof ServerPlayer _plr45 && _plr45.level() instanceof ServerLevel _serverLevel45
					&& _plr45.getAdvancements().getOrStartProgress(_serverLevel45.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:gorpinominal_taste"))).isDone())) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Hey, wanna listen to some tunes?", true);
				if (entity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(GorpModItems.GORP_RADIO_DISC.get()).copy();
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("gorp:gorpinominal_taste"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "The Cyberpunk 2077 OST is the freaking bomb!", true);
			}
		} else if (((entity instanceof Player _entity50 && _entity50.containerMenu instanceof GorpModMenus.MenuAccessor _menu50) ? _menu50.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("aggrussel")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "I hate those stinky aggrussels!", true);
		} else if (((entity instanceof Player _entity52 && _entity52.containerMenu instanceof GorpModMenus.MenuAccessor _menu52) ? _menu52.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("tron")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Tron Legacy is one of the all-time greats!", true);
		} else if (((entity instanceof Player _entity54 && _entity54.containerMenu instanceof GorpModMenus.MenuAccessor _menu54) ? _menu54.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("bite")
				|| ((entity instanceof Player _entity55 && _entity55.containerMenu instanceof GorpModMenus.MenuAccessor _menu55) ? _menu55.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("permission")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "A watched pot never boils! ...or something", true);
		} else if (((entity instanceof Player _entity57 && _entity57.containerMenu instanceof GorpModMenus.MenuAccessor _menu57) ? _menu57.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("last ascension")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Just sayin', mage class is for losers", true);
		} else if (((entity instanceof Player _entity59 && _entity59.containerMenu instanceof GorpModMenus.MenuAccessor _menu59) ? _menu59.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("i love you")) {
			if (entity instanceof Player _player)
				_player.closeContainer();
			if (world.isClientSide())
				Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(GorpModItems.AREA_EFFECT_CLOUD_2.get()));
		} else if (((entity instanceof Player _entity62 && _entity62.containerMenu instanceof GorpModMenus.MenuAccessor _menu62) ? _menu62.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("how are you")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "I'm doing quite gorpily!", true);
		} else if ((((entity instanceof Player _entity64 && _entity64.containerMenu instanceof GorpModMenus.MenuAccessor _menu64) ? _menu64.getMenuState(0, "GorpMessage", "") : "").toLowerCase()).equals("let me eat your gorpy layer")) {
			if (entity instanceof ServerPlayer _plr65 && _plr65.level() instanceof ServerLevel _serverLevel65
					&& _plr65.getAdvancements().getOrStartProgress(_serverLevel65.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:permission_gorped"))).isDone()) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "I already said you could! Bite away!", true);
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "Okay! You asked nicely after all.", true);
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("gorp:permission_gorped"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		} else if (((entity instanceof Player _entity69 && _entity69.containerMenu instanceof GorpModMenus.MenuAccessor _menu69) ? _menu69.getMenuState(0, "GorpMessage", "") : "").toLowerCase().contains("help")) {
			if (entity instanceof Player _player)
				_player.closeContainer();
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
									_ent.level().getServer(), _ent),
							"/tellraw @s [\"\",{\"text\":\"Stuck or lost? Visit the wiki guide at \"},{\"text\":\"https://gorps.world/\",\"color\":\"#92DC36\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://gorps.world/\"}}]");
				}
			}
		} else if ((((entity instanceof Player _entity72 && _entity72.containerMenu instanceof GorpModMenus.MenuAccessor _menu72) ? _menu72.getMenuState(0, "GorpMessage", "") : "").toLowerCase()).equals("/bypassgorp epic style")) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", "gorp.keyPhrase() bypassed successfully.", true);
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("gorp:permission_gorped"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		} else if (((entity instanceof Player _entity75 && _entity75.containerMenu instanceof GorpModMenus.MenuAccessor _menu75) ? _menu75.getMenuState(0, "GorpMessage", "") : "").length() > 0) {
			if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "GorpResponse", (Component.translatable(("dialogue.gorp." + Mth.nextInt(RandomSource.create(), 0, 4))).getString()), true);
		}
	}
}