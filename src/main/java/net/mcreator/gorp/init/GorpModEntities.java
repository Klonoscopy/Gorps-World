/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.gorp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.gorp.entity.HostileBreadEntity;
import net.mcreator.gorp.entity.GorplingEntity;
import net.mcreator.gorp.entity.GorpCoinProjectileEntity;
import net.mcreator.gorp.entity.GorpBuddyEntity;
import net.mcreator.gorp.GorpMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GorpModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GorpMod.MODID);
	public static final RegistryObject<EntityType<GorpBuddyEntity>> GORP_BUDDY = register("gorp_buddy",
			EntityType.Builder.<GorpBuddyEntity>of(GorpBuddyEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(GorpBuddyEntity::new)

					.sized(0.6f, 0.6f));
	public static final RegistryObject<EntityType<GorplingEntity>> GORPLING = register("gorpling",
			EntityType.Builder.<GorplingEntity>of(GorplingEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(GorplingEntity::new)

					.sized(0.3f, 0.4f));
	public static final RegistryObject<EntityType<HostileBreadEntity>> HOSTILE_BREAD = register("hostile_bread",
			EntityType.Builder.<HostileBreadEntity>of(HostileBreadEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HostileBreadEntity::new)

					.sized(1.5f, 2f));
	public static final RegistryObject<EntityType<GorpCoinProjectileEntity>> GORP_COIN_PROJECTILE = register("gorp_coin_projectile", EntityType.Builder.<GorpCoinProjectileEntity>of(GorpCoinProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(GorpCoinProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			GorpBuddyEntity.init();
			GorplingEntity.init();
			HostileBreadEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(GORP_BUDDY.get(), GorpBuddyEntity.createAttributes().build());
		event.put(GORPLING.get(), GorplingEntity.createAttributes().build());
		event.put(HOSTILE_BREAD.get(), HostileBreadEntity.createAttributes().build());
	}
}