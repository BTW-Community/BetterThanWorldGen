package btwg.api.block.blocks;

import btw.block.blocks.SlabBlock;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class BTWGStoneSlabBlock extends SlabBlock {
    public static final int TYPE_OFFSET = 1;

    private final StoneType type;

    public BTWGStoneSlabBlock(int id, StoneType type) {
        super(id, Material.rock);

        this.type = type;

        if (type.strata() == 0) {
            this.setHardness(2.25f);
            this.setResistance(10.0f);
        }
        else if (type.strata() == 1) {
            this.setHardness(3.0f);
            this.setResistance(13.0f);
        }
        else {
            this.setHardness(4.5f);
            this.setResistance(20.0f);
        }

        this.setPicksEffectiveOn();
        this.setChiselsEffectiveOn();

        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + type.name() + "_slab");
        this.setTextureName(BetterThanWorldGen.MODID + ":" + type.name());

        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public int getCombinedBlockID(int metadata) {
        int variant = this.setIsUpsideDown(metadata, false) >> 1;

        if (variant == StoneType.COBBLESTONE_TYPE + TYPE_OFFSET) {
            return this.type.cobblestoneID();
        }
        else if (variant == StoneType.STONE_BRICK_TYPE + TYPE_OFFSET) {
            return this.type.stoneBrickID();
        }
        else {
            return this.type.stoneID();
        }
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        int variant = this.setIsUpsideDown(metadata, false) >> 1;

        if (variant == StoneType.COBBLESTONE_TYPE + TYPE_OFFSET) {
            return this.type.cobblestoneMetadata();
        }
        else if (variant == StoneType.STONE_BRICK_TYPE + TYPE_OFFSET) {
            return this.type.stoneBrickMetadata();
        }
        else {
            return this.type.stoneMetadata();
        }
    }

    @Override
    public boolean hasMortar(IBlockAccess blockAccess, int x, int y, int z) {
        int variant = this.setIsUpsideDown(blockAccess.getBlockMetadata(x, y, z), false);
        return variant != 0;
    }

    //------ Client Side Functionality  ------//

    @Environment(EnvType.CLIENT)
    protected Icon[] icons;

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        int variant = this.setIsUpsideDown(metadata, false) >> 1;
        return this.icons[variant];
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.icons = new Icon[4];

        this.icons[0] = iconRegister.registerIcon(this.getTextureName() + "/raw");
        this.icons[StoneType.COBBLESTONE_TYPE + TYPE_OFFSET] = iconRegister.registerIcon(this.getTextureName() + "/cobble");
        this.icons[StoneType.POLISHED_TYPE + TYPE_OFFSET] = iconRegister.registerIcon(this.getTextureName() + "/polished");
        this.icons[StoneType.STONE_BRICK_TYPE + TYPE_OFFSET] = iconRegister.registerIcon(this.getTextureName() + "/bricks");
    }

    @Override
    public void getSubBlocks(int iBlockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(iBlockID, 1, 0));
        list.add(new ItemStack(iBlockID, 1, 2));
        list.add(new ItemStack(iBlockID, 1, 4));
        list.add(new ItemStack(iBlockID, 1, 6));
    }
}
