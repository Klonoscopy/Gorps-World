package net.mcreator.gorp.procedures;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.gorp.network.GorpModVariables;
import net.mcreator.gorp.GorpMod;

public class GorpsWorldPlayerLeavesDimensionProcedure {
	public static void execute(LevelAccessor world, double x, double z, Entity entity) {
		if (entity == null)
			return;
		{
			Entity _ent = entity;
			if (!_ent.level().isClientSide() && _ent.getServer() != null) {
				_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
						_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("teleport @s ~ " + (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z) + 2) + " ~"));
			}
		}
		if (GorpModVariables.WorldVariables.get(world).gorphel_defeated == 2) {
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
									_ent.level().getServer(), _ent),
							"tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> We just defeated \"},{\"text\":\"Gorphel, the Unslain\",\"color\":\"#CD1525\"},{\"text\":\", but why do I still feel uneasy?\"}]");
				}
			}
			GorpMod.queueServerWork(160, () -> {
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
								"tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Poly-Gorphel\",\"color\":\"#CD1525\"},{\"text\":\"> You've only just met your match.\"}]");
					}
				}
			});
		}
	}
}