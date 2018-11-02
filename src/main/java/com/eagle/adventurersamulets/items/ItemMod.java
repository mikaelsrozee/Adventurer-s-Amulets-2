package com.eagle.adventurersamulets.items;

import com.eagle.adventurersamulets.AdventurersAmulets;
import com.eagle.adventurersamulets.init.ModItems;
import net.minecraft.item.Item;

public class ItemMod extends Item {

  protected String name;

  public ItemMod(String name) {
    this.name = name;
    setCreativeTab(AdventurersAmulets.creativeTab);
    setRegistryName(name);
    setUnlocalizedName(name);
    ModItems.modItems.add(this);
  }

  public void initModel() {
    AdventurersAmulets.proxy.initModel(this, 0, name);
  }
}
