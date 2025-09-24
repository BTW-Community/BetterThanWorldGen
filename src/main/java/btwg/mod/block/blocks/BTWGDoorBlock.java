package btwg.mod.block.blocks;

import btw.block.blocks.DoorBlockWood;
import btwg.mod.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class BTWGDoorBlock extends DoorBlockWood {
    private final WoodType woodType;

    public BTWGDoorBlock(int id, WoodType woodType) {
        super(id);

        this.woodType = woodType;

        this.setUnlocalizedName("btwg." + this.woodType.name() + "_door");
        this.setTextureName("btwg:" + this.woodType.name() + "_door");
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return (par1 & 8) != 0 ? 0 : this.woodType.doorItemID();
    }

    @Override
    public int damageDropped(int metadata) {
        return this.woodType.doorItemMetadata();
    }

    //------ Client Side Functionality  ------//

    @Environment(EnvType.CLIENT)
    private Icon[] bottom_icons;
    @Environment(EnvType.CLIENT)
    private Icon[] topIcons;

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.topIcons = new Icon[2];
        this.bottom_icons = new Icon[2];
        this.topIcons[0] = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.bottom_icons[0] = iconRegister.registerIcon(this.getTextureName() + "_bottom");
        this.topIcons[1] = new IconFlipped(this.topIcons[0], true, false);
        this.bottom_icons[1] = new IconFlipped(this.bottom_icons[0], true, false);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int par1, int par2) {
        return this.bottom_icons[0];
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int metadata) {
        if (metadata != 1 && metadata != 0) {
            int var6 = this.getFullMetadata(blockAccess, x, y, z);
            int var7 = var6 & 3;
            boolean var8 = (var6 & 4) != 0;
            boolean isFlipped = false;
            boolean isTop = (var6 & 8) != 0;

            if (var8) {
                if (var7 == 0 && metadata == 2) {
                    isFlipped = true;
                } else if (var7 == 1 && metadata == 5) {
                    isFlipped = true;
                } else if (var7 == 2 && metadata == 3) {
                    isFlipped = true;
                } else if (var7 == 3 && metadata == 4) {
                    isFlipped = true;
                }
            }
            else {
                if (var7 == 0 && metadata == 5) {
                    isFlipped = true;
                } else if (var7 == 1 && metadata == 3) {
                    isFlipped = true;
                } else if (var7 == 2 && metadata == 4) {
                    isFlipped = true;
                } else if (var7 == 3 && metadata == 2) {
                    isFlipped = true;
                }

                if ((var6 & 0x10) != 0) {
                    isFlipped = !isFlipped;
                }
            }
            return isTop ? this.topIcons[isFlipped ? 1 : 0] : this.bottom_icons[isFlipped ? 1 : 0];
        }
        return this.bottom_icons[0];
    }
}
