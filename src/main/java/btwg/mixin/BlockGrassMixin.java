package btwg.mixin;

import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockGrass.class)
public abstract class BlockGrassMixin extends Block {
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
    private Icon iconGrassSideOverlay;

    protected BlockGrassMixin(int id, Material material) {
        super(id, material);
    }

    @ModifyConstant(method = "canGrassSurviveAtLocation(Lnet/minecraft/src/World;III)Z", constant = @Constant(intValue = 9))
    private static int modifyMinimumLightLevel(int value) {
        return 4;
    }

    @ModifyConstant(method = "canGrassSurviveAtLocation(Lnet/minecraft/src/World;III)Z", constant = @Constant(intValue = 2))
    private static int modifyMinimumOpacity(int value) {
        return 1;
    }

    @Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("TAIL"))
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register, CallbackInfo ci) {
        this.setTextureName(BetterThanWorldGen.MODID + ":stone/rhyolite/grass");

        this.blockIcon = register.registerIcon(this.getTextureName() + "_side");
        this.iconGrassTop = register.registerIcon(this.getTextureName() + "_top");
        this.iconSnowSide = register.registerIcon(this.getTextureName() + "_side_snowed");
        this.iconGrassSideOverlay = register.registerIcon(this.getTextureName() + "_side_overlay");
        this.iconGrassTopSparse = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/grass_sparse_top_overlay");
        this.iconGrassTopSparseDirt = register.registerIcon(BetterThanWorldGen.MODID + ":stone/rhyolite/grass_sparse_top");
    }
}
