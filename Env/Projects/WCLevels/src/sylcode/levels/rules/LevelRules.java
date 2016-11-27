package sylcode.levels.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.staartvin.statz.api.API;
import me.staartvin.statz.database.datatype.RowRequirement;
import me.staartvin.statz.datamanager.PlayerStat;

public class LevelRules {
	int level;

	List<ArrowRule> arrowRules;
	List<BreakRule> breakRules;
	BucketEmptyRule bucketEmptyRule;
	BucketFillRule bucketFillRule;
	List<CommandsRule> commandsRules;
	List<CraftRule> craftRules;
	List<DamageTakeRule> damageTakeRules;
	DeathRule deathRule;
	List<EatRule> eatRules;
	EggThrowRule eggThrownRule;
	JoinRule joinRule;
	KickRule kickRule;
	List<KillRule> killRules;
	List<PlayerKillRule> playerKillRules;
	List<PlaceRule> placeRules;
	PlayTimeRule playTimeRule;
	List<ToolBreakRule> toolBreakRules;
	List<TravelRule> travelRules;
	List<VillagerTradeRule> villagerTradeRules;
	XPRule xpRule;
	int earn;
	
	String[] commands;
	
	
	public LevelRules(int level, List<ArrowRule> arrowRules,List<BreakRule> breakRules,BucketEmptyRule bucketEmptyRule,
			BucketFillRule bucketFillRule,List<CommandsRule> commandsRules,List<CraftRule> craftRules,
			List<DamageTakeRule> damageTaketRules,DeathRule deathRule,List<EatRule> eatRules,
			EggThrowRule eggThrownRule,JoinRule joinRule,KickRule kickRule,
			List<KillRule> killRules,List<PlayerKillRule> playerKillRules,List<PlaceRule> placeRules,PlayTimeRule playTimeRule,
			List<ToolBreakRule> toolBreakRules,List<TravelRule> travelRules,List<VillagerTradeRule> villagerTradeRules,
			XPRule xpRule, int earn, String[] commands)
	{
		this.level = level;
		this.arrowRules = arrowRules;
		this.breakRules = breakRules;
		this.bucketEmptyRule = bucketEmptyRule;
		this.bucketFillRule = bucketFillRule;
		this.commandsRules = commandsRules;
		this.craftRules = craftRules;
		this.damageTakeRules = damageTaketRules;
		this.deathRule = deathRule;
		this.eatRules = eatRules;
		this.eggThrownRule = eggThrownRule;
		this.joinRule = joinRule;
		this.kickRule = kickRule;
		this.killRules = killRules;
		this.playerKillRules = playerKillRules;
		this.placeRules = placeRules;
		this.playTimeRule = playTimeRule;
		this.toolBreakRules = toolBreakRules;
		this.travelRules = travelRules;
		this.villagerTradeRules = villagerTradeRules;
		this.xpRule = xpRule;
		this.earn = earn;
		this.commands = commands;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public List<KillRule> getKillRules()
	{
		return killRules;
	}
	public List<PlayerKillRule> getPlayerKillRules()
	{
		return playerKillRules;
	}
	
	public List<BreakRule> getBreakRules()
	{
		return breakRules;
	}
	
	public List<PlaceRule> getPlaceRules()
	{
		return placeRules;
	}
	
	public List<CraftRule> getCraftRules()
	{
		return this.craftRules;
	}
	
	public List<CommandsRule> getCommandsRules()
	{
		return this.commandsRules;
	}

	public BucketFillRule getBucketFillRule()
	{
		return this.bucketFillRule;
	}

	public List<DamageTakeRule> getDamageTakeRules()
	{
		return this.damageTakeRules;
	}

	public DeathRule getDeathRule()
	{
		return this.deathRule;
	}

	public List<EatRule> getEatRules()
	{
		return this.eatRules;
	}

	public EggThrowRule getEggThrowRule()
	{
		return this.eggThrownRule;
	}

	public JoinRule getJoinRule()
	{
		return this.joinRule;
	}

	public KickRule getKickRule()
	{
		return this.kickRule;
	}

	public PlayTimeRule getPlayTimeRule()
	{
		return this.playTimeRule;
	}

	public List<ToolBreakRule> getToolBreakRules()
	{
		return this.toolBreakRules;
	}

	public List<TravelRule> getTravelRules()
	{
		return this.travelRules;
	}

	public List<VillagerTradeRule> getVillagerTradeRules()
	{
		return this.villagerTradeRules;
	}

	public BucketEmptyRule getBucketEmptyRule()
	{
		return this.bucketEmptyRule;
	}
	
	public XPRule getXPRule()
	{
		return this.xpRule;
	}
	
	public int getEarn()
	{
		return this.earn;
	}
	
	public void setEarn(int n)
	{
		if (this.earn<n)
			this.earn = n;
	}
	
	public String[] getCommand()
	{
		return this.commands;
	}
	
	public boolean checkLevel(API api,String uuid,int earned)
	{
		try{
			Double n;
			if(arrowRules!=null)
			{
				for(int i=0;i<arrowRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.ARROWS_SHOT, UUID.fromString(uuid),new RowRequirement("forceShot", String.valueOf(arrowRules.get(i).Power)));
					if (arrowRules.get(i).Shots>n)
						return false;
				}
			}
			if(breakRules!=null)
			{
				for(int i=0;i<breakRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.BLOCKS_BROKEN, UUID.fromString(uuid), 
							new RowRequirement("typeid", String.valueOf(breakRules.get(i).blockID)),
							new RowRequirement("datavalue", String.valueOf(breakRules.get(i).metadata)));
					if (breakRules.get(i).numberOfBreaks>n)
						return false;
				}
			}
			if(bucketEmptyRule!=null)
			{
					n = (Double) api.getSpecificData(PlayerStat.BUCKETS_EMPTIED, UUID.fromString(uuid));
					if (bucketEmptyRule.numberOfEmpty>n)
						return false;
			}
			if(bucketFillRule!=null)
			{
					n = (Double) api.getSpecificData(PlayerStat.BUCKETS_FILLED, UUID.fromString(uuid));
					if (bucketFillRule.numberOfFill>n)
						return false;
			}
			if(craftRules!=null)
			{
				for(int i=0;i<craftRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.ITEMS_CRAFTED, UUID.fromString(uuid), 
							new RowRequirement("item", craftRules.get(i).itemID));
					if (craftRules.get(i).numberOfCrafts>n)
						return false;
				}
			}
			if(damageTakeRules!=null)
			{
				for(int i=0;i<damageTakeRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.DAMAGE_TAKEN, UUID.fromString(uuid), 
							new RowRequirement("cause", damageTakeRules.get(i).DamageType));
					if (damageTakeRules.get(i).amount>n)
						return false;
				}
			}
			if(deathRule!=null)
			{
				n = (Double) api.getSpecificData(PlayerStat.DEATHS, UUID.fromString(uuid));
				if (deathRule.deaths>n)
					return false;
			}
			if(eatRules!=null)
			{
				for(int i=0;i<eatRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.FOOD_EATEN, UUID.fromString(uuid), 
							new RowRequirement("foodEaten", eatRules.get(i).Food));
					if (eatRules.get(i).NumberOfEat>n)
						return false;
				}
			}
			if(eggThrownRule!=null)
			{
				n = (Double) api.getSpecificData(PlayerStat.EGGS_THROWN, UUID.fromString(uuid));
				if (eggThrownRule.NumberOfEggs>n)
					return false;
			}
			if(joinRule!=null)
			{
				n = (Double) api.getSpecificData(PlayerStat.JOINS, UUID.fromString(uuid));
				if (joinRule.NumberJoined>n)
					return false;
			}
			if(kickRule!=null)
			{
				n = (Double) api.getSpecificData(PlayerStat.TIMES_KICKED, UUID.fromString(uuid));
				if (kickRule.NumberKicked>n)
					return false;
			}
			if(killRules!=null)
			{
				for(int i=0;i<killRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.KILLS_MOBS, UUID.fromString(uuid), 
							new RowRequirement("mob", killRules.get(i).entityID));
					if (killRules.get(i).numberOfKills>n)
						return false;
				}
			}
			if(playerKillRules!=null)
			{
				for(int i=0;i<playerKillRules.size();i++)
				{
					if(playerKillRules.get(i).name.equals("*"))
					{
						 n = (Double) api.getTotalOf(PlayerStat.KILLS_PLAYERS, UUID.fromString(uuid), null);
							if (playerKillRules.get(i).numberOfKills>n)
								return false;
					}
					else
					{
						n = (Double) api.getSpecificData(PlayerStat.KILLS_PLAYERS, UUID.fromString(uuid), 
								new RowRequirement("playerKilled", playerKillRules.get(i).name));
						if (playerKillRules.get(i).numberOfKills>n)
							return false;
					}
				}
			}
			if(placeRules!=null)
			{
				for(int i=0;i<placeRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.BLOCKS_PLACED, UUID.fromString(uuid), 
							new RowRequirement("typeid", String.valueOf(placeRules.get(i).blockID)),
							new RowRequirement("datavalue", String.valueOf(placeRules.get(i).metadata)));
					if (placeRules.get(i).numberOfPlace>n)
						return false;
				}
			}
			if(playTimeRule!=null)
			{
				n = (Double) api.getSpecificData(PlayerStat.TIME_PLAYED, UUID.fromString(uuid));
				if (playTimeRule.Time>n)
					return false;
			}
			if(toolBreakRules!=null)
			{
				for(int i=0;i<toolBreakRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.TOOLS_BROKEN, UUID.fromString(uuid), 
							new RowRequirement("item", toolBreakRules.get(i).itemID));
					if (toolBreakRules.get(i).NumberOfBreaks>n)
						return false;
				}
			}
			if(travelRules!=null)
			{
				for(int i=0;i<travelRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, UUID.fromString(uuid), 
							new RowRequirement("moveType", travelRules.get(i).MovementType));
					if (travelRules.get(i).Distance>n)
						return false;
				}
			}
			if(villagerTradeRules!=null)
			{
				for(int i=0;i<villagerTradeRules.size();i++)
				{
					n = (Double) api.getSpecificData(PlayerStat.BLOCKS_BROKEN, UUID.fromString(uuid), 
							new RowRequirement("trade", villagerTradeRules.get(i).itemID));
					if (villagerTradeRules.get(i).Number>n)
						return false;
				}
			}
			if(xpRule!=null)
			{
				n = (Double) api.getSpecificData(PlayerStat.XP_GAINED, UUID.fromString(uuid));
				if (xpRule.numberOfXP>n)
					return false;
			}
			
			if (earned < earn)
				return false;
			
			return true;
		}
		catch (Exception ex) {
			
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public List<String> getCurrentStatus(API api,String uuid,int earned)
	{
		List<String> messages = new ArrayList<String>();
		Double n;
		ChatColor base, base1,data;
		base = ChatColor.DARK_AQUA;
		base1 = ChatColor.AQUA;
		if(arrowRules!=null)
		{
			messages.add(base1+"Выпущено стрел:");
			for(int i=0;i<arrowRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.ARROWS_SHOT, UUID.fromString(uuid),new RowRequirement("forceShot", String.valueOf(arrowRules.get(i).Power)));
				if(n==null) n=0.0;
				if (arrowRules.get(i).Shots>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+"Power: " + arrowRules.get(i).Power*100+"%" + " " + data + n.intValue() + "/" + arrowRules.get(i).Shots + base + "; ");
			}
		}
		if(breakRules!=null)
		{
			messages.add(base1+"Сломано блоков:");
			for(int i=0;i<breakRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.BLOCKS_BROKEN, UUID.fromString(uuid), 
						new RowRequirement("typeid", String.valueOf(breakRules.get(i).blockID)),
						new RowRequirement("datavalue", String.valueOf(breakRules.get(i).metadata)));
				if(n==null) n=0.0;
				if (breakRules.get(i).numberOfBreaks>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base + "" + Material.getMaterial(breakRules.get(i).blockID)+":"+breakRules.get(i).metadata + " " + data + n.intValue()+ "/" + breakRules.get(i).numberOfBreaks  + base + "; ");
			}
		}
		if(bucketEmptyRule!=null)
		{
			messages.add(base1+"Опустошено ведер:");
				n = (Double) api.getSpecificData(PlayerStat.BUCKETS_EMPTIED, UUID.fromString(uuid));
				if(n==null) n=0.0;
				if (bucketEmptyRule.numberOfEmpty>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				
				messages.add(data+ String.valueOf(n.intValue()) + "/" + bucketEmptyRule.numberOfEmpty + base + "; ");
		}
		if(bucketFillRule!=null)
		{
			messages.add(base1+"Наполнено ведер:");
			n = (Double) api.getSpecificData(PlayerStat.BUCKETS_FILLED, UUID.fromString(uuid));
			if(n==null) n=0.0;
			if (bucketFillRule.numberOfFill>n)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data+ String.valueOf(n.intValue()) + "/" +  bucketFillRule.numberOfFill + base + "; ");
		}
		if(craftRules!=null)
		{
			messages.add(base1+"Скрафчено:");
			for(int i=0;i<craftRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.ITEMS_CRAFTED, UUID.fromString(uuid), 
						new RowRequirement("item", craftRules.get(i).itemID));
				if(n==null) n=0.0;
				if (craftRules.get(i).numberOfCrafts>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+ Material.getMaterial(craftRules.get(i).itemID) + " "+ data + n.intValue() + "/" + craftRules.get(i).numberOfCrafts + base + "; ");
			}
		}
		if(damageTakeRules!=null)
		{
			messages.add(base1+"Получено урона:");
			for(int i=0;i<damageTakeRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.DAMAGE_TAKEN, UUID.fromString(uuid), 
						new RowRequirement("cause", damageTakeRules.get(i).DamageType));
				if(n==null) n=0.0;
				if (damageTakeRules.get(i).amount>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+   damageTakeRules.get(i).DamageType + " " + data +n.intValue()  + "/" + damageTakeRules.get(i).amount + base + "; ");
			}
		}
		if(deathRule!=null)
		{
			messages.add(base1+"Вы умерли: ");
			n = (Double) api.getSpecificData(PlayerStat.DEATHS, UUID.fromString(uuid));
			if(n==null) n=0.0;
			if (deathRule.deaths>n)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data+ String.valueOf(n.intValue()) + "/" + deathRule.deaths + base + "; ");
		}
		if(eatRules!=null)
		{
			messages.add(base1+"Съедено:");
			for(int i=0;i<eatRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.FOOD_EATEN, UUID.fromString(uuid), 
						new RowRequirement("foodEaten", eatRules.get(i).Food));
				if(n==null) n=0.0;
				if (eatRules.get(i).NumberOfEat>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+  eatRules.get(i).Food + " " + data +n.intValue()  + "/" + eatRules.get(i).NumberOfEat + base + "; ");
			}
		}
		if(eggThrownRule!=null)
		{
			messages.add(base1+"Брошено яиц:");
			n = (Double) api.getSpecificData(PlayerStat.EGGS_THROWN, UUID.fromString(uuid));
			if(n==null) n=0.0;
			if (eggThrownRule.NumberOfEggs>n)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data+ String.valueOf(n.intValue()) + "/" + eggThrownRule.NumberOfEggs + base + "; ");
		}
		if(joinRule!=null)
		{
			messages.add(base1+"Зашли на сервер:");
			n = (Double) api.getSpecificData(PlayerStat.JOINS, UUID.fromString(uuid));
			if(n==null) n=0.0;
			if (joinRule.NumberJoined>n)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data+ String.valueOf(n.intValue()) + "/" + joinRule.NumberJoined + base + "; ");
		}
		if(kickRule!=null)
		{
			messages.add(base1+"Были кикнуты:");
			n = (Double) api.getSpecificData(PlayerStat.TIMES_KICKED, UUID.fromString(uuid));
			if(n==null) n=0.0;
			if (kickRule.NumberKicked>n)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data+ String.valueOf(n.intValue()) + "/" + kickRule.NumberKicked + base + "; ");
		}
		if(killRules!=null)
		{
			messages.add(base1+"Убито:");
			for(int i=0;i<killRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.KILLS_MOBS, UUID.fromString(uuid), 
						new RowRequirement("mob", killRules.get(i).entityID));
				if(n==null) n=0.0;
				if (killRules.get(i).numberOfKills>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+  killRules.get(i).entityID + " " + data +n.intValue()  + "/" + killRules.get(i).numberOfKills + base + "; ");
			}
		}
		if(playerKillRules!=null)
		{
			messages.add(base1+"Убито игроков:");
			for(int i=0;i<playerKillRules.size();i++)
			{
				if (playerKillRules.get(i).name.equals("*"))
				{
					n = (Double) api.getTotalOf(PlayerStat.KILLS_PLAYERS, UUID.fromString(uuid), null);
					if(n==null) n=0.0;
					if (playerKillRules.get(i).numberOfKills>n)
						data = ChatColor.YELLOW;
					else data = ChatColor.DARK_GREEN;
					messages.add(base+""+  "ALL" + " " + data +n.intValue()  + "/" + playerKillRules.get(i).numberOfKills + base + "; ");
				}
				else
				{
					n = (Double) api.getSpecificData(PlayerStat.KILLS_PLAYERS, UUID.fromString(uuid), 
							new RowRequirement("mob", playerKillRules.get(i).name));
					if(n==null) n=0.0;
					if (playerKillRules.get(i).numberOfKills>n)
						data = ChatColor.YELLOW;
					else data = ChatColor.DARK_GREEN;
					messages.add(base+""+  playerKillRules.get(i).name + " " + data +n.intValue()  + "/" + playerKillRules.get(i).numberOfKills + base + "; ");
				}
			}
		}
		if(placeRules!=null)
		{
			messages.add(base1+"Поставлено блоков:");
			for(int i=0;i<placeRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.BLOCKS_PLACED, UUID.fromString(uuid), 
						new RowRequirement("typeid", String.valueOf(placeRules.get(i).blockID)),
						new RowRequirement("datavalue", String.valueOf(placeRules.get(i).metadata)));
				if(n==null) n=0.0;
				if (placeRules.get(i).numberOfPlace>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+  Material.getMaterial(placeRules.get(i).blockID)+":"+placeRules.get(i).metadata + " " + data + n.intValue() +  "/" +placeRules.get(i).numberOfPlace +  base + "; ");
				}
		}
		if(playTimeRule!=null)
		{
			messages.add(base1+"Время игры:");
			n = (Double) api.getSpecificData(PlayerStat.TIME_PLAYED, UUID.fromString(uuid));
			if(n==null) n=0.0;
			if (playTimeRule.Time>n)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data+ String.valueOf(n.intValue()) + "/" + playTimeRule.Time + base + "; ");
		}
		if(toolBreakRules!=null)
		{
			messages.add(base1+"Сломано инструментов:");
			for(int i=0;i<toolBreakRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.TOOLS_BROKEN, UUID.fromString(uuid), 
						new RowRequirement("item", toolBreakRules.get(i).itemID));
				if(n==null) n=0.0;
				if (toolBreakRules.get(i).NumberOfBreaks>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+  toolBreakRules.get(i).itemID + " " + data + n.intValue() + "/" + toolBreakRules.get(i).NumberOfBreaks + base + "; ");
			}
		}
		if(travelRules!=null)
		{
			messages.add(base1+"Пройдено пути:");
			for(int i=0;i<travelRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, UUID.fromString(uuid), 
						new RowRequirement("moveType", travelRules.get(i).MovementType));
				if(n==null) n=0.0;
				if (travelRules.get(i).Distance>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+  travelRules.get(i).MovementType + " " + data +n.intValue()  + "/" + travelRules.get(i).Distance + base + "; ");
			}
		}
		if(villagerTradeRules!=null)
		{
			messages.add(base1+"Проведено торгов с жителями:");
			for(int i=0;i<villagerTradeRules.size();i++)
			{
				n = (Double) api.getSpecificData(PlayerStat.BLOCKS_BROKEN, UUID.fromString(uuid), 
						new RowRequirement("trade", villagerTradeRules.get(i).itemID));
				if(n==null) n=0.0;
				if (villagerTradeRules.get(i).Number>n)
					data = ChatColor.YELLOW;
				else data = ChatColor.DARK_GREEN;
				messages.add(base+""+  villagerTradeRules.get(i).itemID + " " + data +n.intValue() + "/" + villagerTradeRules.get(i).Number  + base + "; ");
			}
		}
		if(xpRule!=null)
		{
			messages.add(base1+"Получено опыта:");
			n = (Double) api.getSpecificData(PlayerStat.XP_GAINED, UUID.fromString(uuid));
			if(n==null) n=0.0;
			if (xpRule.numberOfXP>n)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data+ String.valueOf(n.intValue()) + "/" + xpRule.numberOfXP + base + "; ");
		}
		if(earn!=0)
		{
			messages.add(base1+"Заработано $: ");
			
			if(earned<earn)
				data = ChatColor.YELLOW;
			else data = ChatColor.DARK_GREEN;
			messages.add(data + String.valueOf(earned) + "/" + earn + base +"; ");
		}
		
		return messages;
	}
}
