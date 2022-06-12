package io.github.happyhippo77.witchery2.block.entity;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<WitchsCauldronEntity> WITCHS_CAULDRON_ENTITY;

    public static void registerAllBlockEntities() {
        WITCHS_CAULDRON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), FabricBlockEntityTypeBuilder.create(WitchsCauldronEntity::new, ModBlocks.WITCHS_CAULDRON).build(null));

    }
}
