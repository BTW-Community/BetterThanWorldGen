package btwg.mixin;

import btw.block.blocks.AttachedSlabBlock;
import btw.block.blocks.GrassSlabBlock;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GrassSlabBlock.class)
public abstract class GrassSlabBlockMixin extends AttachedSlabBlock {
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTop;
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTopSparse;
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassTopSparseDirt;
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconSnowSide;
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconSnowSideHalf;
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassSideOverlay;
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconGrassSideOverlayHalf;

    protected GrassSlabBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("TAIL"))
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register, CallbackInfo ci) {
        this.setTextureName(BetterThanWorldGen.MODID + ":stone/rhyolite/grass");

        this.blockIcon = register.registerIcon(this.getTextureName() + "_slab_side");
        this.iconGrassTop = register.registerIcon(this.getTextureName() + "_top");
        this.iconSnowSide = register.registerIcon(this.getTextureName() + "_side_snowed");
        this.iconSnowSideHalf = register.registerIcon(this.getTextureName() + "_slab_side_snowed");
        this.iconGrassSideOverlay = register.registerIcon(this.getTextureName() + "_side_overlay");
        this.iconGrassSideOverlayHalf = register.registerIcon(this.getTextureName() + "_slab_side_overlay");
        this.iconGrassTopSparse = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/sparse_grass_top_overlay");
        this.iconGrassTopSparseDirt = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/sparse_grass_top");
    }
}
