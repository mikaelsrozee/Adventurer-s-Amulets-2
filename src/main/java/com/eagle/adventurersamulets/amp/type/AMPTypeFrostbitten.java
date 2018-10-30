package com.eagle.adventurersamulets.amp.type;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.AMP;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class AMPTypeFrostbitten implements IAMPType {

  @Override
  public String getID() {
    return Dictionary.AMP.FROSTBITTEN_ID;
  }

  @Override
  public String getLocalizedName() {
    return I18n.format(AMP.FROSTBITTEN_KEY);
  }

  @Override
  public boolean isChargeConditionCorrect(EntityPlayer player) {
    return player.getEntityWorld().canSnowAt(player.getPosition(), false);
  }

}
