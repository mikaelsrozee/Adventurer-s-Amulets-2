package com.eagle.adventurersamulets.blocks;

import com.eagle.adventurersamulets.AdventurersAmulets;
import com.eagle.adventurersamulets.init.ModBlocks;
import com.eagle.adventurersamulets.init.ModItems;
import com.eagle.adventurersamulets.items.ItemBlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMod extends Block {

  public BlockMod(String name) {
    this(name, Material.ROCK);
  }

  public BlockMod(String name, Material material) {
    this(name, material, SoundType.STONE);
  }

  public BlockMod(String name, Material material, SoundType soundType) {
    super(material);
    setUnlocalizedName(name);
    setRegistryName(name);
    setHardness(1.0F);
    setCreativeTab(AdventurersAmulets.creativeTab);
    setSoundType(soundType);
    ModBlocks.modBlocks.add(this);
    ModItems.modItemBlocks.add(new ItemBlockMod(this));
  }

  @SideOnly(Side.CLIENT)
  public void initModel() {
    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
  }

}
