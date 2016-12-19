package com.thunder.Updaters;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;
import com.thunder.Api.IEnergyAdd;
import com.thunder.Armor.IEnergyArmor;
import com.thunder.Armor.IModArmor;
import com.thunder.Blocks.BlockList;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.InventoryEnergyPlayer;

import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyUpdaterLight extends IEnergyUpdater implements IEnergyAdd {
	
	public static final Set replaceableBlocks = Sets.newHashSet(new Block[] {Blocks.air, BlockList.BlockLight});
	
	IEnergyUpdaterLight(){
		super();
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
	}
	
	Random rand = new Random();

	@Override
	public void onItemInventoryTick(ItemStack itemstack, World world, EntityLivingBase entity) {
		
		if (entity != null && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			InventoryEnergyPlayer inv = ExtendedInventory.get(player).inventory;

			ItemStack matrix = inv.getStackInSlot(4);
			int counter = 0;
			if(matrix != null && (matrix.getItem().equals(UpdatersList.EnergyMatrixStandart) || matrix.getItem().equals(UpdatersList.EnergyMatrixUltra)) && hasFullSet(player)){
				NBTTagCompound nbtData = getOrCreateNbtData(matrix);
				 
				 for(int a = 0; a < inv.getSizeInventory(); a++){
					ItemStack stack = inv.getStackInSlot(a);
					if(stack != null && (stack.getItem().equals(UpdatersList.EnergyUpdaterLight))){
						counter += 1;
					}
				 }
				
				 if(nbtData.getBoolean("active") && ElectricItem.manager.canUse(matrix, 20.0D) && counter == 1){
					 
					 if (!player.worldObj.isRemote)
						{
	
							int x = (int) (player.posX + rand.nextInt(2) - rand.nextInt(2));
							int y = (int) (player.posY + rand.nextInt(2) - rand.nextInt(2));
							int z = (int) (player.posZ + rand.nextInt(2) - rand.nextInt(2));
						 
							if (replaceableBlocks.contains(player.worldObj.getBlock(x, y, z)))
							{
								player.worldObj.setBlock(x, y, z, BlockList.BlockLight);
							}
							ElectricItem.manager.discharge(matrix, 20.0D, 4, true, false, false);
						}
		 

				 }
			}
		}

	}
	
	@Override
	protected String getUpdater(){	
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLight.getupdater");
	}
	
	@Override
	protected String getType(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLight.gettype");
	}
	
	@Override
	protected String getMatrix(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLight.getmatrix");
	}
	
	@Override
	protected String getEffect(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLight.geteffect");
	}		

}