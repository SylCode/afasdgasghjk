package com.thunder.EnergyAdditions;

import com.thunder.Blocks.AEnergyChargerContainer;
import com.thunder.Blocks.AEnergyChargerGui;
import com.thunder.Blocks.ATileEntityElectricBlock;
import com.thunder.Blocks.AdvancedEnergyChargerTileEntity;
import com.thunder.inventory.ContainerEnergyPlayer;
import com.thunder.inventory.ExtendedInventory;
import com.thunder.inventory.GuiEnergyInventory;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EnergyAdditionsCommonProxy implements IGuiHandler {
	
	  public void preInit() {
		  
		  
	  }
	  
	  public void init() {}
	  
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
			
			return ctx.getServerHandler().playerEntity;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (ID == EnergyAdditionsCore.GUI_CUSTOM_INV) {
			return new ContainerEnergyPlayer(player, player.inventory, ExtendedInventory.get(player).inventory);
		} else if(ID == EnergyAdditionsCore.GUI_ADC){
			
			if ((entity instanceof ATileEntityElectricBlock)) {
			      return new AEnergyChargerContainer(player, (ATileEntityElectricBlock)entity);
			    }
			
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (ID == EnergyAdditionsCore.GUI_CUSTOM_INV) {
			return new GuiEnergyInventory(player, player.inventory, ExtendedInventory.get(player).inventory);
		} else if(ID == EnergyAdditionsCore.GUI_ADC){
			
			if ((entity instanceof ATileEntityElectricBlock)) {
			      return new AEnergyChargerGui(new AEnergyChargerContainer(player, (ATileEntityElectricBlock)entity));
				}
			
		}
		return null;
	
	}	  

}
