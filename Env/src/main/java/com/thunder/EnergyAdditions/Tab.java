package com.thunder.EnergyAdditions;

import com.thunder.Items.ItemList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Tab extends CreativeTabs {

public Tab(String lable)
{
  super("EnergyAdditions");
}


@Override
public Item getTabIconItem() {
	
	return ItemList.DiamondInlaidCircuit;
}


}