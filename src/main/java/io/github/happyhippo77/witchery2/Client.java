package io.github.happyhippo77.witchery2;

import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import io.github.happyhippo77.witchery2.particle.particles.BubbleParticle;
import io.github.happyhippo77.witchery2.particle.particles.PowerParticle;
import io.github.happyhippo77.witchery2.render.blockentities.WitchsCauldronEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Here we will put client-only registration code
        BlockEntityRendererRegistry.register(ModBlockEntities.WITCHS_CAULDRON_ENTITY, WitchsCauldronEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.BUBBLE_PARTICLE, BubbleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.POWER_PARTICLE, PowerParticle.Factory::new);
    }
}
