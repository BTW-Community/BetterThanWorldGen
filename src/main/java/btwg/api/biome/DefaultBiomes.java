package btwg.api.biome;

import btwg.api.biome.data.BiomeData;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.Block;
import net.minecraft.src.ResourceLocation;

public class DefaultBiomes {
    public static final int RIVER_ID = 50;
    public static final int FROZEN_RIVER_ID = 51;
    public static final int BEACH_ID = 52;
    public static final int FROZEN_BEACH_ID = 53;

    public static final BiomeData.HeightData RIVER_HEIGHT = new BiomeData.HeightData(-0.75F, 0.0F);
    public static final BiomeData.HeightData FLAT_HEIGHT = new BiomeData.HeightData(0.0F, 0.0F);

    public static final BTWGBiome RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(RIVER_ID, loc("river")))
            .setHeightData(RIVER_HEIGHT)
            .setRiver());

    public static final BTWGBiome FROZEN_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(FROZEN_RIVER_ID, loc("frozen_river")))
            .setHeightData(RIVER_HEIGHT)
            .setRiver())
            .setTemperatureAndRainfall(0.0F, 0.5F)
            .setSnow();

    public static final BTWGBiome BEACH = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(BEACH_ID, loc("beach")))
            .setHeightData(FLAT_HEIGHT))
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID);

    public static final BTWGBiome FROZEN_BEACH = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(FROZEN_BEACH_ID, loc("frozen_beach")))
            .setHeightData(FLAT_HEIGHT))
            .setTemperatureAndRainfall(0.0F, 0.5F)
            .setSnow()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID);

    public static void initBiomes() {}

    public static ResourceLocation loc(String name) {
        return new ResourceLocation(BetterThanWorldGen.MODID, name);
    }
}
