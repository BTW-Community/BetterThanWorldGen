package btwg.api.block.blocks;

import btw.block.blocks.LooseDirtSlabBlock;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityFallingSand;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

public class LooseRegolithSlabBlock extends LooseDirtSlabBlock {
    private final StoneType type;

    public LooseRegolithSlabBlock(int id, StoneType type) {
        super(id);
        this.type = type;

        this.setTextureName(BetterThanWorldGen.MODID + ":stone/" + type.name() + "/loose_regolith");
        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_" + type.name()  + "_regolith_slab");
    }

    @Override
    public int getCombinedBlockID(int var1) {
        return this.type.looseDirtID();
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        return this.type.looseDirtMetadata();
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, this.type.dirtPileID(), 3, this.type.dirtPileMetadata(), fChanceOfDrop);
        return true;
    }

    @Override
    public boolean spreadGrassToBlock(World world, int x, int y, int z) {
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseSparseGrassSlabID(), this.type.looseSparseGrassSlabMetadata());
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

    //------ Client Side Functionality ------//

    @Override
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(this.getTextureName());
    }
}
