package com.eagle.adventurersamulets.proxy;

import com.eagle.adventurersamulets.Dictionary.Core;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

  @Override
  public ClientProxy getClientProxy() {
    return this;
  }

  @Override
  public void initModel(Item item, int meta, String id) {
    ModelLoader.setCustomModelResourceLocation(item, meta,
        new ModelResourceLocation(Core.MOD_ID + ":" + id, "inventory"));
  }

}
