 package sylcode.noobprotect;
 
 import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

 import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitTask;
 

 
 public class NPPList
 {
   NoobProtect plg;
   Long prtreal = Long.valueOf(3600000L);
   Long prtplay = Long.valueOf(5L);
   
   NPUtil u;
   
   BukkitTask tid;
   
   BukkitTask tid2;
   boolean userealtime = true;
   boolean useplaytime = true;
   
   public NPPList(NoobProtect plg) {
     this.plg = plg;
     this.u = plg.u;
     this.prtreal = Long.valueOf(plg.prttime * 60000L);
     this.prtplay = Long.valueOf(plg.prtplay * 60000L);
     this.useplaytime = plg.useplaytime;
     this.userealtime = plg.userealtime;
     
     loadPlayerList();
     
     this.tid = Bukkit.getScheduler().runTaskTimerAsynchronously(this.plg, new Runnable() {
       public void run() {
         NPPList.this.updateOnlinePlayersPVP();
 
       }
       
 
 
     }, 200L, plg.pvpupdatetime * 20L);
     
 
     if (plg.playerwarn) {
       this.tid2 = Bukkit.getScheduler().runTaskTimerAsynchronously(this.plg, new Runnable() {
         public void run() {
           NPPList.this.warnPlayers();
         }
       }, plg.playerwarntime * 1200L, plg.playerwarntime * 1200L);
     }
   }
   
   public class NPPlayer
   {
     Long rtimelimit;
     Long playtimeleft;
     
     public NPPlayer()
     {
       this.rtimelimit = Long.valueOf(System.currentTimeMillis() + NPPList.this.prtreal.longValue());
       this.playtimeleft = NPPList.this.prtplay;
     }
     
     public NPPlayer(Long rtimelimit, Long playtimeleft)
     {
       this.rtimelimit = rtimelimit;
       this.playtimeleft = playtimeleft;
     }
     
     public boolean updatePlayTime(Long pt) {
       this.playtimeleft = Long.valueOf(Math.max(this.playtimeleft.longValue() - pt.longValue(), 0L));
       return this.playtimeleft.longValue() > 0L;
     }
   }
   
 
 
   private HashMap<String, NPPlayer> players = new HashMap<String, NPPlayer>();
   
public void warnPlayers() 
{
	Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
	for(Player p:arrayOfPlayer)
	{
		printPlayerProtected(p, false, true);
	}
}
   
 
 
   public boolean updatePlayTime(Player p)
   {
     if (!this.players.containsKey(p.getName())) return false;
     Long ct = Long.valueOf(System.currentTimeMillis());
     Long pt = ct;
     if (p.hasMetadata("NP-checktime")) pt = Long.valueOf(((MetadataValue)p.getMetadata("NP-checktime").get(0)).asLong());
     p.setMetadata("NP-checktime", new FixedMetadataValue(this.plg, ct));
     Long playtime = Long.valueOf(ct.longValue() - pt.longValue());
     return ((NPPlayer)this.players.get(p.getName())).updatePlayTime(playtime);
   }
   
   public void addPlayer(Player p)
   {
     if (!this.players.containsKey(p.getName())) this.players.put(p.getName(), new NPPlayer());
     savePlayerList();
   }
   
   public void setPlayer(Player p) 
   {
     this.players.put(p.getName(), new NPPlayer());
     savePlayerList();
   }
   
   public void setPlayer(String pname) {
     this.players.put(pname, new NPPlayer());
     savePlayerList();
   }
   
   public boolean unprotectPlayer(Player p) 
   {
     if (this.players.containsKey(p.getName())) 
     {
       this.players.remove(p.getName());
       savePlayerList();
       return true;
     }
     return false;
   }
   
   public boolean unprotectPlayer(String pname) 
   {
     if (this.players.containsKey(pname)) 
     {
       this.players.remove(pname);
       restorePlayerName(pname);
       savePlayerList();
       return true;
     }
     return false;
   }
   
public void updateOnlinePlayersPVP() 
{
	Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
	for(Player p:arrayOfPlayer)
	{
		if (updatePlayerPVP(p))
			this.u.printMSG(p, new Object[] { "msg_warnpvpon", Character.valueOf('6') });
		}
}
   
   public boolean updatePlayerPVP(Player p) {
     if ((this.players.containsKey(p.getName())) && (
       ((this.userealtime) && (((NPPlayer)this.players.get(p.getName())).rtimelimit.longValue() < System.currentTimeMillis())) || (
       (this.useplaytime) && (!updatePlayTime(p))))) 
     {
       this.players.remove(p.getName());
       restorePlayerName(p);
       return true;
     }
     
     return false;
   }
   
   public boolean setPlayerName (Player p)
   {
	   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	   String command = "manuaddv "+p.getName()+" suffix"+" &a (N)";
	   Bukkit.dispatchCommand(console, command);
	    return true;
   }
   public boolean restorePlayerName (Player p)
   {
       ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
       String command = "manudelv "+p.getName()+" suffix";
	   Bukkit.dispatchCommand(console, command);;
	    return true;
   }
   

   public boolean restorePlayerName (String p)
   {
       ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
       String command = "manudelv "+p+" suffix";
	   Bukkit.dispatchCommand(console, command);;
	    return true;
   }
   
   public boolean getPvpOff(Player p)
   {
     if (isPlayerInUnprotectedWorld(p)) return false;
     if (isPlayerInUnprotectedRegion(p)) return false;
     return (this.players.containsKey(p.getName())) && (
       ((this.userealtime) && (((NPPlayer)this.players.get(p.getName())).rtimelimit.longValue() > System.currentTimeMillis())) || (
       (this.useplaytime) && (updatePlayTime(p))));
   }
   
   public boolean isPlayerInUnprotectedWorld(Player p)
   {
     if (this.plg.no_pvp_worlds.isEmpty()) return false;
     return this.plg.no_pvp_worlds.contains(p.getWorld().getName());
   }
   
   public boolean isPlayerInUnprotectedRegion(Player p) {
     return isLocationInUnprotectedRegion(p.getLocation());
   }
   

public boolean isLocationInUnprotectedRegion(Location loc) 
{
	if (!this.plg.wg_active) return false;
	if (this.plg.no_pvp_regions.isEmpty()) return false;
	RegionContainer container = plg.worldguard.getRegionContainer();
	RegionQuery query = container.createQuery();
	ApplicableRegionSet regions = query.getApplicableRegions(loc);
	for (ProtectedRegion region : regions)
	{
		if (this.plg.no_pvp_regions.contains(region.getId())) return true;
		}
	return false;
}

   public void savePlayerList()
   {
     try {
       File f = new File(this.plg.getDataFolder() + File.separator + "players.yml");
       if (f.exists()) f.delete();
       if (this.players.size() > 0) {
         f.createNewFile();
         YamlConfiguration cfg = new YamlConfiguration();
         for (String name : this.players.keySet()) {
           if (this.useplaytime) cfg.set(name + ".playtime", ((NPPlayer)this.players.get(name)).playtimeleft);
           if (this.userealtime) { cfg.set(name + ".realtime", ((NPPlayer)this.players.get(name)).rtimelimit);
           }
         }
         cfg.save(f);
       }
     }
     catch (Exception localException) {}
   }
   
 
   public void loadPlayerList()
   {
     try
     {
       File f = new File(this.plg.getDataFolder() + File.separator + "players.yml");
       if (f.exists()) {
         YamlConfiguration cfg = new YamlConfiguration();
         cfg.load(f);
         for (String name : cfg.getKeys(false)) {
           this.players.put(name, new NPPlayer(Long.valueOf(cfg.getLong(name + ".realtime", 0L)), Long.valueOf(cfg.getLong(name + ".playtime", 0L))));
         }
       }
     }
     catch (Exception localException) {}
   }
   
   public String getProtectTime(Player p) {
     return getProtectTime(p.getName());
   }
   
   public String getProtectTime(String pname) {
     if (this.players.containsKey(pname)) {
       Date d = new Date(((NPPlayer)this.players.get(pname)).rtimelimit.longValue());
       SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       
       if (!this.plg.timezone.isEmpty()) f.setTimeZone(TimeZone.getTimeZone(this.plg.timezone));
       return f.format(d);
     }
     return "";
   }
   
   public String getPlayTimeLeft(Player p) {
     return getPlayTimeLeft(p.getName());
   }
   
   public String getPlayTimeLeft(String pname) {
     if (this.players.containsKey(pname)) {
       Long time = Long.valueOf(((NPPlayer)this.players.get(pname)).playtimeleft.longValue() / 1000L);
       int seconds = (int)(time.longValue() % 60L);
       int minutes = (int)(time.longValue() % 3600L / 60L);
       int hours = (int)(time.longValue() / 3600L);
       return String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds) });
     }
     return "";
   }
   
   public void printPlayerProtected(Player p, boolean prtempty, boolean pvpon) {
     if (this.players.containsKey(p.getName())) {
       String msg = this.u.getMSG(new Object[] { "msg_protected", Character.valueOf('b') });
       if (this.useplaytime) msg = msg + " " + this.u.getMSG(new Object[] { "msg_playtime", Character.valueOf('b'), Character.valueOf('e'), getPlayTimeLeft(p) });
       if (this.userealtime) msg = msg + " " + this.u.getMSG(new Object[] { "msg_realtime", Character.valueOf('b'), Character.valueOf('e'), getProtectTime(p) });
       this.u.printMsg(p, msg);
       if (pvpon) this.u.printMSG(p, new Object[] { "msg_typepvpon", Character.valueOf('e'), Character.valueOf('6'), "/pvp-on" });
     } else if (prtempty) { this.u.printMSG(p, new Object[] { "msg_notprotected" });
     }
   }
   
   public void printTargetPlayerProtected(CommandSender p, Player tp) { if (this.players.containsKey(tp.getName())) {
       String msg = this.u.getMSG(new Object[] { "msg_plrisunprotected", Character.valueOf('b'), Character.valueOf('e'), tp.getName() });
       if (this.useplaytime) msg = msg + " " + this.u.getMSG(new Object[] { "msg_playtime", Character.valueOf('b'), Character.valueOf('e'), getPlayTimeLeft(tp) });
       if (this.userealtime) msg = msg + " " + this.u.getMSG(new Object[] { "msg_realtime", Character.valueOf('b'), Character.valueOf('e'), getProtectTime(tp) });
       this.u.printMsg(p, msg);
     } else { this.u.printMSG(p, new Object[] { "msg_plrisunprotected", tp.getName() });
     }
   }
   
   public void printList(CommandSender p, int pnum, String mask)
   {
     if (this.players.size() > 0) {
       List<String> ln = new ArrayList<String>();
       String name; String pt; label184: for (Iterator<String> localIterator = this.players.keySet().iterator(); localIterator.hasNext(); 
           
 
 
           ln.add("&2" + name + " &a" + pt))
       {
         name = (String)localIterator.next();
         pt = "";
         if (this.userealtime) pt = pt + " : " + getProtectTime(name);
         if (this.useplaytime) pt = pt + " : " + getPlayTimeLeft(name);
         if ((!mask.isEmpty()) && (!name.contains(mask))) break label184;
       }
       if (ln.size() > 0) this.u.printPage(p, ln, pnum, "msg_plisttitle", "msg_plistfooter", true); else
         this.u.printMSG(p, new Object[] { "msg_emptylist", Character.valueOf('6') });
     } else { this.u.printMSG(p, new Object[] { "msg_emptylist", Character.valueOf('6') });
     }
   }
 }


