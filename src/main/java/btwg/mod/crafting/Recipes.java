package btwg.mod.crafting;

import btw.block.blocks.MouldingAndDecorativeBlock;
import btw.block.blocks.SidingAndCornerAndDecorativeBlock;
import btw.crafting.recipe.RecipeManager;
import btw.inventory.util.InventoryUtils;
import btw.item.BTWItems;
import btw.item.tag.TagInstance;
import btw.item.tag.TagOrStack;
import btwg.mod.block.BTWGBlocks;
import btwg.api.block.WoodType;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class Recipes {
    public static void initRecipes() {
        initSoilRecipes();
        initWoodRecipes();
    }

    private static void initSoilRecipes() {
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.earthenClay, new ItemStack(BTWGBlocks.looseEarthenClay));
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.earthenClay, new TagOrStack[]{
                new ItemStack(BTWItems.dirtPile, 8),
                new TagInstance(Tags.CLAY_BALLS, 1),
        });
        RecipeManager.addShapelessRecipe(new ItemStack(BTWGBlocks.looseEarthenClay), new Object[]{
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                Tags.CLAY_BALLS,
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
        });

        RecipeManager.addPistonPackingRecipe(BTWGBlocks.sandyDirt, new ItemStack(BTWGBlocks.looseSandyDirt));
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.sandyDirt, new ItemStack[]{
                new ItemStack(BTWItems.dirtPile, 4),
                new ItemStack(BTWItems.sandPile, 4)
        });
        RecipeManager.addShapelessRecipe(new ItemStack(BTWGBlocks.looseSandyDirt), new Object[]{
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.dirtPile),
                new ItemStack(BTWItems.sandPile),
                new ItemStack(BTWItems.sandPile),
                new ItemStack(BTWItems.sandPile),
                new ItemStack(BTWItems.sandPile),
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
        addWoodSubBlockRecipesOfType(
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

    public static void addWoodSubBlockRecipesOfType(int fullBlockID, int fullBlockMetadata, int sidingAndCornerBlockID, int mouldingBlockID) {
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
}
