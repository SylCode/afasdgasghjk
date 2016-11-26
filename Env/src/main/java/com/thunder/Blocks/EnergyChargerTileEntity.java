package com.thunder.Blocks;

import java.util.List;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.Ic2Items;
import ic2.core.block.wiring.TileEntityElectricBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EnergyChargerTileEntity extends TileEntityElectricBlock {
	
public EnergyChargerTileEntity() {
		super(4, 2048, 40000000);
		
	}

	@Override
	public String getInventoryName() {
		
		return "EnergyCharger";
	}
	
	protected void updateEntityServer()
	  {
		
		 List list = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((float) (xCoord -9), (float) (yCoord - 11), (float) (zCoord - 9), (float) (xCoord + 11), (float) (yCoord + 11), (float) (zCoord + 11)));
		 for(int i=0; i < list.size(); i++)
         {
              Entity e = (Entity) list.get(i);
              if(e instanceof EntityPlayer){
            	  getItems((EntityPlayer) e);
              }
         }
		
	  }
		
	protected void getItems(EntityPlayer player)
	  {
	    if (player != null)
	    {
	      for (ItemStack current : player.inventory.armorInventory) {
	        if (current != null) {
	          chargeitems(current, 8192);
	        }
	      }
	      for (ItemStack current : player.inventory.mainInventory) {
	        if (current != null) {
	          chargeitems(current, 8192);
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
}
