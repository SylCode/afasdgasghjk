package com.thunder.Creative;

import java.util.List;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ISoulAmulet extends Item {
	
	public ISoulAmulet(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditionsCreative);

		this.setMaxStackSize(1);
	}
	
	

	public void onUpdate(ItemStack amulet, World world, Entity entity, int slot, boolean par5)
	  {
		 if(entity instanceof EntityPlayer){
			 
			 EntityPlayer player = (EntityPlayer)entity;		 
			 NBTTagCompound amuletData = getOrCreateNbtData(amulet);
			 
			 
			 if(amuletData.getString("amuletOwner").equals("")){
				 
				 amuletData.setString("amuletOwner", player.getDisplayName());
				 updateAttributes(amuletData);
			 }
			 

		 }		 
		 
	  }
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack amulet, EntityPlayer player, List par3List, boolean par4)
	{
		par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tag.creative.relic"));
		
		NBTTagCompound amuletData = getOrCreateNbtData(amulet);
		
		if(player.getDisplayName().equals(amuletData.getString("amuletOwner")))
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.GREEN + amuletData.getString("amuletOwner"));
		else
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.RED + amuletData.getString("amuletOwner"));
		

	}
	
	public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack)
	  {
	    NBTTagCompound ret = itemStack.getTagCompound();
	    if (ret == null)
	    {
	      ret = new NBTTagCompound();
	      
	      itemStack.setTagCompound(ret);
	    }
	    return ret;
	  }
	  
	private static void updateAttributes(NBTTagCompound nbtData)
	  {
	    String amuletOwner = nbtData.getString("amuletOwner");
	    
	  }

}
