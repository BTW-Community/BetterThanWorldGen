package btwg.mod.block;

import btwg.mod.block.blocks.*;
import net.minecraft.src.*;

public abstract class BTWGBlocks {
    public static Block earthenClay;
    public static Block grassyEarthenClay;
    public static Block looseEarthenClay;

    public static Block sandyDirt;
    public static Block looseSandyDirt;

    public static Block tallDryGrass;
    public static Block shortDryGrass;
    public static Block bush;

    public static void initBlocks() {
        initSoils();
        initPlants();
    }

    private static void initSoils() {
        earthenClay = new EarthenClayBlock(BTWGBlockIDs.EARTHEN_CLAY_ID, false);
        register(earthenClay);
        grassyEarthenClay = new EarthenClayBlock(BTWGBlockIDs.GRASSY_EARTHEN_CLAY_ID, true);
        register(grassyEarthenClay);
        looseEarthenClay = new LooseEarthenClayBlock(BTWGBlockIDs.LOOSE_EARTHEN_CLAY_ID);
        register(looseEarthenClay);

        sandyDirt = new SandyDirtBlock(BTWGBlockIDs.SANDY_DIRT_ID);
        register(sandyDirt);
        looseSandyDirt = new LooseSandyDirtBlock(BTWGBlockIDs.LOOSE_SANDY_DIRT_ID);
        register(looseSandyDirt);
    }

    private static void initPlants() {

        tallDryGrass = new ShrubBlock(BTWGBlockIDs.TALL_DRY_GRASS_ID)
                .setCanStayOnSand()
                .setReplaceable()
                .setTextureName("btwg:tall_dry_grass")
                .setUnlocalizedName("btwg.tall_dry_grass");
        register(tallDryGrass);

        shortDryGrass = new ShrubBlock(BTWGBlockIDs.SHORT_DRY_GRASS_ID)
                .setCanStayOnSand()
                .setReplaceable()
                .setTextureName("btwg:short_dry_grass")
                .setUnlocalizedName("btwg.short_dry_grass");
        shortDryGrass.initBlockBounds(0.2, 0.0, 0.2, 0.8, 0.6, 0.8);
        register(shortDryGrass);

        bush = new ColorizedShrubBlock(BTWGBlockIDs.BUSH_ID)
                .setReplaceable()
                .setTextureName("btwg:bush")
                .setUnlocalizedName("btwg.bush");
        register(new ItemColored(bush.blockID - 256, true));
    }

    private static void register(Block block, String[]names) {
        Item.itemsList[block.blockID] = new ItemMultiTextureTile(block.blockID - 256, block, names);
    }

    private static void register(ItemBlock blockItem) {
        Item.itemsList[blockItem.itemID] = blockItem;
    }

    private static void register(Block block) {
        ItemBlock blockItem = new ItemBlock(block.blockID - 256);
        Item.itemsList[blockItem.itemID] = blockItem;
    }
}
