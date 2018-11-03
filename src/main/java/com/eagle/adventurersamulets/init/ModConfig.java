package com.eagle.adventurersamulets.init;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public final class ModConfig {

  public static Configuration config;

  public static int ampInitial = 0;
  public static int ampMiningLevel = 3;
  public static int ampMaxUses = 1561;
  public static float ampEfficiency = 8.0F;
  public static float ampDamage = 4.0F;
  public static int ampEnchantability = 25;
  public static int pestleDurability = 31;
  public static int windmakersHeight = 127;
  public static int earthboundHeight = 30;
  public static int searingMaxAMP = 1000;
  public static boolean fortuneSmelting = true;
  public static int scorchingCoreMaxAMP = 1000;
  public static int waterWalkerMaxAMP = 1000;
  public static int windmakersBladeMaxAMP = 1000;
  public static int windmakersBladeDashCost = 5;
  public static int windmakersBladeDashCooldown = 25;

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
    ampInitial = config.getInt("AMPInitial", category, ampInitial, 0, Integer.MAX_VALUE, "The amount of AMP in any AMP item when it is first crafted.");
    ampMiningLevel = config.getInt("AMPMiningLevel", category, ampMiningLevel, -1, Integer.MAX_VALUE, "Mining Level for AMP tools.");
    ampMaxUses = config.getInt("AMPMaxUses", category, ampMaxUses, -1, Integer.MAX_VALUE, "Durability of AMP tools.");
    ampEfficiency = config.getFloat("AMPEfficiency", category, ampEfficiency, 0, Float.MAX_VALUE, "Mining speed of AMP tools.");
    ampDamage = config.getFloat("AMPDamage", category, ampDamage, 0, Float.MAX_VALUE, "Damage of AMP tools.");
    ampEnchantability = config.getInt("AMPEnchantability", category, ampEnchantability, 0, Integer.MAX_VALUE, "Enchantability of AMP tools.");
    windmakersHeight = config.getInt("windmakersHeight", category, windmakersHeight, 0, 255, "The height above which Windmaker's items will charge.");
    earthboundHeight = config.getInt("earthboundHeight", category, earthboundHeight, 0, 255, "The height below which Earthbound items will charge.");
    searingMaxAMP = config.getInt("searingMaxAMP", category, searingMaxAMP, 0, Integer.MAX_VALUE, "The maximum AMP that the Searing Iron/Gemstone can store.");
    fortuneSmelting = config.getBoolean("fortuneSmelting", category, fortuneSmelting, "Set to false to disable the fortune multiplier on the Pickaxe of the Scorching Core when it is enchanted with fortune.");
    scorchingCoreMaxAMP = config.getInt("scorchingCoreMaxAMP", category, scorchingCoreMaxAMP, 0, Integer.MAX_VALUE, "Maximum AMP that the Pickaxe of the Scorching Core can store.");
    windmakersBladeMaxAMP = config.getInt("windmakersBladeMaxAMP", category, windmakersBladeMaxAMP, 0, Integer.MAX_VALUE, "Maximum AMP that the Windmaker's Blade can store.");
    windmakersBladeDashCost = config.getInt("windmakersBladeDashCost", category, windmakersBladeDashCost, 0, windmakersBladeMaxAMP, "The AMP cost per dash.");
    windmakersBladeDashCooldown = config.getInt("windmakersBladeDashCooldown", category, windmakersBladeDashCooldown, 0, Integer.MAX_VALUE, "The time, in ticks, for the cooldown of the Blade's dash ability.");
    waterWalkerMaxAMP = config.getInt("waterWalkerMaxAMP", category, waterWalkerMaxAMP, 0, Integer.MAX_VALUE, "Maximum AMP that the Belt of the Water Walker can store.");

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
