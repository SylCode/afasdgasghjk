package com.thunder.inventory;

import com.thunder.Api.IEnergyAdd;
import com.thunder.Updaters.IEnergyMatrixLow;
import com.thunder.Updaters.IEnergyMatrixStandart;
import com.thunder.Updaters.IEnergyMatrixUltra;
import com.thunder.Updaters.IEnergyUpdater;
import com.thunder.Updaters.UpdatersList;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEnergy extends Slot{
	
	public SlotEnergy(IInventory inventory, int slotIndex, int x, int y)
	{
		super(inventory, slotIndex, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{	
		if (stack == null || !(stack.getItem() instanceof IEnergyAdd))
			return false;
		if ((this.getSlotIndex() == 0 || this.getSlotIndex() == 1 || this.getSlotIndex() == 2 || this.getSlotIndex() == 3) && stack.getItem() instanceof IEnergyUpdater && stack.getItem() instanceof IEnergyAdd)
			return true;
		if (this.getSlotIndex() == 4 && (stack.getItem().equals(UpdatersList.EnergyMatrix) ||  stack.getItem().equals(UpdatersList.EnergyMatrixStandart) || stack.getItem().equals(UpdatersList.EnergyMatrixUltra)) && stack.getItem() instanceof IEnergyAdd)
			return true;
		
		return false;
	}

}
