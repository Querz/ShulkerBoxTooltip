package com.misterpemodder.shulkerboxtooltip.impl.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record Payload<T>(CustomPacketPayload.Type<?> id, T value) implements CustomPacketPayload {
  @Override
  public Type<? extends CustomPacketPayload> type() {
    return this.id;
  }
}
