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

public class IEnergyUpdaterCombat1 extends IEnergyUpdater implements IEnergyAdd {
	
	IEnergyUpdaterCombat1(){
		super();
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
	}

	@Override
	public float addDamageToEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity, float damage) {
		
		if (player != null) {
			InventoryEnergyPlayer inv = ExtendedInventory.get(player).inventory;
			 
			ItemStack matrix = inv.getStackInSlot(4);
			int counter = 0;
			if(matrix != null && (matrix.getItem().equals(UpdatersList.EnergyMatrix) ||  matrix.getItem().equals(UpdatersList.EnergyMatrixStandart) || matrix.getItem().equals(UpdatersList.EnergyMatrixUltra)) && hasFullSet(player)){
				NBTTagCompound nbtData = getOrCreateNbtData(matrix);
				 
				 for(int a = 0; a < inv.getSizeInventory(); a++){
					ItemStack stack = inv.getStackInSlot(a);
					if(stack != null && (stack.getItem().equals(UpdatersList.EnergyUpdater1) || stack.getItem().equals(UpdatersList.EnergyUpdaterCombat2) || stack.getItem().equals(UpdatersList.EnergyUpdaterCombat3))){
						counter += 1;
					}
				 }
				
				 if(nbtData.getBoolean("active") && ElectricItem.manager.canUse(matrix, 1000.0D) && counter == 1){
				 ElectricItem.manager.discharge(matrix, 1000.0D, 4, true, false, false);
				 return damage + 1.5F;
				 }
			}
		}
		return damage;
	}
	
	@Override
	protected String getUpdater(){	
		return "Combat";
	}
	
	@Override
	protected String getType(){
		return "Low";
	}
	
	@Override
	protected String getMatrix(){
		return "Low Matrix +";
	}
	
	@Override
	protected String getEffect(){
		return "+ 1.5 physical damage with autoattack(1000.0 EU)";
	}		

}
