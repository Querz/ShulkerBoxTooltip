package com.misterpemodder.shulkerboxtooltip.impl.network.context;

import com.misterpemodder.shulkerboxtooltip.impl.network.channel.Channel;
import net.minecraft.server.level.ServerPlayer;

public record C2SMessageContext<T>(ServerPlayer player, Channel<T> channel) implements MessageContext<T> {
  @Override
  public void execute(Runnable task) {
    this.player.server.execute(task);
  }

  @Override
  public ServerPlayer getPlayer() {
    return this.player;
  }

  @Override
  public Channel<T> getChannel() {
    return this.channel;
  }

  @Override
  public Side getReceivingSide() {
    return Side.SERVER;
  }
}
