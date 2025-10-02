package btwg.mixin;

import net.minecraft.src.ComponentVillageStartPiece;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.ComponentVillageWell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ComponentVillageWell.class)
public abstract class ComponentVillageWellMixin extends StructureComponent {
    @Inject(method = "<init>(Lnet/minecraft/src/ComponentVillageStartPiece;ILjava/util/Random;II)V", at = @At("RETURN"))
    public void modifyBoundingBox(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, int par4, int par5, CallbackInfo ci) {
        switch (this.coordBaseMode) {
            case 0:
            case 2: {
                this.boundingBox = new StructureBoundingBox(par4, 100, par5, par4 + 6 - 1, 140, par5 + 6 - 1);
                break;
            }
            default: {
                this.boundingBox = new StructureBoundingBox(par4, 100, par5, par4 + 6 - 1, 140, par5 + 6 - 1);
            }
        }
    }
}
