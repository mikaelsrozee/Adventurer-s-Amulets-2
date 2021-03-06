package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.blocks.BlockMod;
import com.eagle.adventurersamulets.items.*;

import java.util.LinkedList;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Core.MOD_ID)
public class ModItems {

  public static LinkedList<ItemMod> modItems = new LinkedList<>();
  public static LinkedList<ItemBlockMod> modItemBlocks = new LinkedList<>();

  public static Item.ToolMaterial ampToolMaterial = EnumHelper
      .addToolMaterial("AMPToolMaterial", ModConfig.ampMiningLevel, ModConfig.ampMaxUses,
          ModConfig.ampEfficiency, ModConfig.ampDamage, ModConfig.ampEnchantability);

  public static ItemPestle PESTLE = new ItemPestle();
  public static ItemMod ASH = new ItemMod(Dictionary.Item.ASH);
  public static ItemSearing GEMSTONE = new ItemSearing(Dictionary.Item.SEARINGGEMSTONE);
  public static ItemSearing IRON = new ItemSearing(Dictionary.Item.SEARINGIRON);
  public static ItemMod SLATEROD = new ItemMod(Dictionary.Item.SLATEROD);
  public static ItemSmeltingPickaxe SMELTINGPICKAXE = new ItemSmeltingPickaxe();
  public static ItemMod FEATHERDUST = new ItemMod(Dictionary.Item.FEATHERDUST);
  public static ItemMod EMERALDSTRING = new ItemMod(Dictionary.Item.EMERALDSTRING);
  public static ItemMod FEATHEREDASH = new ItemMod(Dictionary.Item.FEATHEREDASH);
  public static ItemWindmakersBlade WINDMAKERSBLADE = new ItemWindmakersBlade();
  public static ItemWaterWalkBelt WATERWALKBELT = new ItemWaterWalkBelt();
  public static ItemMod OCEANICDUST = new ItemMod(Dictionary.Item.OCEANICDUST);
  public static ItemSmoulderingCloak SMOULDERINGCLOAK = new ItemSmoulderingCloak();
  public static ItemSnowflakeVeil SNOWFLAKEVEIL = new ItemSnowflakeVeil();
  public static ItemAntiGravRing ANTIGRAVRING = new ItemAntiGravRing();
  public static ItemAmbrosia AMBROSIA = new ItemAmbrosia();
  public static ItemMod TRANSPOSINGINGOT = new ItemMod(Dictionary.Item.TRANSPOSINGINGOT);
  public static ItemMod CLOAK = new ItemMod(Dictionary.Item.CLOAK);
  public static ItemMod PACKEDSNOW = new ItemMod(Dictionary.Item.PACKEDSNOW); // TODO: Make throwable
  public static ItemMod FROSTDUST = new ItemMod(Dictionary.Item.FROSTDUST);

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    IForgeRegistry<Item> registry = event.getRegistry();

    modItems.forEach(registry::register);
    modItemBlocks.forEach(registry::register);

    registry.registerAll(SMELTINGPICKAXE, WINDMAKERSBLADE);
  }

  @SubscribeEvent
  public static void registerItemModels(ModelRegistryEvent event) {
    for (ItemMod item : modItems) {
      item.initModel();
    }

    for (BlockMod block : ModBlocks.modBlocks) {
      block.initModel();
    }

    SMELTINGPICKAXE.initModel();
    WINDMAKERSBLADE.initModel();
  }


}
