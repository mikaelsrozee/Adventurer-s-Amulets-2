package com.eagle.adventurersamulets.api;

import com.eagle.adventurersamulets.api.recipe.RecipePestle;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;

public final class AdvAmAPI {

  public static final List<RecipePestle> pestleRecipes = new ArrayList<>();

  /**
   * Registers a Pestle recipe.
   *
   * @param output The outputted ItemStack
   * @param input  An oredict key, ItemStack, Item or Block instance used in the creation of the recipe
   * @return The recipe created
   */
  public static RecipePestle registerPestleRecipe(ItemStack output, Object input)
  {
    RecipePestle recipe = new RecipePestle(output, input);
    pestleRecipes.add(recipe);
    return recipe;
  }
}
