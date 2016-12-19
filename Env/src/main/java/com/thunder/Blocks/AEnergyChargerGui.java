package com.thunder.Blocks;

import org.lwjgl.opengl.GL11;

import com.thunder.Items.ItemList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.GuiIconButton;
import ic2.core.IC2;
import ic2.core.network.NetworkManager;
import ic2.core.util.GuiTooltipHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

@SideOnly(Side.CLIENT)
public class AEnergyChargerGui extends GuiContainer{
	
	  private final AEnergyChargerContainer container;
	  private final String armorInv;
	  private final String level;
	  private final String name;
	  
	  ItemStack binder = new ItemStack(ItemList.EnergyBinder);
	  
	  public AEnergyChargerGui(AEnergyChargerContainer container)
	  {
	    super(container);
	    
	    this.ySize = 196;
	    this.container = container;
	    this.armorInv = StatCollector.translateToLocal("ic2.EUStorage.gui.info.armor");
	    this.level = StatCollector.translateToLocal("ic2.EUStorage.gui.info.level");
	    
	    switch (((ATileEntityElectricBlock)container.base).tier)
	    {
	    case 4: this.name = StatCollector.translateToLocal("gui.charger.name"); break;
	    case 5: this.name = StatCollector.translateToLocal("gui.advcharger.name"); break;
	    default:
	    	this.name = "Charger";
	    }
	    
	    
	  }
	  
	  public void initGui()
	  {
	    super.initGui();

	    if(((ATileEntityElectricBlock)this.container.base).tier == 4){
	    	
	    	this.buttonList.add(new GuiButton(1, (this.width - this.xSize) / 2 + 144, (this.height - this.ySize) / 2 + 60, 28, 20, "512"));
	    	this.buttonList.add(new GuiButton(2, (this.width - this.xSize) / 2 + 115, (this.height - this.ySize) / 2 + 60, 28, 20, "2048"));
	    	this.buttonList.add(new GuiButton(3, (this.width - this.xSize) / 2 + 86, (this.height - this.ySize) / 2 + 60, 28, 20, "8192"));
	    } else if(((ATileEntityElectricBlock)this.container.base).tier == 5)
	    	this.buttonList.add(new GuiButton(4, (this.width - this.xSize) / 2 + 7, (this.height - this.ySize) / 2 + 21, 30, 20, StatCollector.translateToLocal("gui.advcharger.tooltip3")));
	    this.buttonList.add(new GuiIconButton(0, (this.width - this.xSize) / 2 + 152, (this.height - this.ySize) / 2 + 4, 20, 20, new ItemStack(Items.redstone), true));
	  }
	  
	  protected void drawGuiContainerForegroundLayer(int par1, int par2)
	  {
		if(((ATileEntityElectricBlock)this.container.base).tier == 4){
	    this.fontRendererObj.drawString(this.name, (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 6, 4210752);
	    this.fontRendererObj.drawString(this.armorInv, 8, this.ySize - 126 + 3, 4210752);
	    
	    this.fontRendererObj.drawString(this.level, 79, 25, 4210752);
	    int e = (int)Math.min(((ATileEntityElectricBlock)this.container.base).energy, ((ATileEntityElectricBlock)this.container.base).maxStorage);
	    this.fontRendererObj.drawString(" " + e, 110, 35, 4210752);
	    this.fontRendererObj.drawString("/" + ((ATileEntityElectricBlock)this.container.base).maxStorage, 110, 45, 4210752);
	    this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.charger.output"), 94, 102, 4210752);
	    
	    RenderItem renderItem = new RenderItem();
	    RenderHelper.enableGUIStandardItemLighting();
	    switch (((ATileEntityElectricBlock)this.container.base).getVoltageMode())
	    {
	    case 1: 
	      renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, binder, 150, 82);
	      break;
	    case 2: 
	      renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, binder, 121, 82);
	      break;
	    case 3: 
	      renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, binder, 92, 82);
	    }
	    RenderHelper.disableStandardItemLighting();

	    GuiTooltipHelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, ((ATileEntityElectricBlock)this.container.base).getredstoneMode(), 153, 3, 172, 22);
		}else
		{
			 	this.fontRendererObj.drawString(this.name, (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2 -3, 6, 4210752);
			    this.fontRendererObj.drawString(this.armorInv, 8, this.ySize - 126 + 3, 4210752);
			    
			    this.fontRendererObj.drawString(this.level, 79, 25, 4210752);
			    int e = (int)Math.min(((ATileEntityElectricBlock)this.container.base).energy, ((ATileEntityElectricBlock)this.container.base).maxStorage);
			    this.fontRendererObj.drawString(" " + e, 110, 35, 4210752);
			    this.fontRendererObj.drawString("/" + ((ATileEntityElectricBlock)this.container.base).maxStorage, 110, 45, 4210752); 
			    
			    this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.advcharger.chargeslot.name"), 98, 73, 4210752);
			    this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.advcharger.tooltip3") + ": " + ((ATileEntityElectricBlock)this.container.base).getVoltageMode(), 8, 44, 4210752);
			    
			    GuiTooltipHelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, ((ATileEntityElectricBlock)this.container.base).getredstoneMode(), 152, 4, 171, 23);
			    GuiTooltipHelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, StatCollector.translateToLocal("gui.advcharger.tooltip1"), 7, 21, 36, 50);
			    }
		
	  }
	  
	  protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	  {
		if(((ATileEntityElectricBlock)this.container.base).tier == 4){
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.mc.getTextureManager().bindTexture(background1);
	    int j = (this.width - this.xSize) / 2;
	    int k = (this.height - this.ySize) / 2;
	    drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
	    if (((ATileEntityElectricBlock)this.container.base).energy > 0.0D)
	    {
	      int i1 = (int)(24.0F * ((ATileEntityElectricBlock)this.container.base).getChargeLevel());
	      drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
	    }
		}else
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		    this.mc.getTextureManager().bindTexture(background2);
		    int j = (this.width - this.xSize) / 2;
		    int k = (this.height - this.ySize) / 2;
		    drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
		    if (((ATileEntityElectricBlock)this.container.base).energy > 0.0D)
		    {
		      int i1 = (int)(24.0F * ((ATileEntityElectricBlock)this.container.base).getChargeLevel());
		      drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
		    }
			
		}
	  }
	  
	  protected void actionPerformed(GuiButton guibutton)
	  {
	  super.actionPerformed(guibutton);
	    
	      ((NetworkManager)IC2.network.get()).initiateClientTileEntityEvent((TileEntity)this.container.base, guibutton.id);
	  }

	 
	  
	  
	  private static final ResourceLocation background1 = new ResourceLocation("thunder:textures/gui/GUIEnergyCharger.png");
	  private static final ResourceLocation background2 = new ResourceLocation("thunder:textures/gui/GUIAdvancedEnergyCharger.png");
}


