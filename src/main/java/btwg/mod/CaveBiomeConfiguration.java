package btwg.mod;

import btwg.api.biome.CaveBiome;
import btwg.api.biome.CaveNoiseVector;
import net.minecraft.src.Block;
import net.minecraft.src.ResourceLocation;

public abstract class CaveBiomeConfiguration {
    // Temperature constants
    public static final double COLD = 0.15;
    public static final double TEMPERATE = 0.5;
    public static final double HOT = 0.85;

    // Humidity constants
    public static final double ARID = 0.15;
    public static final double SEMI_HUMID = 0.5;
    public static final double VERY_HUMID = 0.85;

    //------ Middle Values ------//

    public static final CaveBiome BARREN_CAVERNS = new CaveBiome(loc("barren_caverns"),
            new CaveNoiseVector(
                    TEMPERATE,
                    SEMI_HUMID,
                    false
            ));

    public static final CaveBiome VOLCANIC_FISSURE = new CaveBiome(loc("volcanic_fissure"),
            new CaveNoiseVector(
                    TEMPERATE,
                    SEMI_HUMID,
                    true
            ));

    //------ High Temperature, High Humidity ------//

    public static final CaveBiome LUSH_CAVES = new CaveBiome(loc("lush_caves"),
            new CaveNoiseVector(
                    HOT,
                    VERY_HUMID,
                    false
            ));

    public static final CaveBiome UNDERGROUND_JUNGLE = new CaveBiome(loc("underground_jungle"),
            new CaveNoiseVector(
                    HOT,
                    VERY_HUMID,
                    true
            ));

    //------ High Temperature, Low Humidity ------//

    public static final CaveBiome SANDSTORM_CAVES = new CaveBiome(loc("sandstorm_caves"),
            new CaveNoiseVector(
                    HOT,
                    ARID,
                    false
            ))
            .setTopBlockID((short) Block.sandStone.blockID)
            .setFillerBlockID((short) Block.sandStone.blockID);

    public static final CaveBiome INFESTED_DEPTHS = new CaveBiome(loc("infested_depths"),
            new CaveNoiseVector(
                    HOT,
                    ARID,
                    true
            ));

    //------ Low Temperature, High Humidity ------//

    public static final CaveBiome TIMELESS_GROTTO = new CaveBiome(loc("timeless_grotto"),
            new CaveNoiseVector(
                    COLD,
                    VERY_HUMID,
                    false
            ));

    public static final CaveBiome FUNGAL_GROTTO = new CaveBiome(loc("fungal_grotto"),
            new CaveNoiseVector(
                    COLD,
                    VERY_HUMID,
                    true
            ));

    //------ Low Temperature, Low Humidity ------//

    public static final CaveBiome FROZEN_CAVERNS = new CaveBiome(loc("frozen_caverns"),
            new CaveNoiseVector(
                    COLD,
                    ARID,
                    false
            ));

    public static final CaveBiome CRYSTALLINE_CAVERNS = new CaveBiome(loc("crystalline_caverns"),
            new CaveNoiseVector(
                    COLD,
                    ARID,
                    true
            ));

    public static void initCaveBiomes() {}

    public static ResourceLocation loc(String name) {
        return new ResourceLocation(BetterThanWorldGen.MODID, name);
    }
}
