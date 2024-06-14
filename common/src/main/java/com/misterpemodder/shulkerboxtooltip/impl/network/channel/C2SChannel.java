package com.misterpemodder.shulkerboxtooltip.impl.network.channel;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.level.ServerPlayer;

/**
 * Client-to-server channel abstraction.
 *
 * @param <T> The message data type.
 */
public interface C2SChannel<T> extends Channel<T> {
  /**
   * Registers handling of messages in this channel for the given player.
   *
   * @param player The player.
   */
  void registerFor(ServerPlayer player);

  /**
   * Unregisters handling of messages in this channel for the given player.
   *
   * @param player The player.
   */
  void unregisterFor(ServerPlayer player);

  /**
   * Sends a message to the server.
   *
   * @param message The message to send.
   */
  @Environment(EnvType.CLIENT)
  void sendToServer(T message);

  /**
   * @return Whether a payload can successfully be sent to the server.
   */
  @Environment(EnvType.CLIENT)
  boolean canSendToServer();

  /**
   * Called when the client player disconnects from a server.
   */
  @Environment(EnvType.CLIENT)
  void onDisconnect();
}
