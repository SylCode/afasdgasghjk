package com.thunder.inventory;


import com.thunder.EnergyAdditions.EnergyAdditionsCommonProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedInventory implements IExtendedEntityProperties {
	
	public final static String EXT_PROP_NAME = "ExtendedInventory";
	private final EntityPlayer player;
	
	public final InventoryEnergyPlayer inventory = new InventoryEnergyPlayer();
	
	public ExtendedInventory(EntityPlayer player)
	{
		this.player = player;

	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedInventory.EXT_PROP_NAME, new ExtendedInventory(player));
	}
	
	public static final ExtendedInventory get(EntityPlayer player)
	{
		return (ExtendedInventory) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	public void copy(ExtendedInventory props) {
		inventory.copy(props.inventory);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
		
		this.inventory.writeToNBT(properties);
		compound.setTag(EXT_PROP_NAME, properties);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.inventory.readFromNBT(properties);

	}
	
	@Override
	public void init(Entity entity, World world)
	{
	}	
	
}