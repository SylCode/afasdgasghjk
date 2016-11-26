package com.thunder.Keys;


import com.thunder.EnergyAdditions.EnergyAdditionsCore;
import com.thunder.EnergyAdditions.EnergyAdditionsInfo;
import com.thunder.inventory.ContainerEnergyPlayer;
import com.thunder.network.OpenGuiMessage;
import com.thunder.network.PacketDispatcher;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class KeyInputHandler {
	
	
	private Keybindings getPressedKey(){
		
		for(Keybindings key : Keybindings.values()){
			if(key.isPressed())return key;
		}
		
		return null;
		
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event){
			
		Keybindings key = getPressedKey();
		if(key != null){

			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			World world = Minecraft.getMinecraft().theWorld;
			switch(key){
			case GuiY:
					PacketDispatcher.sendToServer(new OpenGuiMessage(EnergyAdditionsCore.GUI_CUSTOM_INV));
		            break;
			}
		}
		
	}

}
