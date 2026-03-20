package net.mcreator.gorp.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.gorp.network.GorpModVariables;
import net.mcreator.gorp.init.GorpModMenus;

public class GorpophoneButtonClickProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (getAmountInGUISlot(entity, 0) > 0) {
			if (!((entity instanceof Player _entity1 && _entity1.containerMenu instanceof GorpModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "gorpcastmsg", "") : "").isEmpty()) {
				if (entity instanceof Player _player && _player.containerMenu instanceof GorpModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(0).remove(1);
					_player.containerMenu.broadcastChanges();
				}
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"title @a times 8 50 8");
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("title @a subtitle {\"text\":\"\\\"" + ""
									+ (((entity instanceof Player _entity4 && _entity4.containerMenu instanceof GorpModMenus.MenuAccessor _menu4) ? _menu4.getMenuState(0, "gorpcastmsg", "") : "").replaceAll("\\\"", "")) + "\\\"\"}"));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"title @a title {\"text\":\"\"}");
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("Gorpcasted by: " + entity.getDisplayName().getString())), true);
				if (entity instanceof Player _player)
					_player.closeContainer();
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("/tellraw @a [\"\",{\"text\":\"[GORPCAST] \",\"color\":\"#73F008\"},{\"text\":\"from " + "" + entity.getDisplayName().getString() + "\",\"color\":\"gray\"},{\"text\":\"\\n\"},{\"text\":\""
									+ (((entity instanceof Player _entity11 && _entity11.containerMenu instanceof GorpModMenus.MenuAccessor _menu11) ? _menu11.getMenuState(0, "gorpcastmsg", "") : "").replaceAll("\"", "\\\""))
									+ "\",\"color\":\"white\"}]"));
			} else {
				{
					entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.gorpophone_tooltip_text = "\u00A7cText field cannot be empty";
						capability.markSyncDirty();
					});
				}
			}
		} else {
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.gorpophone_tooltip_text = "\u00A7cRequires (1x) Gorp Coin";
					capability.markSyncDirty();
				});
			}
		}
	}

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof GorpModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}
}