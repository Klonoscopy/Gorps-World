package net.mcreator.gorp.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.gorp.procedures.GorpPlayerFinishesUsingItemProcedure;
import net.mcreator.gorp.procedures.GorpOnDroppedItemEntityDestroyedProcedure;
import net.mcreator.gorp.procedures.BittenGorpRightclickedProcedure;
import net.mcreator.gorp.init.GorpModItems;

public class GorpItem extends Item {
	public GorpItem() {
		super(new Item.Properties().stacksTo(8).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0f).alwaysEat().build()));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 12;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		BittenGorpRightclickedProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
		return ar;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = new ItemStack(GorpModItems.BITTEN_GORP.get());
		super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		GorpPlayerFinishesUsingItemProcedure.execute(world, x, y, z, entity, itemstack);
		if (itemstack.isEmpty()) {
			return retval;
		} else {
			if (entity instanceof Player player && !player.getAbilities().instabuild) {
				if (!player.getInventory().add(retval))
					player.drop(retval, false);
			}
			return itemstack;
		}
	}

	@Override
	public void onDestroyed(ItemEntity entity, DamageSource damagesource) {
		super.onDestroyed(entity, damagesource);
		GorpOnDroppedItemEntityDestroyedProcedure.execute(entity.level(), entity);
	}
}