 package sylcode.noobprotect;
 
 import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

 import javax.xml.parsers.DocumentBuilderFactory;

 import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
 
 public abstract class FGUtilCore
 {
   JavaPlugin plg;
   public String px = "";
   private String permprefix = "fgutilcore.";
   private boolean version_check = false;
   private String version_check_url = "";
   private String version_name = "";
   private String version_info_perm = this.permprefix + "config";
   private String language = "english";
   private String plgcmd = "<command>";
   
   YamlConfiguration lng;
   
   protected HashMap<String, String> msg = new HashMap<String, String>();
   private char c1 = 'a';
   private char c2 = '2';
   protected String msglist = "";
   private boolean colorconsole = false;
   
   protected HashMap<String, Cmd> cmds = new HashMap<String, Cmd>();
   protected String cmdlist = "";
   
   PluginDescriptionFile des;
   private double version_current = 0.0D;
   private double version_new = 0.0D;
   private String version_new_str = "unknown";
   private Logger log = Logger.getLogger("Minecraft");
   Random random = new Random();
   
   BukkitTask chId;
   
   public FGUtilCore(JavaPlugin plg, boolean vcheck, boolean savelng, String lng, String devbukkitname, String version_name, String plgcmd, String px)
   {
     this.plg = plg;
     this.des = plg.getDescription();
     this.version_current = Double.parseDouble(this.des.getVersion().replaceFirst("\\.", "").replace("/", ""));
     this.version_name = version_name;
     this.version_check = vcheck;
     this.language = lng;
     InitMsgFile();
     initStdMsg();
     
 
     if (devbukkitname.isEmpty()) { this.version_check = false;
     } else {
       this.version_check_url = ("http://dev.bukkit.org/server-mods/" + devbukkitname + "/files.rss");
       this.permprefix = (devbukkitname + ".");
       UpdateMsg();
       startUpdateTick();
     }
     
     if (version_name.isEmpty()) this.version_name = this.des.getName(); else
       this.version_name = version_name;
     this.px = px;
     this.plgcmd = plgcmd;
   }
   
 
 
   private void initStdMsg()
   {
     addMSG("msg_outdated", "%1% is outdated!");
     addMSG("msg_pleasedownload", "Please download new version (%1%) from ");
     addMSG("hlp_help", "Help");
     addMSG("hlp_thishelp", "%1% - this help");
     addMSG("hlp_execcmd", "%1% - execute command");
     addMSG("hlp_typecmd", "Type %1% - to get additional help");
     addMSG("hlp_typecmdpage", "Type %1% - to see another page of this help");
     addMSG("hlp_commands", "Command list:");
     addMSG("hlp_cmdparam_command", "command");
     addMSG("hlp_cmdparam_page", "page");
     addMSG("hlp_cmdparam_parameter", "parameter");
     addMSG("cmd_unknown", "Unknown command: %1%");
     addMSG("cmd_cmdpermerr", "Something wrong (check command, permissions)");
     addMSG("enabled", "enabled");
     this.msg.put("enabled", ChatColor.DARK_GREEN + (String)this.msg.get("enabled"));
     addMSG("disabled", "disabled");
     this.msg.put("disabled", ChatColor.RED + (String)this.msg.get("disabled"));
     addMSG("lst_title", "String list:");
     addMSG("lst_footer", "Page: [%1% / %2%]");
     addMSG("lst_listisempty", "List is empty");
     addMSG("msg_config", "Configuration");
     addMSG("cfgmsg_general_check-updates", "Check updates: %1%");
     addMSG("cfgmsg_general_language", "Language: %1%");
     addMSG("cfgmsg_general_language-save", "Save translation file: %1%");
   }
   
   public void setConsoleColored(boolean colorconsole)
   {
     this.colorconsole = colorconsole;
   }
   
   public boolean isConsoleColored() {
     return this.colorconsole;
   }
   
 
 
   public void SetVersionCheck(boolean vc)
   {
     this.version_check = vc;
   }
   
 
 
 
   public void UpdateMsg(Player p)
   {
     if ((this.version_check) && (p.hasPermission(this.version_info_perm)) && (this.version_new > this.version_current)) {
       printMSG(p, new Object[] { "msg_outdated", Character.valueOf('e'), Character.valueOf('6'), "&6" + this.des.getName() + " v" + this.des.getVersion() });
       printMSG(p, new Object[] { "msg_pleasedownload", Character.valueOf('e'), Character.valueOf('6'), this.version_new_str });
       printMsg(p, "&3" + this.version_check_url.replace("files.rss", ""));
     }
   }
   
 
 
   public void UpdateMsg()
   {
     this.plg.getServer().getScheduler().runTaskAsynchronously(this.plg, new Runnable() {
       public void run() {
         FGUtilCore.this.version_new = FGUtilCore.this.getNewVersion(FGUtilCore.this.version_current);
         if (FGUtilCore.this.version_new > FGUtilCore.this.version_current) {
           FGUtilCore.this.log.info("[" + FGUtilCore.this.des.getName() + "] " + FGUtilCore.this.des.getName() + " v" + FGUtilCore.this.des.getVersion() + " is outdated! Recommended version is v" + FGUtilCore.this.version_new_str);
           FGUtilCore.this.log.info("[" + FGUtilCore.this.des.getName() + "] " + FGUtilCore.this.version_check_url.replace("files.rss", ""));
         }
       }
     });
   }
   
 
 
 
   private double getNewVersion(double currentVersion)
   {
     if (this.version_check) {
       try {
         URL url = new URL(this.version_check_url);
         Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openConnection().getInputStream());
         doc.getDocumentElement().normalize();
         NodeList nodes = doc.getElementsByTagName("item");
         Node firstNode = nodes.item(0);
         if (firstNode.getNodeType() == 1) {
           Element firstElement = (Element)firstNode;
           NodeList firstElementTagName = firstElement.getElementsByTagName("title");
           Element firstNameElement = (Element)firstElementTagName.item(0);
           NodeList firstNodes = firstNameElement.getChildNodes();
           this.version_new_str = firstNodes.item(0).getNodeValue().replace(this.version_name + " v", "").trim();
           return Double.parseDouble(this.version_new_str.replaceFirst("\\.", "").replace("/", ""));
         }
       }
       catch (Exception localException) {}
     }
     
     return currentVersion;
   }
   
 
 
   private void startUpdateTick()
   {
     this.chId = this.plg.getServer().getScheduler().runTaskTimerAsynchronously(this.plg, new Runnable() {
       public void run() {
         FGUtilCore.this.version_new = FGUtilCore.this.getNewVersion(FGUtilCore.this.version_current);
       }
     }, (10 + this.random.nextInt(50)) * 1200, 72000L);
   }
   
 
 
 
 
 
 
 
 
 
   public void addCmd(String cmd, String perm, String desc_id, String desc_key)
   {
     addCmd(cmd, perm, desc_id, desc_key, this.c1, this.c2, false);
   }
   
   public void addCmd(String cmd, String perm, String desc_id, String desc_key, char color) {
     addCmd(cmd, perm, desc_id, desc_key, this.c1, color, false);
   }
   
   public void addCmd(String cmd, String perm, String desc_id, String desc_key, boolean console) {
     addCmd(cmd, perm, desc_id, desc_key, this.c1, this.c2, console);
   }
   
   public void addCmd(String cmd, String perm, String desc_id, String desc_key, char color, boolean console) {
     addCmd(cmd, perm, desc_id, desc_key, this.c1, color, console);
   }
   
   public void addCmd(String cmd, String perm, String desc_id, String desc_key, char color1, char color2) {
     addCmd(cmd, perm, desc_id, desc_key, color1, color2, false);
   }
   
   public void addCmd(String cmd, String perm, String desc_id, String desc_key, char color1, char color2, boolean console) {
     String desc = getMSG(new Object[] { desc_id, desc_key, Character.valueOf(color1), Character.valueOf(color2) });
     this.cmds.put(cmd, new Cmd(this.permprefix + perm, desc, console));
     if (this.cmdlist.isEmpty()) this.cmdlist = cmd; else {
       this.cmdlist = (this.cmdlist + ", " + cmd);
     }
   }
   
 
 
   public boolean checkCmdPerm(CommandSender sender, String cmd)
   {
     if (!this.cmds.containsKey(cmd.toLowerCase())) return false;
     Cmd cm = (Cmd)this.cmds.get(cmd.toLowerCase());
     if ((sender instanceof Player)) return (cm.perm.isEmpty()) || (sender.hasPermission(cm.perm));
     return cm.console;
   }
   
 
   public class Cmd
   {
     String perm;
     String desc;
     boolean console;
     
     public Cmd(String perm, String desc)
     {
       this.perm = perm;
       this.desc = desc;
       this.console = false;
     }
     
     public Cmd(String perm, String desc, boolean console) { this.perm = perm;
       this.desc = desc;
       this.console = console;
     }
   }
   
   public class PageList {
     private List<String> ln;
     private int lpp = 15;
     private String title_msgid = "lst_title";
     private String footer_msgid = "lst_footer";
     private boolean shownum = false;
     
     public void setLinePerPage(int lpp) {
       this.lpp = lpp;
     }
     
     public PageList(List<String> ln, String title_msgid, String footer_msgid, boolean shownum) {
       this.ln = ln;
       if (!title_msgid.isEmpty()) this.title_msgid = title_msgid;
       if (!footer_msgid.isEmpty()) this.footer_msgid = footer_msgid;
       this.shownum = shownum;
     }
     
     public void addLine(String str) {
       this.ln.add(str);
     }
     
     public boolean isEmpty() {
       return this.ln.isEmpty();
     }
     
     public void setTitle(String title_msgid) {
       this.title_msgid = title_msgid;
     }
     
 
     public void setShowNum(boolean shownum) {}
     
     public void setFooter(String footer_msgid)
     {
       this.footer_msgid = footer_msgid;
     }
     
     public void printPage(CommandSender p, int pnum) {
       printPage(p, pnum, this.lpp);
     }
     
     public void printPage(CommandSender p, int pnum, int linesperpage) {
       if (this.ln.size() > 0)
       {
         int maxp = this.ln.size() / linesperpage;
         if (this.ln.size() % linesperpage > 0) maxp++;
         if (pnum > maxp) pnum = maxp;
         int maxl = linesperpage;
         if (pnum == maxp) {
           maxl = this.ln.size() % linesperpage;
           if (maxp == 1) maxl = this.ln.size();
         }
         if (maxl == 0) maxl = linesperpage;
         if (FGUtilCore.this.msg.containsKey(this.title_msgid)) FGUtilCore.this.printMsg(p, "&6&l" + FGUtilCore.this.getMSGnc(new Object[] { this.title_msgid })); else {
           FGUtilCore.this.printMsg(p, this.title_msgid);
         }
         String numpx = "";
         for (int i = 0; i < maxl; i++) {
           if (this.shownum) numpx = "&3" + Integer.toString(1 + i + (pnum - 1) * linesperpage) + ". ";
           FGUtilCore.this.printMsg(p, numpx + "&a" + (String)this.ln.get(i + (pnum - 1) * linesperpage));
         }
         if (maxp > 1) FGUtilCore.this.printMSG(p, new Object[] { this.footer_msgid, Character.valueOf('e'), Character.valueOf('6'), Integer.valueOf(pnum), Integer.valueOf(maxp) });
       } else { FGUtilCore.this.printMSG(p, new Object[] { "lst_listisempty", Character.valueOf('c') });
       }
     }
   }
   
   public void printPage(CommandSender p, List<String> ln, int pnum, String title, String footer, boolean shownum) {
     PageList pl = new PageList(ln, title, footer, shownum);
     pl.printPage(p, pnum);
   }
   
   public void printPage(CommandSender p, List<String> ln, int pnum, String title, String footer, boolean shownum, int lineperpage) {
     PageList pl = new PageList(ln, title, footer, shownum);
     pl.printPage(p, pnum, lineperpage);
   }
   
 
 
 
 
 
 
 
 
   public boolean isIdInList(int id, String str)
   {
     if (!str.isEmpty()) {
       String[] ln = str.split(",");
       if (ln.length > 0)
         for (int i = 0; i < ln.length; i++)
           if ((!ln[i].isEmpty()) && (ln[i].matches("[0-9]*")) && (Integer.parseInt(ln[i]) == id)) return true;
     }
     return false;
   }
   
 
   public boolean isAllIdInList(int[] ids, String str)
   {
     int[] arrayOfInt;
     
     int j = (arrayOfInt = ids).length; for (int i = 0; i < j; i++) { int id = arrayOfInt[i];
       if (!isIdInList(id, str)) return false; }
     return true;
   }
   
 
 
 
 
   public boolean isWordInList(String word, String str)
   {
     String[] ln = str.split(",");
     if (ln.length > 0)
       for (int i = 0; i < ln.length; i++)
         if (ln[i].equalsIgnoreCase(word)) return true;
     return false;
   }
   
 
 
 
 
   public boolean isItemInList(int id, int data, String str)
   {
     String[] ln = str.split(",");
     if (ln.length > 0)
       for (int i = 0; i < ln.length; i++)
         if (compareItemStr(id, data, ln[i])) return true;
     return false;
   }
   
   @SuppressWarnings("deprecation")
