package net.mcreator.gorp.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.mcreator.gorp.procedures.ConsolidatedGorpCubeRightclickedOnBlockProcedure;

public class ConsolidatedGorpCubeItem extends Item {
	public ConsolidatedGorpCubeItem() {
		super(new Item.Properties().stacksTo(8));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		ConsolidatedGorpCubeRightclickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getPlayer());
		return InteractionResult.SUCCESS;
	}
}