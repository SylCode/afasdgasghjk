package com.thunder.Generation;

import java.util.Random;

import com.thunder.Blocks.BlockList;
import com.thunder.EnergyAdditions.Config;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class EnergyOreGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,	IChunkProvider chunkProvider) {
		
		 switch(world.provider.dimensionId)
		 
         {   
		 		 case 1:  
		 			 break;
                 case 0:
                	 
                	 int baseHeight = getSeaLevel(world) + 1;
                	 int baseScale = Math.round(baseHeight * 1.0f);
                	 if (Config.EnergyOreSpawn)
                	    {
                	      int baseCount = 15 * baseScale / 64;
                	      int count = (int)Math.round(random.nextGaussian() * Math.sqrt(baseCount) + baseCount);
                	      for (int n = 0; n < count; n++)
                	      {
                	        int x = chunkX * 16 + random.nextInt(16);
                	        int y = random.nextInt(40 * baseHeight / 64) + random.nextInt(20 * baseHeight / 64) + 10 * baseHeight / 64;
                	        int z = chunkZ * 16 + random.nextInt(16);
                	        new WorldGenMinable(BlockList.EnergyOre, 5, Blocks.stone).generate(world, random, x, y, z);
                	      }
                	    }	 
                	 
                	 break;
                 case -1:  
                	 break;                    
         }
		
	}

    public static int getSeaLevel(World world)
    {
      return world.provider.getAverageGroundLevel();
    }
 
    


}