public boolean compareItemStr(ItemStack item, String itemstr)
   {
     return compareItemStr(item.getTypeId(), item.getData().getData(), item.getAmount(), itemstr);
   }
   
   public boolean compareItemStr(int item_id, int item_data, String itemstr) {
     return compareItemStr(item_id, item_data, 1, itemstr);
   }
   
   @SuppressWarnings("deprecation")
public boolean compareItemStr(int item_id, int item_data, int item_amount, String itemstr)
   {
     if (!itemstr.isEmpty()) {
       int id = -1;
       int amount = 1;
       int data = -1;
       String[] si = itemstr.split("\\*");
       if (si.length > 0) {
         if ((si.length == 2) && (si[1].matches("[1-9]+[0-9]*"))) amount = Integer.parseInt(si[1]);
         String[] ti = si[0].split(":");
         if (ti.length > 0) {
           if (ti[0].matches("[0-9]*")) id = Integer.parseInt(ti[0]); else
             id = Material.getMaterial(ti[0]).getId();
           if ((ti.length == 2) && (ti[1].matches("[0-9]*"))) data = Integer.parseInt(ti[1]);
           return (item_id == id) && ((data < 0) || (item_data == data)) && (item_amount >= amount);
         }
       }
     }
     return false;
   }
   
   public int countItemInInventory(Player p, String itemstr)
   {
     return countItemInInventory(p.getInventory(), itemstr);
   }
   
   public void removeItemInInventory(final Player p, final String itemstr) {
     Bukkit.getScheduler().runTaskLater(this.plg, new Runnable()
     {
       public void run() {
         FGUtilCore.this.removeItemInInventory(p.getInventory(), itemstr);
       }
     }, 1L);
   }
   
   @SuppressWarnings("deprecation")
