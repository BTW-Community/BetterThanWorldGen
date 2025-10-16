package btwg.mod.crafting;

import btw.block.BTWBlocks;
import btw.block.blocks.MouldingAndDecorativeBlock;
import btw.block.blocks.SidingAndCornerAndDecorativeBlock;
import btw.crafting.manager.MillStoneCraftingManager;
import btw.crafting.recipe.RecipeManager;
import btw.crafting.recipe.types.customcrafting.ConditionalRecipe;
import btw.inventory.util.InventoryUtils;
import btw.item.BTWItems;
import btw.item.tag.BTWTags;
import btw.item.tag.Tag;
import btw.item.tag.TagInstance;
import btw.item.tag.TagOrStack;
import btw.util.color.Color;
import btw.world.util.difficulty.DifficultyParam;
import btwg.api.block.StoneType;
import btwg.mod.block.BTWGBlocks;
import btwg.api.block.WoodType;
import btwg.mod.item.Tags;
import net.minecraft.src.*;

import java.util.Arrays;

public class Recipes {
    public static void initRecipes() {
        initTorchRecipes();
        initSoilRecipes();
        initDyeRecipes();
        initWoodRecipes();
        initStoneRecipes();
    }

    private static void initTorchRecipes() {
        // Remove BTW torch recipes
        RecipeManager.removeVanillaRecipe(new ItemStack(BTWBlocks.infiniteUnlitTorch, 1), new Object[]{
                "#",
                "X",
                '#', BTWItems.nethercoal,
                'X', Item.stick
        });
        RecipeManager.removeVanillaRecipe(new ItemStack(BTWBlocks.finiteUnlitTorch), new Object[]{
                "#",
                "X",
                '#', TagInstance.of(BTWTags.coals),
                'X', Item.stick
        });
        RecipeManager.removeVanillaRecipe(new ItemStack(BTWBlocks.infiniteUnlitTorch), new Object[]{
                "#",
                "X",
                '#', TagInstance.of(BTWTags.coals),
                'X', Item.stick
        });

        // Readd recipes with higher quantities
        RecipeManager.addRecipe(new ItemStack(BTWBlocks.infiniteUnlitTorch, 4), new Object[]{
                "#",
                "X",
                '#', BTWItems.nethercoal,
                'X', Item.stick
        });

        ShapedRecipes crudeTorchRecipe = CraftingManager.getInstance().createRecipe(new ItemStack(BTWBlocks.finiteUnlitTorch, 4), new Object[]{
                "#",
                "X",
                '#', TagInstance.of(BTWTags.coals),
                'X', Item.stick
        });

        ShapedRecipes infiniteTorchFromCoalRecipe = CraftingManager.getInstance().createRecipe(new ItemStack(BTWBlocks.infiniteUnlitTorch, 4), new Object[]{
                "#",
                "X",
                '#', TagInstance.of(BTWTags.coals),
                'X', Item.stick
        });

        CraftingManager.getInstance().getRecipeList().add(new ConditionalRecipe(crudeTorchRecipe, world -> !world.getDifficultyParameter(DifficultyParam.CanCraftTorchesFromCoal.class)));
        CraftingManager.getInstance().getRecipeList().add(new ConditionalRecipe(infiniteTorchFromCoalRecipe, world -> world.getDifficultyParameter(DifficultyParam.CanCraftTorchesFromCoal.class)));
    }

