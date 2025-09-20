package btwg.mod.block;

import btw.block.blocks.ChewedLogBlock;
import btw.block.blocks.LogSpikeBlock;
import btwg.mod.block.blocks.*;
import net.minecraft.src.*;

public abstract class BTWGBlocks {
    public static Block planks;
    public static Block stump;
    public static Block workStump;
    public static Block acaciaLog;
    public static Block acaciaChewedLog;
    public static Block acaciaLogSpike;

    public static Block earthenClay;
    public static Block grassyEarthenClay;
    public static Block looseEarthenClay;

    public static Block sandyDirt;
    public static Block looseSandyDirt;

    public static Block dryGrass;
    public static Block shortDryGrass;
    public static Block tallDryGrass;
    public static Block bush;
    public static Block tallGrass;
    public static Block tallFern;

    public static void initBlocks() {
        initSoils();
        initPlants();
        initWood();

        Block.waterMoving.setLightOpacity(2);
        Block.waterStill.setLightOpacity(2);
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

        dryGrass = new ShrubBlock(BTWGBlockIDs.DRY_GRASS_ID)
                .setCanStayOnSand()
                .setReplaceable()
                .setTextureName("btwg:dry_grass")
                .setUnlocalizedName("btwg.dry_grass");
        register(dryGrass);

        shortDryGrass = new ShrubBlock(BTWGBlockIDs.SHORT_DRY_GRASS_ID)
                .setCanStayOnSand()
                .setReplaceable()
                .setTextureName("btwg:short_dry_grass")
                .setUnlocalizedName("btwg.short_dry_grass");
        shortDryGrass.initBlockBounds(0.2, 0.0, 0.2, 0.8, 0.6, 0.8);
        register(shortDryGrass);

        tallDryGrass = new TallPlantBlock(BTWGBlockIDs.TALL_DRY_GRASS_ID, "btwg:tall_dry_grass", new String[] { "btwg.tall_dry_grass" })
                .setCanStayOnSand()
                .setReplaceable()
                .setNeedsShears();
        register(tallDryGrass);

        bush = new ColorizedShrubBlock(BTWGBlockIDs.BUSH_ID)
                .setReplaceable()
                .setTextureName("btwg:bush")
                .setUnlocalizedName("btwg.bush");
        register(new ItemColored(bush.blockID - 256, true));

        tallGrass = new ColorizedTallPlantBlock(BTWGBlockIDs.TALL_GRASS_ID, "btwg:tall_grass", new String[] { "btwg.tall_grass" })
                .setReplaceable()
                .setNeedsShears();
        register(tallGrass);

        tallFern = new ColorizedTallPlantBlock(BTWGBlockIDs.LARGE_FERN_ID, "btwg:large_fern", new String[] { "btwg.large_fern" })
                .setReplaceable()
                .setNeedsShears();
        register(tallFern);
    }

    private static void initWood() {
        planks = new BTWGPlanksBlock(BTWGBlockIDs.PLANKS_ID, new WoodType[] {
                WoodType.ACACIA
        });
        register(planks, new String[] {
                "acacia"
        });

        stump = new StumpBlock(BTWGBlockIDs.STUMP_ID, new WoodType[] {
                WoodType.ACACIA
        });
        register(stump, new String[] {
                "acacia_stump"
        });

        workStump = new WorkStumpBlock(BTWGBlockIDs.WORK_STUMP_ID, new WoodType[] {
                WoodType.ACACIA
        });
        register(workStump, new String[] {
                "acacia_work_stump"
        });

        acaciaLog = new BTWGLogBlock(BTWGBlockIDs.ACACIA_LOG_ID, WoodType.ACACIA);
        register(acaciaLog);

        acaciaLogSpike = new LogSpikeBlock(BTWGBlockIDs.ACACIA_LOG_SPIKE_ID,
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_top",
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_side");

        acaciaChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_ACACIA_LOG_ID,
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_top",
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_side",
                "btwg:" + WoodType.ACACIA.name() + "_stump_top",
                acaciaLogSpike);
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
