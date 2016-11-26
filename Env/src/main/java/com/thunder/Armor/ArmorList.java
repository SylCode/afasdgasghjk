package com.thunder.Armor;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class ArmorList {
	
	public static ItemArmor JuggerArmorHelmet;
	public static ItemArmor JuggerArmorChestPlate;
	public static ItemArmor JuggerArmorLeggings;
	public static ItemArmor JuggerArmorBoots;
	
	public static ItemArmor DefenderArmorHelmet;
	public static ItemArmor DefenderArmorChestPlate;
	public static ItemArmor DefenderArmorLeggings;
	public static ItemArmor DefenderArmorBoots;
	
	public static ItemArmor AgilityArmorHelmet;
	public static ItemArmor AgilityArmorChestPlate;
	public static ItemArmor AgilityArmorLeggings;
	public static ItemArmor AgilityArmorBoots;
	

	public static ItemArmor EnergyArmorHelmet;
	public static ItemArmor EnergyArmorChestPlate;
	public static ItemArmor EnergyArmorLeggings;
	public static ItemArmor EnergyArmorBoots;
	
	public static ItemArmor ModArmorHelmet;
	public static ItemArmor ModArmorChestPlate;
	public static ItemArmor ModArmorLeggings;
	public static ItemArmor ModArmorBoots;
	
	
	
	public static void init(){
		
		EnergyArmorHelmet = (ItemArmor) new IEnergyArmor(ArmorMaterial.DIAMOND, 3, 0).setUnlocalizedName("EnergyArmorHelmet");
		GameRegistry.registerItem(EnergyArmorHelmet, "EnergyArmorHelmet");
		
		EnergyArmorChestPlate = (ItemArmor) new IEnergyArmor(ArmorMaterial.DIAMOND, 3, 1).setUnlocalizedName("EnergyArmorChestPlate");
		GameRegistry.registerItem(EnergyArmorChestPlate, "EnergyArmorChestPlate");
		
		EnergyArmorLeggings = (ItemArmor) new IEnergyArmor(ArmorMaterial.DIAMOND, 3, 2).setUnlocalizedName("EnergyArmorLeggings");
		GameRegistry.registerItem(EnergyArmorLeggings, "EnergyArmorLeggings");
		
		EnergyArmorBoots = (ItemArmor) new IEnergyArmor(ArmorMaterial.DIAMOND, 3,3).setUnlocalizedName("EnergyArmorBoots");
		GameRegistry.registerItem(EnergyArmorBoots, "EnergyArmorBoots");
		
		
		
		ModArmorHelmet = (ItemArmor) new IModArmor(ArmorMaterial.DIAMOND, 3, 0).setUnlocalizedName("ModArmorHelmet");
		GameRegistry.registerItem(ModArmorHelmet, "ModArmorHelmet");
		
		ModArmorChestPlate = (ItemArmor) new IModArmor(ArmorMaterial.DIAMOND, 3, 1).setUnlocalizedName("ModArmorChestPlate");
		GameRegistry.registerItem(ModArmorChestPlate, "ModArmorChestPlate");
		
		ModArmorLeggings = (ItemArmor) new IModArmor(ArmorMaterial.DIAMOND, 3, 2).setUnlocalizedName("ModArmorLeggings");
		GameRegistry.registerItem(ModArmorLeggings, "ModArmorLeggings");
		
		ModArmorBoots = (ItemArmor) new IModArmor(ArmorMaterial.DIAMOND, 3,3).setUnlocalizedName("ModArmorBoots");
		GameRegistry.registerItem(ModArmorBoots, "ModArmorBoots");
		
		
		//armor
		
		//juggernaut
		JuggerArmorHelmet = (ItemArmor) new IJuggerArmor(EnergyAdditionsCore.JuggerArmorM, 3, 0).setUnlocalizedName("JuggerArmorHelmet");
		GameRegistry.registerItem(JuggerArmorHelmet, "JuggerArmorHelmet");
				
		JuggerArmorChestPlate = (ItemArmor) new IJuggerArmor(EnergyAdditionsCore.JuggerArmorM, 3, 1).setUnlocalizedName("JuggerArmorChestPlate");
		GameRegistry.registerItem(JuggerArmorChestPlate, "JuggerArmorChestPlate");
				
		JuggerArmorLeggings = (ItemArmor) new IJuggerArmor(EnergyAdditionsCore.JuggerArmorM, 3, 2).setUnlocalizedName("JuggerArmorLeggings");
		GameRegistry.registerItem(JuggerArmorLeggings, "JuggerArmorLeggings");
				
		JuggerArmorBoots = (ItemArmor) new IJuggerArmor(EnergyAdditionsCore.JuggerArmorM, 3,3).setUnlocalizedName("JuggerArmorBoots");
		GameRegistry.registerItem(JuggerArmorBoots, "JuggerArmorBoots");
				
				//defender
		DefenderArmorHelmet = (ItemArmor) new IDefenderArmor(EnergyAdditionsCore.DefenderArmorM, 3, 0).setUnlocalizedName("DefenderArmorHelmet");
		GameRegistry.registerItem(DefenderArmorHelmet, "DefenderArmorHelmet");
				
		DefenderArmorChestPlate = (ItemArmor) new IDefenderArmor(EnergyAdditionsCore.DefenderArmorM, 3, 1).setUnlocalizedName("DefenderArmorChestPlate");
		GameRegistry.registerItem(DefenderArmorChestPlate, "DefenderArmorChestPlate");
				
		DefenderArmorLeggings = (ItemArmor) new IDefenderArmor(EnergyAdditionsCore.DefenderArmorM, 3, 2).setUnlocalizedName("DefenderArmorLeggings");
		GameRegistry.registerItem(DefenderArmorLeggings, "DefenderArmorLeggings");
				
		DefenderArmorBoots = (ItemArmor) new IDefenderArmor(EnergyAdditionsCore.DefenderArmorM, 3,3).setUnlocalizedName("DefenderArmorBoots");
		GameRegistry.registerItem(DefenderArmorBoots, "DefenderArmorBoots");
				
				//agility
		AgilityArmorHelmet = (ItemArmor) new IAgilityArmor(EnergyAdditionsCore.AgilityArmorM, 3, 0).setUnlocalizedName("AgilityArmorHelmet");
		GameRegistry.registerItem(AgilityArmorHelmet, "AgilityArmorHelmet");
				
		AgilityArmorChestPlate = (ItemArmor) new IAgilityArmor(EnergyAdditionsCore.AgilityArmorM, 3, 1).setUnlocalizedName("AgilityArmorChestPlate");
		GameRegistry.registerItem(AgilityArmorChestPlate, "AgilityArmorChestPlate");
				
		AgilityArmorLeggings = (ItemArmor) new IAgilityArmor(EnergyAdditionsCore.AgilityArmorM, 3, 2).setUnlocalizedName("AgilityArmorLeggings");
		GameRegistry.registerItem(AgilityArmorLeggings, "AgilityArmorLeggings");
				
		AgilityArmorBoots = (ItemArmor) new IAgilityArmor(EnergyAdditionsCore.AgilityArmorM, 3,3).setUnlocalizedName("AgilityArmorBoots");
		GameRegistry.registerItem(AgilityArmorBoots, "AgilityArmorBoots");
				
	}

}
