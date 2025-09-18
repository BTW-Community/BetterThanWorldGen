package btwg.api.biome;

import btw.entity.mob.JungleSpiderEntity;
import btwg.api.world.feature.PlantDistributor;
import btwg.api.world.feature.TreeDistributor;
import net.minecraft.src.*;

import java.util.ArrayList;

public class BTWGBiome extends BiomeGenBase {
    private TreeDistributor treeDistributor = new TreeDistributor() {};
    private PlantDistributor grassDistributor = new PlantDistributor() {};
    
    public BTWGBiome(int par1, ResourceLocation resourceLocation) {
        super(par1);
        this.theBiomeDecorator = new BTWGBiomeDecorator(this);
        this.biomeName = resourceLocation.toString();
    }

    public BTWGBiome addClimate(Climate climate) {
        Climate.climateBiomeMap
                .computeIfAbsent(climate, k -> new ArrayList<>())
                .add(new Climate.ClimateEntry(this, 1.0F));
        return this;
    }

    public BTWGBiome addClimate(Climate climate, float weight) {
        Climate.climateBiomeMap
                .computeIfAbsent(climate, k -> new ArrayList<>())
                .add(new Climate.ClimateEntry(this, weight));
        return this;
    }
    
    public BTWGBiome setTemperatureAndRainfall(float temp, float rainfall) {
        if (temp > 0.1F && temp < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else {
            this.temperature = temp;
            this.rainfall = rainfall;
            return this;
        }
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

    public BTWGBiome setSnow() {
        this.setEnableSnow();
        return this;
    }

    public BTWGBiome setNoRain() {
        this.setDisableRain();
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
}
