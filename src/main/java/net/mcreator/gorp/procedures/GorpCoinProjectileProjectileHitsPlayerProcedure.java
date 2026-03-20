package net.mcreator.gorp.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.gorp.init.GorpModItems;
import net.mcreator.gorp.init.GorpModGameRules;

public class GorpCoinProjectileProjectileHitsPlayerProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double random = 0;
		random = 98 + Math.random();
		if (!world.getLevelData().getGameRules().getBoolean(GorpModGameRules.DISABLE_GLINGSHOT_TRADE) && (entity == sourceentity) == false) {
			if (!((entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null) instanceof IItemHandlerModifiable _modHandler2 ? _modHandler2.getStackInSlot((int) random).copy() : ItemStack.EMPTY).getItem() == GorpModItems.GORP_COIN.get())) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, (entity.getX()), (entity.getY() + 1), (entity.getZ()),
							(entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null) instanceof IItemHandlerModifiable _modHandler7 ? _modHandler7.getStackInSlot((int) random).copy() : ItemStack.EMPTY));
					entityToSpawn.setPickUpDelay(5);
					entityToSpawn.setUnlimitedLifetime();
					_level.addFreshEntity(entityToSpawn);
				}
				{
					final int _slotid = (int) random;
					final ItemStack _setstack = new ItemStack(GorpModItems.GORP_COIN.get()).copy();
					_setstack.setCount(1);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot)
							_modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
					});
				}
			} else {
				if (entity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(GorpModItems.GORP_COIN.get()).copy();
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			}
		}
	}
}