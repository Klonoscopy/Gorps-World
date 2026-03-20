package net.mcreator.gorp.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.gorp.init.GorpModEnchantments;

import java.util.Comparator;

public class GorpSpearEntitySwingsItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double duration = 0;
		double Knockback = 0;
		if (EnchantmentHelper.getItemEnchantmentLevel(GorpModEnchantments.JORP_START.get(), itemstack) != 0 && !(entity instanceof Player _plrCldCheck3 && _plrCldCheck3.getCooldowns().isOnCooldown(itemstack.getItem()))) {
			Knockback = 1.8;
			{
				final Vec3 _center = new Vec3(x, (y + 0.7), z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					if (entityiterator instanceof Player) {
						entityiterator.setDeltaMovement(new Vec3((Math.sin(entityiterator.getYRot() * (-1) * 0.017453292) * Math.cos(entityiterator.getXRot() * 0.017453292) * Knockback),
								(Math.sin(entityiterator.getXRot() * 0.017453292) * (-1) * Knockback), (Math.cos(entityiterator.getYRot() * (-1) * 0.017453292) * Math.cos(entityiterator.getXRot() * 0.017453292) * Knockback)));
					}
				}
			}
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), (int) ((entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) ? 0 : 20));
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:fling_0")), SoundSource.PLAYERS, 1, (float) Mth.nextDouble(RandomSource.create(), 0.7, 0.9));
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:fling_0")), SoundSource.PLAYERS, 1, (float) Mth.nextDouble(RandomSource.create(), 0.7, 0.9), false);
					}
				}
			}
		}
	}
}