package btwg.mod.block;

import btw.block.blocks.*;
import btw.item.blockitems.MouldingAndDecorativeBlockItem;
import btw.item.blockitems.SaplingBlockItem;
import btw.item.blockitems.SidingAndCornerBlockItem;
import btwg.api.block.WoodType;
import btwg.api.block.blocks.*;
import btwg.api.block.blocks.WorkStumpBlock;
import btwg.mod.block.blocks.*;
import btwg.api.item.items.blockItems.BTWGSlabBlockItem;
import btwg.mod.world.feature.tree.BTWGTreeGrowers;
import net.minecraft.src.*;

public abstract class BTWGBlocks {
    public static Block planks;
    public static Block woodSlab;
    public static Block stump;
    public static Block workStump;
    public static Block leaves;
    public static Block leaves2;

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

    public static Block darkOakLog;
    public static Block darkOakChewedLog;
    public static Block darkOakLogSpike;
    public static Block darkOakStairs;
    public static Block darkOakDoor;
    public static Block darkOakTrapdoor;
    public static Block darkOakSidingAndCorner;
    public static Block darkOakMoulding;
    public static Block darkOakSapling;

    public static Block mangroveLog;
    public static Block mangroveChewedLog;
    public static Block mangroveLogSpike;
    public static Block mangroveStairs;
    public static Block mangroveDoor;
    public static Block mangroveTrapdoor;
    public static Block mangroveSidingAndCorner;
    public static Block mangroveMoulding;
    public static Block mangroveSapling;

    public static Block cherryLog;
    public static Block cherryChewedLog;
    public static Block cherryLogSpike;
    public static Block cherryStairs;
    public static Block cherryDoor;
    public static Block cherryTrapdoor;
    public static Block cherrySidingAndCorner;
    public static Block cherryMoulding;
    public static Block cherrySapling;

    public static Block paleOakLog;
    public static Block paleOakChewedLog;
    public static Block paleOakLogSpike;
    public static Block paleOakStairs;
    public static Block paleOakDoor;
    public static Block paleOakTrapdoor;
    public static Block paleOakSidingAndCorner;
    public static Block paleOakMoulding;
    public static Block paleOakSapling;

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

