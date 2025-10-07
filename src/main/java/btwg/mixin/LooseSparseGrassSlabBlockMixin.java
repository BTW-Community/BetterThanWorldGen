package btwg.mixin;

import btw.block.blocks.FallingSlabBlock;
import btw.block.blocks.LooseSparseGrassSlabBlock;
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

@Mixin(LooseSparseGrassSlabBlock.class)
public abstract class LooseSparseGrassSlabBlockMixin extends FallingSlabBlock {
    @Shadow
    @Environment(value= EnvType.CLIENT)
    private Icon iconGrassTopSparse;
    @Shadow
    @Environment(value=EnvType.CLIENT)
    private Icon iconDirtTopSparse;
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

    public LooseSparseGrassSlabBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "registerIcons", at = @At("TAIL"))
    @Environment(value=EnvType.CLIENT)
    public void registerIcons(IconRegister register, CallbackInfo ci) {
        this.blockIcon = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/loose_grass_side");
        this.iconSnowSide = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/loose_grass_side_snowed");
        this.iconSnowSideHalf = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/loose_grass_slab_side_snowed");
        this.iconGrassSideOverlay = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/loose_grass_side_overlay");
        this.iconGrassSideOverlayHalf = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/loose_grass_slab_side_overlay");
        this.iconGrassTopSparse = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/sparse_loose_grass_top_overlay");
        this.iconDirtTopSparse = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/sparse_loose_grass_top");
    }
}
