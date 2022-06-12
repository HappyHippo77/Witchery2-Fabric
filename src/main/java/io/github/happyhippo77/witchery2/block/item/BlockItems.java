package io.github.happyhippo77.witchery2.block.item;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockItems {
    public static void registerAllBlocks() {
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), new BlockItem(ModBlocks.WITCHS_CAULDRON, new FabricItemSettings()));
    }
}
