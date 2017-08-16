package eu.mctraps.MCTrapsDisplayer;

import eu.mctraps.MCTrapsDisplayer.commands.ActionBar;
import eu.mctraps.MCTrapsDisplayer.commands.Title;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static eu.mctraps.MCTrapsDisplayer.MCTrapsDisplayer.colorify;

public class Commands implements CommandExecutor {
    private final MCTrapsDisplayer plugin;

    public Commands(MCTrapsDisplayer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("displayer")) {
            if (sender.hasPermission("tools.displayer.admin")) {
                if (args.length == 0) {
                    sender.sendMessage("§3§l»§3§m----------§3§l« §9Komendy §3§l»§3§m----------§3§l«");
                    sender.sendMessage(" §3§l» §7/bar");
                    sender.sendMessage(" §3§l» §7/titled");
                    sender.sendMessage("§3§l»§3§m----------§3§l« §9Plugin MCTrapsDisplayer v1.0.0 by §6KajmaczeK §3§l»§3§m----------§3§l«");
                    return true;
                } else {
                    sender.sendMessage("§cPoprawne uzycie: §7/" + label + "");
                }
            } else {
                sender.sendMessage("§4Blad: Nie masz uprawnien :(");
            }
        } else if(cmd.getName().equalsIgnoreCase("bar")) {
            if (sender.hasPermission("tools.displayer.bar")) {
                if (args.length == 1) {
                    if (plugin.getServer().getPlayer(args[0]) == null) {
                        if (sender instanceof Player) {
                            ActionBar bar = new ActionBar(args[0], plugin);
                            bar.send((Player)sender);
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[0]).replace('_', ' '));
                        } else {
                            sender.sendMessage("§4Blad: §cmusisz byc graczem aby wykonac ta komende");
                        }
                    } else {
                        sender.sendMessage("§cPoprawne uzycie: §7/" + label + " [gracz] <wiadomosc>");
                    }
                } else if (args.length == 2) {
                    if(args[0].equalsIgnoreCase("all")) {
                        ActionBar bar = new ActionBar(args[1], plugin);
                        bar.sendAll();
                        sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[1]).replace('_', ' ') + "§2 do wszystkich graczy");
                    } else if (plugin.getServer().getPlayer(args[0]) != null) {
                        ActionBar bar = new ActionBar(args[1], plugin);
                        bar.send(plugin.getServer().getPlayer(args[0]));
                        sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[1]).replace('_', ' ') + "§2 do " + plugin.getServer().getPlayer(args[0]).getDisplayName());
                    } else {
                        sender.sendMessage("§4Blad: §cpodany gracz musi byc online");
                    }
                } else if (args.length == 0) {
                    sender.sendMessage("§7/bar");
                    sender.sendMessage(" §3§l» §7/bar §c<wiadomosc> §7- pokaz wiadomosc sobie");
                    sender.sendMessage(" §3§l» §7/bar §c<gracz> <wiadomosc> §7- pokaz wiadomosc danemu graczowi");
                    sender.sendMessage(" §3§l» §7/bar §6all §c<wiadomosc> §7- pokaz wiadomosc wszystkim graczom");
                } else {
                    sender.sendMessage("§cPoprawne uzycie: §7/" + label + " [gracz] <wiadomosc>");
                }
            } else {
                sender.sendMessage("§4Blad: Nie masz uprawnien :(");
            }

            return true;
        } else if(cmd.getName().equalsIgnoreCase("titled")) {
            if(sender.hasPermission("tools.displayer.title")) {
                if(args.length == 1) {
                    if(plugin.getServer().getPlayer(args[0]) == null) {
                        if(sender instanceof Player) {
                            Title title = new Title(args[0], null, 40, plugin);
                            title.send((Player) sender);
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[0]).replace('_', ' '));
                        } else {
                            sender.sendMessage("§4Blad: §cmusisz byc graczem aby wykonac ta komende");
                        }
                    } else {
                        sender.sendMessage("§cPoprawne uzycie: §7/" + label + " [gracz] <wiadomosc> [czas trwania]");
                    }
                } else if(args.length == 2) {
                    if (args[0].equalsIgnoreCase("all")) {
                        Title title = new Title(args[1], null, 40, plugin);
                        title.sendAll();
                        sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[1]).replace('_', ' ') + "§2 do wszystkich graczy");
                    } else if (plugin.getServer().getPlayer(args[0]) != null) {
                        Title title = new Title(args[1], null, 40, plugin);
                        title.send(plugin.getServer().getPlayer(args[0]));
                    } else {
                        if(sender instanceof Player) {
                            Title title = new Title(args[0], args[1], 40, plugin);
                            title.send((Player) sender);
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[0]).replace('_', ' '));
                        } else {
                            sender.sendMessage("§4Blad: §cmusisz byc graczem aby wykonac ta komende");
                        }
                    }
                } else if(args.length == 3) {
                    if(args[0].equalsIgnoreCase("all")) {
                        try {
                            int duration = Integer.parseInt(args[2]);
                            Title title = new Title(args[1], null, duration, plugin);
                            title.sendAll();
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc §4" + colorify(args[1]).replace('_', ' ') + "§2 do wszystkich graczy");
                        } catch (NumberFormatException e) {
                            Title title = new Title(args[1], args[2], 40, plugin);
                            title.sendAll();
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc §4" + colorify(args[1]).replace('_', ' ') + "§2 do wszystkich graczy");
                        }
                    } else if(plugin.getServer().getPlayer(args[0]) != null) {
                        try {
                            int duration = Integer.parseInt(args[2]);
                            Title title = new Title(args[1], null, duration, plugin);
                            title.send(plugin.getServer().getPlayer(args[0]));
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[1]).replace('_', ' ') + "§2 do " + plugin.getServer().getPlayer(args[0]).getDisplayName());
                        } catch (NumberFormatException e) {
                            Title title = new Title(args[1], args[2], 40, plugin);
                            title.send(plugin.getServer().getPlayer(args[0]));
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc §4" + colorify(args[1]).replace('_', ' ') + "§2 do wszystkich graczy");
                        }
                    } else {
                        sender.sendMessage("§4Blad: §cpodany gracz musi byc online");
                    }
                } else if(args.length == 4) {
                    try {
                        if (args[0].equalsIgnoreCase("all")) {
                            Title title = new Title(args[1], args[2], Integer.parseInt(args[3]), plugin);
                            title.sendAll();
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc §4" + colorify(args[1]).replace('_', ' ') + "§2 do wszystkich graczy");
                        } else if (plugin.getServer().getPlayer(args[0]) != null) {
                            Title title = new Title(args[1], args[2], Integer.parseInt(args[3]), plugin);
                            title.send(plugin.getServer().getPlayer(args[0]));
                            sender.sendMessage("§2Pomyslnie wyslano wiadomosc " + colorify(args[1]).replace('_', ' ') + "§2 do " + plugin.getServer().getPlayer(args[0]).getDisplayName());
                        } else {
                            sender.sendMessage("§4Blad: §cpodany gracz musi byc online");
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage("§4Blad: §cczas może zawierać tylko cyfry!");
                    }
                } else if (args.length == 0) {
                    sender.sendMessage("§7/titled");
                    sender.sendMessage(" §3§l» §7/titled §c<title> §a[subtitle] §7- pokaz wiadomosc sobie");
                    sender.sendMessage(" §3§l» §7/titled §a[gracz] §c<title> §a[subtitle] [czas trwania (20 = 1s)] §7- pokaz wiadomosc danemu graczowi");
                    sender.sendMessage(" §3§l» §7/titled §6all §c<title> §a[subtitle] [czas trwania (20 = 1s)] §7- pokaz wiadomosc wszystkim graczom");
                } else {
                    sender.sendMessage("§cPoprawne uzycie: §7/" + label + " [gracz] <title> <subtitle> [czas trwania]");

                }
            } else {
                sender.sendMessage("§4Blad: Nie masz uprawnien :(");
            }

            return true;
        }

        return false;
    }
}
