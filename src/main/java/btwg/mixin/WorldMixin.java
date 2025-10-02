package btwg.mixin;

import btwg.api.world.generate.ChunkProvider;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(World.class)
public class WorldMixin {
    @Inject(method = "findClosestStructureAll(Ljava/lang/String;IIIII)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    public void findClosestStructureAll(String par1Str, int par2, int par3, int par4, int chunkSearchRadius, int minAbandonment, CallbackInfoReturnable<List<ChunkPosition>> cir) {
        if (this.getChunkProvider() instanceof ChunkProvider || this.getChunkProvider() instanceof ChunkProviderServer) {
            IChunkProvider chunkProvider = this.getChunkProvider();

            if (chunkProvider instanceof ChunkProviderServer
                    && (((ChunkProviderServer)chunkProvider).getCurrentChunkProvider() instanceof ChunkProvider
                            || ((ChunkProviderServer)chunkProvider).getCurrentChunkProvider() instanceof ChunkProviderHell))
            {
                chunkProvider = ((ChunkProviderServer)chunkProvider).getCurrentChunkProvider();
            }

            MapGenStructure structureGenerator = null;

            if (chunkProvider instanceof ChunkProviderHell && par1Str.equals("Fortress")) {
                structureGenerator = ((ChunkProviderHell)chunkProvider).genNetherBridge;
            }
            else if (chunkProvider instanceof ChunkProvider) {
                switch (par1Str) {
                    case "Stronghold": {
                        structureGenerator = ((ChunkProvider)chunkProvider).getStrongholdGenerator();
                        break;
                    }
                    case "Village": {
                        structureGenerator = ((ChunkProvider)chunkProvider).getVillageGenerator();
                        break;
                    }
                    case "Mineshaft": {
                        structureGenerator = ((ChunkProvider)chunkProvider).getMineshaftGenerator();
                        break;
                    }
                    case "Temple": {
                        structureGenerator = ((ChunkProvider)chunkProvider).getScatteredFeatureGenerator();
                        break;
                    }
                    default: {
                        System.out.println("Unknown structure type: " + par1Str);
                        return;
                    }
                }
            }

            if (structureGenerator == null) {
                return;
            }

            int centerChunkX = par2 >> 4;
            int centerChunkZ = par4 >> 4;
            ArrayList<ChunkPosition> foundPositions = new ArrayList<ChunkPosition>();

            for (int radius = 0; radius <= chunkSearchRadius; ++radius) {
                ChunkPosition foundPosition;
                int chunkZ;
                int chunkX;
                int i;

                for (i = -radius; i <= radius; i += structureGenerator.getCheckRange()) {
                    chunkX = centerChunkX + i;
                    chunkZ = centerChunkZ + radius;
                    foundPosition = this.getChunkPosition(chunkX, chunkZ, structureGenerator, minAbandonment);

                    if (foundPosition == null || foundPositions.contains(foundPosition)) continue;

                    foundPositions.add(foundPosition);
                }

                for (i = -radius; i <= radius; i += structureGenerator.getCheckRange()) {
                    chunkX = centerChunkX + i;
                    chunkZ = centerChunkZ - radius;
                    foundPosition = this.getChunkPosition(chunkX, chunkZ, structureGenerator, minAbandonment);

                    if (foundPosition == null || foundPositions.contains(foundPosition)) continue;

                    foundPositions.add(foundPosition);
                }

                for (i = -radius + 1; i <= radius - 1; i += structureGenerator.getCheckRange()) {
                    chunkX = centerChunkX + radius;
                    chunkZ = centerChunkZ + i;
                    foundPosition = this.getChunkPosition(chunkX, chunkZ, structureGenerator, minAbandonment);

                    if (foundPosition == null || foundPositions.contains(foundPosition)) continue;

                    foundPositions.add(foundPosition);
                }
                for (i = -radius + 1; i <= radius - 1; i += structureGenerator.getCheckRange()) {
                    chunkX = centerChunkX - radius;
                    chunkZ = centerChunkZ + i;
                    foundPosition = this.getChunkPosition(chunkX, chunkZ, structureGenerator, minAbandonment);

                    if (foundPosition == null || foundPositions.contains(foundPosition)) continue;

                    foundPositions.add(foundPosition);
                }
            }

            cir.setReturnValue(foundPositions);
            cir.cancel();
        }
    }

    @Shadow
    private ChunkPosition getChunkPosition(int chunkX, int chunkZ, MapGenStructure structureGenerator, int minAbandonment) {
        return null;
    }

    @Shadow
    public IChunkProvider getChunkProvider() {
        return null;
    }
}
