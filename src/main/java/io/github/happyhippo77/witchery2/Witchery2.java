package io.github.happyhippo77.witchery2;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.item.BlockItems;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import io.github.happyhippo77.witchery2.sounds.ModSounds;
import io.github.happyhippo77.witchery2.util.BubbleParticleDataSetter;
import io.github.happyhippo77.witchery2.util.PowerParticleDataSetter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Witchery2 implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "witchery2";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static BubbleParticleDataSetter bubbleParticleDataSetter = new BubbleParticleDataSetter();
	public static PowerParticleDataSetter powerParticleDataSetter = new PowerParticleDataSetter();

	// ItemGroups
	public static final ItemGroup WITCHERY2_GROUP = FabricItemGroupBuilder.create(
					new Identifier(MOD_ID, "main"))
			.icon(() -> new ItemStack(ModBlocks.WITCHS_CAULDRON))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(ModBlocks.WITCHS_CAULDRON));
				stacks.add(new ItemStack(ModItems.ANOINTING_PASTE));
				stacks.add(new ItemStack(ModItems.BELLADONNA_SEEDS));
				stacks.add(new ItemStack(ModItems.MANDRAKE_SEEDS));
				stacks.add(new ItemStack(ModItems.WATER_ARTICHOKE_SEEDS));
				stacks.add(new ItemStack(ModItems.SNOWBELL_SEEDS));
				stacks.add(new ItemStack(ModItems.WOLFSBANE_SEEDS));
				stacks.add(new ItemStack(ModItems.GARLIC));
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModSounds.registerAllSounds();
		ModBlocks.registerAllBlocks();
		ModBlockEntities.registerAllBlockEntities();
		BlockItems.registerAllBlocks();
		ModItems.registerAllItems();
		ModParticles.registerAllParticles();
	}
}
