package btwg.api.biome.layer;

import btwg.api.biome.BiomeInterface;
import btwg.api.configuration.Version;
import btwg.api.configuration.WorldData;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class BeachLayer extends BTWGBaseLayer {
    public BeachLayer(long seed, GenLayer parent, WorldData generatorOptions) {
        super(seed, parent, generatorOptions);
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] previousBiomes = this.parent.getInts(x - 1, z - 1, sizeX + 2, sizeZ + 2);
        int[] newBiomes = IntCache.getIntCache(sizeX * sizeZ);
        
        for(int k = 0; k < sizeZ; ++k) {
            for(int i = 0; i < sizeX; ++i) {
                this.initChunkSeed(i + x, k + z);
                int currentBiomeID = previousBiomes[i + 1 + (k + 1) * (sizeX + 2)];
                
                int neighborBiomeNegX = previousBiomes[i + 1 - 1 + (k + 1) * (sizeX + 2)];
                int neighborBiomeNegZ = previousBiomes[i + 1 + (k + 1 - 1) * (sizeX + 2)];
                int neighborBiomePosX = previousBiomes[i + 1 + 1 + (k + 1) * (sizeX + 2)];
                int neighborBiomePosZ = previousBiomes[i + 1 + (k + 1 + 1) * (sizeX + 2)];
                
                BiomeInterface currentBiome = (BiomeInterface) BiomeGenBase.biomeList[currentBiomeID];
                var beachData = currentBiome.getBeachData();
                Version version = BetterThanWorldGen.V1_0_0;
                
                if (beachData.isPresent()
                        && ((neighborBiomeNegX >= 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomeNegX]).makesBeaches())
                        || (neighborBiomeNegZ >= 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomeNegZ]).makesBeaches())
                        || (neighborBiomePosX >= 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomePosX]).makesBeaches())
                        || (neighborBiomePosZ >= 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomePosZ]).makesBeaches())))
                {
                    newBiomes[i + k * sizeX] = beachData.get().get(version).biomeID;
                }
                else {
                    newBiomes[i + k * sizeX] = currentBiomeID;
                }
            }
        }
        
        return newBiomes;
    }
}
