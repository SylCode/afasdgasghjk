package com.thunder.Armor;

import java.util.List;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IC2Items;
import ic2.core.util.Util;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class IJuggerArmor extends ItemArmor implements ISpecialArmor {
	
	public IJuggerArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) {
		super(armorMaterial, renderIndex, armorType);
		
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxStackSize(1);
	//	this.setNoRepair();
		
		if(armorType == 0) this.setMaxDamage(Config.JugArmorHelmetDurability);
			if(armorType == 1) this.setMaxDamage(Config.JugArmorChestPlateDurability);
				if(armorType == 2) this.setMaxDamage(Config.JugArmorLeggingsDurability);
					if(armorType == 3) this.setMaxDamage(Config.JugArmorBootsDurability);

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
	    
	    return "thunder:textures/models/armor/" + "JuggerArmor" + suffix + ".png";
	}
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return (par2ItemStack != null) && (Util.matchesOD(par2ItemStack, IC2Items.getItem("carbonPlate")));
	}	
	

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
		
		if(this.armorType == 0){
			
			if(player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ArmorList.JuggerArmorHelmet &&
					player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ArmorList.JuggerArmorChestPlate &&
						player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ArmorList.JuggerArmorLeggings &&
							player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ArmorList.JuggerArmorBoots){
				
					this.effectPlayer(player, Potion.damageBoost, Config.JugPotionPowerStrength);
								
			}
			
			
		}
		
	}
	
	private void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {
		ItemStack armor = player.inventory.armorItemInSlot(3);
		 if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1 && armor.getItem() == this)
		        player.addPotionEffect(new PotionEffect(potion.id, 79, amplifier));
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		int durab = itemStack.getMaxDamage() - itemStack.getItemDamage();		
		par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.armor.durability") + durab);
					    				
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
			int slot) {
		int durab = armor.getMaxDamage() - armor.getItemDamage();	
		//if falling
	    if ((source == DamageSource.fall) && (this.armorType == 3))
	    {
	      int durabilityPerDamage = durabilityPerDamage();
	      int damageLimit = Integer.MAX_VALUE;
	      if (durabilityPerDamage > 0) {
	        damageLimit = (int)Math.min(damageLimit, 25.0D * durab / durabilityPerDamage);
	      }
	      return new ISpecialArmor.ArmorProperties(10, damage < 8.0D ? 1.0D : 0.875D, damageLimit);
	    }
	    
		if (source.isUnblockable()) {
		      return new ISpecialArmor.ArmorProperties(0, 0.0D, 0);
		    }
		
		double absorptionRatio = getBaseAbsorptionRatio() * getDamageAbsorptionRatio();
		int durabilityPerDamage = durabilityPerDamage();
		int damageLimit = Integer.MAX_VALUE;
		
		if (durabilityPerDamage > 0) {
		      damageLimit = (int)Math.min(damageLimit, 25.0D * durab / durabilityPerDamage);
		    }
		
		return new ISpecialArmor.ArmorProperties(0, absorptionRatio, damageLimit);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		
		int durab = armor.getMaxDamage() - armor.getItemDamage();
		  if (durab >= durabilityPerDamage()) {
		      return (int)Math.round(20.0D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio());
		    }
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		
		if(Config.JugConstDamage == false){
			stack.damageItem(damage * durabilityPerDamage() , entity);
			}else
			{
				if(Config.JugConstDamage == true)
				stack.damageItem(durabilityPerDamage() , entity);	
			}
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
	    return Config.JugDamageAbsorptionRatio;
	  }
	 
	 private int durabilityPerDamage(){
		 
		 return Config.JugDurabPerDamage;
	 }

	 public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }	

}