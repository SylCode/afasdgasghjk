package com.thunder.Updaters;

import java.util.Random;

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

public class IEnergyUpdaterAddExtraDamage2 extends IEnergyUpdater implements IEnergyAdd {
	
	IEnergyUpdaterAddExtraDamage2(){
		super();
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
	}

	@Override
	public float addDamageToEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity, float damage) {
		
		if (player != null) {
			InventoryEnergyPlayer inv = ExtendedInventory.get(player).inventory;
			 
			ItemStack matrix = inv.getStackInSlot(4);
			int counter = 0;
			if(matrix != null && (matrix.getItem().equals(UpdatersList.EnergyMatrixStandart) || matrix.getItem().equals(UpdatersList.EnergyMatrixUltra)) && hasFullSet(player)){
				NBTTagCompound nbtData = getOrCreateNbtData(matrix);
				 
				 for(int a = 0; a < inv.getSizeInventory(); a++){
					ItemStack stack = inv.getStackInSlot(a);
					if(stack != null && (stack.getItem().equals(UpdatersList.EnergyUpdaterAddExtraDamage2) || stack.getItem().equals(UpdatersList.EnergyUpdaterAddExtraDamage3) || stack.getItem().equals(UpdatersList.EnergyUpdaterCombat3))){
						counter += 1;
					}
				 }
				
				 if(nbtData.getBoolean("active") && ElectricItem.manager.canUse(matrix, 250000.0D) && counter == 1){
					 
				 Random r = new Random();
				 int random = r.nextInt(99);
				 if(random <= 20 && random >= 10){
				 ElectricItem.manager.discharge(matrix, 250000.0D, 4, true, false, false);
				 return damage + damage;
				 }
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
		return "Standart";
	}
	
	@Override
	protected String getMatrix(){
		return "Standart Matrix +";
	}
	
	@Override
	protected String getEffect(){
		return "Chance 10% to deal double damage with autoattack(250000.0 EU)";
	}		

}

