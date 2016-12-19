package com.thunder.EnergyAdditions;

import com.thunder.Items.ItemList;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		
		if (fuel != null)
	    {
			if (fuel.getItem().equals(ItemList.EnergyIngot)) {
		        return 16000;
		    }
	    }
		
		
		return 0;
	}

}
