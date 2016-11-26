package com.thunder.Items;

import java.util.List;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyMicrox extends Item {
	
	public IEnergyMicrox(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setMaxStackSize(1);
		
	}
	
	 public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityplayer)
	  {
	    if (!isSimulating()) {
	      return itemStack;
	    }
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (!nbtData.getBoolean("act"))
	    {
	      nbtData.setBoolean("act", true);
	      nbtData.setInteger("timer", 0);
	      updateAttributes(nbtData);
	    }

	    return super.onItemRightClick(itemStack, world, entityplayer);
	  }
	 
	 public static int ticker = 0;

	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean par5)
	 {
	
		
		NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
		if (nbtData.getBoolean("act"))
	    {
		int timer = nbtData.getInteger("timer");
		timer += 1;
		nbtData.setInteger("timer", timer);
		updateAttributes(nbtData);
	    }
		
		
		if(nbtData.getInteger("timer") == 100){
			
			if(entity instanceof EntityPlayer){
				
					EntityPlayer player = (EntityPlayer)entity;
					
					if(player.getHealth() >= 2.0f){
					player.setHealth(2.0f);
					}
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 7800, 11));
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 7800, 11));
					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 7800, 11));
					player.addPotionEffect(new PotionEffect(Potion.weakness.id, 7800, 11));
					player.addPotionEffect(new PotionEffect(Potion.hunger.id, 7800, 11));
					player.inventory.consumeInventoryItem(ItemList.EnergyMicrox);
					nbtData.setInteger("timer", 0);
					updateAttributes(nbtData);
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
	  
	
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
	{
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.microxtooltip"));
		
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
		 
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}
	
	public boolean isSimulating()
	{
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	}
	
	private static void updateAttributes(NBTTagCompound nbtData)
	{
	    boolean active = nbtData.getBoolean("act");
	    int timer = nbtData.getInteger("timer");
	    
	}
	

}
