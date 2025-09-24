package btwg.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.BlockLeavesBase;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockLeavesBase.class)
public abstract class BlockLeavesBaseMixin extends Block {
    @Shadow
    public boolean graphicsLevel;

    protected BlockLeavesBaseMixin(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    @Inject(method = "shouldSideBeRendered(Lnet/minecraft/src/IBlockAccess;IIII)Z", at = @At("HEAD"), cancellable = true)
    @Environment(EnvType.CLIENT)
    public void shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        int adjacentBlockID = blockAccess.getBlockId(x, y, z);
        Block adjacentBlock = Block.blocksList[adjacentBlockID];
        boolean isAdjacentLeaf = adjacentBlock != null && adjacentBlock.isLeafBlock(blockAccess, x, y, z);

        cir.setReturnValue((this.graphicsLevel || !isAdjacentLeaf) && super.shouldSideBeRendered(blockAccess, x, y, z, side));
        cir.cancel();
    }
}
