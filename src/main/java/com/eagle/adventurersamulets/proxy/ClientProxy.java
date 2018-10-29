package com.eagle.adventurersamulets.proxy;

import com.eagle.adventurersamulets.init.ModItems;
import com.eagle.adventurersamulets.items.ItemMod;

public class ClientProxy extends CommonProxy {

  @Override
  public ClientProxy getClientProxy() {
    return this;
  }

  @Override
  public void initModels() {
    ModItems.modItems.forEach(ItemMod::initModel);
  }

}
