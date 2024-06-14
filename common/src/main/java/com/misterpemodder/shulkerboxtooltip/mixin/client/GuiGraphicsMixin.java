package com.misterpemodder.shulkerboxtooltip.mixin.client;

import com.misterpemodder.shulkerboxtooltip.impl.hook.GuiGraphicsExtensions;
import com.misterpemodder.shulkerboxtooltip.impl.tooltip.PositionAwareClientTooltipComponent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin implements GuiGraphicsExtensions {
  @Unique
  private int tooltipTopYPosition = 0;
  @Unique
  private int tooltipBottomYPosition = 0;
  @Unique
  private int mouseX = 0;
  @Unique
  private int mouseY = 0;

  @Redirect(at = @At(value = "INVOKE", target =
      "Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent;"
          + "renderImage(Lnet/minecraft/client/gui/Font;IILnet/minecraft/client/gui/GuiGraphics;)V"), method =
      "renderTooltipInternal(" + "Lnet/minecraft/client/gui/Font;Ljava/util/List;II"
          + "Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;)V")
  private void drawPosAwareComponent(ClientTooltipComponent component, Font font, int x, int y, GuiGraphics graphics) {
    if (component instanceof PositionAwareClientTooltipComponent posAwareComponent) {
      posAwareComponent.drawItemsWithTooltipPosition(font, x, y, graphics, this.getTooltipTopYPosition(),
          this.getTooltipBottomYPosition(), this.getMouseX(), this.getMouseY());
    } else {
      component.renderImage(font, x, y, graphics);
    }
  }

  @Override
  @Intrinsic
  public void setTooltipTopYPosition(int topY) {
    this.tooltipTopYPosition = topY;
  }

  @Override
  @Intrinsic
  public void setTooltipBottomYPosition(int bottomY) {
    this.tooltipBottomYPosition = bottomY;
  }

  @Override
  @Intrinsic
  public int getTooltipTopYPosition() {
    return this.tooltipTopYPosition;
  }

  @Override
  @Intrinsic
  public int getTooltipBottomYPosition() {
    return this.tooltipBottomYPosition;
  }

  @Override
  @Intrinsic
  public void setMouseX(int mouseX) {
    this.mouseX = mouseX;
  }

  @Override
  @Intrinsic
  public int getMouseX() {
    return this.mouseX;
  }

  @Override
  @Intrinsic
  public void setMouseY(int mouseY) {
    this.mouseY = mouseY;
  }

  @Override
  @Intrinsic
  public int getMouseY() {
    return this.mouseY;
  }

}
