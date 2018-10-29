package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.api.AdventurersAmuletsAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {

  public static void init() {
    initPestleRecipes();
  }

  private static void initPestleRecipes() {
    AdventurersAmuletsAPI.registerPestleRecipe(new ItemStack(Items.SUGAR, 2), Items.REEDS);
    AdventurersAmuletsAPI.registerPestleRecipe(new ItemStack(Items.GUNPOWDER, 6), new ItemStack(Items.SKULL, 1, 4));
    AdventurersAmuletsAPI.registerPestleRecipe(new ItemStack(Items.BONE, 6), new ItemStack(Items.SKULL, 1, 0));
    AdventurersAmuletsAPI.registerPestleRecipe(new ItemStack(Items.ROTTEN_FLESH, 6), new ItemStack(Items.SKULL, 1, 2));
    AdventurersAmuletsAPI.registerPestleRecipe(new ItemStack(Items.DYE, 4, 15), Items.BONE);
    AdventurersAmuletsAPI.registerPestleRecipe(new ItemStack(Items.PUMPKIN_SEEDS, 6), Blocks.PUMPKIN);
    AdventurersAmuletsAPI.registerPestleRecipe(new ItemStack(Items.MELON_SEEDS, 3), Items.MELON);
  }

}
