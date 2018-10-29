package com.eagle.adventurersamulets.api.recipe;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipePestle {

  private final Object input;
  private final ItemStack output;

  /**
   * Recipe for the Pestle.
   *
   * @param output The ItemStack created when a pestle is used on the input.
   * @param input  The ItemStack converted into the output when a pestle is used on it.
   */
  public RecipePestle(ItemStack output, Object input)
  {
    if (input instanceof ItemStack)
      this.input = ((ItemStack) input).copy();
    else if (input instanceof Item)
      this.input = new ItemStack((Item) input);
    else if (input instanceof Block)
      this.input = new ItemStack((Block) input);
    else if (input instanceof String)
      this.input = input;
    else throw new RuntimeException("Invalid Pestle recipe : " + output + ", " + input);

    this.output = output.copy();
  }

  public Object getInput()
  {
    return input;
  }

  public ItemStack getOutput()
  {
    return output;
  }

  /**
   * Checks to see if ItemStack is a valid input for the recipe.
   *
   * @param itemStack Input ItemStack.
   * @param recipe Recipe to check against.
   * @return If ItemStack is a valid input for the recipe.
   */
  public boolean matches(ItemStack itemStack, RecipePestle recipe)
  {
    if (recipe.getInput() instanceof ItemStack)
      return itemStack.copy().getItem() == ((ItemStack) recipe.getInput()).copy().getItem() && itemStack.copy().getItemDamage() == ((ItemStack) recipe.getInput()).copy().getItemDamage();
    else if (recipe.getInput() instanceof List)
    {
      for (Object object : (List) recipe.getInput())
      {
        if (object instanceof ItemStack)
        {
          for (int i : OreDictionary.getOreIDs((ItemStack) object))
          {
            for (int j : OreDictionary.getOreIDs(itemStack))
            {
              if (i == j)
                return true;
            }
          }
        }
      }
    }
    return false;
  }

}
