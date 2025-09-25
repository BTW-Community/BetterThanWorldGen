package btwg.api.world.generate;

import btwg.api.world.generate.noise.NoiseProvider;
import net.minecraft.src.*;

import java.util.List;

public class ChunkProvider<T extends NoiseProvider> implements IChunkProvider {
    private final T noiseProvider;

    public ChunkProvider(T noiseProvider) {
        this.noiseProvider = noiseProvider;
    }

    public long getSeed() {
        return this.noiseProvider.seed;
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        return null;
    }

    @Override
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ) {

    }

    @Override
    public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        return this.provideChunk(chunkX, chunkZ);
    }

    @Override
    public boolean saveChunks(boolean bl, IProgressUpdate progressUpdate) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "BTWGChunkProvider";
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType enumCreatureType, int x, int y, int z) {
        return List.of();
    }

    @Override
    public ChunkPosition findClosestStructure(World world, String string, int x, int y, int z) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int chunkX, int chunkZ) {

    }

    @Override
    public void saveExtraData() {}
}
