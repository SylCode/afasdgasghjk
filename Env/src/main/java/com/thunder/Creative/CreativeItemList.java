package com.thunder.Creative;

import com.thunder.Armor.IEnergyArmor;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.Items.IDiamondInlaidCircuit;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class CreativeItemList {
	
	private static String Gandiva = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.Gandiva.name");
	private static String SuicideSquad = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.SuicideSquad.name");
	private static String IonisSwordS = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.IonisSword.name");
	private static String TritonHelmetS = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.TritonHelmet.name");
	private static String PainChestPlateS = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.PainChestPlate.name");
	private static String WalkerLeggingsS = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.WalkerLeggings.name");
	private static String RedBoolBootsS = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.RedBoolBoots.name");
	private static String SoulAmuletS = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.SoulAmulet.name");
	private static String VampiricElixirS = EnumChatFormatting.BOLD + StatCollector.translateToLocal("creative.VampiricElixir.name");
	
	public static Item GundiveBow;
	public static Item SuicideSquadStuff;
	public static Item IonisSword;
	public static Item SoulAmulet;
	
	public static ItemArmor TritonHelmet;
	public static ItemArmor PainChestPlate;
	public static ItemArmor WalkerLeggings;
	public static ItemArmor RedBoolBoots;
	
	public static Item VampiricElixir;
	
	public static void items(){
		
		GundiveBow = new IGundiveBow().setUnlocalizedName("GundiveBow");
	    GameRegistry.registerItem(GundiveBow, "GundiveBow");
	    LanguageRegistry.addName(GundiveBow, EnumChatFormatting.GOLD + Gandiva);
	    
	    SuicideSquadStuff = new ISuicideSquadStuff().setUnlocalizedName("SuicideSquadStuff");
	    GameRegistry.registerItem(SuicideSquadStuff, "SuicideSquadStuff");
	    LanguageRegistry.addName(SuicideSquadStuff, EnumChatFormatting.GOLD + SuicideSquad);
	    
	    IonisSword = new IIonisSword(ToolMaterial.STONE).setUnlocalizedName("IonisSword");
	    GameRegistry.registerItem(IonisSword, "IonisSword");
	    LanguageRegistry.addName(IonisSword, EnumChatFormatting.GOLD + IonisSwordS);
	    
	    SoulAmulet = new ISoulAmulet().setUnlocalizedName("SoulAmulet");
	    GameRegistry.registerItem(SoulAmulet, "SoulAmulet");
	    LanguageRegistry.addName(SoulAmulet, EnumChatFormatting.GOLD + SoulAmuletS);
	    
	    VampiricElixir = new IVampiricElixir().setUnlocalizedName("VampiricElixir");
	    GameRegistry.registerItem(VampiricElixir, "VampiricElixir");
	    LanguageRegistry.addName(VampiricElixir, EnumChatFormatting.DARK_AQUA + VampiricElixirS);
	    
	    
	    //armor
	    
	    TritonHelmet = (ItemArmor) new ICreativeArmor(EnergyAdditionsCore.CreativeArmorM, 3, 0).setUnlocalizedName("TritonHelmet");
		GameRegistry.registerItem(TritonHelmet, "TritonHelmet");
		LanguageRegistry.addName(TritonHelmet, EnumChatFormatting.GOLD + TritonHelmetS);
		
		PainChestPlate = (ItemArmor) new ICreativeArmor(EnergyAdditionsCore.CreativeArmorM, 3, 1).setUnlocalizedName("PainChestPlate");
		GameRegistry.registerItem(PainChestPlate, "PainChestPlate");
		LanguageRegistry.addName(PainChestPlate, EnumChatFormatting.GOLD + PainChestPlateS);
		
		WalkerLeggings = (ItemArmor) new ICreativeArmor(EnergyAdditionsCore.CreativeArmorM, 3, 2).setUnlocalizedName("WalkerLeggings");
		GameRegistry.registerItem(WalkerLeggings, "WalkerLeggings");
		LanguageRegistry.addName(WalkerLeggings, EnumChatFormatting.GOLD + WalkerLeggingsS);
		
		RedBoolBoots = (ItemArmor) new ICreativeArmor(EnergyAdditionsCore.CreativeArmorM, 3,3).setUnlocalizedName("RedBoolBoots");
		GameRegistry.registerItem(RedBoolBoots, "RedBoolBoots");
		LanguageRegistry.addName(RedBoolBoots, EnumChatFormatting.GOLD + RedBoolBootsS);	
		
	}

}
