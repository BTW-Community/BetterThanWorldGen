package btwg.mixin;

import btwg.api.configuration.WorldData;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldProvider.class)
public class WorldProviderMixin {
    @Shadow
    public World worldObj;
    @Shadow
    public WorldType terrainType;
    
    @Inject(method = "createChunkGenerator", at = @At("RETURN"), cancellable = true)
    public void createChunkGenerator(CallbackInfoReturnable<IChunkProvider> cir) {
        if (this.worldObj.getWorldInfo().getTerrainType() != WorldType.FLAT) {
            WorldData worldData = this.worldObj.getData(WorldData.WORLD_GEN_DATA);
            cir.setReturnValue(worldData.getOverworldChunkProvider(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled()));
        }
    }
}
