package com.thunder.EnergyAdditions;

import com.thunder.Creative.CreativeItemList;
import com.thunder.Items.ItemList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabCreative extends CreativeTabs {

public TabCreative(String lable)
{
  super("EnergyAdditionsCreative");
}


@Override
public Item getTabIconItem() {
	
	return CreativeItemList.SuicideSquadStuff;
}


}
