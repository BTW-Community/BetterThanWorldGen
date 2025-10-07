package btwg.api.block.blocks;

import btw.block.blocks.RoughStoneBlock;
import btw.item.util.ItemUtils;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class BTWGRoughStoneBlock extends RoughStoneBlock {
    private final StoneType type;

    public BTWGRoughStoneBlock(int id, StoneType type) {
        super(id, type.strata());
        this.type = type;
        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".rough_" + type.name());

        this.hideFromEMI();
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int metadata = world.getBlockMetadata(x, y, z);
        boolean soundPlayed = false;

        if (metadata < 15) {
            ++metadata;

            if (!world.isRemote && this.isEffectiveItemConversionTool(stack, world, x, y, z)) {
                if (metadata <= 8) {
                    if ((metadata & 1) == 0) {
                        world.playAuxSFX(2269, x, y, z, 0);
                        soundPlayed = true;
                        ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.rockItemID(), 1, this.type.rockItemMetadata()), side);
                    }
                    else if (metadata <= 5 && this.isUberItemConversionTool(stack, world, x, y, z)) {
                        metadata += 3;
                        world.playAuxSFX(2269, x, y, z, 0);
                        soundPlayed = true;
                        ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.rockItemID(), 1, this.type.rockItemMetadata()), side);
                    }
                }
                else if (metadata == 12) {
                    world.playAuxSFX(2270, x, y, z, 0);
                    soundPlayed = true;
                    ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.gravelPileID(), 1, this.type.gravelPileMetadata()), side);
                }
            }

            if (!soundPlayed && !world.isRemote) {
                world.playAuxSFX(2001, x, y, z, this.blockID + (metadata << 12));
            }

            world.setBlockMetadataWithNotify(x, y, z, metadata);
            return true;
        }

        if (!world.isRemote && this.isEffectiveItemConversionTool(stack, world, x, y, z)) {
            world.playAuxSFX(2270, x, y, z, 0);
            ItemUtils.dropStackAsIfBlockHarvested(world, x, y, z, new ItemStack(this.type.gravelPileID(), 1, this.type.gravelPileMetadata()));
        }

        return false;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int i, int j, int k, int iMetadata, float fChance, int iFortuneModifier) {
        if (!world.isRemote) {
            int iItemIDDropped = this.type.rockItemID();
            int iNumDropped = 1;
            int metadataDropped = this.type.rockItemMetadata();
            if (iMetadata < 8) {
                iNumDropped = 8 - iMetadata / 2;
            } else {
                iItemIDDropped = this.type.gravelPileID();
                metadataDropped = this.type.gravelPileMetadata();
                if (iMetadata < 12) {
                    iNumDropped = 2;
                }
            }
            for (int iTempCount = 0; iTempCount < iNumDropped; ++iTempCount) {
                this.dropBlockAsItem_do(world, i, j, k, new ItemStack(iItemIDDropped, 1, metadataDropped));
            }
        }
    }

    //------ Client Side Functionality ------//

    @Environment(value= EnvType.CLIENT)
    private Icon iconBroken;
    @Environment(value=EnvType.CLIENT)
    private Icon[] crackIcons;

    @Override
    @Environment(value=EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(BetterThanWorldGen.MODID + ":stone/" + type.name() + "/rough");
        this.iconBroken = register.registerIcon(BetterThanWorldGen.MODID + ":stone/" + type.name() + "/broken");
        this.crackIcons = new Icon[7];

        for (int i = 0; i < 7; ++i) {
            this.crackIcons[i] = register.registerIcon("btw:rough_stone_overlay_" + (i + 1));
        }
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        if (metadata >= 8) {
            return this.iconBroken;
        }

        return this.blockIcon;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void renderBlockSecondPass(RenderBlocks renderBlocks, int i, int j, int k, boolean bFirstPassResult) {
        IBlockAccess blockAccess;
        int iMetadata;
        if (bFirstPassResult && (iMetadata = (blockAccess = renderBlocks.blockAccess).getBlockMetadata(i, j, k)) > 0 && iMetadata != 8) {
            int iTextureIndex = 0;
            iTextureIndex = iMetadata < 8 ? MathHelper.clamp_int(iMetadata - 1, 0, 6) : MathHelper.clamp_int(iMetadata - 9, 0, 6);
            Icon overlayTexture = this.crackIcons[iTextureIndex];
            if (overlayTexture != null) {
                this.renderBlockWithTexture(renderBlocks, i, j, k, overlayTexture);
            }
        }
    }
}
