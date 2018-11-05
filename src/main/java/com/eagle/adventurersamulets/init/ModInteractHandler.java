package com.eagle.adventurersamulets.init;

import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModInteractHandler {

  @SubscribeEvent
  public void handleRightClick(PlayerInteractEvent event) {
    if (event.getEntityPlayer() == null || event.getWorld() == null) {
      return;
    }
    if (event.getItemStack().getItem() == Items.GLOWSTONE_DUST
        && event.getWorld().getBlockState(event.getPos()) == ModBlocks.SLATE.getDefaultState()) {
      if (!event.getWorld().isRemote) {
        event.getItemStack().setCount(event.getItemStack().getCount() - 1);
        event.getWorld().setBlockState(event.getPos(), ModBlocks.CRAFTINGRUNE.getDefaultState());
      }
      event.getWorld()
          .playSound(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(),
              SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1.0F, 1.0F, true);
      event.getWorld()
          .spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, event.getPos().getX() + 0.5F,
              event.getPos().getY() + 0.5F, event.getPos().getZ() + 0.5F, 1, 1, 1);
    }
  }

}
