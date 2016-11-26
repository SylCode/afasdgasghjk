package com.thunder.Creative;


import java.util.List;
import java.util.Random;
import java.util.UUID;


import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.Items.ItemList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class IGundiveBow extends ItemBow {
	
	public enum BowMode
	{
		SNIPER,
		MASSIVE
	}
	
	public static final String[] bowTextureArray = new String[] {"0", "1", "2", "3"};
	@SideOnly(Side.CLIENT)
	private IIcon[] IIconArray;
	
	
	public IGundiveBow()
    {
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabCombat);
        MinecraftForge.EVENT_BUS.register(this);
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditionsCreative);
		//MinecraftForgeClient.registerItemRenderer(this, EpicItemsRenderer.instance);
    }
	
	 public void onUpdate(ItemStack bow, World world, Entity entity, int slot, boolean par5)
	  {
		 if(entity instanceof EntityPlayer){
			 
			 EntityPlayer player = (EntityPlayer)entity;		 
			 NBTTagCompound bowData = getOrCreateNbtData(bow);
			 
			 if(bowData.getString("bowOwner").equals("")){
				 
				 bowData.setString("bowOwner", player.getDisplayName());
				 updateAttributes(bowData);
			 }
			 
			 if(bowData.getString("mode").equals(""))
				 bowData.setString("mode", "massive");
			 
			 if(bowData.getInteger("duration") == 0){
				 bowData.setInteger("duration", 72000);
				 
			 if(bowData.getInteger("clicked")==0)
				 bowData.setInteger("clicked", 0);
			 
			 }
		 }
		 
	  }
	 
	 public String getBowMode(ItemStack bow)
	 {
		 NBTTagCompound bowData = getOrCreateNbtData(bow);
		 return bowData.getString("mode");
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
	 
	 private static void updateAttributes(NBTTagCompound bowData)
	  {
	    String bowOwner = bowData.getString("bowOwner");
	    
	  }
	 
	 @SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack bow, EntityPlayer player, List par3List, boolean par4)
		{	    				
		 
		    par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tag.creative.relic"));
		    
			NBTTagCompound bowData = getOrCreateNbtData(bow);

		    String mode = bowData.getString("mode");
			 if(mode.equalsIgnoreCase("massive"))
			 {
					par3List.add(EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("tag.creative.bow.mode") +EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("tag.creative.bow.mode.arrowrain"));
			 }
			 else
			 {
					par3List.add(EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("tag.creative.bow.mode") +EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.bow.mode.powershot"));
			 }
				par3List.add(EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("tag.creative.bow.tooltip"));
			if(player.getDisplayName().equals(bowData.getString("bowOwner")))
				par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.GREEN + bowData.getString("bowOwner"));
			else
				par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.RED + bowData.getString("bowOwner"));

			par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.bow.effects.1"));
			par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.bow.effects.2"));
			par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.bow.effects.3"));
		}
	 
	 
	 public boolean hasEffect(ItemStack itemstack)
    {
        return true;
    }
	
	 public void onPlayerStoppedUsing(ItemStack bow, World world, EntityPlayer player, int par4)
    {
        int j = this.getMaxItemUseDuration(bow) - par4;

    	float speed = 3.0F;
    	int nArrows = 1;
    	int maxDuration = 12000;
    	float damage = 1.0F;

        NBTTagCompound bowData = getOrCreateNbtData(bow);
    	int timesClicked = bowData.getInteger("clicked");
    	
        ArrowLooseEvent event = new ArrowLooseEvent(player, bow, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        if(player.getDisplayName().equals(bowData.getString("bowOwner")))
        {
    		Random r = new Random();
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {

	        	if(timesClicked <1)
	        	{
	        		timesClicked++;
	        		bowData.setInteger("clicked", timesClicked);
	        	}
	        	else
	        	{
	        		if(bowData.getString("mode").equalsIgnoreCase("massive"))
	        			bowData.setString("mode", "sniper");
	        		else bowData.setString("mode", "massive");
	        		bowData.setInteger("clicked", 0);
	        	}
                return;
            }
            
        	if(bowData.getString("mode").equalsIgnoreCase("massive"))
        	{
        		nArrows = 180;
        		speed = 3;
        		maxDuration = 72000;
				 bowData.setInteger("duration", 72000);
        		damage = 3.0F;
        	}
        	else
        	{
        		nArrows =1;
        		speed = 9;
        		maxDuration = 144000;
				 bowData.setInteger("duration", 144000);
        		damage = 7.0F;
        	}
        		

            if (f > 1.0F)
            {
                f = 1.0F;
            }
            if (nArrows < 2)
            {
            	 GundiveBowArrow entityarrow = new GundiveBowArrow(world, player, f * speed, damage);
         		
	            if (f == 1.0F)
	            {
	                entityarrow.setIsCritical(true);
	            }

	            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, bow);

	            if (k > 0)
	            {
	                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
	            }

	            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, bow);

	            if (l > 0)
	            {
	                entityarrow.setKnockbackStrength(l);
	            }

	            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, bow) > 0)
	            {
	                entityarrow.setFire(100);
	            }

	            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	        
	            entityarrow.canBePickedUp = 0;

	            if (!world.isRemote)
	            {
	            	world.spawnEntityInWorld(entityarrow);
	            }
            }
            else
            {
            	int flag = 1;
	        	for(int i=1; i<nArrows; i++)
	        	{
	        		double x = r.nextDouble()*15*flag-r.nextInt(15);
	        		r.setSeed(System.nanoTime());
	        		double y = r.nextDouble()*15*flag;
	        		r.setSeed(System.nanoTime()/2);
	        		double z = r.nextDouble()*15*flag-r.nextInt(15);
	        		r.setSeed(System.nanoTime()/4);
		            GundiveBowArrow entityarrow = new GundiveBowArrow(world, player, f * speed, damage, x, y, z);
		            flag*=-1;
		            if (f == 1.0F)
		            {
		                entityarrow.setIsCritical(true);
		            }
	
		            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, bow);
	
		            if (k > 0)
		            {
		                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
		            }
	
		            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, bow);
	
		            if (l > 0)
		            {
		                entityarrow.setKnockbackStrength(l);
		            }
	
		            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, bow) > 0)
		            {
		                entityarrow.setFire(100);
		            }
	
		            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	
		        
		            entityarrow.canBePickedUp = 0;
	
		            if (!world.isRemote)
		            {
		            	world.spawnEntityInWorld(entityarrow);
		            }
	        	}
            }
    	}
	}
	 
	 public ItemStack onEaten(ItemStack bow, World world, EntityPlayer player)
	    {
	        return bow;
	    }

	 public int getMaxItemUseDuration(ItemStack bow)
	    {
	        NBTTagCompound bowData = getOrCreateNbtData(bow);
	        return bowData.getInteger("duration");
	    }
	 
	 public EnumAction getItemUseAction(ItemStack bow)
	    {
	        return EnumAction.bow;
	    }
	 
	 public ItemStack onItemRightClick(ItemStack bow, World world, EntityPlayer player)
	    {
	        ArrowNockEvent event = new ArrowNockEvent(player, bow);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return event.result;
	        }

	        player.setItemInUse(bow, this.getMaxItemUseDuration(bow));

	        return bow;
	    }
	 
	    public int getItemEnchantability()
	    {
	        return 1;
	    }
	    
	    
	  @SideOnly(Side.CLIENT)
	  public void registerIcons(IIconRegister icon)
	    {
		  
		  this.itemIcon = icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5) + "0");
		  this.IIconArray = new IIcon[bowTextureArray.length];
		  for (int N = 0; N < 4; N++) {
		    this.IIconArray[N] = icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5) + bowTextureArray[N]);
		  }

	    }

	  
	    @SideOnly(Side.CLIENT)
	    public IIcon getItemIconForUseDuration(int icon)
	    {
	    	return this.IIconArray[icon];
	    }
	    
       public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
       {
         if (player.getItemInUse() == null) { return this.itemIcon;
         }
         int Pulling = stack.getMaxItemUseDuration() - useRemaining;
         
         if (Pulling >= 18)
         {
           return this.IIconArray[3];
         }
         
         if (Pulling > 13)
         {
           return this.IIconArray[2];
         }
         
         if (Pulling > 0)
         {
           return this.IIconArray[1];
         }
         
         return this.IIconArray[0];
       }
	    
	    public boolean isFull3D(){
	    	
			return true;
	    	
	    }
	/*    
	    @SideOnly(Side.CLIENT)
		public EnumRarity getRarity(ItemStack stack)
		 {
		    return EnumRarity.epic;
		 }*/

}
