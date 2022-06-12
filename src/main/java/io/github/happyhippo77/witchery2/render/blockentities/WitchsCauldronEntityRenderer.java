package io.github.happyhippo77.witchery2.render.blockentities;

import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.PlayerScreenHandler;

@Environment(EnvType.CLIENT)
public class WitchsCauldronEntityRenderer implements BlockEntityRenderer<WitchsCauldronEntity> {
    // A jukebox itemstack
    private static ItemStack stack = new ItemStack(Items.JUKEBOX, 1);

    public WitchsCauldronEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(WitchsCauldronEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        int colorA = 51;

        int level = entity.getLevel();
        int colorR = entity.getColorR();
        int colorG = entity.getColorG();
        int colorB = entity.getColorB();

        float waterHeight = 0;
        boolean filled = true;

        switch (level) {
            case 0 -> filled = false;
            case 1 -> waterHeight = 0.375f;
            case 2 -> waterHeight = 0.53125f;
            case 3 -> waterHeight = 0.6875f;
        }

        matrices.push();

        if (filled) {
            SpriteAtlasTexture atlas = MinecraftClient.getInstance()
                    .getBakedModelManager()
                    .getAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);

            Sprite waterTexture = atlas.getSprite(SimpleFluidRenderHandler.WATER_STILL);

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayers.getFluidLayer(Fluids.WATER.getDefaultState()));
            vertexConsumer = waterTexture.getTextureSpecificVertexConsumer(vertexConsumer);

            // COLOR TINTS THE TEXTURE
            vertexConsumer.vertex(matrices.peek().getPositionMatrix(), 0.125F, waterHeight, 0.125F)
                    .color(colorR, colorG, colorB, colorA)
                    .texture(waterTexture.getMinU(), waterTexture.getMinV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
            vertexConsumer
                    .vertex(matrices.peek().getPositionMatrix(), 0.125F, waterHeight, 0.875F)
                    .color(colorR, colorG, colorB, colorA)
                    .texture(waterTexture.getMinU(), waterTexture.getMaxV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
            vertexConsumer
                    .vertex(matrices.peek().getPositionMatrix(), 0.875F, waterHeight, 0.875F)
                    .color(colorR, colorG, colorB, colorA)
                    .texture(waterTexture.getMaxU(), waterTexture.getMaxV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
            vertexConsumer
                    .vertex(matrices.peek().getPositionMatrix(), 0.875F, waterHeight, 0.125F)
                    .color(colorR, colorG, colorB, colorA)
                    .texture(waterTexture.getMaxU(), waterTexture.getMinV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
        }

        // Mandatory call after GL calls
        matrices.pop();
    }
}
