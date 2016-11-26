package com.thunder.client;


import com.thunder.Creative.CreativeItemList;
import com.thunder.Creative.GundiveBowArrow;
import com.thunder.Creative.GundiveBowArrowRenderer;
import com.thunder.Creative.EpicRenderers.*;
import com.thunder.EnergyAdditions.EnergyAdditionsCommonProxy;
import com.thunder.Items.ItemList;
import com.thunder.Keys.KeyInputHandler;
import com.thunder.Keys.Keybindings;
import com.thunder.Rendering.ShieldRenderer1;
import com.thunder.Rendering.ShieldRenderer2;
import com.thunder.Rendering.ShieldRenderer3;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;


public class EnergyAdditionsClientProxy  extends EnergyAdditionsCommonProxy {
	
		private final Minecraft mc = Minecraft.getMinecraft();
	  
	    public void preInit()
	    {
	      super.preInit();
	    }
	    
	    public void init()
	    {
	      super.init();
			FMLCommonHandler.instance().bus().register(new KeyInputHandler());
			for(Keybindings key : Keybindings.values()){
				ClientRegistry.registerKeyBinding(key.getKeybind());
			}
			
			RenderingRegistry.registerEntityRenderingHandler(GundiveBowArrow.class, new GundiveBowArrowRenderer());
			MinecraftForgeClient.registerItemRenderer(CreativeItemList.GundiveBow, new EpicBowRenderer(1.0F));
			MinecraftForgeClient.registerItemRenderer(CreativeItemList.SuicideSquadStuff, new SuicideSquadStuffRenderer(1.0F));
			MinecraftForgeClient.registerItemRenderer(CreativeItemList.IonisSword, new IonisSwordRenderer(1.0F));
			MinecraftForgeClient.registerItemRenderer(CreativeItemList.SoulAmulet, new SoulAmuletRenderer(1.0F));
			MinecraftForgeClient.registerItemRenderer(ItemList.EnergyShield1, new ShieldRenderer1(1.0F));
			MinecraftForgeClient.registerItemRenderer(ItemList.EnergyShield2, new ShieldRenderer2(1.0F));
			MinecraftForgeClient.registerItemRenderer(ItemList.EnergyShield3, new ShieldRenderer3(1.0F));
	    }
	    
		@Override
		public EntityPlayer getPlayerEntity(MessageContext ctx) {
			return (ctx.side.isClient() ? mc.thePlayer : super.getPlayerEntity(ctx));
		}


}
