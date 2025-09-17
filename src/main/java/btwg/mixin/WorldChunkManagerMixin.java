package btwg.mixin;

import btwg.api.biome.layer.BTWGBaseLayer;
import btwg.api.configuration.WorldData;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.GenLayer;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldChunkManager.class)
public abstract class WorldChunkManagerMixin {
    @Shadow
    private GenLayer genBiomes;
    @Shadow
    private GenLayer biomeIndexLayer;
    
    @Inject(method = "<init>(Lnet/minecraft/src/World;)V", at = @At("TAIL"))
    public void setBTWGBiomes(World world, CallbackInfo ci) {
        WorldData worldData = world.getData(WorldData.WORLD_GEN_DATA);
        if (worldData.isBTWG()) {
            GenLayer[] var4 = BTWGBaseLayer.initializeAllBiomeGenerators(world.getSeed(), world.worldInfo.getTerrainType(), worldData);
            this.genBiomes = var4[0];
            this.biomeIndexLayer = var4[1];
        }
    }
}
