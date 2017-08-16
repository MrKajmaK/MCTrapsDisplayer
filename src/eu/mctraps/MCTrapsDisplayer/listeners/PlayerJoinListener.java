package eu.mctraps.MCTrapsDisplayer.listeners;

import eu.mctraps.MCTrapsDisplayer.MCTrapsDisplayer;
import eu.mctraps.MCTrapsDisplayer.commands.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final MCTrapsDisplayer plugin;

    public PlayerJoinListener(MCTrapsDisplayer plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(plugin.config.getBoolean("titles.onJoin.display")) {
            Title title = new Title(plugin.config.getString("titles.onJoin.title"), plugin.config.getString("titles.onJoin.subtitle"), plugin.config.getInt("titles.defaultDuration"), plugin);
            title.send(e.getPlayer());
        }
    }
}
