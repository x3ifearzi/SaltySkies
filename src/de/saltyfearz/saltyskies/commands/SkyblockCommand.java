package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import de.saltyfearz.saltyskies.skyblock.IslandLogic;
import de.saltyfearz.saltyskies.skyblock.IslandTools;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SkyblockCommand {

    final private SaltySkies plugin;

    public SkyblockCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command ( name = "island", aliases = { "is", "sb", "skyblock" }, description = "§4Skyblock by x3IFeaRzI.", usage = "§6/Skyblock <help>", permission = "SaltySkies.skyblock.player" )
    public void skyblock ( CommandArgs args ) throws SQLException {

        final Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length == 0 ) {

            //TODO OPEN INV

        } else if ( arg.length == 1 ){

            if ( arg[0].equalsIgnoreCase( "help" ) || arg[0].equalsIgnoreCase( "hilfe" ) ) {

                //TODO SEND PLAYER MESSAGE

            } else if ( arg[0].equalsIgnoreCase( "create" ) || arg[0].equalsIgnoreCase( "erstellen" ) ) {

                IslandLogic island = new IslandLogic( plugin, player.getUniqueId().toString() );

                IslandTools iTools = new IslandTools( plugin );

                island.create( player.getDisplayName() );

                player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "island-command", "islandSavedSuccessfully" ) );

                iTools.generateIsland( island.getPositionX(), island.getPositionZ() );

                player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "island-command", "islandLoadedSuccessfully" ) );

                WorldGuardCommand wG = new WorldGuardCommand( plugin );

                wG.onDefine( player );

                CustomConfigRegions.addRegion( WorldGuardCommand.pos1.get( player ), WorldGuardCommand.pos2.get( player ), player, player.getDisplayName() );

                player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "island-command", "islandRegionCreatedSuccessfully" ) );

                island.teleportToIsland( player );

                island.saveIsland( );

                
            } else if ( arg[0].equalsIgnoreCase( "home" ) || arg[0].equalsIgnoreCase( "h" ) || arg[0].equalsIgnoreCase( "zuhause" ) ) {


            } else if ( arg[0].equalsIgnoreCase( "info" ) || arg[0].equalsIgnoreCase( "infos" ) )  {


            } else if ( arg[0].equalsIgnoreCase( "setspawn" ) || arg[0].equalsIgnoreCase( "sethome" ) ) {


            }

        } else if ( arg.length < 3 ) {

            if ( ( arg[0].equalsIgnoreCase( "create" ) || arg[0].equalsIgnoreCase( "erstellen" ) ) && ( arg[1].matches( "^[a-zA-Z0-9]*$" ) || arg[1].isEmpty() ) ) {

                String islandName = arg[1];

                if ( islandName.equals( "" ) ) {

                    islandName = player.getDisplayName();

                }

                createIsland( player, islandName );

            } else {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "island-command", "regex" ) );

            }

        } else {

            //TODO OPEN INV

        }

    }

    public void createIsland( final Player player, final String islandName ) {

    }
}