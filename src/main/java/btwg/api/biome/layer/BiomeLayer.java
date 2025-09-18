package btwg.api.biome.layer;

import btwg.api.configuration.WorldData;
import btwg.mod.BiomeConfiguration;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;
import net.minecraft.src.WorldType;

public class BiomeLayer extends BTWGBaseLayer {
    private BiomeGenBase[] allowedBiomes;
    
    public BiomeLayer(long seed, GenLayer parent, WorldType worldType, WorldData generatorOptions) {
        super(seed, parent, generatorOptions);
        this.allowedBiomes = BiomeConfiguration.biomeList.toArray(new BiomeGenBase[0]);
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] baseLand = this.parent.getInts(x, z, sizeX, sizeZ);
        int[] biomes = IntCache.getIntCache(sizeX * sizeZ);
        
        for(int k = 0; k < sizeZ; ++k) {
            for(int i = 0; i < sizeX; ++i) {
                this.initChunkSeed(i + x, k + z);
                int currentID = baseLand[i + k * sizeX];
                
                if (currentID == 0) {
                    biomes[i + k * sizeX] = 0;
                }
                else if (currentID == BiomeGenBase.mushroomIsland.biomeID) {
                    biomes[i + k * sizeX] = currentID;
                }
                else {
                    biomes[i + k * sizeX] = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;
                }
            }
        }
        
        return biomes;
    }
}
