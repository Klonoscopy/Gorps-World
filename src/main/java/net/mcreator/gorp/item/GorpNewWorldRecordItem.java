package net.mcreator.gorp.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class GorpNewWorldRecordItem extends RecordItem {
	public GorpNewWorldRecordItem() {
		super(15, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:gorp_radio_new_world")), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2040);
	}
}