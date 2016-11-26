package com.thunder.Blocks;

import com.thunder.Items.IEnergyChargerItem;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;

public class BlockList {
	
	public static Block EnergyCharger;
	
	public static void blocklist(){
		
	
	EnergyCharger = new IEnergyCharger().setBlockName("EnergyCharger");
	GameRegistry.registerBlock(EnergyCharger, IEnergyChargerItem.class, "EnergyCharger");			
		
	}

}
