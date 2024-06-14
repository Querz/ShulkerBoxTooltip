package com.misterpemodder.shulkerboxtooltip.mixin.client;

import com.misterpemodder.shulkerboxtooltip.impl.hook.GuiGraphicsExtensions;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TooltipRenderUtil.class)
public class TooltipRenderUtilMixin {
  @Inject(at = @At(value = "HEAD"), method = "renderTooltipBackground(Lnet/minecraft/client/gui/GuiGraphics;IIIII)V")
  private static void updateTooltipLeftAndBottomPos(GuiGraphics graphics, int x, int y, int width, int height, int z,
      CallbackInfo ci) {
    GuiGraphicsExtensions posAccess = (GuiGraphicsExtensions) graphics;
    posAccess.setTooltipTopYPosition(y - 3);
    posAccess.setTooltipBottomYPosition(posAccess.getTooltipTopYPosition() + height + 6);
  }
}
