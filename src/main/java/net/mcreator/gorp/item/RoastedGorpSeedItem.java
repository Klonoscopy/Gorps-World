package net.mcreator.gorp.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import net.mcreator.gorp.procedures.RoastedGorpSeedPlayerFinishesUsingItemProcedure;

public class RoastedGorpSeedItem extends Item {
	public RoastedGorpSeedItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.3f).alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		RoastedGorpSeedPlayerFinishesUsingItemProcedure.execute(entity);
		return retval;
	}
}