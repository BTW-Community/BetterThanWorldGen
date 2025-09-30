package btwg.api.biome.layer;

import btwg.api.biome.Climate;
import btwg.api.configuration.WorldData;
import btwg.mod.BiomeConfiguration;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;
import net.minecraft.src.WorldType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClimateLayer extends BTWGBaseLayer {
    private List<Climate> allowedClimates;

    public ClimateLayer(long seed, GenLayer parent, WorldData worldData) {
        super(seed, parent, worldData);
    }

    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] climates = IntCache.getIntCache(sizeX * sizeZ);

        for(int k = 0; k < sizeZ; ++k) {
            for(int i = 0; i < sizeX; ++i) {
                this.initChunkSeed(i + x, k + z);

                climates[i + k * sizeX] = this.allowedClimates.get(this.nextInt(this.allowedClimates.size())).id() * -1;
            }
        }

        return climates;
    }
}
