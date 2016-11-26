package com.thunder.Items;

import java.util.List;

import com.thunder.Api.IEnergyAdd;
import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.InventoryEnergyPlayer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyBar extends ElectricItemBase{
	
	private ChatComponentText chat;
	
	private double consume = Config.EnergyBarUsesCharge;

	public IEnergyBar(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = Config.EnergyBarmaxCharge;
		this.transferLimit = Config.EnergyBarTransferLimit;
		this.tier = Config.EnergyBarTier;
		
	}
	
	public ItemStack onItemRightClick(ItemStack Item, World world, EntityPlayer player)
	{    
		if (!isSimulating()) {
		      return Item;
		}
		if(ElectricItem.manager.use(Item, Config.EnergyBarUsesCharge, player))
		{		
			player.getFoodStats().addStats(Config.EnergyBarFoodPoints, 2.4F);
			return Item; 
			
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
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.food.tooltip")+Config.EnergyBarFoodPoints);
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.stim.consumes")+consume+" Eu");
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