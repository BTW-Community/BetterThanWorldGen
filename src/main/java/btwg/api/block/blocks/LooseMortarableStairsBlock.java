package btwg.api.block.blocks;

import btw.block.blocks.MortarReceiverStairsBlock;
import net.minecraft.src.Block;
import net.minecraft.src.World;

public class LooseMortarableStairsBlock extends MortarReceiverStairsBlock {
    private final int idMortared;

    public LooseMortarableStairsBlock(int id, Block referenceBlock, int referenceBlockMetadata, int idMortared) {
        super(id, referenceBlock, referenceBlockMetadata);
        this.idMortared = idMortared;
    }

    @Override
    public boolean onMortarApplied(World world, int i, int j, int k) {
        world.setBlockAndMetadataWithNotify(i, j, k, this.idMortared, world.getBlockMetadata(i, j, k));
        return true;
    }

    @Override
    public boolean isBreakableBarricade(World world, int i, int j, int k) {
        return world.getDifficulty().canZombiesBreakBlocks();
    }
}
