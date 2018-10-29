package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.api.AdvAmAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

@EventBusSubscriber(modid = Core.MOD_ID)
public class ModRecipes {

  public static void init() {
    initPestleRecipes();
  }

  @SubscribeEvent
  public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation(Dictionary.Core.MOD_ID), new ItemStack(ModItems.pestle), " XX", " YX", "Y  ", 'X', "stone", 'Y', "cobblestone").setRegistryName(ModItems.pestle.getRegistryName()));
  }

  private static void initPestleRecipes() {
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.SUGAR, 2), Items.REEDS);
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.GUNPOWDER, 6), new ItemStack(Items.SKULL, 1, 4));
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.BONE, 6), new ItemStack(Items.SKULL, 1, 0));
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.ROTTEN_FLESH, 6), new ItemStack(Items.SKULL, 1, 2));
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.DYE, 4, 15), Items.BONE);
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.PUMPKIN_SEEDS, 6), Blocks.PUMPKIN);
    AdvAmAPI.registerPestleRecipe(new ItemStack(Items.MELON_SEEDS, 3), Items.MELON);
  }

}
