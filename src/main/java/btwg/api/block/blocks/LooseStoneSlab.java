package btwg.api.block.blocks;

import btw.block.blocks.MortarReceiverSlabBlock;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class LooseStoneSlab extends MortarReceiverSlabBlock {
    private final StoneType type;

    public LooseStoneSlab(int id, StoneType type) {
        super(id, Material.rock);

        this.type = type;

        this.setHardness(1.0f);
        this.setResistance(5.0f);

        this.setPicksEffectiveOn();
        this.setChiselsEffectiveOn();

        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_" + type.name() + "_slab");
        this.setTextureName(BetterThanWorldGen.MODID + ":stone/" + type.name());

        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public int getCombinedBlockID(int metadata) {
        int variant = this.setIsUpsideDown(metadata, false);

        if (variant == StoneType.COBBLESTONE_TYPE) {
            return this.type.looseCobblestoneID();
        }
        else {
            return this.type.looseStoneBrickID();
        }
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        return this.setIsUpsideDown(metadata, false);
    }

    @Override
    public boolean onMortarApplied(World world, int i, int j, int k) {
        int variant = this.setIsUpsideDown(world.getBlockMetadata(i, j, k), true);

        boolean isUpsideDown = this.getIsUpsideDown(world, i, j, k);

        if (variant == StoneType.COBBLESTONE_TYPE) {
            world.setBlockAndMetadataWithNotify(i, j, k, this.type.cobblestoneSlabID(), this.setIsUpsideDown(this.type.cobblestoneSlabMetadata(), isUpsideDown));
        }
        else {
            world.setBlockAndMetadataWithNotify(i, j, k, this.type.stoneBrickSlabID(), this.setIsUpsideDown(this.type.stoneBrickSlabMetadata(), isUpsideDown));
        }

        return true;
    }

    @Override
    public boolean isBreakableBarricade(World world, int i, int j, int k) {
        return world.getDifficulty().canZombiesBreakBlocks();
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    protected Icon[] icons;

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        int variant = this.setIsUpsideDown(metadata, false) >> 1;
        return this.icons[variant];
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.icons = new Icon[2];

        this.icons[StoneType.COBBLESTONE_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/loose_cobble");
        this.icons[StoneType.STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET] = iconRegister.registerIcon(this.getTextureName() + "/loose_bricks");
    }

    @Override
    public void getSubBlocks(int iBlockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(iBlockID, 1, 0));
        list.add(new ItemStack(iBlockID, 1, 2));
    }
}
