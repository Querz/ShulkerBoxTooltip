package com.misterpemodder.shulkerboxtooltip.impl;

import com.misterpemodder.shulkerboxtooltip.ShulkerBoxTooltip;
import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.misterpemodder.shulkerboxtooltip.impl.config.Configuration;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public record PreviewContextImpl(ItemStack stack, @Nullable Player owner, Configuration config,
                                 @Nullable HolderLookup.Provider registryLookup) implements PreviewContext {
  public static class Builder implements PreviewContext.Builder {
    private final ItemStack stack;
    private Player owner;
    private HolderLookup.Provider registryLookup;

    public Builder(ItemStack stack) {
      this.stack = stack;
    }

    @Override
    public PreviewContextImpl.Builder withOwner(@Nullable Player owner) {
      this.owner = owner;
      return this;
    }

    @Override
    public PreviewContextImpl.Builder withRegistryLookup(@Nullable HolderLookup.Provider registryLookup) {
      this.registryLookup = registryLookup;
      return this;
    }

    @Nonnull
    @Override
    public PreviewContext build() {
      if (this.registryLookup == null && this.owner != null) {
        this.registryLookup = this.owner.registryAccess();
      }
      return new PreviewContextImpl(this.stack, this.owner, ShulkerBoxTooltip.config, this.registryLookup);
    }
  }
}
