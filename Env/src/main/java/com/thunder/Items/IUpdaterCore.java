package com.thunder.Items;

import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class IUpdaterCore extends Item {
	
	public IUpdaterCore(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setMaxStackSize(64);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	

}