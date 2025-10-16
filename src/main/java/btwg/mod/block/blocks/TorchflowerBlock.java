package btwg.mod.block.blocks;

import btwg.api.block.blocks.ShrubBlock;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class TorchflowerBlock extends ShrubBlock {
    public TorchflowerBlock(int id) {
        super(id, "torchflower", new String[] {
                "torchflower",
        }, false, "plant");

        this.setNoRenderOffset();

        this.setTickRandomly(true);
    }

    @Override
    public int getLightValue(IBlockAccess blockAccess, int x, int y, int z) {
        return 4;
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    protected Icon overlayIcon;
    @Environment(EnvType.CLIENT)
    protected static boolean secondPass;

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        super.registerIcons(register);
        this.overlayIcon = register.registerIcon(BetterThanWorldGen.MODID + ":" + prefix + "/torchflower_overlay");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        if (secondPass) {
            return this.overlayIcon;
        }
        return super.getIcon(side, metadata);
    }

    @Environment(EnvType.CLIENT)
    public void renderBlockSecondPass(RenderBlocks renderBlocks, int x, int y, int z, boolean firstPassResult) {
        secondPass = true;

        if (firstPassResult) {
            int metadata = renderBlocks.blockAccess.getBlockMetadata(x, y, z);

            Tessellator tessellator = Tessellator.instance;
            tessellator.setBrightness(renderBlocks.blockAccess.getLightBrightnessForSkyBlocks(x, y, z, 15));
            float var6 = 1.0F;
            int var7 = this.colorMultiplier(renderBlocks.blockAccess, x, y, z);
            float var8 = (float)(var7 >> 16 & 255) / 255.0F;
            float var9 = (float)(var7 >> 8 & 255) / 255.0F;
            float var10 = (float)(var7 & 255) / 255.0F;
            tessellator.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
            double renderX = x;
            double renderY = y;
            double renderZ = z;

            if (this.shouldRenderWithOffset) {
                long offset = (x * 3129871L) ^ z * 116129781L ^ y;
                offset = offset * offset * 42317861L + offset * 11L;
                renderX += ((double) ((float) (offset >> 16 & 15L) / 15.0F) - (double) 0.5F) * (double) 0.5F;
                renderY += ((double) ((float) (offset >> 20 & 15L) / 15.0F) - (double) 1.0F) * 0.2;
                renderZ += ((double) ((float) (offset >> 24 & 15L) / 15.0F) - (double) 0.5F) * (double) 0.5F;
            }

            renderBlocks.drawCrossedSquares(this, metadata, renderX, renderY, renderZ, 1.0F);
        }

        secondPass = false;
    }
}
