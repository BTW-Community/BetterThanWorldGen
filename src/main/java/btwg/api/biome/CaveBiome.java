package btwg.api.biome;

import net.minecraft.src.ResourceLocation;

public class CaveBiome {
    private final ResourceLocation id;
    private final CaveNoiseVector target;

    public CaveBiome(ResourceLocation id, CaveNoiseVector target) {
        this.id = id;
        this.target = target;
    }
}
