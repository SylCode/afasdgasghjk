package com.thunder.EnergyAdditions;

import com.thunder.inventory.ContainerEnergyPlayer;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.GuiEnergyInventory;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EnergyAdditionsCommonProxy implements IGuiHandler {
	
	  public void preInit() {}
	  
	  public void init() {}
	  
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
			
			return ctx.getServerHandler().playerEntity;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if (ID == EnergyAdditionsCore.GUI_CUSTOM_INV) {
			return new ContainerEnergyPlayer(player, player.inventory, ExtendedInventory.get(player).inventory);
		} else {
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if (ID == EnergyAdditionsCore.GUI_CUSTOM_INV) {
			return new GuiEnergyInventory(player, player.inventory, ExtendedInventory.get(player).inventory);
		} else {
			return null;
		}
	
	}	  

}
