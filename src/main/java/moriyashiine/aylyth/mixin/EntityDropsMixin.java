package moriyashiine.aylyth.mixin;

import moriyashiine.aylyth.common.util.AylythDropsAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityDropsMixin implements AylythDropsAccessor {
    @Unique
    private boolean aylyth$preventDrops = false;

    @Override
    public boolean aylyth$shouldPreventDrops() {
        return aylyth$preventDrops;
    }

    @Override
    public void aylyth$setPreventDrops(boolean preventDrops) {
        this.aylyth$preventDrops = preventDrops;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    private void injectWriteNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if (aylyth$preventDrops) {
            nbt.putBoolean("AylythPreventDrops", true);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    private void injectReadNbt(NbtCompound nbt, CallbackInfo ci) {
        this.aylyth$preventDrops = nbt.getBoolean("AylythPreventDrops");
    }
}
