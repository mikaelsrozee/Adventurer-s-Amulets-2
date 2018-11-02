package com.eagle.adventurersamulets;

import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.init.ModAMP;
import com.eagle.adventurersamulets.init.ModConfig;
import com.eagle.adventurersamulets.init.ModCreativeTab;
import com.eagle.adventurersamulets.init.ModInteractHandler;
import com.eagle.adventurersamulets.init.ModItems;
import com.eagle.adventurersamulets.init.ModRecipes;
import com.eagle.adventurersamulets.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Core.MOD_ID, name = Core.MOD_NAME, version = Core.VERSION, dependencies = Core.DEPENDENCIES)
public class AdventurersAmulets {

  @SidedProxy(clientSide = Core.CLIENT_PROXY_CLASS, serverSide = Core.COMMON_PROXY_CLASS)
  public static CommonProxy proxy;

  public static final ModCreativeTab creativeTab = new ModCreativeTab();

  @EventHandler
  public static void preInit(FMLPreInitializationEvent event) {
    ModConfig.loadConfig(event.getSuggestedConfigurationFile());
  }

  @EventHandler
  public static void init(FMLInitializationEvent event) {
    ModRecipes.init();
    ModAMP.registerAMPTypes();
    MinecraftForge.EVENT_BUS.register(new ModInteractHandler());
    MinecraftForge.EVENT_BUS.register(ModItems.SMELTINGPICKAXE);
  }

  @EventHandler
  public static void postInit(FMLPostInitializationEvent event) {

  }

}
