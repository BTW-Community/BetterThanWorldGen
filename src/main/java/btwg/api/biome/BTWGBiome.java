package btwg.api.biome;

import btwg.api.world.feature.TreeDistributor;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ResourceLocation;

public class BTWGBiome extends BiomeGenBase {
    private TreeDistributor treeDistributor = new TreeDistributor() {};
    
    public BTWGBiome(int par1, ResourceLocation resourceLocation) {
        super(par1);
        this.theBiomeDecorator = new BTWGBiomeDecorator(this);
        this.biomeName = resourceLocation.toString();
    }
    
    public BTWGBiome setTemperatureAndRainfall(float temp, float rainfall) {
        if (temp > 0.1F && temp < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        } else {
            this.temperature = temp;
            this.rainfall = rainfall;
            return this;
        }
    }
    
    public TreeDistributor getTreeDistributor() {
        return this.treeDistributor;
    }
    
    public BTWGBiome setTreeDistributor(TreeDistributor treeDistributor) {
        this.treeDistributor = treeDistributor;
        return this;
    }

    public BTWGBiome setTopBlock(int blockID) {
        return this.setTopBlock(blockID, (byte) 0);
    }

    public BTWGBiome setTopBlock(int blockID, int meta) {
        this.topBlock = (short) blockID;
        this.topBlockMetadata = (byte) meta;
        return this;
    }

    public BTWGBiome setFillerBlock(int blockID) {
        return this.setFillerBlock(blockID, (byte) 0);
    }

    public BTWGBiome setFillerBlock(int blockID, int meta) {
        this.fillerBlock = (short) blockID;
        this.fillerBlockMetadata = (byte) meta;
        return this;
    }

    public BTWGBiome setSnow() {
        this.setEnableSnow();
        return this;
    }

    public BTWGBiome setNoRain() {
        this.setDisableRain();
        return this;
    }
}
