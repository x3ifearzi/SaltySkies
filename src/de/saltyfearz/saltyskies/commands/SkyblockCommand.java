package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.entity.Player;

public class SkyblockCommand {

    final private SaltySkies plugin;

    public SkyblockCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command ( name = "island", aliases = { "is", "sb", "skyblock" }, description = "§4Skyblock by x3IFeaRzI.", usage = "§6/Skyblock <help>", permission = "SaltySkies.skyblock.player" )
    public void skyblock ( CommandArgs args ) {

        final Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length == 0 ) {

            //TODO OPEN INV

        } else {

            if ( arg[0].equalsIgnoreCase( "help" ) || arg[0].equalsIgnoreCase( "hilfe" ) ) {

                //TODO SEND PLAYER MESSAGE

            } else if ( arg[0].equalsIgnoreCase( "create" ) || arg[0].equalsIgnoreCase( "erstellen" ) ) {

                
            }
        }

    }
}