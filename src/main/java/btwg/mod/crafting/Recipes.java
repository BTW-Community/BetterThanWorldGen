package btwg.mod.crafting;

import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;
import btw.item.tag.TagInstance;
import btw.item.tag.TagOrStack;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.ItemStack;

public class Recipes {
    public static void initRecipes() {
        initSoilRecipes();
    }

    private static void initSoilRecipes() {
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.earthenClay, new ItemStack(BTWGBlocks.looseEarthenClay));
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.earthenClay, new TagOrStack[] {
                new ItemStack(BTWItems.dirtPile, 8),
                new TagInstance(Tags.CLAY_BALLS, 1),
        });
        RecipeManager.addShapelessRecipe(new ItemStack(BTWGBlocks.looseEarthenClay), new Object[] {
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
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.sandyDirt, new ItemStack[] {
                new ItemStack(BTWItems.dirtPile, 4),
                new ItemStack(BTWItems.sandPile, 4)
        });
        RecipeManager.addShapelessRecipe(new ItemStack(BTWGBlocks.looseSandyDirt), new Object[] {
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
}
