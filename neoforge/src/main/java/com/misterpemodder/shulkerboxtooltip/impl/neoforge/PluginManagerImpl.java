package com.misterpemodder.shulkerboxtooltip.impl.neoforge;

import com.misterpemodder.shulkerboxtooltip.api.neoforge.ShulkerBoxTooltipPlugin;
import com.misterpemodder.shulkerboxtooltip.impl.PluginManager;
import com.misterpemodder.shulkerboxtooltip.impl.PluginManager.PluginContainer;
import net.neoforged.fml.ModList;
import org.jetbrains.annotations.Contract;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class PluginManagerImpl {
  private PluginManagerImpl() {
  }

  /**
   * Implementation of {@link PluginManager#getPluginContainers()}.
   */
  @Contract(" -> !null")
  public static List<PluginContainer> getPluginContainers() {
    return ModList.get().applyForEachModContainer(
        modContainer -> modContainer.getCustomExtension(ShulkerBoxTooltipPlugin.class)
            .map(extension -> new PluginContainer(modContainer.getModId(), extension.apiImplSupplier()))).flatMap(
        Optional::stream).collect(Collectors.toList());
  }
}
