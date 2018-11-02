package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.blocks.BlockMod;
import com.eagle.adventurersamulets.items.ItemBlockMod;
import com.eagle.adventurersamulets.items.ItemMod;
import com.eagle.adventurersamulets.items.ItemPestle;
import java.util.LinkedList;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Core.MOD_ID)
public class ModItems {

  public static LinkedList<ItemMod> modItems = new LinkedList<>();
  public static LinkedList<ItemBlockMod> modItemBlocks = new LinkedList<>();

  public static ItemPestle PESTLE = new ItemPestle();
  public static ItemMod ASH = new ItemMod(Dictionary.Item.ASH);

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    IForgeRegistry<Item> registry = event.getRegistry();

    modItems.forEach(registry::register);
    modItemBlocks.forEach(registry::register);
  }

  @SubscribeEvent
  public static void registerItemModels(ModelRegistryEvent event) {
    for (ItemMod item : modItems)
      item.initModel();

    for (BlockMod block : ModBlocks.modBlocks)
      block.initModel();
  }


}
