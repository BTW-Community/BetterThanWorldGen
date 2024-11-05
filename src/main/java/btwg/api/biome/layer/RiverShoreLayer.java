package btwg.api.biome.layer;

import btwg.api.biome.BiomeInterface;
import btwg.api.configuration.Version;
import btwg.api.configuration.WorldData;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class RiverShoreLayer extends BTWGBaseLayer {
    private final GenLayer riverParent;
    
    public RiverShoreLayer(long seed, GenLayer parent, GenLayer riverParent, WorldData generatorOptions) {
        super(seed, parent, generatorOptions);
        this.riverParent = riverParent;
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] previousBiomes = this.parent.getInts(x - 1, z - 1, sizeX + 2, sizeZ + 2);
        int[] riverBiomes = this.riverParent.getInts(x - 1, z - 1, sizeX + 2, sizeZ + 2);
        int[] newBiomes = IntCache.getIntCache(sizeX * sizeZ);
        
        for(int k = 0; k < sizeZ; ++k) {
            for(int i = 0; i < sizeX; ++i) {
                this.initChunkSeed(i + x, k + z);
                int currentBiomeID = previousBiomes[i + 1 + (k + 1) * (sizeX + 2)];
    
                int neighborBiomeNegX = riverBiomes[i + 1 - 1 + (k + 1) * (sizeX + 2)];
                int neighborBiomeNegZ = riverBiomes[i + 1 + (k + 1 - 1) * (sizeX + 2)];
                int neighborBiomePosX = riverBiomes[i + 1 + 1 + (k + 1) * (sizeX + 2)];
                int neighborBiomePosZ = riverBiomes[i + 1 + (k + 1 + 1) * (sizeX + 2)];
                
                BiomeInterface currentBiome = (BiomeInterface) BiomeGenBase.biomeList[currentBiomeID];
                var riverShoreData = currentBiome.getRiverShoreBiomeData();
                Version version = BetterThanWorldGen.V1_0_0;
    
                if (riverShoreData.isPresent()
                        && ((neighborBiomeNegX > 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomeNegX]).isRiver())
                        || (neighborBiomeNegZ > 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomeNegZ]).isRiver())
                        || (neighborBiomePosX > 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomePosX]).isRiver())
                        || (neighborBiomePosZ > 0 && ((BiomeInterface) BiomeGenBase.biomeList[neighborBiomePosZ]).isRiver())))
                {
                    newBiomes[i + k * sizeX] = riverShoreData.get().get(version).biomeID;
                }
                else {
                    newBiomes[i + k * sizeX] = currentBiomeID;
                }
            }
        }
        
        return newBiomes;
    }
}
