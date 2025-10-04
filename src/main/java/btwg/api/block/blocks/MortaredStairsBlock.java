package btwg.api.block.blocks;

import btw.block.blocks.StairsBlock;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;

import java.util.Random;

public class MortaredStairsBlock extends StairsBlock {
    protected final int idDropped;

    public MortaredStairsBlock(int id, Block referenceBlock, int referenceMetadata, int idDropped) {
        super(id, referenceBlock, referenceMetadata);
        this.idDropped = idDropped;
    }

    @Override
    public boolean hasMortar(IBlockAccess blockAccess, int x, int y, int z) {
        return true;
    }

    @Override
    public int idDropped(int metadata, Random rand, int fortuneModifier) {
        return this.idDropped;
    }

    @Override
    public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        this.dropBlockAsItem(world, x, y, z, metadata, 0);
    }
}
