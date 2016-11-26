package com.thunder.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SlotArmor extends Slot{
	
	final int armorType;

	final EntityPlayer player;

	public SlotArmor(EntityPlayer player, IInventory inventory, int slotIndex, int x, int y, int armorType)
	{
		super(inventory, slotIndex, x, y);
		this.player = player;
		this.armorType = armorType;
	}

	public int getSlotStackLimit()
	{
		return 1;
	}

	public boolean isItemValid(ItemStack itemstack)
	{
		Item item = (itemstack == null ? null : itemstack.getItem());
		return item != null && item.isValidArmor(itemstack, armorType, player);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex()
	{
		return ItemArmor.func_94602_b(this.armorType);
	}
}
