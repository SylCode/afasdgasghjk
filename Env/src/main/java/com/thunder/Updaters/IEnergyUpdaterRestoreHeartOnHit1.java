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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyUpdaterRestoreHeartOnHit1 extends IEnergyUpdater implements IEnergyAdd {
	
	Random r = new Random();
	
	IEnergyUpdaterRestoreHeartOnHit1(){
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
					if(stack != null && (stack.getItem().equals(UpdatersList.EnergyUpdaterRestoreHeartOnHit1) || stack.getItem().equals(UpdatersList.EnergyUpdaterRestoreHeartOnHit2) || stack.getItem().equals(UpdatersList.EnergyUpdaterRestoreHeartOnHit3))){
						counter += 1;
					}
				 }
				
				 if(nbtData.getBoolean("active") && ElectricItem.manager.canUse(matrix, 20000.0D) && counter == 1){
				 
				 int random = r.nextInt(99);
				 if(random <= 30 && random >= 10){
				 float h = player.getHealth() + 2.0F;
				 player.setHealth(h);
				 ElectricItem.manager.discharge(matrix, 20000.0D, 4, true, false, false);
				 return damage;
				 }
				 }
			}
		}
		return damage;
	}
	
	@Override
	protected String getUpdater(){	
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterRestoreHeartOnHit1.getupdater");
	}
	
	@Override
	protected String getType(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterRestoreHeartOnHit1.gettype");
	}
	
	@Override
	protected String getMatrix(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterRestoreHeartOnHit1.getmatrix");
	}
	
	@Override
	protected String getEffect(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterRestoreHeartOnHit1.geteffect");
	}		

}
