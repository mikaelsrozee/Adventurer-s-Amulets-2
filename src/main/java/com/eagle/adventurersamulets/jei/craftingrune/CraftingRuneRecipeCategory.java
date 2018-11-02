package com.eagle.adventurersamulets.jei.craftingrune;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Block;
import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.Dictionary.Item;
import com.eagle.adventurersamulets.jei.AdvAmJEIPlugin;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class CraftingRuneRecipeCategory implements IRecipeCategory {

  public static final String UID = "adventurersamulets.craftingrune";
  private final IDrawable background;
  private final String localizedName;
  private final IDrawable overlay;

  public CraftingRuneRecipeCategory(IGuiHelper guiHelper) {
    background = guiHelper.createBlankDrawable(131, 54);
    localizedName = I18n.format(Core.MOD_ID + ".jei." + Block.CRAFTING_RUNE);
    overlay = guiHelper.createDrawable(new ResourceLocation(Core.MOD_ID, "textures/gui/jei/" + Block.CRAFTING_RUNE + ".png"), 0, 0, 131, 54);
  }

  @Nonnull
  @Override
  public String getUid() {
    return UID;
  }

  @Nonnull
  @Override
  public String getTitle() {
    return localizedName;
  }

  @Override
  public String getModName() {
    return Core.MOD_NAME;
  }

  @Nonnull
  @Override
  public IDrawable getBackground() {
    return background;
  }

  @Nullable
  @Override
  public IDrawable getIcon() {
    return null;
  }

  @Override
  public void drawExtras(@Nonnull Minecraft minecraft) {
    AdvAmJEIPlugin.drawOverlay(overlay, minecraft);
  }

  @Override
  public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
    IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

    guiItemStacks.init(0, false, 94, 18);
    guiItemStacks.init(1, true, 0, 0);
    guiItemStacks.init(2, true, 18, 0);
    guiItemStacks.init(3, true, 36, 0);
    guiItemStacks.init(4, true, 0, 18);
    guiItemStacks.init(5, true, 36, 18);
    guiItemStacks.init(6, true, 0, 36);
    guiItemStacks.init(7, true, 18, 36);
    guiItemStacks.init(8, true, 36, 36);

    guiItemStacks.set(ingredients);
  }

  @Nonnull
  @Override
  public List<String> getTooltipStrings(int mouseX, int mouseY) {
    return new ArrayList();
  }

}
