package sylcode.levels.rules;

import java.util.List;


public class SpecialRules extends LevelRules
{
	String info;
	public SpecialRules(int level, List<ArrowRule> arrowRules,
			List<BreakRule> breakRules, BucketEmptyRule bucketEmptyRule,
			BucketFillRule bucketFillRule, List<CommandsRule> commandsRules,
			List<CraftRule> craftRules, List<DamageTakeRule> damageTaketRules,
			DeathRule deathRule, List<EatRule> eatRules,
			EggThrowRule eggThrownRule, JoinRule joinRule, KickRule kickRule,
			List<KillRule> killRules,List<PlayerKillRule> playerKillRules,List<PlaceRule> placeRules,
			PlayTimeRule playTimeRule, List<ToolBreakRule> toolBreakRules,
			List<TravelRule> travelRules,
			List<VillagerTradeRule> villagerTradeRules, XPRule xpRule,
			int earn, String[] commands, String info) 
	{
		super(level, arrowRules, breakRules, bucketEmptyRule, bucketFillRule,
				commandsRules, craftRules, damageTaketRules, deathRule, eatRules,
				eggThrownRule, joinRule, kickRule, killRules,playerKillRules, placeRules, playTimeRule,
				toolBreakRules, travelRules, villagerTradeRules, xpRule, earn, commands);
		this.info = info;
		
	}
	
	public String getInfo()
	{
		return this.info;
	}
	
}
