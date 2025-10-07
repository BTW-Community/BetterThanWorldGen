package btwg.api.block.blocks;

import btw.block.blocks.FallingSlabBlock;
import btwg.api.block.StoneType;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BTWGGravelSlabBlock extends FallingSlabBlock {
    private final StoneType type;

    public BTWGGravelSlabBlock(int id, StoneType type) {
        super(id, Material.ground);

        this.type = type;

        this.setHardness(0.5f);
        this.setShovelsEffectiveOn(true);
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("btwg." + type.name() + "_gravel_slab");
        this.setTextureName("btwg:stone/" + type.name() + "/gravel");
    }

    @Override
    public int getCombinedBlockID(int var1) {
        return this.type.gravelID();
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        return this.type.gravelMetadata();
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, this.type.gravelPileID(), 3, this.type.gravelPileMetadata(), fChanceOfDrop);
        return true;
    }
}
