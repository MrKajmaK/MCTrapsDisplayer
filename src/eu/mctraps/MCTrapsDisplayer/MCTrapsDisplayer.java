package eu.mctraps.MCTrapsDisplayer;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MCTrapsDisplayer extends JavaPlugin {
    public FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("MCTrapsDisplayer has been enabled");

        saveDefaultConfig();
        getDataFolder().mkdir();
        config = getConfig();

//        getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);

        getCommand("displayer").setExecutor(new Commands(this));
        getCommand("bar").setExecutor(new Commands(this));
        getCommand("titled").setExecutor(new Commands(this));
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
