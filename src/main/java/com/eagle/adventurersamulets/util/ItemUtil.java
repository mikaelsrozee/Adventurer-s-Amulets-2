package com.eagle.adventurersamulets.util;

import com.eagle.adventurersamulets.api.amp.IAMPItem;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;

public class ItemUtil {

  public static void addToolTipInformation(ItemStack itemStack, IAMPItem item,
      List<String> tooltip) {
    if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
      tooltip.add(I18n.format("amptype.currentAMP") + ": " + item.getCurrentAMP(itemStack));
    } else {
      tooltip.add(
          I18n.format("amptype.currentAMP") + ": " + item.getCurrentAMP(itemStack) + " (" + item
              .getAMPType(itemStack).getLocalizedName() + ")");
    }
  }

  public static int getCurrentAMP(ItemStack itemStack, int initial) {
    if (itemStack.getTagCompound() == null) {
      itemStack.setTagCompound(new NBTTagCompound());
    }

    if (!itemStack.getTagCompound().hasKey("AMP")) {
      itemStack.getTagCompound().setInteger("AMP", initial);
      return initial;
    }

    return itemStack.getTagCompound().getInteger("AMP");
  }

}
