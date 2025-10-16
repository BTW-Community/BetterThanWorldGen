package btwg.mod.crafting;

import btw.item.BTWItems;
import btw.item.tag.BTWTags;
import btw.item.tag.Tag;
import btwg.api.block.StoneType;
import btwg.mod.block.BTWGBlocks;
import btwg.api.block.blocks.MultiTextureBlock;
import btwg.mod.item.BTWGItems;
import btwg.api.item.items.MultiItem;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

import java.util.Arrays;

public abstract class Tags {
    public static final Tag FLOWERS = Tag.of(loc("flowers"))
            .addUntilDamage(12, BTWGBlocks.flower.blockID)
            .addUntilDamage(7, BTWGBlocks.flower2.blockID)
            .add(BTWGBlocks.torchflower)
            .add(BTWGBlocks.eyeblossom);
    public static final Tag TALL_FLOWERS = Tag.of(loc("tall_flowers"))
            .addUntilDamage(7, BTWGBlocks.tallFlower.blockID);
    public static final Tag FLOWERS_BOTH = Tag.of(loc("flowers_both"), FLOWERS, TALL_FLOWERS);

    public static final Tag CLAY_BALLS = Tag.of(loc("clay_balls"), Item.clay, BTWItems.netherSludge);
    public static final Tag DIRT_PILES = Tag.of(loc("dirt_piles"), BTWItems.dirtPile);
    public static final Tag GRAVEL_PILES = Tag.of(loc("gravel_piles"), BTWItems.gravelPile);
    public static final Tag SAND_PILES = Tag.of(loc("sand_piles"), BTWItems.sandPile);

    public static void modifyTags() {
        BTWTags.barks.addUntilDamage(((MultiItem) BTWGItems.bark).getNumTypes(), BTWGItems.bark);
        BTWTags.planks.addUntilDamage(((MultiTextureBlock) BTWGBlocks.planks).getNumTypes(), BTWGBlocks.planks);

        BTWTags.woodCorners.add(new ItemStack(BTWGBlocks.acaciaSidingAndCorner, 1, 1))
                .add(new ItemStack(BTWGBlocks.darkOakSidingAndCorner, 1, 1))
                .add(new ItemStack(BTWGBlocks.mangroveSidingAndCorner, 1, 1))
                .add(new ItemStack(BTWGBlocks.cherrySidingAndCorner, 1, 1))
                .add(new ItemStack(BTWGBlocks.paleOakSidingAndCorner, 1, 1));

        BTWTags.woodMouldings.add(new ItemStack(BTWGBlocks.acaciaMoulding, 1, 0))
                .add(new ItemStack(BTWGBlocks.darkOakMoulding, 1, 0))
                .add(new ItemStack(BTWGBlocks.mangroveMoulding, 1, 0))
                .add(new ItemStack(BTWGBlocks.cherryMoulding, 1, 0))
                .add(new ItemStack(BTWGBlocks.paleOakMoulding, 1, 0));

        BTWTags.woodSidings.add(new ItemStack(BTWGBlocks.acaciaSidingAndCorner, 1, 0))
                .add(new ItemStack(BTWGBlocks.darkOakSidingAndCorner, 1, 0))
                .add(new ItemStack(BTWGBlocks.mangroveSidingAndCorner, 1, 0))
                .add(new ItemStack(BTWGBlocks.cherrySidingAndCorner, 1, 0))
                .add(new ItemStack(BTWGBlocks.paleOakSidingAndCorner, 1, 0));

        Arrays.stream(StoneType.STONE_TYPES)
                .filter(type -> type != StoneType.RHYOLITE
                        && type != StoneType.SLATE
                        && type != StoneType.GABBRO)
                .forEach(type -> {
                    BTWTags.coalOres.add(new ItemStack(type.coalOreID(), 1, type.coalOreMetadata()));
                    BTWTags.ironOres.add(new ItemStack(type.ironOreID(), 1, type.ironOreMetadata()));
                    BTWTags.goldOres.add(new ItemStack(type.goldOreID(), 1, type.goldOreMetadata()));
                    BTWTags.diamondOres.add(new ItemStack(type.diamondOreID(), 1, type.diamondOreMetadata()));
                    BTWTags.emeraldOres.add(new ItemStack(type.emeraldOreID(), 1, type.emeraldOreMetadata()));
                    BTWTags.redstoneOres.add(new ItemStack(type.redstoneOreID(), 1, type.redstoneOreMetadata()));
                    BTWTags.lapisOres.add(new ItemStack(type.lapisOreID(), 1, type.lapisOreMetadata()));

                    BTWTags.looseRocks.add(new ItemStack(type.rockItemID(), 1, type.rockItemMetadata()));
                    BTWTags.stoneBrickItems.add(new ItemStack(type.stoneBrickItemID(), 1, type.stoneBrickItemMetadata()));
                });

        Arrays.stream(StoneType.STONE_TYPES)
                .filter(type -> type != StoneType.RHYOLITE)
                .forEach(type -> {
                    DIRT_PILES.add(new ItemStack(type.dirtPileID(), 1, type.dirtPileMetadata()));
                    GRAVEL_PILES.add(new ItemStack(type.gravelPileID(), 1, type.gravelPileMetadata()));
                });
    }

    private static ResourceLocation loc(String id) {
        return new ResourceLocation("btwg", id);
    }
}
