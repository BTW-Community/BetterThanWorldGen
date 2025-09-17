package btwg.mixin;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class GuiInGameMixin extends Gui {
    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method = "renderModDebugOverlay(I)V", at = @At("TAIL"))
    public void addBiomeDebug(int y, CallbackInfo ci) {
        FontRenderer fontRenderer = this.mc.fontRenderer;
        BiomeGenBase currentBiome = this.mc.theWorld.getBiomeGenForCoords((int) this.mc.thePlayer.posX, (int) this.mc.thePlayer.posZ);
        this.drawString(fontRenderer, String.format("Biome: " + currentBiome.biomeName), 2, y + 50, 0xE0E0E0);
    }
}
