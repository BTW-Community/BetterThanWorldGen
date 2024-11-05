//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package btwg.api.biome.layer;

import btwg.api.configuration.WorldData;
import net.minecraft.src.IntCache;

public class ContinentsLayer extends BTWGBaseLayer {
    public ContinentsLayer(long seed, WorldData generatorOptions) {
        super(seed, generatorOptions);
    }
    
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        int[] var5 = IntCache.getIntCache(sizeX * sizeZ);
        
        for(int k = 0; k < sizeZ; ++k) {
            for(int i = 0; i < sizeX; ++i) {
                this.initChunkSeed(x + i, z + k);
                var5[i + k * sizeX] = this.nextInt(2) == 0 ? 1 : 0;
            }
        }
        
        if (x > -sizeX && x <= 0 && z > -sizeZ && z <= 0) {
            var5[-x + -z * sizeX] = 1;
        }
        
        return var5;
    }
}
