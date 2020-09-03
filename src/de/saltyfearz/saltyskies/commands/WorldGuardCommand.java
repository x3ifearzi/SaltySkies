package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import de.saltyfearz.saltyskies.regions.Cuboid;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldGuardCommand {

    final private SaltySkies plugin;

    public static ArrayList< Cuboid > regions = new ArrayList <>();

    public HashMap < Player, Location > pos1 = new HashMap <>();

    public HashMap < Player, Location > pos2 = new HashMap <>();

    public WorldGuardCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "rg", aliases = "region", description = "§6Erstelle dir eine Region", usage = "§6/region <create> <text>", permission = "SaltySkies.region.create")
    public void createRegion ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length != 2 ) {

            player.sendMessage( "§4ERROR" ); //TODO SYNTAX
            return;

        }

        if ( arg[ 0 ].equalsIgnoreCase( "create" ) ) {

            if ( pos1.containsKey( player ) && pos2.containsKey( player ) ) {

                regions.add( new Cuboid( pos1.get( player ), pos2.get( player ) ) );

                CustomConfigRegions configRegions = new CustomConfigRegions( plugin );

                configRegions.addRegion( pos1.get( player ), pos2.get( player ) );

            } else {

                player.sendMessage( "§4NOREGION" ); //TODO

            }
        }
    }

    public boolean isInRegion ( Location location ) {

        for ( Cuboid cuboid : regions ) {

            if ( cuboid.contains( location ) ) {

                return true;

            }
        }

        return false;

    }

    @EventHandler
    public void onDefine ( PlayerInteractEvent event ) {

        Player player = event.getPlayer();

        if ( player.getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE ) {

            if ( event.getAction() == Action.LEFT_CLICK_BLOCK ) {

                pos1.put( player, event.getClickedBlock( ).getLocation( ) );

                player.sendMessage( "§4POS1: " + event.getClickedBlock().getLocation() ); //TODO MESSAGE

            } else if ( event.getAction() == Action.RIGHT_CLICK_BLOCK ) {

                pos2.put( player, event.getClickedBlock( ).getLocation( ) );

                player.sendMessage( "§4POS2: " + event.getClickedBlock().getLocation() ); //TODO MESSAGE
            }

            if ( pos1.containsKey( player ) && pos2.containsKey( player ) ) {

                player.sendMessage( "§4POS1 + POS2 ERFOLGREICH" ); //TODO MESSAGE

            }
        }
    }

    @EventHandler
    public void onBreakInRegion ( BlockBreakEvent event ) {

        if ( isInRegion( event.getBlock( ).getLocation( ) ) ) {

            event.setCancelled( true );

            event.getPlayer().sendMessage( "REGION NO BUILD" ); //TODO MESSAGE

        }
    }

    @EventHandler
    public void onPlaceInRegion ( BlockPlaceEvent event ) {

        if ( isInRegion( event.getBlock( ).getLocation() ) ) {

            event.setCancelled( true );

            event.getPlayer().sendMessage( "REGION NO PLACE" ); //TODO MESSAGE

        }
    }
}
