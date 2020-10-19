package de.saltyfearz.saltyskies.events.jobevents;

import com.mojang.datafixers.kinds.IdF;
import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class WoodcuttingEvent {

    final private SaltySkies plugin;

    public WoodcuttingEvent ( final SaltySkies plugin ) { this.plugin = plugin; }

    public void logBreak ( final BlockBreakEvent event ) {

        final Player player = event.getPlayer( );

        final Block block = event.getBlock( );

        final ItemStack inMainHand = player.getInventory( ).getItemInMainHand( );

        if ( inMainHand.getType( ).name( ).endsWith( "_AXE" ) ) {

            if ( block.getType( ).name( ).endsWith( "_LOG" ) ) {

                final Block treeStem = block.getWorld( ).getBlockAt( block.getLocation( ).subtract( 0, 1, 0 ) );

                if ( treeStem.getType( ).equals( Material.GRASS_BLOCK ) || treeStem.getType( ).equals( Material.PODZOL ) ) {

                    final Location location = block.getLocation( ).add( .5, .5, .5 );

                    block.getWorld( ).spawnParticle( Particle.VILLAGER_HAPPY, location, 35, .5, .5, .5, 0 );

                    treeFeller( player, block.getType( ), block, block );

                } else if ( treeStem.getType( ).name( ).endsWith( "_LOG" ) ) {

                    player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "job-command", "cannotBrokeAtMiddle" ) );

                }

            } else {

                player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "job-command", "saplingNotDestroyable" ) );

            }

        } else {

            player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "job-command", "needAxe" ) );

        }
    }

    private void treeFeller ( final Player player, final Material material, final Block startBlock, final Block endBlock ) {

        int radius = 1;

        for ( int x = radius; x >= -radius; x-- ) {

            for ( int y = radius; y >= -radius; y-- ) {

                for ( int z = radius; z >= -radius; z-- ) {

                    final Block relative = endBlock.getRelative( x, y, z );

                    Bukkit.getScheduler( ).runTaskLater( plugin, ( ) -> {

                        if ( relative.getType( ) == material ) {

                            relative.setType( Material.AIR );

                            player.playSound( player.getLocation( ), Sound.BLOCK_WOOD_BREAK, 1, 1 );

                            if ( startBlock.getLocation( ).distance( player.getLocation( ) ) <= 5 ) {

                                //TODO IF PLAYER HAS TELEPATHY

                                player.getInventory( ).addItem( new ItemStack( material ) );

                                player.sendMessage( plugin.getMsgDE( ).getMessageSuccessDE( "job-command", "earnedXP" ) );

                                //relative.getWorld().dropItem( relative.getLocation(), new ItemStack( material ) );

                            } else {

                                player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "job-command", "toFarAwayToGainAny" ) );
                            }

                            treeFeller( player, material, startBlock, relative );

                        }
                    }, 5 );
                }
            }
        }
    }
}
