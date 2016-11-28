package com.thunder.Creative;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IIonisSword extends ItemSword {
	
	

	public IIonisSword(ToolMaterial material) {
		
		super(material);
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditionsCreative);
		
	}
			
	public Multimap getAttributeModifiers(ItemStack sword)
	  {
	  
	      NBTTagCompound swordData = getOrCreateNbtData(sword);
	           
	      int experience = swordData.getInteger("exp");

	      int damage = swordData.getInteger("dmg"); 
	      if(!swordData.getBoolean("isOwned")) damage = 5;
	      	      
	            
	    Multimap<String, AttributeModifier> ret = HashMultimap.create();
	    ret.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", damage, 0));
	    
	    return ret;
	  }
	
	
	
	public boolean hitEntity(ItemStack sword, EntityLivingBase target, EntityLivingBase source)
	 {
		
		if(source instanceof EntityPlayer)
		{		
			EntityPlayer player = (EntityPlayer)source;
			
			target.addPotionEffect(new PotionEffect(CreativePotionList.agonyIncubation.id, 20*120, 3));

			
			NBTTagCompound swordData = getOrCreateNbtData(sword);
			String d = swordData.getString("swordOwner");
			
			if(target != null && target instanceof EntityLivingBase && player.getDisplayName().equals(swordData.getString("swordOwner")))
			{
				int experience = swordData.getInteger("exp");
			    int damage = swordData.getInteger("dmg"); 
			    if(damage == 0) damage = 5;
				experience +=1;
				swordData.setInteger("exp", experience);
			//	
				if(experience % 100 == 0){
			    	  
			    	  experience += 1;
					  swordData.setInteger("exp", experience);
			    	  damage += 2;
			    	  swordData.setInteger("dmg", damage);
					  updateAttributes(swordData);
			    	 
			      }
				updateAttributes(swordData);
							
				if(experience >= 500) player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 20*2, 0));
				if(experience >= 1000) target.addPotionEffect(new PotionEffect(Potion.hunger.id, 20*2, 3));			
				if(experience >= 1500) player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20*2, 1));
				if(experience >= 2000) target.addPotionEffect(new PotionEffect(Potion.wither.id, 20*2, 3));
			}
		 
		}
		 return true;
	 }
	
	 public void onUpdate(ItemStack sword, World world, Entity entity, int slot, boolean par5)
	  {
		 if(entity instanceof EntityPlayer){
			 
			 EntityPlayer player = (EntityPlayer)entity;		 
			 NBTTagCompound swordData = getOrCreateNbtData(sword);
			 
			 
			 if(swordData.getString("swordOwner").equals("")){
				 
				 swordData.setString("swordOwner", player.getDisplayName());
				 updateAttributes(swordData);
			 }
			 
			 if(player.getDisplayName().equals(swordData.getString("swordOwner"))){
				 
				 int experience = swordData.getInteger("exp");
				 int damage = swordData.getInteger("dmg");
				 if(experience == 0){
			    	  
			    		experience += 1;
						swordData.setInteger("exp", experience);
						damage = 5;
						swordData.setInteger("dmg", damage);
						updateAttributes(swordData);
			    	  
					}
				 swordData.setBoolean("isOwned", true);
				 updateAttributes(swordData);
			 }
			 else {
				 swordData.setBoolean("isOwned", false);
				 updateAttributes(swordData);
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
	    int exp = nbtData.getInteger("exp");
	    int dmg = nbtData.getInteger("dmg");
	    boolean isOwned = nbtData.getBoolean("isOwned");
	    String swordOwner = nbtData.getString("swordOwner");
	    
	  }
	  
	  
	public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack sword, EntityPlayer player, List par3List, boolean par4)
	{
		par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tag.creative.relic"));
		
		NBTTagCompound swordData = getOrCreateNbtData(sword);
		
		if(player.getDisplayName().equals(swordData.getString("swordOwner")))
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.GREEN + swordData.getString("swordOwner"));
		else
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.creative.owner") + EnumChatFormatting.RED + swordData.getString("swordOwner"));
		
		par3List.add(EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("tag.creative.durability"));
		par3List.add(EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("tag.creative.ionis.experience") + swordData.getInteger("exp"));
		par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.ionis.effects.1"));
		par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.ionis.effects.2"));
		par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.ionis.effects.3"));
		par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tag.creative.ionis.effects.4"));
		
		par3List.add(EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("tag.creative.ionis.damage"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	public boolean isFull3D(){
    	
		return true;
    	
    }

}
