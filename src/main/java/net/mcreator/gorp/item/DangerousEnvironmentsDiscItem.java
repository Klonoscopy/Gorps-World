package net.mcreator.gorp.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class DangerousEnvironmentsDiscItem extends RecordItem {
	public DangerousEnvironmentsDiscItem() {
		super(5, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("gorp:gorp_radio_dangerous_environments")), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 1480);
	}
}