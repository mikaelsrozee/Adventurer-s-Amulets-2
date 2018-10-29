package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.items.ItemMod;
import java.util.LinkedList;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Core.MOD_ID)
public final class ModItems {

  public static LinkedList<ItemMod> modItems = new LinkedList<>();

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    IForgeRegistry<Item> registry = event.getRegistry();
    modItems.forEach(registry::register);
  }

}
