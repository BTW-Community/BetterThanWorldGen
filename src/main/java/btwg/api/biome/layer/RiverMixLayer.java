//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package btwg.api.biome.layer;

import btwg.api.biome.BiomeInterface;
import btwg.api.configuration.WorldData;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class RiverMixLayer extends BTWGBaseLayer {
    private final GenLayer biomePatternGeneratorChain;
    private final GenLayer riverPatternGeneratorChain;
    
    public RiverMixLayer(long seed, GenLayer biomeLayer, GenLayer riverLayer, WorldData generatorOptions) {
        super(seed, generatorOptions);
        this.biomePatternGeneratorChain = biomeLayer;
        this.riverPatternGeneratorChain = riverLayer;
    }
    
    public void initWorldGenSeed(long l) {
        this.biomePatternGeneratorChain.initWorldGenSeed(l);
        this.riverPatternGeneratorChain.initWorldGenSeed(l);
        super.initWorldGenSeed(l);
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] previousBiomes = this.biomePatternGeneratorChain.getInts(x, z, sizeX, sizeZ);
        int[] riverBiomes = this.riverPatternGeneratorChain.getInts(x, z, sizeX, sizeZ);
        int[] newBiomes = IntCache.getIntCache(sizeX * sizeZ);
        
        for(int i = 0; i < sizeX * sizeZ; ++i) {
            if (previousBiomes[i] == BiomeGenBase.ocean.biomeID) {
                newBiomes[i] = previousBiomes[i];
            }
            else if (riverBiomes[i] >= 0) {
                BiomeInterface biome = (BiomeInterface) BiomeGenBase.biomeList[previousBiomes[i]];
                var riverData = biome.getRiverBiomeData();
                
                if (riverData.isPresent()) {
                    newBiomes[i] = riverData.get().get(BetterThanWorldGen.getCurrentVersion()).biomeID;
                }
                else if (previousBiomes[i] != BiomeGenBase.mushroomIsland.biomeID && previousBiomes[i] != BiomeGenBase.mushroomIslandShore.biomeID) {
                    newBiomes[i] = riverBiomes[i];
                }
                else {
                    newBiomes[i] = BiomeGenBase.mushroomIslandShore.biomeID;
                }
            }
            else {
                newBiomes[i] = previousBiomes[i];
            }
        }
        
        return newBiomes;
    }
}
