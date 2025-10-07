package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.GrassSlabBlock;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class GrassyRegolithSlabBlock extends GrassSlabBlock {
    private final StoneType type;

    public GrassyRegolithSlabBlock(int blockID, StoneType type) {
        super(blockID);

        this.type = type;

        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".grassy_" + type.name() + "_regolith_slab");
        this.setTextureName(BetterThanWorldGen.MODID + ":stone/" + type.name() + "/grass");
    }

    @Override
    public int idDropped(int metadata, Random random, int fortuneModifier) {
        return this.type.looseDirtSlabID();
    }

    @Override
    public int damageDropped(int metadata) {
        return this.type.looseDirtSlabMetadata();
    }

    @Override
    public int getCombinedBlockID(int var1) {
        return this.type.grassID();
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        return this.type.grassMetadata();
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, this.type.dirtPileID(), 3, this.type.dirtPileMetadata(), fChanceOfDrop);
        return true;
    }

    @Override
    protected void onAnchorBlockLost(World world, int i, int j, int k) {
        world.setBlockAndMetadataWithNotify(i, j, k, this.type.looseDirtSlabID(), world.getBlockMetadata(i, j, k) & 3);
    }

    @Override
    public boolean attemptToCombineWithFallingEntity(World world, int x, int y, int z, EntityFallingSand entity) {
        if (entity.blockID == this.type.looseDirtSlabID() && entity.metadata == this.type.looseDirtSlabMetadata() && !this.getIsUpsideDown(world, x, y, z)) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtSlabID(), this.type.looseDirtSlabMetadata());
            return true;
        }
        return super.attemptToCombineWithFallingEntity(world, x, y, z, entity);
    }

    @Override
    public void onGrazed(World world, int x, int y, int z, EntityAnimal animal) {
        if (!animal.getDisruptsEarthOnGraze()) {
            if (this.isSparse(world, x, y, z)) {
                world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtSlabID(), this.type.looseDirtSlabMetadata());
            } else {
                this.setSparse(world, x, y, z);
            }
        } else {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseSparseGrassSlabID(), this.type.looseSparseGrassSlabMetadata());
            this.notifyNeighborsBlockDisrupted(world, x, y, z);
        }
    }

    @Override
    public void revertToDirt(World world, int x, int y, int z) {
        boolean isUpsideDown = this.getIsUpsideDown(world, x, y, z);
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.dirtSlabID(), this.type.dirtSlabMetadata());
        BTWBlocks.dirtSlab.setSubtype(world, x, y, z, 0);
        BTWBlocks.dirtSlab.setIsUpsideDown(world, x, y, z, isUpsideDown);
    }

    //------ Client Side Functionality ------//

    @Environment(value=EnvType.CLIENT)
    private boolean hasSnowOnTop;
    @Environment(value=EnvType.CLIENT)
    public static boolean secondPass;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTop;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTopSparse;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTopSparseDirt;
    @Environment(value=EnvType.CLIENT)
    private Icon iconSnowSide;
    @Environment(value=EnvType.CLIENT)
    private Icon iconSnowSideHalf;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassSideOverlay;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassSideOverlayHalf;

    @Override
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(this.getTextureName() + "_slab_side");
        this.iconGrassTop = register.registerIcon(this.getTextureName() + "_top");
        this.iconSnowSide = register.registerIcon(this.getTextureName() + "_side_snowed");
        this.iconSnowSideHalf = register.registerIcon(this.getTextureName() + "_slab_side_snowed");
        this.iconGrassSideOverlay = register.registerIcon(this.getTextureName() + "_side_overlay");
        this.iconGrassSideOverlayHalf = register.registerIcon(this.getTextureName() + "_slab_side_overlay");
        this.iconGrassTopSparse = register.registerIcon(this.getTextureName() + "_sparse_top_overlay");
        this.iconGrassTopSparseDirt = register.registerIcon(this.getTextureName() + "_sparse_top");
    }

    // Everything below this is copied from GrassSlabBlock - access wideners didn't work on the icon fields

    @Override
    @Environment(value=EnvType.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        if (this.hasSnowOnTop || !secondPass) {
            return 0xFFFFFF;
        }
        if (ColorizeBlock.colorizeBlock(this, blockAccess, x, y, z)) {
            return ColorizeBlock.blockColor;
        }
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int i = -1; i <= 1; ++i) {
            for (int k = -1; k <= 1; ++k) {
                int iBiomeGrassColor = blockAccess.getBiomeGenForCoords(x + i, z + k).getBiomeGrassColor();
                red += (iBiomeGrassColor & 0xFF0000) >> 16;
                green += (iBiomeGrassColor & 0xFF00) >> 8;
                blue += iBiomeGrassColor & 0xFF;
            }
        }
        return (red / 9 & 0xFF) << 16 | (green / 9 & 0xFF) << 8 | blue / 9 & 0xFF;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int neighborX, int neighborY, int neighborZ, int side) {
        if (secondPass && (side == 0 || this.hasSnowOnTop)) {
            return false;
        }
        return super.shouldSideBeRendered(blockAccess, neighborX, neighborY, neighborZ, side);
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (!secondPass) {
            if (side == 1) {
                return this.iconGrassTopSparseDirt;
            }
            if (side > 1) {
                Icon betterGrassIcon;
                Icon sideIcon = this.hasSnowOnTop ? (this.getIsUpsideDown(blockAccess, x, y, z) ? this.iconSnowSide : this.iconSnowSideHalf) : (this.getIsUpsideDown(blockAccess, x, y, z) ? Block.grass.blockIcon : this.blockIcon);
                if (RenderBlocksUtils.enableBetterGrass && (betterGrassIcon = RenderBlocksUtils.getGrassTexture(this, blockAccess, x, y, z, side, this.iconGrassTopSparseDirt)) != null) {
                    sideIcon = betterGrassIcon;
                }
                return sideIcon;
            }
            return Block.dirt.blockIcon;
        }
        return this.getBlockTextureSecondPass(blockAccess, x, y, z, side);
    }

    @Environment(value=EnvType.CLIENT)
    public Icon getBlockTextureSecondPass(IBlockAccess blockAccess, int x, int y, int z, int side) {
        Icon betterGrassIcon;
        Icon topIcon = this.iconGrassTop;
        if (this.isSparse(blockAccess, x, y, z)) {
            topIcon = this.iconGrassTopSparse;
        }
        if (side == 1) {
            return topIcon;
        }
        if (this.getIsUpsideDown(blockAccess, x, y, z)) {
            return this.iconGrassSideOverlay;
        }
        Icon sideIcon = this.iconGrassSideOverlayHalf;
        if (RenderBlocksUtils.enableBetterGrass && (betterGrassIcon = RenderBlocksUtils.getGrassTexture(this, blockAccess, x, y, z, side, topIcon)) != null) {
            sideIcon = betterGrassIcon;
        }
        return sideIcon;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public boolean renderBlock(RenderBlocks render, int x, int y, int z) {
        this.hasSnowOnTop = this.isSnowCoveringTopSurface(render.blockAccess, x, y, z);
        AxisAlignedBB bounds = this.getBlockBoundsFromPoolBasedOnState(render.blockAccess, x, y, z);
        render.setRenderBounds(bounds);
        return render.renderStandardBlock(this, x, y, z);
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void renderBlockSecondPass(RenderBlocks render, int x, int y, int z, boolean firstPassResult) {
        secondPass = true;
        AxisAlignedBB bounds = this.getBlockBoundsFromPoolBasedOnState(render.blockAccess, x, y, z);
        render.setRenderBounds(bounds);
        render.renderStandardBlock(this, x, y, z);
        secondPass = false;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getIcon(int par1, int par2) {
        return par1 == 1 ? this.iconGrassTop : (par1 == 0 ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public int getBlockColor() {
        if (ColorizeBlock.colorizeBlock(this)) {
            return ColorizeBlock.blockColor;
        }
        double var1 = 0.5;
        double var3 = 1.0;
        return ColorizerGrass.getGrassColor(var1, var3);
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public int getRenderColor(int par1) {
        return ColorizeBlock.colorizeBlock(this, par1) ? ColorizeBlock.blockColor : this.getBlockColor();
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(blockID, 1, 0));
    }
}
