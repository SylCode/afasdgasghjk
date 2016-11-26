package com.thunder.Updaters;

import com.thunder.Items.IDiamondInlaidCircuit;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class UpdatersList {
	
	public static Item EnergyMatrix;
	public static Item EnergyMatrixStandart;
	public static Item EnergyMatrixUltra;
	public static Item EnergyUpdater;
	public static Item EnergyUpdater1;
	public static Item EnergyUpdaterCombat2;
	public static Item EnergyUpdaterCombat3;
	public static Item EnergyUpdaterFallDamage3;
	public static Item EnergyUpdaterRemovePoison;
	public static Item EnergyUpdaterAutoFood;
	public static Item EnergyUpdaterJump1;
	public static Item EnergyUpdaterJump2;
	public static Item EnergyUpdaterJump3;
	public static Item EnergyUpdaterSpeed1;
	public static Item EnergyUpdaterSpeed2;
	public static Item EnergyUpdaterSpeed3;
	public static Item EnergyUpdaterBlind2;
	public static Item EnergyUpdaterLava;
	public static Item EnergyUpdaterUnderWater;
	public static Item EnergyUpdaterNightVision;
	public static Item EnergyUpdaterPoisonAllAround;
	public static Item EnergyUpdaterAddRegeneration2;
	public static Item EnergyUpdaterAddRegeneration3;
	public static Item EnergyUpdaterRestoreHeartOnHit1;
	public static Item EnergyUpdaterRestoreHeartOnHit2;
	public static Item EnergyUpdaterRestoreHeartOnHit3;
	public static Item EnergyUpdaterAddExtraDamage2;
	public static Item EnergyUpdaterAddExtraDamage3;
	public static Item EnergyUpdaterAddJumpOnHit1;
	public static Item EnergyUpdaterAddJumpOnHit2;
	public static Item EnergyUpdaterAddJumpOnHit3;
	public static Item EnergyUpdaterAddSpeedOnHit1;
	public static Item EnergyUpdaterAddSpeedOnHit2;
	public static Item EnergyUpdaterAddSpeedOnHit3;
	public static Item EnergyUpdaterMine2;
	public static Item EnergyUpdaterSlowdown3;
	public static Item EnergyUpdaterRegenerationN3;
	public static Item EnergyUpdaterJetPack;
	
	public static void updatersList(){
		
		EnergyUpdater = new IEnergyUpdater();
		    
	    EnergyUpdaterFallDamage3 = new IEnergyUpdaterFallDamage3().setUnlocalizedName("EnergyUpdaterFallDamage3");
	    GameRegistry.registerItem(EnergyUpdaterFallDamage3, "EnergyUpdaterFallDamage3");
	    
	    EnergyUpdaterRemovePoison = new IEnergyUpdaterRemovePoison().setUnlocalizedName("EnergyUpdaterRemovePoison");
	    GameRegistry.registerItem(EnergyUpdaterRemovePoison, "EnergyUpdaterRemovePoison");
	    
	    EnergyUpdaterAutoFood = new IEnergyUpdaterAutoFood().setUnlocalizedName("EnergyUpdaterAutoFood");
	    GameRegistry.registerItem(EnergyUpdaterAutoFood, "EnergyUpdaterAutoFood");
	    
	    EnergyUpdaterJump1 = new IEnergyUpdaterJump1().setUnlocalizedName("EnergyUpdaterJump1");
	    GameRegistry.registerItem(EnergyUpdaterJump1, "EnergyUpdaterJump1");
	    
	    EnergyUpdaterJump2 = new IEnergyUpdaterJump2().setUnlocalizedName("EnergyUpdaterJump2");
	    GameRegistry.registerItem(EnergyUpdaterJump2, "EnergyUpdaterJump2");
	    
	    EnergyUpdaterJump3 = new IEnergyUpdaterJump3().setUnlocalizedName("EnergyUpdaterJump3");
	    GameRegistry.registerItem(EnergyUpdaterJump3, "EnergyUpdaterJump3");
	    
	    EnergyUpdaterSpeed1 = new IEnergyUpdaterSpeed1().setUnlocalizedName("EnergyUpdaterSpeed1");
	    GameRegistry.registerItem(EnergyUpdaterSpeed1, "EnergyUpdaterSpeed1");
	    
	    EnergyUpdaterSpeed2 = new IEnergyUpdaterSpeed2().setUnlocalizedName("EnergyUpdaterSpeed2");
	    GameRegistry.registerItem(EnergyUpdaterSpeed2, "EnergyUpdaterSpeed2");
	    
	    EnergyUpdaterSpeed3 = new IEnergyUpdaterSpeed3().setUnlocalizedName("EnergyUpdaterSpeed3");
	    GameRegistry.registerItem(EnergyUpdaterSpeed3, "EnergyUpdaterSpeed3");
	    
	    EnergyUpdaterBlind2 = new IEnergyUpdaterBlind2().setUnlocalizedName("EnergyUpdaterBlind2");
	    GameRegistry.registerItem(EnergyUpdaterBlind2, "EnergyUpdaterBlind2");
	    
	    EnergyUpdaterUnderWater = new IEnergyUpdaterUnderWater().setUnlocalizedName("EnergyUpdaterUnderWater");
	    GameRegistry.registerItem(EnergyUpdaterUnderWater, "EnergyUpdaterUnderWater");
	    
	    EnergyUpdaterNightVision = new IEnergyUpdaterNightVision().setUnlocalizedName("EnergyUpdaterNightVision");
	    GameRegistry.registerItem(EnergyUpdaterNightVision, "EnergyUpdaterNightVision");
	    
	    EnergyUpdaterPoisonAllAround = new IEnergyUpdaterPoisonAllAround().setUnlocalizedName("EnergyUpdaterPoisonAllAround");
	    GameRegistry.registerItem(EnergyUpdaterPoisonAllAround, "EnergyUpdaterPoisonAllAround");
	    
	    EnergyUpdaterAddRegeneration2 = new IEnergyUpdaterAddRegeneration2().setUnlocalizedName("EnergyUpdaterAddRegeneration2");
	    GameRegistry.registerItem(EnergyUpdaterAddRegeneration2, "EnergyUpdaterAddRegeneration2");
	    
	    EnergyUpdaterAddRegeneration3 = new IEnergyUpdaterAddRegeneration3().setUnlocalizedName("EnergyUpdaterAddRegeneration3");
	    GameRegistry.registerItem(EnergyUpdaterAddRegeneration3, "EnergyUpdaterAddRegeneration3");
	    
	    EnergyUpdaterRestoreHeartOnHit1 = new IEnergyUpdaterRestoreHeartOnHit1().setUnlocalizedName("EnergyUpdaterRestoreHeartOnHit1");
	    GameRegistry.registerItem(EnergyUpdaterRestoreHeartOnHit1, "EnergyUpdaterRestoreHeartOnHit1");
	    
	    EnergyUpdaterRestoreHeartOnHit2 = new IEnergyUpdaterRestoreHeartOnHit2().setUnlocalizedName("EnergyUpdaterRestoreHeartOnHit2");
	    GameRegistry.registerItem(EnergyUpdaterRestoreHeartOnHit2, "EnergyUpdaterRestoreHeartOnHit2");
	    
	    EnergyUpdaterRestoreHeartOnHit3 = new IEnergyUpdaterRestoreHeartOnHit3().setUnlocalizedName("EnergyUpdaterRestoreHeartOnHit3");
	    GameRegistry.registerItem(EnergyUpdaterRestoreHeartOnHit3, "EnergyUpdaterRestoreHeartOnHit3");
	        
	    EnergyUpdaterAddJumpOnHit1 = new IEnergyUpdaterAddJumpOnHit1().setUnlocalizedName("EnergyUpdaterJumpOnHit1");
	    GameRegistry.registerItem(EnergyUpdaterAddJumpOnHit1, "EnergyUpdaterAddJumpOnHit1");
	    
	    EnergyUpdaterAddJumpOnHit2 = new IEnergyUpdaterAddJumpOnHit2().setUnlocalizedName("EnergyUpdaterJumpOnHit2");
	    GameRegistry.registerItem(EnergyUpdaterAddJumpOnHit2, "EnergyUpdaterAddJumpOnHit2");
	    
	    EnergyUpdaterAddJumpOnHit3 = new IEnergyUpdaterAddJumpOnHit3().setUnlocalizedName("EnergyUpdaterJumpOnHit3");
	    GameRegistry.registerItem(EnergyUpdaterAddJumpOnHit3, "EnergyUpdaterAddJumpOnHit3");
	    
	    EnergyUpdaterAddSpeedOnHit1 = new IEnergyUpdaterAddSpeedOnHit1().setUnlocalizedName("EnergyUpdaterSpeedOnHit1");
	    GameRegistry.registerItem(EnergyUpdaterAddSpeedOnHit1, "EnergyUpdaterAddSpeedOnHit1");
	    
	    EnergyUpdaterAddSpeedOnHit2 = new IEnergyUpdaterAddSpeedOnHit2().setUnlocalizedName("EnergyUpdaterSpeedOnHit2");
	    GameRegistry.registerItem(EnergyUpdaterAddSpeedOnHit2, "EnergyUpdaterAddSpeedOnHit2");
	    
	    EnergyUpdaterAddSpeedOnHit3 = new IEnergyUpdaterAddSpeedOnHit3().setUnlocalizedName("EnergyUpdaterSpeedOnHit3");
	    GameRegistry.registerItem(EnergyUpdaterAddSpeedOnHit3, "EnergyUpdaterAddSpeedOnHit3");
	    
	    EnergyUpdaterMine2 = new IEnergyUpdaterMine2().setUnlocalizedName("EnergyUpdaterMine2");
	    GameRegistry.registerItem(EnergyUpdaterMine2, "EnergyUpdaterMine2");
	    
	    EnergyUpdaterSlowdown3 = new IEnergyUpdaterSlowdown3().setUnlocalizedName("EnergyUpdaterSlowdown3");
	    GameRegistry.registerItem(EnergyUpdaterSlowdown3, "EnergyUpdaterSlowdown3");
	    
	    EnergyUpdaterRegenerationN3 = new IEnergyUpdaterRegenerationN3().setUnlocalizedName("EnergyUpdaterRegenerationN3");
	    GameRegistry.registerItem(EnergyUpdaterRegenerationN3, "EnergyUpdaterRegenerationN3");
	    
	    EnergyMatrix = new IEnergyMatrixLow().setUnlocalizedName("EnergyMatrix");
	    GameRegistry.registerItem(EnergyMatrix, "EnergyMatrix");
	    
	    EnergyMatrixStandart = new IEnergyMatrixStandart().setUnlocalizedName("EnergyMatrixStandart");
	    GameRegistry.registerItem(EnergyMatrixStandart, "EnergyMatrixStandart");
	    
	    EnergyMatrixUltra = new IEnergyMatrixUltra().setUnlocalizedName("EnergyMatrixUltra");
	    GameRegistry.registerItem(EnergyMatrixUltra, "EnergyMatrixUltra");
	    
		
		
		
	}

}
