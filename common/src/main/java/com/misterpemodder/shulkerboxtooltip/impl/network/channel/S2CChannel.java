package com.misterpemodder.shulkerboxtooltip.impl.network.channel;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.level.ServerPlayer;

/**
 * Server-to-client channel abstraction.
 *
 * @param <T> The message data type.
 */
public interface S2CChannel<T> extends Channel<T> {
  /**
   * Registers handling of messages in this channel from the server.
   */
  @Environment(EnvType.CLIENT)
  void register();

  /**
   * Unregisters handling of messages in this channel from the server.
   */
  @Environment(EnvType.CLIENT)
  void unregister();

  /**
   * Sends a message to a specific player.
   *
   * @param player  The target player.
   * @param message The message to send.
   */
  void sendTo(ServerPlayer player, T message);
}
