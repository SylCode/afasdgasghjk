package listeners;
 
 import java.util.Collection;
import java.util.Random;

import org.bukkit.EntityEffect;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thunder.Creative.CreativePotionList;

import sylcode.levels.WCLevels;
import utils.WCUtil;
 
 
 public class WCListener
   implements Listener
 {
   WCLevels plg;
   WCUtil u;
   
   public WCListener(WCLevels plg)
   {
     this.plg = plg;
     this.u = plg.u;
   }
   
   @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
   public void onPlayerJoin(PlayerJoinEvent event) 
   {
	 //Bukkit.getLogger().info("[WCLevels] Capturing player");
     Player p = event.getPlayer();
     //Bukkit.getLogger().info("[WCLevels] Player captured");
     this.plg.players.addPlayer(p);
     //Bukkit.getLogger().info("[WCLevels] Player "+ p.getName() + " Added");
     
     //if (p.hasMetadata("NP-checktime")) p.removeMetadata("NP-checktime", this.plg);
   	     //this.plg.players.printPlayerProtected(p, false, true);
   }
   
   @EventHandler
   public void onPlayerChestOpen(InventoryOpenEvent e)
   {
	   
   }
   
   @EventHandler
   public void onPlayerInteract(PlayerInteractEvent event) 
   {
	   if (event.getAction() == Action.RIGHT_CLICK_BLOCK) 
	   {
		   Block b = event.getClickedBlock();
		   if(b instanceof Chest)
		   {
			   if (b.getMetadata("PLACED").size()!=0) return;
			   
			   Player p = event.getPlayer();
			   Random r = new Random(System.currentTimeMillis());
			   if(r.nextFloat() < 0.05F || r.nextFloat() > 0.95F)
			   {
				   p.playEffect(EntityEffect.HURT);
				   p.addPotionEffect(new PotionEffect(PotionEffectType.getById(52), 20*300, 3));
			   }
		   }
		   
	   }
   }
   
   @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
   public void onPlayerQuit(PlayerQuitEvent event) {
     //Player p = event.getPlayer();
     //if (p.hasMetadata("NP-checktime")) p.removeMetadata("NP-checktime", this.plg);
     this.plg.players.savePlayerList();
   }
 }


