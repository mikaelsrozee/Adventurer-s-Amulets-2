package com.eagle.adventurersamulets.items;

import com.eagle.adventurersamulets.AdventurersAmulets;
import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.util.ItemUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemAmbrosia extends ItemMod implements IAMPItem {

  public ItemAmbrosia() {
    super(Dictionary.Item.AMBROSIA);
    setMaxStackSize(1);
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

  @Nonnull
  public ItemStack onItemUseFinish(@Nonnull ItemStack itemStack, World world, @Nonnull EntityLivingBase entity) {
    EntityPlayer player = entity instanceof EntityPlayer ? (EntityPlayer) entity : null;

    if (!world.isRemote) {
      assert player != null;
      player.setHealth(player.getMaxHealth());
      player.setAbsorptionAmount(player.getMaxHealth());
      player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, 1, true, false));
      player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 6000, 1, true, false));
      player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0, true, false));
      player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 6000, 0, true, false));
      player.getFoodStats().setFoodLevel(20);
      player.getFoodStats().setFoodSaturationLevel(20);
      player.resetCooldown();
    }
    setCurrentAMP(itemStack, 0);
    return itemStack;
  }

  public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) entity;

      if (getAMPType(itemStack).isChargeConditionCorrect(player) && getCurrentAMP(itemStack) < getMaximumAMP(itemStack)) {
        setCurrentAMP(itemStack, getCurrentAMP(itemStack) + 1);

        if (world.getTotalWorldTime() % 10 == 0) {
          if (player.experience <= 0 && player.experienceLevel > 0) {
            player.addExperienceLevel(-1);
            player.addExperience(player.xpBarCap());
          }
          player.addExperience(-1);
        }
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

  @Nonnull
  public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
    if (getCurrentAMP(player.getHeldItem(hand)) == getMaximumAMP(player.getHeldItem(hand))) {
      player.setActiveHand(hand);
      return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
    else return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
  }

  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack itemStack) {
    return getCurrentAMP(itemStack) == getMaximumAMP(itemStack);
  }

  public int getMaxItemUseDuration(ItemStack itemStack) {
    return 32;
  }

  @Nonnull
  public EnumAction getItemUseAction(ItemStack itemStack) {
    return EnumAction.DRINK;
  }

  @Override
  public int getCurrentAMP(ItemStack itemStack) {
    return ItemUtil.getCurrentAMP(itemStack, 0);
  }

  @Override
  public int getMaximumAMP(ItemStack itemStack) {
    return ModConfig.ambrosiaMaxAMP;
  }

  @Override
  public void setCurrentAMP(ItemStack itemStack, int amp) {
    if (itemStack.getTagCompound() != null) {
      itemStack.getTagCompound().setInteger("AMP", amp);
    }
  }

  @Override
  public IAMPType getAMPType(ItemStack itemStack) {
    return AdvAmAPI.getAMPTypeFromID(Dictionary.AMP.FAIRY_ID);
  }

}
