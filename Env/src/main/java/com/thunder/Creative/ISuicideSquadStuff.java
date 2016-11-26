package com.thunder.Creative;

import java.util.HashMap;
import java.util.List;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ISuicideSquadStuff extends Item {
	
	private boolean itemUsed = false;
	
	public ISuicideSquadStuff(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditionsCreative);
		this.setMaxStackSize(1);
		
		MinecraftForge.EVENT_BUS.register(this);    
		FMLCommonHandler.instance().bus().register(this); 
	}
	
	
	public ItemStack onItemRightClick(ItemStack stuff, World world, EntityPlayer player)
	{    
		if (!isSimulating()) {
			return super.onItemRightClick(stuff, world, player);
		}
		
		
		
		itemUsed = true;
		player.attackEntityFrom(DamageSource.generic, 500.0f);

	    return super.onItemRightClick(stuff, world, player);
		
	}
	
	public boolean getStuffStatus(){
						
		return this.itemUsed;
	}
	
	public void setStuffStatus(boolean b){
		
		this.itemUsed = b;
	}
		
	
	public void onUpdate(ItemStack stuff, World world, Entity entity, int slot, boolean par5)
	  {
		 if(entity instanceof EntityPlayer){
			 
			 EntityPlayer player = (EntityPlayer)entity;		 
			 NBTTagCompound stuffData = getOrCreateNbtData(stuff);
			 
			 
			 if(stuffData.getString("stuffOwner").equals("")){
				 
				 stuffData.setString("stuffOwner", player.getDisplayName());
				 updateAttributes(stuffData);
			 }		 
		 }		 
		 
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
	 
	 private static void updateAttributes(NBTTagCompound stuffData)
	  {
	    String stuffOwner = stuffData.getString("stuffOwner");
	    
	  }
	 
	 public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	 
 
	
	@SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack stuff, EntityPlayer player, List par3List, boolean par4)
		{
			par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tag.creative.relic"));
			NBTTagCompound stuffData = getOrCreateNbtData(stuff);
			if(player.getDisplayName().equals(stuffData.getString("stuffOwner")))
			par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.GREEN + stuffData.getString("stuffOwner"));
			else
			par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.RED + stuffData.getString("stuffOwner"));
		}
		
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	public boolean isFull3D(){
		
		return true;
	}
	
	
	

}