    private static void initSoilRecipes() {
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.earthenClay, new ItemStack(BTWGBlocks.looseEarthenClay));
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.earthenClay, new TagOrStack[] {
                new TagInstance(Tags.DIRT_PILES, 4),
                TagInstance.of(Tags.CLAY_BALLS),
        });
        RecipeManager.addShapelessRecipe(new ItemStack(BTWGBlocks.looseEarthenClay), new Object[] {
                Tags.DIRT_PILES,
                Tags.DIRT_PILES,
                Tags.DIRT_PILES,
                Tags.DIRT_PILES,
                Tags.CLAY_BALLS,
                Tags.DIRT_PILES,
                Tags.DIRT_PILES,
                Tags.DIRT_PILES,
                Tags.DIRT_PILES,
        });

        RecipeManager.addPistonPackingRecipe(BTWGBlocks.sandyDirt, new ItemStack(BTWGBlocks.looseSandyDirt));
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.sandyDirt, new TagOrStack[] {
                new TagInstance(Tags.DIRT_PILES, 4),
                new TagInstance(Tags.SAND_PILES, 4),
        });
        RecipeManager.addShapelessRecipe(new ItemStack(BTWGBlocks.looseSandyDirt), new Object[] {
                TagInstance.of(Tags.DIRT_PILES),
                TagInstance.of(Tags.DIRT_PILES),
                TagInstance.of(Tags.DIRT_PILES),
                TagInstance.of(Tags.DIRT_PILES),
                TagInstance.of(Tags.SAND_PILES),
                TagInstance.of(Tags.SAND_PILES),
                TagInstance.of(Tags.SAND_PILES),
                TagInstance.of(Tags.SAND_PILES),
        });
    }

    private static void initDyeRecipes() {
        MillStoneCraftingManager.getInstance().removeRecipe(new ItemStack(Item.dyePowder, 2, Color.RED.colorID), new ItemStack(Block.plantRed));
        MillStoneCraftingManager.getInstance().removeRecipe(new ItemStack(Item.dyePowder, 2, Color.YELLOW.colorID), new ItemStack(Block.plantYellow));

        // TODO: Split white, black, and blue dye from their functional items
        Arrays.stream(Color.values()).forEach(color -> {
            Item output = Item.dyePowder;

            Tag flowerTag = Tags.FLOWERS_BY_COLOR[color.colorID];
            if (!flowerTag.getItems().isEmpty()) {
                RecipeManager.addMillStoneRecipe(new ItemStack(output, 2, color.colorID),
                        TagInstance.of(flowerTag));
            }

            Tag tallFlowerTag = Tags.TALL_FLOWERS_BY_COLOR[color.colorID];
            if (!tallFlowerTag.getItems().isEmpty()) {
                RecipeManager.addMillStoneRecipe(new ItemStack(output, 3, color.colorID),
                        TagInstance.of(tallFlowerTag));
            }
        });
    }

    private static void initWoodRecipes() {
        initWoodRecipesForType(WoodType.ACACIA);
        initWoodRecipesForType(WoodType.DARK_OAK);
        initWoodRecipesForType(WoodType.MANGROVE);
        initWoodRecipesForType(WoodType.CHERRY);
        initWoodRecipesForType(WoodType.PALE_OAK);

        // Remove generic door and trapdoor recipes
        RecipeManager.removeVanillaRecipe(new ItemStack(Item.doorWood, 1 ), new Object[] {
                "##",
                "##",
                "##",
                '#', Block.planks
        });
        RecipeManager.removeVanillaRecipe(new ItemStack(Item.doorWood, 1 ), new Object[] {
                "##",
                "##",
                "##",
                '#', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA)
        });

        RecipeManager.removeVanillaRecipe(new ItemStack(Block.trapdoor),
                new Object[]{
                        "PPS",
                        "PPS",
                        'S', Item.stick,
                        'P', Block.planks
                });
        RecipeManager.removeVanillaRecipe(new ItemStack(Block.trapdoor, 2),
                new Object[]{
                        "PPS",
                        "PPS",
                        'S', Item.stick,
                        'P', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA)
                });

        initVanillaWoodTypeRecipes(0, Item.doorWood.itemID, 0, Block.trapdoor.blockID);
        initVanillaWoodTypeRecipes(1, WoodType.SPRUCE.doorItemID(), WoodType.SPRUCE.doorItemMetadata(), WoodType.SPRUCE.trapdoorID());
        initVanillaWoodTypeRecipes(2, WoodType.BIRCH.doorItemID(), WoodType.BIRCH.doorItemMetadata(), WoodType.BIRCH.trapdoorID());
        initVanillaWoodTypeRecipes(3, WoodType.JUNGLE.doorItemID(), WoodType.JUNGLE.doorItemMetadata(), WoodType.JUNGLE.trapdoorID());
        initVanillaWoodTypeRecipes(4, WoodType.BLOOD_WOOD.doorItemID(), WoodType.BLOOD_WOOD.doorItemMetadata(), WoodType.BLOOD_WOOD.trapdoorID());
    }

    private static void initVanillaWoodTypeRecipes(int plankMetadata, int doorID, int doorMetadata, int trapdoorID) {
        RecipeManager.addRecipe(new ItemStack(doorID, 1, doorMetadata),
                new Object[]{
                        "##",
                        "##",
                        "##",
                        '#', new ItemStack(Block.planks, 1, plankMetadata)
                });
        RecipeManager.addRecipe(new ItemStack(trapdoorID, 1, 0),
                new Object[]{
                        "PPS",
                        "PPS",
                        'S', Item.stick,
                        'P', new ItemStack(Block.planks, 1, plankMetadata)
                });

        RecipeManager.addRecipe(new ItemStack(doorID, 1, doorMetadata),
                new Object[]{
                        "##",
                        "##",
                        "##",
                        '#', new ItemStack(BTWItems.woodSidingStubID, 1, plankMetadata)
                });
        RecipeManager.addRecipe(new ItemStack(trapdoorID, 2, 0),
                new Object[]{
                        "PPS",
                        "PPS",
                        'S', Item.stick,
                        'P', new ItemStack(BTWItems.woodSidingStubID, 1, plankMetadata)
                });
    }

    private static void initWoodRecipesForType(WoodType woodType) {
        // Planks
        RecipeManager.addLogChoppingRecipe(new ItemStack(woodType.plankID(), 2, woodType.plankMetadata()),
                new ItemStack[]{
                        new ItemStack(woodType.barkID(), 1, woodType.barkMetadata()),
                        new ItemStack(BTWItems.sawDust, 2, 0),
                },
                new ItemStack(Item.stick, 2),
                new ItemStack[]{
                        new ItemStack(woodType.barkID(), 1, woodType.barkMetadata()),
                        new ItemStack(BTWItems.sawDust, 4, 0),
                },
                new ItemStack(woodType.logID(), 1, woodType.logMetadata())
        );

        RecipeManager.addSawRecipe(new ItemStack(woodType.plankID(), 4, woodType.plankMetadata()),
                Block.blocksList[woodType.logID()]);

        // Stairs
        RecipeManager.addRecipe(new ItemStack(woodType.stairsID(), 6, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(woodType.plankID(), 1, woodType.plankMetadata())
        });
        RecipeManager.addRecipe(new ItemStack(woodType.stairsID(), 1, 0), new Object[] {
                "#  ",
                "## ",
                '#', new ItemStack(woodType.mouldingID(), 1, 0)
        });

        // Slab
        RecipeManager.addRecipe(new ItemStack(woodType.slabID(), 6, woodType.slabMetadata()), new Object[] {
                "###",
                '#', new ItemStack(woodType.plankID(), 1, woodType.plankMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(woodType.plankID(), 1, woodType.plankMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(woodType.slabID(), 1, woodType.slabMetadata())
        });

        // Subblocks
        initWoodSubBlockRecipesOfType(
                woodType.plankID(),
                woodType.plankMetadata(),
                woodType.sidingAndCornerID(),
                woodType.mouldingID()
        );

        RecipeManager.addSubBlockRecipesToSaw(
                Block.blocksList[woodType.plankID()], woodType.plankMetadata(),
                Block.blocksList[woodType.sidingAndCornerID()],
                Block.blocksList[woodType.mouldingID()]
        );

        RecipeManager.addSawRecipe(new ItemStack(woodType.mouldingID(), 2, 0),
                Block.blocksList[woodType.slabID()], woodType.slabMetadata());

        RecipeManager.addSawRecipe(new ItemStack[] {
                        new ItemStack(woodType.mouldingID(), 1, 0),
                        new ItemStack(woodType.sidingAndCornerID(), 1, 0)
                },
                Block.blocksList[woodType.stairsID()]);

        // Door
        RecipeManager.addRecipe(new ItemStack(woodType.doorItemID(), 1, woodType.doorItemMetadata()),
                new Object[]{
                        "##",
                        "##",
                        "##",
                        '#', new ItemStack(woodType.plankID(), 1, woodType.plankMetadata())
                });
        RecipeManager.addRecipe(new ItemStack(woodType.doorItemID(), 1, woodType.doorItemMetadata()),
                new Object[]{
                        "##",
                        "##",
                        "##",
                        '#', new ItemStack(woodType.sidingAndCornerID(), 1, 0)
                });

        // Trapdoor
        RecipeManager.addRecipe(new ItemStack(woodType.trapdoorID(), 1, 0),
                new Object[]{
                        "PPS",
                        "PPS",
                        'S', Item.stick,
                        'P', new ItemStack(woodType.plankID(), 1, woodType.plankMetadata())
                });
        RecipeManager.addRecipe(new ItemStack(woodType.trapdoorID(), 2, 0),
                new Object[]{
                        "PPS",
                        "PPS",
                        'S', Item.stick,
                        'P', new ItemStack(woodType.sidingAndCornerID(), 1, 0)
                });
    }

    public static void initWoodSubBlockRecipesOfType(int fullBlockID, int fullBlockMetadata, int sidingAndCornerBlockID, int mouldingBlockID) {
        // Subblock recipes
        RecipeManager.addRecipe(new ItemStack(mouldingBlockID, 6, MouldingAndDecorativeBlock.SUBTYPE_PEDESTAL_UP), new Object[] {
                " S ",
                "###",
                "###",
                '#', new ItemStack(fullBlockID, 1, fullBlockMetadata),
                'S', new ItemStack(sidingAndCornerBlockID, 8, 0)
        });

        RecipeManager.addRecipe(new ItemStack(mouldingBlockID, 4, MouldingAndDecorativeBlock.SUBTYPE_TABLE), new Object[] {
                "###",
                " X ",
                " X ",
                '#', new ItemStack(sidingAndCornerBlockID, 1, 0),
                'X', new ItemStack(mouldingBlockID, 1, 0)
        });

        RecipeManager.addRecipe(new ItemStack(sidingAndCornerBlockID, 4, SidingAndCornerAndDecorativeBlock.SUBTYPE_BENCH), new Object[] {
                "###",
                " X ",
                '#', new ItemStack(sidingAndCornerBlockID, 1, 0),
                'X', new ItemStack(mouldingBlockID, 1, 0)
        });

        RecipeManager.addRecipe(new ItemStack(sidingAndCornerBlockID, 6, SidingAndCornerAndDecorativeBlock.SUBTYPE_FENCE), new Object[] {
                "###",
                "###",
                '#', new ItemStack(fullBlockID, 1, fullBlockMetadata)
        });

        RecipeManager.addRecipe(new ItemStack(sidingAndCornerBlockID, 2, SidingAndCornerAndDecorativeBlock.SUBTYPE_FENCE), new Object[] {
                "###",
                '#', new ItemStack(mouldingBlockID, 1, 0)
        });

        // Combining recipes
        RecipeManager.addShapelessRecipe(new ItemStack(fullBlockID, 1, fullBlockMetadata), new Object[] {
                new ItemStack(sidingAndCornerBlockID, 1, 0),
                new ItemStack(sidingAndCornerBlockID, 1, 0)
        });

        RecipeManager.addShapelessRecipe(new ItemStack(sidingAndCornerBlockID, 1, 0), new Object[] {
                new ItemStack(mouldingBlockID, 1, 0),
                new ItemStack(mouldingBlockID, 1, 0)
        });

        RecipeManager.addShapelessRecipe(new ItemStack(mouldingBlockID, 1, 0), new Object[] {
                new ItemStack(sidingAndCornerBlockID, 1, 1),
                new ItemStack(sidingAndCornerBlockID, 1, 1)
        });
    }

    private static void initStoneRecipes() {
        Arrays.stream(StoneType.STONE_TYPES)
                .filter(type -> type != StoneType.RHYOLITE
                        && type != StoneType.SLATE
                        && type != StoneType.GABBRO)
                .forEach(Recipes::initStoneRecipesForType);

        initStoneSoilRecipesForType(StoneType.SLATE);
        initStoneSoilRecipesForType(StoneType.GABBRO);
    }

    private static void initStoneRecipesForType(StoneType type) {

        //------ Smooth Stone ------//

        RecipeManager.addRecipe(new ItemStack(type.stoneID(), 1, type.stoneMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(type.stoneSlabID(), 1, type.stoneSlabMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.stoneSlabID(), 6, type.stoneSlabMetadata()), new Object[] {
                "###",
                '#', new ItemStack(type.stoneID(), 1, type.stoneMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.stoneStairsID(), 6, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(type.stoneID(), 1, type.stoneMetadata())
        });

        //------ Loose Cobblestone ------//

        RecipeManager.addShapelessRecipe(new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata()), new Object[] {
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.looseCobblestoneSlabID(), 1, type.looseCobblestoneSlabMetadata()), new Object[] {
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
                new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
        });

        RecipeManager.addRecipe(new ItemStack(type.looseCobblestoneStairsID(), 1, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.rockItemID(), 8, type.rockItemMetadata()), new Object[] {
                new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata())
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.rockItemID(), 4, type.rockItemMetadata()), new Object[] {
                new ItemStack(type.looseCobblestoneSlabID(), 1, type.looseCobblestoneSlabMetadata())
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.rockItemID(), 6, type.rockItemMetadata()), new Object[] {
                new ItemStack(type.looseCobblestoneStairsID(), 1, 0)
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.rockItemID(), 2, type.rockItemMetadata()), new Object[] {
                BTWTags.chisels,
                new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
        });

        RecipeManager.addRecipe(new ItemStack(type.looseCobblestoneSlabID(), 4, type.looseCobblestoneSlabMetadata()), new Object[] {
                "##",
                '#', new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseCobblestoneStairsID(), 4, 0), new Object[] {
                "# ",
                "##",
                '#', new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseCobblestoneStairsID(), 8, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(type.looseCobblestoneSlabID(), 1, type.looseCobblestoneSlabMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseCobblestoneStairsID(), 2, 0), new Object[] {
                "# ",
                "##",
                '#', new ItemStack(type.looseCobblestoneSlabID(), 1, type.looseCobblestoneSlabMetadata())
        });

        RecipeManager.addPistonPackingRecipe(Block.blocksList[type.looseCobblestoneID()], type.looseCobblestoneMetadata(),
                new ItemStack(type.rockItemID(), 8, type.rockItemMetadata())
        );

        //------ Cobblestone ------//

        RecipeManager.addRecipe(new ItemStack(type.cobblestoneID(), 1, type.cobblestoneMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(type.cobblestoneSlabID(), 1, type.cobblestoneSlabMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.cobblestoneSlabID(), 6, type.cobblestoneSlabMetadata()), new Object[] {
                "###",
                '#', new ItemStack(type.cobblestoneID(), 1, type.cobblestoneMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.cobblestoneStairsID(), 6, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(type.cobblestoneID(), 1, type.cobblestoneMetadata())
        });

        //------ Loose Stone Bricks ------//

        RecipeManager.addShapelessRecipe(new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata()), new Object[] {
                new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
                new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
                new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
                new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.looseStoneBrickSlabID(), 1, type.looseStoneBrickSlabMetadata()), new Object[] {
                new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
                new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
        });

        RecipeManager.addRecipe(new ItemStack(type.looseStoneBrickStairsID(), 1, 0), new Object[] {
                "# ",
                "##",
                '#', new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
        });

        RecipeManager.addRecipe(new ItemStack(type.looseStoneBrickStairsID(), 2, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.stoneBrickItemID(), 4, type.stoneBrickItemMetadata()), new Object[] {
                new ItemStack(type.looseStoneBrickID(), 1, type.looseStoneBrickMetadata())
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.stoneBrickItemID(), 2, type.stoneBrickItemMetadata()), new Object[] {
                new ItemStack(type.looseStoneBrickSlabID(), 1, type.looseStoneBrickSlabMetadata())
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.stoneBrickItemID(), 3, type.stoneBrickItemMetadata()), new Object[] {
                new ItemStack(type.looseStoneBrickStairsID(), 1, 0)
        });

        RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(type.stoneBrickItemID(), 4, type.stoneBrickItemMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new Object[] {
                        BTWTags.chisels,
                        new ItemStack(type.stoneID(), 1, type.stoneMetadata()),
                });

        RecipeManager.addRecipe(new ItemStack(type.looseStoneBrickSlabID(), 4, type.looseStoneBrickSlabMetadata()), new Object[] {
                "##",
                '#', new ItemStack(type.looseStoneBrickID(), 1, type.looseStoneBrickMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseStoneBrickStairsID(), 4, 0), new Object[] {
                "# ",
                "##",
                '#', new ItemStack(type.looseStoneBrickID(), 1, type.looseStoneBrickMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseStoneBrickStairsID(), 8, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(type.looseStoneBrickID(), 1, type.looseStoneBrickMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseStoneBrickID(), 1, type.looseStoneBrickMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(type.looseStoneBrickSlabID(), 1, type.looseStoneBrickSlabMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseStoneBrickStairsID(), 2, 0), new Object[] {
                "# ",
                "##",
                '#', new ItemStack(type.looseStoneBrickSlabID(), 1, type.looseStoneBrickSlabMetadata())
        });

        RecipeManager.addPistonPackingRecipe(Block.blocksList[type.looseStoneBrickID()], type.looseStoneBrickMetadata(),
                new ItemStack(type.stoneBrickItemID(), 4, type.stoneBrickItemMetadata())
        );

        //------ Stone Bricks ------//

        RecipeManager.addRecipe(new ItemStack(type.stoneBrickID(), 1, type.stoneBrickMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(type.stoneBrickSlabID(), 1, type.stoneBrickSlabMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.stoneBrickSlabID(), 6, type.stoneBrickSlabMetadata()), new Object[] {
                "###",
                '#', new ItemStack(type.stoneBrickID(), 1, type.stoneBrickMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.stoneBrickStairsID(), 6, 0), new Object[] {
                "#  ",
                "## ",
                "###",
                '#', new ItemStack(type.stoneBrickID(), 1, type.stoneBrickMetadata())
        });

        RecipeManager.addSoulforgeRecipe(new ItemStack(type.chiseledStoneBrickID(), 12, type.chiseledStoneBrickMetadata()), new Object[] {
                "####",
                "#  #",
                "#  #",
                "####",
                '#', new ItemStack(type.stoneBrickID(), 1, type.stoneBrickMetadata()),
        });

        initStoneSoilRecipesForType(type);
    }

    private static void initStoneSoilRecipesForType(StoneType type) {

        //------ Regolith ------//

        RecipeManager.addShapelessRecipe(new ItemStack(type.looseDirtID(), 1, type.looseDirtMetadata()), new Object[] {
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.looseDirtSlabID(), 1, type.looseDirtSlabMetadata()), new Object[] {
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
                new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.dirtPileID(), 8, type.dirtPileMetadata()), new Object[] {
                new ItemStack(type.looseDirtID(), 1, type.looseDirtMetadata())
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.dirtPileID(), 4, type.dirtPileMetadata()), new Object[] {
                new ItemStack(type.looseDirtSlabID(), 1, type.looseDirtSlabMetadata())
        });

        RecipeManager.addPistonPackingRecipe(Block.blocksList[type.dirtID()], type.dirtMetadata(),
                new ItemStack(type.dirtPileID(), 8, type.dirtPileMetadata())
        );

        RecipeManager.addRecipe(new ItemStack(type.looseDirtSlabID(), 4, type.looseDirtSlabMetadata()), new Object[] {
                "##",
                '#', new ItemStack(type.looseDirtID(), 1, type.looseDirtMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.looseDirtID(), 1, type.looseDirtMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(type.looseDirtSlabID(), 1, type.looseDirtSlabMetadata())
        });

        //------ Gravel ------//

        RecipeManager.addShapelessRecipe(new ItemStack(type.gravelID(), 1, type.gravelMetadata()), new Object[] {
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.gravelSlabID(), 1, type.gravelSlabMetadata()), new Object[] {
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
                new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.gravelPileID(), 8, type.gravelPileMetadata()), new Object[] {
                new ItemStack(type.gravelID(), 1, type.gravelMetadata())
        });

        RecipeManager.addShapelessRecipe(new ItemStack(type.gravelPileID(), 4, type.gravelPileMetadata()), new Object[] {
                new ItemStack(type.gravelSlabID(), 1, type.gravelSlabMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.gravelSlabID(), 4, type.gravelSlabMetadata()), new Object[] {
                "##",
                '#', new ItemStack(type.gravelID(), 1, type.gravelMetadata())
        });

        RecipeManager.addRecipe(new ItemStack(type.gravelID(), 1, type.gravelMetadata()), new Object[] {
                "#",
                "#",
                '#', new ItemStack(type.gravelSlabID(), 1, type.gravelSlabMetadata())
        });

        RecipeManager.addHopperFilteringRecipe(new ItemStack(Block.sand),
                new ItemStack(Item.flint),
                new ItemStack(type.gravelID(), 1, type.gravelMetadata()),
                new ItemStack(BTWBlocks.wickerPane));
    }
}
