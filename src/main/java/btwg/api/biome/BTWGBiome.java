package btwg.api.biome;

import btwg.api.world.feature.TreeDistributor;
import net.minecraft.src.BiomeGenBase;

public class BTWGBiome extends BiomeGenBase {
    private TreeDistributor treeDistributor = new TreeDistributor() {};
    
    public BTWGBiome(int par1) {
        super(par1);
        this.theBiomeDecorator = new BTWGBiomeDecorator(this);
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
}
