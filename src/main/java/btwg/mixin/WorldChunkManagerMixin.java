package btwg.mixin;

import btwg.api.biome.layer.BaseLayer;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.GenLayer;
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
    
    @Inject(method = "<init>(JLnet/minecraft/src/WorldType;)V", at = @At("TAIL"))
    public void setBTWGBiomes(long seed, WorldType worldType, CallbackInfo ci) {
        if (worldType == BetterThanWorldGen.BTWG_WORLD_TYPE) {
            GenLayer[] var4 = BaseLayer.initializeAllBiomeGenerators(seed, worldType);
            this.genBiomes = var4[0];
            this.biomeIndexLayer = var4[1];
        }
    }
}
