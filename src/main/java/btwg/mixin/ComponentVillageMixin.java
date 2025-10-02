package btwg.mixin;

import net.minecraft.src.ComponentVillage;
import net.minecraft.src.StructureComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ComponentVillage.class)
public abstract class ComponentVillageMixin extends StructureComponent {
    @ModifyConstant(method = "getAverageGroundLevel(Lnet/minecraft/src/World;Lnet/minecraft/src/StructureBoundingBox;)I", constant = @Constant(intValue = 64))
    public int modifyAverageGroundLevel(int originalValue) {
        return originalValue + 100;
    }
}
