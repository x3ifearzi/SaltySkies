package de.saltyfearz.saltyskies.events.inventoryclickevents;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryPreventNPEEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void preventNullPointerClick( final InventoryClickEvent event ) {
        checkIfNull( event.getCurrentItem(), event );
    }


    private void checkIfNull (final ItemStack item, final InventoryClickEvent event) {
        try {
            if ( item == null || item.equals( new ItemStack( Material.AIR ) ) ) {
                event.setCancelled( true );
            }
        } catch ( NullPointerException exc ) {
            event.setCancelled( true );
        }
    }
}
