package com.thunder.Updaters;

import java.util.List;

import com.thunder.Api.IEnergyAdd;
import com.thunder.Armor.ArmorList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyUpdater extends Item implements IEnergyAdd {
	
	private String Type;
	private String Effect;
	private String Matrix;
	private String Updater;


	IEnergyUpdater(){
		super();
		
		this.setMaxStackSize(1);
		this.setNoRepair();
				
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	protected static NBTTagCompound getOrCreateNbtData(ItemStack itemStack)
	  {
	    NBTTagCompound ret = itemStack.getTagCompound();
	    if (ret == null)
	    {
	      ret = new NBTTagCompound();
	      
	      itemStack.setTagCompound(ret);
	    }
	    return ret;
	  }
	 
	 protected void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {
			 if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1)
			        player.addPotionEffect(new PotionEffect(potion.id, 79, amplifier));
		}

	@Override
	public void onItemInventoryTick(ItemStack itemstack, World world, EntityLivingBase entity) {
		
	}

	@Override
	public float addDamageToEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity, float damage) {
		return damage;
	}
	
	protected String getType(){
		
		return Type;
	}
	
	protected String getEffect(){
		
		return Effect;
	}
	
	protected String getMatrix(){
		
		return Matrix;
	}
	
	protected String getUpdater(){
		
		return Updater;
	}
	
	

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
	{	
		par3List.add(EnumChatFormatting.GRAY + getUpdater());
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.updaters.type") + getType());
		 
		NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.updaters.requires") + getMatrix());
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.updaters.effect"));
		par3List.add(EnumChatFormatting.GRAY + getEffect());	
	}
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
	    return EnumRarity.common;
	}

	@Override
	public PotionEffect addPotionEffectToEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity) {
		
		return null;
	}

	@Override
	public PotionEffect addPotionEffectToAllAround(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity) {
		
		return null;
	}

	@Override
	public float dealDamageToEntity(ItemStack itemStack, EntityPlayer player, float ammount) {
		
		return 0;
	}

	@Override
	public Boolean setEntityFallDistance(ItemStack itemStack, EntityPlayer player) {
		
		return false;
	}

	@Override
	public Boolean hasEntityAutoSmelt(ItemStack itemStack, EntityPlayer player) {

		return false;
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


}
