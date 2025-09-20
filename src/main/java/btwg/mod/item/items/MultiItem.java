package btwg.mod.item.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.List;

public class MultiItem extends Item {
    private final String name;
    private final String[] types;

    public MultiItem(int id, String name, String[] types) {
        super(id);

        this.name = name;
        this.types = types;

        this.setUnlocalizedName("btwg." + name);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int type = MathHelper.clamp_int(stack.getItemDamage(), 0, this.types.length - 1);
        return super.getUnlocalizedName() + "." + this.types[type];
    }

    //------ Class Specific Functionality ------//

    public int getNumTypes() {
        return this.types.length;
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    private Icon[] icons;

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIconFromDamage(int iDamage) {
        int index = MathHelper.clamp_int(iDamage, 0, this.types.length - 1);
        return this.icons[index];
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.icons = new Icon[this.types.length];
        String[] iconNames = Arrays.stream(this.types)
                .map(type -> "btwg:" + type + "_" + this.name)
                .toArray(String[]::new);

        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = iconRegister.registerIcon(iconNames[i]);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.types.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }
}
