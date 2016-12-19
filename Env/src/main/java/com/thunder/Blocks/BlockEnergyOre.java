package com.thunder.Blocks;

import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockEnergyOre extends Block {

	protected BlockEnergyOre() {
		super(Material.iron);
		
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setHarvestLevel("pickaxe", 2);
		this.setStepSound(soundTypeMetal);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Icon){
		
		this.blockIcon = Icon.registerIcon("thunder:" + "BlockEnergyOre");
	}

}
