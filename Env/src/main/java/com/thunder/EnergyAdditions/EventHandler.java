package com.thunder.EnergyAdditions;



import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.thunder.Api.IEnergyAdd;
import com.thunder.Armor.ArmorList;
import com.thunder.Creative.AgonyPotion;
import com.thunder.Creative.CreativeItemList;
import com.thunder.Creative.CreativePotionList;
import com.thunder.Creative.ISoulAmulet;
import com.thunder.Creative.ISuicideSquadStuff;
import com.thunder.Updaters.IEnergyUpdater;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.InventoryEnergyPlayer;
import com.thunder.network.PacketDispatcher;
import com.thunder.network.SyncPlayerMessage;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.asm.transformers.EventSubscriptionTransformer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class EventHandler {
	
	public HashMap<String, InventoryPlayer> playerKeepsMap = new HashMap<String, InventoryPlayer>();
	public HashMap<String, Integer> playerHasEffectIncubation = new HashMap<String, Integer>();
	public HashMap<String, Integer> playerHasEffectVamp = new HashMap<String, Integer>();
	public HashMap<String, Integer> playerHasEffectAgony = new HashMap<String, Integer>();
	
	@SubscribeEvent
	public void onPlayerJoinEvent(PlayerLoggedInEvent event) {
	 {
		EntityPlayer player = (EntityPlayer)event.player;
		ChatComponentText chatmsg = new ChatComponentText(StatCollector.translateToLocal("eventhandler.hellomessage.1"));
	    event.player.addChatComponentMessage(chatmsg);
	    ChatComponentText chatmsg0 = new ChatComponentText(StatCollector.translateToLocal("eventhandler.hellomessage.2"));
	    event.player.addChatComponentMessage(chatmsg0);
		ChatComponentText chatmsg1 = new ChatComponentText(StatCollector.translateToLocal("eventhandler.hellomessage.3"));
	    event.player.addChatComponentMessage(chatmsg1);
	  
	 	}
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && ExtendedInventory.get((EntityPlayer) event.entity) == null)
			ExtendedInventory.register((EntityPlayer) event.entity);
	}
	
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayerMP) {
			PacketDispatcher.sendTo(new SyncPlayerMessage((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
		}
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		ExtendedInventory.get(event.entityPlayer).copy(ExtendedInventory.get(event.original));
		
		if(event.entityPlayer.getActivePotionEffect(CreativePotionList.agonyIncubation) != null)
		{
			PotionEffect effectIncub = event.entityPlayer.getActivePotionEffect(CreativePotionList.agonyIncubation);
			playerHasEffectIncubation.put(event.entityPlayer.getCommandSenderName(), effectIncub.getDuration());			
			
		}
		
		if(event.entityPlayer.getActivePotionEffect(CreativePotionList.Vamp) != null)
		{
			PotionEffect effectVamp = event.entityPlayer.getActivePotionEffect(CreativePotionList.Vamp);
			playerHasEffectVamp.put(event.entityPlayer.getCommandSenderName(), effectVamp.getDuration());			
			
		}
		
		if(event.entityPlayer.getActivePotionEffect(CreativePotionList.Agony) != null)
		{
			PotionEffect effectAgony = event.entityPlayer.getActivePotionEffect(CreativePotionList.Agony);
			playerHasEffectAgony.put(event.entityPlayer.getCommandSenderName(), effectAgony.getDuration());			
			
		}
	}
	
	@SubscribeEvent
	public void InventoryItemsTick(LivingUpdateEvent event){
		if (event.entity instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			EntityPlayer player = (EntityPlayer) event.entity;
			InventoryEnergyPlayer inv = ExtendedInventory.get(player).inventory;
			for (int a = 0; a < inv.getSizeInventory(); a++) {
				if (inv.getStackInSlot(a) != null && inv.getStackInSlot(a).getItem() instanceof IEnergyAdd) {
					((IEnergyAdd) inv.getStackInSlot(a).getItem()).onItemInventoryTick(inv.getStackInSlot(a), player.worldObj, player);
				}
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void addAdditionsEvent(LivingHurtEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		DamageSource source = event.source;
		
		if(source instanceof EntityDamageSource && source.getSourceOfDamage() instanceof EntityLivingBase)
		{
			EntityLivingBase player = (EntityLivingBase) source.getSourceOfDamage();
			if(player instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient())
			{
				EntityPlayer playerUsing = (EntityPlayer) player;
				InventoryEnergyPlayer inv = ExtendedInventory.get(playerUsing).inventory;
				
				for(int a = 0; a < inv.getSizeInventory(); a++){
					ItemStack stack = inv.getStackInSlot(a);
					if (stack != null && stack.getItem() instanceof IEnergyAdd) {		//added new		
					 IEnergyAdd item = (IEnergyAdd) stack.getItem();
					 //adds									 
				//	 event.ammount = item.addDamageToEntity(stack, playerUsing, entity, event.ammount);//add damage to entity								
					 	item.addDamageToEntity(stack, playerUsing, entity, event.ammount);
					 
							if(item.addPotionEffectToEntity(stack, playerUsing, entity) != null){//add effect to entity
								entity.addPotionEffect(item.addPotionEffectToEntity(stack, playerUsing, entity));
								}
									if(item.addPotionEffectToAllAround(stack, playerUsing, entity) != null){//add effect to all around
										List list = playerUsing.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((float) (playerUsing.posX -6), (float) (playerUsing.posY - 6), (float) (playerUsing.posZ - 6), (float) (playerUsing.posX + 6), (float) (playerUsing.posY + 6), (float) (playerUsing.posZ + 6)));
											for(int i = 0; i < list.size(); i++)
												{
												Entity e = (Entity) list.get(i);
														if(e != null && e != playerUsing && e instanceof EntityLivingBase){
															entity = (EntityLivingBase) e;
															entity.addPotionEffect(item.addPotionEffectToAllAround(stack, playerUsing, entity));
					            	  
															}
													}
										}
							}
					//end of adds
						}
					}
			//potion
			if(player.getActivePotionEffect(CreativePotionList.Vamp) != null && entity.getActivePotionEffect(CreativePotionList.Vamp) == null)
			{
				entity.addPotionEffect(new PotionEffect(CreativePotionList.Vamp.id, 20*1200, 3));
			}
			
				}
			} 
	
	@SubscribeEvent
	public void soulAmuletEvent(LivingAttackEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		ItemStack amulet = null;
		ItemStack usedAmulet = null;
		
		if (entity instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			
			EntityPlayer player = (EntityPlayer) entity;
			
			if(player.inventory.hasItem(CreativeItemList.SoulAmulet)){
				
				for(int a = 0; a < player.inventory.getSizeInventory(); a++){
					
					amulet = player.inventory.getStackInSlot(a);
					if (amulet != null && amulet.getItem().equals(CreativeItemList.SoulAmulet)) {
						NBTTagCompound amuletData = getOrCreateNbtData(amulet);
						
						if(player.getDisplayName().equals(amuletData.getString("amuletOwner"))){
						
						usedAmulet = amulet;					
						break;
						}
					}
					
				}
				
				if(usedAmulet != null)
				{
					NBTTagCompound amuletData = getOrCreateNbtData(usedAmulet);
				
			
					if((event.source.damageType == "player" || event.source.isMagicDamage()) && player.getDisplayName().equals(amuletData.getString("amuletOwner")))
					{
						List list = player.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((float) (player.posX -6), (float) (player.posY - 6), (float) (player.posZ - 6), (float) (player.posX + 6), (float) (player.posY + 6), (float) (player.posZ + 6)));
						int countEntities = 0;
						for(int i = 0; i < list.size(); i++)
						{
							Entity e = (Entity) list.get(i);
							if(e instanceof EntityLivingBase && !(e instanceof EntityPlayer)) countEntities++;
						}
					
					if(countEntities > 0)
						{
							float damage = event.ammount;
							for(int i = 0; i < list.size(); i++)
							{
								Entity e = (Entity) list.get(i);
								if(e instanceof EntityLivingBase && !(e instanceof EntityPlayer))
								{
									EntityLivingBase target = (EntityLivingBase)e;
									target.attackEntityFrom(DamageSource.generic, damage/countEntities);									
								}
							}
							event.setCanceled(true);
						}
					
					}
				}
			}
		}
				
	}
	
	
	@SubscribeEvent
	public void onLivingSpecialSpawn(LivingSpawnEvent.SpecialSpawn event)
	  {
		if(event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySpider || event.entityLiving instanceof EntityCaveSpider)
		{
			Random rand = new Random();
			int r = rand.nextInt(99);
			
			if(r > 1 && r < 15)
			{
				event.entityLiving.addPotionEffect(new PotionEffect(CreativePotionList.Vamp.id, 20*1200, 3));
			}					
		}				
	  }
	
	@SubscribeEvent
	public void agonyPotionTicker(LivingUpdateEvent event){
		
		if(event.entityLiving instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase)event.entityLiving;
			
			PotionEffect effect = entity.getActivePotionEffect(CreativePotionList.Agony);
			PotionEffect effectIncub = entity.getActivePotionEffect(CreativePotionList.agonyIncubation);
			PotionEffect effectVamp = entity.getActivePotionEffect(CreativePotionList.Vamp);
			
			//incubation
			if(effectIncub != null)
			{
				if(effectIncub.getDuration() <= 1){					
					entity.addPotionEffect(new PotionEffect(CreativePotionList.Agony.id, 20*1800, 2));				
				}
			}
			
			//agony
			if(effect != null){
				
				if(effect.getDuration() <= 1){
					entity.removePotionEffect(CreativePotionList.Agony.id);
					entity.attackEntityFrom(DamageSource.generic, 500);				
				}							
	//	  		        		
	    		if(effect.getDuration() < 1800*20 && effect.getDuration() > 1380*20)
	    		{
	    			effectPlayer(entity, Potion.moveSpeed, effect.getAmplifier());
	    			effectPlayer(entity, Potion.weakness, effect.getAmplifier());
	    		} else
	    		
	    		if(effect.getDuration() < 1380*20 && effect.getDuration() > 1080*20)
	    		{
	    			effectPlayer(entity, Potion.digSlowdown, effect.getAmplifier());
	    			effectPlayer(entity, Potion.hunger, effect.getAmplifier()*3);
	    		} else
	    		
	    		if(effect.getDuration() < 1080*20 && effect.getDuration() > 900*20)
	    		{
	    			effectPlayer(entity, Potion.moveSlowdown, effect.getAmplifier());
	    			entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 20*8, effect.getAmplifier()));
	    		} else  
	    		
	    		if(effect.getDuration() < 900*20 && effect.getDuration() > 660*20)
	    		{
	    			List list = entity.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((float) (entity.posX -10), (float) (entity.posY - 10), (float) (entity.posZ - 10), (float) (entity.posX + 10), (float) (entity.posY + 10), (float) (entity.posZ + 10)));
					for(int i = 0; i < list.size(); i++)
					{
						Entity e = (Entity) list.get(i);
						if(e instanceof EntityLivingBase){ 
							EntityLivingBase entityAg = (EntityLivingBase) e;
							if(entityAg.getActivePotionEffect(CreativePotionList.agonyIncubation) == null && entityAg.getActivePotionEffect(CreativePotionList.Agony) == null)
							entityAg.addPotionEffect(new PotionEffect(CreativePotionList.agonyIncubation.id, 20*600, 3));
						}
					}
	    			
	    		}  else
	    		
	    		if(effect.getDuration() < 660*20 && effect.getDuration() > 240*20)
	    		{
	    			effectPlayer(entity, Potion.regeneration, effect.getAmplifier());
	    			effectPlayer(entity, Potion.digSpeed, effect.getAmplifier());
	    			
	    		} else
	    		
	    		if(effect.getDuration() < 240*20)
	    		{
	    			
	    			if(entity.getActivePotionEffect(Potion.regeneration) != null){
		    			
						entity.removePotionEffect(Potion.regeneration.id);
		    		} 
		    		
		    		if(entity.getActivePotionEffect(Potion.field_76434_w) != null){
		    			
		    			entity.removePotionEffect(Potion.field_76434_w.id);
		    		} 
		    		
		    		if(entity.getActivePotionEffect(Potion.field_76443_y) != null){
		    			
		    			entity.removePotionEffect(Potion.field_76443_y.id);	    			
		    		} 
		    		
		    		if(entity.getActivePotionEffect(Potion.field_76444_x) != null){
		    			
		    			entity.removePotionEffect(Potion.field_76444_x.id);
		    		}
		    		
	    			if(!entity.isBurning())
	   				entity.setFire(10);
	    			
	    			effectPlayer(entity, Potion.blindness, effect.getAmplifier());
	    	  		effectPlayer(entity, Potion.confusion, effect.getAmplifier());
	    	  		effectPlayer(entity, Potion.hunger, effect.getAmplifier()*5);
	    	  		effectPlayer(entity, Potion.weakness, effect.getAmplifier()*2);
	    	  		effectPlayer(entity, Potion.moveSlowdown, effect.getAmplifier());
	    			    		    		
	    			    		
	    	  		if(entity.isInWater()){
	    				
	   				if(entity.getActivePotionEffect(Potion.waterBreathing) != null) entity.removePotionEffect(Potion.waterBreathing.id);
	   				entity.motionX *= 0.2000000000000003D;
	   				entity.motionZ *= 0.2000000000000003D;
	   				entity.motionY *= 0.2000000000000003D;
	    			}
	    			
	    			
	    		}
	    		
				
			}
			
			//vamp
			if(effectVamp != null)
			{
				
				if(isSimulating() && isSunVisible(entity.worldObj, (int)entity.posX, (int)entity.posY + 1, (int)entity.posZ))
				{
					if(!entity.isBurning()){
						entity.setFire(10);
					}
					
					effectPlayer(entity, Potion.hunger, effectVamp.getAmplifier()*2);
					effectPlayer(entity, Potion.digSlowdown, 2);
					effectPlayer(entity, Potion.moveSlowdown, 1);
				}else if(isSimulating())
				{			
					effectPlayer(entity, Potion.digSpeed, 2);
					effectPlayer(entity, Potion.moveSpeed, 0);
					entity.addPotionEffect(new PotionEffect(Potion.nightVision.id, 20*18, 1));
					
				}
				
			}
			
			if(playerHasEffectIncubation.containsKey(entity.getCommandSenderName()) && !entity.isPotionActive(CreativePotionList.agonyIncubation.id))
			{
				int duration = playerHasEffectIncubation.get(entity.getCommandSenderName());
				
				entity.addPotionEffect(new PotionEffect(CreativePotionList.agonyIncubation.id, duration, 3));
				playerHasEffectIncubation.remove(entity.getCommandSenderName());
				
			}
			
			if(playerHasEffectVamp.containsKey(entity.getCommandSenderName()) && !entity.isPotionActive(CreativePotionList.Vamp.id))
			{
				int duration = playerHasEffectVamp.get(entity.getCommandSenderName());
				
				entity.addPotionEffect(new PotionEffect(CreativePotionList.Vamp.id, duration, 3));
				playerHasEffectVamp.remove(entity.getCommandSenderName());
				
			}
			
			if(playerHasEffectAgony.containsKey(entity.getCommandSenderName()) && !entity.isPotionActive(CreativePotionList.Agony.id))
			{
				int duration = playerHasEffectAgony.get(entity.getCommandSenderName());
				
				entity.addPotionEffect(new PotionEffect(CreativePotionList.Agony.id, duration, 2));
				playerHasEffectAgony.remove(entity.getCommandSenderName());
				
			}
			
			
			
		}
		
	}
	
	public static boolean isSunVisible(World world, int x, int y, int z)
	  {
	    return (getskylight(world, x, z) > 4) && (!world.provider.hasNoSky) && (world.canBlockSeeTheSky(x, y, z)) && (((world.getWorldChunkManager().getBiomeGenAt(x, z) instanceof BiomeGenDesert)) || ((!world.isRaining()) && (!world.isThundering())));
	  }
	
	private static int getskylight(World world, int x, int z)
	  {
	    return world.getBlockLightValue(x, 255, z);
	  }
	
	
	@SubscribeEvent
	public void milkTicker(PlayerUseItemEvent.Start event){
		
		EntityPlayer player = (EntityPlayer)event.entityPlayer;
		
		if(player.getActivePotionEffect(CreativePotionList.Agony) != null || player.getActivePotionEffect(CreativePotionList.agonyIncubation) != null || player.getActivePotionEffect(CreativePotionList.Vamp) != null)
		{
		
			if(event.item.getItem().equals(Items.milk_bucket))
			{
				event.setCanceled(true);
			}
		}
	}
	
	private void effectPlayer(EntityLivingBase entity, Potion potion, int amplifier) {
		 if (entity.getActivePotionEffect(potion) == null || entity.getActivePotionEffect(potion).getDuration() <= 1)
			 entity.addPotionEffect(new PotionEffect(potion.id, 99, amplifier));
	}
	
	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		if(event.entity instanceof EntityEnderman) {
			
			ItemStack itemStackToDrop = new ItemStack(CreativeItemList.VampiricElixir, 1);
			event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, itemStackToDrop));
			
		}
	}
			
	
	@SubscribeEvent
	public void entityFallEvent(LivingFallEvent event){
		
		EntityLivingBase entity = event.entityLiving;
		if(entity instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			EntityPlayer player = (EntityPlayer) entity;
			InventoryEnergyPlayer inv = ExtendedInventory.get(player).inventory;
			for(int a = 0; a < inv.getSizeInventory(); a++){
				
				ItemStack stack = inv.getStackInSlot(a);
				
				if (stack != null && stack.getItem() instanceof IEnergyAdd) {
					
					IEnergyAdd item = (IEnergyAdd) stack.getItem();
					
					if(item.setEntityFallDistance(stack, player) == true){
						
						event.distance = 0;
					}
						
					
				}
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void onAutoSmelt(HarvestDropsEvent event)
	{
		if (event.harvester != null && !event.isSilkTouching)
		{
			if (event.harvester.getEquipmentInSlot(0) != null)
			{
				ItemStack tool = event.harvester.getEquipmentInSlot(0);
				Random rand = new Random();
				
				EntityPlayer player = (EntityPlayer) event.harvester;
				InventoryEnergyPlayer inv = ExtendedInventory.get(player).inventory;
				
				for(int a = 0; a < inv.getSizeInventory(); a++){
					
					ItemStack stack = inv.getStackInSlot(a);
					
					if (stack != null && stack.getItem() instanceof IEnergyAdd) {
						
					IEnergyAdd item = (IEnergyAdd) stack.getItem();
				
				if (tool.getItem() instanceof ItemTool && item.hasEntityAutoSmelt(stack, player) == true)
				{
					int z = EnchantmentHelper.getFortuneModifier(event.harvester);
					ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(event.block, 1, event.blockMetadata));
					
					if (result != null)
					{
						result.stackSize = z > 0 ? rand.nextInt(z) + 1 : 1;
						tool.attemptDamageItem(1, rand);
						event.drops.clear();
						event.drops.add(result.copy());
					}
					
				}
			}
		}
				
	}
}
	}
	
	@SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event) 
	{
		EntityPlayer player = event.player;
		
		if (playerKeepsMap.containsKey(player.getCommandSenderName()))
		{
			InventoryPlayer keepInventory = playerKeepsMap.get(player.getCommandSenderName());
						
			for (int i = 0; i < player.inventory.armorInventory.length; i++)
			{
				if (keepInventory.armorInventory[i] != null)
				{
					player.inventory.armorInventory[i] = keepInventory.armorInventory[i];
				}
			}
			for (int i = 0; i < player.inventory.mainInventory.length; i++)
			{
				if (keepInventory.mainInventory[i] != null)
				{
					player.inventory.mainInventory[i] = keepInventory.mainInventory[i];
				}
			}
			playerKeepsMap.remove(player.getCommandSenderName());
		}
		
		//potions
		if(playerHasEffectIncubation.containsKey(player.getCommandSenderName()))
		{
			int duration = playerHasEffectIncubation.get(player.getCommandSenderName());
			
			player.addPotionEffect(new PotionEffect(CreativePotionList.agonyIncubation.id, duration, 3));
			playerHasEffectIncubation.remove(player.getCommandSenderName());
			
		}
		
		if(playerHasEffectVamp.containsKey(player.getCommandSenderName()))
		{
			int duration = playerHasEffectVamp.get(player.getCommandSenderName());
			
			player.addPotionEffect(new PotionEffect(CreativePotionList.Vamp.id, duration, 3));
			playerHasEffectVamp.remove(player.getCommandSenderName());
			
		}
		
		if(playerHasEffectAgony.containsKey(player.getCommandSenderName()))
		{
			int duration = playerHasEffectAgony.get(player.getCommandSenderName());
			
			player.addPotionEffect(new PotionEffect(CreativePotionList.Agony.id, duration, 2));
			playerHasEffectAgony.remove(player.getCommandSenderName());
			
		}
	}
	
	@SubscribeEvent
	public void death(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		  {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
				  
			ItemStack item = player.getHeldItem();
			if(item != null && item.getItem() instanceof ISuicideSquadStuff)
			{
				ISuicideSquadStuff stuff = (ISuicideSquadStuff)item.getItem();
				if(stuff != null)
				{
					if(stuff.getStuffStatus())
					{
						stuff.setStuffStatus(false);
						NBTTagCompound stuffData = getOrCreateNbtData(item);
						
						if(player.getDisplayName().equals(stuffData.getString("stuffOwner")))
						{
							InventoryPlayer keepInventory = new InventoryPlayer(null);
							
							//keep all armor
							keepAllArmor(player, keepInventory);
							//keep all items
							for (int i = 0; i < player.inventory.mainInventory.length; i++)
							{
								keepInventory.mainInventory[i] = ItemStack.copyItemStack(player.inventory.mainInventory[i]);
								player.inventory.mainInventory[i] = null;
							}
							
							keepInventory.setItemStack(new ItemStack(CreativeItemList.SuicideSquadStuff));
							playerKeepsMap.put(player.getCommandSenderName(), keepInventory);
						}
					}
				}
			}
			//effect adds
			
			if(player.getActivePotionEffect(CreativePotionList.agonyIncubation) != null)
			{
				PotionEffect effectIncub = player.getActivePotionEffect(CreativePotionList.agonyIncubation);
				playerHasEffectIncubation.put(player.getCommandSenderName(), effectIncub.getDuration());			
				
			}
			
			if(player.getActivePotionEffect(CreativePotionList.Vamp) != null)
			{
				PotionEffect effectVamp = player.getActivePotionEffect(CreativePotionList.Vamp);
				playerHasEffectVamp.put(player.getCommandSenderName(), effectVamp.getDuration());			
				
			}
			
			if(player.getActivePotionEffect(CreativePotionList.Agony) != null)
			{
				PotionEffect effectAgony = player.getActivePotionEffect(CreativePotionList.Agony);
				playerHasEffectAgony.put(player.getCommandSenderName(), effectAgony.getDuration());			
				
			}
			
		}
		}
	
	
	
	private void keepAllArmor(EntityPlayer player, InventoryPlayer keepInventory) {
		for (int i = 0; i < player.inventory.armorInventory.length; i++)
		{
			keepInventory.armorInventory[i] = ItemStack.copyItemStack(player.inventory.armorInventory[i]);
			player.inventory.armorInventory[i] = null;
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
	
	public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	
	
	

	
}
