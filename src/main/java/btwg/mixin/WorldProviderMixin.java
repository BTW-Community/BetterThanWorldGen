package btwg.mixin;

import btwg.api.world.WorldProviderInterface;
import btwg.api.world.WorldTypeInterface;
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
public class WorldProviderMixin implements WorldProviderInterface {
    @Shadow
    public World worldObj;
    @Shadow
    public WorldType terrainType;
    @Shadow
    public String field_82913_c; // generator options
    
    @Inject(method = "createChunkGenerator", at = @At("RETURN"), cancellable = true)
    public void createChunkGenerator(CallbackInfoReturnable<IChunkProvider> cir) {
        if (this.worldObj.getWorldInfo().getTerrainType() != WorldType.FLAT) {
            cir.setReturnValue(((WorldTypeInterface) this.terrainType).getChunkProviderOverworld(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.getGeneratorOptions()));
        }
    }
    
    @Override
    public String getGeneratorOptions() {
        return this.field_82913_c;
    }
}
