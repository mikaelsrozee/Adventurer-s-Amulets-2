package com.eagle.adventurersamulets.items;

import com.eagle.adventurersamulets.AdventurersAmulets;
import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Item;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.amp.IAMPItem;
import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.init.ModItems;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class ItemWindmakersBlade extends ItemSword implements IAMPItem {

  private static final UUID SPEED_MODIFIER_ID = UUID.fromString("1735a950-ce32-4300-9dcb-8773516aa94d");

  protected String name;

  public ItemWindmakersBlade() {
    super(ModItems.ampToolMaterial);
    this.name = Item.WINDMAKERSBLADE;
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

    if (world.getTotalWorldTime() % 1000 == 0 && getCurrentAMP(itemStack) > 0 && !world.isRemote)
      setCurrentAMP(itemStack, getCurrentAMP(itemStack) - 1);
  }

  @Nonnull
  public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
    if (getCurrentAMP(player.getHeldItem(hand)) >= ModConfig.windmakersBladeDashCost && player.getCooldownTracker().getCooldown(player.getHeldItem(hand).getItem(), 0) == 0) {
      player.motionX = player.getLookVec().x;
      player.motionZ = player.getLookVec().z;

      player.motionY = player.getLookVec().y * 1.5D;
      player.fallDistance = -3.0F;

      player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), ModConfig.windmakersBladeDashCooldown);

      setCurrentAMP(player.getHeldItem(hand), getCurrentAMP(player.getHeldItem(hand)) - ModConfig.windmakersBladeDashCost);
      player.swingArm(hand);
      return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
    return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
  }

  public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
    itemStack.damageItem(1, attacker);
    return true;
  }

  @Nonnull
  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot equipmentSlot, ItemStack itemStack) {
    Multimap<String, AttributeModifier> multimap = HashMultimap.create();
    if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
      multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 8, 0));
      multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.8, 0));
      if (getCurrentAMP(itemStack) != 0)
        multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(SPEED_MODIFIER_ID, "Weapon modifier", 0.50, 1));
    }
    return multimap;
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
    return ModConfig.windmakersBladeMaxAMP;
  }

  @Override
  public void setCurrentAMP(ItemStack itemStack, int amp) {
    if (itemStack.getTagCompound() != null)
      itemStack.getTagCompound().setInteger("AMP", amp);
  }

  @Override
  public IAMPType getAMPType(ItemStack itemStack) {
    return AdvAmAPI.getAMPTypeFromID(Dictionary.AMP.WINDMAKERS_ID);
  }
  
}
