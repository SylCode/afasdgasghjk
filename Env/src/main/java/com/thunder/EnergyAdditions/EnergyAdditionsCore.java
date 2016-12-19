package com.thunder.EnergyAdditions;

import com.thunder.Armor.ArmorList;
import com.thunder.Blocks.AdvancedEnergyChargerTileEntity;
import com.thunder.Blocks.BlockList;
import com.thunder.Blocks.EnergyChargerTileEntity;
import com.thunder.Creative.AgonyPotion;
import com.thunder.Creative.CreativeItemList;
import com.thunder.Creative.CreativePotionList;
import com.thunder.Creative.GundiveBowArrow;
import com.thunder.Creative.GundiveBowArrowRenderer;
import com.thunder.Generation.EnergyOreGenerator;
import com.thunder.Items.ItemList;
import com.thunder.Recipes.RecipeList;
import com.thunder.Updaters.UpdatersList;
import com.thunder.network.PacketDispatcher;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid="EnergyAdditions", name=EnergyAdditionsInfo.NAME, version=EnergyAdditionsInfo.VERSION)
public class EnergyAdditionsCore {
	
	@Mod.Instance("EnergyAdditions")
	public static EnergyAdditionsCore mod;
	
	@SidedProxy(clientSide="com.thunder.client.EnergyAdditionsClientProxy", serverSide="com.thunder.EnergyAdditions.EnergyAdditionsCommonProxy")
	public static EnergyAdditionsCommonProxy proxy;
	
	public static CreativeTabs tabEnergyAdditions = new Tab("EnergyAdditions");
	public static CreativeTabs tabEnergyAdditionsCreative = new TabCreative("EnergyAdditionsCreative");
	
	private static Configuration config;
	
	private static int modGuiIndex = 0;
	public static final int GUI_CUSTOM_INV = modGuiIndex++;
	public static final int GUI_ADC = modGuiIndex++;
	public static final int GUI_AT = modGuiIndex++;
	
	public static final EventHandler eventListener = new EventHandler();
	
	public static ArmorMaterial JuggerArmorM = EnumHelper.addArmorMaterial("JuggerArmorM", 500, new int[] {3, 8, 4, 6}, 8);
	public static ArmorMaterial DefenderArmorM = EnumHelper.addArmorMaterial("DefenderArmorM", 750, new int[] {3, 8, 4, 6}, 8);
	public static ArmorMaterial AgilityArmorM = EnumHelper.addArmorMaterial("AgilityArmorM", 450, new int[] {3, 8, 4, 6}, 8);
	public static ArmorMaterial CreativeArmorM = EnumHelper.addArmorMaterial("CreativeArmorM", 300, new int[] {3, 8, 4, 6}, 8);
	

	  @Mod.EventHandler
	  public void preInit(FMLPreInitializationEvent event)
	  {
		config = null;
		config = new Configuration(event.getSuggestedConfigurationFile(), EnergyAdditionsInfo.VERSION, true);
		Config.load();
		
		GameRegistry.registerTileEntity(EnergyChargerTileEntity.class, "EnergyCharger");
		GameRegistry.registerTileEntity(AdvancedEnergyChargerTileEntity.class, "AdvancedEnergyCharger");

	    CreativePotionList.potionReflection();
	    CreativePotionList.init();
		BlockList.blocklist();
		ItemList.items();
		CreativeItemList.items();
		ArmorList.init();
		UpdatersList.updatersList();
		
		OreDictRegistry.registerOres();
		
		GameRegistry.registerWorldGenerator(new EnergyOreGenerator(), 0);
		GameRegistry.registerFuelHandler(new FuelHandler());
		
	    proxy.preInit();
	    
	    PacketDispatcher.preInit();
	    
	  }
	  
	  @Mod.EventHandler
	  public void Init(FMLInitializationEvent event)
	  {
		
	    MinecraftForge.EVENT_BUS.register(eventListener);    
		FMLCommonHandler.instance().bus().register(eventListener); 
		EntityRegistry.registerGlobalEntityID(GundiveBowArrow.class, "GundiveBowArrow", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(GundiveBowArrow.class, "GundiveBowArrow", 1, this, 64, 20, true);//10

		CChestGenHooks.Remove();
	    
	    proxy.init();
	    NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
	    
	    
	  }
	  
	  @Mod.EventHandler
	  public void postInit(FMLPostInitializationEvent event) {
		  
		  RecipeList.Recipes();
	  }
	  
	  public static Configuration getConfig()
	  {
			return config;
	  }
	  
	 
}
