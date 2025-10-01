package btwg.api.biome;

import btw.entity.mob.JungleSpiderEntity;
import btwg.api.world.feature.PlantDistributor;
import btwg.api.world.feature.TreeDistributor;
import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BTWGBiome extends BiomeGenBase {
    public static final Set<BTWGBiome> biomeList = new HashSet<>();

    private TreeDistributor treeDistributor = new TreeDistributor() {};
    private PlantDistributor grassDistributor = new PlantDistributor() {};

    public final BiomeNoiseParameterTarget noiseTarget;

    private Optional<Integer> grassColorOverride = Optional.empty();
    private Optional<Integer> foliageColorOverride = Optional.empty();
    
    public BTWGBiome(int id, ResourceLocation name, BiomeNoiseParameterTarget noiseTarget) {
        super(id);

        this.theBiomeDecorator = new BTWGBiomeDecorator(this);
        this.biomeName = name.toString();

        this.noiseTarget = noiseTarget;

        this.temperature = (float) noiseTarget.target().temperature();
        this.rainfall = (float) noiseTarget.target().humidity();

        if (this.temperature < 0.15F) {
            this.setEnableSnow();
        }

        if (this.rainfall < 0.1F) {
            this.setDisableRain();
        }

        biomeList.add(this);
    }
    
    public PlantDistributor getGrassDistributor() {
        return this.grassDistributor;
    }
    
    public BTWGBiome setGrassDistributor(PlantDistributor grassDistributor) {
        this.grassDistributor = grassDistributor;
        return this;
    }

    public TreeDistributor getTreeDistributor() {
        return this.treeDistributor;
    }

    public BTWGBiome setTreeDistributor(TreeDistributor treeDistributor) {
        this.treeDistributor = treeDistributor;
        return this;
    }

    public BTWGBiome setTopBlock(int blockID) {
        return this.setTopBlock(blockID, (byte) 0);
    }

    public BTWGBiome setTopBlock(int blockID, int meta) {
        this.topBlock = (short) blockID;
        this.topBlockMetadata = (byte) meta;
        return this;
    }

    public BTWGBiome setFillerBlock(int blockID) {
        return this.setFillerBlock(blockID, (byte) 0);
    }

    public BTWGBiome setFillerBlock(int blockID, int meta) {
        this.fillerBlock = (short) blockID;
        this.fillerBlockMetadata = (byte) meta;
        return this;
    }

    public BTWGBiome setNoLakes() {
        this.theBiomeDecorator.generateLakes = false;
        return this;
    }

    public BTWGBiome setNoAnimals() {
        this.spawnableCreatureList.clear();
        return this;
    }

    public BTWGBiome setNoLargeAnimals() {
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        return this;
    }

    public BTWGBiome setHasHorses() {
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        return this;
    }

    public BTWGBiome setHasWolves() {
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        return this;
    }

    public BTWGBiome setHasJungleSpiders() {
        this.spawnableMonsterList.add(new SpawnListEntry(JungleSpiderEntity.class, 2, 1, 1));
        return this;
    }

    public BTWGBiome setHasOcelots() {
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        return this;
    }

    public BTWGBiome setHasWitches() {
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 1, 1, 1));
        return this;
    }

    public BTWGBiome setHasSlimes() {
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
        return this;
    }

    public BTWGBiome setWaterColor(int color) {
        this.waterColorMultiplier = color;
        return this;
    }

    public BTWGBiome setGrassColor(int color) {
        this.grassColorOverride = Optional.of(color);
        return this;
    }

    public BTWGBiome setFoliageColor(int color) {
        this.foliageColorOverride = Optional.of(color);
        return this;
    }

    @Override
    public int getBiomeGrassColor() {
        return this.grassColorOverride.orElseGet(super::getBiomeGrassColor);
    }

    @Override
    public int getBiomeFoliageColor() {
        return this.foliageColorOverride.orElseGet(super::getBiomeFoliageColor);
    }
}
