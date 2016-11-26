package com.thunder.Items;

import java.util.List;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.typesafe.config.ConfigException.Generic;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class IEnergyShield1 extends ElectricItemBase{
	
	private ChatComponentText chat;	
	
	public IEnergyShield1(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = Config.ShLvl1maxCharge;
		this.transferLimit = Config.ShLvl1TransferLimit;
		this.tier = Config.ShLvl1Tier;
		
		
		
		 MinecraftForge.EVENT_BUS.register(this);
	}
	
	public boolean isActive(ItemStack itemStack)
	{
		NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
		if (nbtData.getBoolean("active"))
			return true;
		return false;
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (!isSimulating()) {
		      return itemStack;
		}
		NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (nbtData.getBoolean("active"))
	    {
	      nbtData.setBoolean("active", false);
	      updateAttributes(nbtData);
	      if(!world.isRemote){
	      chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.shield.unactivated"));
		  player.addChatComponentMessage(chat);
	      }
	    }
	    else if (ElectricItem.manager.canUse(itemStack, 10.0D))//here
	    {
	      nbtData.setBoolean("active", true);
	      updateAttributes(nbtData);
	      if (nbtData.getBoolean("message")){
		    	if(!world.isRemote){
			   	      chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.shield.alldisabled"));
			   		  player.addChatComponentMessage(chat);
			   	      }
		  nbtData.setBoolean("message", false);
		  updateAttributes(nbtData);
  
	      }else if (!world.isRemote){
	    	  chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.shield.activated"));
	  		  player.addChatComponentMessage(chat);
	    	  
	      }
	      
	    } else
	    	 if(!world.isRemote){
	   	      chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.general.noteboughtenergy"));
	   		  player.addChatComponentMessage(chat);
	   	      }
	    	
	    return super.onItemRightClick(itemStack, world, player);
	}
	
	public static int ticker = 0;
	
	 public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean par5)
	  {
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (!nbtData.getBoolean("active")) {
	      return;
	    }
	    	    
	    if (((ticker % 16 == 0) && (entity instanceof EntityPlayerMP))) {
	    	EntityPlayer player = (EntityPlayer)entity;
	    	//
	    	int count = 0;
			for (int slot1 = 0; slot1 < player.inventory.getSizeInventory(); slot1++)//
			{
				ItemStack Stack = player.inventory.getStackInSlot(slot1);//
				
				if (Stack != null && Stack.getItem().equals(ItemList.EnergyShield1))
				{
					count = count + 1;
				}
								
			}
			if(count > 1){
				   
				if(!nbtData.getBoolean("message")){
			    	nbtData.setBoolean("message", true);
			    	updateAttributes(nbtData);
			    }

			    if (nbtData.getBoolean("active"))
			    {
			    nbtData.setBoolean("active", false);
			    updateAttributes(nbtData);
			    return;
			    }
			    
			}
	    }


	    }


	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
	{
		if(GuiScreen.isShiftKeyDown()){
			NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
			if(nbtData.getBoolean("active")){
				par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.enabled") );
			}else 
			{
				par3List.add(EnumChatFormatting.RED +  StatCollector.translateToLocal("tag.items.general.disabled"));
			}
		    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.general.onright"));
		    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.shield.tooltip.1"));
		    par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.tier") + this.tier);
		    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.shield.tooltip.2"));

		    if(Config.ShLvl1DamageTypeProjectile)
		    par3List.add(EnumChatFormatting.GRAY +StatCollector.translateToLocal("tag.items.shield.damage.projectile")	+"(x20): " + EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.shield.damageblock.yes"));
		    else par3List.add(EnumChatFormatting.GRAY +StatCollector.translateToLocal("tag.items.shield.damage.projectile")	+"(x20): " + EnumChatFormatting.RED + StatCollector.translateToLocal("tag.items.shield.damageblock.no"));
		    if(Config.ShLvl1DamageTypeFireDamage)
		    par3List.add(EnumChatFormatting.GRAY +StatCollector.translateToLocal("tag.items.shield.damage.firedamage") +"(x5): " + EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.shield.damageblock.yes"));
		    else par3List.add(EnumChatFormatting.GRAY +StatCollector.translateToLocal("tag.items.shield.damage.firedamage") +"(x5): " + EnumChatFormatting.RED + StatCollector.translateToLocal("tag.items.shield.damageblock.no"));
		    if(Config.ShLvl1DamageTypeFall)
		    par3List.add(EnumChatFormatting.GRAY +StatCollector.translateToLocal("tag.items.shield.damage.fall") +"(x100): " + EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.shield.damageblock.yes"));
		    else par3List.add(EnumChatFormatting.GRAY +StatCollector.translateToLocal("tag.items.shield.damage.fall") +"(x100): " + EnumChatFormatting.RED + StatCollector.translateToLocal("tag.items.shield.damageblock.no"));

			} else{
				NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
				if(nbtData.getBoolean("active")){
					par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.enabled") );
				}else 
				{
					par3List.add(EnumChatFormatting.RED +  StatCollector.translateToLocal("tag.items.general.disabled"));
				}
			    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.general.onright"));
			    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.shield.tooltip.1"));
			    par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.tier") + this.tier);
			    
			    par3List.add(StatCollector.translateToLocal("tag.items.shield.shift"));
			}
			
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;
	  
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		
		 this.textures = new IIcon[2];
		    
		    this.textures[0] = iconRegister.registerIcon("thunder:" + getUnlocalizedName().substring(5));
		    this.textures[1] = iconRegister.registerIcon("thunder:" + getUnlocalizedName().substring(5) + "Active");
	}
	
	@SideOnly(Side.CLIENT)
	  public boolean requiresMultipleRenderPasses()
	  {
	    return true;
	  }
	
	 @SideOnly(Side.CLIENT)
	  public IIcon getIcon(ItemStack itemStack, int pass)
	  {
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (nbtData.getBoolean("active")) {
	      return this.textures[1];
	    }
	    return this.textures[0];
	  }
	 
	@SubscribeEvent
	public void EventShield(LivingAttackEvent event) {


		if (event.entityLiving instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			
			ItemStack equipped = player.getHeldItem();
			ItemStack thisitem = new ItemStack(ItemList.EnergyShield1);
			
			if(equipped != null && equipped.getItem() == thisitem.getItem()){
				
				
				NBTTagCompound nbtData = getOrCreateNbtData(equipped);
				
				if(nbtData.getBoolean("active")){

				double a = event.ammount;
				//events-------------------------
		        
		        if(event.source.isProjectile() && Config.ShLvl1DamageTypeProjectile == true && ElectricItem.manager.canUse(equipped, a*20)){
		        	drainShield(equipped, a*20, player);
					event.setCanceled(true);	
					chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.shield.consumed") + a*20 + " EU");
					player.addChatComponentMessage(chat);
				}
		        
		        if(event.source.isFireDamage() && Config.ShLvl1DamageTypeFireDamage == true && ElectricItem.manager.canUse(equipped, a*5)){
		        	drainShield(equipped, a*5, player);
					event.setCanceled(true);
					chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.shield.consumed") + a*5 + " EU");
					player.addChatComponentMessage(chat);
				}
		        
		        if(event.source == DamageSource.fall && Config.ShLvl1DamageTypeFall == true && ElectricItem.manager.canUse(equipped, a*100)){
		        	drainShield(equipped, a*100, player);
					event.setCanceled(true);	
					chat = new ChatComponentText(StatCollector.translateToLocal("tag.items.shield.consumed") + a*100 + " EU");
					player.addChatComponentMessage(chat);
				}
		
				//events-------------------------
				
			}
				
		}
	}

		
}
	 
	 

	  @SideOnly(Side.CLIENT)
	  public EnumRarity getRarity(ItemStack stack)
	  {
	    return EnumRarity.uncommon;
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
	 
	  public static void drainShield(ItemStack itemStack, double amount, EntityLivingBase entity)
	  {
	    if (!ElectricItem.manager.use(itemStack, amount, entity))
	    {
	      NBTTagCompound nbtData = getOrCreateNbtData(itemStack);

	      nbtData.setBoolean("active", false);
	      updateAttributes(nbtData);

	      }
	    }
	  
	  
	  private static void updateAttributes(NBTTagCompound nbtData)
	  {
	    boolean active = nbtData.getBoolean("active");
	    boolean message = nbtData.getBoolean("message");
	  }
	  public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	  
	  
}