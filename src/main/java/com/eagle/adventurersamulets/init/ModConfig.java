package com.eagle.adventurersamulets.init;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public final class ModConfig {

  public static Configuration config;

  public static int pestleDurability = 31;

  public static void loadConfig(File file) {
    config = new Configuration(file);

    config.load();
    load();
  }

  public static void load() {
    String category;

    category = "Items";
    config.addCustomCategoryComment(category, "Allows for the tweaking of non-AMP-related items.");
    pestleDurability = config.getInt("pestleDurability", category, pestleDurability, 0, Integer.MAX_VALUE, "The durability of the Pestle.");

    if (config.hasChanged())
      config.save();
  }

}
