package btwg.mixin;

import btw.block.blocks.FallingSlabBlock;
import btw.block.blocks.LooseDirtSlabBlock;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LooseDirtSlabBlock.class)
public abstract class LooseDirtSlabBlockMixin extends FallingSlabBlock {
    public LooseDirtSlabBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("TAIL"))
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register, CallbackInfo ci) {
        this.blockIcon = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/loose_regolith");
    }
}
