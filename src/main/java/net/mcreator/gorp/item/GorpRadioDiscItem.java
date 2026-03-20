package net.mcreator.gorp.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.List;

public class GorpRadioDiscItem extends RecordItem {
	public GorpRadioDiscItem() {
		super(5, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:gorp_radio")), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3220);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.gorp.gorp_radio_disc.description_0"));
		list.add(Component.translatable("item.gorp.gorp_radio_disc.description_1"));
	}
}