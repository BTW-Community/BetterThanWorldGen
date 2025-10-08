package btwg.mod.block;

import btw.block.BTWBlocks;
import btw.block.blocks.*;
import btw.item.BTWItems;
import btw.item.blockitems.*;
import btw.util.color.Color;
import btwg.api.block.StoneType;
import btwg.api.block.WoodType;
import btwg.api.block.blocks.*;
import btwg.api.block.blocks.WorkStumpBlock;
import btwg.api.item.ItemInterface;
import btwg.api.item.items.blockItems.BTWGWoodSlabBlockItem;
import btwg.mod.BetterThanWorldGen;
import btwg.mod.block.blocks.*;
import btwg.mod.world.feature.tree.BTWGTreeGrowers;
import net.minecraft.src.*;

import java.util.Arrays;

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
    public static Block tallDryGrass;
    public static Block grass;
    public static Block grassWithOverlay;
    public static Block tallGrass;
    public static Block tallGrassWithOverlay;
    public static Block glowWorms;

    public static Block limestone;
    public static Block roughLimestone;
    public static Block limestoneStairs;
    public static Block limestoneDecorative;
    public static Block limestoneSlab;
    public static Block cobbledLimestoneStairs;
    public static Block limestoneBrickStairs;
    public static Block looseLimestone;
    public static Block looseLimestoneSlab;
    public static Block looseCobbledLimestoneStairs;
    public static Block looseLimestoneBrickStairs;
    public static Block limestoneSidingAndCorner;
    public static Block limestoneMoulding;
    public static Block cobbledLimestoneSidingAndCorner;
    public static Block cobbledLimestoneMoulding;
    public static Block limestoneBrickSidingAndCorner;
    public static Block limestoneBrickMoulding;
    public static Block limestoneRegolith;
    public static Block limestoneRegolithSlab;
    public static Block looseLimestoneRegolith;
    public static Block looseLimestoneRegolithSlab;
    public static Block grassyLimestoneRegolith;
    public static Block grassyLimestoneRegolithSlab;
    public static Block looseSparseGrassyLimestoneRegolith;
    public static Block looseSparseGrassyLimestoneRegolithSlab;
    public static Block limestoneGravel;
    public static Block limestoneGravelSlab;
    public static Block limestoneRegolithFarmland;

    public static Block andesite;
    public static Block roughAndesite;
    public static Block andesiteStairs;
    public static Block andesiteDecorative;
    public static Block andesiteSlab;
    public static Block cobbledAndesiteStairs;
    public static Block andesiteBrickStairs;
    public static Block looseAndesite;
    public static Block looseAndesiteSlab;
    public static Block looseCobbledAndesiteStairs;
    public static Block looseAndesiteBrickStairs;
    public static Block andesiteSidingAndCorner;
    public static Block andesiteMoulding;
    public static Block cobbledAndesiteSidingAndCorner;
    public static Block cobbledAndesiteMoulding;
    public static Block andesiteBrickSidingAndCorner;
    public static Block andesiteBrickMoulding;
    public static Block andesiteRegolith;
    public static Block andesiteRegolithSlab;
    public static Block looseAndesiteRegolith;
    public static Block looseAndesiteRegolithSlab;
    public static Block grassyAndesiteRegolith;
    public static Block grassyAndesiteRegolithSlab;
    public static Block looseSparseGrassyAndesiteRegolith;
    public static Block looseSparseGrassyAndesiteRegolithSlab;
    public static Block andesiteGravel;
    public static Block andesiteGravelSlab;
    public static Block andesiteRegolithFarmland;

    public static Block basalt;
    public static Block roughBasalt;
    public static Block basaltStairs;
    public static Block basaltDecorative;
    public static Block basaltSlab;
    public static Block cobbledBasaltStairs;
    public static Block basaltBrickStairs;
    public static Block looseBasalt;
    public static Block looseBasaltSlab;
    public static Block looseCobbledBasaltStairs;
    public static Block looseBasaltBrickStairs;
    public static Block basaltSidingAndCorner;
    public static Block basaltMoulding;
    public static Block cobbledBasaltSidingAndCorner;
    public static Block cobbledBasaltMoulding;
    public static Block basaltBrickSidingAndCorner;
    public static Block basaltBrickMoulding;
    public static Block basaltRegolith;
    public static Block basaltRegolithSlab;
    public static Block looseBasaltRegolith;
    public static Block looseBasaltRegolithSlab;
    public static Block grassyBasaltRegolith;
    public static Block grassyBasaltRegolithSlab;
    public static Block looseSparseGrassyBasaltRegolith;
    public static Block looseSparseGrassyBasaltRegolithSlab;
    public static Block basaltGravel;
    public static Block basaltGravelSlab;
    public static Block basaltRegolithFarmland;

    public static Block calcite;
    public static Block roughCalcite;
    public static Block calciteStairs;
    public static Block calciteDecorative;
    public static Block calciteSlab;
    public static Block cobbledCalciteStairs;
    public static Block calciteBrickStairs;
    public static Block looseCalcite;
    public static Block looseCalciteSlab;
    public static Block looseCobbledCalciteStairs;
    public static Block looseCalciteBrickStairs;
    public static Block calciteSidingAndCorner;
    public static Block calciteMoulding;
    public static Block cobbledCalciteSidingAndCorner;
    public static Block cobbledCalciteMoulding;
    public static Block calciteBrickSidingAndCorner;
    public static Block calciteBrickMoulding;
    public static Block calciteRegolith;
    public static Block calciteRegolithSlab;
    public static Block looseCalciteRegolith;
    public static Block looseCalciteRegolithSlab;
    public static Block grassyCalciteRegolith;
    public static Block grassyCalciteRegolithSlab;
    public static Block looseSparseGrassyCalciteRegolith;
    public static Block looseSparseGrassyCalciteRegolithSlab;
    public static Block calciteGravel;
    public static Block calciteGravelSlab;
    public static Block calciteRegolithFarmland;

    public static Block diorite;
    public static Block roughDiorite;
    public static Block dioriteStairs;
    public static Block dioriteDecorative;
    public static Block dioriteSlab;
    public static Block cobbledDioriteStairs;
    public static Block dioriteBrickStairs;
    public static Block looseDiorite;
    public static Block looseDioriteSlab;
    public static Block looseCobbledDioriteStairs;
    public static Block looseDioriteBrickStairs;
    public static Block dioriteSidingAndCorner;
    public static Block dioriteMoulding;
    public static Block cobbledDioriteSidingAndCorner;
    public static Block cobbledDioriteMoulding;
    public static Block dioriteBrickSidingAndCorner;
    public static Block dioriteBrickMoulding;
    public static Block dioriteRegolith;
    public static Block dioriteRegolithSlab;
    public static Block looseDioriteRegolith;
    public static Block looseDioriteRegolithSlab;
    public static Block grassyDioriteRegolith;
    public static Block grassyDioriteRegolithSlab;
    public static Block looseSparseGrassyDioriteRegolith;
    public static Block looseSparseGrassyDioriteRegolithSlab;
    public static Block dioriteGravel;
    public static Block dioriteGravelSlab;
    public static Block dioriteRegolithFarmland;

    public static Block granite;
    public static Block roughGranite;
    public static Block graniteStairs;
    public static Block graniteDecorative;
    public static Block graniteSlab;
    public static Block cobbledGraniteStairs;
    public static Block graniteBrickStairs;
    public static Block looseGranite;
    public static Block looseGraniteSlab;
    public static Block looseCobbledGraniteStairs;
    public static Block looseGraniteBrickStairs;
    public static Block graniteSidingAndCorner;
    public static Block graniteMoulding;
    public static Block cobbledGraniteSidingAndCorner;
    public static Block cobbledGraniteMoulding;
    public static Block graniteBrickSidingAndCorner;
    public static Block graniteBrickMoulding;
    public static Block graniteRegolith;
    public static Block graniteRegolithSlab;
    public static Block looseGraniteRegolith;
    public static Block looseGraniteRegolithSlab;
    public static Block grassyGraniteRegolith;
    public static Block grassyGraniteRegolithSlab;
    public static Block looseSparseGrassyGraniteRegolith;
    public static Block looseSparseGrassyGraniteRegolithSlab;
    public static Block graniteGravel;
    public static Block graniteGravelSlab;
    public static Block graniteRegolithFarmland;

    public static Block kimberlite;
    public static Block roughKimberlite;
    public static Block kimberliteStairs;
    public static Block kimberliteDecorative;
    public static Block kimberliteSlab;
    public static Block cobbledKimberliteStairs;
    public static Block kimberliteBrickStairs;
    public static Block looseKimberlite;
    public static Block looseKimberliteSlab;
    public static Block looseCobbledKimberliteStairs;
    public static Block looseKimberliteBrickStairs;
    public static Block kimberliteSidingAndCorner;
    public static Block kimberliteMoulding;
    public static Block cobbledKimberliteSidingAndCorner;
    public static Block cobbledKimberliteMoulding;
    public static Block kimberliteBrickSidingAndCorner;
    public static Block kimberliteBrickMoulding;
    public static Block kimberliteRegolith;
    public static Block kimberliteRegolithSlab;
    public static Block looseKimberliteRegolith;
    public static Block looseKimberliteRegolithSlab;
    public static Block grassyKimberliteRegolith;
    public static Block grassyKimberliteRegolithSlab;
    public static Block looseSparseGrassyKimberliteRegolith;
    public static Block looseSparseGrassyKimberliteRegolithSlab;
    public static Block kimberliteGravel;
    public static Block kimberliteGravelSlab;
    public static Block kimberliteRegolithFarmland;

    public static Block phyllite;
    public static Block roughPhyllite;
    public static Block phylliteStairs;
    public static Block phylliteDecorative;
    public static Block phylliteSlab;
    public static Block cobbledPhylliteStairs;
    public static Block phylliteBrickStairs;
    public static Block loosePhyllite;
    public static Block loosePhylliteSlab;
    public static Block looseCobbledPhylliteStairs;
    public static Block loosePhylliteBrickStairs;
    public static Block phylliteSidingAndCorner;
    public static Block phylliteMoulding;
    public static Block cobbledPhylliteSidingAndCorner;
    public static Block cobbledPhylliteMoulding;
    public static Block phylliteBrickSidingAndCorner;
    public static Block phylliteBrickMoulding;
    public static Block phylliteRegolith;
    public static Block phylliteRegolithSlab;
    public static Block loosePhylliteRegolith;
    public static Block loosePhylliteRegolithSlab;
    public static Block grassyPhylliteRegolith;
    public static Block grassyPhylliteRegolithSlab;
    public static Block looseSparseGrassyPhylliteRegolith;
    public static Block looseSparseGrassyPhylliteRegolithSlab;
    public static Block phylliteGravel;
    public static Block phylliteGravelSlab;
    public static Block phylliteRegolithFarmland;

    public static Block schist;
    public static Block roughSchist;
    public static Block schistStairs;
    public static Block schistDecorative;
    public static Block schistSlab;
    public static Block cobbledSchistStairs;
    public static Block schistBrickStairs;
    public static Block looseSchist;
    public static Block looseSchistSlab;
    public static Block looseCobbledSchistStairs;
    public static Block looseSchistBrickStairs;
    public static Block schistSidingAndCorner;
    public static Block schistMoulding;
    public static Block cobbledSchistSidingAndCorner;
    public static Block cobbledSchistMoulding;
    public static Block schistBrickSidingAndCorner;
    public static Block schistBrickMoulding;
    public static Block schistRegolith;
    public static Block schistRegolithSlab;
    public static Block looseSchistRegolith;
    public static Block looseSchistRegolithSlab;
    public static Block grassySchistRegolith;
    public static Block grassySchistRegolithSlab;
    public static Block looseSparseGrassySchistRegolith;
    public static Block looseSparseGrassySchistRegolithSlab;
    public static Block schistGravel;
    public static Block schistGravelSlab;
    public static Block schistRegolithFarmland;

    public static Block shale;
    public static Block roughShale;
    public static Block shaleStairs;
    public static Block shaleDecorative;
    public static Block shaleSlab;
    public static Block cobbledShaleStairs;
    public static Block shaleBrickStairs;
    public static Block looseShale;
    public static Block looseShaleSlab;
    public static Block looseCobbledShaleStairs;
    public static Block looseShaleBrickStairs;
    public static Block shaleSidingAndCorner;
    public static Block shaleMoulding;
    public static Block cobbledShaleSidingAndCorner;
    public static Block cobbledShaleMoulding;
    public static Block shaleBrickSidingAndCorner;
    public static Block shaleBrickMoulding;
    public static Block shaleRegolith;
    public static Block shaleRegolithSlab;
    public static Block looseShaleRegolith;
    public static Block looseShaleRegolithSlab;
    public static Block grassyShaleRegolith;
    public static Block grassyShaleRegolithSlab;
    public static Block looseSparseGrassyShaleRegolith;
    public static Block looseSparseGrassyShaleRegolithSlab;
    public static Block shaleGravel;
    public static Block shaleGravelSlab;
    public static Block shaleRegolithFarmland;

    public static Block tuff;
    public static Block roughTuff;
    public static Block tuffStairs;
    public static Block tuffDecorative;
    public static Block tuffSlab;
    public static Block cobbledTuffStairs;
    public static Block tuffBrickStairs;
    public static Block looseTuff;
    public static Block looseTuffSlab;
    public static Block looseCobbledTuffStairs;
    public static Block looseTuffBrickStairs;
    public static Block tuffSidingAndCorner;
    public static Block tuffMoulding;
    public static Block cobbledTuffSidingAndCorner;
    public static Block cobbledTuffMoulding;
    public static Block tuffBrickSidingAndCorner;
    public static Block tuffBrickMoulding;
    public static Block tuffRegolith;
    public static Block tuffRegolithSlab;
    public static Block looseTuffRegolith;
    public static Block looseTuffRegolithSlab;
    public static Block grassyTuffRegolith;
    public static Block grassyTuffRegolithSlab;
    public static Block looseSparseGrassyTuffRegolith;
    public static Block looseSparseGrassyTuffRegolithSlab;
    public static Block tuffGravel;
    public static Block tuffGravelSlab;
    public static Block tuffRegolithFarmland;

    public static Block coalOre;
    public static Block ironOre;
    public static Block goldOre;
    public static Block diamondOre;
    public static Block emeraldOre;
    public static Block redstoneOre;
    public static Block lapisOre;


    public static void initBlocks() {
        initSoils();
        initPlants();
        initWood();
        initStone();
        initOre();

        Block.waterMoving.setLightOpacity(2);
        Block.waterStill.setLightOpacity(2);

        RoughStoneBlock.strataLevelBlockArray[0] = (RoughStoneBlock) BTWBlocks.upperStrataRoughStone;
        RoughStoneBlock.strataLevelBlockArray[1] = (RoughStoneBlock) BTWBlocks.midStrataRoughStone;
        RoughStoneBlock.strataLevelBlockArray[2] = (RoughStoneBlock) BTWBlocks.deepStrataRoughStone;

        Block.dirt.setTextureName(BetterThanWorldGen.MODID + ":stone/rhyolite/regolith");
        BTWBlocks.looseDirt.setTextureName(BetterThanWorldGen.MODID + ":stone/rhyolite/loose_regolith");
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
        dryGrass = new ShrubBlock(BTWGBlockIDs.DRY_GRASS_ID, "dry_grass",
                new String[] {
                        "medium",
                        "short",
                },
                false,
                "plant")
                .setCanStayOnSand()
                .setReplaceable();
        register(dryGrass, new String[] {
                "medium",
                "short",
        });

        tallDryGrass = new TallPlantBlock(BTWGBlockIDs.TALL_DRY_GRASS_ID, "tall_dry_grass",
                new String[] {
                        "tall_dry_grass"
                },
                false,
                "plant")
                .setCanStayOnSand()
                .setReplaceable()
                .setNeedsShears()
                .setTextureName("");
        register(tallDryGrass);

        grass = new ColorizedShrubBlock(BTWGBlockIDs.GRASS_ID, "grass",
                new String[] {
                        "bush",
                },
                false,
                "plant")
                .setReplaceable()
                .setTextureName("");
        register(new ItemColored(grass.blockID - 256, true)
                .setBlockNames(new String[] {
                        "bush",
                }));

        grassWithOverlay = new ColorizedShrubBlock(BTWGBlockIDs.GRASS_WITH_OVERLAY_ID, "grass2",
                new String[] {
                        "bluegrass",
                        "bromegrass",
                        "fountain_grass",
                },
                false,
                "plant")
                .setHasOverlays()
                .setReplaceable()
                .setTextureName("");
        register(new ItemColored(grassWithOverlay.blockID - 256, true)
                .setBlockNames(new String[] {
                        "bluegrass",
                        "bromegrass",
                        "fountain_grass",
                }));

        tallGrass = new ColorizedTallPlantBlock(BTWGBlockIDs.TALL_GRASS_ID, "tall_grass",
                new String[] {
                        "tall_grass",
                        "large_fern",
                },
                false,
                "plant")
                .setReplaceable()
                .setNeedsShears()
                .setTextureName("");
        register(new ItemColored(tallGrass.blockID - 256, true)
                .setBlockNames(new String[] {
                        "tall_grass",
                        "large_fern",
                }));

        tallGrassWithOverlay = new ColorizedTallPlantBlock(BTWGBlockIDs.TALL_GRASS_WITH_OVERLAY_ID, "tall_grass2",
                new String[] {
                        "phragmite",
                        "cattail",
                },
                false,
                "plant")
                .setHasOverlays()
                .setReplaceable()
                .setNeedsShears()
                .setTextureName("");
        register(new ItemColored(tallGrassWithOverlay.blockID - 256, true)
                .setBlockNames(new String[] {
                        "phragmite",
                        "cattail",
                }));

        glowWorms = new HangingVineBlock(BTWGBlockIDs.GLOW_WORMS_ID)
                .setReplaceable(false)
                .setTextureName("btwg:glow_worms")
                .setUnlocalizedName("btwg.glow_worms")
                .setLightValue(0.5F);
        register(glowWorms);
    }

    private static void initWood() {

        //------ Additional Vanilla Wood Blocks ------//

        spruceDoor = new BTWGDoorBlock(BTWGBlockIDs.SPRUCE_DOOR_ID, WoodType.SPRUCE);
        spruceTrapdoor = new BlockTrapDoor(BTWGBlockIDs.SPRUCE_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.spruce_trapdoor")
                .setTextureName(BetterThanWorldGen.MODID + ":wood/spruce_trapdoor")
                .setHopperFilter();
        register(spruceTrapdoor);

        birchDoor = new BTWGDoorBlock(BTWGBlockIDs.BIRCH_DOOR_ID, WoodType.BIRCH);
        birchTrapdoor = new BlockTrapDoor(BTWGBlockIDs.BIRCH_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.birch_trapdoor")
                .setTextureName(BetterThanWorldGen.MODID + ":wood/birch_trapdoor")
                .setHopperFilter();
        register(birchTrapdoor);

        jungleDoor = new BTWGDoorBlock(BTWGBlockIDs.JUNGLE_DOOR_ID, WoodType.JUNGLE);
        jungleTrapdoor = new BlockTrapDoor(BTWGBlockIDs.JUNGLE_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.jungle_trapdoor")
                .setTextureName(BetterThanWorldGen.MODID + ":wood/jungle_trapdoor")
                .setHopperFilter();
        register(jungleTrapdoor);

        bloodWoodDoor = new BTWGDoorBlock(BTWGBlockIDs.BLOOD_WOOD_DOOR_ID, WoodType.BLOOD_WOOD);
        bloodWoodTrapdoor = new BlockTrapDoor(BTWGBlockIDs.BLOOD_WOOD_TRAPDOOR_ID)
                .setUnlocalizedName("btwg.blood_wood_trapdoor")
                .setTextureName(BetterThanWorldGen.MODID + ":wood/blood_wood_trapdoor")
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
        register(new BTWGWoodSlabBlockItem(BTWGBlockIDs.WOOD_SLAB_ID - 256, new String[] {
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
                "acacia",
                "dark_oak",
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
                "btwg:wood/chewed_" + WoodType.ACACIA.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.ACACIA.name() + "_log_side");
        register(acaciaLogSpike);

        acaciaChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_ACACIA_LOG_ID,
                "btwg:wood/chewed_" + WoodType.ACACIA.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.ACACIA.name() + "_log_side",
                "btwg:wood/" + WoodType.ACACIA.name() + "_stump_top",
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
                .setTextureName("btwg:wood/acacia_trapdoor")
                .setHopperFilter();
        register(acaciaTrapdoor);

        acaciaSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.ACACIA_SIDING_ID,
                "btwg:wood/acacia_planks", "btwg.acacia_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.ACACIA_SIDING_ID - 256));

        acaciaMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.ACACIA_MOULDING_ID,
                "btwg:wood/acacia_planks", "btwg:wood/acacia_column",
                BTWGBlockIDs.ACACIA_SIDING_ID,
                "btwg.acacia_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.ACACIA_MOULDING_ID - 256));

        acaciaSapling = new SaplingBlock(BTWGBlockIDs.ACACIA_SAPLING_ID, "btwg.acacia_sapling", "btwg:wood/acacia_sapling")
                .addTreeGrower(BTWGTreeGrowers.SAVANNA_TREE, 2)
                .addTreeGrower(BTWGTreeGrowers.SPLIT_SAVANNA_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.ACACIA_SAPLING_ID - 256, acaciaSapling));

        //------ Dark Oak ------//

        darkOakLog = new BTWGLogBlock(BTWGBlockIDs.DARK_OAK_LOG_ID, WoodType.DARK_OAK);
        register(darkOakLog);

        darkOakLogSpike = new LogSpikeBlock(BTWGBlockIDs.DARK_OAK_LOG_SPIKE_ID,
                "btwg:wood/chewed_" + WoodType.DARK_OAK.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.DARK_OAK.name() + "_log_side");
        register(darkOakLogSpike);

        darkOakChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_DARK_OAK_LOG_ID,
                "btwg:wood/chewed_" + WoodType.DARK_OAK.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.DARK_OAK.name() + "_log_side",
                "btwg:wood/" + WoodType.DARK_OAK.name() + "_stump_top",
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
                .setTextureName("btwg:wood/dark_oak_trapdoor")
                .setHopperFilter();
        register(darkOakTrapdoor);

        darkOakSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.DARK_OAK_SIDING_ID,
                "btwg:wood/dark_oak_planks", "btwg.dark_oak_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.DARK_OAK_SIDING_ID - 256));

        darkOakMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.DARK_OAK_MOULDING_ID,
                "btwg:wood/dark_oak_planks", "btwg:wood/dark_oak_column",
                BTWGBlockIDs.DARK_OAK_SIDING_ID,
                "btwg.dark_oak_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.DARK_OAK_MOULDING_ID - 256));

        darkOakSapling = new SaplingBlock(BTWGBlockIDs.DARK_OAK_SAPLING_ID, "btwg.dark_oak_sapling", "btwg:wood/dark_oak_sapling")
                .addTreeGrower(BTWGTreeGrowers.DARK_OAK_TREE, 1)
                .add2x2TreeGrower(BTWGTreeGrowers.HUGE_DARK_OAK_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.DARK_OAK_SAPLING_ID - 256, darkOakSapling));

        //------ Mangrove ------//

        mangroveLog = new BTWGLogBlock(BTWGBlockIDs.MANGROVE_LOG_ID, WoodType.MANGROVE);
        register(mangroveLog);

        mangroveLogSpike = new LogSpikeBlock(BTWGBlockIDs.MANGROVE_LOG_SPIKE_ID,
                "btwg:wood/chewed_" + WoodType.MANGROVE.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.MANGROVE.name() + "_log_side");
        register(mangroveLogSpike);

        mangroveChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_MANGROVE_LOG_ID,
                "btwg:wood/chewed_" + WoodType.MANGROVE.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.MANGROVE.name() + "_log_side",
                "btwg:wood/" + WoodType.MANGROVE.name() + "_stump_top",
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
                .setTextureName("btwg:wood/mangrove_trapdoor")
                .setHopperFilter();
        register(mangroveTrapdoor);

        mangroveSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.MANGROVE_SIDING_ID,
                "btwg:wood/mangrove_planks", "btwg.mangrove_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.MANGROVE_SIDING_ID - 256));

        mangroveMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.MANGROVE_MOULDING_ID,
                "btwg:wood/mangrove_planks", "btwg:wood/mangrove_column",
                BTWGBlockIDs.MANGROVE_SIDING_ID,
                "btwg.mangrove_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.MANGROVE_MOULDING_ID - 256));

        mangroveSapling = new SaplingBlock(BTWGBlockIDs.MANGROVE_SAPLING_ID, "btwg.mangrove_sapling", "btwg:wood/mangrove_sapling");
        register(new SaplingBlockItem(BTWGBlockIDs.MANGROVE_SAPLING_ID - 256, mangroveSapling));

        //------ Cherry ------//

        cherryLog = new BTWGLogBlock(BTWGBlockIDs.CHERRY_LOG_ID, WoodType.CHERRY);
        register(cherryLog);

        cherryLogSpike = new LogSpikeBlock(BTWGBlockIDs.CHERRY_LOG_SPIKE_ID,
                "btwg:wood/chewed_" + WoodType.CHERRY.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.CHERRY.name() + "_log_side");
        register(cherryLogSpike);

        cherryChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_CHERRY_LOG_ID,
                "btwg:wood/chewed_" + WoodType.CHERRY.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.CHERRY.name() + "_log_side",
                "btwg:wood/" + WoodType.CHERRY.name() + "_stump_top",
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
                .setTextureName("btwg:wood/cherry_trapdoor")
                .setHopperFilter();
        register(cherryTrapdoor);

        cherrySidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.CHERRY_SIDING_ID,
                "btwg:wood/cherry_planks", "btwg.cherry_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.CHERRY_SIDING_ID - 256));

        cherryMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.CHERRY_MOULDING_ID,
                "btwg:wood/cherry_planks", "btwg:wood/cherry_column",
                BTWGBlockIDs.CHERRY_SIDING_ID,
                "btwg.cherry_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.CHERRY_MOULDING_ID - 256));

        cherrySapling = new SaplingBlock(BTWGBlockIDs.CHERRY_SAPLING_ID, "btwg.cherry_sapling", "btwg:wood/cherry_sapling")
                .addTreeGrower(BTWGTreeGrowers.CHERRY_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.CHERRY_SAPLING_ID - 256, cherrySapling));

        //------ Pale Oak ------//

        paleOakLog = new BTWGLogBlock(BTWGBlockIDs.PALE_OAK_LOG_ID, WoodType.PALE_OAK);
        register(paleOakLog);

        paleOakLogSpike = new LogSpikeBlock(BTWGBlockIDs.PALE_OAK_LOG_SPIKE_ID,
                "btwg:wood/chewed_" + WoodType.PALE_OAK.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.PALE_OAK.name() + "_log_side");
        register(paleOakLogSpike);

        paleOakChewedLog = new ChewedLogBlock(BTWGBlockIDs.CHEWED_PALE_OAK_LOG_ID,
                "btwg:wood/chewed_" + WoodType.PALE_OAK.name() + "_log_top",
                "btwg:wood/chewed_" + WoodType.PALE_OAK.name() + "_log_side",
                "btwg:wood/" + WoodType.PALE_OAK.name() + "_stump_top",
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
                .setTextureName("btwg:wood/pale_oak_trapdoor")
                .setHopperFilter();
        register(paleOakTrapdoor);

        paleOakSidingAndCorner = new BTWGWoodSidingAndCornerBlock(BTWGBlockIDs.PALE_OAK_SIDING_ID,
                "btwg:wood/pale_oak_planks", "btwg.pale_oak_siding");
        register(new SidingAndCornerBlockItem(BTWGBlockIDs.PALE_OAK_SIDING_ID - 256));

        paleOakMoulding = new BTWGWoodMouldingBlock(BTWGBlockIDs.PALE_OAK_MOULDING_ID,
                "btwg:wood/pale_oak_planks", "btwg:wood/pale_oak_column",
                BTWGBlockIDs.PALE_OAK_SIDING_ID,
                "btwg.pale_oak_moulding");
        register(new MouldingAndDecorativeBlockItem(BTWGBlockIDs.PALE_OAK_MOULDING_ID - 256));

        paleOakSapling = new SaplingBlock(BTWGBlockIDs.PALE_OAK_SAPLING_ID, "btwg.pale_oak_sapling", "btwg:wood/pale_oak_sapling")
                .addTreeGrower(BTWGTreeGrowers.PALE_OAK_TREE, 1)
                .add2x2TreeGrower(BTWGTreeGrowers.HUGE_PALE_OAK_TREE, 1);
        register(new SaplingBlockItem(BTWGBlockIDs.PALE_OAK_SAPLING_ID - 256, paleOakSapling));
    }

    private static void initStone() {

        //------ Limestone ------//

        limestone = new BTWGStoneBlock(BTWGBlockIDs.LIMESTONE_ID, StoneType.LIMESTONE);
        register(limestone);

        roughLimestone = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_LIMESTONE_ID, StoneType.LIMESTONE);
        register(roughLimestone);

        limestoneStairs = new StairsBlock(BTWGBlockIDs.LIMESTONE_STAIRS_ID, limestone, StoneType.LIMESTONE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".limestone_stairs");
        register(limestoneStairs);

        limestoneDecorative = new StoneDecorativeBlock(BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, StoneType.LIMESTONE);
        register(limestoneDecorative, ((MultiTextureBlock) limestoneDecorative).getNames());

        limestoneSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.LIMESTONE_SLAB_ID, StoneType.LIMESTONE);
        register(new SlabWithMetadataBlockItem(
                limestoneSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledLimestoneStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_LIMESTONE_STAIRS_ID, limestoneDecorative,
                StoneType.LIMESTONE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_LIMESTONE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_limestone_stairs");
        register(cobbledLimestoneStairs);

        limestoneBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.LIMESTONE_BRICK_STAIRS_ID, limestoneDecorative,
                StoneType.LIMESTONE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_LIMESTONE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".limestone_brick_stairs");
        register(limestoneBrickStairs);

        looseLimestone = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_LIMESTONE_ID, StoneType.LIMESTONE);
        register(looseLimestone, new String[] {
                "cobble",
                "bricks"
        });

        looseLimestoneSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_LIMESTONE_SLAB_ID, StoneType.LIMESTONE);
        register(new SlabWithMetadataBlockItem(
                looseLimestoneSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledLimestoneStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_LIMESTONE_STAIRS_ID,
                looseLimestone, StoneType.LIMESTONE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_LIMESTONE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_limestone_stairs");
        register(looseCobbledLimestoneStairs);

        looseLimestoneBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_LIMESTONE_BRICK_STAIRS_ID,
                looseLimestone, StoneType.LIMESTONE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_LIMESTONE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_limestone_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseLimestoneBrickStairs);

        limestoneRegolith = new RegolithBlock(BTWGBlockIDs.LIMESTONE_REGOLITH_ID, StoneType.LIMESTONE);
        register(limestoneRegolith);

        limestoneRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.LIMESTONE_REGOLITH_SLAB_ID, StoneType.LIMESTONE);
        register(new SlabBlockItem(limestoneRegolithSlab.blockID - 256));

        grassyLimestoneRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_LIMESTONE_REGOLITH_ID, StoneType.LIMESTONE);
        register(grassyLimestoneRegolith);

        grassyLimestoneRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_LIMESTONE_REGOLITH_SLAB_ID, StoneType.LIMESTONE);
        register(new SlabBlockItem(grassyLimestoneRegolithSlab.blockID - 256));

        looseLimestoneRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_LIMESTONE_REGOLITH_ID, StoneType.LIMESTONE);
        register(looseLimestoneRegolith);

        looseLimestoneRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_LIMESTONE_REGOLITH_SLAB_ID, StoneType.LIMESTONE);
        register(new SlabBlockItem(looseLimestoneRegolithSlab.blockID - 256));

        looseSparseGrassyLimestoneRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_LIMESTONE_REGOLITH_ID, StoneType.LIMESTONE);
        register(looseSparseGrassyLimestoneRegolith);

        looseSparseGrassyLimestoneRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_LIMESTONE_REGOLITH_SLAB_ID, StoneType.LIMESTONE);
        register(new SlabBlockItem(looseSparseGrassyLimestoneRegolithSlab.blockID - 256));

        limestoneGravel = new BTWGGravelBlock(BTWGBlockIDs.LIMESTONE_GRAVEL_ID, StoneType.LIMESTONE);
        register(limestoneGravel);

        limestoneGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.LIMESTONE_GRAVEL_SLAB_ID, StoneType.LIMESTONE);
        register(new SlabBlockItem(limestoneGravelSlab.blockID - 256));

        //------ Andesite ------//

        andesite = new BTWGStoneBlock(BTWGBlockIDs.ANDESITE_ID, StoneType.ANDESITE);
        register(andesite);

        roughAndesite = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_ANDESITE_ID, StoneType.ANDESITE);
        register(roughAndesite);

        andesiteStairs = new StairsBlock(BTWGBlockIDs.ANDESITE_STAIRS_ID, andesite, StoneType.ANDESITE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".andesite_stairs");
        register(andesiteStairs);

        andesiteDecorative = new StoneDecorativeBlock(BTWGBlockIDs.ANDESITE_DECORATIVE_ID, StoneType.ANDESITE);
        register(andesiteDecorative, ((MultiTextureBlock) andesiteDecorative).getNames());

        andesiteSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.ANDESITE_SLAB_ID, StoneType.ANDESITE);
        register(new SlabWithMetadataBlockItem(
                andesiteSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledAndesiteStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_ANDESITE_STAIRS_ID, andesiteDecorative,
                StoneType.ANDESITE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_ANDESITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_andesite_stairs");
        register(cobbledAndesiteStairs);

        andesiteBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.ANDESITE_BRICK_STAIRS_ID, andesiteDecorative,
                StoneType.ANDESITE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_ANDESITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".andesite_brick_stairs");
        register(andesiteBrickStairs);

        looseAndesite = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_ANDESITE_ID, StoneType.ANDESITE);
        register(looseAndesite, new String[] {
                "cobble",
                "bricks"
        });

        looseAndesiteSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_ANDESITE_SLAB_ID, StoneType.ANDESITE);
        register(new SlabWithMetadataBlockItem(
                looseAndesiteSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledAndesiteStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_ANDESITE_STAIRS_ID,
                looseAndesite, StoneType.ANDESITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_ANDESITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_andesite_stairs");
        register(looseCobbledAndesiteStairs);

        looseAndesiteBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_ANDESITE_BRICK_STAIRS_ID,
                looseAndesite, StoneType.ANDESITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_ANDESITE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_andesite_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseAndesiteBrickStairs);

        andesiteRegolith = new RegolithBlock(BTWGBlockIDs.ANDESITE_REGOLITH_ID, StoneType.ANDESITE);
        register(andesiteRegolith);

        andesiteRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.ANDESITE_REGOLITH_SLAB_ID, StoneType.ANDESITE);
        register(new SlabBlockItem(andesiteRegolithSlab.blockID - 256));

        grassyAndesiteRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_ANDESITE_REGOLITH_ID, StoneType.ANDESITE);
        register(grassyAndesiteRegolith);

        grassyAndesiteRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_ANDESITE_REGOLITH_SLAB_ID, StoneType.ANDESITE);
        register(new SlabBlockItem(grassyAndesiteRegolithSlab.blockID - 256));

        looseAndesiteRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_ANDESITE_REGOLITH_ID, StoneType.ANDESITE);
        register(looseAndesiteRegolith);

        looseAndesiteRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_ANDESITE_REGOLITH_SLAB_ID, StoneType.ANDESITE);
        register(new SlabBlockItem(looseAndesiteRegolithSlab.blockID - 256));

        looseSparseGrassyAndesiteRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_ANDESITE_REGOLITH_ID, StoneType.ANDESITE);
        register(looseSparseGrassyAndesiteRegolith);

        looseSparseGrassyAndesiteRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_ANDESITE_REGOLITH_SLAB_ID, StoneType.ANDESITE);
        register(new SlabBlockItem(looseSparseGrassyAndesiteRegolithSlab.blockID - 256));

        andesiteGravel = new BTWGGravelBlock(BTWGBlockIDs.ANDESITE_GRAVEL_ID, StoneType.ANDESITE);
        register(andesiteGravel);

        andesiteGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.ANDESITE_GRAVEL_SLAB_ID, StoneType.ANDESITE);
        register(new SlabBlockItem(andesiteGravelSlab.blockID - 256));

        //------ Basalt ------//

        basalt = new BTWGStoneBlock(BTWGBlockIDs.BASALT_ID, StoneType.BASALT);
        register(basalt);

        roughBasalt = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_BASALT_ID, StoneType.BASALT);
        register(roughBasalt);

        basaltStairs = new StairsBlock(BTWGBlockIDs.BASALT_STAIRS_ID, basalt, StoneType.BASALT.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".basalt_stairs");
        register(basaltStairs);

        basaltDecorative = new StoneDecorativeBlock(BTWGBlockIDs.BASALT_DECORATIVE_ID, StoneType.BASALT);
        register(basaltDecorative, ((MultiTextureBlock) basaltDecorative).getNames());

        basaltSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.BASALT_SLAB_ID, StoneType.BASALT);
        register(new SlabWithMetadataBlockItem(
                basaltSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledBasaltStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_BASALT_STAIRS_ID, basaltDecorative,
                StoneType.BASALT.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_BASALT_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_basalt_stairs");
        register(cobbledBasaltStairs);

        basaltBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.BASALT_BRICK_STAIRS_ID, basaltDecorative,
                StoneType.BASALT.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_BASALT_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".basalt_brick_stairs");
        register(basaltBrickStairs);

        looseBasalt = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_BASALT_ID, StoneType.BASALT);
        register(looseBasalt, new String[] {
                "cobble",
                "bricks"
        });

        looseBasaltSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_BASALT_SLAB_ID, StoneType.BASALT);
        register(new SlabWithMetadataBlockItem(
                looseBasaltSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledBasaltStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_BASALT_STAIRS_ID,
                looseBasalt, StoneType.BASALT.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_BASALT_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_basalt_stairs");
        register(looseCobbledBasaltStairs);

        looseBasaltBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_BASALT_BRICK_STAIRS_ID,
                looseBasalt, StoneType.BASALT.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_BASALT_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_basalt_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseBasaltBrickStairs);

        basaltRegolith = new RegolithBlock(BTWGBlockIDs.BASALT_REGOLITH_ID, StoneType.BASALT);
        register(basaltRegolith);

        basaltRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.BASALT_REGOLITH_SLAB_ID, StoneType.BASALT);
        register(new SlabBlockItem(basaltRegolithSlab.blockID - 256));

        grassyBasaltRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_BASALT_REGOLITH_ID, StoneType.BASALT);
        register(grassyBasaltRegolith);

        grassyBasaltRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_BASALT_REGOLITH_SLAB_ID, StoneType.BASALT);
        register(new SlabBlockItem(grassyBasaltRegolithSlab.blockID - 256));

        looseBasaltRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_BASALT_REGOLITH_ID, StoneType.BASALT);
        register(looseBasaltRegolith);

        looseBasaltRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_BASALT_REGOLITH_SLAB_ID, StoneType.BASALT);
        register(new SlabBlockItem(looseBasaltRegolithSlab.blockID - 256));

        looseSparseGrassyBasaltRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_BASALT_REGOLITH_ID, StoneType.BASALT);
        register(looseSparseGrassyBasaltRegolith);

        looseSparseGrassyBasaltRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_BASALT_REGOLITH_SLAB_ID, StoneType.BASALT);
        register(new SlabBlockItem(looseSparseGrassyBasaltRegolithSlab.blockID - 256));

        basaltGravel = new BTWGGravelBlock(BTWGBlockIDs.BASALT_GRAVEL_ID, StoneType.BASALT);
        register(basaltGravel);

        basaltGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.BASALT_GRAVEL_SLAB_ID, StoneType.BASALT);
        register(new SlabBlockItem(basaltGravelSlab.blockID - 256));

        //------ Calcite ------//

        calcite = new BTWGStoneBlock(BTWGBlockIDs.CALCITE_ID, StoneType.CALCITE);
        register(calcite);

        roughCalcite = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_CALCITE_ID, StoneType.CALCITE);
        register(roughCalcite);

        calciteStairs = new StairsBlock(BTWGBlockIDs.CALCITE_STAIRS_ID, calcite, StoneType.CALCITE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".calcite_stairs");
        register(calciteStairs);

        calciteDecorative = new StoneDecorativeBlock(BTWGBlockIDs.CALCITE_DECORATIVE_ID, StoneType.CALCITE);
        register(calciteDecorative, ((MultiTextureBlock) calciteDecorative).getNames());

        calciteSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.CALCITE_SLAB_ID, StoneType.CALCITE);
        register(new SlabWithMetadataBlockItem(
                calciteSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledCalciteStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_CALCITE_STAIRS_ID, calciteDecorative,
                StoneType.CALCITE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_CALCITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_calcite_stairs");
        register(cobbledCalciteStairs);

        calciteBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.CALCITE_BRICK_STAIRS_ID, calciteDecorative,
                StoneType.CALCITE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_CALCITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".calcite_brick_stairs");
        register(calciteBrickStairs);

        looseCalcite = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_CALCITE_ID, StoneType.CALCITE);
        register(looseCalcite, new String[] {
                "cobble",
                "bricks"
        });

        looseCalciteSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_CALCITE_SLAB_ID, StoneType.CALCITE);
        register(new SlabWithMetadataBlockItem(
                looseCalciteSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledCalciteStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_CALCITE_STAIRS_ID,
                looseCalcite, StoneType.CALCITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_CALCITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_calcite_stairs");
        register(looseCobbledCalciteStairs);

        looseCalciteBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_CALCITE_BRICK_STAIRS_ID,
                looseCalcite, StoneType.CALCITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_CALCITE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_calcite_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseCalciteBrickStairs);

        calciteRegolith = new RegolithBlock(BTWGBlockIDs.CALCITE_REGOLITH_ID, StoneType.CALCITE);
        register(calciteRegolith);

        calciteRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.CALCITE_REGOLITH_SLAB_ID, StoneType.CALCITE);
        register(new SlabBlockItem(calciteRegolithSlab.blockID - 256));

        grassyCalciteRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_CALCITE_REGOLITH_ID, StoneType.CALCITE);
        register(grassyCalciteRegolith);

        grassyCalciteRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_CALCITE_REGOLITH_SLAB_ID, StoneType.CALCITE);
        register(new SlabBlockItem(grassyCalciteRegolithSlab.blockID - 256));

        looseCalciteRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_CALCITE_REGOLITH_ID, StoneType.CALCITE);
        register(looseCalciteRegolith);

        looseCalciteRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_CALCITE_REGOLITH_SLAB_ID, StoneType.CALCITE);
        register(new SlabBlockItem(looseCalciteRegolithSlab.blockID - 256));

        looseSparseGrassyCalciteRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_CALCITE_REGOLITH_ID, StoneType.CALCITE);
        register(looseSparseGrassyCalciteRegolith);

        looseSparseGrassyCalciteRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_CALCITE_REGOLITH_SLAB_ID, StoneType.CALCITE);
        register(new SlabBlockItem(looseSparseGrassyCalciteRegolithSlab.blockID - 256));

        calciteGravel = new BTWGGravelBlock(BTWGBlockIDs.CALCITE_GRAVEL_ID, StoneType.CALCITE);
        register(calciteGravel);

        calciteGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.CALCITE_GRAVEL_SLAB_ID, StoneType.CALCITE);
        register(new SlabBlockItem(calciteGravelSlab.blockID - 256));

        //------ Diorite ------//

        diorite = new BTWGStoneBlock(BTWGBlockIDs.DIORITE_ID, StoneType.DIORITE);
        register(diorite);

        roughDiorite = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_DIORITE_ID, StoneType.DIORITE);
        register(roughDiorite);

        dioriteStairs = new StairsBlock(BTWGBlockIDs.DIORITE_STAIRS_ID, diorite, StoneType.DIORITE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".diorite_stairs");
        register(dioriteStairs);

        dioriteDecorative = new StoneDecorativeBlock(BTWGBlockIDs.DIORITE_DECORATIVE_ID, StoneType.DIORITE);
        register(dioriteDecorative, ((MultiTextureBlock) dioriteDecorative).getNames());

        dioriteSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.DIORITE_SLAB_ID, StoneType.DIORITE);
        register(new SlabWithMetadataBlockItem(
                dioriteSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledDioriteStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_DIORITE_STAIRS_ID, dioriteDecorative,
                StoneType.DIORITE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_DIORITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_diorite_stairs");
        register(cobbledDioriteStairs);

        dioriteBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.DIORITE_BRICK_STAIRS_ID, dioriteDecorative,
                StoneType.DIORITE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_DIORITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".diorite_brick_stairs");
        register(dioriteBrickStairs);

        looseDiorite = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_DIORITE_ID, StoneType.DIORITE);
        register(looseDiorite, new String[] {
                "cobble",
                "bricks"
        });

        looseDioriteSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_DIORITE_SLAB_ID, StoneType.DIORITE);
        register(new SlabWithMetadataBlockItem(
                looseDioriteSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledDioriteStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_DIORITE_STAIRS_ID,
                looseDiorite, StoneType.DIORITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_DIORITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_diorite_stairs");
        register(looseCobbledDioriteStairs);

        looseDioriteBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_DIORITE_BRICK_STAIRS_ID,
                looseDiorite, StoneType.DIORITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_DIORITE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_diorite_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseDioriteBrickStairs);

        dioriteRegolith = new RegolithBlock(BTWGBlockIDs.DIORITE_REGOLITH_ID, StoneType.DIORITE);
        register(dioriteRegolith);

        dioriteRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.DIORITE_REGOLITH_SLAB_ID, StoneType.DIORITE);
        register(new SlabBlockItem(dioriteRegolithSlab.blockID - 256));

        grassyDioriteRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_DIORITE_REGOLITH_ID, StoneType.DIORITE);
        register(grassyDioriteRegolith);

        grassyDioriteRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_DIORITE_REGOLITH_SLAB_ID, StoneType.DIORITE);
        register(new SlabBlockItem(grassyDioriteRegolithSlab.blockID - 256));

        looseDioriteRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_DIORITE_REGOLITH_ID, StoneType.DIORITE);
        register(looseDioriteRegolith);

        looseDioriteRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_DIORITE_REGOLITH_SLAB_ID, StoneType.DIORITE);
        register(new SlabBlockItem(looseDioriteRegolithSlab.blockID - 256));

        looseSparseGrassyDioriteRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_DIORITE_REGOLITH_ID, StoneType.DIORITE);
        register(looseSparseGrassyDioriteRegolith);

        looseSparseGrassyDioriteRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_DIORITE_REGOLITH_SLAB_ID, StoneType.DIORITE);
        register(new SlabBlockItem(looseSparseGrassyDioriteRegolithSlab.blockID - 256));

        dioriteGravel = new BTWGGravelBlock(BTWGBlockIDs.DIORITE_GRAVEL_ID, StoneType.DIORITE);
        register(dioriteGravel);

        dioriteGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.DIORITE_GRAVEL_SLAB_ID, StoneType.DIORITE);
        register(new SlabBlockItem(dioriteGravelSlab.blockID - 256));

        //------ Granite ------//

        granite = new BTWGStoneBlock(BTWGBlockIDs.GRANITE_ID, StoneType.GRANITE);
        register(granite);

        roughGranite = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_GRANITE_ID, StoneType.GRANITE);
        register(roughGranite);

        graniteStairs = new StairsBlock(BTWGBlockIDs.GRANITE_STAIRS_ID, granite, StoneType.GRANITE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".granite_stairs");
        register(graniteStairs);

        graniteDecorative = new StoneDecorativeBlock(BTWGBlockIDs.GRANITE_DECORATIVE_ID, StoneType.GRANITE);
        register(graniteDecorative, ((MultiTextureBlock) graniteDecorative).getNames());

        graniteSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.GRANITE_SLAB_ID, StoneType.GRANITE);
        register(new SlabWithMetadataBlockItem(
                graniteSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledGraniteStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_GRANITE_STAIRS_ID, graniteDecorative,
                StoneType.GRANITE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_GRANITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_granite_stairs");
        register(cobbledGraniteStairs);

        graniteBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.GRANITE_BRICK_STAIRS_ID, graniteDecorative,
                StoneType.GRANITE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_GRANITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".granite_brick_stairs");
        register(graniteBrickStairs);

        looseGranite = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_GRANITE_ID, StoneType.GRANITE);
        register(looseGranite, new String[] {
                "cobble",
                "bricks"
        });

        looseGraniteSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_GRANITE_SLAB_ID, StoneType.GRANITE);
        register(new SlabWithMetadataBlockItem(
                looseGraniteSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledGraniteStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_GRANITE_STAIRS_ID,
                looseGranite, StoneType.GRANITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_GRANITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_granite_stairs");
        register(looseCobbledGraniteStairs);

        looseGraniteBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_GRANITE_BRICK_STAIRS_ID,
                looseGranite, StoneType.GRANITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_GRANITE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_granite_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseGraniteBrickStairs);

        graniteRegolith = new RegolithBlock(BTWGBlockIDs.GRANITE_REGOLITH_ID, StoneType.GRANITE);
        register(graniteRegolith);

        graniteRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.GRANITE_REGOLITH_SLAB_ID, StoneType.GRANITE);
        register(new SlabBlockItem(graniteRegolithSlab.blockID - 256));

        grassyGraniteRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_GRANITE_REGOLITH_ID, StoneType.GRANITE);
        register(grassyGraniteRegolith);

        grassyGraniteRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_GRANITE_REGOLITH_SLAB_ID, StoneType.GRANITE);
        register(new SlabBlockItem(grassyGraniteRegolithSlab.blockID - 256));

        looseGraniteRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_GRANITE_REGOLITH_ID, StoneType.GRANITE);
        register(looseGraniteRegolith);

        looseGraniteRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_GRANITE_REGOLITH_SLAB_ID, StoneType.GRANITE);
        register(new SlabBlockItem(looseGraniteRegolithSlab.blockID - 256));

        looseSparseGrassyGraniteRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_GRANITE_REGOLITH_ID, StoneType.GRANITE);
        register(looseSparseGrassyGraniteRegolith);

        looseSparseGrassyGraniteRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_GRANITE_REGOLITH_SLAB_ID, StoneType.GRANITE);
        register(new SlabBlockItem(looseSparseGrassyGraniteRegolithSlab.blockID - 256));

        graniteGravel = new BTWGGravelBlock(BTWGBlockIDs.GRANITE_GRAVEL_ID, StoneType.GRANITE);
        register(graniteGravel);

        graniteGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.GRANITE_GRAVEL_SLAB_ID, StoneType.GRANITE);
        register(new SlabBlockItem(graniteGravelSlab.blockID - 256));

        //------ Kimberlite ------//

        kimberlite = new BTWGStoneBlock(BTWGBlockIDs.KIMBERLITE_ID, StoneType.KIMBERLITE);
        register(kimberlite);

        roughKimberlite = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_KIMBERLITE_ID, StoneType.KIMBERLITE);
        register(roughKimberlite);

        kimberliteStairs = new StairsBlock(BTWGBlockIDs.KIMBERLITE_STAIRS_ID, kimberlite, StoneType.KIMBERLITE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".kimberlite_stairs");
        register(kimberliteStairs);

        kimberliteDecorative = new StoneDecorativeBlock(BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, StoneType.KIMBERLITE);
        register(kimberliteDecorative, ((MultiTextureBlock) kimberliteDecorative).getNames());

        kimberliteSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.KIMBERLITE_SLAB_ID, StoneType.KIMBERLITE);
        register(new SlabWithMetadataBlockItem(
                kimberliteSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledKimberliteStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_KIMBERLITE_STAIRS_ID, kimberliteDecorative,
                StoneType.KIMBERLITE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_KIMBERLITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_kimberlite_stairs");
        register(cobbledKimberliteStairs);

        kimberliteBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.KIMBERLITE_BRICK_STAIRS_ID, kimberliteDecorative,
                StoneType.KIMBERLITE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_KIMBERLITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".kimberlite_brick_stairs");
        register(kimberliteBrickStairs);

        looseKimberlite = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_KIMBERLITE_ID, StoneType.KIMBERLITE);
        register(looseKimberlite, new String[] {
                "cobble",
                "bricks"
        });

        looseKimberliteSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_KIMBERLITE_SLAB_ID, StoneType.KIMBERLITE);
        register(new SlabWithMetadataBlockItem(
                looseKimberliteSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledKimberliteStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_KIMBERLITE_STAIRS_ID,
                looseKimberlite, StoneType.KIMBERLITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_KIMBERLITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_kimberlite_stairs");
        register(looseCobbledKimberliteStairs);

        looseKimberliteBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_KIMBERLITE_BRICK_STAIRS_ID,
                looseKimberlite, StoneType.KIMBERLITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_KIMBERLITE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_kimberlite_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseKimberliteBrickStairs);

        kimberliteRegolith = new RegolithBlock(BTWGBlockIDs.KIMBERLITE_REGOLITH_ID, StoneType.KIMBERLITE);
        register(kimberliteRegolith);

        kimberliteRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.KIMBERLITE_REGOLITH_SLAB_ID, StoneType.KIMBERLITE);
        register(new SlabBlockItem(kimberliteRegolithSlab.blockID - 256));

        grassyKimberliteRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_KIMBERLITE_REGOLITH_ID, StoneType.KIMBERLITE);
        register(grassyKimberliteRegolith);

        grassyKimberliteRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_KIMBERLITE_REGOLITH_SLAB_ID, StoneType.KIMBERLITE);
        register(new SlabBlockItem(grassyKimberliteRegolithSlab.blockID - 256));

        looseKimberliteRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_KIMBERLITE_REGOLITH_ID, StoneType.KIMBERLITE);
        register(looseKimberliteRegolith);

        looseKimberliteRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_KIMBERLITE_REGOLITH_SLAB_ID, StoneType.KIMBERLITE);
        register(new SlabBlockItem(looseKimberliteRegolithSlab.blockID - 256));

        looseSparseGrassyKimberliteRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_KIMBERLITE_REGOLITH_ID, StoneType.KIMBERLITE);
        register(looseSparseGrassyKimberliteRegolith);

        looseSparseGrassyKimberliteRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_KIMBERLITE_REGOLITH_SLAB_ID, StoneType.KIMBERLITE);
        register(new SlabBlockItem(looseSparseGrassyKimberliteRegolithSlab.blockID - 256));

        kimberliteGravel = new BTWGGravelBlock(BTWGBlockIDs.KIMBERLITE_GRAVEL_ID, StoneType.KIMBERLITE);
        register(kimberliteGravel);

        kimberliteGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.KIMBERLITE_GRAVEL_SLAB_ID, StoneType.KIMBERLITE);
        register(new SlabBlockItem(kimberliteGravelSlab.blockID - 256));

        //------ Phyllite ------//

        phyllite = new BTWGStoneBlock(BTWGBlockIDs.PHYLLITE_ID, StoneType.PHYLLITE);
        register(phyllite);

        roughPhyllite = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_PHYLLITE_ID, StoneType.PHYLLITE);
        register(roughPhyllite);

        phylliteStairs = new StairsBlock(BTWGBlockIDs.PHYLLITE_STAIRS_ID, phyllite, StoneType.PHYLLITE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".phyllite_stairs");
        register(phylliteStairs);

        phylliteDecorative = new StoneDecorativeBlock(BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, StoneType.PHYLLITE);
        register(phylliteDecorative, ((MultiTextureBlock) phylliteDecorative).getNames());

        phylliteSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.PHYLLITE_SLAB_ID, StoneType.PHYLLITE);
        register(new SlabWithMetadataBlockItem(
                phylliteSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledPhylliteStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_PHYLLITE_STAIRS_ID, phylliteDecorative,
                StoneType.PHYLLITE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_PHYLLITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_phyllite_stairs");
        register(cobbledPhylliteStairs);

        phylliteBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.PHYLLITE_BRICK_STAIRS_ID, phylliteDecorative,
                StoneType.PHYLLITE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_PHYLLITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".phyllite_brick_stairs");
        register(phylliteBrickStairs);

        loosePhyllite = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_PHYLLITE_ID, StoneType.PHYLLITE);
        register(loosePhyllite, new String[] {
                "cobble",
                "bricks"
        });

        loosePhylliteSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_PHYLLITE_SLAB_ID, StoneType.PHYLLITE);
        register(new SlabWithMetadataBlockItem(
                loosePhylliteSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledPhylliteStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_PHYLLITE_STAIRS_ID,
                loosePhyllite, StoneType.PHYLLITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_PHYLLITE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_phyllite_stairs");
        register(looseCobbledPhylliteStairs);

        loosePhylliteBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_PHYLLITE_BRICK_STAIRS_ID,
                loosePhyllite, StoneType.PHYLLITE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_PHYLLITE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_phyllite_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(loosePhylliteBrickStairs);

        phylliteRegolith = new RegolithBlock(BTWGBlockIDs.PHYLLITE_REGOLITH_ID, StoneType.PHYLLITE);
        register(phylliteRegolith);

        phylliteRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.PHYLLITE_REGOLITH_SLAB_ID, StoneType.PHYLLITE);
        register(new SlabBlockItem(phylliteRegolithSlab.blockID - 256));

        grassyPhylliteRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_PHYLLITE_REGOLITH_ID, StoneType.PHYLLITE);
        register(grassyPhylliteRegolith);

        grassyPhylliteRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_PHYLLITE_REGOLITH_SLAB_ID, StoneType.PHYLLITE);
        register(new SlabBlockItem(grassyPhylliteRegolithSlab.blockID - 256));

        loosePhylliteRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_PHYLLITE_REGOLITH_ID, StoneType.PHYLLITE);
        register(loosePhylliteRegolith);

        loosePhylliteRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_PHYLLITE_REGOLITH_SLAB_ID, StoneType.PHYLLITE);
        register(new SlabBlockItem(loosePhylliteRegolithSlab.blockID - 256));

        looseSparseGrassyPhylliteRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_PHYLLITE_REGOLITH_ID, StoneType.PHYLLITE);
        register(looseSparseGrassyPhylliteRegolith);

        looseSparseGrassyPhylliteRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_PHYLLITE_REGOLITH_SLAB_ID, StoneType.PHYLLITE);
        register(new SlabBlockItem(looseSparseGrassyPhylliteRegolithSlab.blockID - 256));

        phylliteGravel = new BTWGGravelBlock(BTWGBlockIDs.PHYLLITE_GRAVEL_ID, StoneType.PHYLLITE);
        register(phylliteGravel);

        phylliteGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.PHYLLITE_GRAVEL_SLAB_ID, StoneType.PHYLLITE);
        register(new SlabBlockItem(phylliteGravelSlab.blockID - 256));

        //------ Schist ------//

        schist = new BTWGStoneBlock(BTWGBlockIDs.SCHIST_ID, StoneType.SCHIST);
        register(schist);

        roughSchist = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_SCHIST_ID, StoneType.SCHIST);
        register(roughSchist);

        schistStairs = new StairsBlock(BTWGBlockIDs.SCHIST_STAIRS_ID, schist, StoneType.SCHIST.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".schist_stairs");
        register(schistStairs);

        schistDecorative = new StoneDecorativeBlock(BTWGBlockIDs.SCHIST_DECORATIVE_ID, StoneType.SCHIST);
        register(schistDecorative, ((MultiTextureBlock) schistDecorative).getNames());

        schistSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.SCHIST_SLAB_ID, StoneType.SCHIST);
        register(new SlabWithMetadataBlockItem(
                schistSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledSchistStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_SCHIST_STAIRS_ID, schistDecorative,
                StoneType.SCHIST.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_SCHIST_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_schist_stairs");
        register(cobbledSchistStairs);

        schistBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.SCHIST_BRICK_STAIRS_ID, schistDecorative,
                StoneType.SCHIST.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_SCHIST_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".schist_brick_stairs");
        register(schistBrickStairs);

        looseSchist = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_SCHIST_ID, StoneType.SCHIST);
        register(looseSchist, new String[] {
                "cobble",
                "bricks"
        });

        looseSchistSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_SCHIST_SLAB_ID, StoneType.SCHIST);
        register(new SlabWithMetadataBlockItem(
                looseSchistSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledSchistStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_SCHIST_STAIRS_ID,
                looseSchist, StoneType.SCHIST.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_SCHIST_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_schist_stairs");
        register(looseCobbledSchistStairs);

        looseSchistBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_SCHIST_BRICK_STAIRS_ID,
                looseSchist, StoneType.SCHIST.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_SCHIST_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_schist_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseSchistBrickStairs);

        schistRegolith = new RegolithBlock(BTWGBlockIDs.SCHIST_REGOLITH_ID, StoneType.SCHIST);
        register(schistRegolith);

        schistRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.SCHIST_REGOLITH_SLAB_ID, StoneType.SCHIST);
        register(new SlabBlockItem(schistRegolithSlab.blockID - 256));

        grassySchistRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_SCHIST_REGOLITH_ID, StoneType.SCHIST);
        register(grassySchistRegolith);

        grassySchistRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_SCHIST_REGOLITH_SLAB_ID, StoneType.SCHIST);
        register(new SlabBlockItem(grassySchistRegolithSlab.blockID - 256));

        looseSchistRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_SCHIST_REGOLITH_ID, StoneType.SCHIST);
        register(looseSchistRegolith);

        looseSchistRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_SCHIST_REGOLITH_SLAB_ID, StoneType.SCHIST);
        register(new SlabBlockItem(looseSchistRegolithSlab.blockID - 256));

        looseSparseGrassySchistRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SCHIST_REGOLITH_ID, StoneType.SCHIST);
        register(looseSparseGrassySchistRegolith);

        looseSparseGrassySchistRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SCHIST_REGOLITH_SLAB_ID, StoneType.SCHIST);
        register(new SlabBlockItem(looseSparseGrassySchistRegolithSlab.blockID - 256));

        schistGravel = new BTWGGravelBlock(BTWGBlockIDs.SCHIST_GRAVEL_ID, StoneType.SCHIST);
        register(schistGravel);

        schistGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.SCHIST_GRAVEL_SLAB_ID, StoneType.SCHIST);
        register(new SlabBlockItem(schistGravelSlab.blockID - 256));

        //------ Shale ------//

        shale = new BTWGStoneBlock(BTWGBlockIDs.SHALE_ID, StoneType.SHALE);
        register(shale);

        roughShale = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_SHALE_ID, StoneType.SHALE);
        register(roughShale);

        shaleStairs = new StairsBlock(BTWGBlockIDs.SHALE_STAIRS_ID, shale, StoneType.SHALE.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".shale_stairs");
        register(shaleStairs);

        shaleDecorative = new StoneDecorativeBlock(BTWGBlockIDs.SHALE_DECORATIVE_ID, StoneType.SHALE);
        register(shaleDecorative, ((MultiTextureBlock) shaleDecorative).getNames());

        shaleSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.SHALE_SLAB_ID, StoneType.SHALE);
        register(new SlabWithMetadataBlockItem(
                shaleSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledShaleStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_SHALE_STAIRS_ID, shaleDecorative,
                StoneType.SHALE.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_SHALE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_shale_stairs");
        register(cobbledShaleStairs);

        shaleBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.SHALE_BRICK_STAIRS_ID, shaleDecorative,
                StoneType.SHALE.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_SHALE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".shale_brick_stairs");
        register(shaleBrickStairs);

        looseShale = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_SHALE_ID, StoneType.SHALE);
        register(looseShale, new String[] {
                "cobble",
                "bricks"
        });

        looseShaleSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_SHALE_SLAB_ID, StoneType.SHALE);
        register(new SlabWithMetadataBlockItem(
                looseShaleSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledShaleStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_SHALE_STAIRS_ID,
                looseShale, StoneType.SHALE.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_SHALE_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_shale_stairs");
        register(looseCobbledShaleStairs);

        looseShaleBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_SHALE_BRICK_STAIRS_ID,
                looseShale, StoneType.SHALE.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_SHALE_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_shale_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseShaleBrickStairs);

        shaleRegolith = new RegolithBlock(BTWGBlockIDs.SHALE_REGOLITH_ID, StoneType.SHALE);
        register(shaleRegolith);

        shaleRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.SHALE_REGOLITH_SLAB_ID, StoneType.SHALE);
        register(new SlabBlockItem(shaleRegolithSlab.blockID - 256));

        grassyShaleRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_SHALE_REGOLITH_ID, StoneType.SHALE);
        register(grassyShaleRegolith);

        grassyShaleRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_SHALE_REGOLITH_SLAB_ID, StoneType.SHALE);
        register(new SlabBlockItem(grassyShaleRegolithSlab.blockID - 256));

        looseShaleRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_SHALE_REGOLITH_ID, StoneType.SHALE);
        register(looseShaleRegolith);

        looseShaleRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_SHALE_REGOLITH_SLAB_ID, StoneType.SHALE);
        register(new SlabBlockItem(looseShaleRegolithSlab.blockID - 256));

        looseSparseGrassyShaleRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SHALE_REGOLITH_ID, StoneType.SHALE);
        register(looseSparseGrassyShaleRegolith);

        looseSparseGrassyShaleRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SHALE_REGOLITH_SLAB_ID, StoneType.SHALE);
        register(new SlabBlockItem(looseSparseGrassyShaleRegolithSlab.blockID - 256));

        shaleGravel = new BTWGGravelBlock(BTWGBlockIDs.SHALE_GRAVEL_ID, StoneType.SHALE);
        register(shaleGravel);

        shaleGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.SHALE_GRAVEL_SLAB_ID, StoneType.SHALE);
        register(new SlabBlockItem(shaleGravelSlab.blockID - 256));

        //------ Tuff ------//

        tuff = new BTWGStoneBlock(BTWGBlockIDs.TUFF_ID, StoneType.TUFF);
        register(tuff);

        roughTuff = new BTWGRoughStoneBlock(BTWGBlockIDs.ROUGH_TUFF_ID, StoneType.TUFF);
        register(roughTuff);

        tuffStairs = new StairsBlock(BTWGBlockIDs.TUFF_STAIRS_ID, tuff, StoneType.TUFF.stoneMetadata())
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".tuff_stairs");
        register(tuffStairs);

        tuffDecorative = new StoneDecorativeBlock(BTWGBlockIDs.TUFF_DECORATIVE_ID, StoneType.TUFF);
        register(tuffDecorative, ((MultiTextureBlock) tuffDecorative).getNames());

        tuffSlab = new BTWGStoneSlabBlock(BTWGBlockIDs.TUFF_SLAB_ID, StoneType.TUFF);
        register(new SlabWithMetadataBlockItem(
                tuffSlab.blockID - 256,
                new String[] {
                        "raw",
                        "cobble",
                        "polished",
                        "bricks"
                }
        ));

        cobbledTuffStairs = new MortaredStairsBlock(
                BTWGBlockIDs.COBBLED_TUFF_STAIRS_ID, tuffDecorative,
                StoneType.TUFF.cobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_TUFF_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".cobbled_tuff_stairs");
        register(cobbledTuffStairs);

        tuffBrickStairs = new MortaredStairsBlock(
                BTWGBlockIDs.TUFF_BRICK_STAIRS_ID, tuffDecorative,
                StoneType.TUFF.stoneBrickMetadata(),
                BTWGBlockIDs.LOOSE_COBBLED_TUFF_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".tuff_brick_stairs");
        register(tuffBrickStairs);

        looseTuff = new LooseStoneDecorativeBlock(BTWGBlockIDs.LOOSE_TUFF_ID, StoneType.TUFF);
        register(looseTuff, new String[] {
                "cobble",
                "bricks"
        });

        looseTuffSlab = new LooseStoneSlab(BTWGBlockIDs.LOOSE_TUFF_SLAB_ID, StoneType.TUFF);
        register(new SlabWithMetadataBlockItem(
                looseTuffSlab.blockID - 256,
                new String[] {
                        "cobble",
                        "bricks"
                }
        ));

        looseCobbledTuffStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_COBBLED_TUFF_STAIRS_ID,
                looseTuff, StoneType.TUFF.looseCobblestoneMetadata(),
                BTWGBlockIDs.COBBLED_TUFF_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_tuff_stairs");
        register(looseCobbledTuffStairs);

        looseTuffBrickStairs = new LooseMortarableStairsBlock(
                BTWGBlockIDs.LOOSE_TUFF_BRICK_STAIRS_ID,
                looseTuff, StoneType.TUFF.looseCobblestoneMetadata(),
                BTWGBlockIDs.LOOSE_TUFF_BRICK_STAIRS_ID)
                .setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_cobbled_tuff_stairs")
                .setCreativeTab(CreativeTabs.tabMaterials);
        register(looseTuffBrickStairs);

        tuffRegolith = new RegolithBlock(BTWGBlockIDs.TUFF_REGOLITH_ID, StoneType.TUFF);
        register(tuffRegolith);

        tuffRegolithSlab = new RegolithSlabBlock(BTWGBlockIDs.TUFF_REGOLITH_SLAB_ID, StoneType.TUFF);
        register(new SlabBlockItem(tuffRegolithSlab.blockID - 256));

        grassyTuffRegolith = new GrassyRegolithBlock(BTWGBlockIDs.GRASSY_TUFF_REGOLITH_ID, StoneType.TUFF);
        register(grassyTuffRegolith);

        grassyTuffRegolithSlab = new GrassyRegolithSlabBlock(BTWGBlockIDs.GRASSY_TUFF_REGOLITH_SLAB_ID, StoneType.TUFF);
        register(new SlabBlockItem(grassyTuffRegolithSlab.blockID - 256));

        looseTuffRegolith = new LooseRegolithBlock(BTWGBlockIDs.LOOSE_TUFF_REGOLITH_ID, StoneType.TUFF);
        register(looseTuffRegolith);

        looseTuffRegolithSlab = new LooseRegolithSlabBlock(BTWGBlockIDs.LOOSE_TUFF_REGOLITH_SLAB_ID, StoneType.TUFF);
        register(new SlabBlockItem(looseTuffRegolithSlab.blockID - 256));

        looseSparseGrassyTuffRegolith = new LooseSparseGrassyRegolithBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_TUFF_REGOLITH_ID, StoneType.TUFF);
        register(looseSparseGrassyTuffRegolith);

        looseSparseGrassyTuffRegolithSlab = new LooseSparseGrassyRegolithSlabBlock(BTWGBlockIDs.LOOSE_SPARSE_GRASSY_TUFF_REGOLITH_SLAB_ID, StoneType.TUFF);
        register(new SlabBlockItem(looseSparseGrassyTuffRegolithSlab.blockID - 256));

        tuffGravel = new BTWGGravelBlock(BTWGBlockIDs.TUFF_GRAVEL_ID, StoneType.TUFF);
        register(tuffGravel);

        tuffGravelSlab = new BTWGGravelSlabBlock(BTWGBlockIDs.TUFF_GRAVEL_SLAB_ID, StoneType.TUFF);
        register(new SlabBlockItem(tuffGravelSlab.blockID - 256));
    }

    private static void initOre() {
        StoneType[] stoneTypes = Arrays.stream(StoneType.STONE_TYPES)
                .filter(stoneType -> stoneType != StoneType.RHYOLITE
                                && stoneType != StoneType.SLATE
                                && stoneType != StoneType.GABBRO)
                .toArray(StoneType[]::new);

        String[] stoneTypeNames = Arrays.stream(StoneType.STONE_TYPES)
                .map(StoneType::name)
                .toArray(String[]::new);

        coalOre = new BTWGOreBlock(BTWGBlockIDs.COAL_ORE_ID,
                0,
                Item.coal.itemID, 0,
                "coal",
                stoneTypes)
                .setIDDroppedOnConversion((difficulty, metadata) -> ((OreBlockStaged) Block.oreCoal).idDroppedOnConversion(difficulty, metadata))
                .setMetadataDroppedOnConversion(metadata -> ((OreBlockStaged) Block.oreCoal).damageDroppedOnConversion(metadata));
        register(coalOre, stoneTypeNames);

        ironOre = new BTWGOreBlock(BTWGBlockIDs.IRON_ORE_ID,
                1,
                BTWItems.ironOreChunk.itemID, 0,
                "iron",
                stoneTypes)
                .setIDDroppedOnConversion((difficulty, metadata) -> ((OreBlockStaged) Block.oreIron).idDroppedOnConversion(difficulty, metadata))
                .setMetadataDroppedOnConversion(metadata -> ((OreBlockStaged) Block.oreIron).damageDroppedOnConversion(metadata));
        register(ironOre, stoneTypeNames);

        goldOre = new BTWGOreBlock(BTWGBlockIDs.GOLD_ORE_ID,
                2,
                BTWItems.goldOreChunk.itemID, 0,
                "gold",
                stoneTypes)
                .setIDDroppedOnConversion((difficulty, metadata) -> ((OreBlockStaged) Block.oreGold).idDroppedOnConversion(difficulty, metadata))
                .setMetadataDroppedOnConversion(metadata -> ((OreBlockStaged) Block.oreGold).damageDroppedOnConversion(metadata));
        register(goldOre, stoneTypeNames);

        diamondOre = new BTWGOreBlock(BTWGBlockIDs.DIAMOND_ORE_ID,
                2,
                Item.diamond.itemID, 0,
                "diamond",
                stoneTypes)
                .setIDDroppedOnConversion((difficulty, metadata) -> ((OreBlockStaged) Block.oreDiamond).idDroppedOnConversion(difficulty, metadata))
                .setMetadataDroppedOnConversion(metadata -> ((OreBlockStaged) Block.oreDiamond).damageDroppedOnConversion(metadata));
        register(diamondOre, stoneTypeNames);

        emeraldOre = new BTWGOreBlock(BTWGBlockIDs.EMERALD_ORE_ID,
                2,
                Item.emerald.itemID, 0,
                "emerald",
                stoneTypes)
                .setIDDroppedOnConversion((difficulty, metadata) -> ((OreBlockStaged) Block.oreEmerald).idDroppedOnConversion(difficulty, metadata))
                .setMetadataDroppedOnConversion(metadata -> ((OreBlockStaged) Block.oreEmerald).damageDroppedOnConversion(metadata));
        register(emeraldOre, stoneTypeNames);

        redstoneOre = new BTWGOreBlock(BTWGBlockIDs.REDSTONE_ORE_ID,
                2,
                Item.redstone.itemID, 0,
                "redstone",
                stoneTypes)
                .setIDDroppedOnConversion((difficulty, metadata) -> ((OreBlockStaged) Block.oreRedstone).idDroppedOnConversion(difficulty, metadata))
                .setMetadataDroppedOnConversion(metadata -> ((OreBlockStaged) Block.oreRedstone).damageDroppedOnConversion(metadata))
                .setQuantityDropped(rand -> Block.oreRedstone.quantityDropped(rand));
        register(redstoneOre, stoneTypeNames);

        lapisOre = new BTWGOreBlock(BTWGBlockIDs.LAPIS_ORE_ID,
                2,
                Item.dyePowder.itemID, Color.BLUE.colorID,
                "lapis",
                stoneTypes)
                .setIDDroppedOnConversion((difficulty, metadata) -> ((OreBlockStaged) Block.oreLapis).idDroppedOnConversion(difficulty, metadata))
                .setMetadataDroppedOnConversion(metadata -> ((OreBlockStaged) Block.oreLapis).damageDroppedOnConversion(metadata))
                .setQuantityDropped(rand -> Block.oreLapis.quantityDropped(rand));
        register(redstoneOre, stoneTypeNames);
    }

    private static void register(Block block, String[] names) {
        Item.itemsList[block.blockID] = ((ItemInterface) new ItemMultiTextureTile(block.blockID - 256, block, names)).btwg$setModNamespace(BetterThanWorldGen.NAME);
    }

    private static void register(ItemBlock blockItem) {
        Item.itemsList[blockItem.itemID] = ((ItemInterface) blockItem).btwg$setModNamespace(BetterThanWorldGen.NAME);
    }

    private static void register(Block block) {
        Item.itemsList[block.blockID] = ((ItemInterface) new ItemBlock(block.blockID - 256)).btwg$setModNamespace(BetterThanWorldGen.NAME);
    }
}
