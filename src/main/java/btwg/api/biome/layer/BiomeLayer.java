package btwg.api.biome.layer;

import btwg.api.configuration.WorldData;
import btwg.mod.BiomeConfiguration;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;
import net.minecraft.src.WorldType;

public class BiomeLayer extends GenLayer {
    private BiomeGenBase[] allowedBiomes;
    
    public BiomeLayer(long seed, GenLayer parent, WorldType worldType, WorldData generatorOptions) {
        super(seed);
        this.allowedBiomes = BiomeConfiguration.biomeList.toArray(new BiomeGenBase[0]);
        this.parent = parent;
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] var5 = this.parent.getInts(x, z, sizeX, sizeZ);
        int[] var6 = IntCache.getIntCache(sizeX * sizeZ);
        
        for(int k = 0; k < sizeZ; ++k) {
            for(int i = 0; i < sizeX; ++i) {
                this.initChunkSeed(i + x, k + z);
                int var9 = var5[i + k * sizeX];
                
                if (var9 == 0) {
                    var6[i + k * sizeX] = 0;
                }
                else if (var9 == BiomeGenBase.mushroomIsland.biomeID) {
                    var6[i + k * sizeX] = var9;
                }
                else if (var9 == 1) {
                    var6[i + k * sizeX] = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;
                }
                else {
                    int var10 = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;
                    if (var10 == BiomeGenBase.taiga.biomeID) {
                        var6[i + k * sizeX] = var10;
                    } else {
                        var6[i + k * sizeX] = BiomeGenBase.icePlains.biomeID;
                    }
                }
            }
        }
        
        return var6;
    }
}
