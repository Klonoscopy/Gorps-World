package net.mcreator.gorp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.network.GorpModVariables;
import net.mcreator.gorp.init.GorpModMenus;
import net.mcreator.gorp.GorpMod;

public class GorpophoneCostButtonProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double slot_count = 0;
		GorpMod.queueServerWork(1, () -> {
			{
				entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.gorpophone_slot_count = getAmountInGUISlot(entity, 0);
					capability.markSyncDirty();
				});
			}
			if (getAmountInGUISlot(entity, 0) == 0) {
				{
					entity.getCapability(GorpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.gorpophone_tooltip_text = "\u00A7cRequires (1x) Gorp Coin";
						capability.markSyncDirty();
					});
				}
			}
		});
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