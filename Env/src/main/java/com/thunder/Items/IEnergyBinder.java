package com.thunder.Items;

import java.util.List;

import com.thunder.Blocks.AdvancedEnergyChargerTileEntity;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class IEnergyBinder extends Item {
	
	public IEnergyBinder(){
		super();
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setMaxStackSize(1);
	
	}
		
	private ChatComponentText chat;
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{    
		if (!isSimulating()) {
			return super.onItemRightClick(stack, world, player);
		}
		
		NBTTagCompound stackData = getOrCreateNbtData(stack);

		if (IC2.keyboard.isAltKeyDown(player) && stackData.getBoolean("InfoMode"))
		{
			stackData.setBoolean("InfoMode", false);

		}
		else if (IC2.keyboard.isAltKeyDown(player) && !stackData.getBoolean("InfoMode"))
		{
			stackData.setBoolean("InfoMode", true);
		}
				

	    return super.onItemRightClick(stack, world, player);
		
	}
	
	public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	 
	
	
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		
		
		
		if(!world.isRemote){
			
			NBTTagCompound stackData = getOrCreateNbtData(stack);
			
			if(player.isSneaking() && !stackData.getBoolean("InfoMode")){
		
				if(!(world.getTileEntity(x, y, z) instanceof AdvancedEnergyChargerTileEntity) && (world.getTileEntity(x, y, z) instanceof TileEntityElectricMachine))
					{
						stackData.setInteger("xCoord", x);
						stackData.setInteger("yCoord", y);
						stackData.setInteger("zCoord", z);
					} 
			}
		
			if(player.isSneaking() && !stackData.getBoolean("InfoMode"))
			{
				TileEntity tile = world.getTileEntity(x, y, z);
				
				if(tile instanceof AdvancedEnergyChargerTileEntity){
					
					AdvancedEnergyChargerTileEntity te = (AdvancedEnergyChargerTileEntity)tile;
					
					
					if(stackData.getInteger("xCoord") != 0 && stackData.getInteger("yCoord") != 0 && stackData.getInteger("zCoord") != 0 ){
						
								
						if(te.Pos1X == 0 && te.Pos1Y == 0 && te.Pos1Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos1X = stackData.getInteger("xCoord");
							te.Pos1Y = stackData.getInteger("yCoord");
							te.Pos1Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos1X + " " + te.Pos1Y + " " + te.Pos1Z + " bound with 1 slot");
							player.addChatComponentMessage(chat);
							
						}else if(te.Pos2X == 0 && te.Pos2Y == 0 && te.Pos2Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos2X = stackData.getInteger("xCoord");
							te.Pos2Y = stackData.getInteger("yCoord");
							te.Pos2Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos2X + " " + te.Pos2Y + " " + te.Pos2Z + " bound with 2 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos3X == 0 && te.Pos3Y == 0 && te.Pos3Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos3X = stackData.getInteger("xCoord");
							te.Pos3Y = stackData.getInteger("yCoord");
							te.Pos3Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos3X + " " + te.Pos3Y + " " + te.Pos3Z + " bound with 3 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos4X == 0 && te.Pos4Y == 0 && te.Pos4Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos4X = stackData.getInteger("xCoord");
							te.Pos4Y = stackData.getInteger("yCoord");
							te.Pos4Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos4X + " " + te.Pos4Y + " " + te.Pos4Z + " bound with 4 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos5X == 0 && te.Pos5Y == 0 && te.Pos5Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos5X = stackData.getInteger("xCoord");
							te.Pos5Y = stackData.getInteger("yCoord");
							te.Pos5Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos5X + " " + te.Pos5Y + " " + te.Pos5Z + " bound with 5 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos6X == 0 && te.Pos6Y == 0 && te.Pos6Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos6X = stackData.getInteger("xCoord");
							te.Pos6Y = stackData.getInteger("yCoord");
							te.Pos6Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos6X + " " + te.Pos6Y + " " + te.Pos6Z + " bound with 6 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos7X == 0 && te.Pos7Y == 0 && te.Pos7Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos7X = stackData.getInteger("xCoord");
							te.Pos7Y = stackData.getInteger("yCoord");
							te.Pos7Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos7X + " " + te.Pos7Y + " " + te.Pos7Z + " bound with 7 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos8X == 0 && te.Pos8Y == 0 && te.Pos8Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos8X = stackData.getInteger("xCoord");
							te.Pos8Y = stackData.getInteger("yCoord");
							te.Pos8Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos8X + " " + te.Pos8Y + " " + te.Pos8Z + " bound with 8 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos9X == 0 && te.Pos9Y == 0 && te.Pos9Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos9X = stackData.getInteger("xCoord");
							te.Pos9Y = stackData.getInteger("yCoord");
							te.Pos9Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos9X + " " + te.Pos9Y + " " + te.Pos9Z + " bound with 9 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos10X == 0 && te.Pos10Y == 0 && te.Pos10Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos10X = stackData.getInteger("xCoord");
							te.Pos10Y = stackData.getInteger("yCoord");
							te.Pos10Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos10X + " " + te.Pos10Y + " " + te.Pos10Z + " bound with 10 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos11X == 0 && te.Pos11Y == 0 && te.Pos11Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos11X = stackData.getInteger("xCoord");
							te.Pos11Y = stackData.getInteger("yCoord");
							te.Pos11Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos11X + " " + te.Pos11Y + " " + te.Pos11Z + " bound with 11 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos12X == 0 && te.Pos12Y == 0 && te.Pos12Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos12X = stackData.getInteger("xCoord");
							te.Pos12Y = stackData.getInteger("yCoord");
							te.Pos12Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos12X + " " + te.Pos12Y + " " + te.Pos12Z + " bound with 12 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos13X == 0 && te.Pos13Y == 0 && te.Pos13Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos13X = stackData.getInteger("xCoord");
							te.Pos13Y = stackData.getInteger("yCoord");
							te.Pos13Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos13X + " " + te.Pos13Y + " " + te.Pos13Z + " bound with 13 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos14X == 0 && te.Pos14Y == 0 && te.Pos14Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos14X = stackData.getInteger("xCoord");
							te.Pos14Y = stackData.getInteger("yCoord");
							te.Pos14Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos14X + " " + te.Pos14Y + " " + te.Pos14Z + " bound with 14 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos15X == 0 && te.Pos15Y == 0 && te.Pos15Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos15X = stackData.getInteger("xCoord");
							te.Pos15Y = stackData.getInteger("yCoord");
							te.Pos15Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos15X + " " + te.Pos15Y + " " + te.Pos15Z + " bound with 15 slot");
							player.addChatComponentMessage(chat);
							
						} else if(te.Pos16X == 0 && te.Pos16Y == 0 && te.Pos16Z == 0)
						{
							if(!isValid(te, stackData.getInteger("xCoord"), stackData.getInteger("yCoord"), stackData.getInteger("zCoord")))
							{
								chat = new ChatComponentText("Machine is already registered.");
								player.addChatComponentMessage(chat);
								return false;
							}
							te.Pos16X = stackData.getInteger("xCoord");
							te.Pos16Y = stackData.getInteger("yCoord");
							te.Pos16Z = stackData.getInteger("zCoord");
							
							chat = new ChatComponentText("Machine with coordinates " + te.Pos16X + " " + te.Pos16Y + " " + te.Pos16Z + " bound with 16 slot");
							player.addChatComponentMessage(chat);
							
						}
						

						
					}
					
				}
				
			}
			if(player.isSneaking() && stackData.getBoolean("InfoMode")){
				
				TileEntity tile = world.getTileEntity(x, y, z);
				
				if(tile instanceof AdvancedEnergyChargerTileEntity){
					
					AdvancedEnergyChargerTileEntity te = (AdvancedEnergyChargerTileEntity)tile;				

					int [] freeSlots;
					int [] usedSlots;
					freeSlots = new int[16];
					usedSlots = new int[16];
					
					
					for(int i = 0; i < freeSlots.length; i++)
					{
						if(te.getXPos(i + 1) == 0 && te.getYPos(i + 1) == 0 && te.getZPos(i + 1) == 0) freeSlots[i] = i + 1;
					}
					
					String slots = "";
					String Slots = "";
					
					for(int i = 0; i < freeSlots.length; i++){
						if(freeSlots[i] != 0) slots = slots + freeSlots[i] + " ";
						else usedSlots[i] = i + 1;
						
					}
					if(!slots.isEmpty()){
						chat = new ChatComponentText(StatCollector.translateToLocal("tag.energybinder.message1") + slots);					
					}else chat = new ChatComponentText(StatCollector.translateToLocal("tag.energybinder.message2"));
					
					player.addChatMessage(chat);
					
					for(int i = 0; i < 16; i++){
						
						if(usedSlots[i] != 0){
							chat = new ChatComponentText(StatCollector.translateToLocal("tag.energybinder.message3") + usedSlots[i] + StatCollector.translateToLocal("tag.energybinder.message4") + getCoords(te, i) + " .");
							player.addChatMessage(chat);
						}
						
					}
					
				}
				
			}
			
		
		}
		
		return true;
	}
	
	public boolean isValid(AdvancedEnergyChargerTileEntity te, int x, int y, int z){
		
		
		for(int i = 0; i < 16; i++){
			
			if(te.getXPos(i + 1) == x && te.getYPos(i + 1) == y && te.getZPos(i + 1) == z) return false;
			
		}
		
		return true;
	}
	
	

	
	public String getCoords(AdvancedEnergyChargerTileEntity te, int pos){
		
		String poss = "";
		
		switch(pos){
			case 0:
				return poss = te.getXPos(1) + " " + te.getYPos(1) + " " + te.getZPos(1);
			case 1:
				return poss = te.getXPos(2) + " " + te.getYPos(2) + " " + te.getZPos(2);
			case 2:
				return poss = te.getXPos(3) + " " + te.getYPos(3) + " " + te.getZPos(3);
			case 3:
				return poss = te.getXPos(4) + " " + te.getYPos(4) + " " + te.getZPos(4);
			case 4:
				return poss = te.getXPos(5) + " " + te.getYPos(5) + " " + te.getZPos(5);
			case 5:
				return poss = te.getXPos(6) + " " + te.getYPos(6) + " " + te.getZPos(6);
			case 6:
				return poss = te.getXPos(7) + " " + te.getYPos(7) + " " + te.getZPos(7);
			case 7:
				return poss = te.getXPos(8) + " " + te.getYPos(8) + " " + te.getZPos(8);
			case 8:
				return poss = te.getXPos(9) + " " + te.getYPos(9) + " " + te.getZPos(9);
			case 9:
				return poss = te.getXPos(10) + " " + te.getYPos(10) + " " + te.getZPos(10);
			case 10:
				return poss = te.getXPos(11) + " " + te.getYPos(11) + " " + te.getZPos(11);
			case 11:
				return poss = te.getXPos(12) + " " + te.getYPos(12) + " " + te.getZPos(12);
			case 12:
				return poss = te.getXPos(13) + " " + te.getYPos(13) + " " + te.getZPos(13);
			case 13:
				return poss = te.getXPos(14) + " " + te.getYPos(14) + " " + te.getZPos(14);
			case 14:
				return poss = te.getXPos(15) + " " + te.getYPos(15) + " " + te.getZPos(15);
			case 15:
				return poss = te.getXPos(16) + " " + te.getYPos(16) + " " + te.getZPos(16);
				
		}
		return poss;
		
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister Icon){
		
		this.itemIcon = Icon.registerIcon("thunder:" + this.getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
	{	    	
		
		
		NBTTagCompound stackData = getOrCreateNbtData(stack);
		
		int xCoord = stackData.getInteger("xCoord");	
		int yCoord = stackData.getInteger("yCoord");
		int zCoord = stackData.getInteger("zCoord");
		
		String posx = "" + xCoord;
		String posy = "" + yCoord;
		String posz = "" + zCoord;
		
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.energybinder.tooltip1") + EnumChatFormatting.AQUA + posx + " " + posy + " " + posz);
		
		if(stackData.getBoolean("InfoMode"))
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.energybinder.tooltip2") + EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("tag.items.general.enabled"));
		else par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.energybinder.tooltip2") + EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("tag.items.general.disabled"));
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.energybinder.tooltip3"));
		
	}
	

}