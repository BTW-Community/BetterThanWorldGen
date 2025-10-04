package btwg.mod.item;

import btw.crafting.util.FurnaceBurnTime;
import btwg.api.block.StoneType;
import btwg.api.block.WoodType;
import btwg.api.item.ItemInterface;
import btwg.api.item.items.BTWGDoorItem;
import btwg.api.item.items.MultiItem;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

import static net.minecraft.src.Item.*;

public class BTWGItems {
    public static Item bark;
    public static Item door;

    public static Item rock;
    public static Item stoneBrick;
    public static Item gravelPile;
    public static Item dirtPile;

    public static void initItems() {
        bark = ((ItemInterface) new MultiItem(BTWGItemIDs.BARK_ID, "bark",
                new String[] {
                        WoodType.ACACIA.name(),
                        WoodType.DARK_OAK.name(),
                        WoodType.MANGROVE.name(),
                        WoodType.CHERRY.name(),
                        WoodType.PALE_OAK.name(),
                }))
                .btwg$setModNamespace(BetterThanWorldGen.NAME)
                .setBuoyant()
                .setBellowsBlowDistance(2)
                .setfurnaceburntime(FurnaceBurnTime.KINDLING)
                .setFilterableProperties(FILTERABLE_SMALL | FILTERABLE_THIN)
                .setCreativeTab(CreativeTabs.tabMaterials);

        door = ((ItemInterface) new BTWGDoorItem(BTWGItemIDs.DOOR_ID,
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
                }))
                .btwg$setModNamespace(BetterThanWorldGen.NAME);

        rock = ((ItemInterface) new MultiItem(BTWGItemIDs.ROCK_ID, "rock",
                new String[] {
                        StoneType.LIMESTONE.name()
                }))
                .btwg$setModNamespace(BetterThanWorldGen.NAME)
                .setFilterableProperties(FILTERABLE_SMALL)
                .setCreativeTab(CreativeTabs.tabMaterials);

        stoneBrick = ((ItemInterface) new MultiItem(BTWGItemIDs.STONE_BRICK_ID, "brick",
                new String[] {
                        StoneType.LIMESTONE.name()
                }))
                .btwg$setModNamespace(BetterThanWorldGen.NAME)
                .setCreativeTab(CreativeTabs.tabMaterials);

        dirtPile = ((ItemInterface) new MultiItem(BTWGItemIDs.DIRT_PILE_ID, "regolith_pile",
                new String[] {
                        StoneType.LIMESTONE.name()
                }))
                .btwg$setModNamespace(BetterThanWorldGen.NAME)
                .setFilterableProperties(FILTERABLE_FINE)
                .setCreativeTab(CreativeTabs.tabMaterials);

        gravelPile = ((ItemInterface) new MultiItem(BTWGItemIDs.GRAVEL_PILE_ID, "gravel_pile",
                new String[] {
                        StoneType.LIMESTONE.name()
                }))
                .btwg$setModNamespace(BetterThanWorldGen.NAME)
                .setFilterableProperties(FILTERABLE_FINE)
                .setCreativeTab(CreativeTabs.tabMaterials);
    }
}
