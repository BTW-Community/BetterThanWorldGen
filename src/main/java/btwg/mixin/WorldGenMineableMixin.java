package btwg.mixin;

import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.WorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldGenMinable.class)
public abstract class WorldGenMineableMixin extends WorldGenerator {
    @ModifyConstant(method = "generate", constant = @Constant(intValue = 48))
    private int modifyStrata2Height(int originalValue) {
        return 66;
    }

    @ModifyConstant(method = "generate", constant = @Constant(intValue = 24))
    private int modifyStrata3Height(int originalValue) {
        return 33;
    }
}
