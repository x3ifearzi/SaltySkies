package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.configs.CustomConfigMessager;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TimeCommand {

  final private SaltySkies plugin;

  public TimeCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

  @Command(name = "day", description = "§6Ändere die Uhrzeit zum Tag.", usage = "§6/day", permission = "SaltySkies.day")
  public void timeToDay ( CommandArgs args ) {

    Player player = args.getPlayer();

    World world = Bukkit.getServer().getWorld( player.getWorld().getName() );

    Objects.requireNonNull(world)
           .setTime( 1000 );

    player.sendMessage(plugin.getMsgDE().getMessageSuccessDE( "time-command", "day" ));
  }

  @Command(name = "night", description = "§6Ändere die Uhrzeit zur Nacht", usage = "§6/night", permission = "SaltySkies.night")
  public void timeToNight ( CommandArgs args ) {

    Player player = args.getPlayer();

    World world = Bukkit.getServer().getWorld( player.getWorld().getName() );

    Objects.requireNonNull(world)
           .setTime( 13000 );

    player.sendMessage(plugin.getMsgDE().getMessageSuccessDE( "time-command", "night" ));
  }


}
