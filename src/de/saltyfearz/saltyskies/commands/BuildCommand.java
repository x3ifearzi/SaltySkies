package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BuildCommand {

    final private SaltySkies plugin;

    public static final ArrayList < UUID > build = new ArrayList<>();

    public BuildCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "build", aliases = "b", description = "§6Du kannst dir Rechte zum Bauen geben.", permission = "SaltySkies.build")
    public void toggleBuild ( CommandArgs args ) {

        Player player = args.getPlayer( );

        Player target = player;

        String[] arg = args.getArgs();

        if ( arg.length > 1 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "build-command", "syntax" ) );
            return;

        }

        if ( arg.length == 1 ) {

            target = Bukkit.getServer( ).getPlayer( arg[0] );


            if ( target == null ) {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "system", "noTargetOnline" ) );
                return;

            }

        }


        if ( !build.contains( target.getUniqueId() ) ) {

            build.add( target.getUniqueId() );

            target.sendMessage( plugin.getMsgDE().getMessageInfoDE( "build-command", "build" ) );

            if ( !player.equals( target ) ) {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "build-command", "otherPlayerBuild" ) );

            }

        } else {

            build.remove( target.getUniqueId() );

            target.sendMessage( plugin.getMsgDE().getMessageInfoDE( "build-command", "noBuild" ) );

            if ( !player.equals( target ) ) {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "build-command", "otherPlayerNoBuild" ) );

            }
        }

    }
}
