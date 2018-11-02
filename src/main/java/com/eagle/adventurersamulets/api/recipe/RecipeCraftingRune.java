package com.eagle.adventurersamulets.api.recipe;

import com.eagle.adventurersamulets.api.block.ICraftingRune;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeCraftingRune {

  private final Object[] input;
  private final IBlockState output;
  private final int outputQuantity, tierRequired;

  public RecipeCraftingRune(IBlockState output, Object... input) {
    this(output, 8, input);
  }

  public RecipeCraftingRune(IBlockState output, int outputQuantity, Object... input) {
    this(output, outputQuantity, 0, input);
  }

  public RecipeCraftingRune(IBlockState output, int outputQuantity, int tierRequired, Object... input) {
    this.input = input;
    this.output = output;
    this.outputQuantity = outputQuantity;
    this.tierRequired = tierRequired;
    checkArguments(output, outputQuantity, tierRequired, input);
  }

  private void checkArguments(IBlockState output, int outputQuantity, int tierRequired, Object... input) {
    if ((input.length != 8)) {
      throw new IllegalArgumentException("Input must be 8 components in length");
    }

    int i = 0;
    while (i < input.length) {
      if ((!(input[i] instanceof String || input[i] instanceof Block))) {
        throw new IllegalArgumentException("Inputs must be an oredict key or a block");
      }

      i++;
    }

    if (outputQuantity < 0 || outputQuantity > 8) {
      throw new IllegalArgumentException("Output quantity must be non-negative and less than 8.");
    }
    if (tierRequired < 0) {
      throw new IllegalArgumentException("Tier required must be non-negative.");
    }
  }

  public boolean matches(World world, BlockPos pos) {
    if (world.getBlockState(pos).getBlock() instanceof ICraftingRune) {
      if (((ICraftingRune) world.getBlockState(pos).getBlock()).getTier() >= tierRequired) {
        BlockPos[] allPos = new BlockPos[]{
            pos.add(-1, 0, -1), pos.add(0, 0, -1), pos.add(1, 0, -1),
            pos.add(-1, 0, 0), pos.add(1, 0, 0), pos.add(-1, 0, 1),
            pos.add(0, 0, 1), pos.add(1, 0, 1)};
        IBlockState[] allStates = new IBlockState[]{
            world.getBlockState(allPos[0]), world.getBlockState(allPos[1]),
            world.getBlockState(allPos[2]), world.getBlockState(allPos[3]),
            world.getBlockState(allPos[4]), world.getBlockState(allPos[5]),
            world.getBlockState(allPos[6]), world.getBlockState(allPos[7])};

        int i = 0;
        while (i < allStates.length) {
          if (input[i] instanceof Block) {
            return allStates[i].getBlock() == input[i];
          }

          if (allStates[i].getBlock() == Blocks.AIR) {
            return false;
          }

          ItemStack itemStack = new ItemStack(allStates[i].getBlock(), 1,
              allStates[i].getBlock().damageDropped(allStates[i]));
          String key = (String) input[i];

          if (!isOreDict(itemStack, key)) {
            return false;
          }
          i++;
        }

        return true;
      }
    }

    return false;
  }

  private boolean isOreDict(ItemStack itemStack, String key) {
    if (itemStack == null) {
      return false;
    }

    List<ItemStack> ores = OreDictionary.getOres(key);
    for (ItemStack stack : ores) {
      ItemStack stack2 = stack.copy();
      if (stack.getItemDamage() == Short.MAX_VALUE) {
        stack2.setItemDamage(itemStack.getItemDamage());
      }

      if (itemStack.isItemEqual(stack2)) {
        return true;
      }

    }

    return false;
  }

  public boolean set(World world, BlockPos pos) {
    if (output != null) {
      BlockPos[] allPos = new BlockPos[]{
          pos.add(-1, 0, -1), pos.add(0, 0, -1), pos.add(1, 0, -1),
          pos.add(-1, 0, 0), pos.add(1, 0, 0), pos.add(-1, 0, 1),
          pos.add(0, 0, 1), pos.add(1, 0, 1)};

      int i = 0;
      while (i < 8) {
        world.destroyBlock(allPos[i], false);

        if (i < outputQuantity) {
          world.setBlockState(allPos[i], output);
        }

        i++;
      }

      return true;
    }

    return false;
  }

  public Object[] getInput() {
    return input;
  }

  public IBlockState getOutput() {
    return output;
  }

  public int getOutputQuantity() {
    return outputQuantity;
  }

  public int getTierRequired() {
    return tierRequired;
  }

}
