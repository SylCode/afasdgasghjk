package com.thunder.Blocks;

import java.util.List;

import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlotArmor;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

public class AEnergyChargerContainer extends ContainerFullInv<ATileEntityElectricBlock>{
	
	public AEnergyChargerContainer(EntityPlayer player, ATileEntityElectricBlock tileEntity)
	  {
	    super(player, tileEntity, 196);
	    for (int col = 0; col < 4; col++) {
	      addSlotToContainer(new InvSlotArmor(player.inventory, col, 8 + col * 18, 84));
	    }
	    if(((ATileEntityElectricBlock)this.base).tier == 5){
	    	addSlotToContainer(new SlotInvSlot(tileEntity.dischargeSlot2, 0, 56, 23));
	        addSlotToContainer(new SlotInvSlot(tileEntity.dischargeSlot1, 0, 56, 47));
	        
	        addSlotToContainer(new SlotInvSlot(tileEntity.chargeSlot1, 0, 98, 84));
	        addSlotToContainer(new SlotInvSlot(tileEntity.chargeSlot2, 0, 116, 84));
	        addSlotToContainer(new SlotInvSlot(tileEntity.chargeSlot3, 0, 134, 84));
	        addSlotToContainer(new SlotInvSlot(tileEntity.chargeSlot4, 0, 152, 84));
	    } else
	    	if(((ATileEntityElectricBlock)this.base).tier == 4){
	    		addSlotToContainer(new SlotInvSlot(tileEntity.dischargeSlot1, 0, 56, 35));
	    	}
	  }
	  
	  public List<String> getNetworkedFields()
	  {
	    List<String> ret = super.getNetworkedFields();
	    
	    ret.add("energy");
	    ret.add("redstoneMode");
	    ret.add("dischargeSlot2");
	    ret.add("dischargeSlot1");
	    ret.add("chargeSlot1");
	    ret.add("chargeSlot2");
	    ret.add("chargeSlot3");
	    ret.add("chargeSlot4");
	    ret.add("voltageMode");
	    
	    return ret;
	  }
	
	

}
