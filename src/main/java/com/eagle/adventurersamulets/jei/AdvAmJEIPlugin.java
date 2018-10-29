package com.eagle.adventurersamulets.jei;

import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.recipe.RecipePestle;
import com.eagle.adventurersamulets.init.ModItems;
import com.eagle.adventurersamulets.jei.pestle.PestleRecipeCategory;
import com.eagle.adventurersamulets.jei.pestle.PestleRecipeWrapper;
import javax.annotation.Nonnull;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class AdvAmJEIPlugin implements IModPlugin {

  @Override
  public void registerCategories(IRecipeCategoryRegistration registry) {
    registry.addRecipeCategories(new PestleRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
  }

  @Override
  public void register(@Nonnull IModRegistry registry) {
    registry.handleRecipes(RecipePestle.class, PestleRecipeWrapper::new, PestleRecipeCategory.UID);

    registry.addRecipes(AdvAmAPI.pestleRecipes, PestleRecipeCategory.UID);

    registry.addRecipeCatalyst(new ItemStack(ModItems.pestle), PestleRecipeCategory.UID);
  }

}
