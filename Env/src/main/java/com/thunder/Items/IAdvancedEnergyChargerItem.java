package com.thunder.Items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class IAdvancedEnergyChargerItem extends ItemBlock {

    public IAdvancedEnergyChargerItem(Block block) {
        super(block);
        
        this.setMaxStackSize(1);
    }
    

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
	{
		
		NBTTagCompound nbtData = getOrCreateNbtData(itemStack);
		par3List.add(StatCollector.translateToLocal("tag.items.charger.output") + StatCollector.translateToLocal("tag.items.charger2.capacity"));
		String energy = "" + nbtData.getInteger("energy");
		par3List.add(StatCollector.translateToLocal("tag.items.charger.energy") + energy + " EU");
		par3List.add(StatCollector.translateToLocal("tag.items.advcharger.tooltip4"));

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
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
	    return EnumRarity.epic;
	}

}