package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ClearInventoryCommand {

    final private SaltySkies plugin;

    public ClearInventoryCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "clearinventory", aliases = {"ci", "cinv"}, description = "§6Leere dein Inventar.", usage = "§6/clearinventory [<player>]", permission = "SaltySkies.clearinventory")
    public void clearInventory ( CommandArgs args ) {

        Player player = args.getPlayer();

        String[] arg = args.getArgs( );

        if ( arg.length > 1 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "clearinv-command", "syntax" ) );
            return;

        }

        if ( arg.length == 1 ) {

            Player target = Bukkit.getServer( ).getPlayer( arg[0] );

            if ( target == null ) {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "system", "noTargetOnline" ) );
                return;

            }

            target.getInventory().clear();

            target.sendMessage( ReplaceHolder.replaceHolderSender( player, plugin.getMsgDE().getMessageInfoDE( "clearinv-command", "invClearTarget" ) ) );
            player.sendMessage( ReplaceHolder.replaceHolderTarget( target, plugin.getMsgDE().getMessageSuccessDE( "clearinv-command", "invClearToTarget" ) ) );

        } else {

            player.getInventory().clear();

            player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "clearinv-command", "invClear" ) );
        }
    }

    //TODO REVERT CLEARINVENTORY
}
