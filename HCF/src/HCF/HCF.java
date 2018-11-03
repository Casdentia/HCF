package HCF;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import HCF.factions.FactionsCommand;

public class HCF extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
		
		Bukkit.getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		getCommand("f").setExecutor(new FactionsCommand());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(ChatColor.GREEN + "[+] " + event.getPlayer().getName());
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage("");
		if(getConfig().get(event.getPlayer().getName()) == null) {
			getConfig().set(event.getPlayer().getName() + ".faction", "none");
			getConfig().set(event.getPlayer().getName() + ".DTR", 2);
			saveConfig();
		}
	}
	
	
	
	public static String cc(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}
