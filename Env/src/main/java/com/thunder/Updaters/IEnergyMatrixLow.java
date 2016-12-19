package com.thunder.Updaters;

import java.util.List;

import com.thunder.Api.IEnergyAdd;
import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.Items.ElectricItemBase;
import com.thunder.Items.ItemList;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyMatrixLow extends ElectricItemBase implements IEnergyAdd{
	
	IEnergyMatrixLow(){
		super();
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = 1000000;
		this.transferLimit = 20000;
		this.tier = 3;
	}
	
	 public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityplayer)
	  {
	    if (!isSimulating()) {
	      return itemStack;
	    }
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (nbtData.getBoolean("active"))
	    {
	      nbtData.setBoolean("active", false);
	      updateAttributes(nbtData);
	    }
	    else if (ElectricItem.manager.canUse(itemStack, 1000.0D))
	    {
	      nbtData.setBoolean("active", true);
	      updateAttributes(nbtData);
	      
	    }
	    return super.onItemRightClick(itemStack, world, entityplayer);
	  }

	public static int ticker = 0;
	
	
	
	@Override
	public void onItemInventoryTick(ItemStack itemstack, World world, EntityLivingBase player) {
		

		NBTTagCompound nbtData = getOrCreateNbtData(itemstack);
		if (!nbtData.getBoolean("active")) {
		      return;
		    }
		if ((ticker % 16 == 0) && ((player instanceof EntityPlayerMP))) {
			
			if(ElectricItem.manager.getCharge(itemstack) < 1000.0){
				
				nbtData.setBoolean("active", false);
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
	  
	  private static void updateAttributes(NBTTagCompound nbtData)
	  {
	    boolean active = nbtData.getBoolean("active");
	    
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	 @SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
		{	
		 	par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.matrix.type.low"));
		 
			NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
			if(nbtData.getBoolean("active")){
				par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.enabled") );
			}else 
			{
				par3List.add(EnumChatFormatting.RED +  StatCollector.translateToLocal("tag.items.general.disabled"));
			}	
		}

	@Override
	public float addDamageToEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity, float damage) {
		return damage;
	}
 
	@Override
	public Boolean addPotionEffectToEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity) {
		return false;
	}

	@Override
	public Boolean addPotionEffectToAllAround(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity) {
		return false;
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
	
}
