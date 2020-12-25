package de.saltyfearz.saltyskies.enchantments;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.enums.PICKAXES;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class TelepathyEvent implements Listener {

    final private SaltySkies plugin;
    
    public TelepathyEvent ( final SaltySkies plugin ) { this.plugin = plugin; }
    
    @EventHandler
    public void onTelepathy( BlockBreakEvent event ) {

        List<Object> list = new ArrayList<>();

        list.add(Collections.singletonList( Arrays.toString( PICKAXES.values( ) ) ));

        Player player = event.getPlayer( );

        Block block = event.getBlock();
        
        if ( !player.getInventory().getItemInMainHand().getType().name().equalsIgnoreCase( String.valueOf( list.iterator().next() ) ) ) return;
        
        if ( block.getState() instanceof Container ) return;
        
        if ( player.getInventory().firstEmpty() == -1 ) {
            
            plugin.getMsgDE().getMessageInfoDE( "enchant-command", "telepathy-fullinv" );
            return;

        }

        event.setDropItems( false );

        Collection<ItemStack> drops = block.getDrops( player.getInventory().getItemInMainHand() );

        if ( drops.isEmpty() ) return;

        player.getInventory().addItem( drops.iterator().next() );
        
        
    }
}
