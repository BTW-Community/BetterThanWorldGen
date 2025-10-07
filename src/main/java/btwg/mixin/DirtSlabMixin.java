package btwg.mixin;

import btw.block.blocks.AttachedSlabBlock;
import btw.block.blocks.DirtSlabBlock;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DirtSlabBlock.class)
public abstract class DirtSlabMixin extends AttachedSlabBlock {
    protected DirtSlabMixin(int blockID, Material material) {
        super(blockID, material);
    }

    @Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("TAIL"))
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register, CallbackInfo ci) {
        this.blockIcon = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/regolith");
    }
}
