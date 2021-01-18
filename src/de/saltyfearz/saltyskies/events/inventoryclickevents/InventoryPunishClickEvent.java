package de.saltyfearz.saltyskies.events.inventoryclickevents;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.PunishCommand;
import de.saltyfearz.saltyskies.utils.AInventory;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class InventoryPunishClickEvent implements Listener {

    private final SaltySkies plugin;

    public InventoryPunishClickEvent( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler
    public void onPunishBanClick ( final InventoryClickEvent event ) {

        final Player player = ( Player ) event.getWhoClicked( );

        if ( event.getView( ).getTitle( ).startsWith( "§4§lBAN" ) ) {

            final Player target = Bukkit.getPlayer( event.getView( ).getTitle( ).replace( "§4§lBAN » §c", "" ) );

            event.setCancelled( true );
            player.updateInventory( );

            if ( event.getSlot( ) == 29 ) {

                AInventory anvilInv = new AInventory( player, eventP -> {

                    if ( eventP.getSlot( ) == AInventory.AnvilSlot.OUTPUT ) {

                        eventP.setWillClose( true );
                        eventP.setWillDestroy( true );

                        player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "punish-command", "banReason" ) ); //TODO BANREASON

                        player.openInventory( new PunishCommand( plugin ).banGUI( player, "BANGUI", target, eventP.getPlayer( ).getName( ) ) );

                    } else {

                        eventP.setWillClose( false );
                        eventP.setWillDestroy( false );

                    }

                } );

                ItemStack iS = new ItemStack( Material.PAPER, 1 );

                ItemMeta iM = iS.getItemMeta( );

                if ( iM == null ) return;

                iM.setDisplayName( "Bitte einen Grund eingeben" );
                iS.setItemMeta( iM );

                anvilInv.setSlot( AInventory.AnvilSlot.INPUT_LEFT, iS );
                anvilInv.open( );

            } else if ( event.getSlot( ) == 33 ) {

                AInventory anvilInv = new AInventory( player, eventP -> {

                    if ( eventP.getSlot( ) == AInventory.AnvilSlot.OUTPUT ) {

                        eventP.setWillClose( true );
                        eventP.setWillDestroy( true );

                        player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "punish-command", "banDuration" ) ); //TODO BANDURATION

                        player.openInventory( new PunishCommand( plugin ).banGUI( player, "BANGUI", target, eventP.getName( ) ) );

                    } else {
                        eventP.setWillClose( false );
                        eventP.setWillDestroy( false );
                    }
                } );

                ItemStack iS = new ItemStack( Material.PAPER, 1 );

                ItemMeta iM = iS.getItemMeta( );

                if ( iM == null ) return;

                iM.setDisplayName( "Dauer in Minuten angeben" );
                iS.setItemMeta( iM );

                anvilInv.setSlot( AInventory.AnvilSlot.INPUT_LEFT, iS );
                anvilInv.open( );

            } else if ( event.getSlot( ) == 22 ) {

                if ( !Objects.requireNonNull( Objects.requireNonNull( player.getOpenInventory( ).getItem( 20 ) ).getItemMeta( ) ).getDisplayName( ).equals( "§eGrund" ) && !Objects.requireNonNull( Objects.requireNonNull( player.getOpenInventory( ).getItem( 24 ) ).getItemMeta( ) ).getDisplayName( ).equals( "§eDauer Minuten" ) ) {

                    Date date = new Date( );

                    long dateLong = date.getTime( );

                    int dateMinutes;

                    dateMinutes = Integer.parseInt( Arrays.toString( Objects.requireNonNull( Objects.requireNonNull( player.getOpenInventory( ).getItem( 24 ) ).getItemMeta( ) ).getDisplayName( ).split( "^[0-9]*$" ) ) );

                    String banReason = Objects.requireNonNull( Objects.requireNonNull( player.getOpenInventory( ).getItem( 20 ) ).getItemMeta( ) ).getDisplayName( );

                    dateLong = dateLong + dateMinutes * 3600;

                    Date newDate = new Date( );

                    newDate.setTime( dateLong );

                    if ( target == null ) {

                        player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "punish-command", "playerDoesNotExist" ) );
                        return;

                    }

                    Bukkit.getBanList( BanList.Type.NAME ).addBan( target.getUniqueId( ).toString( ), banReason, newDate, player.getDisplayName( ) );

                    target.kickPlayer( "§4§lDu wurdest von §c" + player.getName( ) + "§4§l gebannt. \n §cGrund: " + banReason + "\n§cDu bist gebannt bis zum " + newDate + "\n§cMelde dich im TS oder auf dem Discord, falls die ein Fehlban war.\n" );

                }
            }
        }
    }


}
