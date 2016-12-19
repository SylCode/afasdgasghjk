package com.thunder.Blocks;

import java.util.List;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.Ic2Items;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.block.machine.tileentity.TileEntityMacerator;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.block.wiring.TileEntityElectricBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EnergyChargerTileEntity extends ATileEntityElectricBlock {
	
	
	public EnergyChargerTileEntity() {
		super(4, 2048, 40000000);
		
	}

	@Override
	public String getInventoryName() {
		
		return "EnergyCharger";
	}
	
	
	protected void updateEntityServer()
	  {
		
		super.updateEntityServer();
	    
	    boolean needsInvUpdate = false;
	    if ((getDemandedEnergy() > 0.0D) && (!this.dischargeSlot1.isEmpty()))
	    {
	      double gain = this.dischargeSlot1.discharge(this.maxStorage - this.energy, false);
	      
	      this.energy += gain;
	      needsInvUpdate = gain > 0.0D;
	    }
	    if ((this.redstoneMode == 5) || (this.redstoneMode == 6)) {
	      this.hasRedstone = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
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
		
		if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){
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
	
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction)
	  {
	    return false;
	}
	
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
	{
	    return !facingMatchesDirection(direction);
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
	  
	  private boolean isEmittingRedstone = false;
}
