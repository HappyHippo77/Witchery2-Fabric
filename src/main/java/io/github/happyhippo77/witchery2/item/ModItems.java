package io.github.happyhippo77.witchery2.item;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.item.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final AnointingPaste ANOINTING_PASTE = new AnointingPaste(new FabricItemSettings());
    public static final BelladonnaSeeds BELLADONNA_SEEDS = new BelladonnaSeeds(new FabricItemSettings());
    public static final MandrakeSeeds MANDRAKE_SEEDS = new MandrakeSeeds(new FabricItemSettings());
    public static final WaterArtichokeSeeds WATER_ARTICHOKE_SEEDS = new WaterArtichokeSeeds(new FabricItemSettings());
    public static final SnowbellSeeds SNOWBELL_SEEDS = new SnowbellSeeds(new FabricItemSettings());
    public static final WolfsbaneSeeds WOLFSBANE_SEEDS = new WolfsbaneSeeds(new FabricItemSettings());
    public static final Garlic GARLIC = new Garlic(new FabricItemSettings());
    public static void registerAllItems() {
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "anointing_paste"), ANOINTING_PASTE);
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "belladonna_seeds"), BELLADONNA_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_seeds"), MANDRAKE_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "water_artichoke_seeds"), WATER_ARTICHOKE_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "snowbell_seeds"), SNOWBELL_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "wolfsbane_seeds"), WOLFSBANE_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(Witchery2.MOD_ID, "garlic"), GARLIC);
    }
}
