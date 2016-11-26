package com.thunder.Items;

import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2Potion;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class INuclearComponent extends Item {
	
	protected final int radiationLength;
	protected final int amplifier;
	
	public INuclearComponent(int radiationLength1, int amplifier1){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setMaxStackSize(64);
		
	    this.radiationLength = radiationLength1;
	    this.amplifier = amplifier1;
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem)
	  {
	    if ((entity instanceof EntityLivingBase))
	    {
	      EntityLivingBase entityLiving = (EntityLivingBase)entity;
	      if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving) && Config.NuclearComponentRadioactive == true)
	      {
	        PotionEffect effect = ((EntityPlayer)entity).getActivePotionEffect(IC2Potion.radiation);
	        if (effect == null) {
	          IC2Potion.radiation.applyTo(entityLiving, this.radiationLength * 20, this.amplifier);
	        } else {
	          IC2Potion.radiation.applyTo(entityLiving, ((EntityPlayer)entity).getActivePotionEffect(IC2Potion.radiation).getDuration() + this.radiationLength, this.amplifier);
	        }
	      }
	    }
	  }	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	

}
