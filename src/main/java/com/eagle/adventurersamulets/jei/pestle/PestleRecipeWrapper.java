package com.eagle.adventurersamulets.jei.pestle;

import com.eagle.adventurersamulets.api.recipe.RecipePestle;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class PestleRecipeWrapper implements IRecipeWrapper {

  private final List<List<ItemStack>> input;
  private final ItemStack output;

  public PestleRecipeWrapper(RecipePestle recipePestle)
  {
    ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();

    if (recipePestle.getInput() instanceof ItemStack)
      builder.add(ImmutableList.of((ItemStack) recipePestle.getInput()));
    else if (recipePestle.getInput() instanceof String)
      builder.add(OreDictionary.getOres((String) recipePestle.getInput()));

    input = builder.build();
    output = recipePestle.getOutput();
  }

  @Override
  public void getIngredients(@Nonnull IIngredients ingredients)
  {
    ingredients.setInputLists(ItemStack.class, input);
    ingredients.setOutput(ItemStack.class, output);
  }

  @Override
  public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
  {

  }

  @Nonnull
  @Override
  public List<String> getTooltipStrings(int mouseX, int mouseY)
  {
    return ImmutableList.of();
  }

  @Override
  public boolean handleClick(@Nonnull Minecraft minecraft, int mouseX, int mouseY, int mouseButton)
  {
    return false;
  }

}
