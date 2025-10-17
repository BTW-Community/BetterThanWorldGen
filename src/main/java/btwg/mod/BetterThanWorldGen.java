package btwg.mod;

import btw.AddonHandler;
import btw.BTWAddon;
import btwg.api.biome.DefaultBiomes;
import btwg.api.client.DebugRegistry;
import btwg.api.configuration.Version;
import btwg.api.configuration.WorldData;
import btwg.mod.block.BTWGBlocks;
import btwg.mod.crafting.Recipes;
import btwg.mod.item.Tags;
import btwg.mod.item.BTWGItems;

public class BetterThanWorldGen extends BTWAddon {
    private static BetterThanWorldGen instance;
    public static final String MODID = "btwg";
    public static final String NAME = "Better Than World Gen";
    
    public static final Version V1_0_0 = new Version(MODID, 1, 0, 0);
    
    private static Version currentVersion;
    
    public BetterThanWorldGen() {
        super();
    }
    
    @Override
    public void preInitialize() {
        instance = this;
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        
        if (!this.getModID().equals(MODID)) {
            throw new IllegalStateException("Mod id does not match records!");
        }
        
        //currentVersion = Version.fromString(this.getModID(), this.getVersionString()).orElseThrow();

        BTWGBlocks.initBlocks();
        BTWGItems.initItems();

        Tags.modifyTags();
        Recipes.initRecipes();

        WorldData.initData();

        DefaultBiomes.initBiomes();
        BiomeConfiguration.initBiomes();
        CaveBiomeConfiguration.initCaveBiomes();

        DebugRegistry.init();
    }

    @Override
    public void postInitialize() {
    }
    
    public static Version getCurrentVersion() {
        return currentVersion;
    }
    
    public static BetterThanWorldGen getInstance() {
        return instance;
    }
}