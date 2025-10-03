package btwg.mixin;

import btwg.api.biome.BTWGBiome;
import btwg.api.biome.BiomeNoiseVector;
import btwg.api.world.generate.ChunkProvider;
import net.minecraft.server.MinecraftServer;
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
        y += 38;
        int j = 12;

        FontRenderer fontRenderer = this.mc.fontRenderer;
        BiomeGenBase currentBiome = this.mc.theWorld.getBiomeGenForCoords((int) this.mc.thePlayer.posX, (int) this.mc.thePlayer.posZ);
        this.drawString(fontRenderer, String.format("Biome: " + currentBiome.biomeName), 2, y += j, 0xE0E0E0);

        ChunkProviderServer serverChunkProvider = (ChunkProviderServer) MinecraftServer.getServer().worldServerForDimension(0).getChunkProvider();
        IChunkProvider chunkProvider = serverChunkProvider.getCurrentChunkProvider();

        if (chunkProvider instanceof ChunkProvider btwgProvider) {
            BiomeNoiseVector vector = btwgProvider.getNoiseProvider().getBiomeVector((int) this.mc.thePlayer.posX, (int) this.mc.thePlayer.posZ);
            this.drawString(fontRenderer, "Biome Noise:", 2, y += j + 3, 0xE0E0E0);
            this.drawString(fontRenderer, String.format("T: %.2f, H: %.2f, C: %.2f, E: %.2f, W: %.2f", vector.temperature(), vector.humidity(), vector.continentalness(), vector.erosion(), vector.weirdness()), 2, y += j, 0xE0E0E0);

            if (!(currentBiome instanceof BTWGBiome)) {
                return;
            }

            BiomeNoiseVector target = ((BTWGBiome) currentBiome).noiseTarget.target();
            this.drawString(fontRenderer, "Biome Targets:", 2, y += j + 3, 0xE0E0E0);
            this.drawString(fontRenderer, String.format("T: %.2f, H: %.2f, C: %.2f, E: %.2f, W: %.2f", target.temperature(), target.humidity(), target.continentalness(), target.erosion(), target.weirdness()), 2, y += j, 0xE0E0E0);

            this.drawString(fontRenderer, "Weighted Distance:", 2, y += j + 3, 0xE0E0E0);
            this.drawString(fontRenderer, String.format("%.2f", ((BTWGBiome) currentBiome).noiseTarget.distanceSqFromTarget(vector)), 2, y += j, 0xE0E0E0);
        }
    }
}
