package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btwg.api.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BTWGLeafBlock extends BlockLeaves {
    private final WoodType[] woodTypes;
    private final String[][] textures;

    public BTWGLeafBlock(int id, WoodType[] woodTypes) {
        super(id);

        if (woodTypes.length > 4) {
            throw new IllegalArgumentException("Cannot exceed 4 wood types in a leaf block");
        }

        this.woodTypes = woodTypes;
        this.textures = new String[2][woodTypes.length];
        this.textures[0] = Arrays.stream(woodTypes)
                .map(type -> "btwg:" + type.name() + "_leaves")
                .toArray(String[]::new);
        this.textures[1] = Arrays.stream(woodTypes)
                .map(type -> "btwg:" + type.name() + "_leaves_opaque")
                .toArray(String[]::new);

        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);

        setHardness(0.2F);
        setAxesEffectiveOn(true);

        setBuoyant();

        setLightOpacity(1);

        setFireProperties(Flammability.LEAVES);
        this.setStepSound(BTWBlocks.leavesStepSound);;
    }

    @Override
    public int idDropped(int metadata, Random rand, int fortuneModifier) {
        return woodTypes[MathHelper.clamp_int(metadata, 0, woodTypes.length - 1)].saplingID();
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int fortuneModifier) {
        if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemShears) {
            player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this.blockID, 1, fortuneModifier & 3));
        } else {
            super.harvestBlock(world, player, x, y, z, fortuneModifier);
        }
    }

    //------ Client Side Functionality ------//

    /** 1 for fast graphic. 0 for fancy graphics. used in iconArray. */
    @Environment(EnvType.CLIENT)
    private int iconType;
    @Environment(EnvType.CLIENT)
    private Icon[][] iconArray;

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        iconArray = new Icon[2][];

        for (int i = 0; i < this.textures.length; i++) {
            this.iconArray[i] = new Icon[this.textures[i].length];

            for (int j = 0; j < this.textures[i].length; j++) {
                this.iconArray[i][j] = par1IconRegister.registerIcon(this.textures[i][j]);
            }
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        int leafType = MathHelper.clamp_int(metadata & 3, 0, this.woodTypes.length - 1);
        return this.iconArray[this.iconType][leafType];
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setGraphicsLevel(boolean isFancy) {
        this.graphicsLevel = isFancy;
        this.iconType = isFancy ? 0 : 1;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void getSubBlocks(int id, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.woodTypes.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        int adjacentBlockID = blockAccess.getBlockId(x, y, z);
        Block adjacentBlock = Block.blocksList[adjacentBlockID];

        boolean isAdjacentLeaf = adjacentBlock != null && adjacentBlock.isLeafBlock(blockAccess, x, y, z);

        return (this.graphicsLevel || !isAdjacentLeaf) && super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        int type = blockAccess.getBlockMetadata(x, y, z) & 3;

        if (woodTypes[MathHelper.clamp_int(type, 0, this.woodTypes.length - 1)].colorLeaves()) {
            return super.colorMultiplier(blockAccess, x, y, z);
        }

        return 0xFFFFFF;
    }
}
