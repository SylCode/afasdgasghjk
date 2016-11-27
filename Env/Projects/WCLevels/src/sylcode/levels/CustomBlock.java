package sylcode.levels;

 
 public class CustomBlock {
   private int xpGain;
   private boolean canDoubleDrop;
   private int smeltingXpGain;
   
   public CustomBlock(int xpGain, boolean canDoubleDrop, int smeltingXpGain) {
     this.xpGain = xpGain;
     this.canDoubleDrop = canDoubleDrop;
     this.smeltingXpGain = smeltingXpGain;
   }
   
   public int getXpGain() {
     return this.xpGain;
   }
   
   public boolean isDoubleDropEnabled() {
     return this.canDoubleDrop;
   }
   
   public int getSmeltingXpGain() {
     return this.smeltingXpGain;
   }
 }

