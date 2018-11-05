package com.eagle.adventurersamulets.proxy;

import net.minecraft.item.Item;

public interface IProxy {

  ClientProxy getClientProxy();

  void initModel(Item item, int meta, String id);
}
