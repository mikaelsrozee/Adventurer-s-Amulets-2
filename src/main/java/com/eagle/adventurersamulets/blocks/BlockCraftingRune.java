package com.eagle.adventurersamulets.blocks;

import com.eagle.adventurersamulets.Dictionary.Block;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.block.ICraftingRune;
import com.eagle.adventurersamulets.api.recipe.RecipeCraftingRune;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCraftingRune extends BlockMod implements ICraftingRune {

  public BlockCraftingRune() {
    super(Block.CRAFTING_RUNE);
  }

  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
    for (RecipeCraftingRune recipe : AdvAmAPI.craftingRuneRecipes) {
      if (recipe.matches(world, pos)) {
        recipe.set(world, pos);
        player.swingArm(hand);
        world.spawnParticle(EnumParticleTypes.SPELL, pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D, 0, 1, 0);
        world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1.0F, 1.0F, true);
        return true;
      }
    }

    return false;
  }

  @Override
  public int getTier() {
    return 0;
  }
}
