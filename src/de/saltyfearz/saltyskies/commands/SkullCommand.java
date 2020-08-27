package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import de.saltyfearz.saltyskies.skull.SkullCreator;
import org.bukkit.entity.Player;

public class SkullCommand {

  final private SaltySkies plugin;

  public SkullCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

  @Command(name = "skull", aliases = {"head", "kopf"}, description = "§6Gebe dir einen beliebigen Kopf.", usage = "§6/skull [<text>]")
  public void createSkull(CommandArgs args) {

    Player player = args.getPlayer();

    String[] arg = args.getArgs();

    if (player.getInventory()
              .firstEmpty() == -1) {
      player.sendMessage(plugin.getMsgDE().getMessageInfoDE( "skull-command", "notEnoughSpaceInv" ));
      return;
    }

      player.getInventory().setItemInMainHand ( SkullCreator.itemWithName ( SkullCreator.createSkull(), arg[0] ));

    player.sendMessage(plugin.getMsgDE().getMessageSuccessDE("skull-command", "successGaveSkull"));
  }
}
