package btwg.mod.crafting;

import btw.block.BTWBlocks;
import btw.item.BTWItems;
import btwg.api.block.StoneType;
import btwg.api.block.WoodType;
import emi.dev.emi.emi.api.EmiEntrypoint;
import emi.dev.emi.emi.api.EmiPlugin;
import emi.dev.emi.emi.api.EmiRegistry;
import emi.dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import emi.dev.emi.emi.api.stack.EmiFistStack;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.shims.java.net.minecraft.text.Text;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EmiEntrypoint
public class BTWGEMIPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {

        //------ Log Chiseling ------//

        List<EmiStack> logChisels = new ArrayList<>();
        logChisels.add(EmiFistStack.HAND);
        logChisels.add(EmiStack.of(BTWItems.sharpStone));
        logChisels.add(EmiStack.of(BTWItems.ironChisel));
        logChisels.add(EmiStack.of(BTWItems.diamondChisel));

        List<ItemStack> woodTypes = new ArrayList<>();
        woodTypes.add(new ItemStack(WoodType.ACACIA.plankID(), 1, WoodType.ACACIA.plankMetadata()));
        woodTypes.add(new ItemStack(WoodType.DARK_OAK.plankID(), 1, WoodType.DARK_OAK.plankMetadata()));
        woodTypes.add(new ItemStack(WoodType.MANGROVE.plankID(), 1, WoodType.MANGROVE.plankMetadata()));
        woodTypes.add(new ItemStack(WoodType.CHERRY.plankID(), 1, WoodType.CHERRY.plankMetadata()));
        woodTypes.add(new ItemStack(WoodType.PALE_OAK.plankID(), 1, WoodType.PALE_OAK.plankMetadata()));

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/shaft_from_chiseling"))
                .leftInput(EmiIngredient.of(logChisels.stream().toList()))
                .rightInput(EmiIngredient.of(woodTypes.stream().map(EmiStack::of).toList()), false, sw -> {
                    sw.appendTooltip(Text.translatable("emi.world_interaction.btw.shaft_from_chiseling"));
                    return sw;
                }).output(EmiStack.of(Item.stick)).supportsRecipeTree(true).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/sawdust_from_chiseling"))
                .leftInput(EmiIngredient.of(logChisels.stream().toList())).rightInput(EmiIngredient.of(woodTypes.stream().map(EmiStack::of).toList()), false, sw -> {
                    sw.appendTooltip(Text.translatable("emi.world_interaction.btw.sawdust_from_chiseling"));
                    return sw;
                }).output(EmiStack.of(BTWItems.sawDust)).supportsRecipeTree(true).build());

        addBarkInWorldRecipe(registry, logChisels, WoodType.ACACIA);
        addBarkInWorldRecipe(registry, logChisels, WoodType.DARK_OAK);
        addBarkInWorldRecipe(registry, logChisels, WoodType.MANGROVE);
        addBarkInWorldRecipe(registry, logChisels, WoodType.CHERRY);
        addBarkInWorldRecipe(registry, logChisels, WoodType.PALE_OAK);

        //------ Stone Chiseling ------//

        List<EmiStack> stoneChisels = new ArrayList<>();
        stoneChisels.add(EmiStack.of(BTWItems.pointyStick));
        stoneChisels.add(EmiStack.of(BTWItems.sharpStone));

        List<EmiStack> ironChisels = new ArrayList<>();
        ironChisels.add(EmiStack.of(BTWItems.ironChisel));
        ironChisels.add(EmiStack.of(BTWItems.diamondChisel));

        List<EmiStack> diamondChisels = new ArrayList<>();
        diamondChisels.add(EmiStack.of(BTWItems.diamondChisel));

        Arrays.stream(StoneType.STONE_TYPES)
                .filter(type -> type != StoneType.RHYOLITE
                        && type != StoneType.SLATE
                        && type != StoneType.GABBRO)
                .forEach(type -> {
                    if (type.strata() == 0) {
                        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/" + type.name() + "_rock_from_chiseling"))
                                .leftInput(EmiIngredient.of(stoneChisels.stream().toList())).rightInput(EmiStack.of(new ItemStack(type.stoneID(), 1, type.stoneMetadata())), false)
                                .output(EmiStack.of(new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()))).supportsRecipeTree(true).build());
                    }

                    if (type.strata() == 2) {
                        addBrickInWorldRecipe(registry, diamondChisels, type);
                    }
                    else {
                        addBrickInWorldRecipe(registry, ironChisels, type);
                    }
                });

        //------ Mossy Cobblestone ------//

        Arrays.stream(StoneType.STONE_TYPES)
                .filter(type -> type != StoneType.RHYOLITE
                        && type != StoneType.SLATE
                        && type != StoneType.GABBRO)
                .forEach(type -> {
                    registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/mossy_cobbled_" + type.name()))
                            .leftInput(EmiStack.of(new ItemStack(type.cobblestoneID(), 1, type.cobblestoneMetadata()))).rightInput(EmiStack.of(Block.mobSpawner), false)
                            .output(EmiStack.of(new ItemStack(type.mossyCobblestoneID(), 1, type.mossyCobblestoneMetadata()))).supportsRecipeTree(true).build());
                    registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/mossy_cobbled_" + type.name() + "_from_loose"))
                            .leftInput(EmiStack.of(new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata()))).rightInput(EmiStack.of(Block.mobSpawner), false)
                            .output(EmiStack.of(new ItemStack(type.mossyCobblestoneID(), 1, type.mossyCobblestoneMetadata()))).supportsRecipeTree(true).build());
                });

        //------ Mossy Bricks ------//

        // TODO

        //------ Cracked Bricks ------//

        // TODO

        //------ Mortaring ------//

        Item[] mortars = {Item.clay, Item.slimeBall, BTWItems.netherSludge};
        String[] mortarNames = {"clay", "slime", "nether_sludge"};



        Arrays.stream(StoneType.STONE_TYPES)
                .filter(type -> type != StoneType.RHYOLITE
                        && type != StoneType.SLATE
                        && type != StoneType.GABBRO)
                .forEach(type -> {
                    for (int i = 0; i < mortars.length; i++) {
                        Item mortarItem = mortars[i];
                        String mortarName = mortarNames[i];

                        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/cobbled_" + type.name() + "_" + mortarName))
                                .leftInput(EmiStack.of(mortarItem)).rightInput(EmiStack.of(new ItemStack(type.looseCobblestoneID(), 1, type.looseCobblestoneMetadata())), false)
                                .output(EmiStack.of(new ItemStack(type.cobblestoneID(), 1, type.cobblestoneMetadata())))
                                .supportsRecipeTree(true).build());
                        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/cobbled_" + type.name() + "_slab_" + mortarName))
                                .leftInput(EmiStack.of(mortarItem)).rightInput(EmiStack.of(new ItemStack(type.looseCobblestoneSlabID(), 1, type.looseCobblestoneSlabMetadata())), false)
                                .output(EmiStack.of(new ItemStack(type.cobblestoneSlabID(), 1, type.cobblestoneSlabMetadata())))
                                .supportsRecipeTree(true).build());
                        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/cobbled_" + type.name() + "_stairs_" + mortarName))
                                .leftInput(EmiStack.of(mortarItem)).rightInput(EmiStack.of(new ItemStack(type.looseCobblestoneStairsID(), 1, 0)), false)
                                .output(EmiStack.of(new ItemStack(type.cobblestoneStairsID(), 1, 0)))
                                .supportsRecipeTree(true).build());

                        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/" + type.name() + "_bricks_" + mortarName))
                                .leftInput(EmiStack.of(mortarItem)).rightInput(EmiStack.of(new ItemStack(type.looseStoneBrickID(), 1, type.looseStoneBrickMetadata())), false)
                                .output(EmiStack.of(new ItemStack(type.stoneBrickID(), 1, type.stoneBrickMetadata())))
                                .supportsRecipeTree(true).build());
                        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/" + type.name() + "_brick_slab_" + mortarName))
                                .leftInput(EmiStack.of(mortarItem)).rightInput(EmiStack.of(new ItemStack(type.looseStoneBrickSlabID(), 1, type.looseStoneBrickSlabMetadata())), false)
                                .output(EmiStack.of(new ItemStack(type.stoneBrickSlabID(), 1, type.stoneBrickSlabMetadata())))
                                .supportsRecipeTree(true).build());
                        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/" + type.name() + "_brick_stairs_" + mortarName))
                                .leftInput(EmiStack.of(mortarItem)).rightInput(EmiStack.of(new ItemStack(type.looseStoneBrickStairsID(), 1, 0)), false)
                                .output(EmiStack.of(new ItemStack(type.stoneBrickStairsID(), 1, 0)))
                                .supportsRecipeTree(true).build());
                    }
                });
    }

    private void addBrickInWorldRecipe(EmiRegistry registry, List<EmiStack> diamondChisels, StoneType type) {
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/" + type.name() + "_brick_from_chiseling"))
                .leftInput(EmiIngredient.of(diamondChisels.stream().toList())).rightInput(EmiStack.of(new ItemStack(type.stoneID(), 1, type.stoneMetadata())), false)
                .output(EmiStack.of(new ItemStack(type.stoneBrickID(), 1, type.stoneBrickMetadata()))).supportsRecipeTree(true).build());
    }

    private void addBarkInWorldRecipe(EmiRegistry registry, List<EmiStack> logChisels, WoodType type) {
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new ResourceLocation("btwg", "/world/block_interaction/btw/" + type.name() + "_bark_from_chiseling"))
                .leftInput(EmiIngredient.of(logChisels.stream().toList())).rightInput(EmiStack.of(new ItemStack(type.logID(), 1, type.logMetadata())), false, sw -> {
                    sw.appendTooltip(Text.translatable("emi.world_interaction.btw.bark_from_chiseling"));
                    return sw;
                }).output(EmiStack.of(new ItemStack(type.barkID(), 1, type.barkMetadata()))).supportsRecipeTree(true).build());

    }
}
