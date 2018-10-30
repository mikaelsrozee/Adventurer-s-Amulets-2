package com.eagle.adventurersamulets.amp.type;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class AMPTypeFloral implements IAMPType {

  @Override
  public String getID() {
    return Dictionary.AMP.FLORAL_ID;
  }

  @Override
  public String getLocalizedName() {
    return I18n.format("amptype.floral.name");
  }

  @Override
  public boolean isChargeConditionCorrect(EntityPlayer player) {
    return player.getEntityWorld().canBlockSeeSky(player.getPosition()) && player.getEntityWorld().isDaytime();
  }

}
