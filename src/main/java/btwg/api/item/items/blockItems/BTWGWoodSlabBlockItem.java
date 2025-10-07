package btwg.api.item.items.blockItems;

import btw.item.blockitems.SlabWithMetadataBlockItem;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;

public class BTWGWoodSlabBlockItem extends SlabWithMetadataBlockItem {
    public BTWGWoodSlabBlockItem(int itemID, String[] names) {
        super(itemID, names);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (this.names != null) {
            return super.getUnlocalizedName() + "." + this.names[MathHelper.clamp_int(stack.getItemDamage() / 2, 0, this.names.length - 1)];
        }
        return stack.getUnlocalizedName();
    }
}
