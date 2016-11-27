package sylcode.levels;
 
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.staartvin.statz.api.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitTask;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

import sylcode.levels.rules.LevelRules;
import sylcode.levels.rules.SpecialRules;
import tasks.CheckPlayerInventoryTask;
import utils.WCUtil;
 

 
 public class PlayerList
 {
   WCLevels plg;
   private HashMap<String, WCPlayer> players = new HashMap<String, WCPlayer>();
   
   WCUtil u;
   API api;
   
   BukkitTask tid;
   
   BukkitTask tid2;
   boolean userealtime = true;
   boolean useplaytime = true;
   
   public PlayerList(WCLevels plg) 
   {
	   if (players.size()==0)
	   {
	     this.plg = plg;
	     this.u = plg.u;
	     this.api = plg.getStatzAPI();
	
		 Bukkit.getLogger().info("Loading Player List");
	     loadPlayerList();
	   }
   }
   
   public class WCPlayer
   {
	   String name;
	   String uuid;
	   int level;
	   int earned;
	   LevelRules aim;
	   List<SpecialRules> specialRules;
	   List<Integer> completedSpecialRules;
   
     public WCPlayer(String name , String uuid, int level,LevelRules aim, List<SpecialRules> specialRules, List<Integer> completedSpecialRules)
     {
    	 this.name = name;
    	 this.level = level;
    	 this.uuid = uuid;
    	 this.aim = aim;
    	 this.specialRules = specialRules;
    	 this.earned=0;
    	 this.completedSpecialRules = completedSpecialRules;
     }
     
     
     public int getLevel()
     {
    	 return this.level;
     }
     
     public boolean setPlayerName (int level)
     {
  	   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
  	   String command = "manuaddv "+this.name+" suffix"+" &a [" + level + "]";
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
     
     @SuppressWarnings("deprecation")
	public void AwardPlayer()
 	{
    	 String[] commands = this.aim.getCommand();
    	 List<String> giveCommands = new ArrayList<String>();
     	 for(int i=0; i<commands.length; i++)
     	 {
     		 if(commands[i].contains("give"))
     		 {
     			 giveCommands.add(commands[i].replaceAll("#p", this.name));
     		 }
     		 else
     		 {
	    		 Bukkit.getLogger().info("[WCLevels] Awarding player with : " + commands[i].replaceAll("#p", this.name));
	    		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands[i].replaceAll("#p", this.name));
	    		 spawnFireworks(Bukkit.getPlayer(this.name));
     		 }
     	 }
     	 if (giveCommands.size()!=0)
     	 {
     		 BukkitTask task = new CheckPlayerInventoryTask(this.name, giveCommands).runTaskTimer(plg,0, 40);
     	 }
 	}
     
     @SuppressWarnings("deprecation")
	public void SpecialAwardPlayer(int awardNumber)
  	{
     	 String[] commands = this.specialRules.get(awardNumber).getCommand();
     	 String info = this.specialRules.get(awardNumber).getInfo().replaceAll("#p", this.name);
     	 GlobalMessage(info);
     	 List<String> giveCommands = new ArrayList<String>();
     	 for(int i=0; i<commands.length; i++)
     	 {
     		 if(commands[i].contains("give"))
     		 {
     			 giveCommands.add(commands[i].replaceAll("#p", this.name));
     		 }
     		 else
     		 {
         		 generateSpecialEffects(Bukkit.getPlayer(this.name));
         		 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands[i].replaceAll("#p", this.name));
     		 }
     	 }
     	 if (giveCommands.size()!=0)
     	 {
     		 BukkitTask task = new CheckPlayerInventoryTask(this.name, giveCommands).runTaskTimer(plg,0, 40);
     	 }
  	}
     
     public void generateSpecialEffects(Player p)
     {
    	 Location loc = p.getLocation();
    	 World w = p.getWorld();
    	 
    	 w.setTime(18000);
 	    w.setThundering(true);
 	    w.setThunderDuration(10000);
 	    
 	    List<Location> locs = new ArrayList<Location>();
 	    for (int i=1; i<=2; i++)
 	    {
	 	    locs.add(new Location(w,loc.getX()+i*2,loc.getY(),loc.getZ()));
	
	 	    locs.add(new Location(w,loc.getX()-i*2,loc.getY(),loc.getZ()));
	
	 	    locs.add(new Location(w,loc.getX(),loc.getY(),loc.getZ()+i*2));
	
	 	    locs.add(new Location(w,loc.getX(),loc.getY(),loc.getZ()-i*2));
	
	 	    locs.add(new Location(w,loc.getX(),loc.getY()+i*2,loc.getZ()));
	
	 	    locs.add(new Location(w,loc.getX(),loc.getY()-i*2,loc.getZ()));
 	    }
 	    
 	    for(Location l : locs)
 	    {
 	 	    w.strikeLightning(l);
 	    }
     }
     
     public void spawnFireworks(Player p)
     {
    	 for(int i=0; i<3; i++)
    	 {
	    	 Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
	         FireworkMeta fwm = fw.getFireworkMeta();
	        
	         //Our random generator
	         Random r = new Random();   
	
	         //Get the type
	         int rt = r.nextInt(4) + 1;
	         Type type = Type.BALL;       
	         if (rt == 1) type = Type.BALL;
	         if (rt == 2) type = Type.BALL_LARGE;
	         if (rt == 3) type = Type.BURST;
	         if (rt == 4) type = Type.CREEPER;
	         if (rt == 5) type = Type.STAR;
	        
	         //Get our random colours   
	         int r1i = r.nextInt(17) + 1;
	         int r2i = r.nextInt(17) + 1;
	         Color c1 = getColor(r1i);
	         Color c2 = getColor(r2i);
	        
	         //Create our effect with this
	         FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
	        
	         //Then apply the effect to the meta
	         fwm.addEffect(effect);
	        
	         //Generate some random power and set it
	         int rp = r.nextInt(2) + 1;
	         fwm.setPower(rp);
	        
	         //Then apply this to our rocket
	         fw.setFireworkMeta(fwm);  
    	 }
     }
     
     private Color getColor(int i) 
     {
    	 Color c = null;
    	 if(i==1){
    	 c=Color.AQUA;
    	 }
    	 if(i==2){
    	 c=Color.BLACK;
    	 }
    	 if(i==3){
    	 c=Color.BLUE;
    	 }
    	 if(i==4){
    	 c=Color.FUCHSIA;
    	 }
    	 if(i==5){
    	 c=Color.GRAY;
    	 }
    	 if(i==6){
    	 c=Color.GREEN;
    	 }
    	 if(i==7){
    	 c=Color.LIME;
    	 }
    	 if(i==8){
    	 c=Color.MAROON;
    	 }
    	 if(i==9){
    	 c=Color.NAVY;
    	 }
    	 if(i==10){
    	 c=Color.OLIVE;
    	 }
    	 if(i==11){
    	 c=Color.ORANGE;
    	 }
    	 if(i==12){
    	 c=Color.PURPLE;
    	 }
    	 if(i==13){
    	 c=Color.RED;
    	 }
    	 if(i==14){
    	 c=Color.SILVER;
    	 }
    	 if(i==15){
    	 c=Color.TEAL;
    	 }
    	 if(i==16){
    	 c=Color.WHITE;
    	 }
    	 if(i==17){
    	 c=Color.YELLOW;
    	 }
    	  
    	 return c;
    }
     
     
     public void completeLevel()
     {
    	 this.SpecialAwardPlayer(0);
     }
     
     public void updateLevel(LevelRules aim)
     {
    	 this.level += 1;
    	 this.aim = aim;
     }
     
     public void updateLevel()
     {
    	 this.level += 1;
     }
     
     @SuppressWarnings("deprecation")
     public void messageStatus(API api)
     {
	     Bukkit.getLogger().info(name);
	     Player player = Bukkit.getPlayer(name);
    	 String message = ChatColor.DARK_PURPLE + "Твой уровень: "+ ChatColor.GREEN + this.level;
    	 player.sendMessage(message);
    	 List<String> messages = this.aim.getCurrentStatus(api, this.uuid, this.earned);
    	 for(String mes : messages)
    	 {
    		 player.sendMessage(mes);
    	 }
     }
     
     @SuppressWarnings("deprecation")
     public void messageSpecialStatus(API api)
     {
	     Bukkit.getLogger().info(name);
	     Player player = Bukkit.getPlayer(name);
    	 String message = ChatColor.DARK_PURPLE + "Твой статус эпических достижений: ";
    	 player.sendMessage(message);
    	 int ct=1;
    	 for(SpecialRules r:specialRules)
    	 {
    		 player.sendMessage(ChatColor.AQUA +"Достижение "+ct);
	    	 List<String> messages = r.getCurrentStatus(api, this.uuid, this.earned);
	    	 for(String mes : messages)
	    	 {
	    		 player.sendMessage(mes);
	    	 }
	    	 ct++;
    	 }
     }

 
     
     @SuppressWarnings("deprecation")
     public void updateEarns()
     {
    	 Essentials essentials = (Essentials)Bukkit.getServer().getPluginManager().getPlugin("Essentials");
         Player pl = Bukkit.getPlayer(name);
         User user = essentials.getUser(pl);
         try
         {
        	 BigDecimal e = user.getMoney();
        	 this.earned = e.intValue();
         }
         catch(Exception ex) 
         {
        	 this.earned = 0;
        	 pl.sendMessage(ChatColor.DARK_RED+"WCL: Error occured during getting your money");
         }
     }
     
   }
   

   
   
   @SuppressWarnings("deprecation")
   	public boolean checkLevel(WCPlayer p)
   {
	   p.updateEarns();
	   if(plg.getLevelRules().size()>p.getLevel())
	  	 if(p.aim.checkLevel(plg.getStatzAPI(), p.uuid, p.earned))
	  	 {
	  		 Player player = Bukkit.getPlayer(p.name);
	  		 p.AwardPlayer();
	  		 if (p.getLevel()+1<plg.getLevelRules().size())
	  		 {
		  		 int newlevel = p.getLevel()+1;
	  			 p.setPlayerName(newlevel);
		  		 player.sendMessage(ChatColor.DARK_PURPLE + "Поздравляем, Вы получили новый уровень");
		  		 p.updateLevel(plg.getNewRule(p.getLevel()));
	  		 }
	  		 else
			 {
		  		 int newlevel = p.getLevel()+1;
	  			 p.setPlayerName(newlevel);
		  		 p.updateLevel();
	  			 player.sendMessage(ChatColor.DARK_PURPLE + "Поздравляем, Вы прошли последний доступный уровень и достигли высшего мастерства."+ ChatColor.GOLD+ "\n Теперь Вы бог сервера.");
	  			 warnPlayers(p.name);
			 }
	  		 savePlayerList();
	  		 return true;
	  	 }
	   return false;
   } 
   
   public boolean checkSpecialRules(WCPlayer p)
  {
	   p.updateEarns();
	   for(int i=0; i<p.specialRules.size(); i++)
	   {
		   if(!p.completedSpecialRules.contains(i))
		   {
		 	 if(p.specialRules.get(i).checkLevel(plg.getStatzAPI(), p.uuid, p.earned))
		 	 {
		 		 p.completedSpecialRules.add(i);
		 		 p.SpecialAwardPlayer(i);
		 		 savePlayerList();
		 		 return true;
		 	 }
		   }
	   }
	   return false;
  }

   
   
	public void warnPlayers(String name) 
	{
		Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
		for(Player p:arrayOfPlayer)
		{
			p.sendMessage(ChatColor.DARK_PURPLE + "Игрок " + ChatColor.GOLD + name + ChatColor.DARK_PURPLE + " достиг высшего уровня. Поздравляем чемпиона!");
		}
	}
	
	public void informPlayers()
	{
		Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
		for(Player p:arrayOfPlayer)
		{
			WCPlayer pl = getPlayer(p.getName());
			pl.messageStatus(this.plg.getStatzAPI());
		}
	}
	
	
	public void informPlayer(WCPlayer p)
	{
		p.messageStatus(this.plg.getStatzAPI());
	}
	

   public void GlobalMessage(String message)
   {
	   String[] s = message.split(":");
	   if(s.length>1)
	   {
		   for(String m : s)
			   Bukkit.broadcastMessage(m);
	   }
	   else Bukkit.broadcastMessage(message);
   }
   
	
   
   
	public void checkPlayerLevels()
	{
		Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
		for(Player p:arrayOfPlayer)
		{
			WCPlayer pl = getPlayer(p.getName());
			checkLevel(pl);
		}
	}

	public void checkPlayerSpecialRules()
	{
		Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
		for(Player p:arrayOfPlayer)
		{
			checkSpecialRules(getPlayer(p.getName()));
		}
	}
	
	
	
	public WCPlayer getPlayer(String name)
	{
		return this.players.get(name);
	}

   public void addPlayer(Player p)
   {
     if (!this.players.containsKey(p.getName())) 
     {
    	 Bukkit.getLogger().info("[WCLevels] Adding new player " + p.getName() + " to List");
    	 this.players.put(p.getName(), new WCPlayer(p.getName(),p.getUniqueId().toString(),1,this.plg.getLevelRules().get(0),this.plg.getSpecialRules(),new ArrayList<Integer>()));
	     WCPlayer pl = this.players.get(p.getName());
	     pl.setPlayerName(1);
	     this.players.remove(p.getName());
	     this.players.put(p.getName(), pl);
	     savePlayerList();
     }
   }
   
   public WCPlayer getPlayer(Player p)
   {
	   return this.players.get(p.getName());
   }

   public void savePlayerList()
   {
	   Bukkit.getLogger().info("[WCLevels] Saving Player List");
     try {
       File f = new File(this.plg.getDataFolder() + File.separator + "players.yml");
       if (f.exists()) f.delete();
       if (this.players.size() > 0) {
         f.createNewFile();
         YamlConfiguration cfg = new YamlConfiguration();
         for (String name : this.players.keySet()) 
         {
        	 cfg.set(name + ".Level", this.players.get(name).level);
        	 cfg.set(name + ".UUID", this.players.get(name).uuid);
        	 String temp = "";
        	 for(Integer i:this.players.get(name).completedSpecialRules)
        	 {
        		 temp+=String.valueOf(i) + ";";
        	 }
        	 if(temp != "")
        	 {
        		 temp = temp.substring(0, temp.length()-1);
        	 }
        	 cfg.set(name + ".CompletedSpecialRules", temp);
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
       if (f.exists()) 
       {
         YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
         
         for (String name : cfg.getKeys(false)) 
         {
  		   int level = Integer.valueOf(cfg.getInt(name + ".Level"));
  		 String[] nums = {""};
  		 List<Integer> completedSpecialRules = new ArrayList<Integer>();
  		 try 
  		 {
  			 nums = cfg.getString(name+ ".CompletedSpecialRules").split(";");
	  		 for(String s:nums)
	  		 {
	  			 completedSpecialRules.add(Integer.valueOf(s));
	  		 }
  		 }
  		 catch(Exception ex){}
        	 this.players.put(name, new WCPlayer(name,cfg.getString(name+".UUID"),level,this.plg.getNewRule(level),this.plg.getSpecialRules(), completedSpecialRules));
		   this.players.get(name).setPlayerName(level);
         }
       }
       else Bukkit.getLogger().info("[WCLevels] File NOT FOUND");
     }
     catch (Exception localException) {}
   }
   
  
   
 }


