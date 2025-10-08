package btwg.api.block.blocks;

import btw.block.blocks.OreBlockStaged;
import btw.item.items.ChiselItem;
import btw.item.items.PickaxeItem;
import btw.item.items.ToolItem;
import btw.world.util.difficulty.Difficulty;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BTWGOreBlock extends OreBlockStaged {
    protected final String name;

    protected final StoneType[] types;

    protected final int toolLevel;

    protected final int idDropped;
    protected final int metadataDropped;

    protected Function<Random, Integer> quantityDroppedFn = rand -> 1;

    protected Optional<BiFunction<Difficulty, Integer, Integer>> idDroppedOnConversionFn = Optional.empty();
    protected Optional<Function<Integer, Integer>> metadataDroppedOnConversionFn = Optional.empty();
    protected Optional<Function<Random, Integer>> quantityDroppedOnConversionFn = Optional.empty();

    public BTWGOreBlock(int id, int toolLevel, int idDropped, String name, StoneType[] types) {
        this(id, idDropped, 0, toolLevel, name, types);
    }

    public BTWGOreBlock(int id, int toolLevel, int idDropped, int metadataDropped, String name, StoneType[] types) {
        super(id);

        this.idDropped = idDropped;
        this.metadataDropped = metadataDropped;

        this.types = types;
        this.toolLevel = toolLevel;

        this.name = name;

        this.setTextureName(BetterThanWorldGen.MODID + ":stone/");
        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + name + "_ore");
    }

    @Override
    public int idDropped(int metadata, Random random, int fortuneModifier) {
        return this.idDropped;
    }

    @Override
    public int damageDropped(int metadata) {
        return this.metadataDropped;
    }

    @Override
    public int quantityDropped(Random rand) {
        return this.quantityDroppedFn.apply(rand);
    }

    @Override
    public int idDroppedOnConversion(Difficulty difficulty, int metadata) {
        return this.idDroppedOnConversionFn.orElse((d, m) -> this.idDropped).apply(difficulty, metadata);
    }

    @Override
    public int damageDroppedOnConversion(int metadata) {
        return this.metadataDroppedOnConversionFn.orElse((m) -> this.metadataDropped).apply(metadata);
    }

    @Override
    public int quantityDroppedOnConversion(Random rand) {
        return this.quantityDroppedOnConversionFn.orElse((r) -> 1).apply(rand);
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int iLevel;
        int oldMetadata = world.getBlockMetadata(x, y, z);

        world.setBlockAndMetadataWithNotify(x, y, z, this.types[MathHelper.clamp_int(oldMetadata, 0, this.types.length - 1)].roughStoneID(), 4);

        if (!world.isRemote && (iLevel = this.getConversionLevelForTool(stack, world, x, y, z)) > 0) {
            world.playAuxSFX(2269, x, y, z, 0);

            if (iLevel >= 3) {
                this.ejectItemsOnGoodPickConversion(stack, world, x, y, z, oldMetadata, side);
            }
            else if (iLevel == 2) {
                this.ejectItemsOnStonePickConversion(stack, world, x, y, z, oldMetadata, side);
            }
            else {
                this.ejectItemsOnChiselConversion(stack, world, x, y, z, oldMetadata, side);
            }
        }
        return true;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortuneModifier) {
        super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortuneModifier);

        StoneType type = this.types[MathHelper.clamp_int(metadata, 0, this.types.length - 1)];

        if (!world.isRemote) {
            this.dropItemsIndividually(world, x, y, z, type.rockItemID(), 6, type.rockItemMetadata(), 1.0f);
        }
    }

    @Override
    public int getStrata(int metadata) {
        return this.types[MathHelper.clamp_int(metadata, 0, this.types.length - 1)].strata();
    }

    @Override
    public int getRequiredToolLevelForOre(IBlockAccess blockAccess, int i, int j, int k) {
        return this.toolLevel;
    }

    //------ Class Specific Functionality  ------//

    public BTWGOreBlock setQuantityDropped(int quantityDropped) {
        this.quantityDroppedFn = rand -> quantityDropped;
        return this;
    }

    public BTWGOreBlock setQuantityDropped(Function<Random, Integer> quantityDroppedFn) {
        this.quantityDroppedFn = quantityDroppedFn;
        return this;
    }

    public BTWGOreBlock setIDDroppedOnConversion(int idDroppedOnConversion) {
        this.idDroppedOnConversionFn = Optional.of((difficulty, metadata) -> idDroppedOnConversion);
        return this;
    }

    public BTWGOreBlock setIDDroppedOnConversion(BiFunction<Difficulty, Integer, Integer> idDroppedOnConversionFn) {
        this.idDroppedOnConversionFn = Optional.of(idDroppedOnConversionFn);
        return this;
    }

    public BTWGOreBlock setMetadataDroppedOnConversion(int metadataDroppedOnConversion) {
        this.metadataDroppedOnConversionFn = Optional.of((metadata) -> metadataDroppedOnConversion);
        return this;
    }

    public BTWGOreBlock setMetadataDroppedOnConversion(Function<Integer, Integer> metadataDroppedOnConversionFn) {
        this.metadataDroppedOnConversionFn = Optional.of(metadataDroppedOnConversionFn);
        return this;
    }

    protected int getConversionLevelForTool(ItemStack stack, World world, int i, int j, int k) {
        if (stack != null) {
            if (stack.getItem() instanceof PickaxeItem) {
                int toolLevel = ((ToolItem) stack.getItem()).toolMaterial.getHarvestLevel();

                if (toolLevel >= this.getRequiredToolLevelForOre(world, i, j, k)) {
                    if (toolLevel > 1 || world.getDifficulty().doesStonePickBreakStone()) {
                        return 3;
                    }

                    return 2;
                }
            }
            else if (stack.getItem() instanceof ChiselItem && ((ToolItem)stack.getItem()).toolMaterial.getHarvestLevel() >= this.getRequiredToolLevelForOre(world, i, j, k)) {
                return 1;
            }
        }

        return 0;
    }

    //------ Client Side Functionality  ------//

    @Environment(value=EnvType.CLIENT)
    private Icon[] icons;

    @Override
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.icons = new Icon[this.types.length];

        for (int i = 0; i < this.types.length; i++) {
            this.icons[i] = register.registerIcon(this.getTextureName() + this.types[i].name() + "/" + this.name + "_ore");
        }
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        return this.icons[MathHelper.clamp_int(metadata, 0, this.types.length - 1)];
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.types.length; i++) {
            list.add(new ItemStack(blockID, 1, i));
        }
    }
}
