package HCF.factions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import HCF.HCF;

public class FactionsCommand implements CommandExecutor {
	
	/*TODO
	 * who is command
	 * list command
	 * and finish the claims
	 * 
	 * 
	 */
	
	
	
	private static HCF plugin = HCF.getPlugin(HCF.class);
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0 || args[0] == null) {
				p.sendMessage(HCF.cc(""));
				p.sendMessage(HCF.cc("&e/f create <name> &7- Creates a faction!"));
				p.sendMessage(HCF.cc("&e/f invite <player> &7- invites a player to your faction!"));
				p.sendMessage(HCF.cc("&e/f accept &7- accepts a faction invite!"));
				p.sendMessage(HCF.cc("&e/f kick <player> &7- kicks a player from your faction!"));
				p.sendMessage(HCF.cc("&e/f list &7- List online factions!"));
				p.sendMessage(HCF.cc("&e/f top &7- shows the top 5 factions"));
				p.sendMessage(HCF.cc("&e/f who <faction name> &7- shows info about the faction"));
				p.sendMessage(HCF.cc(""));
				return true;
			}
			
			
			
			if(args[0].equalsIgnoreCase("who")) {
				
				if(args[1] == null) {
					p.sendMessage(HCF.cc("&c/f whois <Player>"));
					return true;
				}
				
				
				
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				if(args.length <= 1) {
					p.sendMessage(HCF.cc("&c/f create <name>"));
					return true;
				}
				if(factionmanager.isinfaction(p) == true) {
					p.sendMessage(HCF.cc("&cYou must leave your faction before you can make one!"));
					return true;
				}else {
					if(plugin.getConfig().getString(p.getName()) != null) {
						plugin.getConfig().set(args[1] + ".Leader", p.getName());
						plugin.getConfig().set(args[1] + ".members", p.getName());
						plugin.getConfig().set(p.getName() + ".faction", args[1]);
						plugin.saveConfig();
						p.sendMessage(HCF.cc("&eFaction was created!"));
						return true;
					}else {
						p.sendMessage(HCF.cc("&cPlease relog because we couldnt find any data on you!"));
						return true;
					}
				}
			}
			
			if(args[0].equalsIgnoreCase("invite")) {
				if(factionmanager.isLeader(p) == true) {
					Player t = (Player) Bukkit.getPlayer(args[1]);
					if(t == null) {
						p.sendMessage(HCF.cc("&cThat player is not online!"));
						return true;
					}
					if(factionmanager.isinfaction(t) == true) {
						p.sendMessage(HCF.cc("&cThat Player is in a faction!"));
						return true;
					}else {
						if(factionmanager.getFaction(p) == null) {
							p.sendMessage(HCF.cc("&cYou must be in a faction to do this command!"));
							return true;
						}
						factionmanager.waitingtojoin.put(t, factionmanager.getFaction(p));
						p.sendMessage(HCF.cc("&eYou invited &c" + t.getName() + "&e to your faction!"));
						t.sendMessage(HCF.cc("&eYou invited to " + factionmanager.getFaction(p) + " &eto accept do &c/f accept"));
						return true;
					}
				}else {
					p.sendMessage(HCF.cc("&cYou must be the leader of the faction to do this!"));
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("accept")) {
				if(factionmanager.waitingtojoin.containsKey(p)) {
					factionmanager.setfaction(p, factionmanager.waitingtojoin.get(p));
					p.sendMessage(HCF.cc("&eYou have joint &c" + factionmanager.waitingtojoin.get(p)));
					factionmanager.waitingtojoin.remove(p);
					return true;
				}else {
					p.sendMessage(HCF.cc("&cYou was not invited to a faction!"));
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("kick")) {
				if(factionmanager.isLeader(p) == true) {
					Player t = (Player) Bukkit.getPlayer(args[1]);
					if(factionmanager.getFaction(t).equals(factionmanager.getFaction(p))){
						
						factionmanager.setfaction(t, "none");
						p.sendMessage(HCF.cc("&eYou kicked &c" + t.getName() + " &eout of your faction!"));
						t.sendMessage(HCF.cc("&cYou was kicked from " + factionmanager.getFaction(p)));
						return true;
					}else {
						p.sendMessage(HCF.cc("&CThis player is not in your faction!"));
						return true;
					}
				}else{
					p.sendMessage(HCF.cc("&cYou must be the owner of this faction to kick a player"));
					return true;
				}	
			}
			
			
		}else {
			sender.sendMessage(HCF.cc("&cOnly players can run faction commands"));
			return true;
		}
			
		return false;
	}

}
