package com.thunder.Updaters;

import com.thunder.Api.IEnergyAdd;
import com.thunder.Armor.IEnergyArmor;
import com.thunder.Armor.IModArmor;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.InventoryEnergyPlayer;

import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyUpdaterLavaFreezing extends IEnergyUpdater implements IEnergyAdd {
	
	IEnergyUpdaterLavaFreezing(){
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
			if(matrix != null && (matrix.getItem().equals(UpdatersList.EnergyMatrixUltra)) && hasFullSet(player)){
				NBTTagCompound nbtData = getOrCreateNbtData(matrix);
				 
				 for(int a = 0; a < inv.getSizeInventory(); a++){
					ItemStack stack = inv.getStackInSlot(a);
					if(stack != null && (stack.getItem().equals(UpdatersList.EnergyUpdaterLavaFreezing))){
						counter += 1;
					}
				 }
				
				 if(nbtData.getBoolean("active") && ElectricItem.manager.canUse(matrix, 25.0D) && counter == 1){
			 
					 int i = MathHelper.floor_double(player.posX);
					 int j = MathHelper.floor_double(player.boundingBox.minY - 1);
					 int k = MathHelper.floor_double(player.posZ);

					 
					 if (world.getBlock(i, j, k).getMaterial() == Material.lava && player.motionY < 0.0D) {
							
						 if(world.getBlock(i, j, k).getMaterial() == Material.lava)
						 world.setBlock(i, j, k, Blocks.obsidian);
						 if(world.getBlock(i + 1, j, k + 1).getMaterial() == Material.lava)
						 world.setBlock(i + 1, j, k + 1, Blocks.obsidian);
						 if(world.getBlock(i - 1, j, k - 1).getMaterial() == Material.lava)
						 world.setBlock(i - 1, j, k - 1, Blocks.obsidian);
						 if(world.getBlock(i - 1, j, k + 1).getMaterial() == Material.lava)
						 world.setBlock(i - 1, j, k + 1, Blocks.obsidian);
						 if(world.getBlock(i + 1, j, k - 1).getMaterial() == Material.lava)
						 world.setBlock(i + 1, j, k - 1, Blocks.obsidian);
						 if(world.getBlock(i, j, k + 1).getMaterial() == Material.lava)
						 world.setBlock(i, j, k + 1, Blocks.obsidian);
						 if(world.getBlock(i + 1, j, k).getMaterial() == Material.lava)
						 world.setBlock(i + 1, j, k, Blocks.obsidian);
						 if(world.getBlock(i, j, k - 1).getMaterial() == Material.lava)
						 world.setBlock(i, j, k - 1, Blocks.obsidian);
						 if(world.getBlock(i - 1, j, k).getMaterial() == Material.lava)
						 world.setBlock(i - 1, j, k, Blocks.obsidian);
						 
						 ElectricItem.manager.discharge(matrix, 25.0D, 4, true, false, false);
					}
					 
					if (world.getBlock(i, j, k).getMaterial() == Material.rock) {
						
						 boolean lava = false;
							
						 if(world.getBlock(i + 1, j, k + 1).getMaterial() == Material.lava)
						 lava = world.setBlock(i + 1, j, k + 1, Blocks.obsidian);
						 if(world.getBlock(i - 1, j, k - 1).getMaterial() == Material.lava)
						 lava =world.setBlock(i - 1, j, k - 1, Blocks.obsidian);
						 if(world.getBlock(i - 1, j, k + 1).getMaterial() == Material.lava)
						 lava = world.setBlock(i - 1, j, k + 1, Blocks.obsidian);
						 if(world.getBlock(i + 1, j, k - 1).getMaterial() == Material.lava)
						 lava = world.setBlock(i + 1, j, k - 1, Blocks.obsidian);
						 if(world.getBlock(i, j, k + 1).getMaterial() == Material.lava)
						 lava = world.setBlock(i, j, k + 1, Blocks.obsidian);
						 if(world.getBlock(i + 1, j, k).getMaterial() == Material.lava)
						 lava = world.setBlock(i + 1, j, k, Blocks.obsidian);
						 if(world.getBlock(i, j, k - 1).getMaterial() == Material.lava)
						 lava = world.setBlock(i, j, k - 1, Blocks.obsidian);
						 if(world.getBlock(i - 1, j, k).getMaterial() == Material.lava)
						 lava = world.setBlock(i - 1, j, k, Blocks.obsidian);
						 
						 if(lava){
						 ElectricItem.manager.discharge(matrix, 25.0D, 4, true, false, false);
						 lava = false;
						 }
					}
					 
					
					 

				 }
			}
		}

	}
	
	@Override
	protected String getUpdater(){	
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLavaFreezing.getupdater");
	}
	
	@Override
	protected String getType(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLavaFreezing.gettype");
	}
	
	@Override
	protected String getMatrix(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLavaFreezing.getmatrix");
	}
	
	@Override
	protected String getEffect(){
		return StatCollector.translateToLocal("tag.updaters.EnergyUpdaterLavaFreezing.geteffect");
	}		

}
