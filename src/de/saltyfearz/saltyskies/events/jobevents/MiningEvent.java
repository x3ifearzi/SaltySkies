package de.saltyfearz.saltyskies.events.jobevents;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class MiningEvent {

    final private SaltySkies plugin;

    public MiningEvent( final SaltySkies plugin ) { this.plugin = plugin; }

    public void miningBreak( BlockBreakEvent event ) {

        final Player player = event.getPlayer();

        final Block block = event.getBlock();

        final ItemStack inMainHand = event.getPlayer().getInventory().getItemInMainHand();

        if ( !inMainHand.getType().name().endsWith( "_PICKAXE" ) ) {

            player.sendMessage(ReplaceHolder.replaceHolderStringBlock(plugin.getMsgDE().getMessage("job-system", "needPickaxe"), "§c" + block.getBlockData().getMaterial().name() + "§7" ));
            return;

        }

        final Location location = block.getLocation();

        afterBreakEvent( player, inMainHand, block, Color.fromRGB( 125, 84, 125 ), location);

    }

    private void afterBreakEvent( final Player player, final ItemStack inMainHand, final Block block, final Color color, final Location location ) {

        block.getDrops(inMainHand).forEach( drop -> { player.getInventory().addItem( drop ); });

        player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "job-system", "earnedXP" ) );

        //BlockMetaData blockMetaData = new BlockMetaData(location.getWorld(), location.getX(), location.getY(), location.getZ(), block.getType());
        //plugin.blockMetaData.put(blockMetaData, System.currentTimeMillis());
        //block.setType(Material.LIGHT_GRAY_CONCRETE); //For Farms

        block.setType( Material.AIR );

        Particle.DustOptions dustOptions = new Particle.DustOptions( color, 1 );

        location.add( .5, .5, .5 );

        Objects.requireNonNull( location.getWorld( ) ).spawnParticle( Particle.REDSTONE, location, 100, .5, .5, .5, 0, dustOptions );

    }

}
