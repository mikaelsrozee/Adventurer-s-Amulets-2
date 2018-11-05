package com.eagle.adventurersamulets.init;

import com.eagle.adventurersamulets.Dictionary;
import com.eagle.adventurersamulets.Dictionary.Core;
import com.eagle.adventurersamulets.blocks.BlockCraftingRune;
import com.eagle.adventurersamulets.blocks.BlockMod;
import java.util.LinkedList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Core.MOD_ID)
public class ModBlocks {

  public static LinkedList<BlockMod> modBlocks = new LinkedList<>();

  public static Block SLATE = new BlockMod(Dictionary.Block.SLATE);
  public static BlockCraftingRune CRAFTINGRUNE = new BlockCraftingRune();
  public static Block EMERALDCLOTH = new BlockMod(Dictionary.Block.EMERALDCLOTH, Material.CLOTH,
      SoundType.CLOTH).setHardness(2.0F).setLightLevel(2.0F);

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    IForgeRegistry<Block> registry = event.getRegistry();
    modBlocks.forEach(registry::register);
  }

}
