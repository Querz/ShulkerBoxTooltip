package com.misterpemodder.shulkerboxtooltip.impl.network.channel;

import com.misterpemodder.shulkerboxtooltip.impl.network.context.MessageContext;
import com.misterpemodder.shulkerboxtooltip.impl.network.message.MessageType;
import net.minecraft.resources.ResourceLocation;

/**
 * Base network channel abstraction.
 *
 * @param <T> The message data type.
 */
public interface Channel<T> {
  ResourceLocation getId();

  MessageType<T> getMessageType();

  void registerPayloadType();

  void onRegister(MessageContext<T> context);

  void onUnregister(MessageContext<T> context);
}
