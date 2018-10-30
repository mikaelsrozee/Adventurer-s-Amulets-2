package com.eagle.adventurersamulets.api.amp;

import net.minecraft.item.ItemStack;

public interface IAMPItem {

  /**
   * Returns the amount of AMP stored in the ItemStack.
   */
  int getCurrentAMP(ItemStack itemStack);

  /**
   * Returns the maximum amount of AMP that can be stored in the ItemStack.
   */
  int getMaximumAMP(ItemStack itemStack);

  /**
   * Sets the current AMP in the ItemStack.
   */
  void setCurrentAMP(ItemStack itemStack, int amp);

  /**
   * Returns the type of AMP the ItemStack can receive.
   */
  IAMPType getAMPType(ItemStack itemStack);

}
