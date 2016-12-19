package com.thunder.Blocks;

import com.thunder.EnergyAdditions.EnergyAdditionsCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class EnergyOre extends BlockOre {
	

	public EnergyOre(){
		super();
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(EnergyAdditionsCore.tabEnergyAdditions);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setHarvestLevel("pickaxe", 2);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Icon){
		
		this.blockIcon = Icon.registerIcon("thunder:" + "EnergyOre");
	}
	
	

}
