package btwg.mod.block.blocks;

import btw.client.render.util.RenderUtils;
import btwg.api.block.blocks.ShrubBlock;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class EyeBlossomBlock extends ShrubBlock {
    public static final int CLOSED = 0;
    public static final int OPEN = 1;

    public EyeBlossomBlock(int id) {
        super(id, "eyeblossom", new String[] {
                "closed_eyeblossom",
                "open_eyeblossom"
        }, false, "plant");

        this.setNoRenderOffset();

        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        boolean changed  = false;

        if (world.isDaytime()) {
            if (this.isOpen(world, x, y, z)) {
                this.setOpen(world, x, y, z, false);
                changed = true;
            }
        }
        else {
            if (!this.isOpen(world, x, y, z)) {
                this.setOpen(world, x, y, z, true);
                changed = true;
            }
        }

        if (changed) {
            world.markBlockForUpdate(x, y, z);
        }
    }

    @Override
    public int getLightValue(IBlockAccess blockAccess, int x, int y, int z) {
        if (this.isOpen(blockAccess, x, y, z)) {
            return 4;
        }
        return 0;
    }

    //------ Class Specific Methods ------//

    public boolean isOpen(int metadata) {
        return metadata == OPEN;
    }

    public boolean isOpen(IBlockAccess blockAccess, int x, int y, int z) {
        return this.isOpen(blockAccess.getBlockMetadata(x, y, z));
    }

    public void setOpen(World world, int x, int y, int z, boolean open) {
        if (open) {
            world.setBlockMetadataWithNotify(x, y, z, OPEN);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, CLOSED);
        }
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    protected Icon openOverlayIcon;
    @Environment(EnvType.CLIENT)
    protected static boolean secondPass;

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        super.registerIcons(register);
        this.openOverlayIcon = register.registerIcon(BetterThanWorldGen.MODID + ":" + prefix + "/open_eyeblossom_overlay");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        if (secondPass && this.isOpen(metadata)) {
            return this.openOverlayIcon;
        }
        return super.getIcon(side, metadata);
    }

    @Environment(EnvType.CLIENT)
    public void renderBlockSecondPass(RenderBlocks renderBlocks, int x, int y, int z, boolean firstPassResult) {
        secondPass = true;

        if (firstPassResult) {
            int metadata = renderBlocks.blockAccess.getBlockMetadata(x, y, z);

            if (this.isOpen(metadata)) {
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
        }

        secondPass = false;
    }
}
