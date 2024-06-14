package com.misterpemodder.shulkerboxtooltip.impl.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;

public abstract class PositionAwareClientTooltipComponent implements ClientTooltipComponent {
  public abstract void drawItemsWithTooltipPosition(Font font, int x, int y, GuiGraphics graphics, int tooltipTopY,
      int tooltipBottomY, int mouseX, int mouseY);
}
