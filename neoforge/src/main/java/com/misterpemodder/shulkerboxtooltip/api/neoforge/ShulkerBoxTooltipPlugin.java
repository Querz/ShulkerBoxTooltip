package com.misterpemodder.shulkerboxtooltip.api.neoforge;

import com.misterpemodder.shulkerboxtooltip.api.ShulkerBoxTooltipApi;
import net.neoforged.fml.IExtensionPoint;

import java.util.function.Supplier;

/**
 * <b>NeoForge-specific API, do not use on Forge, Fabric, or Quilt!</b>
 *
 * @param apiImplSupplier A function that returns an instance of {@link ShulkerBoxTooltipApi}.
 * @since 4.1.0
 */
public record ShulkerBoxTooltipPlugin(Supplier<ShulkerBoxTooltipApi> apiImplSupplier) implements IExtensionPoint {
}
