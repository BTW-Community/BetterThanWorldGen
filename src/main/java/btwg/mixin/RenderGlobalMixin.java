package btwg.mixin;

import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin {
    @Shadow
    private WorldClient theWorld;
    @Shadow
    private Minecraft mc;

    @Inject(method = "loadRenderers()V", at = @At("RETURN"))
    public void setLeavesGraphicsLevel(CallbackInfo ci) {
        if (this.theWorld != null) {
            ((BlockLeaves) BTWGBlocks.leaves).setGraphicsLevel(this.mc.gameSettings.fancyGraphics);
        }
    }
}
