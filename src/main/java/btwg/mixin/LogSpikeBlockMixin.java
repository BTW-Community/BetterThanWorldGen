package btwg.mixin;

import btw.block.blocks.LogSpikeBlock;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogSpikeBlock.class)
public abstract class LogSpikeBlockMixin extends Block {
    protected LogSpikeBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    public void modifyBoundingBox(int blockID, String sideTexture, String topTexture, CallbackInfo ci) {
        this.hideFromEMI();
    }
}
