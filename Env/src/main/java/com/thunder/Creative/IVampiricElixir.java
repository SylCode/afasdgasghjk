package com.thunder.Creative;

import java.util.List;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IVampiricElixir extends Item {
	
	public  IVampiricElixir(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditionsCreative);
		this.setMaxStackSize(16);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	
	public ItemStack onItemRightClick(ItemStack Item, World world, EntityPlayer player)
	{ 
		if (!isSimulating()) {
		      return super.onItemRightClick(Item, world, player);
		}
		
		if(player.isPotionActive(CreativePotionList.Vamp))
		{
			player.removePotionEffect(CreativePotionList.Vamp.id);
			player.inventory.consumeInventoryItem(CreativeItemList.VampiricElixir);
			
		}
		
		return super.onItemRightClick(Item, world, player);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack Item, EntityPlayer player, List par3List, boolean par4)
	{
	    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.elixir.effects"));
	}
	
	public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	

}