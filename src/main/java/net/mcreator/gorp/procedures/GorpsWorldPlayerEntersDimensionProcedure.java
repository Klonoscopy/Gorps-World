package net.mcreator.gorp.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.gorp.GorpMod;

public class GorpsWorldPlayerEntersDimensionProcedure {
	public static void execute(LevelAccessor world, double x, double z, Entity entity) {
		if (entity == null)
			return;
		double safetospawn = 0;
		safetospawn = 64;
		while (safetospawn > 0) {
			if (!((world.getBlockState(BlockPos.containing(x, safetospawn, z))).getBlock() == Blocks.AIR && (world.getBlockState(BlockPos.containing(x, safetospawn - 1, z))).getBlock() == Blocks.AIR)) {
				safetospawn = safetospawn - 1;
			} else {
				break;
			}
		}
		while (safetospawn > 0) {
			if ((world.getBlockState(BlockPos.containing(x, safetospawn - 1, z))).getBlock() == Blocks.AIR) {
				safetospawn = safetospawn - 1;
			} else {
				break;
			}
		}
		{
			Entity _ent = entity;
			if (!_ent.level().isClientSide() && _ent.getServer() != null) {
				_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
						_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("teleport @s ~ " + safetospawn + " ~"));
			}
		}
		if (entity instanceof ServerPlayer _player) {
			Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("gorp:a_step_into_the_gorp"));
			AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
			if (!_ap.isDone()) {
				for (String criteria : _ap.getRemainingCriteria())
					_player.getAdvancements().award(_adv, criteria);
			}
		}
		if (!world.isClientSide()) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.crit")), SoundSource.NEUTRAL, (float) 0.3, 1);
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.crit")), SoundSource.NEUTRAL, (float) 0.3, 1, false);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, (float) 0.3, (float) 0.9);
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, (float) 0.3, (float) 0.9, false);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.thunder")), SoundSource.NEUTRAL, (float) 0.3, (float) 1.2);
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.thunder")), SoundSource.NEUTRAL, (float) 0.3, (float) 1.2, false);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_shrieker.shriek")), SoundSource.NEUTRAL, (float) 0.3, 1);
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_shrieker.shriek")), SoundSource.NEUTRAL, (float) 0.3, 1, false);
				}
			}
		}
		if (!(entity instanceof ServerPlayer _plr25 && _plr25.level() instanceof ServerLevel _serverLevel25
				&& _plr25.getAdvancements().getOrStartProgress(_serverLevel25.getServer().getAdvancements().getAdvancement(new ResourceLocation("gorp:a_step_into_the_gorp"))).isDone())) {
			GorpMod.queueServerWork(100, () -> {
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
								"tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> You've finally made it to Gorp's World!\"}]");
					}
				}
				GorpMod.queueServerWork(100, () -> {
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> Oh, by the way...\"}]");
						}
					}
					GorpMod.queueServerWork(30, () -> {
						{
							Entity _ent = entity;
							if (!_ent.level().isClientSide() && _ent.getServer() != null) {
								_ent.getServer().getCommands().performPrefixedCommand(
										new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
												_ent.getDisplayName(), _ent.level().getServer(), _ent),
										"tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> My home is under siege by \"},{\"text\":\"Grip, the Gorp God\",\"bold\":true,\"italic\":true,\"color\":\"#FF356B\"},{\"text\":\". An unruly tyrant who wants to invade the Overworld to assume control.\",\"color\":\"white\"}]");
							}
						}
						GorpMod.queueServerWork(100, () -> {
							{
								Entity _ent = entity;
								if (!_ent.level().isClientSide() && _ent.getServer() != null) {
									_ent.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
													_ent.getDisplayName(), _ent.level().getServer(), _ent),
											"/tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> Your world will be in grave danger if he acquires the \"},{\"text\":\"Gorp Rift Bioconduit\",\"bold\":true,\"italic\":true,\"color\":\"#E66FEA\"},{\"text\":\".\"}]");
								}
							}
							GorpMod.queueServerWork(80, () -> {
								{
									Entity _ent = entity;
									if (!_ent.level().isClientSide() && _ent.getServer() != null) {
										_ent.getServer().getCommands()
												.performPrefixedCommand(
														new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
																_ent.getDisplayName(), _ent.level().getServer(), _ent),
														"/tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> You know, that thing you're holding...\"}]");
									}
								}
								GorpMod.queueServerWork(50, () -> {
									{
										Entity _ent = entity;
										if (!_ent.level().isClientSide() && _ent.getServer() != null) {
											_ent.getServer().getCommands()
													.performPrefixedCommand(
															new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
																	_ent.getDisplayName(), _ent.level().getServer(), _ent),
															"/tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> Basically, you need to kill God.\"}]");
										}
									}
									GorpMod.queueServerWork(40, () -> {
										{
											Entity _ent = entity;
											if (!_ent.level().isClientSide() && _ent.getServer() != null) {
												_ent.getServer().getCommands()
														.performPrefixedCommand(
																new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
																		_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
																"/tellraw @p [\"\",{\"text\":\"<\"},{\"text\":\"Gorp\",\"color\":\"#A5EA58\"},{\"text\":\"> Good luck!\"}]");
											}
										}
									});
								});
							});
						});
					});
				});
			});
		}
	}
}