package com.eagle.adventurersamulets.amp.type;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class AMPTypeAquatic implements IAMPType {

  @Override
  public String getID() {
    return Dictionary.AMP.AQUATIC_ID;
  }

  @Override
  public String getLocalizedName() {
    return I18n.format("amptype.aquatic.name");
  }

  @Override
  public boolean isChargeConditionCorrect(EntityPlayer player) {
    return player.isInWater() || player.getEntityWorld().isRainingAt(player.getPosition());
  }

}
