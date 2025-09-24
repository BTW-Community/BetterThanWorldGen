package btwg.mod.block;

import btw.block.blocks.*;
import btw.item.blockitems.MouldingAndDecorativeBlockItem;
import btw.item.blockitems.SaplingBlockItem;
import btw.item.blockitems.SidingAndCornerBlockItem;
import btwg.mod.block.blocks.*;
import btwg.mod.block.blocks.WorkStumpBlock;
import btwg.mod.item.items.blockItems.BTWGSlabBlockItem;
import btwg.mod.world.feature.tree.BTWGTreeGrowers;
import net.minecraft.src.*;

public abstract class BTWGBlocks {
    public static Block planks;
    public static Block woodSlab;
    public static Block stump;
    public static Block workStump;
    public static Block leaves;

    public static Block spruceDoor;
    public static Block spruceTrapdoor;
    public static Block birchDoor;
    public static Block birchTrapdoor;
    public static Block jungleDoor;
    public static Block jungleTrapdoor;
    public static Block bloodWoodDoor;
    public static Block bloodWoodTrapdoor;

    public static Block acaciaLog;
    public static Block acaciaChewedLog;
    public static Block acaciaLogSpike;
    public static Block acaciaStairs;
    public static Block acaciaDoor;
    public static Block acaciaTrapdoor;
    public static Block acaciaSidingAndCorner;
    public static Block acaciaMoulding;
    public static Block acaciaSapling;

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

        //------ Additional Vanilla Wood Blocks ------//

        spruceDoor = new BTWGDoorBlock(BTWGBlockIDs.SPRUCE_DOOR_ID, WoodType.SPRUCE);
        spruceTrapdoor = new BlockTrapDoor(BTWGBlockIDs.SPRUCE_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.spruce_trapdoor")
                .setTextureName("btwg:spruce_trapdoor")
                .setHopperFilter();
        register(spruceTrapdoor);

        birchDoor = new BTWGDoorBlock(BTWGBlockIDs.BIRCH_DOOR_ID, WoodType.BIRCH);
        birchTrapdoor = new BlockTrapDoor(BTWGBlockIDs.BIRCH_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.birch_trapdoor")
                .setTextureName("btwg:birch_trapdoor")
                .setHopperFilter();
        register(birchTrapdoor);

        jungleDoor = new BTWGDoorBlock(BTWGBlockIDs.JUNGLE_DOOR_ID, WoodType.JUNGLE);
        jungleTrapdoor = new BlockTrapDoor(BTWGBlockIDs.JUNGLE_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.jungle_trapdoor")
                .setTextureName("btwg:jungle_trapdoor")
                .setHopperFilter();
        register(jungleTrapdoor);

        //------ General ------//

        planks = new BTWGPlanksBlock(BTWGBlockIDs.PLANKS_ID,
                new WoodType[] {
                        WoodType.ACACIA
                });
        register(planks, new String[] {
                "acacia"
        });

        woodSlab = new BTWGWoodSlabBlock(BTWGBlockIDs.WOOD_SLAB_ID,
                new WoodType[] {
                        WoodType.ACACIA
                });
        register(new BTWGSlabBlockItem(BTWGBlockIDs.WOOD_SLAB_ID - 256, new String[] {
                "acacia"
        }));

        stump = new StumpBlock(BTWGBlockIDs.STUMP_ID,
                new WoodType[] {
                        WoodType.ACACIA
                })
                .setUnlocalizedName("btwg.stump");
        register(stump, new String[] {
                "acacia_stump"
        });

        workStump = new WorkStumpBlock(BTWGBlockIDs.WORK_STUMP_ID,
                new WoodType[] {
                        WoodType.ACACIA
                })
                .setUnlocalizedName("btwg.work_stump");
        register(workStump, new String[] {
                "acacia_work_stump"
        });

        leaves = new BTWGLeafBlock(BTWGBlockIDs.LEAVES_ID,
                new WoodType[] {
                        WoodType.ACACIA
                })
                .setUnlocalizedName("btwg.leaves");
        register(leaves, new String[] {
                "acacia"
        });

        //------ Acacia ------//

        acaciaLog = new BTWGLogBlock(BTWGBlockIDs.ACACIA_LOG_ID, WoodType.ACACIA);
        register(acaciaLog);

        acaciaLogSpike = new LogSpikeBlock(BTWGBlockIDs.ACACIA_LOG_SPIKE_ID,
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_top",
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_side");
        register(acaciaLogSpike);

        acaciaChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_ACACIA_LOG_ID,
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_top",
                "btwg:chewed_" + WoodType.ACACIA.name() + "_log_side",
                "btwg:" + WoodType.ACACIA.name() + "_stump_top",
                acaciaLogSpike);
        register(acaciaChewedLog);

        acaciaStairs = new WoodStairsBlock(BTWGBlockIDs.ACACIA_STAIRS_ID,
                Block.blocksList[WoodType.ACACIA.plankID()],
                WoodType.ACACIA.plankMetadata())
                .setUnlocalizedName("btwg.acacia_stairs");
        register(acaciaStairs);

        acaciaDoor = new BTWGDoorBlock(BTWGBlockIDs.ACACIA_DOOR_ID, WoodType.ACACIA);

        acaciaTrapdoor = new BlockTrapDoor(BTWGBlockIDs.ACACIA_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.acacia_trapdoor")
                .setTextureName("btwg:acacia_trapdoor")
                .setHopperFilter();
        register(acaciaTrapdoor);

        acaciaSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.ACACIA_SIDING_ID,
                "btwg:acacia_planks", "btwg.acacia_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.ACACIA_SIDING_ID - 256));

        acaciaMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.ACACIA_MOULDING_ID,
                "btwg:acacia_planks", "btwg:acacia_column",
                BTWGBlockIDs.ACACIA_SIDING_ID,
                "btwg.acacia_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.ACACIA_MOULDING_ID - 256));

        acaciaSapling = new SaplingBlock(BTWGBlockIDs.ACACIA_SAPLING_ID, "btwg.acacia_sapling", "btwg:acacia_sapling")
                .addTreeGrower(BTWGTreeGrowers.SAVANNA_TREE, 2)
                .addTreeGrower(BTWGTreeGrowers.SPLIT_SAVANNA_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.ACACIA_SAPLING_ID - 256, acaciaSapling));
    }

    private static void register(Block block, String[] names) {
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
