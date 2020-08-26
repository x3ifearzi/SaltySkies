package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import de.saltyfearz.saltyskies.skull.SkullCreator;
import org.bukkit.entity.Player;

public class SkullCommand {

  private static final String TEST_SKULL = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDllY2NjNWMxYzc5YWE3ODI2YTE1YTdmNWYxMmZiNDAzMjgxNTdjNTI0MjE2NGJhMmFlZjQ3ZTVkZTlhNWNmYyJ9fX0=";

  @Command(name = "skull", aliases = {"head", "kopf"}, description = "§6Gebe dir einen beliebigen Kopf.", usage = "§6/skull [<text>]")
  public void createSkull(CommandArgs args) {

    Player player = args.getPlayer();

    String[] arg = args.getArgs();

    if (player.getInventory()
              .firstEmpty() == 0) {
      player.sendMessage(MessageHandlerDE.getMessageInfoDE("skull-command", "notEnoughSpaceInv"));
      return;
    }

    if (arg[0].equals("item")) {
      player.getInventory()
            .setItemInMainHand(
                SkullCreator.itemWithBase64(SkullCreator.createSkull(), TEST_SKULL)
            );
    } else if (arg[0].equals("block")) {
      SkullCreator.blockWithBase64(player.getLocation()
                                         .getBlock(), TEST_SKULL);
    }

    player.sendMessage(MessageHandlerDE.getMessageSuccessDE("skull-command", "successGaveSkull"));
  }
}
