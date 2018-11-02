package com.eagle.adventurersamulets.proxy;

import net.minecraft.item.Item;

public class CommonProxy implements IProxy {

  @Override
  public ClientProxy getClientProxy() {
    return null;
  }

  @Override
  public void initModel(Item item, int meta, String id) {

  }

}
