package com.thunder.Creative;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.base.Throwables;

import cpw.mods.fml.relauncher.ReflectionHelper;
import ic2.core.IC2DamageSource;
import ic2.core.IC2Potion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AgonyPotion extends Potion {
	
	public static AgonyPotion agony;
	
	private ItemStack stackIcon;
	
	public void setIcon(ItemStack icon)
	{
		this.stackIcon = icon;
	}
	  
	public AgonyPotion(int id1, boolean badEffect, int liquidColor)
	  {
	    super(id1, badEffect, liquidColor);
		this.setEffectiveness(0.25D);
	    
	  }   
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc)
	{
		x += 6;
		y += 7;
		net.minecraft.client.renderer.entity.RenderItem itemRender = new net.minecraft.client.renderer.entity.RenderItem();
		if (this.stackIcon == null) this.stackIcon = new ItemStack(Items.blaze_powder);
		net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
		itemRender.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), this.stackIcon, x, y, false);
		net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
	}
}
