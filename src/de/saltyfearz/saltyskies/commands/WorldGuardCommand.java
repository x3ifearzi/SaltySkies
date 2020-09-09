package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import de.saltyfearz.saltyskies.regions.Cuboid;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.apache.logging.log4j.core.net.Priority;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldGuardCommand implements Listener {

    final private SaltySkies plugin;

    public ArrayList< Cuboid > regions = new ArrayList <>();

    public static final HashMap < Player, Location > pos1 = new HashMap <>();

    public static final HashMap < Player, Location > pos2 = new HashMap <>();

    public WorldGuardCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "rg", aliases = "region", description = "§6Erstelle dir eine Region", usage = "§6/region <create> <text>", permission = "SaltySkies.region.create")
    public void createRegion ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length != 2 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "region-command", "syntax" ) ); //TODO SYNTAX
            return;

        }

        if ( arg[ 0 ].equalsIgnoreCase( "create" ) ) {

            if ( pos1.containsKey( player ) && pos2.containsKey( player ) ) {

                regions.add( new Cuboid( pos1.get( player ), pos2.get( player ) ) );

                CustomConfigRegions configRegions = new CustomConfigRegions( plugin );

                configRegions.addRegion( pos1.get( player ), pos2.get( player ), player, arg [ 1 ] == null ? "region_" + player.getName() : arg[ 1 ]);

                player.sendMessage( ReplaceHolder.replaceHolderString( arg[ 1 ] == null ? "" : arg[ 1 ], plugin.getMsgDE().getMessageSuccessDE( "region-command", "regionSuccessfully" ) ) );

            } else {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "region-command", "noRegionAvailable" ) );

            }
        }
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void onDefine ( PlayerInteractEvent event ) {

        Player player = event.getPlayer();

        if ( player.getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE ) {

            if ( event.getAction() == Action.LEFT_CLICK_BLOCK ) {

                event.setCancelled( true );

                pos1.put( player, event.getClickedBlock( ).getLocation( ) );

                player.sendMessage( ReplaceHolder.replaceHolderLocationXYZ( event.getClickedBlock( ).getLocation( ), plugin.getMsgDE().getMessageInfoDE( "region-command", "pos1" ) ) );

            }

            if ( event.getAction() == Action.RIGHT_CLICK_BLOCK ) {

                pos2.put( player, event.getClickedBlock().getLocation( ) );

                player.sendMessage( ReplaceHolder.replaceHolderLocationXYZ( event.getClickedBlock( ).getLocation( ), plugin.getMsgDE().getMessageInfoDE( "region-command", "pos2" ) ) );
            }

            if ( pos1.containsKey( player ) && pos2.containsKey( player ) ) {

                player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "region-command", "posSuccessfully" ) );

                plugin.getConfigRegions().registerRegions( player );

            }
        }
    }

    @EventHandler ( priority = EventPriority.HIGH )
    public void onBreakInRegion ( BlockBreakEvent event ) {

        if ( !plugin.getConfigRegions().isInRegion( event.getBlock().getLocation(), plugin.getConfigRegions().getLocation( event.getPlayer(), "pos1"), plugin.getConfigRegions().getLocation( event.getPlayer(), "pos2" ) ) ) {

            event.setCancelled( true );

            event.getPlayer().sendMessage( plugin.getMsgDE().getMessageInfoDE( "region-command", "noBuildAllowed" ) ); //TODO SET OWNER

        } else {
            event.setCancelled( !BuildCommand.build.contains( event.getPlayer().getUniqueId( ) ) );
        }
    }

    @EventHandler ( priority = EventPriority.HIGH )
    public void onPlaceInRegion ( BlockPlaceEvent event ) {

        if ( !plugin.getConfigRegions().isInRegion( event.getBlock().getLocation(), plugin.getConfigRegions().getLocation( event.getPlayer(), "pos1"), plugin.getConfigRegions().getLocation( event.getPlayer(), "pos2" ) ) ) {

            event.setCancelled( true );

            event.getPlayer().sendMessage( plugin.getMsgDE().getMessageInfoDE( "region-command", "noPlaceAllowed" ) ); //TODO SET OWNER

        } else {
            event.setCancelled( !BuildCommand.build.contains( event.getPlayer().getUniqueId( ) ) );
        }
    }
}