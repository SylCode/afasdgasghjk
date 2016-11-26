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

public class IUltimateStimulator2 extends ElectricItemBase{
	
	private ChatComponentText chat;
	private int strpower = Config.UltStimLvl2EffectStrengthPower + 1;
	private int regpower = Config.UltStimLvl2EffectRegenerationPower + 1;
	private int defpower = Config.UltStimLvl2EffectDefencePower + 1;

	public IUltimateStimulator2 (){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = Config.UltStimLvl2maxCharge;
		this.transferLimit = Config.UltStimLvl2TransferLimit;
		this.tier = Config.UltStimLvl2Tier;
		
	}
	
	public ItemStack onItemRightClick(ItemStack Item, World world, EntityPlayer player)
	{
		if (!isSimulating()) {
		      return Item;
		}
		if(ElectricItem.manager.use(Item, Config.UltStimLvl2ChargeToUse, player))
		{
		
			if(!world.isRemote){
			chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.general.stim.feelthepower"));
			player.addChatComponentMessage(chat);
			}
			
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 20*Config.UltStimLvl2EffectStrengthDuration, Config.UltStimLvl2EffectStrengthPower));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 20*Config.UltStimLvl2EffectRegenerationDuration, Config.UltStimLvl2EffectRegenerationPower));
			player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 20*Config.UltStimLvl2EffectDefenceDuration, Config.UltStimLvl2EffectDefencePower));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 20*Config.UltStimLvl2EffectDefenceDuration, Config.UltStimLvl2EffectDefencePower));
		      
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
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.effect.strength")+strpower+StatCollector.translateToLocal("tag.items.stim.on")+Config.UltStimLvl2EffectStrengthDuration+StatCollector.translateToLocal("tag.items.stim.sec"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.effect.regeneration")+regpower+StatCollector.translateToLocal("tag.items.stim.on")+Config.UltStimLvl2EffectRegenerationDuration+StatCollector.translateToLocal("tag.items.stim.sec"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.effect.defence")+defpower+StatCollector.translateToLocal("tag.items.stim.on")+Config.UltStimLvl2EffectDefenceDuration+StatCollector.translateToLocal("tag.items.stim.sec"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.effect.fireprotection")+defpower+StatCollector.translateToLocal("tag.items.stim.on")+Config.UltStimLvl2EffectDefenceDuration+StatCollector.translateToLocal("tag.items.stim.sec"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.consumes")+Config.UltStimLvl2ChargeToUse+" Eu");
	    par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.tier") + this.tier);
	}
	
	  @SideOnly(Side.CLIENT)
	  public EnumRarity getRarity(ItemStack stack)
	  {
	    return EnumRarity.rare;
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
