package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.LooseSparseGrassSlabBlock;
import btw.client.render.util.RenderUtils;
import btw.item.BTWItems;
import btw.world.util.BlockPos;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class LooseSparseGrassyRegolithSlabBlock extends LooseSparseGrassSlabBlock {
    private final StoneType type;

    public LooseSparseGrassyRegolithSlabBlock(int id, StoneType type) {
        super(id);
        this.type = type;

        this.setTextureName(BetterThanWorldGen.MODID + ":stone/" + type.name() + "/loose_sparse_grass");
        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_sparse_grassy_" + type.name()  + "_regolith_slab");
    }

    @Override
    public void randomUpdateTick(World world, int x, int y, int z, Random rand) {
        if (!BlockGrass.canGrassSurviveAtLocation(world, x, y, z)) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtSlabID(), this.type.looseDirtSlabMetadata());
        } else if (BlockGrass.canGrassSpreadFromLocation(world, x, y, z)) {
            if (rand.nextFloat() <= 0.8f) {
                BlockGrass.checkForGrassSpreadFromLocation(world, x, y, z);
            }
            if (rand.nextInt(12) == 0) {
                world.setBlockAndMetadataWithNotify(x, y, z, this.type.grassSlabID(), this.type.grassSlabMetadata());
            }
        }
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
    public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int iMetadata, float chanceOfDrop) {
        this.dropItemsIndividually(world, x, y, z, this.type.dirtPileID(), 3, this.type.dirtPileMetadata(), chanceOfDrop);
        return true;
    }

    @Override
    public int getCombinedBlockID(int iMetadata) {
        return this.type.looseSparseGrassID();
    }

    @Override
    public int getCombinedMetadata(int iMetadata) {
        return this.type.looseSparseGrassMetadata();
    }

    @Override
    protected ItemStack createStackedBlock(int metadata) {
        return new ItemStack(this.type.looseSparseGrassSlabID(), 1, this.type.looseSparseGrassSlabMetadata());
    }

    @Override
    public void onGrazed(World world, int x, int y, int z, EntityAnimal animal) {
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtSlabID(), this.type.looseDirtSlabMetadata());
        if (animal.getDisruptsEarthOnGraze()) {
            this.notifyNeighborsBlockDisrupted(world, x, y, z);
        }
    }

    @Override
    public void onVegetationAboveGrazed(World world, int x, int y, int z, EntityAnimal animal) {
        if (animal.getDisruptsEarthOnGraze()) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtID(), this.type.looseDirtMetadata());
            this.notifyNeighborsBlockDisrupted(world, x, y, z);
        }
    }

    @Override
    public boolean attemptToCombineWithFallingEntity(World world, int x, int y, int z, EntityFallingSand entity) {
        if (entity.blockID == BTWBlocks.looseDirtSlab.blockID && !this.getIsUpsideDown(world, x, y, z)) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtID(), this.type.looseDirtMetadata());
            return true;
        }
        return super.attemptToCombineWithFallingEntity(world, x, y, z, entity);
    }

    //------ Client Side Functionality ------//

    @Environment(value= EnvType.CLIENT)
    private boolean hasSnowOnTop;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTopSparse;
    @Environment(value=EnvType.CLIENT)
    private Icon iconDirtTopSparse;
    @Environment(value=EnvType.CLIENT)
    private Icon iconSnowSide;
    @Environment(value=EnvType.CLIENT)
    private Icon iconSnowSideHalf;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassSideOverlay;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassSideOverlayHalf;

    @Override
    @Environment(value=EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(this.getTextureName() + "_side");
        this.iconSnowSide = register.registerIcon(this.getTextureName() + "_side_snowed");
        this.iconGrassSideOverlay = register.registerIcon(this.getTextureName() + "_side_overlay");
        this.iconGrassTopSparse = register.registerIcon(this.getTextureName() + "_top_overlay");
        this.iconDirtTopSparse = register.registerIcon(this.getTextureName() + "_top");
        this.iconSnowSideHalf = register.registerIcon(this.getTextureName() + "_slab_side_snowed");
        this.iconGrassSideOverlayHalf = register.registerIcon(this.getTextureName() + "_slab_side_overlay");
    }

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
        BlockPos pos = new BlockPos(neighborX, neighborY, neighborZ, Facing.oppositeSide[side]);
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
                return this.iconDirtTopSparse;
            }
            if (side > 1) {
                Icon betterGrassIcon;
                Icon sideIcon = this.blockIcon;
                if (this.hasSnowOnTop) {
                    sideIcon = this.getIsUpsideDown(blockAccess, x, y, z) ? this.iconSnowSide : this.iconSnowSideHalf;
                }
                if (RenderBlocksUtils.enableBetterGrass && (betterGrassIcon = RenderBlocksUtils.getGrassTexture(this, blockAccess, x, y, z, side, this.iconDirtTopSparse)) != null) {
                    sideIcon = betterGrassIcon;
                }
                return sideIcon;
            }
            return BTWBlocks.looseDirt.blockIcon;
        }
        return this.getBlockTextureSecondPass(blockAccess, x, y, z, side);
    }

    @Environment(value=EnvType.CLIENT)
    public Icon getBlockTextureSecondPass(IBlockAccess blockAccess, int x, int y, int z, int side) {
        Icon betterGrassIcon;
        Icon topIcon = this.iconGrassTopSparse;
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
    public void renderBlockAsItem(RenderBlocks renderBlocks, int itemDamage, float brightness) {
        renderBlocks.setRenderBounds(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
        RenderUtils.renderInvBlockWithMetadata(renderBlocks, this, -0.5f, -0.5f, -0.5f, 0);
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getIcon(int par1, int par2) {
        return par1 == 1 ? Block.blocksList[this.type.looseDirtID()].blockIcon : (par1 == 0 ? Block.blocksList[this.type.looseDirtID()].getBlockTextureFromSide(par1) : this.blockIcon);
    }

    @Override
    public void renderFallingBlock(RenderBlocks renderBlocks, int x, int y, int z, int metadata) {
        renderBlocks.setRenderBounds(this.getFixedBlockBoundsFromPool());
        this.renderBlockSecondPass(renderBlocks, x, y, z, this.renderBlock(renderBlocks, x, y, z));
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public int idPicked(World world, int x, int y, int z) {
        return BTWBlocks.grassSlab.blockID;
    }
}
