package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ShrubBlock extends BlockFlower {
    private boolean isReplaceable;
    private boolean canStayOnSand;
    private boolean needsShears;

    protected final String name;
    protected final String[] types;

    protected final boolean useTypeAsFolder;
    protected final String prefix;

    public ShrubBlock(int id, String name) {
        this(id, name, new String[] {}, false, null);
    }

    public ShrubBlock(int id, String name, String[] types) {
        this(id, name, types, false, null);
    }

    public ShrubBlock(int id, String name, String[] types, boolean useTypeAsFolder, String prefix) {
        super(id, Material.plants);

        this.name = name;
        this.types = types;
        this.useTypeAsFolder = useTypeAsFolder;
        this.prefix = prefix;

        this.initBlockBounds(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);
        this.setStepSound(BTWBlocks.plantsStepSound);
        this.setHardness(0.0f);
        this.setBuoyant();
        this.setFireProperties(Flammability.GRASS);
        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + name);
    }

    protected boolean canGrowOnBlock(World world, int x, int y, int z) {
        Block blockOn = Block.blocksList[world.getBlockId(x, y, z)];
        return blockOn != null && (blockOn.canWildVegetationGrowOnBlock(world, x, y, z) || (this.canStayOnSand && blockOn.blockMaterial == Material.sand && blockOn.hasLargeCenterHardPointToFacing(world, x, y, z, EnumFacing.UP.ordinal())));
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return this.canGrowOnBlock(world, x, y - 1, z);
    }

    @Override
    public int idDropped(int iMetadata, Random rand, int iFortuneModifier) {
        return -1;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int par6) {
        if (!world.isRemote && (!needsShears || (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemShears))) {
            player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this, 1, par6));
        } else {
            super.harvestBlock(world, player, x, y, z, par6);
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
    public ItemStack getStackRetrievedByBlockDispenser(World world, int i, int j, int k) {
        return this.createStackedBlock(world.getBlockMetadata(i, j, k));
    }

    public ShrubBlock setReplaceable() {
        this.isReplaceable = true;
        this.blockMaterial = Material.vine;
        return this;
    }

    public ShrubBlock setCanStayOnSand() {
        this.canStayOnSand = true;
        return this;
    }

    public ShrubBlock setNeedsShears() {
        this.needsShears = true;
        return this;
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    protected Icon[] icons;

    @Override
    @Environment(EnvType.CLIENT)
    public boolean renderBlock(RenderBlocks renderBlocks, int x, int y, int z) {
        return renderBlocks.renderCrossedSquares(this, x, y, z, true);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        if (this.types.length == 0) {
            this.icons = new Icon[1];
            this.icons[0] = iconRegister.registerIcon(this.getTextureName());
            return;
        }

        this.icons = new Icon[types.length];
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
            this.icons[i] = iconRegister.registerIcon(iconNames[i]);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        return this.icons[metadata];
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void getSubBlocks(int id, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < types.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }
}
