package com.misterpemodder.shulkerboxtooltip.impl.provider;

import com.misterpemodder.shulkerboxtooltip.ShulkerBoxTooltip;
import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.misterpemodder.shulkerboxtooltip.api.color.ColorKey;
import com.misterpemodder.shulkerboxtooltip.impl.config.Configuration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ShulkerBoxPreviewProvider extends InventoryAwarePreviewProvider<ShulkerBoxBlockEntity> {
  public ShulkerBoxPreviewProvider(int maxRowSize, Supplier<? extends ShulkerBoxBlockEntity> blockEntitySupplier) {
    super(maxRowSize, blockEntitySupplier);
  }

  @Override
  public boolean showTooltipHints(PreviewContext context) {
    return true;
  }

  @Override
  @Environment(EnvType.CLIENT)
  public ColorKey getWindowColorKey(PreviewContext context) {
    DyeColor dye = ((ShulkerBoxBlock) Block.byItem(context.stack().getItem())).getColor();

    if (dye == null)
      return ColorKey.SHULKER_BOX;
    return switch (dye) {
      case ORANGE -> ColorKey.ORANGE_SHULKER_BOX;
      case MAGENTA -> ColorKey.MAGENTA_SHULKER_BOX;
      case LIGHT_BLUE -> ColorKey.LIGHT_BLUE_SHULKER_BOX;
      case YELLOW -> ColorKey.YELLOW_SHULKER_BOX;
      case LIME -> ColorKey.LIME_SHULKER_BOX;
      case PINK -> ColorKey.PINK_SHULKER_BOX;
      case GRAY -> ColorKey.GRAY_SHULKER_BOX;
      case LIGHT_GRAY -> ColorKey.LIGHT_GRAY_SHULKER_BOX;
      case CYAN -> ColorKey.CYAN_SHULKER_BOX;
      case PURPLE -> ColorKey.PURPLE_SHULKER_BOX;
      case BLUE -> ColorKey.BLUE_SHULKER_BOX;
      case BROWN -> ColorKey.BROWN_SHULKER_BOX;
      case GREEN -> ColorKey.GREEN_SHULKER_BOX;
      case RED -> ColorKey.RED_SHULKER_BOX;
      case BLACK -> ColorKey.BLACK_SHULKER_BOX;
      default -> ColorKey.WHITE_SHULKER_BOX;
    };
  }

  @Override
  public List<Component> addTooltip(PreviewContext context) {
    ItemStack stack = context.stack();

    // Restore the vanilla behavior of adding question marks to the tooltip when the item has a loot table
    if (this.canUseLootTables()
        && ShulkerBoxTooltip.config.tooltip.lootTableInfoType == Configuration.LootTableInfoType.HIDE && stack.has(
        DataComponents.CONTAINER_LOOT)) {
      Style style = Style.EMPTY.withColor(ChatFormatting.GRAY);

      return Collections.singletonList(Component.literal("???????").setStyle(style));
    }
    return super.addTooltip(context);
  }
}
