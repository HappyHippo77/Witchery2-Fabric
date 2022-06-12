package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

import static java.lang.Math.round;

public class WitchsCauldron extends BlockWithEntity implements BlockEntityProvider {

    public WitchsCauldron(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        WitchsCauldronEntity entity = (WitchsCauldronEntity) world.getBlockEntity(pos);
        if (entity.isBoiling()) {
            int i;
            double xPos;
            double zPos;
            for (i = 0; i < 2; ++i) {
                xPos = 0.2D + random.nextDouble() * 0.6D;
                zPos = 0.2D + random.nextDouble() * 0.6D;
                double yPos = 0;
                switch (entity.getLevel()) {
                    case 1 -> yPos = 0.375d;
                    case 2 -> yPos = 0.53125d;
                    case 3 -> yPos = 0.6875d;
                }
                Witchery2.bubbleParticleDataSetter.setData(entity.getColorR(), entity.getColorG(), entity.getColorB(), 255);
                world.addParticle(ModParticles.BUBBLE_PARTICLE, pos.getX() + xPos, pos.getY() + yPos, pos.getZ() + zPos, 0, 0, 0);
            }

            if (entity.isPowered()) {
                for (i = 0; i < 1 + Math.min(entity.getRitualSeconds(), 5); ++i) {
                    xPos = 0.3D + random.nextDouble() * 0.4D;
                    zPos = 0.3D + random.nextDouble() * 0.4D;
                    double yPos = 0;
                    switch (entity.getLevel()) {
                        case 1 -> yPos = 0.375d;
                        case 2 -> yPos = 0.53125d;
                        case 3 -> yPos = 0.6875d;
                    }
                    float maxColorShift = 0.2F;
                    float doubleColorShift = maxColorShift * 2.0F;
                    float colorshiftR = random.nextFloat() * doubleColorShift - maxColorShift;
                    float colorshiftG = random.nextFloat() * doubleColorShift - maxColorShift;
                    float colorshiftB = random.nextFloat() * doubleColorShift - maxColorShift;
                    Witchery2.powerParticleDataSetter.setData(
                            round((float) entity.getColorR() / 255 + colorshiftR * 255),
                            round((float) entity.getColorG() / 255 + colorshiftG * 255),
                            round((float) entity.getColorB() / 255 + colorshiftB * 255),
                            255, entity.isRitualInProgress(), entity.isRitualInProgress());
                    world.addParticle(ModParticles.POWER_PARTICLE, pos.getX() + xPos, pos.getY() + yPos, pos.getZ() + zPos, random.nextDouble() * 0.08D - 0.04D, random.nextDouble() * 0.05D + 0.08D, random.nextDouble() * 0.08D - 0.04D);
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WitchsCauldronEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.WITCHS_CAULDRON_ENTITY, (world1, pos, state1, be) -> WitchsCauldronEntity.tick(world1, pos, state1, be));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof WitchsCauldronEntity entity)
        {
            return entity.onUse(state, world, pos, player, hand, hit);
        }
        return ActionResult.PASS;
    }
}
