package net.mcreator.gorp.potion;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.gorp.procedures.OnFireEffectStartedappliedProcedure;
import net.mcreator.gorp.procedures.OnFireEffectExpiresProcedure;

public class OnFireMobEffect extends MobEffect {
	public OnFireMobEffect() {
		super(MobEffectCategory.HARMFUL, -39424);
		this.addAttributeModifier(ForgeMod.STEP_HEIGHT_ADDITION.get(), "99dff28e-8922-3889-b8ae-084ad57695ae", 0.6, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.addAttributeModifiers(entity, attributeMap, amplifier);
		OnFireEffectStartedappliedProcedure.execute();
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		OnFireEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}