public int removeItemInInventory(Inventory inv, String itemstr) {
     int left = 1;
     if (left <= 0) return -1;
     int id = -1;
     int data = -1;
     String[] si = itemstr.split("\\*");
     if (si.length == 0) return left;
     if ((si.length == 2) && (si[1].matches("[1-9]+[0-9]*"))) left = Integer.parseInt(si[1]);
     String[] ti = si[0].split(":");
     
     if (ti.length > 0) {
       if (ti[0].matches("[0-9]*")) id = Integer.parseInt(ti[0]); else
         id = Material.getMaterial(ti[0]).getId();
       if ((ti.length == 2) && (ti[1].matches("[0-9]*"))) data = Integer.parseInt(ti[1]);
     }
     if (id <= 0) return left;
     for (int i = 0; i < inv.getContents().length; i++) {
       ItemStack slot = inv.getItem(i);
       if ((slot != null) && 
         (id == slot.getTypeId()) && (
         (data <= 0) || (data == slot.getData().getData()))) {
         int slotamount = slot.getAmount();
         if (slotamount != 0) {
           if (slotamount <= left) {
             left -= slotamount;
             inv.setItem(i, null);
           }
           else {
             slot.setAmount(slotamount - left);
             left = 0;
           }
           if (left == 0) return 0;
         } } }
     return left;
   }
   
   @SuppressWarnings("deprecation")
