package com.thunder.Items;

import java.util.List;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.typesafe.config.ConfigException.Generic;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyStimulatorDef1 extends ElectricItemBase{
	
	private ChatComponentText chat;
	private int power = Config.DefStimLvl1EffectPower + 1;

	public IEnergyStimulatorDef1(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = Config.DefStimLvl1maxCharge;
		this.transferLimit = Config.DefStimLvl1TransferLimit;
		this.tier = Config.DefStimLvl1Tier;
		
	}
	
	public ItemStack onItemRightClick(ItemStack Item, World world, EntityPlayer player)
	{
		if (!isSimulating()) {
		      return Item;
		}
		if(ElectricItem.manager.use(Item, Config.DefStimLvl1ChargeToUse, player))
		{
		
			if(!world.isRemote){
			chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.general.stim.feelthepower"));
			player.addChatComponentMessage(chat);
			}
			
			player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 20*Config.DefStimLvl1EffectDuration, Config.DefStimLvl1EffectPower));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 20*Config.DefStimLvl1EffectDuration, Config.DefStimLvl1EffectPower));
		      
		}
		else{
			
			if(!world.isRemote){
				chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.general.noteboughtenergy"));
				player.addChatComponentMessage(chat);
				}
			
		}
	        return Item;
	}
	

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack Item, EntityPlayer player, List par3List, boolean par4)
	{		
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.general.onright"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.effect.defence")+power+StatCollector.translateToLocal("tag.items.stim.on")+Config.DefStimLvl1EffectDuration+ StatCollector.translateToLocal("tag.items.stim.sec"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.effect.fireprotection")+power+StatCollector.translateToLocal("tag.items.stim.on")+Config.DefStimLvl1EffectDuration+ StatCollector.translateToLocal("tag.items.stim.sec"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.consumes")+Config.DefStimLvl1ChargeToUse+" Eu");
	    par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.tier") + this.tier);
	}
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	  {
	    return EnumRarity.uncommon;
	 }
	  
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	  public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	  
	
	

}