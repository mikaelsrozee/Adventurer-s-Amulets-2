package com.eagle.adventurersamulets.blocks;

import com.eagle.adventurersamulets.AdventurersAmulets;
import com.eagle.adventurersamulets.init.ModBlocks;
import com.eagle.adventurersamulets.init.ModItems;
import com.eagle.adventurersamulets.items.ItemBlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMod extends Block {

  protected String name;

  public BlockMod(String name) {
    this(name, Material.ROCK);
  }

  public BlockMod(String name, Material material) {
    this(name, material, SoundType.STONE);
  }

  public BlockMod(String name, Material material, SoundType soundType) {
    super(material);
    this.name = name;
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
    AdventurersAmulets.proxy.initModel(Item.getItemFromBlock(this), 0, name);
  }

}
