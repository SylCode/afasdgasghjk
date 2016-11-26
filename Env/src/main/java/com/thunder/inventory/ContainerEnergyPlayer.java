package com.thunder.inventory;

import com.thunder.Items.IEnergyBar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerEnergyPlayer extends Container {
	
	private static final int ARMOR_START = InventoryEnergyPlayer.INV_SIZE, ARMOR_END = ARMOR_START+3,
			INV_START = ARMOR_END+1, INV_END = INV_START+26, HOTBAR_START = INV_END+1,
			HOTBAR_END = HOTBAR_START+8;
	 
	 private EntityPlayer Player;
	
	public ContainerEnergyPlayer(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryEnergyPlayer inventoryCustom)
	{
		int i;
		this.addSlotToContainer(new SlotEnergy(inventoryCustom, 0, 77, 8));
		this.addSlotToContainer(new SlotEnergy(inventoryCustom, 1, 77, 26));
		this.addSlotToContainer(new SlotEnergy(inventoryCustom, 2, 77, 44));
		this.addSlotToContainer(new SlotEnergy(inventoryCustom, 3, 77, 62));
		this.addSlotToContainer(new SlotEnergy(inventoryCustom, 4, 113, 35));
		
		for (i = 0; i < 4; ++i)
		{
			this.addSlotToContainer(new SlotArmor(player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18, 

					i));
		}
		
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

		
			if (par2 < INV_START)
			{
				
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			
			else
			{
				
				if (itemstack1.getItem() instanceof IEnergyBar)
				{
					if (!this.mergeItemStack(itemstack1, 0, InventoryEnergyPlayer.INV_SIZE, false))
					{
						return null;
					}
				}
				
				else if (itemstack1.getItem() instanceof ItemArmor)
				{
					int type = ((ItemArmor) itemstack1.getItem()).armorType;
					if (!this.mergeItemStack(itemstack1, ARMOR_START + type, ARMOR_START + type + 1, false))
					{
						return null;
					}
				}
				
				else if (par2 >= INV_START && par2 < HOTBAR_START)
				{
					
					if (!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_START + 1, false))
					{
						return null;
					}
				}
				
				else if (par2 >= HOTBAR_START && par2 < HOTBAR_END + 1)
				{
					if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false))
					{
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
	

}
