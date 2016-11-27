 package sylcode.noobprotect;
 
 import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
 

 public class NoobProtect extends JavaPlugin
 {
   boolean joinprotect = true;
   boolean userealtime = true;
   boolean useplaytime = true;
   int prttime = 2880;
   int prtplay = 300;
   int pvponcooldown = 10;
   int pvpupdatetime = 5;
   boolean playerwarn = true;
   int playerwarntime = 30;
   String timezone = "";
   boolean version_check = false;
   boolean language_save = true;
   String language = "english";
   List<String> no_pvp_worlds = new ArrayList<String>();
   List<String> no_pvp_regions = new ArrayList<String>();
   
   NPUtil u;
   
   NPCmd cmd;
   
   NPPList players;
   NPListener l;
   WorldGuardPlugin worldguard;
   boolean wg_active = false;
   
   public void onEnable()
   {
     if (!getDataFolder().exists()) getDataFolder().mkdirs();
     loadCfg();
     saveCfg();
     this.wg_active = connectWorldGuard();
     
     this.u = new NPUtil(this, this.version_check, this.language_save, this.language, "noob-protector", "NoobProtector", "noob", "&3[NP]&f ");
     this.players = new NPPList(this);
     
     this.cmd = new NPCmd(this);
     getCommand("noob").setExecutor(this.cmd);
     getCommand("pvp-on").setExecutor(this.cmd);
     
     this.l = new NPListener(this);
     PluginManager pm = getServer().getPluginManager();
     pm.registerEvents(this.l, this);
     try
     {
       MetricsLite metrics = new MetricsLite(this);
       metrics.start();
     }
     catch (IOException localIOException) {}
   }
   
 
   public void onDisable()
   {
     this.players.savePlayerList();
   }
   
   public void saveCfg()
   {
     getConfig().set("general.version-check", Boolean.valueOf(this.version_check));
     getConfig().set("general.language", this.language);
     getConfig().set("general.language-save", Boolean.valueOf(this.language_save));
     getConfig().set("general.time-zone", this.timezone);
     getConfig().set("protect.after-join", Boolean.valueOf(this.joinprotect));
     getConfig().set("protect.realtime.enable", Boolean.valueOf(this.userealtime));
     getConfig().set("protect.realtime.time", Integer.valueOf(this.prttime));
     getConfig().set("protect.playtime.enable", Boolean.valueOf(this.useplaytime));
     getConfig().set("protect.playtime.time", Integer.valueOf(this.prtplay));
     getConfig().set("general.pvp-on-cool-down", Integer.valueOf(this.pvponcooldown));
     getConfig().set("schedule.pvp-update-time", Integer.valueOf(this.pvpupdatetime));
     getConfig().set("schedule.player-warn.enable", Boolean.valueOf(this.playerwarn));
     getConfig().set("schedule.player-warn.time", Integer.valueOf(this.playerwarntime));
     getConfig().set("unprotected.worlds", this.no_pvp_worlds);
     getConfig().set("unprotected.regions", this.no_pvp_regions);
     saveConfig();
   }
   
   public void loadCfg() {
     this.joinprotect = getConfig().getBoolean("protect.after-join", true);
     this.userealtime = getConfig().getBoolean("protect.realtime.enable", true);
     this.prttime = getConfig().getInt("protect.realtime.time", 2880);
     this.useplaytime = getConfig().getBoolean("protect.playtime.enable", true);
     this.prtplay = getConfig().getInt("protect.playtime.time", 300);
     if ((!this.userealtime) && (!this.useplaytime)) this.useplaytime = true;
     this.timezone = getConfig().getString("general.time-zone", "");
     this.version_check = getConfig().getBoolean("general.version-check", true);
     this.language_save = getConfig().getBoolean("general.language-save", false);
     this.language = getConfig().getString("general.language", "english");
     this.pvponcooldown = getConfig().getInt("general.pvp-on-cool-down", this.pvponcooldown);
     this.pvpupdatetime = getConfig().getInt("schedule.pvp-update-time", this.pvpupdatetime);
     this.playerwarn = getConfig().getBoolean("schedule.player-warn.enable", this.playerwarn);
     this.playerwarntime = getConfig().getInt("schedule.player-warn.time", this.playerwarntime);
     this.no_pvp_worlds = getConfig().getStringList("unprotected.worlds");
     this.no_pvp_regions = getConfig().getStringList("unprotected.regions");
   }
   
   public boolean connectWorldGuard() {
     Plugin worldGuard = getServer().getPluginManager().getPlugin("WorldGuard");
     if ((worldGuard != null) && ((worldGuard instanceof WorldGuardPlugin))) {
       this.worldguard = ((WorldGuardPlugin)worldGuard);
       return true;
     }
     return false;
   }
 }


