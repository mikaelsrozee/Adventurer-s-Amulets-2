package com.eagle.adventurersamulets.items;

import com.eagle.adventurersamulets.AdventurersAmulets;
import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.util.ItemUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSearing extends ItemMod implements IAMPItem {

  public ItemSearing(String name) {
    super(name);
  }

  @SideOnly(Side.CLIENT)
  public void initModel() {
    AdventurersAmulets.proxy.initModel(this, 0, name + "_0");
    AdventurersAmulets.proxy.initModel(this, 1, name + "_1");
    AdventurersAmulets.proxy.initModel(this, 2, name + "_2");
    AdventurersAmulets.proxy.initModel(this, 3, name + "_3");
  }

  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip,
      ITooltipFlag flag) {
    ItemUtil.addToolTipInformation(itemStack, this, tooltip);
  }

  public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot,
      boolean isSelected) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) entity;
      if (getAMPType(itemStack).isChargeConditionCorrect(player)
          && getCurrentAMP(itemStack) < getMaximumAMP(itemStack)) {
        setCurrentAMP(itemStack, getCurrentAMP(itemStack) + 1);
      }

      if (getCurrentAMP(itemStack) >= getMaximumAMP(itemStack)) {
        setDamage(itemStack, 3);
      } else if (getCurrentAMP(itemStack) >= getMaximumAMP(itemStack) * 0.66) {
        setDamage(itemStack, 2);
      } else if (getCurrentAMP(itemStack) >= getMaximumAMP(itemStack) * 0.33) {
        setDamage(itemStack, 1);
      } else {
        setDamage(itemStack, 0);
      }
    }
  }

  @Override
  public int getCurrentAMP(ItemStack itemStack) {
    return ItemUtil.getCurrentAMP(itemStack, 0);
  }

  @Override
  public int getMaximumAMP(ItemStack itemStack) {
    return ModConfig.searingMaxAMP;
  }

  @Override
  public void setCurrentAMP(ItemStack itemStack, int amp) {
    if (itemStack.getTagCompound() != null) {
      itemStack.getTagCompound().setInteger("AMP", amp);
    }
  }

  @Override
  public IAMPType getAMPType(ItemStack itemStack) {
    return AdvAmAPI.getAMPTypeFromID(Dictionary.AMP.INFERNAL_ID);
  }

}
