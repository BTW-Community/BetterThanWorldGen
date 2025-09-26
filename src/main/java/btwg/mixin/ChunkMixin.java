package btwg.mixin;

import net.minecraft.src.Chunk;
import net.minecraft.src.ExtendedBlockStorage;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chunk.class)
public class ChunkMixin {
    @Shadow
    private ExtendedBlockStorage[] storageArrays;

    @Inject(method = "<init>(Lnet/minecraft/src/World;[S[BII)V", at = @At("TAIL"))
    public void init(World world, short[] blockIDs, byte[] metadata, int chunkX, int chunkZ, CallbackInfo ci) {
        this.storageArrays = new ExtendedBlockStorage[16];

        int height = blockIDs.length / 256;
        for (int i = 0; i < 16; ++i) {
            for (int k = 0; k < 16; ++k) {
                for (int j = 0; j < height; ++j) {
                    int index = (i * 16 + k) * height + j;

                    short blockID = blockIDs[index];

                    if (blockID == 0) continue;

                    int ySectorIndex = j >> 4;

                    if (this.storageArrays[ySectorIndex] == null) {
                        this.storageArrays[ySectorIndex] = new ExtendedBlockStorage(ySectorIndex << 4, !world.provider.hasNoSky);
                    }

                    byte meta = metadata[index];
                    this.storageArrays[ySectorIndex].setExtBlockID(i, j & 0xF, k, blockID);
                    this.storageArrays[ySectorIndex].setExtBlockMetadata(i, j & 0xF, k, meta);
                }
            }
        }
    }
}
