package com.eagle.adventurersamulets.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import com.eagle.adventurersamulets.Dictionary.AMP;
import com.eagle.adventurersamulets.Dictionary.Item;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.util.ItemUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
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

public class ItemSnowflakeVeil extends ItemMod implements IBauble, IAMPItem {

  public ItemSnowflakeVeil() {
    super(Item.SNOWFLAKEVEIL);
    setMaxStackSize(1);
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
  }

  @SubscribeEvent
  public void onAttackedByEnemy(LivingDamageEvent event) {
    if (event.getEntity() instanceof EntityPlayer) {
      EntityPlayer victim = (EntityPlayer) event.getEntityLiving();
      ItemStack itemStack = BaublesApi.getBaublesHandler(victim)
          .getStackInSlot(BaubleType.BODY.ordinal());

      if (itemStack.getItem() == this) {
        if (getCurrentAMP(itemStack) >= ModConfig.snowflakeVeilCost) {
          if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();

            if (attacker != null) {
              attacker.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 255, true, false));
              attacker.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 0, true, false));
              attacker.attackEntityFrom(DamageSource.MAGIC, 2.5F);
              setCurrentAMP(itemStack, getCurrentAMP(itemStack) - ModConfig.snowflakeVeilCost);
            }
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
    return ModConfig.snowflakeVeilMaxAMP;
  }

  @Override
  public void setCurrentAMP(ItemStack itemStack, int amp) {
    if (itemStack.getTagCompound() != null) {
      itemStack.getTagCompound().setInteger("AMP", amp);
    }
  }

  @Override
  public IAMPType getAMPType(ItemStack itemStack) {
    return AdvAmAPI.getAMPTypeFromID(AMP.FROSTBITTEN_ID);
  }
}
