package com.thunder.Keys;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

public enum Keybindings {
	
	GuiY("key.GuiWC.gui1", Keyboard.KEY_Y);
	
	private final KeyBinding keybinding;
	
	private Keybindings(String keyName, int defaultKeyCode){
		keybinding = new KeyBinding(keyName, defaultKeyCode, "GuiWC");
	}

	public KeyBinding getKeybind(){
		return keybinding;
	}
	
	public boolean isPressed(){
		return keybinding.isPressed();
	}
}
