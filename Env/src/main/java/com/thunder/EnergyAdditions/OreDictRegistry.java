package com.thunder.EnergyAdditions;

import com.thunder.Blocks.BlockList;
import com.thunder.Items.ItemList;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictRegistry {
	
	public static void registerOres(){
			
		 OreDictionary.registerOre("blockCharger", BlockList.EnergyCharger);
		 OreDictionary.registerOre("blockAdvancedCharger", BlockList.AdvancedEnergyCharger);
		 OreDictionary.registerOre("blockLight", BlockList.BlockLight);
		 OreDictionary.registerOre("oreEnergy", BlockList.EnergyOre);
		 OreDictionary.registerOre("blockEnergy", BlockList.BlockEnergyOre);
		 OreDictionary.registerOre("plateEnergy", ItemList.EnergyPlate);
		 OreDictionary.registerOre("dustEnergy", ItemList.EnergyDust);
		 OreDictionary.registerOre("ingotEnergy", ItemList.EnergyIngot);
		 OreDictionary.registerOre("nuggetEnergy", ItemList.EnergyNugget);
		 OreDictionary.registerOre("rodEnergy", ItemList.EnergyRod);
		 OreDictionary.registerOre("circuitDiamond", ItemList.DiamondInlaidCircuit);
		 OreDictionary.registerOre("circuitIridium", ItemList.IridiumInlaidCircuit);
		 
		 OreDictionary.registerOre("itemMagnet", ItemList.EnergyShieldMagnet);
		 OreDictionary.registerOre("itemMCoil", ItemList.EnergyShieldMagnetCoil);
		 OreDictionary.registerOre("itemShCore", ItemList.EnergyShieldCore);
		 OreDictionary.registerOre("itemSCore", ItemList.EnergySaberCore);
		 OreDictionary.registerOre("itemMCore", ItemList.ModificatorCore);
		 OreDictionary.registerOre("itemUCore", ItemList.UpdaterCore);
		 OreDictionary.registerOre("itemSTCoreStr", ItemList.EnergyStimulatorCoreStr);
		 OreDictionary.registerOre("itemSTCoreDef", ItemList.EnergyStimulatorCoreDef);
		 OreDictionary.registerOre("itemSTCoreReg", ItemList.EnergyStimulatorCoreReg);
		 OreDictionary.registerOre("itemSTExtrStr", ItemList.EnergyStimulatorExtractStr);
		 OreDictionary.registerOre("itemSTExtrDef", ItemList.EnergyStimulatorExtractDef);
		 OreDictionary.registerOre("itemSTExtrReg", ItemList.EnergyStimulatorExtractReg);
		 OreDictionary.registerOre("itemRComponent", ItemList.NuclearComponent);
		 OreDictionary.registerOre("itemColorRed", ItemList.ColorModificatorRed);
		 OreDictionary.registerOre("itemColorBlue", ItemList.ColorModificatorBlue);
		
	}

}
