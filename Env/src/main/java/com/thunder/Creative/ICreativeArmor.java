package com.thunder.Creative;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.thunder.Armor.ArmorList;
import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.Updaters.UpdatersList;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.InventoryEnergyPlayer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.core.util.StackUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ICreativeArmor extends ItemArmor implements ISpecialArmor {
	
	private int indH = 0;

	public ICreativeArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) {
		super(armorMaterial, renderIndex, armorType);
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditionsCreative);
		
	    setMaxStackSize(1);
	    setNoRepair();
	    if(this.armorType == 1){
	    MinecraftForge.EVENT_BUS.register(this);
	    }
		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
	    String name = getUnlocalizedName();
	    if ((name != null) && (name.length() > 4)) {
	      name = name.substring(5);
	    }
	    this.itemIcon = iconRegister.registerIcon("thunder:" + name);
	}
	
	
	
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    int suffix = this.armorType == 2 ? 2 : 1;
	    
	    return "thunder:textures/models/armor/" + "ModArmor" + suffix + ".png";
	    
	}
	

	
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor)
	
	{	
		
		NBTTagCompound armorData = getOrCreateNbtData(armor);
		
		switch(this.armorType){
		
		case 0:
			
			if(player.getDisplayName().equals(armorData.getString("armorOwner"))){
				
				if(player.isInWater()){
					
					effectPlayer(player, Potion.waterBreathing, 1);
					player.motionX *= 1.2000000000000003D;
					player.motionZ *= 1.2000000000000003D;
					
				}
			}
								
			break;
			
		case 1:
			
			if(player.getDisplayName().equals(armorData.getString("armorOwner"))){
				
				player.extinguish();
				effectPlayer(player, Potion.regeneration, 2);
				player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(28);
				indH = 1;
			}
	
			break;
			
		case 2:
			
			if(player.getDisplayName().equals(armorData.getString("armorOwner"))){
				
				effectPlayer(player, Potion.moveSpeed, 3);
				effectPlayer(player, Potion.jump, 2);
				
			}
			
			
			break;
			
			
		case 3:
			
			if(player.getDisplayName().equals(armorData.getString("armorOwner"))){
				
				  if(world.isRemote){
					  if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen() && Minecraft.getMinecraft().currentScreen == null) {
						  
							if (player.motionY > 0.0D) {
								player.motionY += 0.084999999105930327D;
							} else {
								player.motionY += 0.11699999910593033D;
							}
						}

						if (player.motionY < 0.0D && player.isSneaking() == false) {
							player.motionY /= 1.1499999761581421D;
							player.motionY /= 1.1499999761581421D;
							player.motionY /= 1.1499999761581421D;
						}

							
						}
				  
				  player.fallDistance = 0.0f;
				
			}
			
			
			
			break;
		
		
		
		}
	
	    
	}
	
	@SubscribeEvent
	public void EventHealth(LivingUpdateEvent event) {
		
	     if(event.entity instanceof EntityPlayerMP){
				EntityPlayer player = (EntityPlayer) event.entity;
							
				if((indH == 1 && player.getMaxHealth() > 20) && ((player.inventory.armorItemInSlot(2) == null || (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() != CreativeItemList.PainChestPlate)))){
										    
					 player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
					 float h = player.getHealth();
					 player.setHealth(h);
				     indH = 0;
				    
				}
				
	     	}
		
	 }
	
	@SubscribeEvent
	public void EventChestShield(LivingAttackEvent event) {
		
		if (event.entityLiving instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
						
			ItemStack armor = player.inventory.armorItemInSlot(2);
			ItemStack ch = new ItemStack(CreativeItemList.PainChestPlate);
			
			if(armor != null && armor.getItem() == ch.getItem()){
				
				NBTTagCompound armorData = getOrCreateNbtData(armor);
				
				if(player.getDisplayName().equals(armorData.getString("armorOwner"))){
					
					 if(event.source.isMagicDamage()){
				        	
							event.setCanceled(true);	
							IChatComponent chat = new ChatComponentText("Magic damage blocked!");
							player.addChatComponentMessage(chat);	
						}
				}
				
				
			}
			
		}
	}
	
	private void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {
		ItemStack armor = player.inventory.armorItemInSlot(3);
		 if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1)
		        player.addPotionEffect(new PotionEffect(potion.id, 79, amplifier));
	}
	
	public void onUpdate(ItemStack armor, World world, Entity entity, int slot, boolean par5)
	  {
		 if(entity instanceof EntityPlayer){
			 
			 EntityPlayer player = (EntityPlayer)entity;		 
			 NBTTagCompound armorData = getOrCreateNbtData(armor);
			 
			 
			 if(armorData.getString("armorOwner").equals("")){
				 
				 armorData.setString("armorOwner", player.getDisplayName());
				 updateAttributes(armorData);
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
	 
	private static void updateAttributes(NBTTagCompound armorData)
	  {
	    String armorOwner = armorData.getString("armorOwner");
	    
	  }
	
	public int getItemEnchantability()
	{
	    return 6;
	}
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
	    return false;
	}

	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
	    return true;
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		
		NBTTagCompound armorData = getOrCreateNbtData(armor);
		
		if(player instanceof EntityPlayer){
			
			EntityPlayer owner  = (EntityPlayer)player;
			
			if(owner.getDisplayName().equals(armorData.getString("armorOwner"))){
				
		
				if ((source == DamageSource.fall) && (this.armorType == 3))
				    {
				      int damageLimit = Integer.MAX_VALUE;
				      return new ISpecialArmor.ArmorProperties(10, damage < 8.0D ? 1.0D : 0.875D, damageLimit);
				    }
				
				
				if (source.isUnblockable()) {
				      return new ISpecialArmor.ArmorProperties(0, 0.0D, 0);
				    }
				
				double absorptionRatio = getBaseAbsorptionRatio() * getDamageAbsorptionRatio(owner);
				int damageLimit = Integer.MAX_VALUE;	
				
				return new ISpecialArmor.ArmorProperties(0, absorptionRatio, damageLimit);
			}
			return new ISpecialArmor.ArmorProperties(0, 0.0D, 0);
		}
		return new ISpecialArmor.ArmorProperties(0, 0.0D, 0);
	}
	
	

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		
		
	}
	
	public boolean hasFullSet(EntityPlayer player){
	
			if(player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == CreativeItemList.TritonHelmet
			        && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == CreativeItemList.PainChestPlate
			        && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == CreativeItemList.WalkerLeggings
			        && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == CreativeItemList.RedBoolBoots){
				
				return true;
			}

		return false;
		
	
	}
	
	 private double getBaseAbsorptionRatio()
	  {
	    switch (this.armorType)
	    {
	    case 0: 
	      return 0.15D;
	    case 1: 
	      return 1.0D;
	    case 2: 
	      return 0.3D;
	    case 3: 
	      return 0.15D;
	    }
	    return 0.0D;
	  }
	
	 
	 private double getDamageAbsorptionRatio(EntityPlayer player)
	  {
		 if(hasFullSet(player))
			 return 1.0D;
		 if(this.armorType == 1) 
			 return 0.9888D;
		 
		 return 0.95;

	  } 
	 
	 @SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack armor, EntityPlayer player, List par3List, boolean par4)
		{	    					    
		 
		 	par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tag.creative.relic"));
		 
			NBTTagCompound armorData = getOrCreateNbtData(armor);
			if(player.getDisplayName().equals(armorData.getString("armorOwner")))
			par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.GREEN + armorData.getString("armorOwner"));
			else
			par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.RED + armorData.getString("armorOwner"));
			
			par3List.add(EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("tag.creative.durability"));
			
			switch(this.armorType){
			case 0:
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.tritonhelmet.effects.1"));
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.tritonhelmet.effects.2"));
				break;
			case 1:
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.painchestplate.effects.1"));
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.painchestplate.effects.2"));
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.painchestplate.effects.3"));
				break;
				
			case 2:
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.walkerleggings.effects.1"));
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.walkerleggings.effects.2"));
				break;
				
			case 3:
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.redboolboots.effects.1"));
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.redboolboots.effects.2"));
				par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.redboolboots.effects.3"));
				break;
			
			}
		}

	 public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	 

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		
		NBTTagCompound armorData = getOrCreateNbtData(armor);
		if(player.getDisplayName().equals(armorData.getString("armorOwner")))
		return (int)Math.round(20.0D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio(player));
		return 0;
	}
	  

}
