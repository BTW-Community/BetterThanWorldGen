package btwg.api.biome.layer;

import btw.util.RandomSelector;

import btwg.api.biome.Climate;
import btwg.api.biome.layer.util.LayerRandom;
import btwg.api.configuration.WorldData;
import btwg.mod.BiomeConfiguration;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class BiomeLayer extends BTWGBaseLayer {
    public Map<Climate, ArrayList<Climate.ClimateEntry>> climateBiomeMap;

    private final GenLayer climates;
    private final LayerRandom layerRandom;
    
    public BiomeLayer(long seed, GenLayer parent, GenLayer climates, WorldData generatorOptions) {
        super(seed, parent, generatorOptions);
        this.climates = climates;

        this.layerRandom = new LayerRandom(this);
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] baseLand = this.parent.getInts(x, z, sizeX, sizeZ);
        int[] climates = this.climates.getInts(x, z, sizeX, sizeZ);
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
                    int climateID = climates[i + k * sizeX] * -1;

                    Climate climate = Climate.getClimate(climateID).orElseThrow();

                    ArrayList<Climate.ClimateEntry> biomeList = this.climateBiomeMap.get(climate);

                    ToDoubleFunction<Climate.ClimateEntry> weighter = Climate.ClimateEntry::weight;
                    RandomSelector<Climate.ClimateEntry> selector = RandomSelector.weighted(biomeList, weighter);

                    biomes[i + k * sizeX] = selector.next(this.layerRandom).biome().biomeID;
                }
            }
        }
        
        return biomes;
    }
}
