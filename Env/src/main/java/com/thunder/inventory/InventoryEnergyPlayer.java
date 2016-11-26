package com.thunder.inventory;

import java.util.List;

import com.thunder.Api.IEnergyAdd;
import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.Items.IEnergyBar;
import com.thunder.Updaters.IEnergyMatrixLow;
import com.thunder.Updaters.IEnergyUpdater;
import com.thunder.Updaters.UpdatersList;
import com.thunder.network.PacketDispatcher;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;

public class InventoryEnergyPlayer implements IInventory {
	
	private final String name = "Updaters";
	private final String tagName = "EnergyInvTag";
	
	private EntityPlayer player;
	
	public static final int INV_SIZE = 5;
	
	private ItemStack[] inventory = new ItemStack[INV_SIZE];
	
	public InventoryEnergyPlayer()
	{
		
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize > amount)
			{
				stack = stack.splitStack(amount);
				this.markDirty();
			}
			else
			{
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		this.inventory[slot] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	public String getInventoryName()
	{
		return name;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return name.length() > 0;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}
	
	@Override
	public void markDirty()
	{
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0) {
				inventory[i] = null;
			}
		}
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		if (itemstack == null || !(itemstack.getItem() instanceof IEnergyAdd))
			return false;
		if (slot == 0 || slot == 1 || slot == 2 || slot == 3 && itemstack.getItem() instanceof IEnergyUpdater && itemstack.getItem() instanceof IEnergyAdd)
			return true;
		if (slot == 4	&& (itemstack.getItem().equals(UpdatersList.EnergyMatrix) ||  itemstack.getItem().equals(UpdatersList.EnergyMatrixStandart) || itemstack.getItem().equals(UpdatersList.EnergyMatrixUltra)) && itemstack.getItem() instanceof IEnergyAdd)
			return true;
		return false;
	}
	
	public void writeToNBT(NBTTagCompound compound)
	{
		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag(tagName, items);
		
	}

	public void readFromNBT(NBTTagCompound compound) {
		NBTTagList items = compound.getTagList(tagName, compound.getId());
		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if (slot >= 0 && slot < getSizeInventory()) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}

	}
	
	public void copy(InventoryEnergyPlayer inv) {
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack stack = inv.getStackInSlot(i);
			inventory[i] = (stack == null ? null : stack.copy());
		}
		markDirty();
	}
	
	
    public boolean hasItemStack(ItemStack stack)
    {
        int i;

        for (i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null && this.inventory[i].isItemEqual(stack))
            {
                return true;
            }
        }


        return false;
    }
    
    public void dropAllItems()
    {
        int i;
        for (i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                this.player.func_146097_a(this.inventory[i], true, false);
                this.inventory[i] = null;
            }
        }
    }

}
