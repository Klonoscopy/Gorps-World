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

import java.util.Calendar;

public class GorpHammerRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 12) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("tellraw @a {\"text\":\"<Gorp> The time is now " + "" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 12) + ":"
								+ (Calendar.getInstance().get(Calendar.MINUTE) < 10 ? "0" + Calendar.getInstance().get(Calendar.MINUTE) : Calendar.getInstance().get(Calendar.MINUTE)) + " PM\"}"));
		} else {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("tellraw @a {\"text\":\"<Gorp> The time is now " + "" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 0 ? 12 : Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + ":"
								+ (Calendar.getInstance().get(Calendar.MINUTE) < 10 ? "0" + Calendar.getInstance().get(Calendar.MINUTE) : Calendar.getInstance().get(Calendar.MINUTE)) + " AM.\"}"));
		}
		if (entity instanceof Player _player)
			_player.getCooldowns().addCooldown(itemstack.getItem(), 40);
	}
}