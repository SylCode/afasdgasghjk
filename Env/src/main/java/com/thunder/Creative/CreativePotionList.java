package com.thunder.Creative;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thunder.EnergyAdditions.EnergyAdditionsInfo;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;

public class CreativePotionList {
	
	private static final Logger logger = LogManager.getLogger(EnergyAdditionsInfo.MODID);
	
	public static Potion Agony;
	public static Potion agonyIncubation;
	public static Potion Vamp;
	
	public static void init(){
		
		
		Agony = new AgonyPotion(51, true, 0xFA2E00).setPotionName(StatCollector.translateToLocal("potioneffect.agony"));
		agonyIncubation = new AgonyIncubationPotion(52, true, 0xA031D4).setPotionName(StatCollector.translateToLocal("potioneffect.agonyincubation"));
		Vamp = new VampPotion(53, true, 0x2E5E6E).setPotionName(StatCollector.translateToLocal("potioneffect.vamp"));
		
	}
	
	public static void potionReflection()
	{
		try
		{
			Potion[] potionTypes;
			Field f = ReflectionHelper.findField(Potion.class, "potionTypes", "field_76425_a");

			Field modfield = Field.class.getDeclaredField("modifiers");
			modfield.setAccessible(true);
			modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			potionTypes = (Potion[])f.get(null);

			if (potionTypes.length <= 128)
			{
				final Potion[] newPotionTypes = new Potion[256];
				System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
				f.set(null, newPotionTypes);
			}
			else
			{
				logWarning("potionTypes's array length was previously reflected.");
			}
		}
		catch (Exception e)
		{
			logError("Error during Potion array reflection.", e);
		}
	}
	
	public static void logWarning(String s)
	{
		logger.warn(s);
	}
	
	public static void logError(String s)
	{
		logger.error(s);
	}

	public static void logError(String s, Exception e)
	{
		logger.error(s, e);
	}

}
