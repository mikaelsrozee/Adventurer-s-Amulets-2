package com.eagle.adventurersamulets.items;

import com.eagle.adventurersamulets.Dictionary.Item;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.recipe.RecipePestle;
import com.eagle.adventurersamulets.init.ModConfig;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPestle extends ItemMod {

  public ItemPestle() {
    super(Item.PESTLE);
    setMaxStackSize(1);
    setMaxDamage(ModConfig.pestleDurability);
    setNoRepair();
    setFull3D();
  }

  @Nonnull
  @Override
  public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand,
      EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() && hitY == 1.0) {
      List entityItems = world
          .getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 2, 1)));
      for (Object obj : entityItems) {
        if (obj instanceof EntityItem) {
          EntityItem entityItem = (EntityItem) obj;
          for (RecipePestle recipe : AdvAmAPI.pestleRecipes) {
            if (recipe.matches(entityItem.getItem(), recipe)
                && entityItem.getItem().getCount() == 1) {
              world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entityItem.posX, entityItem.posY,
                  entityItem.posZ, 0.0D, 0.0D, 0.0D);
              if (!world.isRemote) {
                entityItem.setItem(recipe.getOutput().copy());
                player.getHeldItem(hand).damageItem(1, player);
              }
              return EnumActionResult.SUCCESS;
            }
          }
        }
      }
    }
    return EnumActionResult.FAIL;
  }
}
