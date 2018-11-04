package HCF.factions;

import HCF.HCF;
import HCF.claim.Claim;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
			Player player = (Player) sender;
			if(args.length == 0 || args[0] == null) {
				player.sendMessage(HCF.colorize(""));
				player.sendMessage(HCF.colorize("&e/f create <name> &7- Creates a faction!"));
				player.sendMessage(HCF.colorize("&e/f invite <player> &7- invites a player to your faction!"));
				player.sendMessage(HCF.colorize("&e/f accept &7- accepts a faction invite!"));
				player.sendMessage(HCF.colorize("&e/f kick <player> &7- kicks a player from your faction!"));
				player.sendMessage(HCF.colorize("&e/f list &7- List online factions!"));
				player.sendMessage(HCF.colorize("&e/f top &7- shows the top 5 factions"));
				player.sendMessage(HCF.colorize("&e/f who <faction name> &7- shows info about the faction"));
				player.sendMessage(HCF.colorize(""));
				return true;
			}

			/* Testing claim functionality */
			if (args[0].equalsIgnoreCase("claim")) {

				Location first = player.getLocation().add(10, 0, 10);
				Location second = player.getLocation().subtract(10, 0, 10);

				HCF plugin = HCF.getInstance();

				Claim claim = new Claim(player.getDisplayName(), first, second);
				claim.save(plugin.getConfig());
				plugin.saveConfig();


			}
			
			if(args[0].equalsIgnoreCase("who")) {
				
				if(args[1] == null) {
					player.sendMessage(HCF.colorize("&c/f whois <Player>"));
					return true;
				}
				
				
				
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				if(args.length <= 1) {
					player.sendMessage(HCF.colorize("&c/f create <name>"));
					return true;
				}
				if(FactionManager.isInFaction(player) == true) {
					player.sendMessage(HCF.colorize("&cYou must leave your faction before you can make one!"));
					return true;
				}else {
					if(plugin.getConfig().getString(player.getName()) != null) {
						plugin.getConfig().set(args[1] + ".Leader", player.getName());
						plugin.getConfig().set(args[1] + ".members", player.getName());
						plugin.getConfig().set(player.getName() + ".faction", args[1]);
						plugin.saveConfig();
						player.sendMessage(HCF.colorize("&eFaction was created!"));
						return true;
					}else {
						player.sendMessage(HCF.colorize("&cPlease relog because we couldnt find any data on you!"));
						return true;
					}
				}
			}
			
			if(args[0].equalsIgnoreCase("invite")) {
				if(FactionManager.isLeader(player) == true) {
					Player t = (Player) Bukkit.getPlayer(args[1]);
					if(t == null) {
						player.sendMessage(HCF.colorize("&cThat player is not online!"));
						return true;
					}
					if(FactionManager.isInFaction(t) == true) {
						player.sendMessage(HCF.colorize("&cThat Player is in a faction!"));
						return true;
					}else {
						if(FactionManager.getFaction(player) == null) {
							player.sendMessage(HCF.colorize("&cYou must be in a faction to do this command!"));
							return true;
						}
						FactionManager.waitingtojoin.put(t, FactionManager.getFaction(player));
						player.sendMessage(HCF.colorize("&eYou invited &c" + t.getName() + "&e to your faction!"));
						t.sendMessage(HCF.colorize("&eYou invited to " + FactionManager.getFaction(player) + " &eto accept do &c/f accept"));
						return true;
					}
				}else {
					player.sendMessage(HCF.colorize("&cYou must be the leader of the faction to do this!"));
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("accept")) {
				if(FactionManager.waitingtojoin.containsKey(player)) {
					FactionManager.setFaction(player, FactionManager.waitingtojoin.get(player));
					player.sendMessage(HCF.colorize("&eYou have joint &c" + FactionManager.waitingtojoin.get(player)));
					FactionManager.waitingtojoin.remove(player);
					return true;
				}else {
					player.sendMessage(HCF.colorize("&cYou was not invited to a faction!"));
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("kick")) {
				if(FactionManager.isLeader(player) == true) {
					Player t = (Player) Bukkit.getPlayer(args[1]);
					if(FactionManager.getFaction(t).equals(FactionManager.getFaction(player))){
						
						FactionManager.setFaction(t, "none");
						player.sendMessage(HCF.colorize("&eYou kicked &c" + t.getName() + " &eout of your faction!"));
						t.sendMessage(HCF.colorize("&cYou was kicked from " + FactionManager.getFaction(player)));
						return true;
					}else {
						player.sendMessage(HCF.colorize("&CThis player is not in your faction!"));
						return true;
					}
				}else{
					player.sendMessage(HCF.colorize("&cYou must be the owner of this faction to kick a player"));
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
