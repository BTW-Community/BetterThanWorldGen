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

        Tessellator tessellator = Tessellator.instance;
        int metadata = render.blockAccess.getBlockMetadata(x, y, z);
        Icon icon = this.overlays[MathHelper.clamp_int(metadata, 0, this.overlays.length - 1)];

        double minU = icon.getMinU();
        double minV = icon.getMinV();
        double maxU = icon.getMaxU();
        double maxV = icon.getMaxV();
        double width = 0.45;
        double minX = x + 0.5 - width;
        double maxX = x + 0.5 + width;
        double minZ = z + 0.5 - width;
        double maxZ = z + 0.5 + width;

        tessellator.addVertexWithUV(minX, y + 1, minZ, minU, minV);
        tessellator.addVertexWithUV(minX, y + 0.0, minZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, y + 0.0, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, y + 1, maxZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, y + 1, maxZ, minU, minV);
        tessellator.addVertexWithUV(maxX, y + 0.0, maxZ, minU, maxV);
        tessellator.addVertexWithUV(minX, y + 0.0, minZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, y + 1, minZ, maxU, minV);
        tessellator.addVertexWithUV(minX, y + 1, maxZ, minU, minV);
        tessellator.addVertexWithUV(minX, y + 0.0, maxZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, y + 0.0, minZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, y + 1, minZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, y + 1, minZ, minU, minV);
        tessellator.addVertexWithUV(maxX, y + 0.0, minZ, minU, maxV);
        tessellator.addVertexWithUV(minX, y + 0.0, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, y + 1, maxZ, maxU, minV);

        secondPass = false;
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

        if (ColorizeBlock.colorizeBlock(this, blockAccess, x, y, z)) {
            return ColorizeBlock.blockColor;
        }
        else {
            return blockAccess.getBiomeGenForCoords(x, z).getBiomeGrassColor();
        }
    }
}
