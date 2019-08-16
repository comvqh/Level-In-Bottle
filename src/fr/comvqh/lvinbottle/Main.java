package fr.comvqh.lvinbottle;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.comvqh.lvinbottle.commands.Commands;
import fr.comvqh.lvinbottle.listeners.BottleXPListener;

public class Main extends JavaPlugin {
	
	public void onEnable()
	{
		getLogger().info("Level-In-Bottle V1.0 - Enabled");
		
	    CommandExecutor commandsExecutor = new Commands();
		getCommand("bottlexp").setExecutor(commandsExecutor);
		
		Listener l = new BottleXPListener();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(l, this);
	}
	
	public void onDisable() {
		getLogger().info("Level-In-Bottle V1.0 - Disabled");
	}
	
}
