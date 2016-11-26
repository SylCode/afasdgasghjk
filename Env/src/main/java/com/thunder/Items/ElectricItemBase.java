package com.thunder.Items;

import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.init.InternalName;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ElectricItemBase extends Item implements IElectricItem {
	
	public int maxCharge;
	public int transferLimit;
	public int tier;

	public ElectricItemBase()
	  {
	    super();
	    setMaxDamage(27);
	    setMaxStackSize(1);
	    setNoRepair();
	  }

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		
		return false;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		
		return this;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		
		return this;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		
		return this.maxCharge;
	}

	@Override
	public int getTier(ItemStack itemStack) {
		
		return this.tier;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		
		return this.transferLimit;
	}
	
	public List<String> getHudInfo(ItemStack itemStack)
	{
	    List<String> info = new LinkedList();
	    info.add(ElectricItem.manager.getToolTip(itemStack));
	    return info;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList)
	{
	    ItemStack itemStack = new ItemStack(this, 1);
	    if (getChargedItem(itemStack) == this)
	    {
	      ItemStack charged = new ItemStack(this, 1);
	      ElectricItem.manager.charge(charged, Double.POSITIVE_INFINITY, Integer.MAX_VALUE, true, false);
	      itemList.add(charged);
	    }
	    if (getEmptyItem(itemStack) == this)
	    {
	      ItemStack charged = new ItemStack(this, 1);
	      ElectricItem.manager.charge(charged, 0.0D, Integer.MAX_VALUE, true, false);
	      itemList.add(charged);
	    }
	}

}
