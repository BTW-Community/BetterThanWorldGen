package btwg.mixin;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockSandStone.class)
public abstract class BlockSandstoneMixin extends Block {
    @Shadow
    private Icon field_94404_cP; // Bottom icon

    protected BlockSandstoneMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (y < 255 && blockAccess.getBlockId(x, y + 1, z) == this.blockID) {
            return this.field_94404_cP;
        } else {
            return super.getBlockTexture(blockAccess, x, y, z, side);
        }
    }
}
