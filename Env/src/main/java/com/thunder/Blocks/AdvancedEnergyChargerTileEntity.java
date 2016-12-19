package com.thunder.Blocks;

import java.util.List;

import ic2.api.energy.EnergyNet;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.Ic2Items;
import ic2.core.block.TileEntityLiquidTankElectricMachine;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.block.machine.tileentity.TileEntityMacerator;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class AdvancedEnergyChargerTileEntity extends ATileEntityElectricBlock {
	
	
	public int Pos2X;
	public int Pos2Y;
	public int Pos2Z;

	public int Pos3X;
	public int Pos3Y;
	public int Pos3Z;

	public int Pos4X;
	public int Pos4Y;
	public int Pos4Z;

	public int Pos5X;
	public int Pos5Y;
	public int Pos5Z;

	public int Pos6X;
	public int Pos6Y;
	public int Pos6Z;

	public int Pos7X;
	public int Pos7Y;
	public int Pos7Z;

	public int Pos8X;
	public int Pos8Y;
	public int Pos8Z;

	public int Pos1X;
	public int Pos1Y;
	public int Pos1Z;

	public int Pos9X;
	public int Pos9Y;
	public int Pos9Z;

	public int Pos10X;
	public int Pos10Y;
	public int Pos10Z;

	public int Pos11X;
	public int Pos11Y;
	public int Pos11Z;

	public int Pos12X;
	public int Pos12Y;
	public int Pos12Z;

	public int Pos13X;
	public int Pos13Y;
	public int Pos13Z;

	public int Pos14X;
	public int Pos14Y;
	public int Pos14Z;

	public int Pos15X;
	public int Pos15Y;
	public int Pos15Z;

	public int Pos16X;
	public int Pos16Y;
	public int Pos16Z;
	

	
	public AdvancedEnergyChargerTileEntity() {
		super(5, 2048, 400000000);
		
		
	}
	
	@Override
	public String getInventoryName() {
		
		return "AdvancedEnergyCharger";
	}
	
	@Override
	protected void updateEntityServer()
	  {
		 super.updateEntityServer();
		    
		    boolean needsInvUpdate = false;
		    if (this.energy >= 1.0D)
		    {
		      double sent1 = this.chargeSlot1.charge(this.energy);
		      double sent2 = this.chargeSlot2.charge(this.energy);
		      double sent3 = this.chargeSlot3.charge(this.energy);
		      double sent4 = this.chargeSlot4.charge(this.energy);
		      
		      this.energy -= sent1;
		      this.energy -= sent2;
		      this.energy -= sent3;
		      this.energy -= sent4;
		      if(sent1 > 0.0D || sent2 > 0.0D || sent3 > 0.0D || sent4 > 0.0D)
		      needsInvUpdate = true;
		    }
		    if (((getDemandedEnergy() > 0.0D) && (!this.dischargeSlot1.isEmpty())) || ((getDemandedEnergy() > 0.0D) && (!this.dischargeSlot2.isEmpty())))
		    {
		      double gain1 = this.dischargeSlot1.discharge(this.maxStorage - this.energy, false);
		      double gain2 = this.dischargeSlot2.discharge(this.maxStorage - this.energy, false);
		      
		      this.energy += gain1;
		      this.energy += gain2;
		      
		      if(gain1 > 0.0D || gain2 > 0.0D)
		      needsInvUpdate = true;
		    }
		    boolean shouldEmitRedstone = shouldEmitRedstone();
		    if (shouldEmitRedstone != this.isEmittingRedstone)
		    {
		      this.isEmittingRedstone = shouldEmitRedstone;
		      setActive(this.isEmittingRedstone);
		      
		      this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
		    }
		    if (needsInvUpdate) {
		      markDirty();
		    }
		
		if(this.energy < 0) this.energy = 0;
		
		if (this.energy > 1000){
			scanForMachines();
		}
		
		if(this.canEmitEnergy()){
			
				 List list = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((float) (xCoord -9), (float) (yCoord - 11), (float) (zCoord - 9), (float) (xCoord + 11), (float) (yCoord + 11), (float) (zCoord + 11)));
				 for(int i=0; i < list.size(); i++)
		         {
		              Entity e = (Entity) list.get(i);
		              if(e instanceof EntityPlayer){
		            	  getItems((EntityPlayer) e);
		              }
		         }
					
		}
		
	  }
	
	protected void getItems(EntityPlayer player)
	  {
	    if (player != null)
	    {
	      for (ItemStack current : player.inventory.armorInventory) {
	        if (current != null) {
	          chargeitems(current, this.getVoltage());
	        }
	      }
	      for (ItemStack current : player.inventory.mainInventory) {
	        if (current != null) {
	          chargeitems(current, this.getVoltage());
	        }
	      }
	    }
	  }
	
	  
	protected void chargeitems(ItemStack itemstack, int chargefactor)
	  {
	    if (!(itemstack.getItem() instanceof IElectricItem)) {
	      return;
	    }
	    if (itemstack.getItem() == Ic2Items.debug.getItem()) {
	      return;
	    }
	    double freeamount = ElectricItem.manager.charge(itemstack, Double.POSITIVE_INFINITY, this.tier, true, true);
	    
	    double charge = 0.0D;
	    if (freeamount >= 0.0D)
	    {
	      if (freeamount >= chargefactor * getTickRate()) {
	        charge = chargefactor * getTickRate();
	      } else {
	        charge = freeamount;
	      }
	      if (this.energy < charge) {
	        charge = this.energy;
	      }
	      this.energy -= ElectricItem.manager.charge(itemstack, charge, this.tier, true, false);
	    }
	  }

	  protected int getTickRate()
	  {
	    return 2;
	  }
	
	@Override
	 public void readFromNBT(NBTTagCompound tag)
	 {
	    super.readFromNBT(tag);
	    

	    this.energy = Util.limit(tag.getDouble("energy"), 0.0D, this.maxStorage + EnergyNet.instance.getPowerFromTier(this.tier));
	    this.redstoneMode = tag.getByte("redstoneMode");
	    

	    
	    this.Pos1X = tag.getInteger("Pos1X");
	    this.Pos1Y = tag.getInteger("Pos1Y");
	    this.Pos1Z = tag.getInteger("Pos1Z");
	    
	    this.Pos2X = tag.getInteger("Pos2X");
	    this.Pos2Y = tag.getInteger("Pos2Y");
	    this.Pos2Z = tag.getInteger("Pos2Z");
	    
	    this.Pos3X = tag.getInteger("Pos3X");
	    this.Pos3Y = tag.getInteger("Pos3Y");
	    this.Pos3Z = tag.getInteger("Pos3Z");
	    
	    this.Pos4X = tag.getInteger("Pos4X");
	    this.Pos4Y = tag.getInteger("Pos4Y");
	    this.Pos4Z = tag.getInteger("Pos4Z");
	    
	    this.Pos5X = tag.getInteger("Pos5X");
	    this.Pos5Y = tag.getInteger("Pos5Y");
	    this.Pos5Z = tag.getInteger("Pos5Z");
	    
	    this.Pos6X = tag.getInteger("Pos6X");
	    this.Pos6Y = tag.getInteger("Pos6Y");
	    this.Pos6Z = tag.getInteger("Pos6Z");
	    
	    this.Pos7X = tag.getInteger("Pos7X");
	    this.Pos7Y = tag.getInteger("Pos7Y");
	    this.Pos7Z = tag.getInteger("Pos7Z");
	    
	    this.Pos8X = tag.getInteger("Pos8X");
	    this.Pos8Y = tag.getInteger("Pos8Y");
	    this.Pos8Z = tag.getInteger("Pos8Z");
	    
	    this.Pos9X = tag.getInteger("Pos9X");
	    this.Pos9Y = tag.getInteger("Pos9Y");
	    this.Pos9Z = tag.getInteger("Pos9Z");
	    
	    this.Pos10X = tag.getInteger("Pos10X");
	    this.Pos10Y = tag.getInteger("Pos10Y");
	    this.Pos10Z = tag.getInteger("Pos10Z");
	    
	    this.Pos11X = tag.getInteger("Pos11X");
	    this.Pos11Y = tag.getInteger("Pos11Y");
	    this.Pos11Z = tag.getInteger("Pos11Z");
	    
	    this.Pos12X = tag.getInteger("Pos12X");
	    this.Pos12Y = tag.getInteger("Pos12Y");
	    this.Pos12Z = tag.getInteger("Pos12Z");
	    
	    this.Pos13X = tag.getInteger("Pos13X");
	    this.Pos13Y = tag.getInteger("Pos13Y");
	    this.Pos13Z = tag.getInteger("Pos13Z");
	    
	    this.Pos14X = tag.getInteger("Pos14X");
	    this.Pos14Y = tag.getInteger("Pos14Y");
	    this.Pos14Z = tag.getInteger("Pos14Z");
	    
	    this.Pos15X = tag.getInteger("Pos15X");
	    this.Pos15Y = tag.getInteger("Pos15Y");
	    this.Pos15Z = tag.getInteger("Pos15Z");
	    
	    this.Pos16X = tag.getInteger("Pos16X");
	    this.Pos16Y = tag.getInteger("Pos16Y");
	    this.Pos16Z = tag.getInteger("Pos16Z");

	  }
	  
	 @Override
	  public void writeToNBT(NBTTagCompound tag)
	  {
	    super.writeToNBT(tag);
	    
	    tag.setInteger("Pos1X", this.Pos1X);
	    tag.setInteger("Pos1Y", this.Pos1Y);
	    tag.setInteger("Pos1Z", this.Pos1Z);
	    
	    tag.setInteger("Pos2X", this.Pos2X);
	    tag.setInteger("Pos2Y", this.Pos2Y);
	    tag.setInteger("Pos2Z", this.Pos2Z);
	    
	    tag.setInteger("Pos3X", this.Pos3X);
	    tag.setInteger("Pos3Y", this.Pos3Y);
	    tag.setInteger("Pos3Z", this.Pos3Z);
	    
	    tag.setInteger("Pos4X", this.Pos4X);
	    tag.setInteger("Pos4Y", this.Pos4Y);
	    tag.setInteger("Pos4Z", this.Pos4Z);
	    
	    tag.setInteger("Pos5X", this.Pos5X);
	    tag.setInteger("Pos5Y", this.Pos5Y);
	    tag.setInteger("Pos5Z", this.Pos5Z);
	    
	    tag.setInteger("Pos6X", this.Pos6X);
	    tag.setInteger("Pos6Y", this.Pos6Y);
	    tag.setInteger("Pos6Z", this.Pos6Z);
	    
	    tag.setInteger("Pos7X", this.Pos7X);
	    tag.setInteger("Pos7Y", this.Pos7Y);
	    tag.setInteger("Pos7Z", this.Pos7Z);
	    
	    tag.setInteger("Pos8X", this.Pos8X);
	    tag.setInteger("Pos8Y", this.Pos8Y);
	    tag.setInteger("Pos8Z", this.Pos8Z);
	    
	    tag.setInteger("Pos9X", this.Pos9X);
	    tag.setInteger("Pos9Y", this.Pos9Y);
	    tag.setInteger("Pos9Z", this.Pos9Z);
	    
	    tag.setInteger("Pos10X", this.Pos10X);
	    tag.setInteger("Pos10Y", this.Pos10Y);
	    tag.setInteger("Pos10Z", this.Pos10Z);
	    
	    tag.setInteger("Pos11X", this.Pos11X);
	    tag.setInteger("Pos11Y", this.Pos11Y);
	    tag.setInteger("Pos11Z", this.Pos11Z);
	    
	    tag.setInteger("Pos12X", this.Pos12X);
	    tag.setInteger("Pos12Y", this.Pos12Y);
	    tag.setInteger("Pos12Z", this.Pos12Z);
	    
	    tag.setInteger("Pos13X", this.Pos13X);
	    tag.setInteger("Pos13Y", this.Pos13Y);
	    tag.setInteger("Pos13Z", this.Pos13Z);
	    
	    tag.setInteger("Pos14X", this.Pos14X);
	    tag.setInteger("Pos14Y", this.Pos14Y);
	    tag.setInteger("Pos14Z", this.Pos14Z);
	    
	    tag.setInteger("Pos15X", this.Pos15X);
	    tag.setInteger("Pos15Y", this.Pos15Y);
	    tag.setInteger("Pos15Z", this.Pos15Z);
	    
	    tag.setInteger("Pos16X", this.Pos16X);
	    tag.setInteger("Pos16Y", this.Pos16Y);
	    tag.setInteger("Pos16Z", this.Pos16Z);
	      
	    
	    tag.setDouble("energy", this.energy);
	    tag.setBoolean("active", getActive());
	    tag.setByte("redstoneMode", this.redstoneMode);

	  }
	
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction)
	  {
	    return false;
	  }
	
	 public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
	  {
	    return !facingMatchesDirection(direction);
	  }
	
	public void scanForMachines(){		
		  
		TileEntityElectricMachine te = null;
		double energyForUse = 0.0D;
    
	  
	  if(Pos1X != 0 && Pos1Y != 0 && Pos1Z != 0 && this.worldObj.getTileEntity(Pos1X, Pos1Y, Pos1Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos1X, Pos1Y, Pos1Z);
		  	  	  
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;	  
			  
		  }
		  
		  
	  } else {this.Pos1X = 0; this.Pos1Y = 0; this.Pos1Z = 0;}
	  
	  if(Pos2X != 0 && Pos2Y != 0 && Pos2Z != 0 && this.worldObj.getTileEntity(Pos2X, Pos2Y, Pos2Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos2X, Pos2Y, Pos2Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;		  
			  
		  }

		  
	  } else {this.Pos2X = 0; this.Pos2Y = 0; this.Pos2Z = 0;}

	  if(Pos3X != 0 && Pos3Y != 0 && Pos3Z != 0 && this.worldObj.getTileEntity(Pos3X, Pos3Y, Pos3Z) instanceof TileEntityElectricMachine){
	  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos3X, Pos3Y, Pos3Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			  
		  } 
	  
	  } else {this.Pos3X = 0; this.Pos3Y = 0; this.Pos3Z = 0;}

	  if(Pos4X != 0 && Pos4Y != 0 && Pos4Z != 0 && this.worldObj.getTileEntity(Pos4X, Pos4Y, Pos4Z) instanceof TileEntityElectricMachine){
	  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos4X, Pos4Y, Pos4Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			 	  
		  }
		
	  
	  } else {this.Pos4X = 0; this.Pos4Y = 0; this.Pos4Z = 0;}
	  
	  if(Pos5X != 0 && Pos5Y != 0 && Pos5Z != 0 && this.worldObj.getTileEntity(Pos5X, Pos5Y, Pos5Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos5X, Pos5Y, Pos5Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			  	  
		  }

	  
	  } else {this.Pos5X = 0; this.Pos5Y = 0; this.Pos5Z = 0;}
	  
	  if(Pos6X != 0 && Pos6Y != 0 && Pos6Z != 0 && this.worldObj.getTileEntity(Pos6X, Pos6Y, Pos6Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos6X, Pos6Y, Pos6Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			 		  
		  }

	  
	  } else {this.Pos6X = 0; this.Pos6Y = 0; this.Pos6Z = 0;}
	  
	  if(Pos7X != 0 && Pos7Y != 0 && Pos7Z != 0 && this.worldObj.getTileEntity(Pos7X, Pos7Y, Pos7Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos7X, Pos7Y, Pos7Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			 		  
		  }
	
	  
	  } else {this.Pos7X = 0; this.Pos7Y = 0; this.Pos7Z = 0;}
	  
	  if(Pos8X != 0 && Pos8Y != 0 && Pos8Z != 0 && this.worldObj.getTileEntity(Pos8X, Pos8Y, Pos8Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos8X, Pos8Y, Pos8Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			 		  
		  }
		 
	  
	  } else {this.Pos8X = 0; this.Pos8Y = 0; this.Pos8Z = 0;}
	  
	  if(Pos9X != 0 && Pos9Y != 0 && Pos9Z != 0 && this.worldObj.getTileEntity(Pos9X, Pos9Y, Pos9Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos9X, Pos9Y, Pos9Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			  		  
		  }
		  
	  
	  } else {this.Pos9X = 0; this.Pos9Y = 0; this.Pos9Z = 0;}
	  
	  if(Pos10X != 0 && Pos10Y != 0 && Pos10Z != 0 && this.worldObj.getTileEntity(Pos10X, Pos10Y, Pos10Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos10X, Pos10Y, Pos10Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			 		  
		  }
		 
	  
	  } else {this.Pos10X = 0; this.Pos10Y = 0; this.Pos10Z = 0;}
	  
	  if(Pos11X != 0 && Pos11Y != 0 && Pos11Z != 0 && this.worldObj.getTileEntity(Pos11X, Pos11Y, Pos11Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos11X, Pos11Y, Pos11Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			  		  
		  }
		 
	  
	  } else {this.Pos11X = 0; this.Pos11Y = 0; this.Pos11Z = 0;}
	  
	  if(Pos12X != 0 && Pos12Y != 0 && Pos12Z != 0 && this.worldObj.getTileEntity(Pos12X, Pos12Y, Pos12Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos12X, Pos12Y, Pos12Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			  		  
		  }
		  
	  
	  } else {this.Pos12X = 0; this.Pos12Y = 0; this.Pos12Z = 0;}
	  
	  if(Pos13X != 0 && Pos13Y != 0 && Pos13Z != 0 && this.worldObj.getTileEntity(Pos13X, Pos13Y, Pos13Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos13X, Pos13Y, Pos13Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			  			  
		  }
		  
	  
	  } else {this.Pos13X = 0; this.Pos13Y = 0; this.Pos13Z = 0;}
	  
	  if(Pos14X != 0 && Pos14Y != 0 && Pos14Z != 0 && this.worldObj.getTileEntity(Pos14X, Pos14Y, Pos14Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos14X, Pos14Y, Pos14Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			  		  
		  }
		 
	  
	  } else {this.Pos14X = 0; this.Pos14Y = 0; this.Pos14Z = 0;}
	  
	  if(Pos15X != 0 && Pos15Y != 0 && Pos15Z != 0 && this.worldObj.getTileEntity(Pos15X, Pos15Y, Pos15Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos15X, Pos15Y, Pos15Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
						  
		  }
		
	  
	  } else {this.Pos15X = 0; this.Pos15Y = 0; this.Pos15Z = 0;}
	  
	  if(Pos16X != 0 && Pos16Y != 0 && Pos16Z != 0 && this.worldObj.getTileEntity(Pos16X, Pos16Y, Pos16Z) instanceof TileEntityElectricMachine){
		  
		  te = (TileEntityElectricMachine)this.worldObj.getTileEntity(Pos16X, Pos16Y, Pos16Z);
		  energyForUse = te.getDemandedEnergy();
		  
		  
		  if(this.energy >= energyForUse){
			  
			  this.energy -= energyForUse;
			  te.energy += energyForUse;
			 			  
		  }
		 
	  
	  } else {this.Pos16X = 0; this.Pos16Y = 0; this.Pos16Z = 0;}
	  	
	}
	

