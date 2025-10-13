package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import btw.world.util.difficulty.DifficultyParam;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class GrassyRegolithBlock extends BlockGrass {
    protected final StoneType type;

    public GrassyRegolithBlock(int id, StoneType type) {
        super(id);

        this.type = type;

        this.setTextureName(BetterThanWorldGen.MODID + ":stone/" + type.name()  + "/grass");
        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".grassy_" + type.name()  + "_regolith");
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!canGrassSurviveAtLocation(world, x, y, z)) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.dirtID(), this.type.dirtMetadata());
        }
        else {
            super.updateTick(world, x, y, z, rand);
        }
    }

    @Override
    public int idDropped(int metadata, Random rand, int fortuneModifier) {
        return this.type.looseDirtID();
    }

    @Override
    public int damageDropped(int metadata) {
        return this.type.looseDirtMetadata();
    }

    @Override
    protected ItemStack createStackedBlock(int metadata) {
        if (this.isSparse(metadata)) {
            return new ItemStack(this.type.dirtID(), 1, this.type.dirtMetadata());
        }
        return new ItemStack(this);
    }

    @Override
    public void onGrazed(World world, int x, int y, int z, EntityAnimal animal) {
        if (!animal.getDisruptsEarthOnGraze()) {
            if (this.isSparse(world, x, y, z)) {
                world.setBlockAndMetadataWithNotify(x, y, z, this.type.dirtID(), this.type.dirtMetadata());
            } else {
                this.setSparse(world, x, y, z);
            }
        } else {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseSparseGrassID(), this.type.looseSparseGrassMetadata());
            this.notifyNeighborsBlockDisrupted(world, x, y, z);
        }
    }

    @Override
    public void onVegetationAboveGrazed(World world, int x, int y, int z, EntityAnimal animal) {
        if (animal.getDisruptsEarthOnGraze()) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseSparseGrassID(), this.type.looseSparseGrassMetadata());
            this.notifyNeighborsBlockDisrupted(world, x, y, z);
        }
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int fromSide) {
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtID(), this.type.looseDirtMetadata());

        if (!world.isRemote) {
            world.playAuxSFX(2291, x, y, z, 0);

            if (world.rand.nextInt(10) == 0) {
                ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.hempSeeds), fromSide);
            }
        }

        return true;
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
        this.dropItemsIndividually(world, x, y, z, this.type.dirtPileID(), 6, this.type.dirtPileMetadata(), chanceOfDrop);
        return true;
    }

    @Override
    protected void onNeighborDirtDugWithImproperTool(World world, int x, int y, int z, int toFacing) {
        if (world.getDifficultyParameter(DifficultyParam.ShouldGrassLoosenWhenDigging.class) && toFacing == 0) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtID(), this.type.looseDirtMetadata());
        }
    }

    //------ Client Side Functionality ------//

    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTop;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTopSparse;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTopSparseDirt;
    @Environment(value=EnvType.CLIENT)
    private Icon iconSnowSide;
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassSideOverlay;
    @Environment(value=EnvType.CLIENT)
    private boolean hasSnowOnTop;

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(this.getTextureName() + "_side");
        this.iconGrassTop = register.registerIcon(this.getTextureName() + "_top");
        this.iconSnowSide = register.registerIcon(this.getTextureName() + "_side_snowed");
        this.iconGrassSideOverlay = register.registerIcon(this.getTextureName() + "_side_overlay");
        this.iconGrassTopSparse = register.registerIcon(this.getTextureName() + "_sparse_top_overlay");
        this.iconGrassTopSparseDirt = register.registerIcon(this.getTextureName() + "_sparse_top");
    }

    // Everything below this is copied from BlockGrass - access wideners didn't work on the icon fields

    @Override
    @Environment(EnvType.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        if (this.hasSnowOnTop || !secondPass) {
            return 0xFFFFFF;
        }
        if (ColorizeBlock.colorizeBlock(this, blockAccess, x, y, z)) {
            return ColorizeBlock.blockColor;
        }
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;
        for (int var8 = -1; var8 <= 1; ++var8) {
            for (int var9 = -1; var9 <= 1; ++var9) {
                int var10 = blockAccess.getBiomeGenForCoords(x + var9, z + var8).getBiomeGrassColor();
                var5 += (var10 & 0xFF0000) >> 16;
                var6 += (var10 & 0xFF00) >> 8;
                var7 += var10 & 0xFF;
            }
        }
        return (var5 / 9 & 0xFF) << 16 | (var6 / 9 & 0xFF) << 8 | var7 / 9 & 0xFF;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int neighborX, int neighborY, int neighborZ, int side) {
        if (secondPass && (side == 0 || this.hasSnowOnTop)) {
            return false;
        }
        return super.shouldSideBeRendered(blockAccess, neighborX, neighborY, neighborZ, side);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (!secondPass) {
            if (side == 1) {
                return this.iconGrassTopSparseDirt;
            }

            if (side > 1) {
                Icon betterGrassIcon;
                Icon sideIcon = this.blockIcon;

                if (this.hasSnowOnTop) {
                    sideIcon = this.iconSnowSide;
                }

                if (RenderBlocksUtils.enableBetterGrass && (betterGrassIcon = RenderBlocksUtils.getGrassTexture(this, blockAccess, x, y, z, side, this.iconGrassTopSparseDirt)) != null) {
                    sideIcon = betterGrassIcon;
                }

                return sideIcon;
            }

            return Block.blocksList[this.type.dirtID()].blockIcon;
        }

        return this.getBlockTextureSecondPass(blockAccess, x, y, z, side);
    }

    @Environment(EnvType.CLIENT)
    public Icon getBlockTextureSecondPass(IBlockAccess blockAccess, int x, int y, int z, int side) {
        Icon betterGrassIcon;
        Icon topIcon = this.iconGrassTop;

        if (this.isSparse(blockAccess, x, y, z)) {
            topIcon = this.iconGrassTopSparse;
        }

        if (side == 1) {
            return topIcon;
        }

        Icon sideIcon = this.iconGrassSideOverlay;

        if (RenderBlocksUtils.enableBetterGrass && (betterGrassIcon = RenderBlocksUtils.getGrassTexture(this, blockAccess, x, y, z, side, topIcon)) != null) {
            sideIcon = betterGrassIcon;
        }

        return sideIcon;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean renderBlock(RenderBlocks render, int x, int y, int z) {
        this.hasSnowOnTop = this.isSnowCoveringTopSurface(render.blockAccess, x, y, z);
        render.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        return render.renderStandardBlock(this, x, y, z);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void renderBlockSecondPass(RenderBlocks render, int x, int y, int z, boolean firstPassResult) {
        secondPass = true;
        render.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        render.renderStandardBlock(this, x, y, z);
        secondPass = false;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int par1, int par2) {
        return par1 == 1 ? this.iconGrassTop : (par1 == 0 ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getBlockColor() {
        if (ColorizeBlock.colorizeBlock(this)) {
            return ColorizeBlock.blockColor;
        }

        double var1 = 0.5;
        double var3 = 1.0;
        return ColorizerGrass.getGrassColor(var1, var3);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getRenderColor(int par1) {
        return ColorizeBlock.colorizeBlock(this, par1) ? ColorizeBlock.blockColor : this.getBlockColor();
    }
}
