package HCF.factions;

import HCF.HCF;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class FactionManager {

	static boolean isLeader;
	static boolean infaction;
	public static HashMap<Player, String> waitingtojoin = new HashMap<>();
	
	public static boolean isInFaction(Player p) {
		if(HCF.getInstance().getConfig().get(p.getName() + ".faction") == null || HCF.getInstance().getConfig().get(p.getName() + ".faction").equals("none")) {
			infaction = false;
		}else {
			infaction = true;
		}
		return infaction;
	}
	
	public static boolean isLeader(Player p) {
		if(HCF.getInstance().getConfig().get(HCF.getInstance().getConfig().get(p.getName()) + ".Leader").equals(p.getName())) {
			isLeader = true;
		}else {
			isLeader = false;
		}
		return isLeader;
	}
	
	public static void setFaction(Player p, String fname) {
		ArrayList<String> members = new ArrayList<>();
		members.add(HCF.getInstance().getConfig().getString(fname + ".members"));
		members.add(p.getName());
		HCF.getInstance().getConfig().set(fname + ".members", members);
		HCF.getInstance().saveConfig();
		members.removeAll(members);
	}

	public static String getFaction(Player p) {
		if(HCF.getInstance().getConfig().get(p.getName() + ".faction") != null) {
			return HCF.getInstance().getConfig().getString(p.getName() + ".faction");
		}
		return null;
	}
	
}
