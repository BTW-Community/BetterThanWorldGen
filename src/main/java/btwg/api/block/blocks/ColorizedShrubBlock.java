package btwg.api.block.blocks;

import com.prupe.mcpatcher.cc.ColorizeBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.IBlockAccess;

public class ColorizedShrubBlock extends ShrubBlock {
    public ColorizedShrubBlock(int id) {
        super(id);
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    @Override
    public int getBlockColor() {
        if (ColorizeBlock.colorizeBlock(this)) {
            return ColorizeBlock.blockColor;
        }
        else {
            double var1 = 0.5D;
            double var3 = 1.0D;
            return ColorizerGrass.getGrassColor(var1, var3);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getRenderColor(int metadata) {
        if (ColorizeBlock.colorizeBlock(this, metadata)) {
            return ColorizeBlock.blockColor;
        }
        else {
            return ColorizerFoliage.getFoliageColorBasic();
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        if (ColorizeBlock.colorizeBlock(this, blockAccess, x, y, z)) {
            return ColorizeBlock.blockColor;
        }
        else {
            return blockAccess.getBiomeGenForCoords(x, z).getBiomeGrassColor();
        }
    }
}
