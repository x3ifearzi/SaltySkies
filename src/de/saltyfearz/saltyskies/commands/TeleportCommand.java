package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeleportCommand {

    final private SaltySkies plugin;

    public TeleportCommand( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "tpa", description = "§6Stelle eine Teleportierungsanfrage an andere Spieler.", usage = "§6/tpa <player>", permission = "SaltySkies.tpa")
    public void teleportToThem( CommandArgs args ) {
        Player player = args.getPlayer();
        String[] arg = args.getArgs();

        if (arg.length == 1) {
            Player target = Bukkit.getServer().getPlayer(arg[0]);
            if (target != null) {
                if (!target.getName().equalsIgnoreCase(player.getName())) {
                    if (requests.containsKey(player)) {
                        requests.get(target).add(player);
                    } else {
                        ArrayList <Player> req = new ArrayList<Player>();
                        req.add(player);
                        requests.put(target, req);
                    }
                    player.sendMessage(DE_lang.prefix + "Teleportanfrage an " + target.getName() + " versendet.");
                    String playerName = player.getName();
                    target.sendMessage(DE_lang.prefix + "" + playerName + " möchte sich zu dir teleportieren.");
                    target.sendMessage(DE_lang.prefix + "§aAnnehmen? /tpa accept " + playerName);
                    target.sendMessage(DE_lang.prefix + "§cAblehnen? /tpa deny " + playerName);
                } else {
                    player.sendMessage(DE_lang.prefix + "Du kannst keine Teleportierungsanfrage an dich selber stellen.");
                }
            } else {
                player.sendMessage(DE_lang.prefix + "Der Spieler " + arg[0] + " konnte nicht gefunden werden.");
            }
        } else if (arg.length == 2) {
            if (arg[0].equalsIgnoreCase("accept") || arg[0].equalsIgnoreCase("deny")) {
                Player target = Bukkit.getServer().getPlayer(arg[1]);
                if (target != null) {
                    if (!target.getName().equals(player.getName())) {
                        if (requests.containsKey(player)) {
                            if (requests.get(player).contains(target)) {
                                requests.get(player).remove(target);
                                if (arg[0].equalsIgnoreCase("accept")) {
                                    target.teleport(player.getLocation());
                                    player.sendMessage(DE_lang.prefix + "Du hast die Teleportierungsanfrage von " + target.getName() + " angenommen.");
                                    target.sendMessage(DE_lang.prefix + "Der Spieler " + player.getName() + " hat deine Teleportierungsanfrage angenommen.");
                                } else if (arg[0].equalsIgnoreCase("deny")) {
                                    player.sendMessage(DE_lang.prefix + "Du hast die Teleportierungsanfrage von " + target.getName() + " abgelehnt.");
                                    target.sendMessage(DE_lang.prefix + "Der Spieler " + player.getName() + " hat deine Teleportierungsanfrage abgelehnt.");
                                }
                            } else {
                                player.sendMessage(DE_lang.prefix + "Der Spieler " + target.getName() + " hat dir keine Teleportierungsanfrage geschickt.");
                            }
                        } else {
                            player.sendMessage(DE_lang.prefix + "Der Spieler " + target.getName() + " hat dir keine Teleportierungsanfrage geschickt.");
                        }
                    } else {
                        player.sendMessage(DE_lang.prefix + "Du kannst keine Teleportierungsanfrage an dich selber stellen.");
                    }
                } else {
                    player.sendMessage(DE_lang.prefix + "Der Spieler " + arg[0] + "existiert nicht.");
                }

            }
        } else {
            player.sendMessage(DE_lang.prefix + "§6-------------------------");
            player.sendMessage(DE_lang.prefix + "§6     Teleportieren?      ");
            player.sendMessage(DE_lang.prefix + "§6  /tpa <Spieler>         ");
            player.sendMessage(DE_lang.prefix + "§6  /tpa accept <Spieler>  ");
            player.sendMessage(DE_lang.prefix + "§6  /tpa deny <Spieler>    ");
            player.sendMessage(DE_lang.prefix + "§6-------------------------");
        }
    }
    @Command(name = "tp",
            aliases = {"SkyBlex:tp", "mstp"},
            description = "Teleportiere dich zu einem anderen Spieler.",
            usage = "/tp",
            permission = "SkyBlex.tp",
            noPerm = "§e§l» §6§lSkyBlex  §8× §7 Du hast keine Rechte diesen Befehl ausführen zu können.")
    public void teleportToThem(CommandArgs args) {
        Player player = args.getPlayer();
        String[] arg = args.getArgs();

        if (arg.length == 1) {
            Player target = Bukkit.getServer().getPlayer(arg[0]);
            if (target != null) {
                if(!target.getName().equals(player.getName())) {
                    player.teleport(target.getLocation());
                    player.sendMessage(DE_lang.prefix + "Du hast dich zum Spieler " + arg[0] + " teleportiert.");
                } else {
                    player.sendMessage(DE_lang.prefix + "Du kannst keine Teleportierungsanfrage an dich selber stellen.");
                }
            } else {
                player.sendMessage(DE_lang.prefix + "Der Spieler " + arg[0] + " konnte nicht gefunden werden.");
            }
        }
    }

    @Command(name = "tphere",
            aliases = {"SkyBlex:tphere", "mstphere"},
            description = "Teleportiere dich zu einem anderen Spieler.",
            usage = "/tphere",
            permission = "SkyBlex.tphere",
            noPerm = "§e§l» §6§lSkyBlex  §8× §7 Du hast keine Rechte diesen Befehl ausführen zu können.")
    public void teleportToMe(CommandArgs args) {
        Player player = args.getPlayer();
        String[] arg = args.getArgs();
        if (arg.length == 1) {
            Player target = Bukkit.getServer().getPlayer(arg[0]);
            if (target != null) {
                if(!target.getName().equals(player.getName())) {
                    target.teleport(player.getLocation());
                    player.sendMessage(DE_lang.prefix + "Du hast den Spieler " + arg[0] + " zu dir teleportiert.");
                    target.sendMessage(DE_lang.prefix + "Du wurdest von " + player.getName() + " zu ihm teleportiert." );
                } else {
                    player.sendMessage(DE_lang.prefix + "Du kannst keine Teleportierungsanfrage an dich selber stellen.");
                }
            }
        }
    }
}