        bloodWoodDoor = new BTWGDoorBlock(BTWGBlockIDs.BLOOD_WOOD_DOOR_ID, WoodType.BLOOD_WOOD);
        bloodWoodTrapdoor = new BlockTrapDoor(BTWGBlockIDs.BLOOD_WOOD_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.blood_wood_trapdoor")
                .setTextureName("btwg:blood_wood_trapdoor")
                .setHopperFilter();
        register(bloodWoodTrapdoor);

        //------ General ------//

        planks = new BTWGPlanksBlock(BTWGBlockIDs.PLANKS_ID,
                new WoodType[] {
                        WoodType.ACACIA,
                        WoodType.DARK_OAK,
                        WoodType.MANGROVE,
                        WoodType.CHERRY,
                        WoodType.PALE_OAK
                });
        register(planks, new String[] {
                "acacia",
                "dark_oak",
                "mangrove",
                "cherry",
                "pale_oak"
        });

        woodSlab = new BTWGWoodSlabBlock(BTWGBlockIDs.WOOD_SLAB_ID,
                new WoodType[] {
                        WoodType.ACACIA,
                        WoodType.DARK_OAK,
                        WoodType.MANGROVE,
                        WoodType.CHERRY,
                        WoodType.PALE_OAK
                });
        register(new BTWGSlabBlockItem(BTWGBlockIDs.WOOD_SLAB_ID - 256, new String[] {
                "acacia",
                "dark_oak",
                "mangrove",
                "cherry",
                "pale_oak"
        }));

        stump = new StumpBlock(BTWGBlockIDs.STUMP_ID,
                new WoodType[] {
                        WoodType.ACACIA,
                        WoodType.DARK_OAK,
                        WoodType.MANGROVE,
                        WoodType.CHERRY,
                        WoodType.PALE_OAK
                })
                .setUnlocalizedName("btwg.stump");
        register(stump, new String[] {
                "acacia_stump",
                "dark_oak_stump",
                "mangrove",
                "cherry",
                "pale_oak"
        });

        workStump = new WorkStumpBlock(BTWGBlockIDs.WORK_STUMP_ID,
                new WoodType[] {
                        WoodType.ACACIA,
                        WoodType.DARK_OAK,
                        WoodType.MANGROVE,
                        WoodType.CHERRY,
                        WoodType.PALE_OAK
                })
                .setUnlocalizedName("btwg.work_stump");
        register(workStump, new String[] {
                "acacia",
                "dark_oak",
                "mangrove",
                "cherry",
                "pale_oak"
        });

        leaves = new BTWGLeafBlock(BTWGBlockIDs.LEAVES_ID,
                new WoodType[] {
                        WoodType.ACACIA,
                        WoodType.DARK_OAK,
                        WoodType.MANGROVE,
                        WoodType.CHERRY
                })
                .setUnlocalizedName("btwg.leaves");
        register(leaves, new String[] {
                "acacia",
                "dark_oak",
                "mangrove",
                "cherry"
        });

        leaves2 = new BTWGLeafBlock(BTWGBlockIDs.LEAVES_2_ID,
                new WoodType[] {
                        WoodType.PALE_OAK
                })
                .setUnlocalizedName("btwg.leaves");
        register(leaves2, new String[] {
                "pale_oak"
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

        //------ Dark Oak ------//

        darkOakLog = new BTWGLogBlock(BTWGBlockIDs.DARK_OAK_LOG_ID, WoodType.DARK_OAK);
        register(darkOakLog);

        darkOakLogSpike = new LogSpikeBlock(BTWGBlockIDs.DARK_OAK_LOG_SPIKE_ID,
                "btwg:chewed_" + WoodType.DARK_OAK.name() + "_log_top",
                "btwg:chewed_" + WoodType.DARK_OAK.name() + "_log_side");
        register(darkOakLogSpike);

        darkOakChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_DARK_OAK_LOG_ID,
                "btwg:chewed_" + WoodType.DARK_OAK.name() + "_log_top",
                "btwg:chewed_" + WoodType.DARK_OAK.name() + "_log_side",
                "btwg:" + WoodType.DARK_OAK.name() + "_stump_top",
                darkOakLogSpike);
        register(darkOakChewedLog);

        darkOakStairs = new WoodStairsBlock(BTWGBlockIDs.DARK_OAK_STAIRS_ID,
                Block.blocksList[WoodType.DARK_OAK.plankID()],
                WoodType.DARK_OAK.plankMetadata())
                .setUnlocalizedName("btwg.dark_oak_stairs");
        register(darkOakStairs);

        darkOakDoor = new BTWGDoorBlock(BTWGBlockIDs.DARK_OAK_DOOR_ID, WoodType.DARK_OAK);

        darkOakTrapdoor = new BlockTrapDoor(BTWGBlockIDs.DARK_OAK_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.dark_oak_trapdoor")
                .setTextureName("btwg:dark_oak_trapdoor")
                .setHopperFilter();
        register(darkOakTrapdoor);

        darkOakSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.DARK_OAK_SIDING_ID,
                "btwg:dark_oak_planks", "btwg.dark_oak_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.DARK_OAK_SIDING_ID - 256));

        darkOakMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.DARK_OAK_MOULDING_ID,
                "btwg:dark_oak_planks", "btwg:dark_oak_column",
                BTWGBlockIDs.DARK_OAK_SIDING_ID,
                "btwg.dark_oak_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.DARK_OAK_MOULDING_ID - 256));

        darkOakSapling = new SaplingBlock(BTWGBlockIDs.DARK_OAK_SAPLING_ID, "btwg.dark_oak_sapling", "btwg:dark_oak_sapling")
                .addTreeGrower(BTWGTreeGrowers.DARK_OAK_TREE, 1)
                .add2x2TreeGrower(BTWGTreeGrowers.HUGE_DARK_OAK_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.DARK_OAK_SAPLING_ID - 256, darkOakSapling));

        //------ Mangrove ------//

        mangroveLog = new BTWGLogBlock(BTWGBlockIDs.MANGROVE_LOG_ID, WoodType.MANGROVE);
        register(mangroveLog);

        mangroveLogSpike = new LogSpikeBlock(BTWGBlockIDs.MANGROVE_LOG_SPIKE_ID,
                "btwg:chewed_" + WoodType.MANGROVE.name() + "_log_top",
                "btwg:chewed_" + WoodType.MANGROVE.name() + "_log_side");
        register(mangroveLogSpike);

        mangroveChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_MANGROVE_LOG_ID,
                "btwg:chewed_" + WoodType.MANGROVE.name() + "_log_top",
                "btwg:chewed_" + WoodType.MANGROVE.name() + "_log_side",
                "btwg:" + WoodType.MANGROVE.name() + "_stump_top",
                mangroveLogSpike);
        register(mangroveChewedLog);

        mangroveStairs = new WoodStairsBlock(BTWGBlockIDs.MANGROVE_STAIRS_ID,
                Block.blocksList[WoodType.MANGROVE.plankID()],
                WoodType.MANGROVE.plankMetadata())
                .setUnlocalizedName("btwg.mangrove_stairs");
        register(mangroveStairs);

        mangroveDoor = new BTWGDoorBlock(BTWGBlockIDs.MANGROVE_DOOR_ID, WoodType.MANGROVE);

        mangroveTrapdoor = new BlockTrapDoor(BTWGBlockIDs.MANGROVE_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.mangrove_trapdoor")
                .setTextureName("btwg:mangrove_trapdoor")
                .setHopperFilter();
        register(mangroveTrapdoor);

        mangroveSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.MANGROVE_SIDING_ID,
                "btwg:mangrove_planks", "btwg.mangrove_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.MANGROVE_SIDING_ID - 256));

        mangroveMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.MANGROVE_MOULDING_ID,
                "btwg:mangrove_planks", "btwg:mangrove_column",
                BTWGBlockIDs.MANGROVE_SIDING_ID,
                "btwg.mangrove_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.MANGROVE_MOULDING_ID - 256));

        mangroveSapling = new SaplingBlock(BTWGBlockIDs.MANGROVE_SAPLING_ID, "btwg.mangrove_sapling", "btwg:mangrove_sapling")
                .addTreeGrower(BTWGTreeGrowers.SAVANNA_TREE, 2)
                .addTreeGrower(BTWGTreeGrowers.SPLIT_SAVANNA_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.MANGROVE_SAPLING_ID - 256, mangroveSapling));

        //------ Cherry ------//

        cherryLog = new BTWGLogBlock(BTWGBlockIDs.CHERRY_LOG_ID, WoodType.CHERRY);
        register(cherryLog);

        cherryLogSpike = new LogSpikeBlock(BTWGBlockIDs.CHERRY_LOG_SPIKE_ID,
                "btwg:chewed_" + WoodType.CHERRY.name() + "_log_top",
                "btwg:chewed_" + WoodType.CHERRY.name() + "_log_side");
        register(cherryLogSpike);

        cherryChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_CHERRY_LOG_ID,
                "btwg:chewed_" + WoodType.CHERRY.name() + "_log_top",
                "btwg:chewed_" + WoodType.CHERRY.name() + "_log_side",
                "btwg:" + WoodType.CHERRY.name() + "_stump_top",
                cherryLogSpike);
        register(cherryChewedLog);

