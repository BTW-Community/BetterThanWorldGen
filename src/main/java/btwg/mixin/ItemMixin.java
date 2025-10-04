package btwg.mixin;

import btwg.api.item.ItemInterface;
import net.minecraft.src.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin implements ItemInterface {
    @Unique
    private String btwgNamespaceOverride;

    @Inject(method = "getModId", at = @At("RETURN"), cancellable = true)
    public void redirectModID(CallbackInfoReturnable<String> cir) {
        if (btwgNamespaceOverride != null)
            cir.setReturnValue(btwgNamespaceOverride);
    }

    @Override
    public Item btwg$setModNamespace(String namespace) {
        btwgNamespaceOverride = namespace;
        return (Item) (Object) this;
    }
}