public int getXPos(int pos){
		
		switch(pos){
			case 1:
				return this.Pos1X;
			case 2:
				return this.Pos2X;
		case 3:
				return this.Pos3X;
		case 4:
				return this.Pos4X;
		case 5:
				return this.Pos5X;
		case 6:
				return this.Pos6X;
		case 7:
				return this.Pos7X;
		case 8:
				return this.Pos8X;
		case 9:
				return this.Pos9X;
		case 10:
				return this.Pos10X;
		case 11:
				return this.Pos11X;
		case 12:
				return this.Pos12X;
		case 13:
				return this.Pos13X;
		case 14:
				return this.Pos14X;
		case 15:
				return this.Pos15X;
		case 16:
				return this.Pos16X;
								
		}
		return pos;		
	}
	
	public int getYPos(int pos){
		
		switch(pos){
		case 1:
				return this.Pos1Y;
		case 2:
				return this.Pos2Y;
		case 3:
				return this.Pos3Y;
		case 4:
				return this.Pos4Y;
		case 5:
				return this.Pos5Y;
		case 6:
				return this.Pos6Y;
		case 7:
				return this.Pos7Y;
		case 8:
				return this.Pos8Y;
		case 9:
				return this.Pos9Y;
		case 10:
				return this.Pos10Y;
		case 11:
				return this.Pos11Y;
		case 12:
				return this.Pos12Y;
		case 13:
				return this.Pos13Y;
		case 14:
				return this.Pos14Y;
		case 15:
				return this.Pos15Y;
		case 16:
				return this.Pos16Y;
									
		}
		return pos;					
	}
	
	public int getZPos(int pos){
		
		switch(pos){
		case 1:
				return this.Pos1Z;
		case 2:
				return this.Pos2Z;
		case 3:
				return this.Pos3Z;
		case 4:
				return this.Pos4Z;
		case 5:
				return this.Pos5Z;
		case 6:
				return this.Pos6Z;
		case 7:
				return this.Pos7Z;
		case 8:
				return this.Pos8Z;
		case 9:
				return this.Pos9Z;
		case 10:
				return this.Pos10Z;
		case 11:
				return this.Pos11Z;
		case 12:
				return this.Pos12Z;
		case 13:
				return this.Pos13Z;
		case 14:
				return this.Pos14Z;
		case 15:
				return this.Pos15Z;
		case 16:
				return this.Pos16Z;
									
		}
		return pos;		
	}	
	

	private boolean isEmittingRedstone = false;
}
