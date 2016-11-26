package com.thunder.Blocks;

import java.util.List;
import java.util.Random;

import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IC2Items;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.util.StackUtil;
import ic2.core.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class IEnergyCharger extends Block {
	
	private ChatComponentText chat;
	
	public IEnergyCharger(){
		super(Material.iron);
		this.setStepSound(soundTypeMetal);
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setHardness(1.5F);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Icon){
		
		this.blockIcon = Icon.registerIcon("thunder:" + "EnergyCharger");
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side) {
		TileEntity te = blockAccess.getTileEntity(x, y, z);
	    if (!(te instanceof TileEntityElectricBlock)) {
	      return 0;
	    }
	    return ((TileEntityElectricBlock)te).isEmittingRedstone() ? 15 : 0;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	@Override  
	public boolean isNormalCube(IBlockAccess world, int i, int j, int k)
	{
	    return false;
	}
	
	@Override
	public boolean canProvidePower(){
		return true;
	}
	
	@Override
	public boolean hasComparatorInputOverride(){
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int side)
	{
	    TileEntity te = world.getTileEntity(x, y, z);
	    if (!(te instanceof TileEntityElectricBlock)) {
	      return 0;
	    }
	    TileEntityElectricBlock teb = (TileEntityElectricBlock)te;
	    
	    return (int)Math.round(Util.map(teb.energy, teb.maxStorage, 15.0D));
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side){
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack){
		
		if (!isSimulating()) {
		      return;
		}
		
		TileEntity te = world.getTileEntity(x, y, z);
		if (te == null) {
		      return;
		}
		if (te instanceof TileEntityElectricBlock) {
			
			TileEntityElectricBlock electricBlock = (TileEntityElectricBlock)te;

		      NBTTagCompound nbttagcompound = getOrCreateNbtData(itemStack);
		      ((TileEntityElectricBlock)te).energy = nbttagcompound.getDouble("energy");
		}
	    if (entityliving == null)
	    {
	      return;
	    }
	}
	
	@Override
	public final boolean hasTileEntity(int metadata){
		return true;
	}
	
	@Override
	public final TileEntity createTileEntity(World world, int metadata) {
		return new EnergyChargerTileEntity();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var6, float var7, float var8, float var9){
		
		if (player.getCurrentEquippedItem() == IC2Items.getItem("wrench") || player.getCurrentEquippedItem() == IC2Items.getItem("electricWrench"))
			return true;
		EnergyChargerTileEntity te = (EnergyChargerTileEntity) world.getTileEntity(x, y, z);
		
		if (te instanceof TileEntityElectricBlock) {
		if (!player.isSneaking() && !world.isRemote) {
			
			long energy = (long) te.energy;
			chat = new ChatComponentText(StatCollector.translateToLocal("tag.blocks.energycharger.energy") + energy + " EU");
			player.addChatMessage(chat);
			return true;
			}
		}
		
		return false;
	}
	
	public int quantityDropped(Random random)
	{
	    return 1;
	}
	
	public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack)
	  {
	    NBTTagCompound ret = itemStack.getTagCompound();
	    if (ret == null)
	    {
	      ret = new NBTTagCompound();
	      
	      itemStack.setTagCompound(ret);
	    }
	    return ret;
	  }
	
	public boolean isSimulating()
	  {
	    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	  }
	
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
    {
        boolean flag = world.isBlockIndirectlyGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j + 1, k);
        int l = world.getBlockMetadata(i, j, k);
        boolean flag1 = (l & 8) != 0;

        if (flag && !flag1)
        {
        	world.scheduleBlockUpdate(i, j, k, this, this.tickRate(world));
        }
     
    }

}
