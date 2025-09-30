package btwg.api.block.blocks;

import btw.block.blocks.RoughStoneBlock;
import btw.item.items.ChiselItem;
import btw.item.items.PickaxeItem;
import btw.item.items.ToolItem;
import btw.item.util.ItemUtils;
import btwg.mod.BetterThanWorldGen;
import btwg.mod.block.StoneType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class BTWGStoneBlock extends BlockStone {
    private final String name;
    private final StoneType type;
    
    public BTWGStoneBlock(int blockID, String name, StoneType type) {
        super(blockID);
        this.name = name;
        this.type = type;
        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + name);
        this.setTextureName(BetterThanWorldGen.MODID + ":" + name);
    }
    
    @Override
    public int getStrata(int meta) {
        return this.type.strata();
    }
    
    @Override
    public int getBlockIDOnInfest(EntityLiving entity, int metadata) {
        return this.type.infestedStoneID();
    }
    
    @Override
    public int idDropped(int iMetaData, Random random, int iFortuneModifier) {
        return this.type.cobblestoneID();
    }
    
    @Override
    public int damageDropped(int metadata) {
        return this.type.cobblestoneMetadata();
    }
    
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortuneModifier) {
        super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortuneModifier);
        if (!world.isRemote) {
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this.type.rockItemID(), 1, this.type.rockItemMetadata()));
            if (!this.getIsCracked(metadata)) {
                this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this.type.gravelPileID(), 1, this.type.gravelPileMetadata()));
            }
        }
    }
    
    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chance) {
        this.dropItemsIndividually(world, x, y, z, this.type.rockItemID(), 5, this.type.rockItemMetadata(), chance);
        int numGravel = this.getIsCracked(metadata) ? 2 : 3;
        this.dropItemsIndividually(world, x, y, z, this.type.gravelPileID(), numGravel, this.type.gravelPileMetadata(), chance);
        return true;
    }
    
    @Override
    public boolean canConvertBlock(ItemStack stack, World world, int i, int j, int k) {
        return true;
    }
    
    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int metadata = world.getBlockMetadata(x, y, z);
        int strata = this.getStrata(metadata);
        int toolLevel = this.getConversionLevelForTool(stack, world, x, y, z);
        
        if (this.getIsCracked(metadata)) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.type.roughStoneID(), 0);
            
            if (!world.isRemote && toolLevel > 0) {
                world.playAuxSFX(2269, x, y, z, 0);
                ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.rockItemID(), 1, this.type.rockItemMetadata()), side);
            }
        }
        else if (toolLevel == 2) {
            if (world.getDifficulty().doesStonePickBreakStone()) {
                if (!world.isRemote) {
                    world.playAuxSFX(2001, x, y, z, this.blockID);
                }
                
                this.dropBlockAsItem(world, x, y, z, metadata, 0);
                world.setBlockToAir(x, y, z);
            }
            else {
                world.setBlockAndMetadataWithNotify(x, y, z, RoughStoneBlock.strataLevelBlockArray[strata].blockID, 4);
                
                if (!world.isRemote) {
                    world.playAuxSFX(2269, x, y, z, 0);
                    ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.rockItemID(), 3, this.type.rockItemMetadata()), side);
                    ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.gravelPileID(), 1, this.type.gravelPileMetadata()), side);
                }
            }
        }
        else if (toolLevel == 3) {
            world.setBlockAndMetadataWithNotify(x, y, z, RoughStoneBlock.strataLevelBlockArray[strata].blockID, 2);
            
            if (!world.isRemote) {
                world.playAuxSFX(2269, x, y, z, 0);
                ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.stoneBrickID(), 1, this.type.stoneBrickMetadata()), side);
                ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.gravelPileID(), 1, this.type.gravelPileMetadata()), side);
            }
        }
        else {
            if (!world.isRemote) {
                world.playAuxSFX(2270, x, y, z, 0);
                ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.type.gravelPileID(), 1, this.type.gravelPileMetadata()), side);
            }
            
            this.setIsCracked(world, x, y, z, true);
        }
        
        return true;
    }
    
    protected int getConversionLevelForTool(ItemStack stack, World world, int x, int y, int z) {
        if (stack != null) {
            int toolLevel;
            
            if (stack.getItem() instanceof PickaxeItem) {
                toolLevel = ((ToolItem)stack.getItem()).toolMaterial.getHarvestLevel();
                
                if (toolLevel >= this.getEfficientToolLevel(world, x, y, z)) {
                    return 2;
                }
            }
            else if (stack.getItem() instanceof ChiselItem) {
                toolLevel = ((ToolItem)stack.getItem()).toolMaterial.getHarvestLevel();
                
                if (toolLevel >= this.getEfficientToolLevel(world, x, y, z)) {
                    if (toolLevel >= this.getUberToolLevel(world, x, y, z)) {
                        return 3;
                    }
                    
                    return 1;
                }
            }
        }
        
        return 0;
    }
    
    //------ Client Only Functionality ------//
    
    @Environment(EnvType.CLIENT)
    private Icon crackedIcon;
    
    @Override
    @Environment(EnvType.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(blockID, 1, 0));
    }
    
    @Override
    @Environment(EnvType.CLIENT)
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }
    
    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        super.registerIcons(register);
        crackedIcon = register.registerIcon(this.getTextureName() + "_cracked");
    }
    
    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        return this.getIsCracked(metadata) ? this.crackedIcon : this.blockIcon;
    }
}
