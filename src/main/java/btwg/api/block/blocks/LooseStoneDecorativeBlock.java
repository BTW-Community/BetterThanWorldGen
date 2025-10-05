package btwg.api.block.blocks;

import btw.block.blocks.LavaReceiverBlock;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class LooseStoneDecorativeBlock extends LavaReceiverBlock {
    public static final int TYPE_OFFSET = 1;
    private final StoneType type;

    public LooseStoneDecorativeBlock(int id, StoneType type) {
        super(id, Material.rock);
        this.type = type;

        this.setHardness(1.0f);
        this.setResistance(5.0f);

        this.setPicksEffectiveOn();
        this.setChiselsEffectiveOn();

        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_" + type.name());
        this.setTextureName(BetterThanWorldGen.MODID + ":" + type.name());

        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random rand) {
        if (!this.checkForFall(world, i, j, k)) {
            if (this.getHasLavaInCracks(world, i, j, k)) {
                if (this.hasWaterAbove(world, i, j, k)) {
                    world.playAuxSFX(2227, i, j, k, 0);
                    world.setBlockAndMetadataWithNotify(i, j, k, this.type.stoneID(), this.type.stoneMetadata());
                }
            }
            else if (this.hasLavaAbove(world, i, j, k)) {
                this.setHasLavaInCracks(world, i, j, k, true);
            }
        }
    }

    @Override
    public void randomUpdateTick(World world, int i, int j, int k, Random rand) {
        if (this.getHasLavaInCracks(world, i, j, k) && world.isRainingAtPos(i, j + 1, k)) {
            world.playAuxSFX(2227, i, j, k, 0);
            world.setBlockAndMetadataWithNotify(i, j, k, this.type.stoneID(), this.type.stoneMetadata());
        }
    }

    @Override
    public boolean onMortarApplied(World world, int i, int j, int k) {
        int variant = this.getVariant(world.getBlockMetadata(i, j, k));

        if (variant == StoneType.COBBLESTONE_TYPE) {
            world.setBlockAndMetadataWithNotify(i, j, k, this.type.cobblestoneID(), this.type.cobblestoneMetadata());
        }
        else {
            world.setBlockAndMetadataWithNotify(i, j, k, this.type.stoneBrickID(), this.type.stoneBrickMetadata());
        }

        return true;
    }

    @Override
    public boolean isBreakableBarricade(World world, int i, int j, int k) {
        return world.getDifficulty().canZombiesBreakBlocks();
    }

    //------ Class Specific Functionality ------//

    protected int getVariant(int metadata) {
        return metadata >> 1;
    }

    //------ Client Side Functionality  ------//

    @Environment(EnvType.CLIENT)
    protected Icon[] icons;
    @Environment(EnvType.CLIENT)
    protected Icon[] lavaOverlayIcons;

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.icons = new Icon[2];

        this.icons[StoneType.COBBLESTONE_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/loose_cobble");
        this.icons[StoneType.STONE_BRICK_TYPE - TYPE_OFFSET] = iconRegister.registerIcon(this.getTextureName() + "/loose_bricks");

        this.lavaOverlayIcons = new Icon[2];

        this.lavaOverlayIcons[StoneType.COBBLESTONE_TYPE] = iconRegister.registerIcon(BetterThanWorldGen.MODID + ":" + this.type.name() + "/cobble_lava_overlay");
        this.lavaOverlayIcons[StoneType.STONE_BRICK_TYPE - TYPE_OFFSET] = iconRegister.registerIcon(BetterThanWorldGen.MODID + ":" + this.type.name() + "/brick_lava_overlay");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        int variant = this.getVariant(metadata);
        return this.icons[MathHelper.clamp_int(variant, 0, this.icons.length - 1)];
    }

    @Override
    @Environment(EnvType.CLIENT)
    protected Icon getLavaCracksOverlay(int metadata) {
        int index = metadata >> 1;
        return this.lavaOverlayIcons[MathHelper.clamp_int(index, 0, this.lavaOverlayIcons.length - 1)];
    }

    @Override
    public void getSubBlocks(int iBlockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(iBlockID, 1, 0));
        list.add(new ItemStack(iBlockID, 1, StoneType.STONE_BRICK_TYPE << 1));
    }
}
