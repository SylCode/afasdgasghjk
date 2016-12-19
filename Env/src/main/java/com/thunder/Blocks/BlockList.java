package com.thunder.Blocks;

import com.thunder.Items.IAdvancedEnergyChargerItem;
import com.thunder.Items.IEnergyChargerItem;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;

public class BlockList {
	
	public static Block EnergyCharger;
	public static Block AdvancedEnergyCharger;
	public static BlockOre EnergyOre;
	public static Block BlockEnergyOre;
	public static Block BlockLight;
	
	public static void blocklist(){
		
	
	EnergyCharger = new IEnergyCharger().setBlockName("EnergyCharger");
	GameRegistry.registerBlock(EnergyCharger, IEnergyChargerItem.class, "EnergyCharger");
	
	AdvancedEnergyCharger = new IAdvancedEnergyCharger().setBlockName("AdvancedEnergyCharger");
	GameRegistry.registerBlock(AdvancedEnergyCharger, IAdvancedEnergyChargerItem.class, "AdvancedEnergyCharger");

	EnergyOre = (BlockOre) new EnergyOre().setBlockName("EnergyOre");
	GameRegistry.registerBlock(EnergyOre, "EnergyOre");
	
	BlockEnergyOre = new BlockEnergyOre().setBlockName("BlockEnergyOre");
	GameRegistry.registerBlock(BlockEnergyOre, "BlockEnergyOre");
	
	BlockLight = new BlockLight().setBlockName("BlockLight");
	GameRegistry.registerBlock(BlockLight, "BlockLight");
		
	}

}
