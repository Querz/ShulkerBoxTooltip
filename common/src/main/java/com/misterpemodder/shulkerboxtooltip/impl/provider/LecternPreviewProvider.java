package com.misterpemodder.shulkerboxtooltip.impl.provider;

import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

import java.util.List;
import java.util.function.Supplier;

public class LecternPreviewProvider extends InventoryAwarePreviewProvider<Container> {
  private static final MapCodec<ItemStack> CODEC = ItemStack.CODEC.fieldOf("Book");

  public LecternPreviewProvider(int maxRowSize, Supplier<? extends Container> inventoryFactory) {
    super(maxRowSize, inventoryFactory);
  }

  @Override
  public List<ItemStack> getInventory(PreviewContext context) {
    int invMaxSize = this.getInventoryMaxSize(context);
    List<ItemStack> inv = NonNullList.withSize(invMaxSize, ItemStack.EMPTY);
    CustomData nbtComponent = context.stack().get(DataComponents.BLOCK_ENTITY_DATA);

    if (nbtComponent != null)
      nbtComponent.read(CODEC).result().ifPresent(book -> inv.set(0, book));

    return inv;
  }

  @Override
  public boolean showTooltipHints(PreviewContext context) {
    return context.stack().has(DataComponents.BLOCK_ENTITY_DATA);
  }
}
