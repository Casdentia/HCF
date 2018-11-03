package HCF.factions;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import HCF.HCF;

public class factionmanager {
	private static HCF plugin = HCF.getPlugin(HCF.class);
	static boolean isLeader;
	static boolean infaction;
	public static HashMap<Player, String> waitingtojoin = new HashMap<>();
	
	public static boolean isinfaction(Player p) {
		if(plugin.getConfig().get(p.getName() + ".faction") == null || plugin.getConfig().get(p.getName() + ".faction").equals("none")) {
			infaction = false;
		}else {
			infaction = true;
		}
		return infaction;
	}
	
	public static boolean isLeader(Player p) {
		if(plugin.getConfig().get(plugin.getConfig().get(p.getName()) + ".Leader").equals(p.getName())) {
			isLeader = true;
		}else {
			isLeader = false;
		}
		return isLeader;
	}
	
	public static void setfaction(Player p, String fname) {
		ArrayList<String> members = new ArrayList<>();
		members.add(plugin.getConfig().getString(fname + ".members"));
		members.add(p.getName());
		plugin.getConfig().set(fname + ".members", members);
		plugin.saveConfig();
		members.removeAll(members);
	}
	public static String getFaction(Player p) {
		if(plugin.getConfig().get(p.getName() + ".faction") != null) {
			return plugin.getConfig().getString(p.getName() + ".faction");
		}
		return null;
	}
	

	
	
	
}
