package net.mcreator.gorp.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import net.mcreator.gorp.procedures.IcyGorpPlayerFinishesUsingItemProcedure;

public class IcyGorpItem extends Item {
	public IcyGorpItem() {
		super(new Item.Properties().rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.4f).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		IcyGorpPlayerFinishesUsingItemProcedure.execute(entity);
		return retval;
	}
}