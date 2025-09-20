package btwg.mod.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.List;

public class MultiTextureBlock extends Block {
    private final String name;
    private final String[] types;

    public MultiTextureBlock(int id, Material material, String name, String[] types) {
        super(id, material);

        this.name = name;
        this.types = types;

        this.setUnlocalizedName("btwg." + name);
    }

    //------ Class Specific Functionality ------//

    public int getNumTypes() {
        return this.types.length;
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    private Icon[] icons;

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.icons = new Icon[types.length];

        String[] iconNames = Arrays.stream(this.types)
                .map(type -> "btwg:" + type + "_" + this.name)
                .toArray(String[]::new);

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
