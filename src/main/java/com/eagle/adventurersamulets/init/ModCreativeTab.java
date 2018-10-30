package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.Dictionary.Core;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTab extends CreativeTabs {

  public ModCreativeTab() {
    super(Core.MOD_ID);
  }

  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(ModItems.pestle);
  }

}
