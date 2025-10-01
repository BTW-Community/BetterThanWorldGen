package btwg.mod.item;

import btw.crafting.util.FurnaceBurnTime;
import btwg.api.block.WoodType;
import btwg.api.item.items.BTWGDoorItem;
import btwg.api.item.items.MultiItem;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

import static net.minecraft.src.Item.FILTERABLE_SMALL;
import static net.minecraft.src.Item.FILTERABLE_THIN;

public class BTWGItems {
    public static Item bark;
    public static Item door;

    public static void initItems() {
        bark = new MultiItem(BTWGItemIDs.BARK_ID, "bark",
                new String[] {
                        "acacia",
                        "dark_oak",
                        "mangrove",
                        "cherry",
                        "pale_oak"
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
                        WoodType.PALE_OAK
                });
    }
}
