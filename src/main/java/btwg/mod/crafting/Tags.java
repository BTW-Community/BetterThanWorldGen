package btwg.mod.crafting;

import btw.item.BTWItems;
import btw.item.tag.Tag;
import net.minecraft.src.Item;
import net.minecraft.src.ResourceLocation;

public abstract class Tags {
    public static final Tag CLAY = Tag.of(new ResourceLocation("btwg:clay_balls"), Item.clay, BTWItems.netherSludge);
}
