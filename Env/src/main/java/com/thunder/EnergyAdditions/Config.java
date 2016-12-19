package com.thunder.EnergyAdditions;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {
	
	//
	private static final String StringCraft = "Crafting(Enable Craft = true, Disable Craft = false)";
	private static final String StringStim = "Stimulators options";
	private static final String StringShield1 = "Shields options (level 1)";
	private static final String StringShield2 = "Shields options (level 2)";
	private static final String StringShield3 = "Shields options (level 3)";
	private static final String StringEnergyBar = "EnergyBar options";
	private static final String StringOther = "Other options";
	
	private static final String StringSaber = "Saber options";
	
	private static final String StringDef = "Def armor options";
	private static final String StringAg = "Agility armor options";
	private static final String StringJug = "Juggernaut armor options";
	
	private static final String StringModules = "Module options";
	
	private static final String StringArmor = "EnergyArmor options";
	private static final String StringModArmor = "ModArmor options";
	
	//MODWIKISECTION---------------------------------------------------------------------------------------------------------
	
	//ENDSECTION-------------------------------------------------------------------------------------------------------------
	
	public static Boolean IridiumRemove;
	public static Boolean SomeUpdatersSpawning;
	public static Boolean SomeUpdatersCraft;
	public static Boolean EnergyOreSpawn;
	public static Boolean CraftsWithEnergyOre;
	
	public static Boolean UltimateEnergyCraft;
	
	public static Boolean EnergyChargerCraft;
	public static Boolean AdvancedEnergyChargerCraft;
	public static Boolean EnergyBinderCraft;
	public static Boolean UpdatersCraft;
	public static Boolean MatrixCraft;
	public static Boolean UpdaterCoreCraft;
	
	public static Boolean ArmorCraft;
	public static Boolean ModArmorCraft;
	
	public static Boolean DiamondCircuit;
	public static Boolean IridiumCircuit;
	
	public static Boolean EnergySaberCore;
	public static Boolean ModificatorCore;
	public static Boolean NuclearComponent;
	public static Boolean ColorModificatorBlue;
	public static Boolean ColorModificatorRed;
	
	public static Boolean ModulePoisonC;
	public static Boolean ModuleStunC;
	public static Boolean ModuleWeakC;
	public static Boolean ModuleHealC;
	
	public static Boolean EnergySaberCraft;
	public static Boolean SaberModuleCraft;
	
	public static Boolean NuclearComponentRadioactive;
	
	public static Boolean ExtractStrength;
	public static Boolean ExtractDefence;
	public static Boolean ExtractRegeneration;
	
	public static Boolean StrengthCore;
	public static Boolean DefenceCore;
	public static Boolean RegenerationCore;
	
	public static Boolean StimulatorStrengthLvl1;
	public static Boolean StimulatorDefenceLvl1;
	public static Boolean StimulatorRegenerationLvl1;
	
	public static Boolean StimulatorStrengthLvl2;
	public static Boolean StimulatorDefenceLvl2;
	public static Boolean StimulatorRegenerationLvl2;
	
	public static Boolean StimulatorStrengthLvl3;
	public static Boolean StimulatorDefenceLvl3;
	public static Boolean StimulatorRegenerationLvl3;
	
	public static Boolean UltimateStimulatorLvl1;
	public static Boolean UltimateStimulatorLvl2;
	public static Boolean UltimateStimulatorLvl3;
	
	public static Boolean EnergyShieldMagnet;
	public static Boolean EnergyShieldMagnetCoil;
	public static Boolean EnergyShieldCore;
	
	public static Boolean EnergyShieldLvl1;
	public static Boolean EnergyShieldLvl2;
	public static Boolean EnergyShieldLvl3;
	
	public static Boolean EnergyBar;
	//
	//Options stimulators
	//str
	public static int StrStimLvl1maxCharge;
	public static int StrStimLvl2maxCharge;
	public static int StrStimLvl3maxCharge;
	
	public static int StrStimLvl1TransferLimit;
	public static int StrStimLvl2TransferLimit;
	public static int StrStimLvl3TransferLimit;
	
	public static int StrStimLvl1Tier;
	public static int StrStimLvl2Tier;
	public static int StrStimLvl3Tier;
	
	public static double StrStimLvl1ChargeToUse;
	public static double StrStimLvl2ChargeToUse;
	public static double StrStimLvl3ChargeToUse;
	
	public static int StrStimLvl1EffectPower;
	public static int StrStimLvl2EffectPower;
	public static int StrStimLvl3EffectPower;
	
	public static int StrStimLvl1EffectDuration;
	public static int StrStimLvl2EffectDuration;
	public static int StrStimLvl3EffectDuration;
	//reg
	public static int RegStimLvl1maxCharge;
	public static int RegStimLvl2maxCharge;
	public static int RegStimLvl3maxCharge;
	
	public static int RegStimLvl1TransferLimit;
	public static int RegStimLvl2TransferLimit;
	public static int RegStimLvl3TransferLimit;
	
	public static int RegStimLvl1Tier;
	public static int RegStimLvl2Tier;
	public static int RegStimLvl3Tier;
	
	public static double RegStimLvl1ChargeToUse;
	public static double RegStimLvl2ChargeToUse;
	public static double RegStimLvl3ChargeToUse;
	
	public static int RegStimLvl1EffectPower;
	public static int RegStimLvl2EffectPower;
	public static int RegStimLvl3EffectPower;
	
	public static int RegStimLvl1EffectDuration;
	public static int RegStimLvl2EffectDuration;
	public static int RegStimLvl3EffectDuration;
	//def
	public static int DefStimLvl1maxCharge;
	public static int DefStimLvl2maxCharge;
	public static int DefStimLvl3maxCharge;
	
	public static int DefStimLvl1TransferLimit;
	public static int DefStimLvl2TransferLimit;
	public static int DefStimLvl3TransferLimit;
	
	public static int DefStimLvl1Tier;
	public static int DefStimLvl2Tier;
	public static int DefStimLvl3Tier;
	
	public static double DefStimLvl1ChargeToUse;
	public static double DefStimLvl2ChargeToUse;
	public static double DefStimLvl3ChargeToUse;
	
	public static int DefStimLvl1EffectPower;
	public static int DefStimLvl2EffectPower;
	public static int DefStimLvl3EffectPower;
	
	public static int DefStimLvl1EffectDuration;
	public static int DefStimLvl2EffectDuration;
	public static int DefStimLvl3EffectDuration;
	//Ult
	public static int UltStimLvl1maxCharge;
	public static int UltStimLvl2maxCharge;
	public static int UltStimLvl3maxCharge;
	
	public static int UltStimLvl1TransferLimit;
	public static int UltStimLvl2TransferLimit;
	public static int UltStimLvl3TransferLimit;
	
	public static int UltStimLvl1Tier;
	public static int UltStimLvl2Tier;
	public static int UltStimLvl3Tier;
	
	public static double UltStimLvl1ChargeToUse;
	public static double UltStimLvl2ChargeToUse;
	public static double UltStimLvl3ChargeToUse;
	
	public static int UltStimLvl1EffectStrengthPower;
	public static int UltStimLvl2EffectStrengthPower;
	public static int UltStimLvl3EffectStrengthPower;
	
	public static int UltStimLvl1EffectRegenerationPower;
	public static int UltStimLvl2EffectRegenerationPower;
	public static int UltStimLvl3EffectRegenerationPower;
	
	public static int UltStimLvl1EffectDefencePower;
	public static int UltStimLvl2EffectDefencePower;
	public static int UltStimLvl3EffectDefencePower;
	
	public static int UltStimLvl1EffectStrengthDuration;
	public static int UltStimLvl2EffectStrengthDuration;
	public static int UltStimLvl3EffectStrengthDuration;
	
	public static int UltStimLvl1EffectRegenerationDuration;
	public static int UltStimLvl2EffectRegenerationDuration;
	public static int UltStimLvl3EffectRegenerationDuration;
	
	public static int UltStimLvl1EffectDefenceDuration;
	public static int UltStimLvl2EffectDefenceDuration;
	public static int UltStimLvl3EffectDefenceDuration;
	//shields
	
	public static int ShLvl1maxCharge;
	public static int ShLvl2maxCharge;
	public static int ShLvl3maxCharge;
	
	public static int ShLvl1TransferLimit;
	public static int ShLvl2TransferLimit;
	public static int ShLvl3TransferLimit;
	
	public static int ShLvl1Tier;
	public static int ShLvl2Tier;
	public static int ShLvl3Tier;
	
	public static double ShLvl1ChargeToUse;
	public static double ShLvl2ChargeToUse;
	public static double ShLvl3ChargeToUse;
	
	public static float ShLvl1AbsorptionAmount;
	public static float ShLvl2AbsorptionAmount;
	public static float ShLvl3AbsorptionAmount;

	public static double ShLvl1ChargeToUseNohand;
	public static double ShLvl2ChargeToUseNohand;
	public static double ShLvl3ChargeToUseNohand;
	
//	public static Boolean ShLvl1DamageTypeExplosion;
	public static Boolean ShLvl1DamageTypeProjectile;
	public static Boolean ShLvl1DamageTypeFireDamage;
//	public static Boolean ShLvl1DamageTypeMagicDamage;
//	public static Boolean ShLvl1DamageTypeDamageAbsolute;
//	public static Boolean ShLvl1DamageTypeCactus;
	public static Boolean ShLvl1DamageTypeFall;
//	public static Boolean ShLvl1DamageTypeLava;
//	public static Boolean ShLvl1DamageTypeWall;
//	public static Boolean ShLvl1DamageTypeWither;
//	public static Boolean ShLvl1DamageTypeFallingBlock;
//	public static Boolean ShLvl1DamageTypeAnvil;
//	public static Boolean ShLvl1DamageTypeMob;
//	public static Boolean ShLvl1DamageTypePlayer;
//	public static Boolean ShLvl1DamageTypeArrow;
	
	public static Boolean ShLvl2DamageTypeExplosion;
	public static Boolean ShLvl2DamageTypeProjectile;
	public static Boolean ShLvl2DamageTypeFireDamage;
	public static Boolean ShLvl2DamageTypeMagicDamage;
	public static Boolean ShLvl2DamageTypeDamageAbsolute;
	public static Boolean ShLvl2DamageTypeCactus;
	public static Boolean ShLvl2DamageTypeFall;
//	public static Boolean ShLvl2DamageTypeLava;
//	public static Boolean ShLvl2DamageTypeWall;
	public static Boolean ShLvl2DamageTypeWither;
//	public static Boolean ShLvl2DamageTypeFallingBlock;
//	public static Boolean ShLvl2DamageTypeAnvil;
//	public static Boolean ShLvl2DamageTypeMob;
//	public static Boolean ShLvl2DamageTypePlayer;
//	public static Boolean ShLvl2DamageTypeArrow;
	
	public static Boolean ShLvl3DamageTypeExplosion;
	public static Boolean ShLvl3DamageTypeProjectile;
	public static Boolean ShLvl3DamageTypeFireDamage;
	public static Boolean ShLvl3DamageTypeMagicDamage;
	public static Boolean ShLvl3DamageTypeDamageAbsolute;
	public static Boolean ShLvl3DamageTypeCactus;
	public static Boolean ShLvl3DamageTypeFall;
//	public static Boolean ShLvl3DamageTypeLava;
	public static Boolean ShLvl3DamageTypeWall;
	public static Boolean ShLvl3DamageTypeWither;
	public static Boolean ShLvl3DamageTypeFallingBlock;
	public static Boolean ShLvl3DamageTypeAnvil;
	public static Boolean ShLvl3DamageTypeMob;
	public static Boolean ShLvl3DamageTypePlayer;
//	public static Boolean ShLvl3DamageTypeArrow;
	
	public static Boolean ShLvl1EnableDischaringNoInHand;
	public static Boolean ShLvl2EnableDischaringNoInHand;
	public static Boolean ShLvl3EnableDischaringNoInHand;
	//Energybar
	public static int EnergyBarmaxCharge;
	public static int EnergyBarTransferLimit;
	public static int EnergyBarTier;
	public static double EnergyBarUsesCharge;
	public static int EnergyBarFoodPoints;
	//
	
	//saber
	public static int SMaxCharge;
	public static int STier;
	public static int STransferLimit;
	public static int SMaxDmg;
	public static int SMinDmg;
	public static double SNanoDischarge;
	public static double SQuantDischarge;
	public static double SDrainOnUpdate;
	public static Boolean SPoisonModule;
	public static Boolean SWeakModule;
	public static Boolean SStunModule;
	public static Boolean SHealModule;
	public static double SPoisonModuleCharge;
	public static double SWeakModuleCharge;
	public static double SStunModuleCharge;
	public static double SHealModuleCharge;
	public static float SHealModuleHealAmount;
	
	//modules
	public static int PModuleMaxCharge;
	public static int PModuleTier;
	public static int PModuleTransferLimit;
	public static double PModuleDrain;
	
	public static int SModuleMaxCharge;
	public static int SModuleTier;
	public static int SModuleTransferLimit;
	public static double SModuleDrain;
	
	public static int WModuleMaxCharge;
	public static int WModuleTier;
	public static int WModuleTransferLimit;
	public static double WModuleDrain;
	
	public static int HModuleMaxCharge;
	public static int HModuleTier;
	public static int HModuleTransferLimit;
	public static double HModuleDrain;
	
	//armor
	public static int ArmorMaxCharge;
	public static int ArmorTier;
	public static int ArmorTransferLimit;
	public static int ArmorEnergyPerDamage;
	public static double ArmorAbsorbtionRatio;
	
	//mod armor
	public static int ModArmorMaxCharge;
	public static int ModArmorTier;
	public static int ModArmorTransferLimit;
	public static int ModArmorEnergyPerDamage;
	public static double ModArmorAbsorbtionRatio;
	
	//otherarmoroptions
	
	//defenderarmor
		public static double defMaxHealth;
		public static Boolean defConstDamage;
		public static int defDurabPerDamage;
		public static double defDamageAbsorptionRatio;
		public static int defArmorHelmetDurability;
		public static int defArmorChestPlateDurability;
		public static int defArmorLeggingsDurability;
		public static int defArmorBootsDurability;
		//agilityarmor
		public static int AgPotionPowerSpeed;
		public static int AgPotionPowerJump;
		public static Boolean AgFallDamage;
		public static Boolean AgConstDamage;
		public static int AgDurabPerDamage;
		public static double AgDamageAbsorptionRatio;
		public static int AgArmorHelmetDurability;
		public static int AgArmorChestPlateDurability;
		public static int AgArmorLeggingsDurability;
		public static int AgArmorBootsDurability;
		//JuggerArmor
		public static int JugPotionPowerStrength;
		public static Boolean JugConstDamage;
		public static int JugDurabPerDamage;
		public static double JugDamageAbsorptionRatio;
		public static int JugArmorHelmetDurability;
		public static int JugArmorChestPlateDurability;
		public static int JugArmorLeggingsDurability;
		public static int JugArmorBootsDurability;
	
		
	public static void load(){
		
		Configuration config = EnergyAdditionsCore.getConfig();
		config.load();
		
		//
		ConfigCategory Crafting;		
		ConfigCategory OptionsStimLvl1;
		ConfigCategory OptionsShieldsLvl1;
		ConfigCategory OptionsShieldsLvl2;
		ConfigCategory OptionsShieldsLvl3;
		ConfigCategory OptionsEnergyBar;
		ConfigCategory OptionsOther;
		
		ConfigCategory OptionsAg;
		ConfigCategory OptionsJug;
		ConfigCategory OptionsDef;
		
		ConfigCategory OptionsSaber;
		
		ConfigCategory OptionsModule;
		ConfigCategory Info;
		ConfigCategory Armor;
		ConfigCategory ModArmor;
		

		Property property;
		
		//otherarmor===============
		
		OptionsAg = config.getCategory(StringAg);
		//
		property = config.get(OptionsAg.getName(), "SpeedBoostPower", 7);
		AgPotionPowerSpeed = property.getInt(7);
		property = config.get(OptionsAg.getName(), "JumpBoostPower", 7);
		AgPotionPowerJump = property.getInt(7);
		property = config.get(OptionsAg.getName(), "DisableFallDamage", true);
		AgFallDamage = property.getBoolean(true);
		property = config.get(OptionsAg.getName(), "DamageArmorConstant", false);
		AgConstDamage = property.getBoolean(false);
		property = config.get(OptionsAg.getName(), "DurabilityAmountPerDamagePoint", 1);
		AgDurabPerDamage = property.getInt(1);
		property = config.get(OptionsAg.getName(), "AbsorbtionRatio", 0.95);
		AgDamageAbsorptionRatio = property.getDouble(0.95);
		
		property = config.get(OptionsAg.getName(), "ArmorHelmetDurability", 4950);
		AgArmorHelmetDurability = property.getInt(4950);
		property = config.get(OptionsAg.getName(), "ArmorChestPlateDurability", 7200);
		AgArmorChestPlateDurability = property.getInt(7200);
		property = config.get(OptionsAg.getName(), "ArmorLeggingsDurability", 6750);
		AgArmorLeggingsDurability = property.getInt(6750);
		property = config.get(OptionsAg.getName(), "ArmorBootsDurability", 5850);
		AgArmorBootsDurability = property.getInt(5850);
		
		OptionsJug = config.getCategory(StringJug);
		//
		property = config.get(OptionsJug.getName(), "DamageBoostPower", 1);
		JugPotionPowerStrength = property.getInt(1);
		property = config.get(OptionsJug.getName(), "DamageArmorConstant", false);
		JugConstDamage = property.getBoolean(false);
		property = config.get(OptionsJug.getName(), "DurabilityAmountPerDamagePoint", 1);
		JugDurabPerDamage = property.getInt(1);
		property = config.get(OptionsJug.getName(), "AbsorbtionRatio", 0.95);
		JugDamageAbsorptionRatio = property.getDouble(0.95);
		OptionsDef = config.getCategory(StringDef);
		
		property = config.get(OptionsJug.getName(), "ArmorHelmetDurability", 5500);
		JugArmorHelmetDurability = property.getInt(5500);
		property = config.get(OptionsJug.getName(), "ArmorChestPlateDurability", 8000);
		JugArmorChestPlateDurability = property.getInt(8000);
		property = config.get(OptionsJug.getName(), "ArmorLeggingsDurability", 7500);
		JugArmorLeggingsDurability = property.getInt(7500);
		property = config.get(OptionsJug.getName(), "ArmorBootsDurability", 6500);
		JugArmorBootsDurability = property.getInt(6500);
		//
		property = config.get(OptionsDef.getName(), "HealthAmount", 40);
		defMaxHealth = property.getDouble(40);
		property = config.get(OptionsDef.getName(), "DamageArmorConstant", false);
		defConstDamage = property.getBoolean(false);
		property = config.get(OptionsDef.getName(), "DurabilityAmountPerDamagePoint", 1);
		defDurabPerDamage = property.getInt(1);
		property = config.get(OptionsDef.getName(), "AbsorbtionRatio", 0.95);
		defDamageAbsorptionRatio = property.getDouble(0.95);
		
		property = config.get(OptionsDef.getName(), "ArmorHelmetDurability", 8250);
		defArmorHelmetDurability = property.getInt(8250);
		property = config.get(OptionsDef.getName(), "ArmorChestPlateDurability", 12000);
		defArmorChestPlateDurability = property.getInt(12000);
		property = config.get(OptionsDef.getName(), "ArmorLeggingsDurability", 11250);
		defArmorLeggingsDurability = property.getInt(11250);
		property = config.get(OptionsDef.getName(), "ArmorBootsDurability", 9750);
		defArmorBootsDurability = property.getInt(9750);
		
		
		//==========================
		
		Armor = config.getCategory(StringArmor);
		
		property = config.get(Armor.getName(), "ArmorMaxCharge", 1000000);
		ArmorMaxCharge = property.getInt(1000000);
		property = config.get(Armor.getName(), "ArmorTier", 3);
		ArmorTier = property.getInt(3);
		property = config.get(Armor.getName(), "ArmorTransferLimit", 1000);
		ArmorTransferLimit = property.getInt(1000);
		property = config.get(Armor.getName(), "energyPerDamage", 5000);
		ArmorEnergyPerDamage = property.getInt(5000);
		property = config.get(Armor.getName(), "ArmorAbsorbtionRatio", 0.9);
		ArmorAbsorbtionRatio = property.getDouble(0.9);
			
		ModArmor = config.getCategory(StringModArmor);
		
		property = config.get(ModArmor.getName(), "ArmorMaxCharge", 40000000);
		ModArmorMaxCharge = property.getInt(10000000);
		property = config.get(ModArmor.getName(), "ArmorTier", 4);
		ModArmorTier = property.getInt(4);
		property = config.get(ModArmor.getName(), "ArmorTransferLimit", 200000);
		ModArmorTransferLimit = property.getInt(50000);
		property = config.get(ModArmor.getName(), "energyPerDamage", 15000);
		ModArmorEnergyPerDamage = property.getInt(15000);
		property = config.get(ModArmor.getName(), "ArmorAbsorbtionRatio", 1.0);
		ModArmorAbsorbtionRatio = property.getDouble(1.0);
		
		//modules
		
		OptionsModule = config.getCategory(StringModules);
		property = config.get(OptionsModule.getName(), "PoisonModuleMaxCharge", 30000);
		PModuleMaxCharge = property.getInt(30000);
		property = config.get(OptionsModule.getName(), "PoisonModuleTier", 2);
		PModuleTier = property.getInt(2);
		property = config.get(OptionsModule.getName(), "PoisonModuleTransferLimit", 50);
		PModuleTransferLimit = property.getInt(50);
		property = config.get(OptionsModule.getName(), "PoisonModuleDrainEveryTick", 5.0);
		PModuleDrain = property.getDouble(5.0);
		
		OptionsModule = config.getCategory(StringModules);
		property = config.get(OptionsModule.getName(), "StunModuleMaxCharge", 30000);
		SModuleMaxCharge = property.getInt(30000);
		property = config.get(OptionsModule.getName(), "StunModuleTier", 2);
		SModuleTier = property.getInt(2);
		property = config.get(OptionsModule.getName(), "StunModuleTransferLimit", 50);
		SModuleTransferLimit = property.getInt(50);
		property = config.get(OptionsModule.getName(), "StunModuleDrainEveryTick", 5.0);
		SModuleDrain = property.getDouble(5.0);
		
		OptionsModule = config.getCategory(StringModules);
		property = config.get(OptionsModule.getName(), "WeakModuleMaxCharge", 30000);
		WModuleMaxCharge = property.getInt(30000);
		property = config.get(OptionsModule.getName(), "WeakModuleTier", 2);
		WModuleTier = property.getInt(2);
		property = config.get(OptionsModule.getName(), "WeakModuleTransferLimit", 50);
		WModuleTransferLimit = property.getInt(50);
		property = config.get(OptionsModule.getName(), "WeakModuleDrainEveryTick", 5.0);
		WModuleDrain = property.getDouble(5.0);
		
		OptionsModule = config.getCategory(StringModules);
		property = config.get(OptionsModule.getName(), "HealModuleMaxCharge", 30000);
		HModuleMaxCharge = property.getInt(30000);
		property = config.get(OptionsModule.getName(), "HealModuleTier", 2);
		HModuleTier = property.getInt(2);
		property = config.get(OptionsModule.getName(), "HealModuleTransferLimit", 50);
		HModuleTransferLimit = property.getInt(50);
		property = config.get(OptionsModule.getName(), "HealModuleDrainEveryTick", 5.0);
		HModuleDrain = property.getDouble(5.0);
		
		
		
		//saber
		OptionsSaber = config.getCategory(StringSaber);
		//
		property = config.get(OptionsSaber.getName(), "SaberMaxCharge", 400000);
		SMaxCharge = property.getInt(400000);
		property = config.get(OptionsSaber.getName(), "SaberTier", 3);
		STier = property.getInt(3);
		property = config.get(OptionsSaber.getName(), "SaberTransferLimit", 800);
		STransferLimit = property.getInt(800);
		
		property = config.get(OptionsSaber.getName(), "SaberMaxDmg", 25);
		SMaxDmg = property.getInt(25);
		property = config.get(OptionsSaber.getName(), "SaberMinDmg", 1);
		SMinDmg = property.getInt(1);
		
		property = config.get(OptionsSaber.getName(), "NanoDischargeOnHit", 64000.0);
		SNanoDischarge = property.getDouble(64000.0);
		property = config.get(OptionsSaber.getName(), "QuantDischargeOnHit", 450000.0);
		SQuantDischarge = property.getDouble(450000.0);
		property = config.get(OptionsSaber.getName(), "DrainSaberEveryTick", 10.0);
		SDrainOnUpdate = property.getDouble(10.0);
		
		property = config.get(OptionsSaber.getName(), "AllowPoisonModule", true);
		SPoisonModule = property.getBoolean(true);
		property = config.get(OptionsSaber.getName(), "AllowWeakModule", true);
		SWeakModule = property.getBoolean(true);
		property = config.get(OptionsSaber.getName(), "AllowStunModule", true);
		SStunModule = property.getBoolean(true);
		property = config.get(OptionsSaber.getName(), "AllowHealModule", true);
		SHealModule = property.getBoolean(true);
		
		property = config.get(OptionsSaber.getName(), "PoisonModuleUse", 2500.0);
		SPoisonModuleCharge = property.getDouble(2500.0);
		property = config.get(OptionsSaber.getName(), "WeakModuleUse", 4500.0);
		SWeakModuleCharge = property.getDouble(4500.0);
		property = config.get(OptionsSaber.getName(), "StunModuleUse", 5000.0);
		SStunModuleCharge = property.getDouble(5000.0);
		property = config.get(OptionsSaber.getName(), "HealModuleUse", 4000.0);
		SHealModuleCharge = property.getDouble(4000.0);
		
		property = config.get(OptionsSaber.getName(), "HealModuleHealAmount", 2.0);
		SHealModuleHealAmount = (float) property.getDouble(2.0);
		
		
		
		
		//		
		//
		//Other
		OptionsOther = config.getCategory(StringOther);
		
		property = config.get(OptionsOther.getName(), "DisableIridiumSpawning", false);
		IridiumRemove = property.getBoolean(false);
		property = config.get(OptionsOther.getName(), "SomeUpdatersSpawning", true);
		SomeUpdatersSpawning = property.getBoolean(true);
		property = config.get(OptionsOther.getName(), "SomeUpdatersCraft", false);
		SomeUpdatersCraft = property.getBoolean(false);
		property = config.get(OptionsOther.getName(), "NuclearComponentRadioactive", true);
		NuclearComponentRadioactive = property.getBoolean(true);
		property = config.get(OptionsOther.getName(), "EnergyOreSpawn", true);
		EnergyOreSpawn = property.getBoolean(true);
		property = config.get(OptionsOther.getName(), "CraftsWithEnergyOre", true);
		CraftsWithEnergyOre = property.getBoolean(true);
		
		
		
		//Crafting
		Crafting = config.getCategory(StringCraft);
		
		//Circuits
		
		property = config.get(Crafting.getName(), "EnergyChargerCraft", true);
		EnergyChargerCraft = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "AdvancedEnergyChargerCraft", true);
		AdvancedEnergyChargerCraft = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "EnergyBinderCraft", true);
		EnergyBinderCraft = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "UpdatersCraft", true);
		UpdatersCraft = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "UpdaterCoreCraft", true);
		UpdaterCoreCraft = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "MatrixCraft", true);
		MatrixCraft = property.getBoolean(true);
		
		
		
		property = config.get(Crafting.getName(), "UltimateEnergyCraft", true);
		UltimateEnergyCraft = property.getBoolean(true);
		
		
		property = config.get(Crafting.getName(), "ArmorCraft", true);
		ArmorCraft = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "ModArmorCraft", true);
		ModArmorCraft = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "DiamondInlaidCircuit", true);
		DiamondCircuit = property.getBoolean(true);
		property = config.get(Crafting.getName(), "IridiumInlaidCircuit", true);
		IridiumCircuit = property.getBoolean(true);
		
		//saberparts
		property = config.get(Crafting.getName(), "EnergySaberCore", true);
		EnergySaberCore = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ModuleCore", true);
		ModificatorCore = property.getBoolean(true);
		property = config.get(Crafting.getName(), "NuclearComponent", true);
		NuclearComponent = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ColorModificatorRed", true);
		ColorModificatorRed = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ColorModificatorBlue", true);
		ColorModificatorBlue = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "ModulePoison", true);
		ModulePoisonC = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ModuleStun", true);
		ModuleStunC = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ModuleWeakness", true);
		ModuleWeakC = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ModuleHeal", true);
		ModuleHealC = property.getBoolean(true);
		
		property = config.get(Crafting.getName(), "EnergySaber", true);
		EnergySaberCraft = property.getBoolean(true);
		property = config.get(Crafting.getName(), "SaberModule", true);
		SaberModuleCraft = property.getBoolean(true);
		//Extracts
		property = config.get(Crafting.getName(), "ExtractStrength", true);
		ExtractStrength = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ExtractDefence", true);
		ExtractDefence = property.getBoolean(true);
		property = config.get(Crafting.getName(), "ExtractRegeneration", true);
		ExtractRegeneration = property.getBoolean(true);
		//Cores
		property = config.get(Crafting.getName(), "CoreStrength", true);
		StrengthCore = property.getBoolean(true);
		property = config.get(Crafting.getName(), "CoreDefence", true);
		DefenceCore = property.getBoolean(true);
		property = config.get(Crafting.getName(), "CoreRegeneration", true);
		RegenerationCore = property.getBoolean(true);
		//Stimulators
		//lvl1
		property = config.get(Crafting.getName(), "StimulatorStrengthLvl1", true);
		StimulatorStrengthLvl1 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "StimulatorDefenceLvl1", true);
		StimulatorDefenceLvl1 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "StimulatorRegenerationLvl1", true);
		StimulatorRegenerationLvl1 = property.getBoolean(true);
		//lvl2
		property = config.get(Crafting.getName(), "StimulatorStrengthLvl2", true);
		StimulatorStrengthLvl2 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "StimulatorDefenceLvl2", true);
		StimulatorDefenceLvl2 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "StimulatorRegenerationLvl2", true);
		StimulatorRegenerationLvl2 = property.getBoolean(true);
		//lvl3
		property = config.get(Crafting.getName(), "StimulatorStrengthLvl3", true);
		StimulatorStrengthLvl3 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "StimulatorDefenceLvl3", true);
		StimulatorDefenceLvl3 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "StimulatorRegenerationLvl3", true);
		StimulatorRegenerationLvl3 = property.getBoolean(true);
		//Ultimate
		property = config.get(Crafting.getName(), "UltimateStimulatorLvl1", true);
		UltimateStimulatorLvl1 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "UltimateStimulatorLvl2", true);
		UltimateStimulatorLvl2 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "UltimateStimulatorLvl3", true);
		UltimateStimulatorLvl3 = property.getBoolean(true);
		//EnergyShieldParts
		property = config.get(Crafting.getName(), "EnergyShieldMagnet", true);
		EnergyShieldMagnet = property.getBoolean(true);
		property = config.get(Crafting.getName(), "EnergyShieldMagnetCoil", true);
		EnergyShieldMagnetCoil = property.getBoolean(true);
		property = config.get(Crafting.getName(), "EnergyShieldCore", true);
		EnergyShieldCore = property.getBoolean(true);
		//EnergyShields
		property = config.get(Crafting.getName(), "EnergyShieldLvl1", true);
		EnergyShieldLvl1 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "EnergyShieldLvl2", true);
		EnergyShieldLvl2 = property.getBoolean(true);
		property = config.get(Crafting.getName(), "EnergyShieldLvl3", true);
		EnergyShieldLvl3 = property.getBoolean(true);
		//EnergyBar
		property = config.get(Crafting.getName(), "EnergyBar", true);
		EnergyBar = property.getBoolean(true);
		//
		
		OptionsStimLvl1 = config.getCategory(StringStim);
		//Strength
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl1_maxCharge", 10000);
		property.comment = "The maximum charge of stimulator";
		StrStimLvl1maxCharge = property.getInt(10000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl2_maxCharge", 1000000);
		property.comment = "The maximum charge of stimulator";
		StrStimLvl2maxCharge = property.getInt(1000000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl3_maxCharge", 10000000);
		property.comment = "The maximum charge of stimulator";
		StrStimLvl3maxCharge = property.getInt(10000000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl1_TransferLimit", 200);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		StrStimLvl1TransferLimit = property.getInt(200);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl2_TransferLimit", 20000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		StrStimLvl2TransferLimit = property.getInt(20000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl3_TransferLimit", 200000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		StrStimLvl3TransferLimit = property.getInt(200000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl1_Tier", 1);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		StrStimLvl1Tier = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl2_Tier", 2);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		StrStimLvl2Tier = property.getInt(2);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl3_Tier", 3);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		StrStimLvl3Tier = property.getInt(3);
		
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl1_UseCharge", 1000.0);
		property.comment = "The amount of energy that the stimulator will use";
		StrStimLvl1ChargeToUse = property.getDouble(1000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl2_UseCharge", 100000.0);
		property.comment = "The amount of energy that the stimulator will use";
		StrStimLvl2ChargeToUse = property.getDouble(100000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl3_UseCharge", 1000000.0);
		property.comment = "The amount of energy that the stimulator will use";
		StrStimLvl3ChargeToUse = property.getDouble(1000000.0);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl1_EffectPower", 0);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		StrStimLvl1EffectPower = property.getInt(0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl2_EffectPower", 1);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		StrStimLvl2EffectPower = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl3_EffectPower", 2);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		StrStimLvl3EffectPower = property.getInt(2);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl1_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		StrStimLvl1EffectDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl2_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		StrStimLvl2EffectDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Strength_Lvl3_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		StrStimLvl3EffectDuration = property.getInt(120);
		
		//Regeneration
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl1_maxCharge", 10000);
		property.comment = "The maximum charge of stimulator";
		RegStimLvl1maxCharge = property.getInt(10000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl2_maxCharge", 1000000);
		property.comment = "The maximum charge of stimulator";
		RegStimLvl2maxCharge = property.getInt(1000000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl3_maxCharge", 10000000);
		property.comment = "The maximum charge of stimulator";
		RegStimLvl3maxCharge = property.getInt(10000000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl1_TransferLimit", 200);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		RegStimLvl1TransferLimit = property.getInt(200);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl2_TransferLimit", 20000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		RegStimLvl2TransferLimit = property.getInt(20000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl3_TransferLimit", 200000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		RegStimLvl3TransferLimit = property.getInt(200000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl1_Tier", 1);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		RegStimLvl1Tier = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl2_Tier", 2);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		RegStimLvl2Tier = property.getInt(2);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl3_Tier", 3);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		RegStimLvl3Tier = property.getInt(3);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl1_UseCharge", 1000.0);
		property.comment = "The amount of energy that the stimulator will use";
		RegStimLvl1ChargeToUse = property.getDouble(1000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl2_UseCharge", 100000.0);
		property.comment = "The amount of energy that the stimulator will use";
		RegStimLvl2ChargeToUse = property.getDouble(100000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl3_UseCharge", 1000000.0);
		property.comment = "The amount of energy that the stimulator will use";
		RegStimLvl3ChargeToUse = property.getDouble(1000000.0);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl1_EffectPower", 0);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		RegStimLvl1EffectPower = property.getInt(0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl2_EffectPower", 1);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		RegStimLvl2EffectPower = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl3_EffectPower", 2);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		RegStimLvl3EffectPower = property.getInt(2);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl1_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		RegStimLvl1EffectDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl2_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		RegStimLvl2EffectDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Regeneration_Lvl3_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		RegStimLvl3EffectDuration = property.getInt(120);
		
		
		//Defence
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl1_maxCharge", 10000);
		property.comment = "The maximum charge of stimulator";
		DefStimLvl1maxCharge = property.getInt(10000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl2_maxCharge", 1000000);
		property.comment = "The maximum charge of stimulator";
		DefStimLvl2maxCharge = property.getInt(1000000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl3_maxCharge", 10000000);
		property.comment = "The maximum charge of stimulator";
		DefStimLvl3maxCharge = property.getInt(10000000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl1_TransferLimit", 200);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		DefStimLvl1TransferLimit = property.getInt(200);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl2_TransferLimit", 20000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		DefStimLvl2TransferLimit = property.getInt(20000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl3_TransferLimit", 200000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		DefStimLvl3TransferLimit = property.getInt(200000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl1_Tier", 1);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		DefStimLvl1Tier = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl2_Tier", 2);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		DefStimLvl2Tier = property.getInt(2);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl3_Tier", 3);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		DefStimLvl3Tier = property.getInt(3);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl1_UseCharge", 1000.0);
		property.comment = "The amount of energy that the stimulator will use";
		DefStimLvl1ChargeToUse = property.getDouble(1000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl2_UseCharge", 100000.0);
		property.comment = "The amount of energy that the stimulator will use";
		DefStimLvl2ChargeToUse = property.getDouble(100000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl3_UseCharge", 1000000.0);
		property.comment = "The amount of energy that the stimulator will use";
		DefStimLvl3ChargeToUse = property.getDouble(1000000.0);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl1_EffectPower", 0);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		DefStimLvl1EffectPower = property.getInt(0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl2_EffectPower", 1);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		DefStimLvl2EffectPower = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl3_EffectPower", 2);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		DefStimLvl3EffectPower = property.getInt(2);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl1_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		DefStimLvl1EffectDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl2_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		DefStimLvl2EffectDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Defence_Lvl3_EffectDuration", 120);
		property.comment = "The Effect duration in seconds";
		DefStimLvl3EffectDuration = property.getInt(120);
		
		//Ultimate
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_maxCharge", 30000);
		property.comment = "The maximum charge of stimulator";
		UltStimLvl1maxCharge = property.getInt(30000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_maxCharge", 3000000);
		property.comment = "The maximum charge of stimulator";
		UltStimLvl2maxCharge = property.getInt(3000000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_maxCharge", 30000000);
		property.comment = "The maximum charge of stimulator";
		UltStimLvl3maxCharge = property.getInt(30000000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_TransferLimit", 300);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		UltStimLvl1TransferLimit = property.getInt(300);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_TransferLimit", 30000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		UltStimLvl2TransferLimit = property.getInt(30000);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_TransferLimit", 300000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		UltStimLvl3TransferLimit = property.getInt(300000);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_Tier", 1);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		UltStimLvl1Tier = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_Tier", 2);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		UltStimLvl2Tier = property.getInt(2);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_Tier", 3);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		UltStimLvl3Tier = property.getInt(3);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_UseCharge", 3000.0);
		property.comment = "The amount of energy that the stimulator will use";
		UltStimLvl1ChargeToUse = property.getDouble(3000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_UseCharge", 300000.0);
		property.comment = "The amount of energy that the stimulator will use";
		UltStimLvl2ChargeToUse = property.getDouble(300000.0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_UseCharge", 3000000.0);
		property.comment = "The amount of energy that the stimulator will use";
		UltStimLvl3ChargeToUse = property.getDouble(3000000.0);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_EffectStrengthPower", 0);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl1EffectStrengthPower = property.getInt(0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_EffectStrengthPower", 1);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl2EffectStrengthPower = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_EffectStrengthPower", 2);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl3EffectStrengthPower = property.getInt(2);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_EffectRegenerationPower", 0);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl1EffectRegenerationPower = property.getInt(0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_EffectRegenerationPower", 1);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl2EffectRegenerationPower = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_EffectRegenerationPower", 2);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl3EffectRegenerationPower = property.getInt(2);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_EffectDefencePower", 0);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl1EffectDefencePower = property.getInt(0);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_EffectDefencePower", 1);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl2EffectDefencePower = property.getInt(1);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_EffectDefencePower", 2);
		property.comment = "The Effect power. If you set 0 it will be 1, if u set 1 it will be 2 etc.";
		UltStimLvl3EffectDefencePower = property.getInt(2);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_EffectStrengthDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl1EffectStrengthDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_EffectStrengthDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl2EffectStrengthDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_EffectStrengthDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl3EffectStrengthDuration = property.getInt(120);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_EffectRegenerationDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl1EffectRegenerationDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_EffectRegenerationDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl2EffectRegenerationDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_EffectRegenerationDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl3EffectRegenerationDuration = property.getInt(120);
		
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl1_EffectDefenceDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl1EffectDefenceDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl2_EffectDefenceDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl2EffectDefenceDuration = property.getInt(120);
		property = config.get(OptionsStimLvl1.getName(), "Stimulator_Ultimate_Lvl3_EffectDefenceDuration", 120);
		property.comment = "The Effect duration in seconds";
		UltStimLvl3EffectDefenceDuration = property.getInt(120);
		
		//Shields
		//lvl1
		OptionsShieldsLvl1 = config.getCategory(StringShield1);
		
		property = config.get(OptionsShieldsLvl1.getName(), "maxCharge", 10000);
		property.comment = "The maximum charge of shield";
		ShLvl1maxCharge = property.getInt(10000);
		property = config.get(OptionsShieldsLvl1.getName(), "TransferLimit", 200);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		ShLvl1TransferLimit = property.getInt(200);
		property = config.get(OptionsShieldsLvl1.getName(), "Tier", 1);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		ShLvl1Tier = property.getInt(1);
		
		property = config.get(OptionsShieldsLvl1.getName(), "DamageTypeProjectile", true);
		ShLvl1DamageTypeProjectile = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl1.getName(), "DamageTypeFireDamage", true);
		ShLvl1DamageTypeFireDamage = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl1.getName(), "DamageTypeFall", true);
		ShLvl1DamageTypeFall = property.getBoolean(true);
		//lvl2
		
		OptionsShieldsLvl2 = config.getCategory(StringShield2);
		
		property = config.get(OptionsShieldsLvl2.getName(), "maxCharge", 4000000);
		property.comment = "The maximum charge of shield";
		ShLvl2maxCharge = property.getInt(4000000);
		property = config.get(OptionsShieldsLvl2.getName(), "TransferLimit", 80000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		ShLvl2TransferLimit = property.getInt(80000);
		property = config.get(OptionsShieldsLvl2.getName(), "Tier", 2);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		ShLvl2Tier = property.getInt(2);
		
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeExplosion", true);
		ShLvl2DamageTypeExplosion = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeProjectile", true);
		ShLvl2DamageTypeProjectile = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeFireDamage", true);
		ShLvl2DamageTypeFireDamage = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeMagicDamage", true);
		ShLvl2DamageTypeMagicDamage = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeDamageAbsolute", false);
		ShLvl2DamageTypeDamageAbsolute = property.getBoolean(false);
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeCactus", true);
		ShLvl2DamageTypeCactus = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeFall", true);
		ShLvl2DamageTypeFall = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl2.getName(), "DamageTypeWither", true);
		ShLvl2DamageTypeWither = property.getBoolean(true);

		
		//lvl3
		OptionsShieldsLvl3 = config.getCategory(StringShield3);
		
		property = config.get(OptionsShieldsLvl3.getName(), "maxCharge", 40000000);
		property.comment = "The maximum charge of shield";
		ShLvl3maxCharge = property.getInt(40000000);
		property = config.get(OptionsShieldsLvl3.getName(), "TransferLimit", 800000);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		ShLvl3TransferLimit = property.getInt(800000);
		property = config.get(OptionsShieldsLvl3.getName(), "Tier", 3);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		ShLvl3Tier = property.getInt(3);
	
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeExplosion", true);
		ShLvl3DamageTypeExplosion = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeProjectile", true);
		ShLvl3DamageTypeProjectile = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeFireDamage", true);
		ShLvl3DamageTypeFireDamage = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeMagicDamage", true);
		ShLvl3DamageTypeMagicDamage = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeDamageAbsolute", true);
		ShLvl3DamageTypeDamageAbsolute = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeCactus", true);
		ShLvl3DamageTypeCactus = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeFall", true);
		ShLvl3DamageTypeFall = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeWall", true);
		ShLvl3DamageTypeWall = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeWither", true);
		ShLvl3DamageTypeWither = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeFallingBlock", true);
		ShLvl3DamageTypeFallingBlock = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeAnvil", true);
		ShLvl3DamageTypeAnvil = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypeMob", true);
		ShLvl3DamageTypeMob = property.getBoolean(true);
		property = config.get(OptionsShieldsLvl3.getName(), "DamageTypePlayer", true);
		ShLvl3DamageTypePlayer = property.getBoolean(true);
		
		OptionsEnergyBar = config.getCategory(StringEnergyBar);
		property = config.get(OptionsEnergyBar.getName(), "maxCharge", 10000);
		property.comment = "The maximum charge of EnergyBar";
		EnergyBarmaxCharge = property.getInt(10000);
		property = config.get(OptionsEnergyBar.getName(), "TransferLimit", 200);
		property.comment = "How quickly the item will be charged(Amount of energy PER TICK(It will be converted to seconds automatically: 1 tick * 20 = 1 second))";
		EnergyBarTransferLimit = property.getInt(200);
		property = config.get(OptionsEnergyBar.getName(), "Tier", 1);
		property.comment = "The tier. Batteries are Tier 1, Advanced Batteries are Tier 2, Energy Crystals are Tier 3, Lapotron Crystals are Tier 4";
		EnergyBarTier = property.getInt(1);
		property = config.get(OptionsEnergyBar.getName(), "UseCharge", 100.0);
		property.comment = "The amount of energy that the EnergyBar will use";
		EnergyBarUsesCharge = property.getDouble(100.0);
		property = config.get(OptionsEnergyBar.getName(), "ResoreFoodPoints", 2);
		property.comment = "The amount of food that the EnergyBar will restore";
		EnergyBarFoodPoints = property.getInt(2);
		
		if (config.hasChanged()) config.save();
	}

}
