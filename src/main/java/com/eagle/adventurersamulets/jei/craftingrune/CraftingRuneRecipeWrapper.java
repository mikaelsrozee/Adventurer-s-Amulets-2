package com.eagle.adventurersamulets.jei.craftingrune;

import com.eagle.adventurersamulets.api.recipe.RecipeCraftingRune;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CraftingRuneRecipeWrapper implements IRecipeWrapper {

  private final List<List<ItemStack>> input;
  private final ItemStack output;

  private RecipeCraftingRune recipeCraftingRune;

  public CraftingRuneRecipeWrapper(RecipeCraftingRune recipeCraftingRune) {
    ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();

    for (Object object : recipeCraftingRune.getInput()) {
      if (object instanceof Block) {
        if (object != Blocks.AIR) {
          builder.add(Collections.singletonList(new ItemStack((Block) object)));
        }
      } else if (object instanceof String) {
        builder.add(OreDictionary.getOres((String) object));
      }
    }

    input = builder.build();
    output = new ItemStack(recipeCraftingRune.getOutput().getBlock(),
        recipeCraftingRune.getOutputQuantity());
    this.recipeCraftingRune = recipeCraftingRune;
  }

  @Override
  public void getIngredients(@Nonnull IIngredients ingredients) {
    ingredients.setInputLists(VanillaTypes.ITEM, input);
    ingredients.setOutput(VanillaTypes.ITEM, output);
  }

  @Override
  public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX,
      int mouseY) {
    minecraft.fontRenderer.drawString(
        I18n.format("jei.gui.craftingrune.tierRequired") + " " + recipeCraftingRune
            .getTierRequired(), 56, 44, 0);
  }

  @Nonnull
  @Override
  public List<String> getTooltipStrings(int mouseX, int mouseY) {
    ArrayList<String> ret = new ArrayList<>();
    if (mouseX >= 119 && mouseX <= 131 && mouseY >= 12 && mouseY <= 41) {
      ret.add(I18n.format("jei.gui.craftingrune.north"));
      return ret;
    }
    return ImmutableList.of();
  }

  @Override
  public boolean handleClick(@Nonnull Minecraft minecraft, int mouseX, int mouseY,
      int mouseButton) {
    return false;
  }

}
