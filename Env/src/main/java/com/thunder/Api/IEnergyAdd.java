package com.thunder.Api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public interface IEnergyAdd {
	
	public void onItemInventoryTick(ItemStack itemstack, World world, EntityLivingBase entity);
	
	public abstract float addDamageToEntity(ItemStack itemStack,EntityPlayer player, EntityLivingBase entity, float damage);
	
	public abstract Boolean addPotionEffectToEntity(ItemStack itemStack,EntityPlayer player, EntityLivingBase entity);
	
	public abstract Boolean addPotionEffectToAllAround(ItemStack itemStack,EntityPlayer player, EntityLivingBase entity);
	
	public abstract float dealDamageToEntity(ItemStack itemStack,EntityPlayer player, float ammount);
	
	public abstract Boolean setEntityFallDistance(ItemStack itemStack, EntityPlayer player);
	
	public abstract Boolean hasEntityAutoSmelt(ItemStack itemStack, EntityPlayer player);

}
