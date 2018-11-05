package com.eagle.adventurersamulets.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.AMP;
import com.eagle.adventurersamulets.Dictionary.Item;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.util.ItemUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAntiGravRing extends ItemMod implements IBauble, IAMPItem {

  public ItemAntiGravRing() {
    super(Item.ANTIGRAVITYRING);
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

    if (entity.isSneaking()) {
      if (getCurrentAMP(itemStack) >= ModConfig.antiGravRingCost) {
        if (entity.world.getTotalWorldTime() % 20 == 0)
          setCurrentAMP(itemStack, getCurrentAMP(itemStack) - ModConfig.antiGravRingCost);

        entity.fallDistance = 0;

        if (entity.motionY < 0.2F)
          entity.motionY = 0.2F;
      }
    }
  }

  @Override
  public BaubleType getBaubleType(ItemStack itemStack) {
    return BaubleType.RING;
  }

  @Override
  public int getCurrentAMP(ItemStack itemStack) {
    return ItemUtil.getCurrentAMP(itemStack, ModConfig.ampInitial);
  }

  @Override
  public int getMaximumAMP(ItemStack itemStack) {
    return ModConfig.antiGravRingMaxAMP;
  }

  @Override
  public void setCurrentAMP(ItemStack itemStack, int amp) {
    if (itemStack.getTagCompound() != null) {
      itemStack.getTagCompound().setInteger("AMP", amp);
    }
  }

  @Override
  public IAMPType getAMPType(ItemStack itemStack) {
    return AdvAmAPI.getAMPTypeFromID(AMP.TRANSPOSING_ID);
  }
}
