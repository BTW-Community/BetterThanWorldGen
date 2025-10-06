package btwg.mixin;

import btwg.api.block.blocks.GrassyRegolithBlock;
import btwg.api.block.blocks.RegolithBlock;
import btwg.api.configuration.WorldData;
import net.minecraft.src.*;
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

    @Inject(method = "getAverageGroundLevel", at = @At("RETURN"), cancellable = true)
    public void getAverageGroundLevel(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.terrainType == WorldType.FLAT ? 4 : 120);
    }

    @Inject(method = "canCoordinateBeSpawn(II)Z", at = @At("RETURN"), cancellable = true)
    public void canCoordinateBeSpawn(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        int spawnBlockID = this.worldObj.getFirstUncoveredBlock(x, z);
        Block spawnBlock = Block.blocksList[spawnBlockID];

        if (spawnBlock instanceof GrassyRegolithBlock || spawnBlock instanceof RegolithBlock) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
