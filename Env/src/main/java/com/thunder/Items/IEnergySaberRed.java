package com.thunder.Items;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.thunder.EnergyAdditions.Config;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.core.item.armor.ItemArmorNanoSuit;
import ic2.core.item.armor.ItemArmorQuantumSuit;
import ic2.core.item.tool.ItemElectricTool;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class IEnergySaberRed extends ElectricToolBase {
	
	private String module = "None";
	
	 public IEnergySaberRed()
	  {
	    this(10, ElectricToolBase.HarvestLevel.Diamond);
	  }
	
	public IEnergySaberRed(int operationEnergyCost, ElectricToolBase.HarvestLevel harvestLevel){
		super(operationEnergyCost, harvestLevel, EnumSet.of(ElectricToolBase.ToolClass.Sword));
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.maxCharge = Config.SMaxCharge;
		this.transferLimit = Config.STransferLimit;
		this.tier = Config.STier;
		
		 MinecraftForge.EVENT_BUS.register(this);
	}
	
	  @SideOnly(Side.CLIENT)
	  public void registerIcons(IIconRegister iconRegister)
	  {
	    this.textures = new IIcon[2];
	    
	    this.textures[0] = iconRegister.registerIcon("thunder:" + getUnlocalizedName().substring(5));
	    this.textures[1] = iconRegister.registerIcon("thunder:" + getUnlocalizedName().substring(5) + "Active");

	    
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public IIcon getIcon(ItemStack itemStack, int pass)
	  {
		    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
		    if (nbtData.getBoolean("active")) {
		      return this.textures[1];
		    }
		    return this.textures[0];
	  }
	  //code
	  
	  public float getDigSpeed(ItemStack itemStack, Block block, int meta)
	  {
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (nbtData.getBoolean("active"))
	    {
	      return 4.0F;
	    }
	    return 1.0F;
	  }
	  
	  public Multimap getAttributeModifiers(ItemStack stack)
	  {
	    int dmg = Config.SMinDmg;
	    if (ElectricItem.manager.canUse(stack, 600.0D))
	    {
	      NBTTagCompound nbtData = getOrCreateNbtData(stack);
	      if (nbtData.getBoolean("active")) {
	        dmg = Config.SMaxDmg;
	      }
	    }
	    Multimap<String, AttributeModifier> ret = HashMultimap.create();
	    ret.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", dmg, 0));
	    
	    return ret;
	  }
	  
	  public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase source)
	  {
	    NBTTagCompound nbtData = getOrCreateNbtData(stack);
	    if (!nbtData.getBoolean("active")) {
	      return true;
	    }
	    if (isSimulating())
	    {
	      drainSaber(stack, 600.0D, source);
	      if ((!(source instanceof EntityPlayer)) || 
	        (MinecraftServer.getServer().isPVPEnabled()) || (!(target instanceof EntityPlayer))) {
	        for (int i = 0; i < 4; i++)
	        {
	          if (!ElectricItem.manager.canUse(stack, 3000.0D)) {
	            break;
	          }
	          ItemStack armor = target.getEquipmentInSlot(i + 1);
	          if (armor != null)
	          {
	            double amount = 0.0D;
	            if ((armor.getItem() instanceof ItemArmorNanoSuit)) {
	              amount = Config.SNanoDischarge;
	            } else if ((armor.getItem() instanceof ItemArmorQuantumSuit)) {
	              amount = Config.SQuantDischarge;
	            }
	            if (amount > 0.0D)
	            {
	              ElectricItem.manager.discharge(armor, amount, this.tier, true, false, false);
	              if (!ElectricItem.manager.canUse(armor, 1.0D)) {
	                target.setCurrentItemOrArmor(i + 1, null);
	              }
	              drainSaber(stack, 3000.0D, source);
	            }
	          }
	        }
	        //
	       
	        
	        //
	      }
	      //
	      EntityPlayer player = (EntityPlayer) source;	      
	      if((player.inventory.hasItem(ItemList.PoisonModule) && ElectricItem.manager.canUse(stack, Config.SPoisonModuleCharge)) || 
	    		  (player.inventory.hasItem(ItemList.StunModule) && ElectricItem.manager.canUse(stack, Config.SStunModuleCharge)) ||
	    		  (player.inventory.hasItem(ItemList.WeakModule) && ElectricItem.manager.canUse(stack, Config.SWeakModuleCharge)) ||
	    		  (player.inventory.hasItem(ItemList.HealModule) && ElectricItem.manager.canUse(stack, Config.SHealModuleCharge))){
	    	  int countP = 0;
	    	  int countS = 0;
	    	  int countW = 0;
	    	  int countH = 0;
	        	for(int n = 0; n < player.inventory.getSizeInventory(); n++){
		        	
	        		ItemStack StackP = player.inventory.getStackInSlot(n);//
	        		ItemStack StackS = player.inventory.getStackInSlot(n);//
	        		ItemStack StackW = player.inventory.getStackInSlot(n);//
	        		ItemStack StackH = player.inventory.getStackInSlot(n);//
					
					if (StackP != null && StackP.getItem().equals(ItemList.PoisonModule) && Config.SPoisonModule == true)
					{
						NBTTagCompound nbtDataP = getOrCreateNbtData(StackP);
						if(nbtDataP.getBoolean("activeP") && !player.inventory.hasItem(ItemList.StunModule) && !player.inventory.hasItem(ItemList.WeakModule) && !player.inventory.hasItem(ItemList.HealModule)){
						countP = 1;
						}
					}
					if (StackS != null && StackS.getItem().equals(ItemList.StunModule) && Config.SStunModule == true)
					{
						NBTTagCompound nbtDataS = getOrCreateNbtData(StackS);
						if(nbtDataS.getBoolean("activeS") && !player.inventory.hasItem(ItemList.PoisonModule) && !player.inventory.hasItem(ItemList.WeakModule) && !player.inventory.hasItem(ItemList.HealModule)){
						countS = 1;
						}
					}
					if (StackW != null && StackW.getItem().equals(ItemList.WeakModule) && Config.SWeakModule == true)
					{
						NBTTagCompound nbtDataW = getOrCreateNbtData(StackW);
						if(nbtDataW.getBoolean("activeW") && !player.inventory.hasItem(ItemList.PoisonModule) && !player.inventory.hasItem(ItemList.StunModule) && !player.inventory.hasItem(ItemList.HealModule)){
						countW = 1;
						}
					}
					if (StackH != null && StackH.getItem().equals(ItemList.HealModule) && Config.SHealModule == true)
					{
						NBTTagCompound nbtDataH = getOrCreateNbtData(StackH);
						if(nbtDataH.getBoolean("activeH") && !player.inventory.hasItem(ItemList.StunModule) && !player.inventory.hasItem(ItemList.WeakModule) && !player.inventory.hasItem(ItemList.PoisonModule)){
						countH = 1;
						}
					}
	            		
	        	}	  
	        	if(countP == 1 && countS == 0 && countW == 0 && countH == 0){
	        		target.addPotionEffect(new PotionEffect(Potion.poison.getId(), 20*4, 1));
	        		ElectricItem.manager.use(stack, Config.SPoisonModuleCharge, source);    
	        	}
	        	if(countP == 0 && countS == 1 && countW == 0 && countH == 0){
	        		target.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 20*2, 2));
	        		target.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 20*1, 2));
	        		target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20*1, 7));
	        		ElectricItem.manager.use(stack, Config.SStunModuleCharge, source);    
	        	}
	        	if(countP == 0 && countS == 0 && countW == 1 && countH == 0){
	        		target.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 20*4, 1));
	        		ElectricItem.manager.use(stack, Config.SWeakModuleCharge, source);    
	        	}
	        	if(countP == 0 && countS == 0 && countW == 0 && countH == 1){
	        		float heal = player.getHealth();
	        		player.setHealth(heal + Config.SHealModuleHealAmount);
	        		ElectricItem.manager.use(stack, Config.SHealModuleCharge, source);    
	        	}
	        }	
	    }

	    return true;
	  }
	  
	  public boolean onBlockStartBreak(ItemStack itemStack, int i, int j, int k, EntityPlayer player)
	  {
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (nbtData.getBoolean("active")) {
	      drainSaber(itemStack, 80.0D, player);
	    }
	    return false;
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
	    else if (ElectricItem.manager.canUse(itemStack, 32.0D))
	    {
	      nbtData.setBoolean("active", true);
	      updateAttributes(nbtData);
	      
	    }
	    return super.onItemRightClick(itemStack, world, entityplayer);
	  }
	  
	  
	  public static int ticker = 0;
	  
	  public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean par5)
	  {
	    NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	    if (!nbtData.getBoolean("active")) {
	      return;
	    }
	    if ((ticker % 16 == 0) && ((entity instanceof EntityPlayerMP))) {
	    	EntityPlayer player = (EntityPlayer)entity;
	        drainSaber(itemStack, Config.SDrainOnUpdate, (EntityPlayer)entity);
	      }
	    }
	  
	  @SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
		{
	    	
	    	if(player.inventory.hasItem(ItemList.PoisonModule) && !player.inventory.hasItem(ItemList.StunModule) && !player.inventory.hasItem(ItemList.WeakModule) && !player.inventory.hasItem(ItemList.HealModule) && Config.SPoisonModule == true){
	    		module = StatCollector.translateToLocal("tag.items.saber.poison");
	    	}else
	    	if(!player.inventory.hasItem(ItemList.PoisonModule) && player.inventory.hasItem(ItemList.StunModule) && !player.inventory.hasItem(ItemList.WeakModule) && !player.inventory.hasItem(ItemList.HealModule) && Config.SStunModule == true){
	    		module = StatCollector.translateToLocal("tag.items.saber.stun");
		    }else
		    if(!player.inventory.hasItem(ItemList.PoisonModule) && !player.inventory.hasItem(ItemList.StunModule) && player.inventory.hasItem(ItemList.WeakModule) && !player.inventory.hasItem(ItemList.HealModule) && Config.SWeakModule == true){
		    	module = StatCollector.translateToLocal("tag.items.saber.weakness");
		    }else
	    	if(!player.inventory.hasItem(ItemList.PoisonModule) && !player.inventory.hasItem(ItemList.StunModule) && !player.inventory.hasItem(ItemList.WeakModule) && player.inventory.hasItem(ItemList.HealModule) && Config.SHealModule == true){
	    		module = StatCollector.translateToLocal("tag.items.saber.heal");
			}
		    else {module = StatCollector.translateToLocal("tag.items.saber.none");}
	    	
			
			NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
			if(nbtData.getBoolean("active")){
				par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tag.items.general.enabled") );
			}else 
			{
				par3List.add(EnumChatFormatting.RED +  StatCollector.translateToLocal("tag.items.general.disabled"));
			}
			par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.general.energy") + ElectricItem.manager.getCharge(itemStack) + "/" + this.maxCharge + ".0");
			par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.items.saber.modules") + module);
		
		}
	  
	  
	  public static void drainSaber(ItemStack itemStack, double amount, EntityLivingBase entity)
	  {
	    if (!ElectricItem.manager.use(itemStack, amount, entity))
	    {
	      NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
	      
	      nbtData.setBoolean("active", false);
	      updateAttributes(nbtData);
	    }
	  }
	  //
	  
	  @SideOnly(Side.CLIENT)
	  public boolean requiresMultipleRenderPasses()
	  {
	    return true;
	  }
	  
	  public boolean isFull3D()
	  {
	    return true;
	  }
	
	  @SideOnly(Side.CLIENT)
	  private IIcon[] textures;
	  
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

}
