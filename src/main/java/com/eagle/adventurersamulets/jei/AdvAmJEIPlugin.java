package com.eagle.adventurersamulets.jei;

import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.recipe.RecipeCraftingRune;
import com.eagle.adventurersamulets.api.recipe.RecipePestle;
import com.eagle.adventurersamulets.init.ModBlocks;
import com.eagle.adventurersamulets.init.ModItems;
import com.eagle.adventurersamulets.jei.craftingrune.CraftingRuneRecipeCategory;
import com.eagle.adventurersamulets.jei.craftingrune.CraftingRuneRecipeWrapper;
import com.eagle.adventurersamulets.jei.pestle.PestleRecipeCategory;
import com.eagle.adventurersamulets.jei.pestle.PestleRecipeWrapper;
import javax.annotation.Nonnull;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class AdvAmJEIPlugin implements IModPlugin {

  @Override
  public void registerCategories(IRecipeCategoryRegistration registry) {
    registry.addRecipeCategories(new PestleRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
        new CraftingRuneRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
  }

  @Override
  public void register(@Nonnull IModRegistry registry) {
    registry.handleRecipes(RecipePestle.class, PestleRecipeWrapper::new, PestleRecipeCategory.UID);
    registry.handleRecipes(RecipeCraftingRune.class, CraftingRuneRecipeWrapper::new, CraftingRuneRecipeCategory.UID);

    registry.addRecipes(AdvAmAPI.pestleRecipes, PestleRecipeCategory.UID);
    registry.addRecipes(AdvAmAPI.craftingRuneRecipes, CraftingRuneRecipeCategory.UID);

    registry.addRecipeCatalyst(new ItemStack(ModItems.PESTLE), PestleRecipeCategory.UID);
    registry.addRecipeCatalyst(new ItemStack(ModBlocks.CRAFTINGRUNE), CraftingRuneRecipeCategory.UID);
  }

  public static void drawOverlay(IDrawable overlay, Minecraft minecraft) {
    GlStateManager.enableAlpha();
    GlStateManager.enableBlend();
    overlay.draw(minecraft, 0, 0);
    GlStateManager.disableBlend();
    GlStateManager.disableAlpha();
  }

}
