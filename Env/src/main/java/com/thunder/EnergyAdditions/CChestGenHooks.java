package com.thunder.EnergyAdditions;

import java.util.Iterator;

import com.thunder.Updaters.UpdatersList;

import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;

public class CChestGenHooks {
	
		public static void Remove(){
			
			
			ItemStack iridOre = IC2Items.getItem("iridiumOre").copy();
			ItemStack iridShard = IC2Items.getItem("iridiumShard").copy();
			
			
			if(Config.IridiumRemove == true){
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).removeItem(iridOre);
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).removeItem(iridOre);
			
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).removeItem(iridShard);
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).removeItem(iridShard);
			}
			
			
			if(Config.SomeUpdatersSpawning == true){
				
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterMine2), 0, 1, 2));

			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterPoisonAllAround), 0, 1, 2));

			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterBlind2), 0, 1, 2));

			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterUnderWater), 0, 1, 4));

			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 1));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterRegenerationN3), 0, 1, 1));

			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 3));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 2));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(UpdatersList.EnergyUpdaterSlowdown3), 0, 1, 1));

			
			
			}
			
		}
}
