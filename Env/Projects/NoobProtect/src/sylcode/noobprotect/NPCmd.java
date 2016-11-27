 package sylcode.noobprotect;
 
 import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import org.bukkit.metadata.FixedMetadataValue;
//import org.bukkit.metadata.MetadataValue;
 
 

 public class NPCmd
   implements CommandExecutor
 {
   NoobProtect plg;
   NPUtil u;
   
   public NPCmd(NoobProtect plg)
   {
     this.plg = plg;
     this.u = plg.u;
   }
   
 
 
   public boolean onCommand(CommandSender p, Command cmd, String cmdLabel, String[] args)
   {
     if (cmdLabel.equalsIgnoreCase("noob")) {
       if ((args.length > 0) && (this.u.checkCmdPerm(p, args[0]))) {
         if (args.length == 1) return ExecuteCmd(p, args[0]);
         if (args.length == 2) return ExecuteCmd(p, args[0], args[1]);
         if (args.length == 3) return ExecuteCmd(p, args[0], args[1], args[2]);
       } else { this.u.printMSG(p, new Object[] { "cmd_cmdpermerr", Character.valueOf('c') }); }
       return true; }
     if (cmdLabel.equalsIgnoreCase("pvp-on")) {
       if (!(p instanceof Player)) return false;
       Player player = (Player)p;
       if (!player.hasPermission("noob-protector.pvp-on")) return true;
       if (this.plg.players.getPvpOff(player)) 
       {
         //if (checkPvpOnCooldown(player)) 
         //{
           this.plg.players.unprotectPlayer(player);
           this.plg.players.restorePlayerName(player);
           this.u.printMSG(p, new Object[] { "msg_warnpvpon", Character.valueOf('6') });
         //} 
         /*else 
         {
           this.u.printMSG(p, new Object[] { "msg_currenttime", Character.valueOf('3'), Character.valueOf('9'), this.u.getServerTime(this.plg.timezone) });
           this.plg.players.printPlayerProtected(player, true, false);
           this.u.printMSG(p, new Object[] { "msg_pvponcooldown", "/pvp-on;" + this.plg.pvponcooldown });
         }*/
       } else { this.u.printMSG(p, new Object[] { "msg_alreadyunprotected", Character.valueOf('c') });
       }
       return true;
     }
     return false;
   }
   
   /*private boolean checkPvpOnCooldown(Player p) {
     Long ct = Long.valueOf(System.currentTimeMillis());
     if ((!p.hasMetadata("NP-pvp-on-cooldown")) || 
       (ct.longValue() - ((MetadataValue)p.getMetadata("NP-pvp-on-cooldown").get(0)).asLong() > this.plg.pvponcooldown * 1000)) {
       p.setMetadata("NP-pvp-on-cooldown", new FixedMetadataValue(this.plg, ct));
       return false;
     }
     return true;
   }*/
   
   private boolean ExecuteCmd(CommandSender p, String cmd)
   {
     if (cmd.equalsIgnoreCase("help")) {
       this.u.PrintHlpList(p, 1, 15);
       this.u.printMSG(p, new Object[] { "msg_pvponcmd", "/pvp-on" });
     } else if (cmd.equalsIgnoreCase("protect")) {
       if (!(p instanceof Player)) return false;
       Player player = (Player)p;
       this.plg.players.setPlayer(player);
       this.plg.players.printPlayerProtected(player, false, true);
     } else if (cmd.equalsIgnoreCase("unprotect")) {
       if (!(p instanceof Player)) return false;
       Player player = (Player)p;
       if (this.plg.players.unprotectPlayer(player)) this.u.printMSG(p, new Object[] { "msg_youunprotected" }); else
         this.u.printMSG(p, new Object[] { "msg_unprtfail", p.getName() });
     } else if (cmd.equalsIgnoreCase("list")) {
       this.plg.players.printList(p, 1, "");
     } else if (cmd.equalsIgnoreCase("reload")) {
       this.plg.reloadConfig();
       this.plg.loadCfg();
       this.u.printMSG(p, new Object[] { "msg_reloaded" });
     } else if (cmd.equalsIgnoreCase("cfg")) {
       this.u.PrintCfg(p);
     } else { return false; }
     return true;
   }
   
   @SuppressWarnings("deprecation")
private boolean ExecuteCmd(CommandSender p, String cmd, String arg) {
     if (cmd.equalsIgnoreCase("help")) {
       int page = 1;
       if (this.u.isInteger(arg)) page = Integer.parseInt(arg);
       this.u.PrintHlpList(p, page, 15);
     } else if (cmd.equalsIgnoreCase("protect")) {
       Player prp = Bukkit.getPlayerExact(arg);
       	if ((prp != null) && (prp.isOnline())) 
       	{
    	   this.plg.players.setPlayer(prp);
         	this.plg.players.setPlayerName(prp);
         	this.plg.players.printPlayerProtected(prp, false, true);
         	this.plg.players.printTargetPlayerProtected(p, prp);
       	} 
       	else 
       	{ 
    	   	this.u.printMSG(p, new Object[] { "msg_unknownplayer", arg });
       	} 
       } 
     else if (cmd.equalsIgnoreCase("unprotect")) 
       {
    	 Player prp = Bukkit.getPlayerExact(arg);
    	 if (this.plg.players.unprotectPlayer(arg)) 
    	 {
    		 if ((prp != null) && (prp.isOnline())) 
    			 this.u.printMSG(prp, new Object[] { "msg_youunprotected" });
    		 this.u.printMSG(p, new Object[] { "msg_plrisunprotected", arg });
    	 } 
    	 else 
    	 { 
    		 this.u.printMSG(p, new Object[] { "msg_unprtfail", arg });
    	 } 
    	 } 
     else if (cmd.equalsIgnoreCase("list")) {
       int pnum = 1;
       String mask = "";
       if (arg.matches("[1-9]+[0-9]*")) pnum = Integer.parseInt(arg); else
         mask = arg;
       this.plg.players.printList(p, pnum, mask);
     } else { return false; }
     return true;
   }
   
   private boolean ExecuteCmd(CommandSender p, String cmd, String arg1, String arg2) {
     if (cmd.equalsIgnoreCase("list")) {
       int pnum = 1;
       String mask = "";
       if (arg1.matches("[1-9]+[0-9]*")) {
         pnum = Integer.parseInt(arg1);
         mask = arg2;
       } else if (arg2.matches("[1-9]+[0-9]*")) {
         pnum = Integer.parseInt(arg2);
         mask = arg1;
       }
       this.plg.players.printList(p, pnum, mask);
     } else { return false; }
     return true;
   }
 }


