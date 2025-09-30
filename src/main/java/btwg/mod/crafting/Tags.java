package btwg.mod.crafting;

import btw.item.BTWItems;
import btw.item.tag.BTWTags;
import btw.item.tag.Tag;
import btwg.mod.block.BTWGBlocks;
import btwg.api.block.blocks.MultiTextureBlock;
import btwg.mod.item.BTWGItems;
import btwg.api.item.items.MultiItem;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

public abstract class Tags {
    public static final Tag CLAY_BALLS = Tag.of(new ResourceLocation("btwg:clay_balls"), Item.clay, BTWItems.netherSludge);

    public static void modifyTags() {
        BTWTags.barks.addUntilDamage(((MultiItem) BTWGItems.bark).getNumTypes(), BTWGItems.bark);
        BTWTags.planks.addUntilDamage(((MultiTextureBlock) BTWGBlocks.planks).getNumTypes(), BTWGBlocks.planks);

        BTWTags.woodCorners.add(new ItemStack(BTWGBlocks.acaciaSidingAndCorner, 1, 1));
        BTWTags.woodMouldings.add(new ItemStack(BTWGBlocks.acaciaMoulding, 1, 0));
        BTWTags.woodSidings.add(new ItemStack(BTWGBlocks.acaciaSidingAndCorner, 1, 0));
    }
}
