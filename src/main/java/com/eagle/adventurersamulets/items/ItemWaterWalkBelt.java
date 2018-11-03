package com.eagle.adventurersamulets.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.AMP;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class ItemWaterWalkBelt extends ItemMod implements IBauble, IAMPItem {

  public ItemWaterWalkBelt() {
    super(Dictionary.Item.WATERWALKBELT);
  }

  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
    if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
      tooltip.add(I18n.format("amptype.currentAMP") + ": " + getCurrentAMP(itemStack));
    else
      tooltip.add(I18n.format("amptype.currentAMP") + ": " + getCurrentAMP(itemStack) + " (" + getAMPType(itemStack).getLocalizedName() + ")");
  }

  @Override
  public void onWornTick(ItemStack itemStack, EntityLivingBase entity) {
    BlockPos posBelow = entity.getPosition().add(0, -1, 0);
    IBlockState blockStateBelow = entity.world.getBlockState(posBelow);

    if (entity.isSneaking() || getCurrentAMP(itemStack) == 0)
      return;

    if (blockStateBelow == Blocks.WATER.getDefaultState()) {
      if (entity.world.getTotalWorldTime() % 20 == 0)
        setCurrentAMP(itemStack, getCurrentAMP(itemStack) - 1);

      if (entity.posY < posBelow.getY() + 1) {
        entity.motionY = 0;
        entity.addVelocity(0, 0.01, 0);
      }
      entity.onGround = true;
      entity.fallDistance = 0;
    }
  }

  @Override
  public BaubleType getBaubleType(ItemStack itemStack) {
    return BaubleType.BELT;
  }

  @Override
  public int getCurrentAMP(ItemStack itemStack) {
    if (itemStack.getTagCompound() == null)
      itemStack.setTagCompound(new NBTTagCompound());

    if (!itemStack.getTagCompound().hasKey("AMP")) {
      itemStack.getTagCompound().setInteger("AMP", 0);
      return ModConfig.ampInitial;
    }

    return itemStack.getTagCompound().getInteger("AMP");
  }

  @Override
  public int getMaximumAMP(ItemStack itemStack) {
    return ModConfig.waterWalkerMaxAMP;
  }

  @Override
  public void setCurrentAMP(ItemStack itemStack, int amp) {
    if (itemStack.getTagCompound() != null)
      itemStack.getTagCompound().setInteger("AMP", amp);
  }

  @Override
  public IAMPType getAMPType(ItemStack itemStack) {
    return AdvAmAPI.getAMPTypeFromID(AMP.AQUATIC_ID);
  }
}
