package btwg.mixin;

import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase {
    public EntityPlayerMixin(World world) {
        super(world);
    }
    
    @ModifyConstant(method = "addExhaustionForJump()V", constant = @Constant(floatValue = 0.2F))
    private float modifyJumpExhaustion(float exhaustion) {
        return 0.1F;
    }

    @ModifyConstant(method = "addExhaustionForJump()V", constant = @Constant(floatValue = 1.0F))
    private float modifySprintJumpExhaustion(float exhaustion) {
        return 0.5F;
    }
}
