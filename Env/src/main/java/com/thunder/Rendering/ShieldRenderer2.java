package com.thunder.Rendering;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.INVENTORY;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.thunder.Items.ItemList;
import com.thunder.Items.IEnergyShield2;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class ShieldRenderer2 implements IItemRenderer 
{
	 private final float scale;
	 private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	   
	   public ShieldRenderer2(float scale)
	   {
	     this.scale = scale;
	   }
	   
	   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
	   {
	     return (type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || (type == IItemRenderer.ItemRenderType.INVENTORY)|| (type == IItemRenderer.ItemRenderType.ENTITY));
	   }
	   
	   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
	   {
		   if(type == INVENTORY)
			   return false;
		   return false;
	   }
	   
	   @SuppressWarnings("incomplete-switch")
	public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
	   {
	     switch (type) {
	     case EQUIPPED_FIRST_PERSON: 
	         renderEquippedItem(item, (EntityLivingBase)data[1], true);
	       break;
	     case EQUIPPED: 
	       renderEquippedItem(item, (EntityLivingBase)data[1], false);
	       break;
	     case INVENTORY:
	    	 renderInventoryItem(item);
	    	 break;
	     case ENTITY:
	    	 renderEntity(item);
	    	 break;
	     }
	   }
	   private void renderEntity(ItemStack stack)
	   {
		   GL11.glPushMatrix();
		   GL11.glScalef(1F, 1F, 1F);
		   GL11.glTranslatef(0.0F, -0.1F, -1.5F);
		   GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		   GL11.glTranslatef(-0.5F, 0.0F, -1.5F);
		   
		   IIcon icon = stack.getItem().getIcon(stack, 0);
		   Item item = stack.getItem();
		   Tessellator tessellator = Tessellator.instance;
		   ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
		   IEnergyShield2 sh1 = (IEnergyShield2) item;
		     
		    if(sh1.isActive(stack))
		    {
		        GL11.glDepthFunc(GL11.GL_EQUAL);
		        GL11.glDisable(GL11.GL_LIGHTING);
		        Minecraft.getMinecraft().getTextureManager().bindTexture(RES_ITEM_GLINT);
		        GL11.glEnable(GL11.GL_BLEND);
		        OpenGlHelper.glBlendFunc(768, 1, 1, 0);
		        //float f7 = 0.76F;
		        GL11.glColor4f(0.6F, 0.9F, 0.0F, 1.0F);
		        GL11.glMatrixMode(GL11.GL_TEXTURE);
		        GL11.glPushMatrix();
		        float f8 = 0.125F;
		        GL11.glScalef(f8, f8, f8);
		        float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
		        GL11.glTranslatef(f9, 0.0F, 0.0F);
		        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
		        ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		        GL11.glPopMatrix();
		        GL11.glPushMatrix();
		        GL11.glScalef(f8, f8, f8);
		        f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
		        GL11.glTranslatef(-f9, 0.0F, 0.0F);
		        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
		        ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		        GL11.glPopMatrix();
		        GL11.glMatrixMode(GL11.GL_MODELVIEW);
		        GL11.glDisable(GL11.GL_BLEND);
		        GL11.glEnable(GL11.GL_LIGHTING);
		        GL11.glDepthFunc(GL11.GL_LEQUAL);
		    }
		     GL11.glScalef(1, 1, 1);
		     GL11.glPopMatrix();
	   }
	   private void renderInventoryItem(ItemStack stack)
	   {
		   GL11.glPushMatrix();
		   GL11.glScalef(15F, 15F, 15F);
		   GL11.glTranslatef(0.5F, 1.0F, 0.5F);
		   GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		   GL11.glTranslatef(-0.5F, 0.0F, -1.5F);
		   
		   IIcon icon = stack.getItem().getIcon(stack, 0);
		   Item item = stack.getItem();
		   Tessellator tessellator = Tessellator.instance;
		   ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
		   IEnergyShield2 sh1 = (IEnergyShield2) item;
		     
		    if(sh1.isActive(stack))
		    {
		        GL11.glDepthFunc(GL11.GL_EQUAL);
		        GL11.glDisable(GL11.GL_LIGHTING);
		        Minecraft.getMinecraft().getTextureManager().bindTexture(RES_ITEM_GLINT);
		        GL11.glEnable(GL11.GL_BLEND);
		        OpenGlHelper.glBlendFunc(768, 1, 1, 0);
		        //float f7 = 0.76F;
		        GL11.glColor4f(0.6F, 0.9F, 0.0F, 1.0F);
		        GL11.glMatrixMode(GL11.GL_TEXTURE);
		        GL11.glPushMatrix();
		        float f8 = 0.125F;
		        GL11.glScalef(f8, f8, f8);
		        float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
		        GL11.glTranslatef(f9, 0.0F, 0.0F);
		        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
		        ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		        GL11.glPopMatrix();
		        GL11.glPushMatrix();
		        GL11.glScalef(f8, f8, f8);
		        f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
		        GL11.glTranslatef(-f9, 0.0F, 0.0F);
		        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
		        ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		        GL11.glPopMatrix();
		        GL11.glMatrixMode(GL11.GL_MODELVIEW);
		        GL11.glDisable(GL11.GL_BLEND);
		        GL11.glEnable(GL11.GL_LIGHTING);
		        GL11.glDepthFunc(GL11.GL_LEQUAL);
		    }
		    GL11.glScalef(1, 1, 1);
		    GL11.glPopMatrix();
	   }
	   
	   private void renderEquippedItem(ItemStack stack, EntityLivingBase entity, boolean firstPerson)
	   {
		 Item item = stack.getItem();
		 
		 

	     GL11.glPushMatrix();
	     float f = this.scale;
	     if (firstPerson) {
		       f *= 1.6F;
		       GL11.glScalef(f, f, f);
		       GL11.glTranslatef(0.3F, 0.1F*this.scale, 0.2F);
		       GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
		       GL11.glRotatef(95.0F, 0.0F, 1.0F, 0.0F);
		       GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
		     } else {
		       f =5.3F;
		       GL11.glScalef(f, f, f);
		       GL11.glTranslatef(-0.2F, -0.2F * this.scale, 0.4F * this.scale);
		       GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
		       GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
		       GL11.glRotatef(-57.0F, 1.0F, 0.0F, 0.0F);
		     }
	     IIcon icon = stack.getItem().getIcon(stack, 0);
	     Tessellator tessellator = Tessellator.instance;
	     ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
	     
	     IEnergyShield2 sh1 = (IEnergyShield2) item;
	     
	    if(sh1.isActive(stack))
	    {
	        GL11.glDepthFunc(GL11.GL_EQUAL);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        Minecraft.getMinecraft().getTextureManager().bindTexture(RES_ITEM_GLINT);
	        GL11.glEnable(GL11.GL_BLEND);
	        OpenGlHelper.glBlendFunc(768, 1, 1, 0);
	        //float f7 = 0.76F;
	        GL11.glColor4f(0.6F, 0.9F, 0.0F, 1.0F);
	        GL11.glMatrixMode(GL11.GL_TEXTURE);
	        GL11.glPushMatrix();
	        float f8 = 0.125F;
	        GL11.glScalef(f8, f8, f8);
	        float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
	        GL11.glTranslatef(f9, 0.0F, 0.0F);
	        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
	        ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
	        GL11.glPopMatrix();
	        GL11.glPushMatrix();
	        GL11.glScalef(f8, f8, f8);
	        f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
	        GL11.glTranslatef(-f9, 0.0F, 0.0F);
	        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
	        ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
	        GL11.glPopMatrix();
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glDepthFunc(GL11.GL_LEQUAL);
	    }
	     GL11.glPopMatrix();
	   }
	 }

	 
