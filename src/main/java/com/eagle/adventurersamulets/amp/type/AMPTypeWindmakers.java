package com.eagle.adventurersamulets.amp.type;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.AMP;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class AMPTypeWindmakers implements IAMPType {

  @Override
  public String getID() {
    return Dictionary.AMP.WINDMAKERS_ID;
  }

  @Override
  public String getLocalizedName() {
    return I18n.format(AMP.WINDMAKERS_KEY);
  }

  @Override
  public boolean isChargeConditionCorrect(EntityPlayer player) {
    return player.posY > ModConfig.windmakersHeight;
  }

}