public int countItemInInventory(Inventory inv, String itemstr) {
     int count = 0;
     int id = -1;
     int data = -1;
     String[] si = itemstr.split("\\*");
     if (si.length == 0) { return 0;
     }
     String[] ti = si[0].split(":");
     if (ti.length > 0) {
       if (ti[0].matches("[0-9]*")) id = Integer.parseInt(ti[0]); else
         id = Material.getMaterial(ti[0]).getId();
       if ((ti.length == 2) && (ti[1].matches("[0-9]*"))) data = Integer.parseInt(ti[1]);
     }
     if (id <= 0) return 0;
     ItemStack[] arrayOfItemStack;
     int j = (arrayOfItemStack = inv.getContents()).length; for (int i = 0; i < j; i++) { ItemStack slot = arrayOfItemStack[i];
       if ((slot != null) && 
         (id == slot.getTypeId()) && (
         (data < 0) || (data == slot.getData().getData()))) { count += slot.getAmount();
       }
     }
     return count;
   }
   
 
   @SuppressWarnings("deprecation")
public boolean removeItemInHand(Player p, String itemstr)
   {
     if (!itemstr.isEmpty()) {
       int id = -1;
       int amount = 1;
       int data = -1;
       String[] si = itemstr.split("\\*");
       if (si.length > 0) {
         if ((si.length == 2) && (si[1].matches("[1-9]+[0-9]*"))) amount = Integer.parseInt(si[1]);
         String[] ti = si[0].split(":");
         if (ti.length > 0) {
           if (ti[0].matches("[0-9]*")) id = Integer.parseInt(ti[0]); else
             id = Material.getMaterial(ti[0]).getId();
           if ((ti.length == 2) && (ti[1].matches("[0-9]*"))) data = Integer.parseInt(ti[1]);
           return removeItemInHand(p, id, data, amount);
         }
       }
     }
     return false;
   }
   
   @SuppressWarnings("deprecation")
