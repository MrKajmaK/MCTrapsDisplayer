package eu.mctraps.MCTrapsDisplayer.commands;

import eu.mctraps.MCTrapsDisplayer.MCTrapsDisplayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static eu.mctraps.MCTrapsDisplayer.MCTrapsDisplayer.colorify;

public class Title {
    MCTrapsDisplayer plugin;

    IChatBaseComponent chatTitle;
    IChatBaseComponent chatSubtitle;

    PacketPlayOutTitle title;
    PacketPlayOutTitle length;
    PacketPlayOutTitle subtitle;
    PacketPlayOutTitle sublength;

    boolean isTitle = false;
    boolean isSubtitle = false;

    public Title(String message, String subtitle, int duration, MCTrapsDisplayer plugin) {
        this.plugin = plugin;

        if(message != null) {
            isTitle = true;
            chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colorify(message).replace('_', ' ') + "\"}");
            title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
            length = new PacketPlayOutTitle(5, duration, 5);
        }

        if(subtitle != null) {
            isSubtitle = true;
            chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colorify(subtitle).replace('_', ' ') + "\"}");
            this.subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
            sublength = new PacketPlayOutTitle(5, duration, 5);
        }
    }

    public void send(Player p) {
        if(isTitle) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
        }
        if(isSubtitle) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(sublength);
        }
    }

    public void send(Player... players) {
        Player[] pl = players;

        if(isTitle) {
            for (int i = 0; i < pl.length; i++) {
                ((CraftPlayer) pl[i]).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) pl[i]).getHandle().playerConnection.sendPacket(length);
            }
        }
        if(isSubtitle) {
            for (int i = 0; i < pl.length; i++) {
                ((CraftPlayer) pl[i]).getHandle().playerConnection.sendPacket(subtitle);
                ((CraftPlayer) pl[i]).getHandle().playerConnection.sendPacket(sublength);
            }
        }
    }

    public void sendAll() {
        if(isTitle) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
            }
        }
        if(isSubtitle) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(sublength);
            }
        }
    }
}
