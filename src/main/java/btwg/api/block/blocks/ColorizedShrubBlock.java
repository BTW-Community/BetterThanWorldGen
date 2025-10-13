package btwg.api.block.blocks;

import btwg.mod.BetterThanWorldGen;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;

public class ColorizedShrubBlock extends ShrubBlock {
    protected boolean hasOverlays = false;

    public ColorizedShrubBlock(int id, String name) {
        super(id, name);
    }

    public ColorizedShrubBlock(int id, String name, String[] types) {
        super(id, name, types);
    }

    public ColorizedShrubBlock(int id, String name, String[] types, boolean useTypeAsFolder, String prefix) {
        super(id, name, types, useTypeAsFolder, prefix);
    }

    public ColorizedShrubBlock setHasOverlays() {
        this.hasOverlays = true;
        return this;
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    protected Icon[] overlays;

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
    public Icon getIcon(int side, int metadata) {
        if (secondPass) {
            return this.overlays[metadata];
        }
        else {
            return super.getIcon(side, metadata);
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
            this.overlays = new Icon[1];
            this.overlays[0] = iconRegister.registerIcon(this.getTextureName() + "_overlay");
        }

        this.overlays = new Icon[types.length];
        String[] overlayNames;

        String prefix;

        if (this.prefix == null) {
            prefix = "";
        }
        else {
            prefix = this.prefix + "/";
        }

        if (useTypeAsFolder) {
            overlayNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "/" + this.name + "_overlay")
                    .toArray(String[]::new);
        }
        else if (this.getTextureName().isEmpty()) {
            overlayNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "_overlay")
                    .toArray(String[]::new);
        }
        else {
            overlayNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "_" + this.name + "_overlay")
                    .toArray(String[]::new);
        }

        for (int i = 0; i < types.length; i++) {
            this.overlays[i] = iconRegister.registerIcon(overlayNames[i]);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getBlockColor() {
        if (secondPass) {
            return 0xFFFFFF;
        }

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
        if (secondPass) {
            return 0xFFFFFF;
        }

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
}
