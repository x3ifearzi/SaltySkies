package de.saltyfearz.saltyskies.events.inventoryclickevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryPunishClickEvent implements Listener {

    @EventHandler
    public void onPunishBanClick( final InventoryClickEvent event ) {

        final Player player = (Player) event.getWhoClicked();

        if ( event.getView().getTitle().startsWith( "§4§lBAN" ) ) {

            final Player target = Bukkit.getPlayer( event.getView().getTitle().replace( "§4§lBAN » §c", "" ) );

            event.setCancelled( true );
            player.updateInventory();

            if ( event.getSlot() == 29 ) {

            } else if ( event.getSlot() == 33 ) {

            } else if ( event.getSlot() == 22 ) {

            }
        }
    }


}
