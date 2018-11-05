package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Core.MOD_ID)
public class ModRecipes {

  public static void init() {
    initPestleRecipes();
    initCraftingRuneRecipes();
  }

  private static void initPestleRecipes() {
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.SUGAR, 2), Items.REEDS);
    AdvAmAPI
        .registerPestleRecipe(new ItemStack(Items.GUNPOWDER, 6), new ItemStack(Items.SKULL, 1, 4));
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.BONE, 6), new ItemStack(Items.SKULL, 1, 0));
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.ROTTEN_FLESH, 6),
        new ItemStack(Items.SKULL, 1, 2));
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.DYE, 4, 15), Items.BONE);
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.PUMPKIN_SEEDS, 6), Blocks.PUMPKIN);
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.MELON_SEEDS, 3), Items.MELON);
    AdvAmAPI.registerPestleRecipe(new ItemStack(ModItems.ASH, 1), new ItemStack(Items.COAL, 1, 1));
    AdvAmAPI.registerPestleRecipe(new ItemStack(ModItems.FEATHERDUST, 1), Items.FEATHER);
    AdvAmAPI
        .registerPestleRecipe(new ItemStack(ModItems.OCEANICDUST, 1), Items.PRISMARINE_CRYSTALS);
  }

  private static void initCraftingRuneRecipes() {
    if (ModConfig.runicTransformation) {
      AdvAmAPI
          .registerCraftingRuneRecipeWithQuantity(Blocks.OBSIDIAN.getDefaultState(), 4, "logWood",
              "logWood", "logWood", "logWood", "logWood", "logWood", "logWood", "logWood");
      AdvAmAPI
          .registerCraftingRuneRecipeWithQuantity(Blocks.OBSIDIAN.getDefaultState(), 2, "logWood",
              "logWood", "logWood", "logWood", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI
          .registerCraftingRuneRecipeWithQuantity(Blocks.OBSIDIAN.getDefaultState(), 1, "logWood",
              "logWood", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.GRAVEL.getDefaultState(), 1, "dirt",
          "dirt", "dirt", "dirt", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.GRAVEL.getDefaultState(), 2, "dirt",
          "dirt", "dirt", "dirt", "dirt", "dirt", "dirt", "dirt");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.DIRT.getDefaultState(), 4, "gravel",
          Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.DIRT.getDefaultState(), 8, "gravel",
          "gravel", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.SAND.getDefaultState(), 4, "sandstone",
          Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.SAND.getDefaultState(), 8, "sandstone",
          "sandstone", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.LOG.getDefaultState(), 1, "plankWood",
          "plankWood", "plankWood", "plankWood", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.LOG.getDefaultState(), 2, "plankWood",
          "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood",
          "plankWood");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.GOLD_BLOCK.getDefaultState(), 1,
          "blockIron", "blockIron", "blockIron", "blockIron", "blockIron", "blockIron", "blockIron",
          "blockIron");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.DIAMOND_BLOCK.getDefaultState(), 1,
          "blockGold", "blockGold", "blockGold", "blockGold", Blocks.AIR, Blocks.AIR, Blocks.AIR,
          Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.DIAMOND_BLOCK.getDefaultState(), 2,
          "blockGold", "blockGold", "blockGold", "blockGold", "blockGold", "blockGold", "blockGold",
          "blockGold");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.IRON_BLOCK.getDefaultState(), 8,
          "blockGold", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR,
          Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.GOLD_BLOCK.getDefaultState(), 4,
          "blockDiamond", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR,
          Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity(Blocks.GOLD_BLOCK.getDefaultState(), 8,
          "blockDiamond", "blockDiamond", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR,
          Blocks.AIR, Blocks.AIR);
    }
    AdvAmAPI.registerCraftingRuneRecipeWithQuantity(ModBlocks.EMERALDCLOTH.getDefaultState(), 4,
        Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL,
        "blockEmerald");
  }

}
