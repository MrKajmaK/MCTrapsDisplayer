package eu.mctraps.MCTrapsDisplayer;

import eu.mctraps.MCTrapsDisplayer.commands.ActionBar;
import eu.mctraps.MCTrapsDisplayer.commands.Title;
import org.bukkit.entity.Player;

public class DisplayerAPI {
    public static void ActionBar(Player player, String message) {
        ActionBar ab = new ActionBar(message, new MCTrapsDisplayer());
        ab.send(player);
    }
    public static void ActionBar(String message) {
        ActionBar ab = new ActionBar(message, new MCTrapsDisplayer());
        ab.sendAll();
    }

    public static void Title(Player player, String title, String subtitle, int duration) {
        Title t = new Title(title, subtitle, duration, new MCTrapsDisplayer());
        t.send(player);
    }
    public static void Title(Player player, String msg, int duration, Boolean subtitle) {
        if(!subtitle) {
            Title t = new Title(msg, null, duration, new MCTrapsDisplayer());
            t.send(player);
        } else {
            Title t = new Title(null, msg, duration, new MCTrapsDisplayer());
            t.send(player);
        }
    }
    public static void Title(String title, String subtitle, int duration) {
        Title t = new Title(title, subtitle, duration, new MCTrapsDisplayer());
        t.sendAll();
    }
    public static void Title(String msg, int duration, Boolean subtitle) {
        if(!subtitle) {
            Title t = new Title(msg, null, duration, new MCTrapsDisplayer());
            t.sendAll();
        } else {
            Title t = new Title(null, msg, duration, new MCTrapsDisplayer());
            t.sendAll();
        }
    }
}
