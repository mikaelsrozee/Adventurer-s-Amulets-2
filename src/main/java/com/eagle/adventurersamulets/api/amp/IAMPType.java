package com.eagle.adventurersamulets.api.amp;

import net.minecraft.entity.player.EntityPlayer;

public interface IAMPType {

  String getID();

  String getLocalizedName();

  boolean isChargeConditionCorrect(EntityPlayer player);

}
