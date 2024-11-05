package btwg.api.biome.layer;

import btwg.api.biome.BiomeInterface;
import btwg.api.configuration.WorldData;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class SubBiomeLayer extends BTWGBaseLayer {
    public SubBiomeLayer(long seed, GenLayer parent, WorldData generatorOptions) {
        super(seed, parent, generatorOptions);
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] parentInts = this.parent.getInts(x - 1, z - 1, sizeX + 2, sizeZ + 2);
        int[] newInts = IntCache.getIntCache(sizeX * sizeZ);
        
        for(int k = 0; k < sizeZ; ++k) {
            for(int i = 0; i < sizeX; ++i) {
                this.initChunkSeed(i + x, k + z);
                int previousBiome = parentInts[i + 1 + (k + 1) * (sizeX + 2)];
                
                if (this.nextInt(3) == 0) {
                    int newBiome = previousBiome;
                    var subBiome = ((BiomeInterface) BiomeGenBase.biomeList[previousBiome]).getSubBiomeData();
                    
                    if (subBiome.isPresent()) {
                        newBiome = subBiome.get().get(BetterThanWorldGen.V1_0_0).biomeID;
                    }
                    
                    if (newBiome == previousBiome) {
                        newInts[i + k * sizeX] = previousBiome;
                    }
                    else {
                        int var11 = parentInts[i + 1 + (k + 1 - 1) * (sizeX + 2)];
                        int var12 = parentInts[i + 1 + 1 + (k + 1) * (sizeX + 2)];
                        int var13 = parentInts[i + 1 - 1 + (k + 1) * (sizeX + 2)];
                        int var14 = parentInts[i + 1 + (k + 1 + 1) * (sizeX + 2)];
                        
                        if (var11 == previousBiome && var12 == previousBiome && var13 == previousBiome && var14 == previousBiome) {
                            newInts[i + k * sizeX] = newBiome;
                        }
                        else {
                            newInts[i + k * sizeX] = previousBiome;
                        }
                    }
                }
                else {
                    newInts[i + k * sizeX] = previousBiome;
                }
            }
        }
        
        return newInts;
    }
}
