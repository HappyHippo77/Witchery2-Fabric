package io.github.happyhippo77.witchery2.block;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.blocks.WitchsCauldron;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final WitchsCauldron WITCHS_CAULDRON = new WitchsCauldron(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque());

    public static void registerAllBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), WITCHS_CAULDRON);
    }
}
