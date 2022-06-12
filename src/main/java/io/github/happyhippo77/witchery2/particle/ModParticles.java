package io.github.happyhippo77.witchery2.particle;

import io.github.happyhippo77.witchery2.Witchery2;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {
    public static final DefaultParticleType BUBBLE_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType POWER_PARTICLE = FabricParticleTypes.simple();

    public static void registerAllParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Witchery2.MOD_ID, "bubble_particle"), BUBBLE_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Witchery2.MOD_ID, "power_particle"), POWER_PARTICLE);
    }
}
