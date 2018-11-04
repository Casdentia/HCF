package HCF.factions;

import HCF.HCF;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				p.sendMessage(HCF.colorize(""));
				p.sendMessage(HCF.colorize("&e/f create <name> &7- Creates a faction!"));
				p.sendMessage(HCF.colorize("&e/f invite <player> &7- invites a player to your faction!"));
				p.sendMessage(HCF.colorize("&e/f accept &7- accepts a faction invite!"));
				p.sendMessage(HCF.colorize("&e/f kick <player> &7- kicks a player from your faction!"));
				p.sendMessage(HCF.colorize("&e/f list &7- List online factions!"));
				p.sendMessage(HCF.colorize("&e/f top &7- shows the top 5 factions"));
				p.sendMessage(HCF.colorize("&e/f who <faction name> &7- shows info about the faction"));
				p.sendMessage(HCF.colorize(""));
				return true;
			}
			
			
			
			if(args[0].equalsIgnoreCase("who")) {
				
				if(args[1] == null) {
					p.sendMessage(HCF.colorize("&c/f whois <Player>"));
					return true;
				}
				
				
				
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				if(args.length <= 1) {
					p.sendMessage(HCF.colorize("&c/f create <name>"));
					return true;
				}
				if(FactionManager.isInFaction(p) == true) {
					p.sendMessage(HCF.colorize("&cYou must leave your faction before you can make one!"));
					return true;
				}else {
					if(plugin.getConfig().getString(p.getName()) != null) {
						plugin.getConfig().set(args[1] + ".Leader", p.getName());
						plugin.getConfig().set(args[1] + ".members", p.getName());
						plugin.getConfig().set(p.getName() + ".faction", args[1]);
						plugin.saveConfig();
						p.sendMessage(HCF.colorize("&eFaction was created!"));
						return true;
					}else {
						p.sendMessage(HCF.colorize("&cPlease relog because we couldnt find any data on you!"));
						return true;
					}
				}
			}
			
			if(args[0].equalsIgnoreCase("invite")) {
				if(FactionManager.isLeader(p) == true) {
					Player t = (Player) Bukkit.getPlayer(args[1]);
					if(t == null) {
						p.sendMessage(HCF.colorize("&cThat player is not online!"));
						return true;
					}
					if(FactionManager.isInFaction(t) == true) {
						p.sendMessage(HCF.colorize("&cThat Player is in a faction!"));
						return true;
					}else {
						if(FactionManager.getFaction(p) == null) {
							p.sendMessage(HCF.colorize("&cYou must be in a faction to do this command!"));
							return true;
						}
						FactionManager.waitingtojoin.put(t, FactionManager.getFaction(p));
						p.sendMessage(HCF.colorize("&eYou invited &c" + t.getName() + "&e to your faction!"));
						t.sendMessage(HCF.colorize("&eYou invited to " + FactionManager.getFaction(p) + " &eto accept do &c/f accept"));
						return true;
					}
				}else {
					p.sendMessage(HCF.colorize("&cYou must be the leader of the faction to do this!"));
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("accept")) {
				if(FactionManager.waitingtojoin.containsKey(p)) {
					FactionManager.setFaction(p, FactionManager.waitingtojoin.get(p));
					p.sendMessage(HCF.colorize("&eYou have joint &c" + FactionManager.waitingtojoin.get(p)));
					FactionManager.waitingtojoin.remove(p);
					return true;
				}else {
					p.sendMessage(HCF.colorize("&cYou was not invited to a faction!"));
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("kick")) {
				if(FactionManager.isLeader(p) == true) {
					Player t = (Player) Bukkit.getPlayer(args[1]);
					if(FactionManager.getFaction(t).equals(FactionManager.getFaction(p))){
						
						FactionManager.setFaction(t, "none");
						p.sendMessage(HCF.colorize("&eYou kicked &c" + t.getName() + " &eout of your faction!"));
						t.sendMessage(HCF.colorize("&cYou was kicked from " + FactionManager.getFaction(p)));
						return true;
					}else {
						p.sendMessage(HCF.colorize("&CThis player is not in your faction!"));
						return true;
					}
				}else{
					p.sendMessage(HCF.colorize("&cYou must be the owner of this faction to kick a player"));
					return true;
				}	
			}
			
			
		}else {
			sender.sendMessage(HCF.colorize("&cOnly players can run faction commands"));
			return true;
		}
			
		return false;
	}

}
