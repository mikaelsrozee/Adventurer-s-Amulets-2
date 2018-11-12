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
    AdvAmAPI.registerPestleRecipe("sugar", new ItemStack(Items.SUGAR, 2), Items.REEDS);
    AdvAmAPI.registerPestleRecipe("gunpowder", new ItemStack(Items.GUNPOWDER, 6), new ItemStack(Items.SKULL, 1, 4));
    AdvAmAPI.registerPestleRecipe("bone", new ItemStack(Items.BONE, 6), new ItemStack(Items.SKULL, 1, 0));
    AdvAmAPI.registerPestleRecipe("flesh", new ItemStack(Items.ROTTEN_FLESH, 6), new ItemStack(Items.SKULL, 1, 2));
    AdvAmAPI.registerPestleRecipe("bonemeal", new ItemStack(Items.DYE, 4, 15), Items.BONE);
    AdvAmAPI.registerPestleRecipe("pumpkinseeds", new ItemStack(Items.PUMPKIN_SEEDS, 6), Blocks.PUMPKIN);
    AdvAmAPI.registerPestleRecipe("melonseeds", new ItemStack(Items.MELON_SEEDS, 3), Items.MELON);
    AdvAmAPI.registerPestleRecipe("ash", new ItemStack(ModItems.ASH, 1), new ItemStack(Items.COAL, 1, 1));
    AdvAmAPI.registerPestleRecipe("featherdust", new ItemStack(ModItems.FEATHERDUST, 1), Items.FEATHER);
    AdvAmAPI.registerPestleRecipe("oceanicdust", new ItemStack(ModItems.OCEANICDUST, 1), Items.PRISMARINE_CRYSTALS);
  }

  private static void initCraftingRuneRecipes() {
    if (ModConfig.runicTransformation) {
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("8woodto4obsidian", Blocks.OBSIDIAN.getDefaultState(), 4, "logWood", "logWood", "logWood", "logWood", "logWood", "logWood", "logWood", "logWood");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("4woodto2obsidian", Blocks.OBSIDIAN.getDefaultState(), 2, "logWood", "logWood", "logWood", "logWood", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("2woodto1obsidian", Blocks.OBSIDIAN.getDefaultState(), 1, "logWood", "logWood", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("4dirtto1gravel", Blocks.GRAVEL.getDefaultState(), 1, "dirt", "dirt", "dirt", "dirt", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("8dirtto2gravel", Blocks.GRAVEL.getDefaultState(), 2, "dirt", "dirt", "dirt", "dirt", "dirt", "dirt", "dirt", "dirt");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("1gravelto4dirt", Blocks.DIRT.getDefaultState(), 4, "gravel", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipe("2gravelto8dirt", Blocks.DIRT.getDefaultState(),  "gravel", "gravel", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("1sandstoneto4sand", Blocks.SAND.getDefaultState(), 4, "sandstone", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipe("2sandstoneto8sand", Blocks.SAND.getDefaultState(), "sandstone", "sandstone", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("4planksto1log", Blocks.LOG.getDefaultState(), 1, "plankWood", "plankWood", "plankWood", "plankWood", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("8planksto2log", Blocks.LOG.getDefaultState(), 2, "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("8ironto1gold", Blocks.GOLD_BLOCK.getDefaultState(), 1, "blockIron", "blockIron", "blockIron", "blockIron", "blockIron", "blockIron", "blockIron", "blockIron");
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("4goldto1diamond", Blocks.DIAMOND_BLOCK.getDefaultState(), 1, "blockGold", "blockGold", "blockGold", "blockGold", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("8goldto2diamond", Blocks.DIAMOND_BLOCK.getDefaultState(), 2, "blockGold", "blockGold", "blockGold", "blockGold", "blockGold", "blockGold", "blockGold", "blockGold");
      AdvAmAPI.registerCraftingRuneRecipe("1goldto8iron", Blocks.IRON_BLOCK.getDefaultState(), "blockGold", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipeWithQuantity("1diamondto4gold", Blocks.GOLD_BLOCK.getDefaultState(), 4, "blockDiamond", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
      AdvAmAPI.registerCraftingRuneRecipe("2diamondto8hold", Blocks.GOLD_BLOCK.getDefaultState(), "blockDiamond", "blockDiamond", Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.AIR);
    }
    AdvAmAPI.registerCraftingRuneRecipeWithQuantity("emeraldcloth", ModBlocks.EMERALDCLOTH.getDefaultState(), 4, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, Blocks.WOOL, "blockEmerald");
  }

}