        cherryStairs = new WoodStairsBlock(BTWGBlockIDs.CHERRY_STAIRS_ID,
                Block.blocksList[WoodType.CHERRY.plankID()],
                WoodType.CHERRY.plankMetadata())
                .setUnlocalizedName("btwg.cherry_stairs");
        register(cherryStairs);

        cherryDoor = new BTWGDoorBlock(BTWGBlockIDs.CHERRY_DOOR_ID, WoodType.CHERRY);

        cherryTrapdoor = new BlockTrapDoor(BTWGBlockIDs.CHERRY_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.cherry_trapdoor")
                .setTextureName("btwg:cherry_trapdoor")
                .setHopperFilter();
        register(cherryTrapdoor);

        cherrySidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.CHERRY_SIDING_ID,
                "btwg:cherry_planks", "btwg.cherry_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.CHERRY_SIDING_ID - 256));

        cherryMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.CHERRY_MOULDING_ID,
                "btwg:cherry_planks", "btwg:cherry_column",
                BTWGBlockIDs.CHERRY_SIDING_ID,
                "btwg.cherry_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.CHERRY_MOULDING_ID - 256));

        cherrySapling = new SaplingBlock(BTWGBlockIDs.CHERRY_SAPLING_ID, "btwg.cherry_sapling", "btwg:cherry_sapling")
                .addTreeGrower(BTWGTreeGrowers.SAVANNA_TREE, 2)
                .addTreeGrower(BTWGTreeGrowers.SPLIT_SAVANNA_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.CHERRY_SAPLING_ID - 256, cherrySapling));

