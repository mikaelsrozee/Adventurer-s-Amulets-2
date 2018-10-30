package com.eagle.adventurersamulets.amp.type;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.AMP;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class AMPTypeEarthbound implements IAMPType {

  @Override
  public String getID() {
    return Dictionary.AMP.EARTHBOUND_ID;
  }

  @Override
  public String getLocalizedName() {
    return I18n.format(AMP.EARTHBOUND_KEY);
  }

  @Override
  public boolean isChargeConditionCorrect(EntityPlayer player) {
    Material material = player.getEntityWorld().getBlockState(player.getPosition().add(0, -1, 0)).getMaterial();

    return !player.getEntityWorld().canBlockSeeSky(player.getPosition()) && (material == Material.GROUND || material == Material.ROCK) && player.posY < 30;
  }

}
