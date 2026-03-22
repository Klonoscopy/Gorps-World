package world.gorps.gorp.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import world.gorps.gorp.GorpsWorld;

public class CustomSounds {
	private CustomSounds() {
	}
	public static final SoundEvent JONK = registerSound("jonk");
	public static final SoundEvent CRITICAL_JONK = registerSound("critical_jonk");
	public static final SoundEvent FLING_NORMAL = registerSound("fling_normal");
	public static final SoundEvent FLING_SHORT = registerSound("fling_short");
	public static final SoundEvent FLING_LONG = registerSound("fling_long");
	public static final SoundEvent BELCH = registerSound("belch");
	public static final SoundEvent GULP =  registerSound("gulp");

	private static SoundEvent registerSound(String id) {
		Identifier identifier = Identifier.fromNamespaceAndPath(GorpsWorld.MOD_ID, id);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
	}

	public static void initialize() {
	}
}