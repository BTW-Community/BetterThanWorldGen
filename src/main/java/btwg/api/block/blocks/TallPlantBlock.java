package btwg.api.block.blocks;

import btw.block.blocks.FlowerBlock;
import btw.item.util.ItemUtils;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TallPlantBlock extends FlowerBlock {
    protected boolean isReplaceable;
    protected boolean canStayOnSand;
    protected boolean needsShears;

    protected final String name;
    protected final String[] types;

    protected final boolean useTypeAsFolder;
    protected final String prefix;

    public TallPlantBlock(int id, String name) {
        this(id, name, new String[] {}, false, null);
    }

    public TallPlantBlock(int blockID, String name, String[] types) {
        this(blockID, name, types, false, null);
    }

    public TallPlantBlock(int id, String name, String[] types, boolean useTypeAsFolder, String prefix) {
        super(id);

        this.setUnlocalizedName(name);
        this.name = name;
        this.types = types;
        this.useTypeAsFolder = useTypeAsFolder;
        this.prefix = prefix;
    }

    @Override
    public int idDropped(int metadata, Random rand, int fortuneModifier) {
        if (isTopBlock(metadata) && !this.needsShears) {
            return this.blockID;
        }
        else {
            return 0;
        }
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata & 7;
    }

    @Override
    public AxisAlignedBB getBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
        int metadata = blockAccess.getBlockMetadata(x, y, z);
        float minHeight;
        float maxHeight;

        if (this.isTopBlock(metadata)) {
            minHeight = -1F;
            maxHeight = this.getHeight();
        }
        else {
            minHeight = 0F;
            maxHeight = 1 + this.getHeight();
        }

        return AxisAlignedBB.getBoundingBox(0.5F - this.getHalfWidth(), minHeight, 0.5F - this.getHalfWidth(),
                0.5F + this.getHalfWidth(), maxHeight, 0.5F + this.getHalfWidth());
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        if (world.getBlockId(x, y + 1, z) == 0) {
            world.setBlockAndMetadataWithNotify(x, y + 1, z, this.blockID, this.setTopBlock(world.getBlockMetadata(x, y, z), true));
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
        int metadata = world.getBlockMetadata(x, y, z);

        if (isTopBlock(metadata)) {
            if (world.getBlockId(x, y - 1, z) != this.blockID) {
                world.setBlockToAir(x, y, z);
                int idDropped = this.idDropped(metadata, world.rand, 1);

                if (idDropped != 0) {
                    ItemUtils.dropSingleItemAsIfBlockHarvested(world, x, y, z, idDropped, this.damageDropped(metadata));
                }
            }
        }
        else {
            if (world.getBlockId(x, y + 1, z) != this.blockID) {
                world.setBlockToAir(x, y, z);
            }
        }

        super.onNeighborBlockChange(world, x, y, z, neighbourID);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return super.canPlaceBlockAt(world, x, y, z) && world.getBlockId(x, y + 1, z) == 0;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);

        if (isTopBlock(metadata)) {
            return world.getBlockId(x, y - 1, z) == this.blockID;
        }
        else {
            return this.canGrowOnBlock(world, x, y - 1, z) && world.getBlockId(x, y + 1, z) == this.blockID;
        }
    }

    @Override
    public boolean canGrowOnBlock(World world, int x, int y, int z) {
        if (world.getBlockId(x, y, z) == this.blockID) {
            int metadata = world.getBlockMetadata(x, y, z);
            return !isTopBlock(metadata);
        }
        else {
            Block blockOn = Block.blocksList[world.getBlockId(x, y, z)];
            return blockOn != null && (blockOn.canWildVegetationGrowOnBlock(world, x, y, z) || (this.canStayOnSand && blockOn.blockMaterial == Material.sand && blockOn.hasLargeCenterHardPointToFacing(world, x, y, z, EnumFacing.UP.ordinal())));
        }
    }

    @Override
    public boolean canSpitWebReplaceBlock(World world, int x, int y, int z) {
        return this.isReplaceable;
    }

    @Override
    public boolean isReplaceableVegetation(World world, int x, int y, int z) {
        return this.isReplaceable;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemShears) {
            player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            player.getHeldItem().damageItem(1, player);

            if (!isTopBlock(metadata)) {
                this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this.blockID, 1, metadata & 7));
            }
        }
        else {
            super.harvestBlock(world, player, x, y, z, metadata);
        }
    }

    //------------- Class Specific Methods ------------//

    public String[] getTypes() {
        return types;
    }

    public ArrayList<Integer> getSpawnableList() {
        ArrayList<Integer> spawnableList = new ArrayList<>();

        for (int i = 0; i < this.types.length; i++) {
            spawnableList.add(i);
        }

        return spawnableList;
    }

    public float getHalfWidth() {
        return 0.4F;
    }

    public float getHeight() {
        return 0.8F;
    }

    public boolean isTopBlock(IBlockAccess blockAccess, int x, int y, int z) {
        return isTopBlock(blockAccess.getBlockMetadata(x, y, z));
    }

    public boolean isTopBlock(int metadata) {
        return (metadata & 8) != 0;
    }

    public void setTopBlock(World world, int x, int y, int z, boolean isTop) {
        int metadata = world.getBlockMetadata(x, y, z);
        world.setBlockMetadata(x, y, z, this.setTopBlock(metadata, isTop));
    }

    public int setTopBlock(int metadata, boolean isTop) {
        if (isTop) {
            return metadata | 8;
        }
        else {
            return metadata & 7;
        }
    }

    public TallPlantBlock setCanStayOnSand() {
        this.canStayOnSand = true;
        return this;
    }

    public TallPlantBlock setNeedsShears() {
        this.needsShears = true;
        return this;
    }

    public TallPlantBlock setReplaceable() {
        this.isReplaceable = true;
        return this;
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    private Icon[] topIcons;
    @Environment(EnvType.CLIENT)
    private Icon[] bottomIcons;

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata) {
        return topIcons[metadata & 7];
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
        int metadata = blockAccess.getBlockMetadata(x, y, z);

        if (isTopBlock(metadata)) {
            return topIcons[metadata & 7];
        }
        else {
            return bottomIcons[metadata & 7];
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        if (this.types.length == 0) {
            this.topIcons = new Icon[1];
            this.bottomIcons = new Icon[1];
            this.topIcons[0] = iconRegister.registerIcon(this.getTextureName() + "_top");
            this.bottomIcons[0] = iconRegister.registerIcon(this.getTextureName() + "_bottom");
            return;
        }

        this.topIcons = new Icon[types.length];
        this.bottomIcons = new Icon[types.length];
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
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "/" + this.name)
                    .toArray(String[]::new);
        }
        else if (this.getTextureName().isEmpty()) {
            iconNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type)
                    .toArray(String[]::new);
        }
        else {
            iconNames = Arrays.stream(this.types)
                    .map(type -> BetterThanWorldGen.MODID + ":" + prefix + type + "_" + this.name)
                    .toArray(String[]::new);
        }

        for (int i = 0; i < types.length; i++) {
            this.topIcons[i] = iconRegister.registerIcon(iconNames[i] + "_top");
            this.bottomIcons[i] = iconRegister.registerIcon(iconNames[i] + "_bottom");
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < types.length; i++) {
            list.add(new ItemStack(blockID, 1, i));
        }
    }
}