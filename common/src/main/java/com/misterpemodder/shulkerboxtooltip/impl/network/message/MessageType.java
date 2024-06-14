package com.misterpemodder.shulkerboxtooltip.impl.network.message;

import com.misterpemodder.shulkerboxtooltip.impl.network.context.C2SMessageContext;
import com.misterpemodder.shulkerboxtooltip.impl.network.context.MessageContext;
import com.misterpemodder.shulkerboxtooltip.impl.network.context.S2CMessageContext;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Describes a message.
 *
 * @param <T> The message data.
 */
public interface MessageType<T> {
  /**
   * Writes the message to the packet byte buffer.
   *
   * @param message The message to encode.
   * @param buf     The buffer.
   */
  void encode(T message, FriendlyByteBuf buf);

  /**
   * Reads a message from the given buffer.
   *
   * @param buf The buffer.
   * @return The decoded message.
   */
  T decode(FriendlyByteBuf buf);

  /**
   * Handles the given message.
   *
   * @param message The message to handle.
   * @param context Either an instance of {@link C2SMessageContext} or {@link S2CMessageContext}
   */
  void onReceive(T message, MessageContext<T> context);

  /**
   * Called when the message is registered.
   *
   * @param context Either an instance of {@link C2SMessageContext} or {@link S2CMessageContext}
   */
  default void onRegister(MessageContext<T> context) {
  }

  /**
   * Called when the message is unregistered.
   *
   * @param context Either an instance of {@link C2SMessageContext} or {@link S2CMessageContext}
   */
  default void onUnregister(MessageContext<T> context) {
  }
}
