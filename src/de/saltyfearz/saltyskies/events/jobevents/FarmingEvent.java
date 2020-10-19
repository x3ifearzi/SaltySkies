package de.saltyfearz.saltyskies.events.jobevents;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class FarmingEvent {

    final private SaltySkies plugin;

    final private ArrayList<Material> stemList = new ArrayList <>();


    public FarmingEvent( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler
    public void farmBreak( final BlockBreakEvent event ) {

        final Player player = event.getPlayer();

        final Block block = event.getBlock();

        final ItemStack inMainHand = event.getPlayer().getInventory().getItemInMainHand();

        fillStemList();
        
        /**
         *
         * Checking for Stems of Pumpkin and Melon, if true then cancel the event.
         *
        **/

        if ( stemList.iterator().next().equals( block.getType() ) ) {

            event.setCancelled( true );

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "job-command", "cropNotDestroyable" ) );
            return;

        }

        /**
         *
         * If the block isn't a Pumpkin - or Melonblock, then go forward.
         *
        **/

        if ( block.getType() != Material.MELON && block.getType() != Material.PUMPKIN ) {

            if ( inMainHand.getType().name().endsWith( "_HOE" ) ) {

                Ageable ageable = ( Ageable ) block.getState().getBlockData();

                if ( ageable.getAge() != ageable.getMaximumAge() ) {

                    player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "job-command", "cropNotReadyYet" ) );
                    return;

                }

                block.setBlockData( ageable );

                Location location = block.getLocation();

                location.add( .5, .5, .5 );

                block.getWorld().spawnParticle( Particle.VILLAGER_HAPPY, location, 40, .5, .5, .5, 0 );

                dropItemsAtBlock( block, location, inMainHand );

                player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "job-command", "earnedXP" ) );

            } else {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "job-command", "needHoe" ) );
            }

        } else {

            event.setCancelled( true );

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "job-command", "needAxeSeed" ) );

        }
    }

    private void fillStemList() {

        stemList.add( Material.MELON_STEM );
        stemList.add( Material.ATTACHED_MELON_STEM );
        stemList.add( Material.PUMPKIN_STEM );
        stemList.add( Material.ATTACHED_PUMPKIN_STEM );

    }

    public void dropItemsAtBlock(final Block block, final Location location, final ItemStack inMainHand) {

        //TODO ADD CHECK IF ENCHANT TELEPATHY EXISTS!

        block.getDrops(inMainHand).forEach( drop -> Objects.requireNonNull( location.getWorld( ) ).dropItem( location, drop) );

    }
}
