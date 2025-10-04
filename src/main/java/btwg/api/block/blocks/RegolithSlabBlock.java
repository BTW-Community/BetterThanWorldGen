package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.DirtSlabBlock;
import btwg.api.block.StoneType;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class RegolithSlabBlock extends DirtSlabBlock {
    private final StoneType type;

    public RegolithSlabBlock(int blockID, StoneType type) {
        super(blockID);
        this.setTickRandomly(false);

        this.type = type;

        this.setUnlocalizedName("btwg." + type.name() + "_regolith_slab");
        this.setTextureName("btwg:" + type.name() + "_regolith");
    }

    @Override
    public int idDropped(int iMetadata, Random random, int iFortuneModifier) {
        return this.type.looseDirtSlabID();
    }

    @Override
    public int damageDropped(int metadata) {
        return this.type.looseDirtSlabMetadata();
    }

    @Override
    public int getCombinedBlockID(int var1) {
        return this.type.dirtID();
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        return this.type.dirtMetadata();
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, this.type.dirtPileID(), 3, this.type.dirtPileMetadata(), fChanceOfDrop);
        return true;
    }

    @Override
    public float getMovementModifier(World world, int i, int j, int k) {
        return 1.0F;
    }

    @Override
    public boolean getCanGrassSpreadToBlock(World world, int i, int j, int k) {
        Block blockAbove = Block.blocksList[world.getBlockId(i, j + 1, k)];
        boolean isUpsideDown = this.getIsUpsideDown(world, i, j, k);

        return blockAbove == null || blockAbove.getCanGrassGrowUnderBlock(world, i, j + 1, k, !isUpsideDown);
    }

    @Override
    public boolean spreadGrassToBlock(World world, int x, int y, int z) {
        boolean isUpsideDown = this.getIsUpsideDown(world, x, y, z);
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.grassSlabID(), this.type.grassSlabMetadata());

        BTWBlocks.grassSlab.setSparse(world, x, y, z);
        BTWBlocks.grassSlab.setIsUpsideDown(world, x, y, z, isUpsideDown);

        return true;
    }

    @Override
    public boolean getCanMyceliumSpreadToBlock(World world, int i, int j, int k) {
        return false;
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
    protected void onAnchorBlockLost(World world, int i, int j, int k) {
        world.setBlockAndMetadataWithNotify(i, j, k, this.type.looseDirtSlabID(), world.getBlockMetadata(i, j, k) & 3);
    }

    @Override
    protected ItemStack createStackedBlock(int metadata) {
        return new ItemStack(this);
    }

    @Override
    public boolean canBeGrazedOn(IBlockAccess blockAccess, int i, int j, int k, EntityAnimal byAnimal) {
        return false;
    }

    @Override
    public void onVegetationAboveGrazed(World world, int i, int j, int k, EntityAnimal animal) {
        if (animal.getDisruptsEarthOnGraze()) {
            world.setBlockAndMetadataWithNotify(i, j, k, this.type.looseDirtSlabID(), this.type.looseDirtSlabMetadata());
            this.notifyNeighborsBlockDisrupted(world, i, j, k);
        }
    }

    @Override
    protected void onNeighborDirtDugWithImproperTool(World world, int i, int j, int k, int iToFacing) {
        boolean bIsUpsideDown;
        if ((bIsUpsideDown = this.getIsUpsideDown(world, i, j, k)) && iToFacing == 0 || !bIsUpsideDown && iToFacing == 1) {
            world.setBlockAndMetadataWithNotify(i, j, k, this.type.looseDirtSlabID(), this.type.looseDirtSlabMetadata());
        }
    }

    //------ Client Side Functionality ------//

    @Override
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(this.getTextureName());
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getBlockTexture(IBlockAccess blockAccess, int i, int j, int k, int side) {
        return this.blockIcon;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void getSubBlocks(int iBlockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(iBlockID, 1, 0));
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int i, int j, int k) {
        if (ColorizeBlock.colorizeBlock(this, blockAccess, i, j, k)) {
            return ColorizeBlock.blockColor;
        }

        return 0xFFFFFF;
    }
}
