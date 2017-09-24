package eu.mctraps.MCTrapsDisplayer.commands;

import eu.mctraps.MCTrapsDisplayer.MCTrapsDisplayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static eu.mctraps.MCTrapsDisplayer.MCTrapsDisplayer.colorify;

public class ActionBar {
    Player p;
    IChatBaseComponent icbc;
    PacketPlayOutChat bar;

    public ActionBar(String message) {
        this.icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colorify(message).replace('_', ' ') + "\"}");
        this.bar = new PacketPlayOutChat(icbc, (byte)2);
    }

    public void send(Player p) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }

    public void send(Player... players) {
        Player[] pl = players;

        for(int i = 0; i < pl.length; i++) {
            ((CraftPlayer)pl[i]).getHandle().playerConnection.sendPacket(bar);
        }
    }

    public void sendAll() {
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
        }
    }
}
