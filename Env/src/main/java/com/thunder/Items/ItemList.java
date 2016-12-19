package com.thunder.Items;

import com.thunder.Blocks.BlockList;
import com.thunder.Items.ElectricToolBase.HarvestLevel;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemList {
	
	public static Item DiamondInlaidCircuit;
	public static Item IridiumInlaidCircuit;
	
	public static Item EnergyStimulatorExtractStr;
	public static Item EnergyStimulatorCoreStr;
	public static Item EnergyStimulatorStr1;
	public static Item EnergyStimulatorStr2;
	public static Item EnergyStimulatorStr3;
	
	public static Item EnergyStimulatorExtractReg;
	public static Item EnergyStimulatorCoreReg;
	public static Item EnergyStimulatorReg1;
	public static Item EnergyStimulatorReg2;
	public static Item EnergyStimulatorReg3;
	
	public static Item EnergyStimulatorExtractDef;
	public static Item EnergyStimulatorCoreDef;
	public static Item EnergyStimulatorDef1;
	public static Item EnergyStimulatorDef2;
	public static Item EnergyStimulatorDef3;
	
	public static Item UltimateStimulator1;
	public static Item UltimateStimulator2;
	public static Item UltimateStimulator3;
	
	public static Item EnergyShieldCore;
	public static Item EnergyShieldMagnet;
	public static Item EnergyShieldMagnetCoil;
	
	public static Item EnergyShield1;
	public static Item EnergyShield2;
	public static Item EnergyShield3;
	
	public static Item EnergyBar;
	
	public static Item EnergySaber;
	public static Item EnergySaberRed;
	
	public static Item PoisonModule;
	public static Item StunModule;
	public static Item WeakModule;
	public static Item HealModule;
	
	public static Item EnergySaberCore;
	public static Item ModificatorCore;
	public static Item NuclearComponent;
	public static Item ColorModificatorBlue;
	public static Item ColorModificatorRed;
	
	public static Item UpdaterCore;
	
	public static Item EnergyMicrox;
	public static Item UltimateEnergyCrystal;
	
	public static Item EnergyIngot;
	public static Item EnergyRod;
	public static Item EnergyPlate;
	public static Item EnergyNugget;
	public static Item EnergyDust;
	
	public static Item EnergyBinder;
	
	public static void items(){
		
		//energy binder
		
		EnergyBinder = new IEnergyBinder().setUnlocalizedName("EnergyBinder");
	    GameRegistry.registerItem(EnergyBinder, "EnergyBinder");
		
		//energy ingot, nugget, plate, rod
		EnergyIngot = new IEnergyIngot().setUnlocalizedName("EnergyIngot");
	    GameRegistry.registerItem(EnergyIngot, "EnergyIngot");
	    
	    EnergyDust = new IEnergyDust().setUnlocalizedName("EnergyDust");
	    GameRegistry.registerItem(EnergyDust, "EnergyDust");
	    
	    EnergyRod = new IEnergyRod().setUnlocalizedName("EnergyRod");
	    GameRegistry.registerItem(EnergyRod, "EnergyRod");
	    
	    EnergyPlate = new IEnergyPlate().setUnlocalizedName("EnergyPlate");
	    GameRegistry.registerItem(EnergyPlate, "EnergyPlate");
	    
	    EnergyNugget = new IEnergyNugget().setUnlocalizedName("EnergyNugget");
	    GameRegistry.registerItem(EnergyNugget, "EnergyNugget");
	
		//Circuits
		DiamondInlaidCircuit = new IDiamondInlaidCircuit().setUnlocalizedName("DiamondInlaidCircuit");
	    GameRegistry.registerItem(DiamondInlaidCircuit, "DiamondInlaidCircuit");
	    
	    IridiumInlaidCircuit = new IIridiumInlaidCircuit().setUnlocalizedName("IridiumInlaidCircuit");
	    GameRegistry.registerItem(IridiumInlaidCircuit, "IridiumInlaidCircuit");
	    
	    //EnergyShieldParts
	    EnergyShieldMagnet = new IEnergyShieldMagnet().setUnlocalizedName("EnergyShieldMagnet");
	    GameRegistry.registerItem(EnergyShieldMagnet, "EnergyShieldMagnet");
	    
	    EnergyShieldMagnetCoil = new IEnergyShieldMagnetCoil().setUnlocalizedName("EnergyShieldMagnetCoil");
	    GameRegistry.registerItem(EnergyShieldMagnetCoil, "EnergyShieldMagnetCoil");
	    
	    EnergyShieldCore = new IEnergyShieldCore().setUnlocalizedName("EnergyShieldCore");
	    GameRegistry.registerItem(EnergyShieldCore, "EnergyShieldCore");
	    
	    //modules and saber components
	    EnergySaberCore = new IEnergySaberCore().setUnlocalizedName("EnergySaberCore");
	    GameRegistry.registerItem(EnergySaberCore, "EnergySaberCore");
	    
	    ModificatorCore = new IModificatorCore().setUnlocalizedName("ModificatorCore");
	    GameRegistry.registerItem(ModificatorCore, "ModificatorCore");
	    
	    UpdaterCore = new IUpdaterCore().setUnlocalizedName("UpdaterCore");
	    GameRegistry.registerItem(UpdaterCore, "UpdaterCore");
	    
	    NuclearComponent = new INuclearComponent(120, 1).setUnlocalizedName("NuclearComponent");
	    GameRegistry.registerItem(NuclearComponent, "NuclearComponent");
	    
	    ColorModificatorBlue = new IColorModificatorBlue().setUnlocalizedName("ColorModificatorBlue");
	    GameRegistry.registerItem(ColorModificatorBlue, "ColorModificatorBlue");
	    
	    ColorModificatorRed = new IColorModificatorRed().setUnlocalizedName("ColorModificatorRed");
	    GameRegistry.registerItem(ColorModificatorRed, "ColorModificatorRed");
	    
	    //energyMicrox
	    
	    EnergyMicrox = new IEnergyMicrox().setUnlocalizedName("EnergyMicrox");
	    GameRegistry.registerItem(EnergyMicrox, "EnergyMicrox");
	    
	    //ultimate energy crystal
	    
	    UltimateEnergyCrystal = new IUltimateEnergyCrystal().setUnlocalizedName("UltimateEnergyCrystal");
	    GameRegistry.registerItem(UltimateEnergyCrystal, "UltimateEnergyCrystal");
	    
	    
	    //saber
	    
		//saber and modules
	    PoisonModule = new IPoisonModule().setUnlocalizedName("PoisonModule");
	    GameRegistry.registerItem(PoisonModule, "PoisonModule");
	    
	    StunModule = new IStunModule().setUnlocalizedName("StunModule");
	    GameRegistry.registerItem(StunModule, "StunModule");
	    
	    WeakModule = new IWeakModule().setUnlocalizedName("WeakModule");
	    GameRegistry.registerItem(WeakModule, "WeakModule");
	    
	    HealModule = new IHealModule().setUnlocalizedName("HealModule");
	    GameRegistry.registerItem(HealModule, "HealModule");
	        
	    EnergySaber = new IEnergySaber().setUnlocalizedName("EnergySaber");
	    GameRegistry.registerItem(EnergySaber, "EnergySaber");
	    
	    EnergySaberRed = new IEnergySaberRed().setUnlocalizedName("EnergySaberRed");
	    GameRegistry.registerItem(EnergySaberRed, "EnergySaberRed");
	    
	    //Strength
	    EnergyStimulatorExtractStr = new IEnergyStimulatorExtractStr().setUnlocalizedName("EnergyStimulatorExtractStr");
	    GameRegistry.registerItem(EnergyStimulatorExtractStr, "EnergyStimulatorExtractStr");
	    
	    EnergyStimulatorCoreStr = new IEnergyStimulatorCoreStr().setUnlocalizedName("EnergyStimulatorCoreStr");
	    GameRegistry.registerItem(EnergyStimulatorCoreStr, "EnergyStimulatorCoreStr");
	        
		EnergyStimulatorStr1 = new IEnergyStimulatorStr1().setUnlocalizedName("EnergyStimulatorStr1");
	    GameRegistry.registerItem(EnergyStimulatorStr1, "EnergyStimulatorStr1");
		
	    EnergyStimulatorStr2 = new IEnergyStimulatorStr2().setUnlocalizedName("EnergyStimulatorStr2");
	    GameRegistry.registerItem(EnergyStimulatorStr2, "EnergyStimulatorStr2");
		
	    EnergyStimulatorStr3 = new IEnergyStimulatorStr3().setUnlocalizedName("EnergyStimulatorStr3");
	    GameRegistry.registerItem(EnergyStimulatorStr3, "EnergyStimulatorStr3");
	    
	    //Regeneration
	    EnergyStimulatorExtractReg = new IEnergyStimulatorExtractReg().setUnlocalizedName("EnergyStimulatorExtractReg");
	    GameRegistry.registerItem(EnergyStimulatorExtractReg, "EnergyStimulatorExtractReg");
	    
	    EnergyStimulatorCoreReg = new IEnergyStimulatorCoreReg().setUnlocalizedName("EnergyStimulatorCoreReg");
	    GameRegistry.registerItem(EnergyStimulatorCoreReg, "EnergyStimulatorCoreReg");
	        
	    EnergyStimulatorReg1 = new IEnergyStimulatorReg1().setUnlocalizedName("EnergyStimulatorReg1");
	    GameRegistry.registerItem(EnergyStimulatorReg1, "EnergyStimulatorReg1");
		
	    EnergyStimulatorReg2 = new IEnergyStimulatorReg2().setUnlocalizedName("EnergyStimulatorReg2");
	    GameRegistry.registerItem(EnergyStimulatorReg2, "EnergyStimulatorReg2");
	    
	    EnergyStimulatorReg3 = new IEnergyStimulatorReg3().setUnlocalizedName("EnergyStimulatorReg3");
	    GameRegistry.registerItem(EnergyStimulatorReg3, "EnergyStimulatorReg3");
	    
	    //Defence
	    EnergyStimulatorExtractDef = new IEnergyStimulatorExtractDef().setUnlocalizedName("EnergyStimulatorExtractDef");
	    GameRegistry.registerItem(EnergyStimulatorExtractDef, "EnergyStimulatorExtractDef");
	    
	    EnergyStimulatorCoreDef = new IEnergyStimulatorCoreDef().setUnlocalizedName("EnergyStimulatorCoreDef");
	    GameRegistry.registerItem(EnergyStimulatorCoreDef, "EnergyStimulatorCoreDef");
	        
	    EnergyStimulatorDef1 = new IEnergyStimulatorDef1().setUnlocalizedName("EnergyStimulatorDef1");
	    GameRegistry.registerItem(EnergyStimulatorDef1, "EnergyStimulatorDef1");
		
	    EnergyStimulatorDef2 = new IEnergyStimulatorDef2().setUnlocalizedName("EnergyStimulatorDef2");
	    GameRegistry.registerItem(EnergyStimulatorDef2, "EnergyStimulatorDef2");
	    
	    EnergyStimulatorDef3 = new IEnergyStimulatorDef3().setUnlocalizedName("EnergyStimulatorDef3");
	    GameRegistry.registerItem(EnergyStimulatorDef3, "EnergyStimulatorDef3");
	    
	    //Ultimate
	    
	    UltimateStimulator1 = new IUltimateStimulator1().setUnlocalizedName("UltimateStimulator1");
	    GameRegistry.registerItem(UltimateStimulator1, "UltimateStimulator1");
	    
	    UltimateStimulator2 = new IUltimateStimulator2().setUnlocalizedName("UltimateStimulator2");
	    GameRegistry.registerItem(UltimateStimulator2, "UltimateStimulator2");
	    
	    UltimateStimulator3 = new IUltimateStimulator3().setUnlocalizedName("UltimateStimulator3");
	    GameRegistry.registerItem(UltimateStimulator3, "UltimateStimulator3");
	    
	    //Shields
	    EnergyShield1 = new IEnergyShield1().setUnlocalizedName("EnergyShield1");
	    GameRegistry.registerItem(EnergyShield1, "EnergyShield1");
	    
	    EnergyShield2 = new IEnergyShield2().setUnlocalizedName("EnergyShield2");
	    GameRegistry.registerItem(EnergyShield2, "EnergyShield2");
    
	    EnergyShield3 = new IEnergyShield3().setUnlocalizedName("EnergyShield3");
	    GameRegistry.registerItem(EnergyShield3, "EnergyShield3");
	    
	    //EnergyBar
	    
	    EnergyBar = new IEnergyBar().setUnlocalizedName("EnergyBar");
	    GameRegistry.registerItem(EnergyBar, "EnergyBar");
	    
		
	}

}
