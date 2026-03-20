package net.mcreator.gorp.potion;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.procedures.JorpFootedEffectExpiresProcedure;

public class JorpFootedMobEffect extends MobEffect {
	public JorpFootedMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -12354298);
		this.addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "b5625205-baba-3244-ba55-8d05a3117433", -0.2, AttributeModifier.Operation.MULTIPLY_BASE);
		this.addAttributeModifier(ForgeMod.STEP_HEIGHT_ADDITION.get(), "ee8ea608-d59a-3f05-a4d4-6ac9283e70c1", 0.3, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "6828d61f-ba4f-373b-8461-a479719bb461", 0.07, AttributeModifier.Operation.MULTIPLY_BASE);
		this.addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "d4a4f841-f36e-380c-8b1e-716f531af8ef", 0.1, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		JorpFootedEffectExpiresProcedure.execute();
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}