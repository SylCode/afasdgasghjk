package com.thunder.Blocks;

import java.util.Random;

import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLight extends Block{
	
	public BlockLight() {
		super(Material.circuits);
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setTickRandomly(true);
		this.setLightOpacity(0);
		this.opaque = false;
		this.setResistance(0.0F);
		this.setLightLevel(0.99F);
		this.setHardness(0.0F);
		this.canBlockGrass = false;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Icon){
		
		this.blockIcon = Icon.registerIcon("thunder:" + "BlockLight");
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean canReplace(World world, int x, int y, int z, int meta, ItemStack stack)
	{
		return true;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (!world.isRemote) world.scheduleBlockUpdate(x, y, z, this, 5);
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int par1, int par2, int par3, int par4, float par5, int par6) {}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return null;
	}
	
	@Override
	public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_)
	{
		return false;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!world.isRemote) world.setBlockToAir(x, y, z);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

}


