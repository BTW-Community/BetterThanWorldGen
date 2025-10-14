package btwg.api.world.feature;

import btw.entity.mob.villager.trade.VillagerTrade;
import btw.util.RandomSelector;
import btwg.api.block.PlantType;
import btwg.mod.block.BTWGBlocks;
import btwg.api.block.blocks.TallPlantBlock;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.ToDoubleFunction;

public class PlantDistributor {
    protected int patchesPerChunk;
    protected int patchQuantity;
    protected int patchSize;

    private boolean hasInitNoise;
    private World lastWorld;

    protected ArrayList<Pair<PlantType, Double>> plants = new ArrayList<>();

    private static final ArrayList<Pair<PlantType, Double>> defaultPlants = new ArrayList<>();
    static {
        defaultPlants.add(Pair.of(PlantType.SHORT_GRASS, 1.0));
        defaultPlants.add(Pair.of(PlantType.TALL_GRASS, 0.1));
    }

    public PlantDistributor(int patchesPerChunk, int patchQuantity, int patchSize) {
        this.patchesPerChunk = patchesPerChunk;
        this.patchQuantity = patchQuantity;
        this.patchSize = patchSize;
    }

    public PlantDistributor(int patchesPerChunk) {
        this(patchesPerChunk, 128, 8);
    }

    public PlantDistributor() {
        this(1);
    }

    public PlantDistributor addPlant(PlantType plantType, double weight) {
        this.plants.add(Pair.of(plantType, weight));
        return this;
    }

    protected void placePlant(World world, Random rand, int x, int y, int z) {
        var plants = this.plants;

        if (this.plants.isEmpty()) {
            plants = defaultPlants;
        }

        ToDoubleFunction<Pair<PlantType, Double>> weightFunction = Pair::getRight;
        var selector = RandomSelector.weighted(plants, weightFunction);
        var plantType = selector.next(rand).getLeft();

        if (plantType.isTall()) {
            this.placeDoublePlant(world, x, y, z, plantType.id(), plantType.metadata());
        }
        else {
            this.placeSinglePlant(world, x, y, z, plantType.id(), plantType.metadata());
        }
    }

    protected void placeSinglePlant(World world, int x, int y, int z, int blockID, int metadata) {
        if (world.isAirBlock(x, y, z) && Block.blocksList[blockID].canBlockStay(world, x, y, z)) {
            world.setBlockAndMetadata(x, y, z, blockID, metadata);
        }
    }

    protected void placeDoublePlant(World world, int x, int y, int z, int blockID, int metadata) {
        if (Block.blocksList[blockID] instanceof TallPlantBlock tallPlantBlock) {
            if (world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z)
                    && tallPlantBlock.canGrowOnBlock(world, x, y - 1, z))
            {
                world.setBlockAndMetadata(x, y, z, blockID, metadata);
                world.setBlockAndMetadata(x, y + 1, z, blockID, tallPlantBlock.setTopBlock(metadata, true));
            }
        }
    }

    public final void generatePlantsForChunk(World world, Random rand, int x, int z) {
        if (!this.hasInitNoise || world != this.lastWorld) {
            this.initNoise(world, rand);
            this.hasInitNoise = true;
            this.lastWorld = world;
        }

        int patchesPerChunk = this.getPatchesPerChunk(world, rand, x, z);

        if (patchesPerChunk < 0) {
            if (rand.nextInt(-patchesPerChunk) == 0) {
                generatePatch(world, rand, x, z);
            }
        }
        else {
            for (int n = 0; n < patchesPerChunk; n++) {
                generatePatch(world, rand, x, z);
            }
        }
    }

    protected void generatePatch(World world, Random rand, int x, int z) {
        int patchX = x + rand.nextInt(16) + 8;
        int patchZ = z + rand.nextInt(16) + 8;
        int patchY = world.getHeightValue(patchX, patchZ);

        for (int count = 0; count < this.patchQuantity; count++) {
            int i = patchX + rand.nextInt(this.patchSize) - rand.nextInt(this.patchSize);
            int k = patchZ + rand.nextInt(this.patchSize) - rand.nextInt(this.patchSize);
            int j = patchY + rand.nextInt(this.patchSize / 2) - rand.nextInt(this.patchSize / 2);

            this.placePlant(world, rand, i, j, k);
        }
    }

    /**
     * @return Number of patches per chunk. Negative numbers mean fractional patches e.g. -10 means 1/10 of chunks will have a patches
     */
    public int getPatchesPerChunk(World world, Random rand, int x, int z) {
        return this.patchesPerChunk;
    }

    protected void initNoise(World world, Random rand) {}
}
