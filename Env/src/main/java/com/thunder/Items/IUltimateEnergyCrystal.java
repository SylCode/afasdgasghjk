package com.thunder.Items;

import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IBoxable;
import ic2.core.init.InternalName;
import ic2.core.item.ItemBattery;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;


public class IUltimateEnergyCrystal extends ElectricItemBase {
	
	

	public IUltimateEnergyCrystal(){
		super();
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		
		this.maxCharge = 40000000;
		this.transferLimit = 200000;
		this.tier = 4;
	
	}
	
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
			
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		    return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
	    return EnumRarity.epic;
	}
}

