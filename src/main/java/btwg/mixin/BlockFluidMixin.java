package btwg.mixin;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockFluid.class)
public abstract class BlockFluidMixin extends Block {
    @Shadow
    private Icon[] theIcon;

    protected BlockFluidMixin(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    @Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("HEAD"), cancellable = true)
    public void registerIcons(IconRegister iconRegister, CallbackInfo ci) {
        if (this.blockMaterial != Material.lava) {
            this.theIcon = new Icon[]{iconRegister.registerIcon("btwg:water_still"), iconRegister.registerIcon("btwg:water_flow")};
            ci.cancel();
        }
    }
}
