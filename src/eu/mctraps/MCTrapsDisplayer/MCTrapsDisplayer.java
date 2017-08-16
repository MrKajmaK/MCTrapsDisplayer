package eu.mctraps.MCTrapsDisplayer;

import eu.mctraps.MCTrapsDisplayer.listeners.PlayerJoinListener;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MCTrapsDisplayer extends JavaPlugin {
    public FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("MCTrapsDisplayer has been enabled");

        getDataFolder().mkdir();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        config = getConfig();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        getCommand("displayer").setExecutor(new Commands(this));
        getCommand("bar").setExecutor(new Commands(this));
        getCommand("titled").setExecutor(new Commands(this));
        getCommand("bc").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("MCTrapsDisplayer has been disabled");
    }

    public static String colorify(String s) {
        if(s != null) {
            return ChatColor.translateAlternateColorCodes('&', s);
        }

        return null;
    }
}
