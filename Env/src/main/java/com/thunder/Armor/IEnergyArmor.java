package com.thunder.Armor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.ICustomDamageItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IItemHudInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class IEnergyArmor extends ItemArmor implements ISpecialArmor, IElectricItem, IItemHudInfo, ICustomDamageItem {
	
	public int maxCharge;
	public int transferLimit;
	public int tier;
	
	private int indicatorDMG = 0;

	public IEnergyArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) {
		super(armorMaterial, renderIndex, armorType);
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		
		this.maxCharge = Config.ArmorMaxCharge;
		this.transferLimit = Config.ArmorTransferLimit;
		this.tier = Config.ArmorTier;
		
	    setMaxDamage(27);
	    setMaxStackSize(1);
	    setNoRepair();
	    if(this.armorType == 3){
	    MinecraftForge.EVENT_BUS.register(this);
	    }
		
	}
	
	 @SideOnly(Side.CLIENT)
	 @Deprecated
	 public boolean hasEffect(ItemStack stack)
	 {
	    return true;
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
	    
	    return "thunder:textures/models/armor/" + "EnergyArmor" + suffix + ".png";
	    
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
		
		ElectricItem.manager.discharge(stack, damage * getEnergyPerDamage(), Integer.MAX_VALUE, true, false, false);
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
	    return Config.ArmorAbsorbtionRatio;
	  }
	 
	 private int getEnergyPerDamage(){
		 
		 return Config.ArmorEnergyPerDamage;
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public EnumRarity getRarity(ItemStack stack)
	 {
	    return EnumRarity.uncommon;
	 }

	 public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	 

	  private final ThreadLocal<Boolean> allowDamaging = new ThreadLocal();

}
