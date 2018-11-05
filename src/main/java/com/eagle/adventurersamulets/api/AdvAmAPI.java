package com.eagle.adventurersamulets.api;

import com.eagle.adventurersamulets.api.amp.IAMPType;
import com.eagle.adventurersamulets.api.recipe.RecipeCraftingRune;
import com.eagle.adventurersamulets.api.recipe.RecipePestle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public final class AdvAmAPI {

  public static final List<RecipePestle> pestleRecipes = new ArrayList<>();
  public static final List<RecipeCraftingRune> craftingRuneRecipes = new ArrayList<>();

  private static HashMap<String, IAMPType> ampTypes = new HashMap<>();

  /**
   * Registers a Pestle recipe.
   *
   * @param output The outputted ItemStack
   * @param input An oredict key, ItemStack, Item or Block instance used in the creation of the
   * recipe
   * @return The recipe created
   */
  public static RecipePestle registerPestleRecipe(ItemStack output, Object input) {
    RecipePestle recipe = new RecipePestle(output, input);
    pestleRecipes.add(recipe);
    return recipe;
  }

  /**
   * Registers a simple Crafting Rune recipe. This assumes the output quantity is 8, and the tier
   * required is 0.
   *
   * @param input An oredict key, or Block instance used in the creation of the recipe
   * @param output The block state created in the process
   * @return The recipe created
   */
  public static RecipeCraftingRune registerCraftingRuneRecipe(IBlockState output, Object... input) {
    RecipeCraftingRune recipe = new RecipeCraftingRune(output, input);
    craftingRuneRecipes.add(recipe);
    return recipe;
  }

  /**
   * Registers a Crafting Rune recipe. This assumes the tier required is 0.
   *
   * @param input An oredict key, or Block instance used in the creation of the recipe
   * @param output The block state created in the process
   * @param outputQuantity The quantity of output created
   * @return The recipe created
   */
  public static RecipeCraftingRune registerCraftingRuneRecipeWithQuantity(IBlockState output,
      int outputQuantity, Object... input) {
    RecipeCraftingRune recipe = new RecipeCraftingRune(output, outputQuantity, input);
    craftingRuneRecipes.add(recipe);
    return recipe;
  }

  /**
   * Registers a Crafting Rune recipe.
   *
   * @param input An oredict key, or Block instance used in the creation of the recipe
   * @param output The block state created in the process
   * @param outputQuantity The quantity of output created
   * @param tierRequired The tier of rune required to complete the process
   * @return The recipe created
   */
  public static RecipeCraftingRune registerFullCraftingRuneRecipe(IBlockState output,
      int outputQuantity, int tierRequired, Object... input) {
    RecipeCraftingRune recipe = new RecipeCraftingRune(output, outputQuantity, tierRequired, input);
    craftingRuneRecipes.add(recipe);
    return recipe;
  }

  /**
   * Registers a new AMP type.
   *
   * @param ampType The AMP type to register.
   */
  public static void registerAMPType(IAMPType ampType) {
    ampTypes.put(ampType.getID(), ampType);
  }

  /**
   * Returns a registered AMP type for the given ID.
   *
   * @param ID ID to search for.
   * @return AMP type that matches the ID given.
   */
  public static IAMPType getAMPTypeFromID(String ID) {
    return ampTypes.get(ID);
  }
}
