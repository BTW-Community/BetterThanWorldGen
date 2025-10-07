package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class StoneDecorativeBlock extends MultiTextureBlock {
    public static final int NUM_TYPES = 7;

    private final StoneType type;

    public StoneDecorativeBlock(int id, StoneType type) {
        super(id, Material.rock, type.name(), new String[] {
                "cobble",
                "polished",
                "bricks",
                "mossy_bricks",
                "cracked_bricks",
                "chiseled_bricks",
                "mossy_cobble",
        });

        this.type = type;

        if (type.strata() == 0) {
            this.setHardness(2.25f);
            this.setResistance(10.0f);
        }
        else if (type.strata() == 1) {
            this.setHardness(3.0f);
            this.setResistance(13.0f);
        }
        else {
            this.setHardness(4.5f);
            this.setResistance(20.0f);
        }

        this.setPicksEffectiveOn();
        this.setChiselsEffectiveOn();

        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + type.name());
        this.setTextureName(BetterThanWorldGen.MODID + ":stone/" + type.name());

        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public int idDropped(int metadata, Random rand, int fortuneModifier) {
        return switch (metadata) {
            case StoneType.COBBLESTONE_TYPE -> this.type.looseCobblestoneID();
            case StoneType.STONE_BRICK_TYPE,
                 StoneType.MOSSY_STONE_BRICK_TYPE,
                 StoneType.CRACKED_STONE_BRICK_TYPE,
                 StoneType.CHISELED_STONE_BRICK_TYPE -> this.type.looseStoneBrickID();
            default -> this.blockID;
        };
    }

    @Override
    public int damageDropped(int metadata) {
        return switch (metadata) {
            case StoneType.COBBLESTONE_TYPE -> this.type.looseCobblestoneMetadata();
            case StoneType.STONE_BRICK_TYPE,
                 StoneType.MOSSY_STONE_BRICK_TYPE,
                 StoneType.CRACKED_STONE_BRICK_TYPE,
                 StoneType.CHISELED_STONE_BRICK_TYPE -> this.type.looseStoneBrickMetadata();
            default -> metadata;
        };
    }

    @Override
    public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        this.dropBlockAsItem(world, x, y, z, metadata, 0);
    }

    @Override
    public boolean hasMortar(IBlockAccess blockAccess, int x, int y, int z) {
        int metadata = blockAccess.getBlockMetadata(x, y, z);
        return metadata != StoneType.MOSSY_COBBLESTONE_TYPE;
    }

    @Override
    public boolean canBeConvertedByMobSpawner(World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        return metadata == StoneType.COBBLESTONE_TYPE;
    }

    @Override
    public void convertBlockFromMobSpawner(World world, int x, int y, int z) {
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.mossyCobblestoneID(), this.type.mossyCobblestoneMetadata());
    }

    @Override
    public StepSound getStepSound(World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);

        StepSound cobblestoneStepSound = switch (this.type.strata()) {
            case 1 -> BTWBlocks.cobblestoneStrata2StepSound;
            case 2 -> BTWBlocks.cobblestoneStrata3StepSound;
            default -> BTWBlocks.cobblestoneStepSound;
        };

        return switch (metadata) {
            case StoneType.STONE_BRICK_TYPE,
                 StoneType.MOSSY_STONE_BRICK_TYPE,
                 StoneType.CRACKED_STONE_BRICK_TYPE,
                 StoneType.CHISELED_STONE_BRICK_TYPE -> BTWBlocks.stoneBrickStepSound;
            default -> cobblestoneStepSound;
        };
    }

    //------ Client Side Functionality ------//

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.icons = new Icon[NUM_TYPES];

        this.icons[StoneType.COBBLESTONE_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/cobble");
        this.icons[StoneType.POLISHED_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/polished");
        this.icons[StoneType.STONE_BRICK_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/bricks");
        this.icons[StoneType.MOSSY_STONE_BRICK_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/mossy_bricks");
        this.icons[StoneType.CRACKED_STONE_BRICK_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/cracked_bricks");
        this.icons[StoneType.CHISELED_STONE_BRICK_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/chiseled_bricks");
        this.icons[StoneType.MOSSY_COBBLESTONE_TYPE] = iconRegister.registerIcon(this.getTextureName() + "/mossy_cobble");
    }
}
