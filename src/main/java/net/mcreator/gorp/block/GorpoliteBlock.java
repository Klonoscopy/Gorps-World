
package net.mcreator.gorp.block;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.util.ForgeSoundType;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.gorp.procedures.GorpoliteBlockAddedProcedure;

public class GorpoliteBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty RANDOMROTATION = BooleanProperty.create("randomrotation");

	public GorpoliteBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN)
				.sound(new ForgeSoundType(1.0f, 1.0f, () -> ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.netherrack.break")), () -> ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.froglight.step")),
						() -> ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.lodestone.place")), () -> ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.froglight.hit")),
						() -> ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.froglight.fall"))))
				.strength(0.2f, 10f).requiresCorrectToolForDrops().jumpFactor(1.1f));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(RANDOMROTATION, false));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 14;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, RANDOMROTATION);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getClickedFace().getAxis() == Direction.Axis.Y)
			return super.getStateForPlacement(context).setValue(FACING, Direction.NORTH).setValue(RANDOMROTATION, false);
		return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace()).setValue(RANDOMROTATION, false);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return 30f;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 3;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		GorpoliteBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}
}
