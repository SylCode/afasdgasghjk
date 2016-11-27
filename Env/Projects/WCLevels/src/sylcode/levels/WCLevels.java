 package sylcode.levels;
 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import listeners.CmdListener;
import listeners.WCListener;
import me.staartvin.statz.Statz;
import me.staartvin.statz.api.API;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import sylcode.levels.rules.ArrowRule;
import sylcode.levels.rules.BucketEmptyRule;
import sylcode.levels.rules.BucketFillRule;
import sylcode.levels.rules.CommandsRule;
import sylcode.levels.rules.CraftRule;
import sylcode.levels.rules.DamageTakeRule;
import sylcode.levels.rules.DeathRule;
import sylcode.levels.rules.EatRule;
import sylcode.levels.rules.EggThrowRule;
import sylcode.levels.rules.JoinRule;
import sylcode.levels.rules.KickRule;
import sylcode.levels.rules.LevelRules;
import sylcode.levels.rules.BreakRule;
import sylcode.levels.rules.KillRule;
import sylcode.levels.rules.PlaceRule;
import sylcode.levels.rules.PlayTimeRule;
import sylcode.levels.rules.PlayerKillRule;
import sylcode.levels.rules.SpecialRules;
import sylcode.levels.rules.ToolBreakRule;
import sylcode.levels.rules.TravelRule;
import sylcode.levels.rules.VillagerTradeRule;
import sylcode.levels.rules.XPRule;
import utils.WCUtil;
 

 public class WCLevels extends JavaPlugin
 {
	 private List<LevelRules> rules;
	 private List<SpecialRules> specialRules;
	 public WCUtil u;
	 public PlayerList players;
	 private WCListener l;
	 private Statz statz;
	 private API api;
	 private int notification_interval, level_check_interval;
     private BukkitScheduler scheduler;
	 
	 boolean language_save = true;
	 String language = "english";
   
   
   public void onEnable()
   {
     if (!getDataFolder().exists()) getDataFolder().mkdirs();
     //saveCfg();
     loadCfg();
     loadSpecialRules();
     scheduler  = getServer().getScheduler();
     notification_interval = getConfig().getInt("general.players_notification_interval") * 20;
     level_check_interval = getConfig().getInt("general.level_check_interval") * 20;
     this.statz = (Statz)Bukkit.getServer().getPluginManager().getPlugin("Statz");
     
	 if (statz != null) {
		 api = statz.getStatzAPI();
	 }
	 
     this.u = new WCUtil(this, false, this.language_save, this.language, "wclevels", "WCLevels", "wclevels", "&3[WCL]&f ");
     this.players = new PlayerList(this);
     //this.sharePoints = new SharePoints(this);
     
     this.l = new WCListener(this);
     getCommand("wcl").setExecutor(new CmdListener(this));
     getCommand("completelvl").setExecutor(new CmdListener(this));
     PluginManager pm = getServer().getPluginManager();
     pm.registerEvents(this.l, this);
     try
     {
       MetricsLite metrics = new MetricsLite(this);
       metrics.start();
     }
     catch (IOException localIOException) {}
     
     scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
         @Override
         public void run() {
          players.checkPlayerLevels();
          players.checkPlayerSpecialRules();
       }
     }, level_check_interval, level_check_interval);
     
     scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
         @Override
         public void run() {
          players.informPlayers();
          players.savePlayerList();
       }
     }, notification_interval, notification_interval);
   }
   
   public BukkitScheduler getSheduler()
   {
	   return this.scheduler;
   }
   
   public Statz getStatz()
   {
	   return statz;
   }
   
   public List<LevelRules> getLevelRules()
   {
	   return this.rules;
   }
   
   public List<SpecialRules> getSpecialRules()
   {
	   return this.specialRules;
   }
   
   public API getStatzAPI()
   {
	   if(api == null)
	   {
		   api = statz.getStatzAPI();
	   }
	   return api;
   }
   
   public LevelRules getNewRule(int Level)
   {
	   if (Level<rules.size())
		   return (rules.get(Level));
	   else return rules.get(Level-1);
   }
   
   public void onDisable()
   {
     this.players.savePlayerList();
   }
   
   public void loadCfg() 
   {
	   this.rules = new ArrayList<LevelRules>();
	   int nr = getConfig().getInt("general.number_of_levels");
	   for(int i=0; i<nr; i++)
	   {
		   	List<ArrowRule> arrowRules = null;
			List<BreakRule> breakRules = null;
			BucketEmptyRule bucketEmptyRule = null;
			BucketFillRule bucketFillRule = null;
			List<CommandsRule> commandsRules = null;
			List<CraftRule> craftRules = null;
			List<DamageTakeRule> damageTakeRules = null;
			DeathRule deathRule = null;
			List<EatRule> eatRules = null;
			EggThrowRule eggThrownRule = null;
			JoinRule joinRule = null;
			KickRule kickRule = null;
			List<KillRule> killRules = null;
			List<PlayerKillRule> playerKillRules = null;
			List<PlaceRule> placeRules = null;
			PlayTimeRule playTimeRule = null;
			List<ToolBreakRule> toolBreakRules = null;
			List<TravelRule> travelRules = null;
			List<VillagerTradeRule> villagerTradeRules = null;
			XPRule xpRule = null;
		   
			String temp;
			String[] data;
		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".Kill");
			   if (!temp.isEmpty())
			   {
				   killRules = new ArrayList<KillRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   killRules.add(new KillRule(obj[0], Integer.parseInt(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule Kill"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};
		   
		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".KillPlayer");
			   if (!temp.isEmpty())
			   {
				   playerKillRules = new ArrayList<PlayerKillRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   playerKillRules.add(new PlayerKillRule(obj[0], Integer.parseInt(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule Kill"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};
		   
		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".Break");
			   if (!temp.isEmpty())
			   {
				   breakRules = new ArrayList<BreakRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="" && obj[2]!="")
							   breakRules.add(new BreakRule(Integer.parseInt(obj[0]),Integer.parseInt(obj[1]), Integer.parseInt(obj[2])));
						   else Bukkit.getLogger().info("Empty string occured during the rule Break"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};
		   
		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".Place");
			   if (!temp.isEmpty())
			   {
				   placeRules = new ArrayList<PlaceRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="" && obj[2]!="")
							   placeRules.add(new PlaceRule(Integer.parseInt(obj[0]),Integer.parseInt(obj[1]), Integer.parseInt(obj[2])));
						   else Bukkit.getLogger().info("Empty string occured during the rule Place"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".Craft");
			   if (!temp.isEmpty())
			   {
				   craftRules = new ArrayList<CraftRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   craftRules.add(new CraftRule(obj[0],Integer.parseInt(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule Break"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".ArrowShoot");
			   if (!temp.isEmpty())
			   {
				   arrowRules = new ArrayList<ArrowRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   arrowRules.add(new ArrowRule(Integer.parseInt(obj[0]), Double.parseDouble(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule ArrowShoot"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".EmptyBuckets");

			   if (!temp.isEmpty())
			   {
				   bucketEmptyRule = new BucketEmptyRule(Integer.parseInt(temp));
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".FillBuckets");

			   if (!temp.isEmpty())
			   {
				   bucketFillRule = new BucketFillRule(Integer.parseInt(temp));
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".ExecuteCommands");
			   if (!temp.isEmpty())
			   {
				   commandsRules = new ArrayList<CommandsRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   commandsRules.add(new CommandsRule(obj[0], obj[1], Integer.parseInt(obj[2])));
						   else Bukkit.getLogger().info("Empty string occured during the rule ExecuteCommands"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".TakeDamage");
			   if (!temp.isEmpty())
			   {
				   damageTakeRules = new ArrayList<DamageTakeRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   damageTakeRules.add(new DamageTakeRule(obj[0], Integer.parseInt(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule TakeDamage"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".Die");
			   if (!temp.isEmpty())
			   {
				   deathRule = new DeathRule(Integer.parseInt(temp));
			   }
			   else Bukkit.getLogger().info("Empty string occured during the rule Die"+ Integer.toString(i+1) + " in data: Die");
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".Eat");
			   if (!temp.isEmpty())
			   {
				   eatRules = new ArrayList<EatRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   eatRules.add(new EatRule(obj[0], Integer.parseInt(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule Eat" + Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".ThrowEggs");
			   if (!temp.isEmpty())
			   {
				   eggThrownRule = new EggThrowRule(Integer.parseInt(temp));
			   }
			   else Bukkit.getLogger().info("Empty string occured during the rule ThrowEggs"+ Integer.toString(i+1) + " in data: EggsThrow");
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".JoinServer");
			   if (!temp.isEmpty())
			   {
				   joinRule = new JoinRule(Integer.parseInt(temp));
			   }
			   else Bukkit.getLogger().info("Empty string occured during the rule JoinServer"+ Integer.toString(i+1) + " in data: Joins");
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".GetKicked");
			   if (!temp.isEmpty())
			   {
				   kickRule = new KickRule(Integer.parseInt(temp));
			   }
			   else Bukkit.getLogger().info("Empty string occured during the rule GetKicked"+ Integer.toString(i+1) + " in data: Kicked");
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".PlayTime");
			   if (!temp.isEmpty())
			   {
				   playTimeRule = new PlayTimeRule(Integer.parseInt(temp));
			   }
			   else Bukkit.getLogger().info("Empty string occured during the rule"+ Integer.toString(i+1) + " in data: PlayTime");
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".BreakTools");
			   if (!temp.isEmpty())
			   {
				   toolBreakRules = new ArrayList<ToolBreakRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   toolBreakRules.add(new ToolBreakRule(obj[0], Integer.parseInt(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule BreakTools"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".Travel");
			   if (!temp.isEmpty())
			   {
				   travelRules = new ArrayList<TravelRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   travelRules.add(new TravelRule(Integer.parseInt(obj[0]), obj[1]));
						   else Bukkit.getLogger().info("Empty string occured during the rule Travel"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".TradeVillager");
			   if (!temp.isEmpty())
			   {
				   villagerTradeRules = new ArrayList<VillagerTradeRule>();
				   data = temp.split(";");
				   for(int j=0; j<data.length; j++)
				   {
					   String[] obj = data[j].split(" ");
					   if(obj.length>=2)
						   if (obj[0]!=""&&obj[1]!="")
							   villagerTradeRules.add(new VillagerTradeRule(obj[0], Integer.parseInt(obj[1])));
						   else Bukkit.getLogger().info("Empty string occured during the rule TradeVillager"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
				   }
			   }
		   }
		   catch(Exception ex){};

		   try
		   {
			   temp = getConfig().getString("Level"+Integer.toString(i+1)+".GainXP");
			   if (!temp.isEmpty())
			   {
				   xpRule = new XPRule(Integer.parseInt(temp));
			   }
			   else Bukkit.getLogger().info("Empty string occured during the rule"+ Integer.toString(i+1) + " in data: PlayTime");
		   }
		   catch(Exception ex){};
		   
		   int earn=0;
		   try
		   {
			   earn = getConfig().getInt("Level"+Integer.toString(i+1)+".Earn");
		   }
		   catch(Exception ex){};
		   
		   String[] awards = null;
		   try
		   {
			   String award = getConfig().getString("Level"+Integer.toString(i+1)+".Award");
			   awards = award.split(";");
		   }
		   catch(Exception ex){}
		   
		   rules.add(new LevelRules(i+1,arrowRules, breakRules, bucketEmptyRule,bucketFillRule,commandsRules, craftRules,damageTakeRules, deathRule, eatRules, eggThrownRule, joinRule, kickRule, killRules, playerKillRules, placeRules, playTimeRule,toolBreakRules,travelRules,villagerTradeRules,xpRule, earn,awards));
		   
	   }
	   
   }

   public void loadSpecialRules()
   {

       File f = new File(this.getDataFolder() + File.separator + "SpecialAwardsConfig.yml");
       if (f.exists()) 
       {
         YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
         
         this.specialRules = new ArrayList<SpecialRules>();
	  	   int nr = cfg.getInt("general.number_of_rules");
	  	   for(int i=0; i<nr; i++)
	  	   {
	  		   	List<ArrowRule> arrowRules = null;
	  			List<BreakRule> breakRules = null;
	  			BucketEmptyRule bucketEmptyRule = null;
	  			BucketFillRule bucketFillRule = null;
	  			List<CommandsRule> commandsRules = null;
	  			List<CraftRule> craftRules = null;
	  			List<DamageTakeRule> damageTakeRules = null;
	  			DeathRule deathRule = null;
	  			List<EatRule> eatRules = null;
	  			EggThrowRule eggThrownRule = null;
	  			JoinRule joinRule = null;
	  			KickRule kickRule = null;
	  			List<KillRule> killRules = null;
				List<PlayerKillRule> playerKillRules = null;
	  			List<PlaceRule> placeRules = null;
	  			PlayTimeRule playTimeRule = null;
	  			List<ToolBreakRule> toolBreakRules = null;
	  			List<TravelRule> travelRules = null;
	  			List<VillagerTradeRule> villagerTradeRules = null;
	  			XPRule xpRule = null;
	  		   
	  			String temp;
	  			String[] data;
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".Kill");
	  			   if (!temp.isEmpty())
	  			   {
	  				   killRules = new ArrayList<KillRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   killRules.add(new KillRule(obj[0], Integer.parseInt(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule Kill"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	  		   
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".KillPlayer");
	  			   if (!temp.isEmpty())
	  			   {
	  				   playerKillRules = new ArrayList<PlayerKillRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							 playerKillRules.add(new PlayerKillRule(obj[0], Integer.parseInt(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule Kill"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	  		   
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".Break");
	  			   if (!temp.isEmpty())
	  			   {
	  				   breakRules = new ArrayList<BreakRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="" && obj[2]!="")
	  							   breakRules.add(new BreakRule(Integer.parseInt(obj[0]),Integer.parseInt(obj[1]), Integer.parseInt(obj[2])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule Break"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	  		   
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".Place");
	  			   if (!temp.isEmpty())
	  			   {
	  				   placeRules = new ArrayList<PlaceRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="" && obj[2]!="")
	  							   placeRules.add(new PlaceRule(Integer.parseInt(obj[0]),Integer.parseInt(obj[1]), Integer.parseInt(obj[2])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule Place"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".Craft");
	  			   if (!temp.isEmpty())
	  			   {
	  				   craftRules = new ArrayList<CraftRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   craftRules.add(new CraftRule(obj[0],Integer.parseInt(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule Break"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".ArrowShoot");
	  			   if (!temp.isEmpty())
	  			   {
	  				   arrowRules = new ArrayList<ArrowRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   arrowRules.add(new ArrowRule(Integer.parseInt(obj[0]), Double.parseDouble(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule ArrowShoot"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".EmptyBuckets");
	
	  			   if (!temp.isEmpty())
	  			   {
	  				   bucketEmptyRule = new BucketEmptyRule(Integer.parseInt(temp));
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".FillBuckets");
	
	  			   if (!temp.isEmpty())
	  			   {
	  				   bucketFillRule = new BucketFillRule(Integer.parseInt(temp));
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".ExecuteCommands");
	  			   if (!temp.isEmpty())
	  			   {
	  				   commandsRules = new ArrayList<CommandsRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   commandsRules.add(new CommandsRule(obj[0], obj[1], Integer.parseInt(obj[2])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule ExecuteCommands"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".TakeDamage");
	  			   if (!temp.isEmpty())
	  			   {
	  				   damageTakeRules = new ArrayList<DamageTakeRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   damageTakeRules.add(new DamageTakeRule(obj[0], Integer.parseInt(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule TakeDamage"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".Die");
	  			   if (!temp.isEmpty())
	  			   {
	  				   deathRule = new DeathRule(Integer.parseInt(temp));
	  			   }
	  			   else Bukkit.getLogger().info("Empty string occured during the rule Die"+ Integer.toString(i+1) + " in data: Die");
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".Eat");
	  			   if (!temp.isEmpty())
	  			   {
	  				   eatRules = new ArrayList<EatRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   eatRules.add(new EatRule(obj[0], Integer.parseInt(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule Eat" + Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".ThrowEggs");
	  			   if (!temp.isEmpty())
	  			   {
	  				   eggThrownRule = new EggThrowRule(Integer.parseInt(temp));
	  			   }
	  			   else Bukkit.getLogger().info("Empty string occured during the rule ThrowEggs"+ Integer.toString(i+1) + " in data: EggsThrow");
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".JoinServer");
	  			   if (!temp.isEmpty())
	  			   {
	  				   joinRule = new JoinRule(Integer.parseInt(temp));
	  			   }
	  			   else Bukkit.getLogger().info("Empty string occured during the rule JoinServer"+ Integer.toString(i+1) + " in data: Joins");
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".GetKicked");
	  			   if (!temp.isEmpty())
	  			   {
	  				   kickRule = new KickRule(Integer.parseInt(temp));
	  			   }
	  			   else Bukkit.getLogger().info("Empty string occured during the rule GetKicked"+ Integer.toString(i+1) + " in data: Kicked");
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".PlayTime");
	  			   if (!temp.isEmpty())
	  			   {
	  				   playTimeRule = new PlayTimeRule(Integer.parseInt(temp));
	  			   }
	  			   else Bukkit.getLogger().info("Empty string occured during the rule"+ Integer.toString(i+1) + " in data: PlayTime");
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".BreakTools");
	  			   if (!temp.isEmpty())
	  			   {
	  				   toolBreakRules = new ArrayList<ToolBreakRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   toolBreakRules.add(new ToolBreakRule(obj[0], Integer.parseInt(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule BreakTools"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".Travel");
	  			   if (!temp.isEmpty())
	  			   {
	  				   travelRules = new ArrayList<TravelRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   travelRules.add(new TravelRule(Integer.parseInt(obj[0]), obj[1]));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule Travel"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".TradeVillager");
	  			   if (!temp.isEmpty())
	  			   {
	  				   villagerTradeRules = new ArrayList<VillagerTradeRule>();
	  				   data = temp.split(";");
	  				   for(int j=0; j<data.length; j++)
	  				   {
	  					   String[] obj = data[j].split(" ");
	  					   if(obj.length>=2)
	  						   if (obj[0]!=""&&obj[1]!="")
	  							   villagerTradeRules.add(new VillagerTradeRule(obj[0], Integer.parseInt(obj[1])));
	  						   else Bukkit.getLogger().info("Empty string occured during the rule TradeVillager"+ Integer.toString(i+1) + " in data: " + Integer.toString(j));
	  				   }
	  			   }
	  		   }
	  		   catch(Exception ex){};
	
	  		   try
	  		   {
	  			   temp = cfg.getString("Rule"+Integer.toString(i+1)+".GainXP");
	  			   if (!temp.isEmpty())
	  			   {
	  				   xpRule = new XPRule(Integer.parseInt(temp));
	  			   }
	  			   else Bukkit.getLogger().info("Empty string occured during the rule"+ Integer.toString(i+1) + " in data: PlayTime");
	  		   }
	  		   catch(Exception ex){};
	  		   
	  		   int earn=0;
	  		   try
	  		   {
	  			   earn = cfg.getInt("Rule"+Integer.toString(i+1)+".Earn");
	  		   }
	  		   catch(Exception ex){};
	  		   
	  		   String[] awards = null;
	  		   try
	  		   {
	  			   String award = cfg.getString("Rule"+Integer.toString(i+1)+".Award");
	  			   awards = award.split(";");
	  		   }
	  		   catch(Exception ex){}
	  		   
	  		   String info = "";
	  		   try
	  		   {
	  			   info = cfg.getString("Rule"+Integer.toString(i+1)+".Info");
	  		   }
	  		   catch(Exception ex){};
	  		   
	  		   specialRules.add(new SpecialRules(i+1,arrowRules, breakRules, bucketEmptyRule,bucketFillRule,commandsRules, craftRules,damageTakeRules, deathRule, eatRules, eggThrownRule, joinRule, kickRule, killRules, playerKillRules, placeRules, playTimeRule,toolBreakRules,travelRules,villagerTradeRules,xpRule, earn,awards,info));
	  		   
	  	   }
         
       }
   }
 }


