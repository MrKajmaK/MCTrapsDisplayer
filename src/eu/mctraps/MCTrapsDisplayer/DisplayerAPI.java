package eu.mctraps.MCTrapsDisplayer;

import eu.mctraps.MCTrapsDisplayer.commands.ActionBar;
import eu.mctraps.MCTrapsDisplayer.commands.Title;
import org.bukkit.entity.Player;

public class DisplayerAPI {
    public static void ActionBar(Player player, String message) {
        ActionBar ab = new ActionBar(message);
        ab.send(player);
    }
    public static void ActionBar(String message) {
        ActionBar ab = new ActionBar(message);
        ab.sendAll();
    }

    public static void Title(Player player, String title, String subtitle, int duration) {
        Title t = new Title(title, subtitle, duration);
        t.send(player);
    }
    public static void Title(Player player, String msg, int duration, Boolean subtitle) {
        if(!subtitle) {
            Title t = new Title(msg, null, duration);
            t.send(player);
        } else {
            Title t = new Title(null, msg, duration);
            t.send(player);
        }
    }
    public static void Title(String title, String subtitle, int duration) {
        Title t = new Title(title, subtitle, duration);
        t.sendAll();
    }
    public static void Title(String msg, int duration, Boolean subtitle) {
        if(!subtitle) {
            Title t = new Title(msg, null, duration);
            t.sendAll();
        } else {
            Title t = new Title(null, msg, duration);
            t.sendAll();
        }
    }
    public static void Title(Player player, String title, String subtitle) {
        Title t = new Title(title, subtitle, new MCTrapsDisplayer().config.getInt("titles.defaultDuration"));
        t.send(player);
    }
    public static void Title(Player player, String msg, Boolean subtitle) {
        if(!subtitle) {
            Title t = new Title(msg, null, new MCTrapsDisplayer().config.getInt("titles.defaultDuration"));
            t.send(player);
        } else {
            Title t = new Title(null, msg, new MCTrapsDisplayer().config.getInt("titles.defaultDuration"));
            t.send(player);
        }
    }
    public static void Title(String title, String subtitle) {
        Title t = new Title(title, subtitle, new MCTrapsDisplayer().config.getInt("titles.defaultDuration"));
        t.sendAll();
    }
    public static void Title(String msg, Boolean subtitle) {
        if(!subtitle) {
            Title t = new Title(msg, null, new MCTrapsDisplayer().config.getInt("titles.defaultDuration"));
            t.sendAll();
        } else {
            Title t = new Title(null, msg, new MCTrapsDisplayer().config.getInt("titles.defaultDuration"));
            t.sendAll();
        }
    }
}
