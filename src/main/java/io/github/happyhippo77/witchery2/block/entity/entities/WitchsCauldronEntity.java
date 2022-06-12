package io.github.happyhippo77.witchery2.block.entity.entities;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import io.github.happyhippo77.witchery2.particle.particles.BubbleParticle;
import io.github.happyhippo77.witchery2.sounds.ModSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.util.Random;

public class WitchsCauldronEntity extends BlockEntity {
    // Store the current value of the level
    private int level = 0;
    private int colorR = 52;
    private int colorG = 95;
    private int colorB = 218;
    private int TicksHeated = 0;
    private int RitualTicks = 0;
    private boolean powered = false;
    private boolean ritualInProgress;
    public WitchsCauldronEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WITCHS_CAULDRON_ENTITY, pos, state);
    }

    private boolean boiling = false;

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (heldItem.getItem() == Items.WATER_BUCKET || PotionUtil.getPotion(heldItem) == Potions.WATER) {
            if (level < 3 && colorR == 52 && colorG == 95 && colorB == 218) {
                if (!player.isCreative()) {
                    if (heldItem.getItem() == Items.WATER_BUCKET) {
                        player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                    }
                    if (PotionUtil.getPotion(heldItem) == Potions.WATER) {
                        player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
                    }
                }
                if (heldItem.getItem() == Items.WATER_BUCKET) {
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                if (PotionUtil.getPotion(heldItem) == Potions.WATER) {
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                level += 1;
                markDirty();
                return ActionResult.SUCCESS;
            }
        }
        if (heldItem.getItem() == Items.BUCKET || heldItem.getItem() == Items.GLASS_BOTTLE) {
            if (level > 0) {
                if (colorR == 52 && colorG == 95 && colorB == 218){
                    if (!player.isCreative()) {
                        if (heldItem.getItem() == Items.BUCKET) {
                            player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
                        }
                        if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                            player.setStackInHand(hand, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
                        }
                    }
                    if (heldItem.getItem() == Items.BUCKET) {
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }
                    if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                        world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }
                    world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);

                    level -= 1;
                }
                else {
                    // DO SOMETHING
                }
                markDirty();
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.SUCCESS;

    }

    public int getLevel() {
        return level;
    }

    public int getColorR() {
        return colorR;
    }

    public int getColorG() {
        return colorG;
    }

    public int getColorB() {
        return colorB;
    }

    public int getTicksHeated() {
        return TicksHeated;
    }

    public boolean isRitualInProgress() {
        return ritualInProgress;
    }

    public int getRitualTicks() {
        return RitualTicks;
    }
    public int getRitualSeconds() {
        return RitualTicks / 20;
    }

    public boolean isBoiling() {
        return boiling;
    }

    public boolean isPowered() {
        return powered;
    }

    private final Random random = new Random();

    public static void tick(World world, BlockPos pos, BlockState state, WitchsCauldronEntity entity) {
        if (entity.level == 0) {
            entity.colorR = 52;
            entity.colorG = 95;
            entity.colorB = 218;
        }

        // Calculate Ticks Heated
        if (world.getBlockState(pos.down(1)).getBlock() == Blocks.FIRE) {
            if (entity.TicksHeated < 100) {
                entity.TicksHeated += 1;
            }
        }
        else {
            entity.TicksHeated = 0;
        }
        // Set boiling based on whether TicksHeated is equal to 100
        entity.boiling = entity.TicksHeated == 100;

        if (entity.boiling && entity.colorR != 52 || entity.colorG != 95 || entity.colorB != 218) {
            if (entity.random.nextInt(15) == 0) {
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.RANDOM_BLOP, SoundCategory.BLOCKS, 0.8F + entity.random.nextFloat() * 0.2F, 0.8F + entity.random.nextFloat() * 0.2F);
            }
        }

        entity.ritualInProgress = entity.getRitualTicks() > 0;
    }

    // Serialize the BlockEntity
    @Override
    public void writeNbt(NbtCompound tag) {
        // Save the current value of the number to the tag
        tag.putInt("level", level);
        tag.putInt("colorR", colorR);
        tag.putInt("colorG", colorG);
        tag.putInt("colorB", colorB);
        tag.putInt("TicksHeated", TicksHeated);
        tag.putInt("RitualTicks", RitualTicks);
        tag.putBoolean("powered", powered);

        super.writeNbt(tag);
    }

    // Deserialize the BlockEntity
    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);

        level = tag.getInt("level");
        colorR = tag.getInt("colorR");
        colorG = tag.getInt("colorG");
        colorB = tag.getInt("colorB");
        TicksHeated = tag.getInt("TicksHeated");
        RitualTicks = tag.getInt("RitualTicks");
        powered = tag.getBoolean("powered");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}