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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IHealModule extends ElectricItemBase {
	
	public IHealModule(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = Config.HModuleMaxCharge;
		this.transferLimit = Config.HModuleTransferLimit;
		this.tier = Config.HModuleTier;
		
	}
	
	 public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityplayer)
	  {
	    if (!isSimulating()) {
	      return itemStack;
	    }
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (nbtData.getBoolean("activeH"))
	    {
	      nbtData.setBoolean("activeH", false);
	      updateAttributes(nbtData);
	    }
	    else if (ElectricItem.manager.canUse(itemStack, 5.0D))
	    {
	      nbtData.setBoolean("activeH", true);
	      updateAttributes(nbtData);
	      
	    }
	    return super.onItemRightClick(itemStack, world, entityplayer);
	  }
	 
	 public static int ticker = 0;
	 
	  public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean par5)
	  {
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (!nbtData.getBoolean("activeH")) {
	      return;
	    }
	    if ((ticker % 16 == 0) && ((entity instanceof EntityPlayerMP))) {
	        drainModule(itemStack, Config.HModuleDrain, (EntityPlayer)entity);
	      }
	    }
	  
	  public static void drainModule(ItemStack itemStack, double amount, EntityLivingBase entity)
	  {
	    if (!ElectricItem.manager.use(itemStack, amount, entity))
	    {
	      NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	      
	      nbtData.setBoolean("activeH", false);
	      updateAttributes(nbtData);
	    }
	  }
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
	{
		NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
		if(nbtData.getBoolean("activeH")){
			par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.enabled") );
		}else 
		{
			par3List.add(EnumChatFormatting.RED +  StatCollector.translateToLocal("tag.items.general.disabled"));
		}
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.general.energy") + ElectricItem.manager.getCharge(itemStack)+ "/" + this.maxCharge + ".0");

	}
	
	  @SideOnly(Side.CLIENT)
	  public EnumRarity getRarity(ItemStack stack)
	  {
	    return EnumRarity.uncommon;
	  }
	  
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;	  
	  
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		 this.textures = new IIcon[2];
		    
		    this.textures[0] = Icon.registerIcon("thunder:" + getUnlocalizedName().substring(5));
		    this.textures[1] = Icon.registerIcon("thunder:" + getUnlocalizedName().substring(5) + "Active");
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
	    if (nbtData.getBoolean("activeH")) {
	      return this.textures[1];
	    }
	    return this.textures[0];
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
	    boolean active = nbtData.getBoolean("activeH");
	    
	  }
	
	public boolean isSimulating()
	{
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	}
	

}