public boolean removeItemInHand(Player p, int item_id, int item_data, int item_amount) {
     if ((p.getItemInHand() != null) && 
       (p.getItemInHand().getTypeId() == item_id) && 
       (p.getItemInHand().getAmount() >= item_amount) && (
       (item_data < 0) || (item_data == p.getItemInHand().getData().getData())))
     {
       if (p.getItemInHand().getAmount() > item_amount) p.getItemInHand().setAmount(p.getItemInHand().getAmount() - item_amount); else {
         p.setItemInHand(new ItemStack(Material.AIR));
       }
       return true;
     }
     return false;
   }
   
   public void giveItemOrDrop(Player p, ItemStack item) {
     HashMap<Integer, ItemStack> result = p.getInventory().addItem(new ItemStack[] { item });
     if (result.size() > 0) {
       for (Iterator<Integer> localIterator = result.keySet().iterator(); localIterator.hasNext();) { int i = ((Integer)localIterator.next()).intValue();
         p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)result.get(Integer.valueOf(i)));
       }
     }
   }
   
   public void printMsg(CommandSender p, String msg)
   {
     String message = ChatColor.translateAlternateColorCodes('&', msg);
     if ((!(p instanceof Player)) && (!this.colorconsole)) message = ChatColor.stripColor(message);
     p.sendMessage(message);
   }
   
 
   public void printPxMsg(CommandSender p, String msg)
   {
     printMsg(p, this.px + msg);
   }
   
 
 
 
 
 
   public void BC(String msg) { this.plg.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.px + msg)); }



	public void broadcastMSG(String perm, Object... s) 
	{
		Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
		arrayOfPlayer.size();
		for(Player p:arrayOfPlayer)
		{
			if (p.hasPermission(this.permprefix + perm)) 
				printMSG(p, s);
			
		}
	}
   
   public void broadcastMsg(String perm, String msg) 
   { 
	   Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();
		arrayOfPlayer.size();
		for(Player p:arrayOfPlayer)
		{
			if (p.hasPermission(this.permprefix + perm))
			{ 
				printMsg(p, msg);
				}
			}
		}
   
 
 
 
 
 
 
 
 
   public void log(String msg)
   {
     this.log.info(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', this.px + msg)));
   }
   
 
 
   public void SC(String msg)
   {
     this.plg.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.px + msg));
   }
   
 
 
 
 
 
 
 
 
   @SuppressWarnings("deprecation")
