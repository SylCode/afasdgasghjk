package com.thunder.Armor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
import ic2.api.item.ICustomDamageItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IItemHudInfo;
import ic2.core.IC2;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import ic2.core.util.StackUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class IModArmor extends ItemArmor implements ISpecialArmor, IElectricItem, IItemHudInfo, ICustomDamageItem {
	
	public int maxCharge;
	public int transferLimit;
	public int tier;
	
	private int indicatorDMG = 0;

	public IModArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) {
		super(armorMaterial, renderIndex, armorType);
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		
		this.maxCharge = Config.ModArmorMaxCharge;
		this.transferLimit = Config.ModArmorTransferLimit;
		this.tier = Config.ModArmorTier;
		
	    setMaxDamage(27);
	    setMaxStackSize(1);
	    setNoRepair();
	    if(this.armorType == 3){
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
	

	
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	
	  {		
	    if (player.inventory.armorInventory[2] != itemStack) {
	      return;
	    }
	    NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(itemStack);
	    
	    byte toggleTimer = nbtData.getByte("toggleTimer");
	    boolean ret = false;

	    if(this.armorType == 1){
	      boolean jetpack = nbtData.getBoolean("jetpack");
	      boolean hoverMode = nbtData.getBoolean("hoverMode");
	      boolean jetpackUsed = false;
	      
	      if ((IC2.keyboard.isJumpKeyDown(player)) && (IC2.keyboard.isModeSwitchKeyDown(player)) && (toggleTimer == 0))
	      {
	        toggleTimer = 10;
	        hoverMode = !hoverMode;
	        if (isSimulating())
	        {
	          nbtData.setBoolean("hoverMode", hoverMode);
	          if (hoverMode) {
	            IC2.platform.messagePlayer(player, "Energy Armor Hover Mode enabled.", new Object[0]);
	          } else {
	            IC2.platform.messagePlayer(player, "Energy Armor Hover Mode disabled.", new Object[0]);
	          }
	        }
	      }
	      if ((IC2.keyboard.isBoostKeyDown(player)) && (IC2.keyboard.isModeSwitchKeyDown(player)) && (toggleTimer == 0))
	      {
	        toggleTimer = 10;
	        jetpack = !jetpack;
	        if (isSimulating())
	        {
	          nbtData.setBoolean("jetpack", jetpack);
	          if (jetpack) {
	            IC2.platform.messagePlayer(player, "Energy Armor Jetpack enabled.", new Object[0]);
	          } else {
	            IC2.platform.messagePlayer(player, "Energy Armor Jetpack disabled.", new Object[0]);
	          }
	        }
	      }
	      if ((jetpack) && ((IC2.keyboard.isJumpKeyDown(player)) || ((hoverMode) && (player.motionY < -0.029999999329447746D)))) {
	        jetpackUsed = useJetpack(player, hoverMode);
	      }
	      if ((isSimulating()) && (toggleTimer > 0))
	      {
	        toggleTimer = (byte)(toggleTimer - 1);
	        
	        nbtData.setByte("toggleTimer", toggleTimer);
	      }
	      if ((IC2.platform.isRendering()) && (player == IC2.platform.getPlayerInstance()))
	      {
	        if (lastJetpackUsed != jetpackUsed)
	        {
	          if (jetpackUsed)
	          {
	            if (audioSource == null) {
	              audioSource = IC2.audioManager.createSource(player, PositionSpec.Backpack, "Tools/Jetpack/JetpackLoop.ogg", true, false, IC2.audioManager.getDefaultVolume());
	            }
	            if (audioSource != null) {
	              audioSource.play();
	            }
	          }
	          else if (audioSource != null)
	          {
	            audioSource.remove();
	            audioSource = null;
	          }
	          lastJetpackUsed = jetpackUsed;
	        }
	        if (audioSource != null) {
	          audioSource.updatePosition();
	        }
	      }
	      ret = jetpackUsed;	      
	      player.extinguish(); 
	      }
	    
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
	public int getCustomDamage(ItemStack stack) {
	
		return stack.getItemDamage();
	}

	@Override
	public int getMaxCustomDamage(ItemStack stack) {

		return stack.getMaxDamage();
	}
	
	public void setDamage(ItemStack stack, int damage)
	{
	    if (damage == stack.getItemDamage()) {
	      return;
	    }
	    Boolean allow = (Boolean)this.allowDamaging.get();
	    if ((allow == null) || (!allow.booleanValue())) {
	    } else {
	      super.setDamage(stack, damage);
	    }
	}

	@Override
	public void setCustomDamage(ItemStack stack, int damage) {
		
		this.allowDamaging.set(Boolean.valueOf(true));
		stack.setItemDamage(damage);
		this.allowDamaging.set(Boolean.valueOf(false));
		
	}
	
	public static Random random = new Random();

	@Override
	public boolean applyCustomDamage(ItemStack stack, int damage, EntityLivingBase src) {
		
	    if (src != null)
	    {
	      stack.damageItem(damage, src);
	      return true;
	    }
	    return stack.attemptDamageItem(damage, random);
	}

	@Override
	public List<String> getHudInfo(ItemStack itemStack) {
	
		List<String> info = new LinkedList();
		info.add(ElectricItem.manager.getToolTip(itemStack));
		info.add(StatCollector.translateToLocal("Tier:") + " " + this.tier);
		return info;
	}
	
	public void getSubItems(Item item, CreativeTabs tabs, List itemList)
	{
	    ItemStack charged = new ItemStack(this, 1);
	    ElectricItem.manager.charge(charged, Double.POSITIVE_INFINITY, Integer.MAX_VALUE, true, false);
	    
	    itemList.add(charged);
	    itemList.add(new ItemStack(this, 1, getMaxDamage()));
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		
		if(this.armorType == 1)
		return true;
		
		return false;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
	
		return this;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {

		return this;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {

		return this.maxCharge;
	}

	@Override
	public int getTier(ItemStack itemStack) {

		return this.tier;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {

		return this.transferLimit;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		
		if ((source == DamageSource.fall) && (this.armorType == 3))
	    {
	      int energyPerDamage = getEnergyPerDamage();
	      int damageLimit = Integer.MAX_VALUE;
	      if (energyPerDamage > 0) {
	        damageLimit = (int)Math.min(damageLimit, 25.0D * ElectricItem.manager.getCharge(armor) / energyPerDamage);
	      }
	      return new ISpecialArmor.ArmorProperties(10, damage < 8.0D ? 1.0D : 0.875D, damageLimit);
	    }
		
		if (source.isUnblockable()) {
	        return new ISpecialArmor.ArmorProperties(0, 0.0D, 0);
	      }
	      double absorptionRatio = getBaseAbsorptionRatio() * getDamageAbsorptionRatio();
	      int energyPerDamage = getEnergyPerDamage();
	      
	      int damageLimit = Integer.MAX_VALUE;
	      if (energyPerDamage > 0) {
	        damageLimit = (int)Math.min(damageLimit, 25.0D * ElectricItem.manager.getCharge(armor) / energyPerDamage);
	      }
	      return new ISpecialArmor.ArmorProperties(0, absorptionRatio, damageLimit);
		
		
		
	}
	
	
	@SubscribeEvent
	public void onEntityLivingFallEvent(LivingFallEvent event)
	  {
	    if ((isSimulating()) && ((event.entity instanceof EntityLivingBase)))
	    {
	      EntityLivingBase entity = (EntityLivingBase)event.entity;
	      ItemStack armor = entity.getEquipmentInSlot(1);
	      if ((armor != null) && (armor.getItem() == this))
	      {
	        int fallDamage = (int)event.distance - 3;
	        if (fallDamage >= 8) {
	          return;
	        }
	        double energyCost = getEnergyPerDamage() * fallDamage;
	        if (energyCost <= ElectricItem.manager.getCharge(armor))
	        {
	          ElectricItem.manager.discharge(armor, energyCost, Integer.MAX_VALUE, true, false, false);
	          
	          event.setCanceled(true);
	        }
	      }
	    }
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		
		 if (ElectricItem.manager.getCharge(armor) >= getEnergyPerDamage()) {
			 return (int)Math.round(20.0D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio());
		 }
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		
		if(entity != null && entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)entity;
			
			ItemStack H = player.inventory.armorItemInSlot(3);
			ItemStack C = player.inventory.armorItemInSlot(2);
			ItemStack L = player.inventory.armorItemInSlot(1);
			ItemStack B = player.inventory.armorItemInSlot(0);
		
			if(hasFullSet(player) && EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, H) == 4 && 
					EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, C) == 4 &&
					EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, L) == 4 &&
					EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, B) == 4){
					
				ElectricItem.manager.discharge(stack, (damage * getEnergyPerDamage())/2, Integer.MAX_VALUE, true, false, false);
				
			}
			else{
				ElectricItem.manager.discharge(stack, damage * getEnergyPerDamage(), Integer.MAX_VALUE, true, false, false);
				
			}
		} 
	}
	
	public Boolean hasFullSet(EntityPlayer player){
		
		if(player != null){
			
			if(player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ArmorList.ModArmorHelmet
			        && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ArmorList.ModArmorChestPlate
			        && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ArmorList.ModArmorLeggings
			        && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ArmorList.ModArmorBoots){
				
				return true;
			}
					
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
	      return 0.4D;
	    case 2: 
	      return 0.3D;
	    case 3: 
	      return 0.15D;
	    }
	    return 0.0D;
	  }
	
	 
	 private double getDamageAbsorptionRatio()
	  {
	    return Config.ModArmorAbsorbtionRatio;
	  }
	 
	 private int getEnergyPerDamage(){
		 
		 return Config.ModArmorEnergyPerDamage;
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public EnumRarity getRarity(ItemStack stack)
	 {
	    return EnumRarity.rare;
	 }

	 public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	 

	 private final ThreadLocal<Boolean> allowDamaging = new ThreadLocal();
	  
	 public boolean useJetpack(EntityPlayer player, boolean hoverMode)
	  {
	    ItemStack jetpack = player.inventory.armorInventory[2];
	    if (ElectricItem.manager.getCharge(jetpack) == 0.0D) {
	      return false;
	    }
	    float power = 1.0F;
	    float dropPercentage = 0.05F;
	    if ((float)ElectricItem.manager.getCharge(jetpack) / getMaxCharge(jetpack) <= dropPercentage) {
	      power = (float)(power * (ElectricItem.manager.getCharge(jetpack) / (getMaxCharge(jetpack) * dropPercentage)));
	    }
	    if (IC2.keyboard.isForwardKeyDown(player))
	    {
	      float retruster = 3.5F;
	      if (hoverMode) {
	        retruster = 0.5F;
	      }
	      float forwardpower = power * retruster * 2.0F;
	      if (forwardpower > 0.0F) {
	        player.moveFlying(0.0F, 0.4F * forwardpower, 0.02F);
	      }
	    }
	    int worldHeight = IC2.getWorldHeight(player.worldObj);
	    
	    double y = player.posY;
	    if (y > worldHeight - 25)
	    {
	      if (y > worldHeight) {
	        y = worldHeight;
	      }
	      power = (float)(power * ((worldHeight - y) / 25.0D));
	    }
	    double prevmotion = player.motionY;
	    player.motionY = Math.min(player.motionY + power * 0.2F, 0.6000000238418579D);
	    if (hoverMode)
	    {
	      float maxHoverY = -0.025F;
	      if (IC2.keyboard.isSneakKeyDown(player)) {
	        maxHoverY = -0.1F;
	      }
	      if (IC2.keyboard.isJumpKeyDown(player)) {
	        maxHoverY = 0.1F;
	      }
	      if (player.motionY > maxHoverY)
	      {
	        player.motionY = maxHoverY;
	        if (prevmotion > player.motionY) {
	          player.motionY = prevmotion;
	        }
	      }
	    }
	    double consume = 8.0D;
	    if (hoverMode) {
	      consume = 10.0D;
	    }
	    ElectricItem.manager.discharge(jetpack, consume, Integer.MAX_VALUE, true, false, false);
	    
	    player.fallDistance = 0.0F;
	    player.distanceWalkedModified = 0.0F;
	    
	    IC2.platform.resetPlayerInAirTime(player);
	    
	    return true;
	  }
	  
	  public static AudioSource audioSource;
	  private static boolean lastJetpackUsed = false;
}
