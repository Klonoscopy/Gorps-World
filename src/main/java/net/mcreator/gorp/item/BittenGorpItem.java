package net.mcreator.gorp.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import net.mcreator.gorp.procedures.BittenGorpPlayerFinishesUsingItemProcedure;

public class BittenGorpItem extends Item {
	public BittenGorpItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.2f).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		BittenGorpPlayerFinishesUsingItemProcedure.execute(world, x, y, z, entity, itemstack);
		return retval;
	}
}