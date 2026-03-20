package net.mcreator.gorp.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.gorp.procedures.GorpasolRightclickedProcedure;
import net.mcreator.gorp.procedures.GorpasolOnPlayerStoppedUsingProcedure;

public class GorpasolItem extends Item {
	public GorpasolItem() {
		super(new Item.Properties().stacksTo(1));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.CROSSBOW;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 72000;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		entity.startUsingItem(hand);
		GorpasolRightclickedProcedure.execute(entity);
		return ar;
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
		GorpasolOnPlayerStoppedUsingProcedure.execute(entity);
	}
}