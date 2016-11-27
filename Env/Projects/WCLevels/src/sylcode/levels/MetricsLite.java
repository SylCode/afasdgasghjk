 package sylcode.levels;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.UnsupportedEncodingException;
 import java.net.Proxy;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLEncoder;
 import java.util.UUID;
 import java.util.logging.Level;
 import org.bukkit.Bukkit;
 import org.bukkit.configuration.InvalidConfigurationException;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.PluginDescriptionFile;
 import org.bukkit.scheduler.BukkitTask;
 
 
 public class MetricsLite
 {
   private final Plugin plugin;
   private final YamlConfiguration configuration;
   private final File configurationFile;
   private final String guid;
   private final boolean debug;
   private final Object optOutLock = new Object();
   
 
 
 
   private volatile BukkitTask task = null;
   
   public MetricsLite(Plugin plugin) throws IOException {
     if (plugin == null) {
       throw new IllegalArgumentException("Plugin cannot be null");
     }
     
     this.plugin = plugin;
     
 
     this.configurationFile = getConfigFile();
     this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile);
     
 
     this.configuration.addDefault("opt-out", Boolean.valueOf(false));
     this.configuration.addDefault("guid", UUID.randomUUID().toString());
     this.configuration.addDefault("debug", Boolean.valueOf(false));
     
 
     if (this.configuration.get("guid", null) == null) {
       this.configuration.options().header("http://mcstats.org").copyDefaults(true);
       this.configuration.save(this.configurationFile);
     }
     
 
     this.guid = this.configuration.getString("guid");
     this.debug = this.configuration.getBoolean("debug", false);
   }
   
 
 
 
 
 
 
   public boolean start()
   {
     synchronized (this.optOutLock)
     {
       if (isOptOut()) {
         return false;
       }
       
 
       if (this.task != null) {
         return true;
       }
       
 
       this.task = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, new Runnable()
       {
         private boolean firstPost = true;
         
         public void run()
         {
           try {
             synchronized (MetricsLite.this.optOutLock)
             {
               if ((MetricsLite.this.isOptOut()) && (MetricsLite.this.task != null)) {
                 MetricsLite.this.task.cancel();
                 MetricsLite.this.task = null;
               }
             }
             
 
 
 
             MetricsLite.this.postPlugin(!this.firstPost);
             
 
 
             this.firstPost = false;
           } catch (IOException e) {
             if (MetricsLite.this.debug) {
               Bukkit.getLogger().log(Level.INFO, "[Metrics] " + e.getMessage());
             }
           }
         }
       }, 0L, 12000L);
       
       return true;
     }
   }
   
 
 
 
 
   public boolean isOptOut()
   {
     synchronized (this.optOutLock)
     {
       try {
         this.configuration.load(getConfigFile());
       } catch (IOException ex) {
         if (this.debug) {
           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
         }
         return true;
       } catch (InvalidConfigurationException ex) {
         if (this.debug) {
           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
         }
         return true;
       }
       return this.configuration.getBoolean("opt-out", false);
     }
   }
   
 
 
 
 
   public void enable()
     throws IOException
   {
     synchronized (this.optOutLock)
     {
       if (isOptOut()) {
         this.configuration.set("opt-out", Boolean.valueOf(false));
         this.configuration.save(this.configurationFile);
       }
       
 
       if (this.task == null) {
         start();
       }
     }
   }
   
 
 
 
 
   public void disable()
     throws IOException
   {
     synchronized (this.optOutLock)
     {
       if (!isOptOut()) {
         this.configuration.set("opt-out", Boolean.valueOf(true));
         this.configuration.save(this.configurationFile);
       }
       
 
       if (this.task != null) {
         this.task.cancel();
         this.task = null;
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
   public File getConfigFile()
   {
     File pluginsFolder = this.plugin.getDataFolder().getParentFile();
     
 
     return new File(new File(pluginsFolder, "PluginMetrics"), "config.yml");
   }
   
 
 
   private void postPlugin(boolean isPing)
     throws IOException
   {
     PluginDescriptionFile description = this.plugin.getDescription();
     String pluginName = description.getName();
     boolean onlineMode = Bukkit.getServer().getOnlineMode();
     String pluginVersion = description.getVersion();
     String serverVersion = Bukkit.getVersion();
     int playersOnline = Bukkit.getServer().getOnlinePlayers().size();
     
 
 
 
     StringBuilder data = new StringBuilder();
     
 
     data.append(encode("guid")).append('=').append(encode(this.guid));
     encodeDataPair(data, "version", pluginVersion);
     encodeDataPair(data, "server", serverVersion);
     encodeDataPair(data, "players", Integer.toString(playersOnline));
     encodeDataPair(data, "revision", String.valueOf(6));
     
 
     String osname = System.getProperty("os.name");
     String osarch = System.getProperty("os.arch");
     String osversion = System.getProperty("os.version");
     String java_version = System.getProperty("java.version");
     int coreCount = Runtime.getRuntime().availableProcessors();
     
 
     if (osarch.equals("amd64")) {
       osarch = "x86_64";
     }
     
     encodeDataPair(data, "osname", osname);
     encodeDataPair(data, "osarch", osarch);
     encodeDataPair(data, "osversion", osversion);
     encodeDataPair(data, "cores", Integer.toString(coreCount));
     encodeDataPair(data, "online-mode", Boolean.toString(onlineMode));
     encodeDataPair(data, "java_version", java_version);
     
 
     if (isPing) {
       encodeDataPair(data, "ping", "true");
     }
     
 
     URL url = new URL("http://mcstats.org" + String.format("/report/%s", new Object[] { encode(pluginName) }));
     
 
     URLConnection connection;
     
     
     if (isMineshafterPresent()) {
       connection = url.openConnection(Proxy.NO_PROXY);
     } else {
       connection = url.openConnection();
     }
     
     connection.setDoOutput(true);
     
 
     OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
     writer.write(data.toString());
     writer.flush();
     
 
     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
     String response = reader.readLine();
     
 
     writer.close();
     reader.close();
     
     if ((response == null) || (response.startsWith("ERR"))) {
       throw new IOException(response);
     }
   }

 
   private boolean isMineshafterPresent()
   {
     try
     {
       Class.forName("mineshafter.MineServer");
       return true;
     } catch (Exception e) {}
     return false;
   }
   
 
   private static void encodeDataPair(StringBuilder buffer, String key, String value)
     throws UnsupportedEncodingException
   {
     buffer.append('&').append(encode(key)).append('=').append(encode(value));
   }
   
 
 
 
 
   private static String encode(String text)
     throws UnsupportedEncodingException
   {
     return URLEncoder.encode(text, "UTF-8");
   }
 }


