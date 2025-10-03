//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package btwg.api.biome;

import btw.AddonHandler;
import btw.util.ForkableRandom;
import net.minecraft.src.*;

import java.util.Random;

public class BTWGBiomeDecorator extends BiomeDecorator {
    private Random forkedRand;
    private BTWGBiome biome;
    
    public BTWGBiomeDecorator(BTWGBiome biome) {
        super(biome);
        this.biome = biome;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        if (this.currentWorld != null) {
            throw new RuntimeException("Already decorating!!");
        }
        this.currentWorld = par1World;
        this.randomGenerator = par2Random;
        this.forkedRand = ForkableRandom.forkRandom(this.randomGenerator);
        this.chunk_X = par3;
        this.chunk_Z = par4;
        this.decorate();
        this.currentWorld = null;
        this.randomGenerator = null;
        this.forkedRand = null;
    }

    @Override
    protected void decorate() {
        this.generateOres();

        int var2;
        int var3;
        
        this.biome.getTreeDistributor().generateTreesForChunk(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);

        int var4;
        
        for(var2 = 0; var2 < this.bigMushroomsPerChunk; ++var2) {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
        }
        
        int var7;
        for(var2 = 0; var2 < this.flowersPerChunk; ++var2) {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128) + 100;
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            if (this.randomGenerator.nextInt(4) == 0) {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(128) + 100;
                var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            }
        }

        this.biome.getGrassDistributor().generatePlantsForChunk(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z);
        
        for(var2 = 0; var2 < this.waterlilyPerChunk; ++var2) {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            
            for(var7 = this.randomGenerator.nextInt(128) + 100; var7 > 0 && this.currentWorld.getBlockId(var3, var7 - 1, var4) == 0; --var7) {
            }
            
            this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
        }
        
        for(var2 = 0; var2 < this.mushroomsPerChunk; ++var2) {
            if (this.randomGenerator.nextInt(4) == 0) {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var7 = this.currentWorld.getHeightValue(var3, var4);
                this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
            }
            
            if (this.randomGenerator.nextInt(8) == 0) {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var7 = this.randomGenerator.nextInt(128) + 100;
                this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
            }
        }
        
        if (this.randomGenerator.nextInt(4) == 0) {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128) + 100;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }
        
        if (this.randomGenerator.nextInt(8) == 0) {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128) + 100;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }
        
        for(var2 = 0; var2 < this.reedsPerChunk; ++var2) {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            var7 = this.randomGenerator.nextInt(128) + 100;
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
        }
        
        for(var2 = 0; var2 < 10; ++var2) {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128) + 100;
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
        }
        
        if (this.randomGenerator.nextInt(32) == 0) {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128) + 100;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }
        
        if (this.generateLakes) {
            for(var2 = 0; var2 < 100; ++var2) {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8);
                var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            }
            
            for(var2 = 0; var2 < 40; ++var2) {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
                var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            }
        }

        var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
        var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
        this.clayGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        
        AddonHandler.decorateWorld(this, this.currentWorld, this.forkedRand, this.chunk_X, this.chunk_Z, this.biome);
    }

    private void genOreLinearDistribution(int count, WorldGenerator worldGen, int minHeight, int maxHeight) {
        this.genStandardOre1(count, worldGen, minHeight, maxHeight);
    }

    private void genOreTriangleDistribution(int count, WorldGenerator worldGen, int radius, int center) {
        this.genStandardOre1(count, worldGen, radius, center);
    }

    @Override
    protected void generateOres() {

        this.genOreLinearDistribution(20, this.dirtGen, 8, 144);
        this.genOreLinearDistribution(15, this.gravelGen, 8, 144);

        ((WorldGenMinable) this.coalGen).setNoAirExposure(0.5F);
        this.genOreLinearDistribution(30, this.coalGen, 8, 256);

        ((WorldGenMinable) this.ironGen).setNoAirExposure(0.5F);
        this.genOreLinearDistribution(20, this.ironGen, 8, 100);
        ((WorldGenMinable) this.ironGen).setNoAirExposure(1.0F);
        this.genOreTriangleDistribution(10, this.ironGen, 60, 64);

        this.genOreLinearDistribution(3, this.goldGen, 8, 48);

        this.genOreTriangleDistribution(8, this.redstoneGen, 12, 20);

        this.genOreTriangleDistribution(1, this.diamondGen, 12, 20);
        this.genOreLinearDistribution(1, this.diamondGenAirExposed, 16, 24);

        this.genOreTriangleDistribution(1, this.lapisGen, 16, 24);
    }
}
