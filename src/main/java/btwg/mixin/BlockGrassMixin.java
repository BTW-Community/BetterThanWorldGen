package btwg.mixin;

import net.minecraft.src.Block;
import net.minecraft.src.BlockGrass;
import net.minecraft.src.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockGrass.class)
public abstract class BlockGrassMixin extends Block {
    protected BlockGrassMixin(int id, Material material) {
        super(id, material);
    }

    @ModifyConstant(method = "canGrassSurviveAtLocation(Lnet/minecraft/src/World;III)Z", constant = @Constant(intValue = 9))
    private static int modifyGrassSurviveAtLocation(int value) {
        return 4;
    }
}
