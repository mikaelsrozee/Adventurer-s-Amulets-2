package com.eagle.adventurersamulets.init;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public final class ModConfig {

  public static Configuration config;

  public static int pestleDurability = 31;
  public static int windmakersHeight = 127;
  public static int earthboundHeight = 30;

  public static boolean runicTransformation = true;

  public static void loadConfig(File file) {
    config = new Configuration(file);

    config.load();
    load();
  }

  public static void load() {
    String category;

    category = "AMP";
    config.addCustomCategoryComment(category, "Allows for the tweaking of the AMP system.");
    windmakersHeight = config.getInt("windmakersHeight", category, windmakersHeight, 0, 255, "The height above which Windmaker's items will charge.");
    earthboundHeight = config.getInt("earthboundHeight", category, earthboundHeight, 0, 255, "The height below which Earthbound items will charge.");

    category = "Blocks";
    config.addCustomCategoryComment(category, "Allows for the tweaking of non-AMP-related blocks.");
    runicTransformation = config.getBoolean("runicTransformation", category, runicTransformation, "Set to false to disable the transmutation (Wood > Obsidian, Diamond > Gold > Iron) recipes.");

    category = "Items";
    config.addCustomCategoryComment(category, "Allows for the tweaking of non-AMP-related items.");
    pestleDurability = config.getInt("pestleDurability", category, pestleDurability, 0, Integer.MAX_VALUE, "The durability of the Pestle.");

    if (config.hasChanged())
      config.save();
  }

}