public void InitMsgFile()
   {
     try
     {
       this.lng = new YamlConfiguration();
       File f = new File(this.plg.getDataFolder() + File.separator + this.language + ".lng");
       if (f.exists()) { this.lng.load(f);
       } else {
         InputStream is = this.plg.getClass().getResourceAsStream("/language/" + this.language + ".lng");
         if (is != null) this.lng.load(is);
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   
 
 
 
 
 
 
   public void addMSG(String key, String txt)
   {
     this.msg.put(key, ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', this.lng.getString(key, txt))));
     if (this.msglist.isEmpty()) this.msglist = key; else {
       this.msglist = (this.msglist + "," + key);
     }
   }
   
 
 
   public void SaveMSG()
   {
     String[] keys = this.msglist.split(",");
     try {
       File f = new File(this.plg.getDataFolder() + File.separator + this.language + ".lng");
       if (!f.exists()) f.createNewFile();
       YamlConfiguration cfg = new YamlConfiguration();
       for (int i = 0; i < keys.length; i++)
         cfg.set(keys[i], this.msg.get(keys[i]));
       cfg.save(f);
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   
 
 
   public String getMSG(Object... s)
   {
     String str = "&4Unknown message";
     String color1 = "&" + this.c1;
     String color2 = "&" + this.c2;
     if (s.length > 0) {
       String id = s[0].toString();
       str = "&4Unknown message (" + id + ")";
       if (this.msg.containsKey(id)) {
         int px = 1;
         if ((s.length > 1) && ((s[1] instanceof Character))) {
           px = 2;
           color1 = "&" + (Character)s[1];
           if ((s.length > 2) && ((s[2] instanceof Character))) {
             px = 3;
             color2 = "&" + (Character)s[2];
           }
         }
         str = color1 + (String)this.msg.get(id);
         if (px < s.length) {
           for (int i = px; i < s.length; i++) {
             String f = s[i].toString();
             if ((s[i] instanceof Location)) {
               Location loc = (Location)s[i];
               f = loc.getWorld().getName() + "[" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + "]";
             }
             str = str.replace("%" + Integer.toString(i - px + 1) + "%", color2 + f + color1);
           }
         }
       }
     }
     return ChatColor.translateAlternateColorCodes('&', str);
   }
   
   public void printMSG(CommandSender p, Object... s) {
     String message = getMSG(s);
     if ((!(p instanceof Player)) && (!this.colorconsole)) message = ChatColor.stripColor(message);
     p.sendMessage(message);
   }
   
 
 
 
 
 
 
   public void PrintHLP(Player p)
   {
     printMsg(p, "&6&l" + this.version_name + " v" + this.des.getVersion() + " &r&6| " + getMSG(new Object[] { "hlp_help", Character.valueOf('6') }));
     printMSG(p, new Object[] { "hlp_thishelp", "/" + this.plgcmd + " help" });
     printMSG(p, new Object[] { "hlp_execcmd", "/" + this.plgcmd + " <" + getMSG(new Object[] { "hlp_cmdparam_command", Character.valueOf('2') }) + "> [" + getMSG(new Object[] { "hlp_cmdparam_parameter", Character.valueOf('2') }) + "]" });
     printMSG(p, new Object[] { "hlp_typecmd", "/" + this.plgcmd + " help <" + getMSG(new Object[] { "hlp_cmdparam_command", Character.valueOf('2') }) + ">" });
     printMsg(p, getMSG(new Object[] { "hlp_commands" }) + " &2" + this.cmdlist);
   }
   
 
 
 
   public void printHLP(Player p, String cmd)
   {
     if (this.cmds.containsKey(cmd)) {
       printMsg(p, "&6&l" + this.version_name + " v" + this.des.getVersion() + " &r&6| " + getMSG(new Object[] { "hlp_help", Character.valueOf('6') }));
       printMsg(p, ((Cmd)this.cmds.get(cmd)).desc);
     } else { printMSG(p, new Object[] { "cmd_unknown", Character.valueOf('c'), Character.valueOf('e'), cmd });
     }
   }
   
   public void PrintHlpList(CommandSender p, int page, int lpp) { String title = "&6&l" + this.version_name + " v" + this.des.getVersion() + " &r&6| " + getMSG(new Object[] { "hlp_help", Character.valueOf('6') });
     List<String> hlp = new ArrayList<String>();
     hlp.add(getMSG(new Object[] { "hlp_thishelp", "/" + this.plgcmd + " help" }));
     hlp.add(getMSG(new Object[] { "hlp_execcmd", "/" + this.plgcmd + " <" + getMSG(new Object[] { "hlp_cmdparam_command", Character.valueOf('2') }) + "> [" + getMSG(new Object[] { "hlp_cmdparam_parameter", Character.valueOf('2') }) + "]" }));
     if ((p instanceof Player)) { hlp.add(getMSG(new Object[] { "hlp_typecmdpage", "/" + this.plgcmd + " help <" + getMSG(new Object[] { "hlp_cmdparam_page", Character.valueOf('2') }) + ">" }));
     }
     String[] ks = this.cmdlist.replace(" ", "").split(",");
     if (ks.length > 0) { String[] arrayOfString1;
       int j = (arrayOfString1 = ks).length; for (int i = 0; i < j; i++) { String cmd = arrayOfString1[i];
         hlp.add(((Cmd)this.cmds.get(cmd)).desc);
       } }
     printPage(p, hlp, page, title, "", false, lpp);
   }
   
 
 
   public String EnDis(boolean b)
   {
     return b ? getMSG(new Object[] { "enabled", Character.valueOf('2') }) : getMSG(new Object[] { "disabled", Character.valueOf('c') });
   }
   
   public String EnDis(String str, boolean b) {
     String str2 = ChatColor.stripColor(str);
     return ChatColor.RED + str2;
   }
   
 
 
   public void printEnDis(CommandSender p, String msg_id, boolean b)
   {
     p.sendMessage(getMSG(new Object[] { msg_id }) + ": " + EnDis(b));
   }
   
 
 
 
 
 
 
 
   public void setPermPrefix(String ppfx)
   {
     this.permprefix = (ppfx + ".");
     this.version_info_perm = (this.permprefix + "config");
   }
   
 
 
 
   public boolean equalCmdPerm(String cmd, String perm)
   {
     return (this.cmds.containsKey(cmd.toLowerCase())) && 
       (((Cmd)this.cmds.get(cmd.toLowerCase())).perm.equalsIgnoreCase(this.permprefix + perm));
   }
   
 
 
 
 
 
   @SuppressWarnings("deprecation")
public ItemStack parseItemStack(String itemstr)
   {
     if (!itemstr.isEmpty()) {
       int id = -1;
       int amount = 1;
       short data = 0;
       String[] si = itemstr.split("\\*");
       if (si.length > 0) {
         if ((si.length == 2) && (si[1].matches("[1-9]+[0-9]*"))) amount = Integer.parseInt(si[1]);
         String[] ti = si[0].split(":");
         if (ti.length > 0) {
           if (ti[0].matches("[0-9]*")) id = Integer.parseInt(ti[0]); else
             id = Material.getMaterial(ti[0]).getId();
           if ((ti.length == 2) && (ti[1].matches("[0-9]*"))) data = Short.parseShort(ti[1]);
           return new ItemStack(id, amount, data);
         }
       }
     }
     return null;
   }
   
 
 
 
   public boolean isPlayerAround(Location loc, int radius)
   {
     for (Player p : loc.getWorld().getPlayers()) {
       if (p.getLocation().distance(loc) <= radius) return true;
     }
     return false;
   }
   
 
 
   public String getMSGnc(Object... s)
   {
     return ChatColor.stripColor(getMSG(s));
   }
   
 
 
 
   public boolean placeBlock(Location loc, Player p, Material newType, byte newData, boolean phys)
   {
     return placeBlock(loc.getBlock(), p, newType, newData, phys);
   }
   
 
 
   @SuppressWarnings("deprecation")
public boolean placeBlock(Block block, Player p, Material newType, byte newData, boolean phys)
   {
     BlockState state = block.getState();
     block.setTypeIdAndData(newType.getId(), newData, phys);
     BlockPlaceEvent event = new BlockPlaceEvent(state.getBlock(), state, block, p.getItemInHand(), p, true);
     this.plg.getServer().getPluginManager().callEvent(event);
     if (event.isCancelled()) state.update(true);
     return event.isCancelled();
   }
   
   public boolean rollDiceChance(int chance) {
     return this.random.nextInt(100) < chance;
   }
   
   public int getRandomInt(int maxvalue) {
     return this.random.nextInt(maxvalue);
   }
   
 
 
 
   public boolean isIntegerSigned(String str)
   {
     return str.matches("-?[0-9]+[0-9]*");
   }
   
   public boolean isIntegerSigned(String... str) {
     if (str.length == 0) return false;
     String[] arrayOfString; int j = (arrayOfString = str).length; for (int i = 0; i < j; i++) { String s = arrayOfString[i];
       if (!s.matches("-?[0-9]+[0-9]*")) return false; }
     return true;
   }
   
   public boolean isInteger(String str) {
     return str.matches("[0-9]+[0-9]*");
   }
   
   public boolean isInteger(String... str) {
     if (str.length == 0) return false;
     String[] arrayOfString; int j = (arrayOfString = str).length; for (int i = 0; i < j; i++) { String s = arrayOfString[i];
       if (!s.matches("[0-9]+[0-9]*")) return false; }
     return true;
   }
   
   public boolean isIntegerGZ(String str)
   {
     return str.matches("[1-9]+[0-9]*");
   }
   
   public boolean isIntegerGZ(String... str) {
     if (str.length == 0) return false;
     String[] arrayOfString; int j = (arrayOfString = str).length; for (int i = 0; i < j; i++) { String s = arrayOfString[i];
       if (!s.matches("[1-9]+[0-9]*")) return false; }
     return true;
   }
   
   public void printConfig(CommandSender p, int page, int lpp, boolean section, boolean usetranslation) {
     List<String> cfgprn = new ArrayList<String>();
     if (!this.plg.getConfig().getKeys(true).isEmpty())
       for (String k : this.plg.getConfig().getKeys(true)) {
         Object objvalue = this.plg.getConfig().get(k);
         String value = objvalue.toString();
         String str = k;
         if (((objvalue instanceof Boolean)) && (usetranslation)) value = EnDis(((Boolean)objvalue).booleanValue());
         if ((objvalue instanceof MemorySection)) {
           if (!section) continue;
         } else str = k + " : " + value;
         if (usetranslation) str = getMSG(new Object[] { "cfgmsg_" + k.replaceAll("\\.", "_"), value });
         cfgprn.add(str);
       }
     String title = "&6&l" + this.version_name + " v" + this.des.getVersion() + " &r&6| " + getMSG(new Object[] { "msg_config", Character.valueOf('6') });
     printPage(p, cfgprn, page, title, "", false);
   }
 }


