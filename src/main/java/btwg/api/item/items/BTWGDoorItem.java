package btwg.api.item.items;

import btw.item.items.DoorItem;
import btwg.mod.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.List;

public class BTWGDoorItem extends DoorItem {
    private final WoodType[] woodTypes;

    public BTWGDoorItem(int id, WoodType[] woodTypes) {
        super(id);

        this.woodTypes = woodTypes;

        this.setUnlocalizedName("btwg.door");
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int type = MathHelper.clamp_int(stack.getItemDamage(), 0, this.woodTypes.length - 1);
        return super.getUnlocalizedName() + "." + this.woodTypes[type].name();
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
        if (facing == 1
                && player.canPlayerEdit(x, ++y, z, facing, stack)
                && player.canPlayerEdit(x, y + 1, z, facing, stack)
                && this.getDoorBlock().canPlaceBlockAt(world, x, y, z))
        {
            int direction = MathHelper.floor_double((double)((player.rotationYaw + 180.0f) * 4.0f / 360.0f) - 0.5) & 3;

            int metadata = MathHelper.clamp_int(stack.getItemDamage(), 0, this.woodTypes.length - 1);
            ItemDoor.placeDoorBlock(world, x, y, z, direction, Block.blocksList[this.woodTypes[metadata].doorBlockID()]);

            --stack.stackSize;
            return true;
        }
        return false;
    }

    // Not used, but required for the DoorItem superclass
    // Has an implementation so that it doesn't crash if it is called for whatever reason
    @Override
    public Block getDoorBlock() {
        return Block.blocksList[this.woodTypes[0].doorBlockID()];
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    private Icon[] icons;

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIconFromDamage(int iDamage) {
        int index = MathHelper.clamp_int(iDamage, 0, this.woodTypes.length - 1);
        return this.icons[index];
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.icons = new Icon[this.woodTypes.length];
        String[] iconNames = Arrays.stream(this.woodTypes)
                .map(type -> "btwg:" + type.name() + "_door")
                .toArray(String[]::new);

        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = iconRegister.registerIcon(iconNames[i]);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.woodTypes.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }
}
