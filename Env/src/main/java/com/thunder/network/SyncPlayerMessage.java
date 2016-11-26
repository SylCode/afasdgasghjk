package com.thunder.network;

import java.io.IOException;

import com.thunder.inventory.ExtendedInventory;
import com.thunder.network.AbstractMessage.AbstractClientMessage;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SyncPlayerMessage extends AbstractClientMessage<SyncPlayerMessage>{
	
	private NBTTagCompound data;

	public SyncPlayerMessage() {}

	public SyncPlayerMessage(EntityPlayer player) {
		data = new NBTTagCompound();
		ExtendedInventory.get(player).saveNBTData(data);
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		ExtendedInventory.get(player).loadNBTData(data);
	}

}
