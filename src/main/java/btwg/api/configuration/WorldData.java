package btwg.api.configuration;

import btw.world.util.data.DataEntry;
import btw.world.util.data.DataProvider;
import btwg.api.world.generate.ChunkProviderRegistry;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.ResourceLocation;
import net.minecraft.src.World;

public class WorldData {
    public static void initData() {}
    public static final String WORLD_GEN_DATA_Name = "btwg_data";
    public static final DataEntry.WorldDataEntry<WorldData> WORLD_GEN_DATA = DataProvider.getBuilder(WorldData.class)
            .name(WORLD_GEN_DATA_Name)
            .defaultSupplier(WorldData::new)
            .readNBT(WorldData::readFromNBT)
            .writeNBT((tag, value) -> value.writeToNBT(tag))
            .global()
            .build();

    private boolean isBTWG = true;
    private ResourceLocation overworldChunkProviderID = ChunkProviderRegistry.BTWG;

    public static WorldData readFromNBT(NBTTagCompound tag) {
        return new WorldData();
    }

    public void writeToNBT(NBTTagCompound tag) {

    }

    public ResourceLocation getOverworldChunkProviderID() {
        return overworldChunkProviderID;
    }

    public IChunkProvider getOverworldChunkProvider(World world, long seed, boolean mapFeaturesEnabled) {
        return ChunkProviderRegistry.createChunkProvider(this.overworldChunkProviderID, world, seed, mapFeaturesEnabled, this);
    }

    public boolean isBTWG() {
        return isBTWG;
    }
}
