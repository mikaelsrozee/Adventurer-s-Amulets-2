package com.eagle.adventurersamulets.jei.pestle;

import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.Dictionary.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PestleRecipeCategory implements IRecipeCategory {

  public static final String UID = Core.MOD_ID + "." + Item.PESTLE;
  private final IDrawable background;
  private final String localizedName;
  private final IDrawable overlay;

  public PestleRecipeCategory(IGuiHelper guiHelper) {
    background = guiHelper.createBlankDrawable(96, 28);
    localizedName = I18n.format(Core.MOD_ID + ".jei." + Item.PESTLE);
    overlay = guiHelper.createDrawable(new ResourceLocation(Core.MOD_ID, "textures/gui/jei/" + Item.PESTLE + ".png"), 0, 0, 96, 28);
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

  @Nonnull
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
    GlStateManager.enableAlpha();
    GlStateManager.enableBlend();
    overlay.draw(minecraft, 0, 0);
    GlStateManager.disableBlend();
    GlStateManager.disableAlpha();
  }

  @Override
  public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
    if (!(recipeWrapper instanceof PestleRecipeWrapper))
      return;

    IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

    guiItemStacks.init(0, true, 0, 5);
    guiItemStacks.init(1, false, 73, 5);

    List<ItemStack> input = ingredients.getInputs(VanillaTypes.ITEM).get(0);
    List<ItemStack> output = ingredients.getOutputs(VanillaTypes.ITEM).get(0);


    guiItemStacks.set(0, input);
    guiItemStacks.set(1, output);
  }

  @Nonnull
  @Override
  public List<String> getTooltipStrings(int mouseX, int mouseY) {
    return new ArrayList();
  }

}
