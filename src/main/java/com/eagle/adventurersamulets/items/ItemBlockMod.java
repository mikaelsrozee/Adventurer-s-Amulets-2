package com.eagle.adventurersamulets.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockMod extends ItemBlock {

  public ItemBlockMod(Block block) {
    super(block);
    setRegistryName(block.getRegistryName());
  }

}
