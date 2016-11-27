 package sylcode.noobprotect;
 
 import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
 
 
 public class NPListener
   implements Listener
 {
   NoobProtect plg;
   NPUtil u;
   
   public NPListener(NoobProtect plg)
   {
     this.plg = plg;
     this.u = plg.u;
   }
   
   @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
   public void onPlayerJoin(PlayerJoinEvent event) 
   {
     Player p = event.getPlayer();
     if (p.hasMetadata("NP-checktime")) p.removeMetadata("NP-checktime", this.plg);
     if ((!p.hasPlayedBefore()) && (this.plg.joinprotect)) 
    	 {
    	 	this.plg.players.addPlayer(p);
    	    this.plg.players.setPlayerName(p);
    	 }
   	     this.plg.players.updatePlayerPVP(p);
   	     this.plg.players.printPlayerProtected(p, false, true);
   }
   
   @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
   public void onEntityDamage(EntityDamageEvent event)
   {
     if ((event.getEntityType() != EntityType.PLAYER) || (!(event instanceof EntityDamageByEntityEvent)))
     {
  	   if (event.getEntityType() == EntityType.PLAYER)
  	   {
  		   if (this.plg.players.getPvpOff((Player)event.getEntity()))
  			 event.setCancelled(true);
  	   }
  	   else return;
     }
     EntityDamageByEntityEvent dmgev = (EntityDamageByEntityEvent)event;
     if (dmgev.getDamager().getType() != EntityType.PLAYER)
     {
    	 
     }
     else
     {
	     Player p1 = (Player)event.getEntity();
	     Player p2 = (Player)dmgev.getDamager();
	     
	     if ((this.plg.players.getPvpOff(p1)) || (this.plg.players.getPvpOff(p2))) {
	       event.setCancelled(true);
	       informFailedAttack(p2, p1);
     }
     }
   }
   

   
   
   @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
   public void onProjectileDamage(EntityDamageEvent event) {
     if (event.getEntityType() != EntityType.PLAYER) return;
     if (!(event instanceof EntityDamageByEntityEvent)) return;
     EntityDamageByEntityEvent evdm = (EntityDamageByEntityEvent)event;
     if (!(evdm.getDamager() instanceof Projectile)) return;
     Projectile prj = (Projectile)evdm.getDamager();
     if ((prj.getShooter() == null) || (!(prj.getShooter() instanceof Player))) return;
     Player p1 = (Player)event.getEntity();
     Player p2 = (Player)prj.getShooter();
     if ((this.plg.players.getPvpOff(p1)) || (this.plg.players.getPvpOff(p2))) {
       event.setCancelled(true);
       informFailedAttack(p2, p1);
     }
   }
   
   
   @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
   public void onEntityHurt(LivingAttackEvent event)
   {
	   
	   Bukkit.getServer().getLogger().info("EntityHurt");
	   
	   if (event.entity instanceof EntityPlayer)
	    {
		   Bukkit.getServer().getLogger().info("EntityHurtPlayer");
		     Player p1 = (Player) event.entity;
		     if (event.source.getSourceOfDamage() != null && event.source.getSourceOfDamage() instanceof EntityPlayer)
		     {
		    	 Player p2 = (Player)event.source;
		    	 if ((this.plg.players.getPvpOff(p1)) || (this.plg.players.getPvpOff(p2))) {
		    	       event.setCanceled(true);
		    	       informFailedAttack(p2, p1);
		    	     }
		     }
		     else return;
		     
	    }
   }
 
 
 
 
 
 
 
 
   public void informFailedAttack(Player atacker, Player defender)
   {
     if (this.plg.players.getPvpOff(atacker)) { this.u.printMSG(atacker, new Object[] { "msg_youcantattack", Character.valueOf('c'), Character.valueOf('6'), "/pvp-on" });
     } else {
       this.u.printMSG(defender, new Object[] { "msg_warndefender", atacker.getName() });
       this.u.printMSG(atacker, new Object[] { "msg_warnatacker", defender.getName() });
     }
   }
   
   @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
   public void onPlayerQuit(PlayerQuitEvent event) {
     Player p = event.getPlayer();
     if (p.hasMetadata("NP-checktime")) p.removeMetadata("NP-checktime", this.plg);
     this.plg.players.updatePlayTime(p);
     this.plg.players.savePlayerList();
   }
 }


