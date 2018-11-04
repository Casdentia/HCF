package HCF;

import HCF.factions.FactionsCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HCF extends JavaPlugin implements Listener {

	private static HCF instance;

	@Override
	public void onEnable() {

		instance = this;
		
		Bukkit.getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);

		saveDefaultConfig();

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
	
	public static HCF getInstance() {
		return instance;
	}
	
	public static String colorize(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}
