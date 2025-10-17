package btwg.api.biome;

import btwg.api.biome.data.BiomeData;
import btwg.api.world.surfacer.cave.CaveSurfacer;
import net.minecraft.src.ResourceLocation;
import net.minecraft.src.StringTranslate;

import java.util.ArrayList;
import java.util.Optional;

public class CaveBiome {
    public static ArrayList<CaveBiome> caveBiomeList = new ArrayList<>();

    public final ResourceLocation id;
    public final CaveNoiseParameterTarget noiseTarget;

    private BiomeData<CaveSurfacer> surfacerData;

    public short topBlockID;
    public byte topBlockMetadata;

    public short fillerBlockID;
    public byte fillerBlockMetadata;

    public CaveBiome(ResourceLocation id, CaveNoiseVector noiseTarget) {
        this.id = id;
        this.noiseTarget = new CaveNoiseParameterTarget(noiseTarget);

        caveBiomeList.add(this);
    }

    public CaveBiome setTopBlockID(short topBlockID) {
        this.topBlockID = topBlockID;
        return this;
    }

    public CaveBiome setTopBlockMetadata(byte topBlockMetadata) {
        this.topBlockMetadata = topBlockMetadata;
        return this;
    }

    public CaveBiome setFillerBlockID(short fillerBlockID) {
        this.fillerBlockID = fillerBlockID;
        return this;
    }

    public CaveBiome setFillerBlockMetadata(byte fillerBlockMetadata) {
        this.fillerBlockMetadata = fillerBlockMetadata;
        return this;
    }

    public Optional<BiomeData<CaveSurfacer>> getSurfacer() {
        return Optional.ofNullable (this.surfacerData);
    }

    public CaveBiome setSurfacer(BiomeData<CaveSurfacer> surfacer) {
        this.surfacerData = surfacer;
        return this;
    }

    public CaveBiome setSurfacer(CaveSurfacer surfacer) {
        return this.setSurfacer(new BiomeData<>(surfacer));
    }

    public String getName() {
        return StringTranslate.getInstance().translateKey("biome." + this.id.getResourceDomain() + "." + this.id.getResourcePath());
    }
}
