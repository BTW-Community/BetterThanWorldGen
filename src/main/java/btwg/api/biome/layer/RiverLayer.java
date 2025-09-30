package btwg.api.biome.layer;

import btwg.api.biome.DefaultBiomes;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class RiverLayer extends GenLayer {
    public RiverLayer(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int i, int j, int k, int l) {
        int n = i - 1;
        int n2 = j - 1;
        int n3 = k + 2;
        int n4 = l + 2;
        int[] nArray = this.parent.getInts(n, n2, n3, n4);
        int[] nArray2 = IntCache.getIntCache(k * l);
        for (int i2 = 0; i2 < l; ++i2) {
            for (int i3 = 0; i3 < k; ++i3) {
                int n5 = nArray[i3 + 0 + (i2 + 1) * n3];
                int n6 = nArray[i3 + 2 + (i2 + 1) * n3];
                int n7 = nArray[i3 + 1 + (i2 + 0) * n3];
                int n8 = nArray[i3 + 1 + (i2 + 2) * n3];
                int n9 = nArray[i3 + 1 + (i2 + 1) * n3];
                nArray2[i3 + i2 * k] = n9 == 0 || n5 == 0 || n6 == 0 || n7 == 0 || n8 == 0 || n9 != n5 || n9 != n7 || n9 != n6 || n9 != n8 ? /*DefaultBiomes.RIVER.biomeID*/ BiomeGenBase.river.biomeID : -1;
            }
        }
        return nArray2;
    }
}
