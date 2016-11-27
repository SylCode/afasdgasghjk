package com.thunder.Recipes;

import java.util.ArrayList;

import com.thunder.Armor.ArmorList;
import com.thunder.Blocks.BlockList;
import com.thunder.EnergyAdditions.Config;
import com.thunder.Items.ItemList;
import com.thunder.Updaters.UpdatersList;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.RecipeOutput;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeList {
	
	public static void Recipes(){
		
        ItemStack battery = IC2Items.getItem("reBattery").copy();
        battery.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack Chbattery = IC2Items.getItem("chargedReBattery").copy();
        Chbattery.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack energyCrystal = IC2Items.getItem("energyCrystal").copy();
        energyCrystal.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack lapotronCrystal = IC2Items.getItem("lapotronCrystal").copy();
        lapotronCrystal.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        ItemStack StimulatorStr1 = new ItemStack(ItemList.EnergyStimulatorStr1).copy();
        StimulatorStr1.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack StimulatorStr2 = new ItemStack(ItemList.EnergyStimulatorStr2).copy();
        StimulatorStr2.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack StimulatorStr3 = new ItemStack(ItemList.EnergyStimulatorStr3).copy();
        StimulatorStr3.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        ItemStack StimulatorReg1 = new ItemStack(ItemList.EnergyStimulatorReg1).copy();
        StimulatorReg1.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack StimulatorReg2 = new ItemStack(ItemList.EnergyStimulatorReg2).copy();
        StimulatorReg2.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack StimulatorReg3 = new ItemStack(ItemList.EnergyStimulatorReg3).copy();
        StimulatorReg3.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        ItemStack StimulatorDef1 = new ItemStack(ItemList.EnergyStimulatorDef1).copy();
        StimulatorDef1.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack StimulatorDef2 = new ItemStack(ItemList.EnergyStimulatorDef2).copy();
        StimulatorDef2.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack StimulatorDef3 = new ItemStack(ItemList.EnergyStimulatorDef3).copy();
        StimulatorDef3.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        ItemStack EnergySh1 = new ItemStack(ItemList.EnergyShield1).copy();
        EnergySh1.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack EnergySh2 = new ItemStack(ItemList.EnergyShield2).copy();
        EnergySh2.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        
        
        ItemStack EnergyM2 = new ItemStack(UpdatersList.EnergyMatrix).copy();
        EnergyM2.setItemDamage(OreDictionary.WILDCARD_VALUE);
        ItemStack EnergyM3 = new ItemStack(UpdatersList.EnergyMatrixStandart).copy();
        EnergyM3.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        ItemStack EnergyBar = new ItemStack(ItemList.EnergyBar).copy();
        EnergyBar.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        ItemStack JetPack = IC2Items.getItem("electricJetpack").copy();
        JetPack.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        ItemStack ULenergyCrystal = new ItemStack(ItemList.UltimateEnergyCrystal).copy();
        ULenergyCrystal.setItemDamage(OreDictionary.WILDCARD_VALUE);
        
        
        //OreDictSupport (Mostly for GT5/6)
        
       
        
        if(Config.EnergyChargerCraft == true){
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(BlockList.EnergyCharger, 1), new Object[] {"CIC", "IMI", "CIC",  Character.valueOf('M'), IC2Items.getItem("mfsUnit"),  Character.valueOf('C'), ItemList.DiamondInlaidCircuit,  Character.valueOf('I'), ItemList.EnergyShieldMagnetCoil});	
        }
        
        //armor
        if(Config.ArmorCraft == true){
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.EnergyArmorHelmet, 1), new Object[] {"III", "IMI", "CGC",  Character.valueOf('I'), IC2Items.getItem("carbonPlate"), Character.valueOf('M'), energyCrystal, Character.valueOf('C'), ItemList.DiamondInlaidCircuit, Character.valueOf('G'), ItemList.EnergyShieldCore});
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.EnergyArmorChestPlate, 1), new Object[] {"CGC", "IMI", "III",  Character.valueOf('I'), IC2Items.getItem("carbonPlate"), Character.valueOf('M'), energyCrystal, Character.valueOf('C'), ItemList.DiamondInlaidCircuit, Character.valueOf('G'), ItemList.EnergyShieldCore});
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.EnergyArmorLeggings, 1), new Object[] {"III", "CMC", "IGI",  Character.valueOf('I'), IC2Items.getItem("carbonPlate"), Character.valueOf('M'), energyCrystal, Character.valueOf('C'), ItemList.DiamondInlaidCircuit, Character.valueOf('G'), ItemList.EnergyShieldCore});
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.EnergyArmorBoots, 1), new Object[] {"C C", "IGI", "IMI",  Character.valueOf('I'), IC2Items.getItem("carbonPlate"), Character.valueOf('M'), energyCrystal, Character.valueOf('C'), ItemList.DiamondInlaidCircuit, Character.valueOf('G'), ItemList.EnergyShieldCore});     	
        }
        
       //ARMOR NEW
        if(Config.ModArmorCraft == true){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.ModArmorHelmet, 1), new Object[] {"IHI", "DCD", "   ",  Character.valueOf('I'), ItemList.IridiumInlaidCircuit, Character.valueOf('H'), new ItemStack(ArmorList.EnergyArmorHelmet, 1, OreDictionary.WILDCARD_VALUE), Character.valueOf('C'), ULenergyCrystal, Character.valueOf('D'), ItemList.ColorModificatorBlue});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.ModArmorChestPlate, 1), new Object[] {"DJD", "IHI", "ICI", Character.valueOf('J'), JetPack,  Character.valueOf('I'), ItemList.IridiumInlaidCircuit, Character.valueOf('H'), new ItemStack(ArmorList.EnergyArmorChestPlate, 1, OreDictionary.WILDCARD_VALUE), Character.valueOf('C'), ULenergyCrystal, Character.valueOf('D'), ItemList.ColorModificatorBlue});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.ModArmorLeggings, 1), new Object[] {"DHD", "ICI", "I I",  Character.valueOf('I'), ItemList.IridiumInlaidCircuit, Character.valueOf('H'), new ItemStack(ArmorList.EnergyArmorLeggings, 1, OreDictionary.WILDCARD_VALUE), Character.valueOf('C'), ULenergyCrystal, Character.valueOf('D'), ItemList.ColorModificatorBlue});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ArmorList.ModArmorBoots, 1), new Object[] {"   ", "DCD", "IHI",  Character.valueOf('I'), ItemList.IridiumInlaidCircuit, Character.valueOf('H'), new ItemStack(ArmorList.EnergyArmorBoots, 1, OreDictionary.WILDCARD_VALUE), Character.valueOf('C'), ULenergyCrystal, Character.valueOf('D'), ItemList.ColorModificatorBlue});
       }
        
      
        
      
        //UPDATERS
        //core
        if(Config.UpdaterCoreCraft == true){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UpdaterCore, 1), new Object[] {"LRL", "LCL", "LRL",  Character.valueOf('L'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('R'), IC2Items.getItem("electronicCircuit"), Character.valueOf('C'), energyCrystal});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UpdaterCore, 1), new Object[] {"LRL", "LCL", "LRL",  Character.valueOf('L'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('R'), IC2Items.getItem("electronicCircuit"), Character.valueOf('C'), energyCrystal});
        }
        //matrix
        if(Config.MatrixCraft == true){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyMatrix, 1), new Object[] {" R ", "RCR", " R ", Character.valueOf('R'), IC2Items.getItem("electronicCircuit"), Character.valueOf('C'), energyCrystal});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyMatrixStandart, 1), new Object[] {" R ", "RCR", " D ", Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), EnergyM2, Character.valueOf('D'), lapotronCrystal});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyMatrixUltra, 1), new Object[] {"DRD", "RCR", " D ", Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), EnergyM3, Character.valueOf('D'), lapotronCrystal});
        }
        
        //updaters
        if(Config.UpdatersCraft == true){
        //combat
        	
        //unique
        	if(Config.SomeUpdatersCraft == true){
        	
        //
        	//regenerationN3
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddRegeneration3, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddRegeneration3, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
         
        //slowdown3
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('M'), new ItemStack(Items.potionitem, 1, 8236), Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('D'), ItemList.UpdaterCore,Character.valueOf('M'), new ItemStack(Items.potionitem, 1, 8236), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
         
        //poisonallaround
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('M'), new ItemStack(Items.potionitem, 1, 8260), Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('M'), new ItemStack(Items.potionitem, 1, 8260), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
          
        //mine2
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterMine2, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('M'), Items.diamond_pickaxe, Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterMine2, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('M'), Items.diamond_pickaxe, Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
          
        //underwater
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterUnderWater, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8269), Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterUnderWater, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8269), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        //blindallaround
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterBlind2, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('M'), Items.spider_eye, Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterBlind2, 1), new Object[] {"CRC", "RDR", "CMC", Character.valueOf('M'), Items.spider_eye, Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
       
        	
        	
        //
        	
        	}
        	
        //lavawalker
        	
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterLavaFreezing, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), Items.water_bucket, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterLavaFreezing, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), Items.water_bucket, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});         
        	
        //wither       	
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddWither, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), Items.nether_star, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddWither, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), Items.nether_star, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        	
        //str
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddStrength1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyStimulatorExtractStr, Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddStrength1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyStimulatorExtractStr, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddStrength2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddStrength1, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddStrength2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddStrength1, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddStrength3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddStrength2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddStrength3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddStrength2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //falling
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterFallDamage3, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyStimulatorExtractDef, Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterFallDamage3, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyStimulatorExtractDef, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        //autofood
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAutoFood, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), EnergyBar, Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAutoFood, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), EnergyBar, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        //antipoison
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRemovePoison, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyShieldCore, Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRemovePoison, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyShieldCore, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //nightvision
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterNightVision, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8230), Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterNightVision, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8230), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        //speed
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSpeed1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8226), Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSpeed1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8226), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSpeed2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterSpeed1, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSpeed2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterSpeed1, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSpeed3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterSpeed2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterSpeed3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterSpeed2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //jump
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterJump1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), Items.feather, Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterJump1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), Items.feather, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterJump2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterJump1, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterJump2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterJump1, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterJump3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterJump2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterJump3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterJump2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //speed on hit
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddSpeedOnHit1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8258), Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddSpeedOnHit1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8258), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddSpeedOnHit2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddSpeedOnHit1, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddSpeedOnHit2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddSpeedOnHit1, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddSpeedOnHit3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddSpeedOnHit2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddSpeedOnHit3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddSpeedOnHit2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //Jump on hit
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddJumpOnHit1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), Items.feather, Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddJumpOnHit1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), Items.feather, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddJumpOnHit2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddJumpOnHit1, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddJumpOnHit2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddJumpOnHit1, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddJumpOnHit3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddJumpOnHit2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddJumpOnHit3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddJumpOnHit2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //regeneration
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddRegeneration2, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyStimulatorExtractReg, Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddRegeneration2, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), ItemList.EnergyStimulatorExtractReg, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddRegeneration3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddRegeneration2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddRegeneration3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddRegeneration2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //extra damage
 //       ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddExtraDamage2, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8233), Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
 //       ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddExtraDamage2, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8233), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
 //       ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddExtraDamage3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddExtraDamage2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
 //       ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterAddExtraDamage3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterAddExtraDamage2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        //chance to restore
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRestoreHeartOnHit1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8229), Character.valueOf('R'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRestoreHeartOnHit1, 1), new Object[] {"CEC", "RDR", "CRC", Character.valueOf('D'), ItemList.UpdaterCore, Character.valueOf('E'), new ItemStack(Items.potionitem, 1, 8229), Character.valueOf('R'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('C'), IC2Items.getItem("electronicCircuit")});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRestoreHeartOnHit2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterRestoreHeartOnHit1, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRestoreHeartOnHit2, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterRestoreHeartOnHit1, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRestoreHeartOnHit3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterRestoreHeartOnHit2, Character.valueOf('R'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(UpdatersList.EnergyUpdaterRestoreHeartOnHit3, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), UpdatersList.EnergyUpdaterRestoreHeartOnHit2, Character.valueOf('R'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), ItemList.IridiumInlaidCircuit});
        
        }
        
        
        //circuits  
        //ultimate energy crystal
       if(Config.UltimateEnergyCraft == true){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UltimateEnergyCrystal, 1), new Object[] {"CRC", "RDR", "CRC", Character.valueOf('D'), IC2Items.getItem("iridiumPlate"), Character.valueOf('R'), lapotronCrystal, Character.valueOf('C'), ItemList.DiamondInlaidCircuit});
        }
        //saberparts
        if(Config.NuclearComponent == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.NuclearComponent, 1), new Object[] {"LRL", "LPL", "LLL",  Character.valueOf('L'), "plateLead", Character.valueOf('P'), IC2Items.getItem("Plutonium"), Character.valueOf('R'), IC2Items.getItem("reinforcedGlass")}));	
        }
        if(Config.EnergySaberCore == true){
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergySaberCore, 1), new Object[] {"CDC", "DND", "CDC",  Character.valueOf('C'), IC2Items.getItem("advancedAlloy"), Character.valueOf('N'), ItemList.NuclearComponent, Character.valueOf('D'), ItemList.DiamondInlaidCircuit});      	
        }
        if(Config.ModificatorCore == true){
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.ModificatorCore, 1), new Object[] {" ID", "MPC", " ID",  Character.valueOf('I'), new RecipeInputOreDict("plateIron"), Character.valueOf('D'), IC2Items.getItem("glassFiberCableItem"), Character.valueOf('C'), IC2Items.getItem("FluidCell"), Character.valueOf('P'), ItemList.DiamondInlaidCircuit, Character.valueOf('M'),new RecipeInputOreDict("plateCopper")});
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.ModificatorCore, 1), new Object[] {" ID", "MPC", " ID",  Character.valueOf('I'), new RecipeInputOreDict("plateIron"), Character.valueOf('D'), new RecipeInputOreDict("cableGt01Platinum"), Character.valueOf('C'), IC2Items.getItem("FluidCell"), Character.valueOf('P'), ItemList.DiamondInlaidCircuit, Character.valueOf('M'),new RecipeInputOreDict("plateCopper")});
        }
        if(Config.ColorModificatorBlue == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.ColorModificatorBlue, 1), new Object [] {"III", "ISI", "III",  Character.valueOf('I'), Blocks.glass, Character.valueOf('S'), "dustLapis"}));	      	
        }
        if(Config.ColorModificatorRed == true){
        	GameRegistry.addRecipe(new ItemStack(ItemList.ColorModificatorRed, 1), new Object [] {"III", "ISI", "III",  Character.valueOf('I'), Blocks.glass, Character.valueOf('S'), Items.redstone});	      	
        }
        if(Config.EnergySaberCraft == true){
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergySaber, 1), new Object[] {" C ", " E ", "ILI",  Character.valueOf('I'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), ItemList.ColorModificatorBlue, Character.valueOf('E'), ItemList.EnergySaberCore, Character.valueOf('L'), lapotronCrystal});
        	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergySaberRed, 1), new Object[] {" C ", " E ", "ILI",  Character.valueOf('I'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), ItemList.ColorModificatorRed, Character.valueOf('E'), ItemList.EnergySaberCore, Character.valueOf('L'), lapotronCrystal});	
        }
        if(Config.SaberModuleCraft == true){
        	
        	if(Config.ModulePoisonC == true){
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.PoisonModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8228)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.PoisonModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8228)});
        		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.PoisonModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8228)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.PoisonModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8228)});
        	}
        	if(Config.ModuleStunC == true){
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.StunModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8236)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.StunModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8236)});
        		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.StunModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8236)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.StunModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8236)});
        		
        	}
        	if(Config.ModuleWeakC == true){
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.WeakModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8264)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.WeakModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8264)});
        		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.WeakModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8264)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.WeakModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8264)});
        		
        	}
        	if(Config.ModuleHealC == true){
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.HealModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8229)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.HealModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8229)});
        		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.HealModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), battery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8229)});		
        		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.HealModule, 1), new Object[] {"FPF", "BCB", "DBD",  Character.valueOf('F'), new RecipeInputOreDict("cableGt01Platinum"),  Character.valueOf('B'), Chbattery,  Character.valueOf('D'), ItemList.DiamondInlaidCircuit,  Character.valueOf('C'), ItemList.ModificatorCore,  Character.valueOf('P'), new ItemStack(Items.potionitem, 1, 8229)});
        		
        	}
        	
        }

        //diamond circuit
        if(Config.DiamondCircuit == true){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.DiamondInlaidCircuit, 1), new Object[] {"CDC", "DRD", "CTC",  Character.valueOf('C'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('D'), IC2Items.getItem("industrialDiamond"), Character.valueOf('T'), new RecipeInputOreDict("craftingToolWireCutter")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.DiamondInlaidCircuit, 1), new Object[] {"CDC", "DRD", "CTC",  Character.valueOf('C'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('D'), IC2Items.getItem("industrialDiamond"), Character.valueOf('T'), new RecipeInputOreDict("craftingToolWireCutter")});
        
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.DiamondInlaidCircuit, 1), new Object[] {"CDC", "DRD", "CTC",  Character.valueOf('C'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('D'), Items.diamond, Character.valueOf('T'), new RecipeInputOreDict("craftingToolWireCutter")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.DiamondInlaidCircuit, 1), new Object[] {"CDC", "DRD", "CTC",  Character.valueOf('C'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('D'), Items.diamond, Character.valueOf('T'), new RecipeInputOreDict("craftingToolWireCutter")});
        
        }
        //extract recipes
        if(Config.ExtractStrength == true)
        ic2.api.recipe.Recipes.extractor.addRecipe(new RecipeInputItemStack(new ItemStack(Items.potionitem, 1, 8233)), null, new ItemStack(ItemList.EnergyStimulatorExtractStr, 1));
        if(Config.ExtractRegeneration == true)
        ic2.api.recipe.Recipes.extractor.addRecipe(new RecipeInputItemStack(new ItemStack(Items.potionitem, 1, 8225)), null, new ItemStack(ItemList.EnergyStimulatorExtractReg, 1));
        if(Config.ExtractDefence == true)
        ic2.api.recipe.Recipes.extractor.addRecipe(new RecipeInputItemStack(new ItemStack(Items.potionitem, 1, 8259)), null, new ItemStack(ItemList.EnergyStimulatorExtractDef, 1));
        //shields parts
        if(Config.EnergyShieldMagnet == true)
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.EnergyShieldMagnet, 1), new Object [] {"IGI", "GEG", "IGI", Character.valueOf('I'), "plateLead", Character.valueOf('G'), IC2Items.getItem("advancedAlloy"), Character.valueOf('E'), "plateDenseIron"}));
        if(Config.EnergyShieldMagnetCoil == true)
    	GameRegistry.addRecipe(new ItemStack(ItemList.EnergyShieldMagnetCoil, 1), new Object [] {"III", "IEI", "III", Character.valueOf('I'), IC2Items.getItem("coil"),Character.valueOf('E'), ItemList.EnergyShieldMagnet});
        if(Config.EnergyShieldCore == true)
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.EnergyShieldCore, 1), new Object [] {"IGI", "GEG", "IGI", Character.valueOf('I'), "plateIron", Character.valueOf('G'), ItemList.EnergyShieldMagnetCoil, Character.valueOf('E'), IC2Items.getItem("advancedCircuit")}));
        //iridium circuit
        if(Config.IridiumCircuit == true){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.IridiumInlaidCircuit, 1), new Object[] {"CDC", "DRD", "CTC",  Character.valueOf('C'), IC2Items.getItem("insulatedGoldCableItem"), Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('D'), IC2Items.getItem("iridiumOre"), Character.valueOf('T'), new RecipeInputOreDict("craftingToolWireCutter")});
        ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.IridiumInlaidCircuit, 1), new Object[] {"CDC", "DRD", "CTC",  Character.valueOf('C'), new RecipeInputOreDict("cableGt01Gold"), Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('D'), IC2Items.getItem("iridiumOre"), Character.valueOf('T'), new RecipeInputOreDict("craftingToolWireCutter")});
        }      
        //core recipes
        if(Config.StrengthCore == true)
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.EnergyStimulatorCoreStr, 1), new Object [] {"ICI", "GEG", "ICI", Character.valueOf('I'), "plateIron", Character.valueOf('C'), IC2Items.getItem("electronicCircuit"), Character.valueOf('G'), Blocks.glass, Character.valueOf('E'), ItemList.EnergyStimulatorExtractStr}));
        if(Config.RegenerationCore == true)
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.EnergyStimulatorCoreReg, 1), new Object [] {"ICI", "GEG", "ICI", Character.valueOf('I'), "plateIron", Character.valueOf('C'), IC2Items.getItem("electronicCircuit"), Character.valueOf('G'), Blocks.glass, Character.valueOf('E'), ItemList.EnergyStimulatorExtractReg}));
        if(Config.DefenceCore == true)
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.EnergyStimulatorCoreDef, 1), new Object [] {"ICI", "GEG", "ICI", Character.valueOf('I'), "plateIron", Character.valueOf('C'), IC2Items.getItem("electronicCircuit"), Character.valueOf('G'), Blocks.glass, Character.valueOf('E'), ItemList.EnergyStimulatorExtractDef}));
        //stimlvl1
        //stimulator recipes lvl1
        if(Config.StimulatorStrengthLvl1 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorStr1, 1), new Object[] {"ERE", "RCR", "ESE",  Character.valueOf('S'), battery, Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), ItemList.EnergyStimulatorCoreStr, Character.valueOf('E'), new RecipeInputOreDict("plateGold")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorStr1, 1), new Object[] {"ERE", "RCR", "ESE",  Character.valueOf('S'), Chbattery, Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), ItemList.EnergyStimulatorCoreStr, Character.valueOf('E'), new RecipeInputOreDict("plateGold")});
        }
        if(Config.StimulatorRegenerationLvl1 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorReg1, 1), new Object[] {"ERE", "RCR", "ESE",  Character.valueOf('S'), battery, Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), ItemList.EnergyStimulatorCoreReg, Character.valueOf('E'), new RecipeInputOreDict("plateGold")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorReg1, 1), new Object[] {"ERE", "RCR", "ESE",  Character.valueOf('S'), Chbattery, Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), ItemList.EnergyStimulatorCoreReg, Character.valueOf('E'), new RecipeInputOreDict("plateGold")});
        }
        if(Config.StimulatorDefenceLvl1 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorDef1, 1), new Object[] {"ERE", "RCR", "ESE",  Character.valueOf('S'), battery, Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), ItemList.EnergyStimulatorCoreDef, Character.valueOf('E'), new RecipeInputOreDict("plateGold")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorDef1, 1), new Object[] {"ERE", "RCR", "ESE",  Character.valueOf('S'), Chbattery, Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), ItemList.EnergyStimulatorCoreDef, Character.valueOf('E'), new RecipeInputOreDict("plateGold")});
        }
		//stimulator recipes lvl2
        if(Config.StimulatorStrengthLvl2 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorStr2, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), energyCrystal, Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), StimulatorStr1, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorStr2, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), energyCrystal, Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), StimulatorStr1, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        if(Config.StimulatorRegenerationLvl2 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorReg2, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), energyCrystal, Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), StimulatorReg1, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorReg2, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), energyCrystal, Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), StimulatorReg1, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        if(Config.StimulatorDefenceLvl2 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorDef2, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), energyCrystal, Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), StimulatorReg1, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorDef2, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), energyCrystal, Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), StimulatorReg1, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        //stimulator recipes lvl3
        if(Config.StimulatorStrengthLvl3 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorStr3, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), lapotronCrystal, Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), StimulatorStr2, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorStr3, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), lapotronCrystal, Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), StimulatorStr2, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        if(Config.StimulatorRegenerationLvl3 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorReg3, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), lapotronCrystal, Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), StimulatorReg2, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorReg3, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), lapotronCrystal, Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), StimulatorReg2, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        if(Config.StimulatorDefenceLvl3 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorDef3, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), lapotronCrystal, Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), StimulatorDef2, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyStimulatorDef3, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), lapotronCrystal, Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), StimulatorDef2, Character.valueOf('E'), new RecipeInputOreDict("plateDenseGold"), Character.valueOf('D'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        //Ultimate Stimulators
        if(Config.UltimateStimulatorLvl1 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UltimateStimulator1, 1), new Object[] {"EDE", "RCL", "ESE",  Character.valueOf('S'), new RecipeInputOreDict("craftingToolWireCutter"), Character.valueOf('R'), StimulatorStr1, Character.valueOf('D'), StimulatorReg1, Character.valueOf('L'), StimulatorDef1, Character.valueOf('E'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UltimateStimulator1, 1), new Object[] {"EDE", "RCL", "ESE",  Character.valueOf('S'), new RecipeInputOreDict("craftingToolWireCutter"), Character.valueOf('R'), StimulatorStr1, Character.valueOf('D'), StimulatorReg1, Character.valueOf('L'), StimulatorDef1, Character.valueOf('E'), IC2Items.getItem("advancedCircuit"), Character.valueOf('C'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        if(Config.UltimateStimulatorLvl2 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UltimateStimulator2, 1), new Object[] {"EDE", "RCL", "ESE",  Character.valueOf('S'), new RecipeInputOreDict("craftingToolWireCutter"), Character.valueOf('R'), StimulatorStr2, Character.valueOf('D'), StimulatorReg2, Character.valueOf('L'), StimulatorDef2, Character.valueOf('E'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UltimateStimulator2, 1), new Object[] {"EDE", "RCL", "ESE",  Character.valueOf('S'), new RecipeInputOreDict("craftingToolWireCutter"), Character.valueOf('R'), StimulatorStr2, Character.valueOf('D'), StimulatorReg2, Character.valueOf('L'), StimulatorDef2, Character.valueOf('E'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        if(Config.UltimateStimulatorLvl3 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UltimateStimulator3, 1), new Object[] {"EDE", "RCL", "ESE",  Character.valueOf('S'), new RecipeInputOreDict("craftingToolWireCutter"), Character.valueOf('R'), StimulatorStr3, Character.valueOf('D'), StimulatorReg3, Character.valueOf('L'), StimulatorDef3, Character.valueOf('E'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), IC2Items.getItem("glassFiberCableItem")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.UltimateStimulator3, 1), new Object[] {"EDE", "RCL", "ESE",  Character.valueOf('S'), new RecipeInputOreDict("craftingToolWireCutter"), Character.valueOf('R'), StimulatorStr3, Character.valueOf('D'), StimulatorReg3, Character.valueOf('L'), StimulatorDef3, Character.valueOf('E'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), new RecipeInputOreDict("cableGt01Platinum")});
        }
        //EnergyShields
        if(Config.EnergyShieldLvl1 == true){
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyShield1, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), battery, Character.valueOf('R'), IC2Items.getItem("reactorPlatingHeat"), Character.valueOf('C'), ItemList.EnergyShieldCore, Character.valueOf('E'), IC2Items.getItem("transformerUpgrade"), Character.valueOf('D'), IC2Items.getItem("advancedCircuit")});
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyShield1, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), Chbattery, Character.valueOf('R'), IC2Items.getItem("reactorPlatingHeat"), Character.valueOf('C'), ItemList.EnergyShieldCore, Character.valueOf('E'), IC2Items.getItem("transformerUpgrade"), Character.valueOf('D'), IC2Items.getItem("advancedCircuit")});
        }
        if(Config.EnergyShieldLvl2 == true)
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyShield2, 1), new Object[] {"ERE", "RCR", "ERE",  Character.valueOf('R'), ItemList.DiamondInlaidCircuit, Character.valueOf('C'), EnergySh1, Character.valueOf('E'), energyCrystal});
        if(Config.EnergyShieldLvl3 == true)
		ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyShield3, 1), new Object[] {"ERE", "RCR", "ERE",  Character.valueOf('R'), ItemList.IridiumInlaidCircuit, Character.valueOf('C'), EnergySh2, Character.valueOf('E'), lapotronCrystal});
        //energybar
        if(Config.EnergyBar == true){
    	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyBar, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), battery, Character.valueOf('R'), new RecipeInputOreDict("plateIron"), Character.valueOf('C'), Items.bucket, Character.valueOf('E'), IC2Items.getItem("electronicCircuit"), Character.valueOf('D'), IC2Items.getItem("advancedCircuit")});
    	ic2.api.recipe.Recipes.advRecipes.addRecipe(new ItemStack(ItemList.EnergyBar, 1), new Object[] {"EDE", "RCR", "ESE",  Character.valueOf('S'), Chbattery, Character.valueOf('R'), new RecipeInputOreDict("plateIron"), Character.valueOf('C'), Items.bucket, Character.valueOf('E'), IC2Items.getItem("electronicCircuit"), Character.valueOf('D'), IC2Items.getItem("advancedCircuit")});
        }
        
  
	}


	


}
