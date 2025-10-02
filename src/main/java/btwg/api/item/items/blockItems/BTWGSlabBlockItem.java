package btwg.api.item.items.blockItems;

import btw.item.blockitems.SlabBlockItem;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;

public class BTWGSlabBlockItem extends SlabBlockItem {
    private final String baseName;
    private final String[] names;

    public BTWGSlabBlockItem(int id, String baseName, String[] names) {
        super(id);

        this.baseName = baseName;
        this.names = names;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.btwg." + baseName + "." + names[MathHelper.clamp_int(stack.getItemDamage(), 0, names.length - 1)];
    }

    @Override
    public int getMetadata(int damage) {
        return MathHelper.clamp_int(damage, 0, names.length - 1);
    }
}
