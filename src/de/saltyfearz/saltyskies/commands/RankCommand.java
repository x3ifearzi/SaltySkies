package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.mysql.RankSQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RankCommand {

    private final SaltySkies plugin;

    public RankCommand( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "rank", aliases = "rang", description = "§6Rangsystem V1.0", usage = "§6/rank", permission = "SaltySkies.ranks.*")
    public void ranks( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length < 1 || arg.length > 2 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "rank-command", "help" ) );
            return;

        }

        if ( arg.length == 1 ) {

            if ( arg[0].equalsIgnoreCase( "list" ) ) {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "layoutbox", "chatLayoutTopLeft" ) + plugin.getMsgDE().getMessage( "layoutbox", "prefixForLayoutRanks" ) + plugin.getMsgDE().getMessage( "layoutbox", "chatLayoutTopRight" ));

                for ( String rank : RankSQL.getRanks() ) {

                    player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "layoutbox", "chatLayoutLeft" ) + " " + rank + " " + plugin.getMsgDE().getMessage( "layoutbox", "chatLayoutRight" ));

                }

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "layoutbox", "chatLayoutBottomLeft" ) + plugin.getMsgDE().getMessage( "layoutbox", "prefixForLayoutRanks" ) + plugin.getMsgDE().getMessage( "layoutbox", "chatLayoutBottomRight" ) );

            }

        }

    }

    @Command( name = "setrank", aliases = "setrang", description = "§6Rangsystem V1.0", usage = "§6/setrank", permission = "SaltySkies.ranks.setrank")
    public void assignRanks( CommandArgs args ) {

        Player player = args.getPlayer();

        String[] arg = args.getArgs();

        if ( arg.length < 1 || arg.length > 2 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "rank-command", "help" ) );
            return;

        }

        if ( RankSQL.getRanks().contains( arg[1] ) ) {

            Player target = Bukkit.getServer( ).getPlayer( arg[0] );

            if ( target == null ) {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "rank-command", "playerDoesNotExists" ) );
                return;

            }

            String rank = arg[1].toUpperCase();

            RankSQL.setRank( target, rank );

        }
    }
}
