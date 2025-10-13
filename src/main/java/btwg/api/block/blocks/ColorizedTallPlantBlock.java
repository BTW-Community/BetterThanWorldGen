package btwg.api.block.blocks;

import btwg.mod.BetterThanWorldGen;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;

public class ColorizedTallPlantBlock extends TallPlantBlock {
    protected boolean hasOverlays = false;

    public ColorizedTallPlantBlock(int id, String name) {
        super(id, name);
    }

    public ColorizedTallPlantBlock(int id, String name, String[] types) {
        super(id, name, types);
    }

    public ColorizedTallPlantBlock(int id, String name, String[] types, boolean useTypeAsFolder, String prefix) {
        super(id, name, types, useTypeAsFolder, prefix);
    }

    public ColorizedTallPlantBlock setHasOverlays() {
        this.hasOverlays = true;
        return this;
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    protected Icon[] topOverlays;
    protected Icon[] bottomOverlays;

    @Environment(EnvType.CLIENT)
    protected static boolean secondPass;

    @Override
    @Environment(EnvType.CLIENT)
    public void renderBlockSecondPass(RenderBlocks render, int x, int y, int z, boolean firstPassResult) {
        if (!this.hasOverlays) {
            super.renderBlockSecondPass(render, x, y, z, firstPassResult);
            return;
        }

        secondPass = true;
        this.renderBlock(render, x, y, z);
        secondPass = false;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (!secondPass) {
            return super.getBlockTexture(blockAccess, x, y, z, side);
        }

        int metadata = blockAccess.getBlockMetadata(x, y, z);

        if (isTopBlock(metadata)) {
            return topOverlays[MathHelper.clamp_int(metadata & 7, 0, topOverlays.length - 1)];
        }
        else {
            return bottomOverlays[MathHelper.clamp_int(metadata & 7, 0, bottomOverlays.length - 1)];
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        if (!this.hasOverlays) {
            return;
        }

        if (this.types.length == 0) {
            this.topOverlays = new Icon[1];
            this.bottomOverlays = new Icon[1];
            this.topOverlays[0] = iconRegister.registerIcon(this.getTextureName() + "_overlay_top");
            this.bottomOverlays[0] = iconRegister.registerIcon(this.getTextureName() + "_overlay_bottom");
            return;
        }

        this.topOverlays = new Icon[types.length];
        this.bottomOverlays = new Icon[types.length];
        String[] iconNames;

        String prefix;

        if (this.prefix == null) {
            prefix = "";
        }
        else {
            prefix = this.prefix + "/";
        }

        if (useTypeAsFolder) {
            iconNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "/" + this.name + "_overlay")
                    .toArray(String[]::new);
        }
        else if (this.getTextureName().isEmpty()) {
            iconNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "_overlay")
                    .toArray(String[]::new);
        }
        else {
            iconNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "_" + this.name + "_overlay")
                    .toArray(String[]::new);
        }

        for (int i = 0; i < types.length; i++) {
            this.topOverlays[i] = iconRegister.registerIcon(iconNames[i] + "_top");
            this.bottomOverlays[i] = iconRegister.registerIcon(iconNames[i] + "_bottom");
        }
    }

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
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        if (secondPass) {
            return 0xFFFFFF;
        }
        else {
            if (ColorizeBlock.colorizeBlock(this, blockAccess, x, y, z)) {
                return ColorizeBlock.blockColor;
            }
            else {
                return blockAccess.getBiomeGenForCoords(x, z).getBiomeGrassColor();
            }
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
}
