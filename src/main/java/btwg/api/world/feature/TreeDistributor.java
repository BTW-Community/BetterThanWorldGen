package btwg.api.world.feature;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;

import java.util.Random;

public abstract class TreeDistributor {
    protected int treesPerChunk;
    private boolean hasInitNoise;
    private World lastWorld;
    
    public TreeDistributor() {
        this(-10);
    }
    
    public TreeDistributor(int treesPerChunk) {
        this.treesPerChunk = treesPerChunk;
    }
    
    public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
        return rand.nextInt(10) == 0 ? TreeGrowers.OAK_TREE : TreeGrowers.BIG_OAK_TREE;
    }
    
    public final void generateTreesForChunk(World world, Random rand, int x, int z) {
        if (!this.hasInitNoise || world != this.lastWorld) {
            this.initNoise(world, rand);
        }
        this.hasInitNoise = true;
        this.lastWorld = world;
        
        int treesPerChunk = this.getTreeCountPerChunk(world, rand, x, z);
    
        for (int n = 0; n < treesPerChunk; n++) {
            int i = x + rand.nextInt(16) + 8;
            int k = z + rand.nextInt(16) + 8;
            int j = world.getHeightValue(i, k);
            
            AbstractTreeGrower treeGrower = this.getTree(world, rand, i, j, k);
            treeGrower.growTree(world, rand, i, j, k, false);
        }
    }
    
    /**
     * @return Number of trees per chunk. Negative numbers mean fractional trees e.g. -10 means 1/10 of chunks will have a tree
     */
    public int getTreeCountPerChunk(World world, Random rand, int x, int z) {
        return this.treesPerChunk;
    }
    
    protected void initNoise(World world, Random rand) {}
}
