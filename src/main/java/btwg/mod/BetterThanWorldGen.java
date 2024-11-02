package btwg.mod;

import btw.AddonHandler;
import btw.BTWAddon;
import btwg.api.configuration.Version;
import btwg.api.world.generate.ChunkProvider;
import btwg.api.world.WorldTypeInterface;
import btwg.api.world.WorldTypeUtils;
import net.minecraft.src.WorldType;

public class BetterThanWorldGen extends BTWAddon {
    private static BetterThanWorldGen instance;
    private static final String MODID = "btwg";
    
    public static final Version V1_0_0 = new Version(MODID, 1, 0, 0);
    
    public static final WorldType BTWG_WORLD_TYPE = (WorldType) ((WorldTypeInterface) WorldTypeUtils.createWorldType(8, "btwg"))
            .setChunkProviderOverworld(ChunkProvider::new);
    
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
        // Validate current version
        Version.fromString(this.getModID(), this.getVersionString());
        
        BiomeConfiguration.initBiomes();
    }
    
    public static BetterThanWorldGen getInstance() {
        return instance;
    }
}