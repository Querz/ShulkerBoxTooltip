package com.misterpemodder.shulkerboxtooltip.impl.tooltip;

import com.misterpemodder.shulkerboxtooltip.ShulkerBoxTooltip;
import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.misterpemodder.shulkerboxtooltip.api.ShulkerBoxTooltipApi;
import com.misterpemodder.shulkerboxtooltip.api.provider.PreviewProvider;
import com.misterpemodder.shulkerboxtooltip.api.renderer.PreviewRenderer;
import com.misterpemodder.shulkerboxtooltip.impl.config.Configuration.PreviewPosition;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class PreviewClientTooltipComponent extends PositionAwareClientTooltipComponent {
  private final PreviewRenderer renderer;
  private final PreviewProvider provider;
  private final PreviewContext context;

  public PreviewClientTooltipComponent(PreviewTooltipComponent data) {
    PreviewRenderer renderer = data.provider().getRenderer();

    if (renderer == null)
      renderer = PreviewRenderer.getDefaultRendererInstance();
    this.renderer = renderer;
    this.provider = data.provider();
    this.context = data.context();

    renderer.setPreview(this.context, this.provider);
    renderer.setPreviewType(
        ShulkerBoxTooltipApi.getCurrentPreviewType(this.provider.isFullPreviewAvailable(this.context)));
  }

  @Override
  public int getHeight() {
    if (ShulkerBoxTooltip.config.preview.position == PreviewPosition.INSIDE)
      return this.renderer.getHeight() + 2 + 4;
    return 0;
  }

  @Override
  public int getWidth(Font font) {
    if (ShulkerBoxTooltip.config.preview.position == PreviewPosition.INSIDE)
      return this.renderer.getWidth() + 2;
    return 0;
  }

  @Override
  public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
    this.drawAt(x, y, graphics, font, 0, 0);
  }

  @Override
  public void drawItemsWithTooltipPosition(Font font, int x, int y, GuiGraphics graphics, int tooltipTopY,
      int tooltipBottomY, int mouseX, int mouseY) {
    PreviewPosition position = ShulkerBoxTooltip.config.preview.position;

    if (position != PreviewPosition.INSIDE) {
      int h = this.renderer.getHeight();
      int w = this.renderer.getWidth();
      int screenW = graphics.guiWidth();
      int screenH = graphics.guiHeight();

      x = Math.min(x - 4, screenW - w);
      y = tooltipBottomY;
      if (position == PreviewPosition.OUTSIDE_TOP || (position == PreviewPosition.OUTSIDE && y + h > screenH))
        y = tooltipTopY - h;
    }
    this.drawAt(x, y, graphics, font, mouseX, mouseY);
  }

  private void drawAt(int x, int y, GuiGraphics graphics, Font font, int mouseX, int mouseY) {
    this.renderer.draw(x, y, graphics, font, mouseX, mouseY);
  }
}
