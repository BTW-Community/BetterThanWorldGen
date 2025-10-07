package btwg.api.block.blocks;

import btwg.mod.BetterThanWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.List;

public class MultiTextureBlock extends Block {
    protected final String name;
    protected final String[] types;

    protected final boolean useTypeAsFolder;
    protected final String prefix;

    public MultiTextureBlock(int id, Material material, String name, String[] types) {
        this(id, material, name, types, false, null);
    }

    public MultiTextureBlock(int id, Material material, String name, String[] types, boolean useTypeAsFolder, String prefix) {
        super(id, material);

        this.name = name;
        this.types = types;
        this.useTypeAsFolder = useTypeAsFolder;
        this.prefix = prefix;

        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + name);
    }

    //------ Class Specific Functionality ------//

    public int getNumTypes() {
        return this.types.length;
    }

    public String[] getNames() {
        return types;
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    protected Icon[] icons;

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
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
