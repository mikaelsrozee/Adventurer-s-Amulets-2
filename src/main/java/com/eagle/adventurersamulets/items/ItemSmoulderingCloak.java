package com.eagle.adventurersamulets.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Item;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.util.ItemUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSmoulderingCloak extends ItemMod implements IBauble, IAMPItem {

  public ItemSmoulderingCloak() {
    super(Item.SMOULDERINGCLOAK);
  }

  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip,
      ITooltipFlag flag) {
    ItemUtil.addToolTipInformation(itemStack, this, tooltip);
  }

  @Override
  public void onWornTick(ItemStack itemStack, EntityLivingBase entity) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) entity;

      if (getAMPType(itemStack).isChargeConditionCorrect(player)
          && getCurrentAMP(itemStack) < getMaximumAMP(itemStack)) {
        setCurrentAMP(itemStack, getCurrentAMP(itemStack) + 1);
      }
    }

    if (entity.isBurning() && getCurrentAMP(itemStack) >= ModConfig.smoulderingCloakCost) {

      boolean hasFireResist = false;
      for (PotionEffect effect : entity.getActivePotionEffects()) {
        if (effect.getPotion().equals(MobEffects.FIRE_RESISTANCE)) {
          hasFireResist = true;
        }
      }

      if (!hasFireResist) {
        entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 200, 0, true, false));
        setCurrentAMP(itemStack, getCurrentAMP(itemStack) - ModConfig.smoulderingCloakCost);
      }
    }
  }

  @SubscribeEvent
  public void onAttackedByEnemy(LivingDamageEvent event) {
    if (event.getEntity() instanceof EntityPlayer) {
      EntityPlayer victim = (EntityPlayer) event.getEntityLiving();
      ItemStack itemStack = BaublesApi.getBaublesHandler(victim)
          .getStackInSlot(BaubleType.BODY.ordinal());

      if (itemStack.getItem() == this) {
        if (getCurrentAMP(itemStack) >= ModConfig.smoulderingCloakCost) {
          Entity attacker = event.getSource().getTrueSource();

          if (attacker != null) {
            attacker.setFire(3);
            attacker.attackEntityFrom(DamageSource.IN_FIRE, 2.5F);
            setCurrentAMP(itemStack, getCurrentAMP(itemStack) - ModConfig.smoulderingCloakCost);
          }
        }
      }
    }
  }

  @Override
  public BaubleType getBaubleType(ItemStack itemStack) {
    return BaubleType.BODY;
  }

  @Override
  public int getCurrentAMP(ItemStack itemStack) {
    return ItemUtil.getCurrentAMP(itemStack, ModConfig.ampInitial);
  }

  @Override
  public int getMaximumAMP(ItemStack itemStack) {
    return ModConfig.smoulderingCloakMaxAMP;
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
