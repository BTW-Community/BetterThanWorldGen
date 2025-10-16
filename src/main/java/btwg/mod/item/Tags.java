package btwg.mod.item;

import btw.item.BTWItems;
import btw.item.tag.BTWTags;
import btw.item.tag.Tag;
import btw.util.color.Color;
import btwg.api.block.PlantType;
import btwg.api.block.StoneType;
import btwg.mod.block.BTWGBlocks;
import btwg.api.block.blocks.MultiTextureBlock;
import btwg.api.item.items.MultiItem;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

import java.util.Arrays;
import java.util.function.BiConsumer;

public abstract class Tags {
    public static final Tag FLOWERS = Tag.of(loc("flowers"))
            .add(Block.plantYellow)
            .add(Block.plantRed)
            .addUntilDamage(12, BTWGBlocks.flower)
            .addUntilDamage(7, BTWGBlocks.flower2)
            .add(BTWGBlocks.torchflower)
            .addUntilDamage(1, BTWGBlocks.eyeblossom);
    public static final Tag TALL_FLOWERS = Tag.of(loc("tall_flowers"))
            .addUntilDamage(7, BTWGBlocks.tallFlower);
    public static final Tag FLOWERS_BOTH = Tag.of(loc("flowers_both"), FLOWERS, TALL_FLOWERS);

    public static final Tag[] FLOWERS_BY_COLOR = new Tag[16];
    public static final Tag[] TALL_FLOWERS_BY_COLOR = new Tag[16];
    static {
        for (Color color : Color.values()) {
            FLOWERS_BY_COLOR[color.colorID] = Tag.of(loc(color.name().toLowerCase() + "_flowers"));
            TALL_FLOWERS_BY_COLOR[color.colorID] = Tag.of(loc("tall_" + color.name().toLowerCase() + "_flowers"));
        }

        BiConsumer<PlantType, Color> addPlantTypeColor = (type, color) -> {
            ItemStack stack = new ItemStack(type.id(), 1, type.metadata());

            if (type.isTall()) {
                TALL_FLOWERS_BY_COLOR[color.colorID].add(stack);
            }
            else {
                FLOWERS_BY_COLOR[color.colorID].add(stack);
            }
        };

        addPlantTypeColor.accept(PlantType.BLACK_THISTLE, Color.BLACK);

        addPlantTypeColor.accept(PlantType.RED_SNAPDRAGON, Color.RED);
        addPlantTypeColor.accept(PlantType.POPPY, Color.RED);
        addPlantTypeColor.accept(PlantType.GUZMANIA, Color.RED);
        addPlantTypeColor.accept(PlantType.ROSE_BUSH, Color.RED);

        addPlantTypeColor.accept(PlantType.BLUE_GINGER, Color.BLUE);
        addPlantTypeColor.accept(PlantType.CORNFLOWER, Color.BLUE);

        addPlantTypeColor.accept(PlantType.FOXGLOVE, Color.PURPLE);

        addPlantTypeColor.accept(PlantType.PITCHER_PLANT, Color.CYAN);

        addPlantTypeColor.accept(PlantType.PRIMROSE, Color.LIGHT_GRAY);
        addPlantTypeColor.accept(PlantType.AZURE_BLUET, Color.LIGHT_GRAY);
        addPlantTypeColor.accept(PlantType.OXEYE_DAISY, Color.LIGHT_GRAY);
        addPlantTypeColor.accept(PlantType.SWAMP_AZALEA, Color.LIGHT_GRAY);

        addPlantTypeColor.accept(PlantType.EYEBLOSSOM, Color.GRAY);

        addPlantTypeColor.accept(PlantType.PINK_TULIP, Color.PINK);
        addPlantTypeColor.accept(PlantType.PINK_SNAPDRAGON, Color.PINK);
        addPlantTypeColor.accept(PlantType.PEONY, Color.PINK);

        addPlantTypeColor.accept(PlantType.GOLDENROD, Color.YELLOW);
        addPlantTypeColor.accept(PlantType.DANDELION, Color.YELLOW);
        addPlantTypeColor.accept(PlantType.YELLOW_SNAPDRAGON, Color.YELLOW);

        addPlantTypeColor.accept(PlantType.HYACINTH, Color.LIGHT_BLUE);
        addPlantTypeColor.accept(PlantType.BLUE_ORCHID, Color.LIGHT_BLUE);

        addPlantTypeColor.accept(PlantType.ALLIUM, Color.MAGENTA);
        addPlantTypeColor.accept(PlantType.LILAC, Color.MAGENTA);

        addPlantTypeColor.accept(PlantType.DESERT_FLAME, Color.ORANGE);
        addPlantTypeColor.accept(PlantType.HELICONIA, Color.ORANGE);
        addPlantTypeColor.accept(PlantType.TORCHFLOWER, Color.ORANGE);
        addPlantTypeColor.accept(PlantType.OPEN_EYEBLOSSOM, Color.ORANGE);
        addPlantTypeColor.accept(PlantType.MARIGOLD, Color.ORANGE);

        addPlantTypeColor.accept(PlantType.LILY_OF_THE_VALLEY, Color.WHITE);
        addPlantTypeColor.accept(PlantType.WHITE_TULIP, Color.WHITE);
        addPlantTypeColor.accept(PlantType.WHITE_SNAPDRAGON, Color.WHITE);
    }

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
