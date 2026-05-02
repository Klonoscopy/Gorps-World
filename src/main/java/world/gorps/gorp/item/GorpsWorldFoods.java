package world.gorps.gorp.item;

import net.minecraft.world.food.FoodProperties;

public class GorpsWorldFoods {
    public static final FoodProperties GORP = new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).alwaysEdible().build();
    public static final FoodProperties BITTEN_GORP = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties JORP_JUICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.4F).build();
}
