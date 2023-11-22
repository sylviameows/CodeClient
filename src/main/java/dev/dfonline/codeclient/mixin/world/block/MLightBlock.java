package dev.dfonline.codeclient.mixin.world.block;

import dev.dfonline.codeclient.CodeClient;
import dev.dfonline.codeclient.config.Config;
import dev.dfonline.codeclient.location.Build;
import dev.dfonline.codeclient.location.Creator;
import dev.dfonline.codeclient.location.Dev;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.LightBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightBlock.class)
public class MLightBlock {
    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void getRenderType(BlockState state, CallbackInfoReturnable<BlockRenderType> cir) {
        if (Config.getConfig().InvisibleBlocksInDev && CodeClient.location instanceof Creator)
            cir.setReturnValue(BlockRenderType.MODEL);
    }
}
