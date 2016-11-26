package com.thunder.Updaters;

import com.thunder.Api.IEnergyAdd;
import com.thunder.Armor.IEnergyArmor;
import com.thunder.Armor.IModArmor;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.InventoryEnergyPlayer;

import ic2.api.item.ElectricItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class IEnergyUpdaterJetPack extends IEnergyUpdater implements IEnergyAdd {
	
	IEnergyUpdaterJetPack(){
		super();
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
	}
	
	@Override
	protected String getUpdater(){	
		return "Utility";
	}
	
	@Override
	protected String getType(){
		return "Standart";
	}
	
	@Override
	protected String getMatrix(){
		return "Standart Matrix +";
	}
	
	@Override
	protected String getEffect(){
		return "JetPack Modificator";
	}		

}