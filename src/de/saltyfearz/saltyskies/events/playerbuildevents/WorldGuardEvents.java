package de.saltyfearz.saltyskies.events.playerbuildevents;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.BuildCommand;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WorldGuardEvents implements Listener {

    private final SaltySkies plugin;

    public WorldGuardEvents( final SaltySkies plugin, Map<UUID, HashMap<Location, Location>> userRegions  ) {

        this.plugin = plugin;

    }

    @EventHandler ( priority = EventPriority.HIGHEST)
    public void onPlaceInRegion ( final BlockPlaceEvent event ) {

        final Block block   = event.getBlock();
        final Player player = event.getPlayer();

        if ( !CustomConfigRegions.isInRegion( player, block ) && !player.hasPermission("SaltySkies.region.*")) {

            event.setCancelled( true );

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "region-command", "noPlaceAllowed" ) ); //TODO SET OWNER

        } else {

            event.setCancelled( !BuildCommand.build.contains( event.getPlayer().getUniqueId( ) ) );

        }
    }

    //TODO FÜR NICHT SKYBLOCK!
/*    @EventHandler
    public void onAssignedRegionEnter( final PlayerMoveEvent event ) {
        //TODO ADD ON REGION ENTER BUILD RIGHTS
        userRegions.get(event.getPlayer().getUniqueId());
    }*/
}
