package btwg.mixin;

import btw.block.blocks.ChewedLogBlock;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChewedLogBlock.class)
public abstract class ChewedLogBlockMixin extends Block {
    protected ChewedLogBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void modifyBoundingBox(int blockID, String sideTexture, String topTexture, String stumpTopTexture, Block logSpike, CallbackInfo ci) {
        this.hideFromEMI();
    }
}
