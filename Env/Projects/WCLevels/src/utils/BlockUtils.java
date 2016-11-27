package utils;

 import org.bukkit.block.BlockState;
 
 
 public final class BlockUtils
 { 
   public static boolean isOre(BlockState blockState)
   {
     return MaterialUtils.isOre(blockState.getData());
   }
 }