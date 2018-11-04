package com.eagle.adventurersamulets.items;

import com.eagle.adventurersamulets.AdventurersAmulets;
import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Item;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.init.ModItems;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class ItemSmeltingPickaxe extends ItemPickaxe implements IAMPItem {

  protected String name;

  public ItemSmeltingPickaxe() {
    super(ModItems.ampToolMaterial);
    this.name = Item.SMELTINGPICKAXE;
    setUnlocalizedName(name);
    setRegistryName(name);
    setCreativeTab(AdventurersAmulets.creativeTab);
    setMaxStackSize(1);
  }

  @SideOnly(Side.CLIENT)
  public void initModel() {
    AdventurersAmulets.proxy.initModel(this, 0, name);
  }

  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
    if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
      tooltip.add(I18n.format("amptype.currentAMP") + ": " + getCurrentAMP(itemStack));
    else
      tooltip.add(I18n.format("amptype.currentAMP") + ": " + getCurrentAMP(itemStack) + " (" + getAMPType(itemStack).getLocalizedName() + ")");
  }

  public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) entity;
      if (getAMPType(itemStack).isChargeConditionCorrect(player) && getCurrentAMP(itemStack) < getMaximumAMP(itemStack))
        setCurrentAMP(itemStack, getCurrentAMP(itemStack) + 1);
    }

    if (itemStack.getItemDamage() > 0 && getCurrentAMP(itemStack) > 0) {
      itemStack.setItemDamage(itemStack.getItemDamage() - 1);
      setCurrentAMP(itemStack, getCurrentAMP(itemStack) - 1);
    }
  }

  @SubscribeEvent
  public void blockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
    if (event.getHarvester() != null) {
      if (event.getState().getBlock().isToolEffective("pickaxe", event.getState()) && event.getHarvester().getHeldItemMainhand().getItem() == this) {
        ListIterator<ItemStack> iterator = event.getDrops().listIterator();
        while (iterator.hasNext()) {
          ItemStack normal = iterator.next();
          ItemStack smelted = FurnaceRecipes.instance().getSmeltingResult(normal);
          if (smelted != ItemStack.EMPTY) {
            smelted = smelted.copy();
            smelted.setCount(normal.getCount() * getFortuneMultiplier(event.getFortuneLevel()));
            iterator.set(smelted);

            event.getState().getBlock().dropXpOnBlockBreak(event.getWorld(), event.getPos(), itemRand.nextInt(event.getFortuneLevel() + 1) - itemRand.nextInt(event.getFortuneLevel() + 1));
          }
        }
      }
    }
  }

  private int getFortuneMultiplier(int fortuneLevel) {
    if (!ModConfig.fortuneSmelting)
      return 1;
    else return itemRand.nextInt(fortuneLevel + 1) + 1;
  }

  @Override
  public int getCurrentAMP(ItemStack itemStack) {
    if (itemStack.getTagCompound() == null)
      itemStack.setTagCompound(new NBTTagCompound());

    if (!itemStack.getTagCompound().hasKey("AMP")) {
      itemStack.getTagCompound().setInteger("AMP", ModConfig.ampInitial);
      return ModConfig.ampInitial;
    }

    return itemStack.getTagCompound().getInteger("AMP");
  }

  @Override
  public int getMaximumAMP(ItemStack itemStack) {
    return ModConfig.scorchingCoreMaxAMP;
  }

  @Override
  public void setCurrentAMP(ItemStack itemStack, int amp) {
    if (itemStack.getTagCompound() != null)
      itemStack.getTagCompound().setInteger("AMP", amp);
  }

  @Override
  public IAMPType getAMPType(ItemStack itemStack) {
    return AdvAmAPI.getAMPTypeFromID(Dictionary.AMP.INFERNAL_ID);
  }
  
}
