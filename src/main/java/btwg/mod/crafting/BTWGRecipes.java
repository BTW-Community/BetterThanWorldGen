package btwg.mod.crafting;

import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;
import btw.item.tag.Tag;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

public class BTWGRecipes {
    public static void initRecipes() {
        initSoilRecipes();
    }

    private static void initSoilRecipes() {
        RecipeManager.addPistonPackingRecipe(BTWGBlocks.earthenClay, new ItemStack(BTWGBlocks.looseEarthenClay));
        RecipeManager.addShapelessRecipe(new ItemStack(BTWGBlocks.looseEarthenClay), new Object[] {
                new ItemStack(BTWItems.dirtPile, 8),
                Tag.of(new ResourceLocation("btwg:clay_balls"), Item.clay, BTWItems.netherSludge)
        });
    }
}
