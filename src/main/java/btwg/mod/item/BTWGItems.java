package btwg.mod.item;

import btw.crafting.util.FurnaceBurnTime;
import btw.item.BTWItems;
import btwg.api.block.StoneType;
import btwg.api.block.WoodType;
import btwg.api.item.items.BTWGDoorItem;
import btwg.api.item.items.MultiItem;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

import java.util.Arrays;

import static net.minecraft.src.Item.*;

public class BTWGItems {
    public static Item bark;
    public static Item door;

    public static Item rock;
    public static Item stoneBrick;
    public static Item gravelPile;
    public static Item dirtPile;

    public static void initItems() {
        initBTWGItems();

        BTWItems.dirtPile.setTextureName(BetterThanWorldGen.MODID + ":rhyolite_regolith_pile");
    }

    private static void initBTWGItems() {
        bark = new MultiItem(BTWGItemIDs.BARK_ID, "bark",
                new String[] {
                        WoodType.ACACIA.name(),
                        WoodType.DARK_OAK.name(),
                        WoodType.MANGROVE.name(),
                        WoodType.CHERRY.name(),
                        WoodType.PALE_OAK.name(),
                })
                .setBuoyant()
                .setBellowsBlowDistance(2)
                .setfurnaceburntime(FurnaceBurnTime.KINDLING)
                .setFilterableProperties(FILTERABLE_SMALL | FILTERABLE_THIN)
                .setCreativeTab(CreativeTabs.tabMaterials);

        door = new BTWGDoorItem(BTWGItemIDs.DOOR_ID,
                new WoodType[] {
                        WoodType.SPRUCE,
                        WoodType.BIRCH,
                        WoodType.JUNGLE,
                        WoodType.BLOOD_WOOD,
                        WoodType.ACACIA,
                        WoodType.DARK_OAK,
                        WoodType.MANGROVE,
                        WoodType.CHERRY,
                        WoodType.PALE_OAK,
                });

        String[] rockStoneTypes = Arrays.stream(StoneType.STONE_TYPES)
                .filter(stoneType -> stoneType != StoneType.RHYOLITE
                        && stoneType != StoneType.SLATE
                        && stoneType != StoneType.GABBRO)
                .map(StoneType::name)
                .toArray(String[]::new);

        rock = new MultiItem(BTWGItemIDs.ROCK_ID, "rock", rockStoneTypes)
                .setFilterableProperties(FILTERABLE_SMALL)
                .setCreativeTab(CreativeTabs.tabMaterials);

        stoneBrick = new MultiItem(BTWGItemIDs.STONE_BRICK_ID, "brick", rockStoneTypes)
                .setCreativeTab(CreativeTabs.tabMaterials);

        String[] soilStoneTypes = Arrays.stream(StoneType.STONE_TYPES)
                .filter(stoneType -> stoneType != StoneType.RHYOLITE)
                .map(StoneType::name)
                .toArray(String[]::new);

        dirtPile = new MultiItem(BTWGItemIDs.DIRT_PILE_ID, "regolith_pile", soilStoneTypes)
                .setFilterableProperties(FILTERABLE_FINE)
                .setCreativeTab(CreativeTabs.tabMaterials);

        gravelPile = new MultiItem(BTWGItemIDs.GRAVEL_PILE_ID, "gravel_pile", soilStoneTypes)
                .setFilterableProperties(FILTERABLE_FINE)
                .setCreativeTab(CreativeTabs.tabMaterials);
    }
}
