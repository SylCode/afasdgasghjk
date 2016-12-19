package com.thunder.Blocks;

import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.IEnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.tile.IEnergyStorage;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.Platform;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.invslot.InvSlot.Access;
import ic2.core.block.invslot.InvSlot.InvSide;
import ic2.core.block.invslot.InvSlotCharge;
import ic2.core.block.invslot.InvSlotDischarge;
import ic2.core.block.wiring.ContainerElectricBlock;
import ic2.core.block.wiring.GuiElectricBlock;
import ic2.core.init.MainConfig;
import ic2.core.util.ConfigUtil;
import ic2.core.util.StackUtil;
import ic2.core.util.Util;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class ATileEntityElectricBlock
  extends TileEntityInventory
  implements IEnergySink, IEnergySource, IHasGui, INetworkClientTileEntityEventListener, IEnergyStorage
{
  public final int tier;
  public final int output;
  public final int maxStorage;
  
  public ATileEntityElectricBlock(int tier1, int output1, int maxStorage1)
  {
    this.tier = tier1;
    this.output = output1;
    this.maxStorage = maxStorage1;
    
    
    this.chargeSlot1 = new InvSlotCharge(this, 0, tier1);
    this.chargeSlot2 = new InvSlotCharge(this, 1, tier1);
    this.chargeSlot3 = new InvSlotCharge(this, 2, tier1);
    this.chargeSlot4 = new InvSlotCharge(this, 3, tier1);
    this.dischargeSlot1 = new InvSlotDischarge(this, 4, InvSlot.Access.IO, tier1, InvSlot.InvSide.BOTTOM);
    this.dischargeSlot2 = new InvSlotDischarge(this, 5, InvSlot.Access.IO, tier1, InvSlot.InvSide.BOTTOM);
  }
  
  public float getChargeLevel()
  {
    float ret = (float)this.energy / this.maxStorage;
    if (ret > 1.0F) {
      ret = 1.0F;
    }
    return ret;
  }
  
  public void readFromNBT(NBTTagCompound nbttagcompound)
  {
    super.readFromNBT(nbttagcompound);
    this.voltageMode = nbttagcompound.getByte("voltageMode");
    this.energy = Util.limit(nbttagcompound.getDouble("energy"), 0.0D, this.maxStorage + EnergyNet.instance.getPowerFromTier(this.tier));
    this.redstoneMode = nbttagcompound.getByte("redstoneMode");
  }
  
  public void writeToNBT(NBTTagCompound nbttagcompound)
  {
    super.writeToNBT(nbttagcompound);
    nbttagcompound.setByte("voltageMode", this.voltageMode);
    nbttagcompound.setDouble("energy", this.energy);
    nbttagcompound.setBoolean("active", getActive());
    nbttagcompound.setByte("redstoneMode", this.redstoneMode);
  }
  
  public void onLoaded()
  {
    super.onLoaded();
    if (IC2.platform.isSimulating())
    {
      MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
      this.addedToEnergyNet = true;
    }
  }
  
  public void onUnloaded()
  {
    if ((IC2.platform.isSimulating()) && (this.addedToEnergyNet))
    {
      MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
      
      this.addedToEnergyNet = false;
    }
    super.onUnloaded();
  }
  
  protected void updateEntityServer()
  {
    super.updateEntityServer();
    
    boolean needsInvUpdate = false;
    if (this.energy >= 1.0D)
    {
      double sent1 = this.chargeSlot1.charge(this.energy);
      double sent2 = this.chargeSlot2.charge(this.energy);
      double sent3 = this.chargeSlot3.charge(this.energy);
      double sent4 = this.chargeSlot4.charge(this.energy);
      
      this.energy -= sent1;
      this.energy -= sent2;
      this.energy -= sent3;
      this.energy -= sent4;
      if(sent1 > 0.0D || sent2 > 0.0D || sent3 > 0.0D || sent4 > 0.0D)
      needsInvUpdate = true;
    }
    if (((getDemandedEnergy() > 0.0D) && (!this.dischargeSlot1.isEmpty())) || ((getDemandedEnergy() > 0.0D) && (!this.dischargeSlot2.isEmpty())))
    {
      double gain1 = this.dischargeSlot1.discharge(this.maxStorage - this.energy, false);
      double gain2 = this.dischargeSlot2.discharge(this.maxStorage - this.energy, false);
      
      this.energy += gain1;
      this.energy += gain2;
      
      if(gain1 > 0.0D || gain2 > 0.0D)
      needsInvUpdate = true;
    }
    if ((this.redstoneMode == 5) || (this.redstoneMode == 6)) {
      this.hasRedstone = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
    }
    boolean shouldEmitRedstone = shouldEmitRedstone();
    if (shouldEmitRedstone != this.isEmittingRedstone)
    {
      this.isEmittingRedstone = shouldEmitRedstone;
      setActive(this.isEmittingRedstone);
      
      this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
    }
    if (needsInvUpdate) {
      markDirty();
    }
  }
  
  public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
  {
    return !facingMatchesDirection(direction);
  }
  
  public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction)
  {
    return facingMatchesDirection(direction);
  }
  
  public boolean facingMatchesDirection(ForgeDirection direction)
  {
    return direction.ordinal() == getFacing();
  }
  
  public double getOfferedEnergy()
  {
    if ((this.energy >= this.output) && ((this.redstoneMode != 5) || (!this.hasRedstone)) && ((this.redstoneMode != 6) || (!this.hasRedstone) || (this.energy >= this.maxStorage))) {
      return Math.min(this.energy, this.output);
    }
    return 0.0D;
  }
  
  public void drawEnergy(double amount)
  {
    this.energy -= amount;
  }
  
  public double getDemandedEnergy()
  {
    return this.maxStorage - this.energy;
  }
  
  public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage)
  {
    if (this.energy >= this.maxStorage) {
      return amount;
    }
    this.energy += amount;
    return 0.0D;
  }
  
  public int getSourceTier()
  {
    return this.tier;
  }
  
  public int getSinkTier()
  {
    return this.tier;
  }
  
  public ContainerBase<? extends ATileEntityElectricBlock> getGuiContainer(EntityPlayer entityPlayer)
  {
    return new AEnergyChargerContainer(entityPlayer, this);
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin)
  {
    return new AEnergyChargerGui(new AEnergyChargerContainer(entityPlayer, this));
  }
  
  public void onGuiClosed(EntityPlayer entityPlayer) {}
  
  public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side)
  {
    return getFacing() != side;
  }
  
  public void setFacing(short facing)
  {
    if (this.addedToEnergyNet) {
      MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }
    super.setFacing(facing);
    if (this.addedToEnergyNet)
    {
      this.addedToEnergyNet = false;
      MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
      this.addedToEnergyNet = true;
    }
  }
  
  public boolean isEmittingRedstone()
  {
    return this.isEmittingRedstone;
  }
  
  public boolean shouldEmitRedstone()
  {
    boolean shouldEmitRedstone = false;
    switch (this.redstoneMode)
    {
    case 1: 
      shouldEmitRedstone = this.energy >= this.maxStorage - this.output * 20;
      break;
    case 2: 
      shouldEmitRedstone = (this.energy > this.output) && (this.energy < this.maxStorage);
      break;
    case 3: 
      shouldEmitRedstone = ((this.energy > this.output) && (this.energy < this.maxStorage)) || (this.energy < this.output);
      break;
    case 4: 
      shouldEmitRedstone = this.energy < this.output;
    }
    if ((this.isEmittingRedstone == shouldEmitRedstone) || (this.redstoneUpdateInhibit == 0))
    {
      this.redstoneUpdateInhibit = 5;
      
      return shouldEmitRedstone;
    }
    this.redstoneUpdateInhibit -= 1;
    
    return this.isEmittingRedstone;
  }
  
  public void onNetworkEvent(EntityPlayer player, int event)
  {
	switch(event){
	case 0:
    this.redstoneMode = ((byte)(this.redstoneMode + 1));
    if (this.redstoneMode >= redstoneModes) {
      this.redstoneMode = 0;
    }
    IC2.platform.messagePlayer(player, getredstoneMode(), new Object[0]);
    break;
	case 1:
		this.voltageMode = 1;
		break;
	case 2:
		this.voltageMode = 2;
		break;
	case 3:
		this.voltageMode = 3;
		break;
	case 4:
		this.voltageMode = ((byte)(this.voltageMode + 1));
	    if (this.voltageMode >= voltageModes) {
	      this.voltageMode = 0;
	    }
	    IC2.platform.messagePlayer(player, StatCollector.translateToLocal("gui.advcharger.tooltip2") + this.voltageMode, new Object[0]);
		break;
	}
  }
  
  
  public int getVoltage(){
	  
	  if(this.voltageMode == 1) return 512;
	  if(this.voltageMode == 2) return 2048;
	  if(this.voltageMode == 3) return 8192;
	  return 0;
  }
  
  public Byte getVoltageMode(){
	  
	  return this.voltageMode;
  }
  
  public boolean canEmitEnergy(){
	  
	  return this.voltageMode != 0;
  }
  
  public String getredstoneMode()
  {
    if ((this.redstoneMode > 6) || (this.redstoneMode < 0)) {
      return "";
    }
    return StatCollector.translateToLocal("ic2.EUStorage.gui.mod.redstone" + this.redstoneMode);
  }
  
  public ItemStack getWrenchDrop(EntityPlayer entityPlayer)
  {
    ItemStack ret = super.getWrenchDrop(entityPlayer);
    
    float energyRetainedInStorageBlockDrops = ConfigUtil.getFloat(MainConfig.get(), "balance/energyRetainedInStorageBlockDrops");
    if (energyRetainedInStorageBlockDrops > 0.0F)
    {
      NBTTagCompound nbttagcompound = StackUtil.getOrCreateNbtData(ret);
      nbttagcompound.setDouble("energy", this.energy * energyRetainedInStorageBlockDrops);
    }
    return ret;
  }
  
  public int getStored()
  {
    return (int)this.energy;
  }
  
  public int getCapacity()
  {
    return this.maxStorage;
  }
  
  public int getOutput()
  {
    return this.output;
  }
  
  public double getOutputEnergyUnitsPerTick()
  {
    return this.output;
  }
  
  public void setStored(int energy1)
  {
    this.energy = energy1;
  }
  
  public int addEnergy(int amount)
  {
    this.energy += amount;
    return amount;
  }
  
  public boolean isTeleporterCompatible(ForgeDirection side)
  {
    return true;
  }
  
  public double energy = 0.0D;
  public boolean hasRedstone = false;
  public byte redstoneMode = 0;
  public static byte redstoneModes = 7;
  public byte voltageMode = 1;
  public static byte voltageModes = 4;
  private boolean isEmittingRedstone = false;
  private int redstoneUpdateInhibit = 5;
  public boolean addedToEnergyNet = false;
  public final InvSlotCharge chargeSlot1;
  public final InvSlotCharge chargeSlot2;
  public final InvSlotCharge chargeSlot3;
  public final InvSlotCharge chargeSlot4;
  public final InvSlotDischarge dischargeSlot1;
  public final InvSlotDischarge dischargeSlot2;

}
