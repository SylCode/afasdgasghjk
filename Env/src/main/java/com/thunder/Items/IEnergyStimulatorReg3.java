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

public class IEnergyStimulatorReg3 extends ElectricItemBase{
	
	private ChatComponentText chat;
	private int power = Config.RegStimLvl3EffectPower + 1;

	public IEnergyStimulatorReg3(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = Config.RegStimLvl3maxCharge;
		this.transferLimit = Config.RegStimLvl3TransferLimit;
		this.tier = Config.RegStimLvl3Tier;
		
	}
	
	public ItemStack onItemRightClick(ItemStack Item, World world, EntityPlayer player)
	{
		if (!isSimulating()) {
		      return Item;
		}
		if(ElectricItem.manager.use(Item, Config.RegStimLvl3ChargeToUse, player))
		{
		
			if(!world.isRemote){
			chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.general.stim.feelthepower"));
			player.addChatComponentMessage(chat);
			}
			
			player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 20*Config.RegStimLvl3EffectDuration, Config.RegStimLvl3EffectPower));
		      
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
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.effect.regeneration")+power+StatCollector.translateToLocal("tag.items.stim.on")+Config.RegStimLvl3EffectDuration+StatCollector.translateToLocal("tag.items.stim.sec"));
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.consumes")+Config.RegStimLvl3ChargeToUse+" Eu");
	    par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.tier") + this.tier);
	}
	
	  @SideOnly(Side.CLIENT)
	  public EnumRarity getRarity(ItemStack stack)
	  {
	    return EnumRarity.epic;
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