        //------ Pale Oak ------//

        paleOakLog = new BTWGLogBlock(BTWGBlockIDs.PALE_OAK_LOG_ID, WoodType.PALE_OAK);
        register(paleOakLog);

        paleOakLogSpike = new LogSpikeBlock(BTWGBlockIDs.PALE_OAK_LOG_SPIKE_ID,
                "btwg:chewed_" + WoodType.PALE_OAK.name() + "_log_top",
                "btwg:chewed_" + WoodType.PALE_OAK.name() + "_log_side");
        register(paleOakLogSpike);

        paleOakChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_PALE_OAK_LOG_ID,
                "btwg:chewed_" + WoodType.PALE_OAK.name() + "_log_top",
                "btwg:chewed_" + WoodType.PALE_OAK.name() + "_log_side",
                "btwg:" + WoodType.PALE_OAK.name() + "_stump_top",
                paleOakLogSpike);
        register(paleOakChewedLog);

        paleOakStairs = new WoodStairsBlock(BTWGBlockIDs.PALE_OAK_STAIRS_ID,
                Block.blocksList[WoodType.PALE_OAK.plankID()],
                WoodType.PALE_OAK.plankMetadata())
                .setUnlocalizedName("btwg.pale_oak_stairs");
        register(paleOakStairs);

        paleOakDoor = new BTWGDoorBlock(BTWGBlockIDs.PALE_OAK_DOOR_ID, WoodType.PALE_OAK);

        paleOakTrapdoor = new BlockTrapDoor(BTWGBlockIDs.PALE_OAK_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.pale_oak_trapdoor")
                .setTextureName("btwg:pale_oak_trapdoor")
                .setHopperFilter();
        register(paleOakTrapdoor);

        paleOakSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.PALE_OAK_SIDING_ID,
                "btwg:pale_oak_planks", "btwg.pale_oak_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.PALE_OAK_SIDING_ID - 256));

        paleOakMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.PALE_OAK_MOULDING_ID,
                "btwg:pale_oak_planks", "btwg:pale_oak_column",
                BTWGBlockIDs.PALE_OAK_SIDING_ID,
                "btwg.pale_oak_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.PALE_OAK_MOULDING_ID - 256));

        paleOakSapling = new SaplingBlock(BTWGBlockIDs.PALE_OAK_SAPLING_ID, "btwg.pale_oak_sapling", "btwg:pale_oak_sapling")
                .addTreeGrower(BTWGTreeGrowers.PALE_OAK_TREE, 1)
                .add2x2TreeGrower(BTWGTreeGrowers.HUGE_PALE_OAK_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.PALE_OAK_SAPLING_ID - 256, paleOakSapling));
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
