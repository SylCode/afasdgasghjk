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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyUpdaterJump1 extends IEnergyUpdater implements IEnergyAdd {
	
	IEnergyUpdaterJump1(){
		super();
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
	}

	@Override
	public void onItemInventoryTick(ItemStack itemstack, World world, EntityLivingBase entity) {
		
		if (entity != null && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			InventoryEnergyPlayer inv = ExtendedInventory.get(player).inventory;
			 
			ItemStack matrix = inv.getStackInSlot(4);
			int counter = 0;
			if(matrix != null && (matrix.getItem().equals(UpdatersList.EnergyMatrix) ||  matrix.getItem().equals(UpdatersList.EnergyMatrixStandart) || matrix.getItem().equals(UpdatersList.EnergyMatrixUltra)) && hasFullSet(player)){
				NBTTagCompound nbtData = getOrCreateNbtData(matrix);
				 
				 for(int a = 0; a < inv.getSizeInventory(); a++){
					ItemStack stack = inv.getStackInSlot(a);
					if(stack != null && (stack.getItem().equals(UpdatersList.EnergyUpdaterJump1) || stack.getItem().equals(UpdatersList.EnergyUpdaterJump2) || stack.getItem().equals(UpdatersList.EnergyUpdaterJump3))){
						counter += 1;
					}
				 }
				
				 if(nbtData.getBoolean("active") && ElectricItem.manager.canUse(matrix, 30.0D) && counter == 1){
			 
					 	 effectPlayer(player, Potion.jump, 1);
						 ElectricItem.manager.discharge(matrix, 30.0D, 4, true, false, false);
					 

				 }
			}
		}

	}
	
	@Override
	protected String getUpdater(){	
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterJump1.getupdater");
	}
	
	@Override
	protected String getType(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterJump1.gettype");
	}
	
	@Override
	protected String getMatrix(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterJump1.getmatrix");
	}
	
	@Override
	protected String getEffect(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterJump1.geteffect");
	}		

}
