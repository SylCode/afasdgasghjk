package com.thunder.Items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class IEnergyChargerItem extends ItemBlock {

    public IEnergyChargerItem(Block block) {
        super(block);
    }
    

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4)
	{
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.blocks.energycharger.tooltip.1"));
		par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tag.blocks.energycharger.tooltip.2"));

	}

}
