package de.saltyfearz.saltyskies.events.playerbuildevents;

import de.saltyfearz.saltyskies.commands.BuildCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerBuildEvent implements Listener {


    @EventHandler ( priority = EventPriority.HIGHEST )
    public void onBreak ( BlockBreakEvent e ) {
        Player p = e.getPlayer( );
        e.setCancelled( !BuildCommand.build.contains( p.getUniqueId( ) ) );
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void onBuild ( BlockPlaceEvent e ) {
        Player p = e.getPlayer( );
        e.setCancelled( !BuildCommand.build.contains( p.getUniqueId( ) ) );
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void onPlace ( PlayerBucketEmptyEvent e ) {
        Material bucket = e.getBucket( );
        Player p = e.getPlayer( );
        if ( !BuildCommand.build.contains( p.getUniqueId( ) ) ) {
            e.setCancelled( bucket.toString( ).contains( "LAVA" ) || bucket.toString( ).contains( "WATER" ) );
        }
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void onInteract ( PlayerInteractEvent e ) {
        Player p = e.getPlayer( );
        if ( ( e.getAction( ) == Action.RIGHT_CLICK_BLOCK || e.getAction( ) == Action.LEFT_CLICK_BLOCK || e.getAction( ) == Action.LEFT_CLICK_AIR || e.getAction( ) == Action.RIGHT_CLICK_AIR && e.getItem( ).getType( ) != Material.APPLE || e.getItem( ).getType( ) != Material.PORKCHOP || e.getItem( ).getType( ) != Material.BREAD ) && !BuildCommand.build.contains( p.getUniqueId( ) ) ) {
            e.setCancelled( true );
        }
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void noUproot ( PlayerInteractEvent e ) {
        Player p = e.getPlayer( );
        if ( ( e.getAction( ) == Action.PHYSICAL && e.getClickedBlock( ).getType( ) == Material.LEGACY_SOIL ) && !BuildCommand.build.contains( p.getUniqueId( ) ) ) {
            e.setCancelled( true );
        }
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void onFallDamage ( EntityDamageEvent e ) {
        if ( e.getEntity( ) instanceof Player && e.getCause( ) == EntityDamageEvent.DamageCause.FALL ) {
            e.setCancelled( true );
        }
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void onJoin ( PlayerJoinEvent e ) {
        BuildCommand.build.remove( e.getPlayer( ).getUniqueId( ) );
    }

    @EventHandler ( priority = EventPriority.HIGHEST )
    public void denyUse ( PlayerInteractEvent e ) {
        if ( !BuildCommand.build.contains( e.getPlayer( ).getUniqueId( ) ) && e.getAction( ) == Action.RIGHT_CLICK_BLOCK && ( e.getClickedBlock( ).getType( ).toString( ).contains( "DOOR" ) || e.getClickedBlock( ).getType( ) == Material.LEVER || e.getClickedBlock( ).getType( ).toString( ).contains( "CHEST" ) || e.getClickedBlock( ).getType( ) == Material.DISPENSER || e.getClickedBlock( ).getType( ) == Material.DROPPER || e.getClickedBlock( ).getType( ).toString( ).contains( "HOPPER" ) ) ) {
            e.setCancelled( true );
        }
    }
}
