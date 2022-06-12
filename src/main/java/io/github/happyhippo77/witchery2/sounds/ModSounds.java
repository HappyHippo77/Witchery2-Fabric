package io.github.happyhippo77.witchery2.sounds;

import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final Identifier RANDOM_BLOP_IDENTIFIER = new Identifier("witchery2:random.blop");
    public static SoundEvent RANDOM_BLOP = new SoundEvent(RANDOM_BLOP_IDENTIFIER);
    public static void registerAllSounds() {
        Registry.register(Registry.SOUND_EVENT, RANDOM_BLOP_IDENTIFIER, RANDOM_BLOP);
    }
}
