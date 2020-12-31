package de.saltyfearz.saltyskies.events.jobevents;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.BuildCommand;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import de.saltyfearz.saltyskies.enums.FARMING_BLOCKS;
import de.saltyfearz.saltyskies.enums.LUMBERJACK_BLOCKS;
import de.saltyfearz.saltyskies.enums.MINING_BLOCKS;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import java.util.Arrays;
import java.util.Objects;

public class SkillEvents implements Listener {

    final private SaltySkies plugin;

    final ItemStack apple = new ItemStack( Material.APPLE );

    public SkillEvents( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void blockInteractions( final BlockBreakEvent event ) {


        final Block block = event.getBlock();

        final Player player = event.getPlayer();

        if ( player.isSneaking() ) return;

        if ( !CustomConfigRegions.isInRegion(player, block) && !player.hasPermission("SaltySkies.region.*") ) {

            event.setCancelled(true);

            player.sendMessage(plugin.getMsgDE().getMessageInfoDE("region-command", "noBuildAllowed")); //TODO SET OWNER

            return;

        }

        if ( !BuildCommand.build.contains( player.getUniqueId( ) ) ) return;

        if ( Arrays.stream( FARMING_BLOCKS.values() ).anyMatch(farming_blocks -> farming_blocks.toString().equalsIgnoreCase( block.getType( ).name( ) ) ) ) {

            event.setCancelled( true );
            plugin.getFarmingEvent().farmBreak( event );

        } else if ( Arrays.stream( MINING_BLOCKS.values() ).anyMatch( mining_blocks -> mining_blocks.toString().equalsIgnoreCase( block.getType( ).name( ) ) ) ) {

            event.setCancelled( true );
            plugin.getMiningEvent().miningBreak( event );

        } else if ( Arrays.stream( LUMBERJACK_BLOCKS.values() ).anyMatch(lumberjack_blocks -> lumberjack_blocks.toString().equalsIgnoreCase( block.getType( ).name( ) ) ) ) {

            event.setCancelled( true );
            plugin.getWoodcuttingEvent().logBreak( event );

        }
    }

    @EventHandler
    public void cancelTreeStripping( final PlayerInteractEvent event ) {

        final ItemStack inMainHand = event.getPlayer().getInventory().getItemInMainHand();

        if (!BuildCommand.build.contains(event.getPlayer().getUniqueId( ))) return;

        if ( ! ( event.getAction() == Action.RIGHT_CLICK_BLOCK ) || !inMainHand.getType().name().endsWith( "_AXE" ) || !Objects.equals(event.getHand(), EquipmentSlot.HAND)) return;

        final Block block = event.getClickedBlock( );

        if ( block == null ) return;

        if ( block.getType().name().endsWith( "_LOG" ) ) return;

        event.setCancelled( true );

        event.getPlayer().sendMessage( plugin.getMsgDE().getMessageInfoDE( "job-system", "canNotStripping" ) );

    }

    @EventHandler
    public void leafDelay( final LeavesDecayEvent event ) {

        event.setCancelled( true );

        if ( Math.random() > 0.05 ) {

            Objects.requireNonNull( event.getBlock( ).getLocation( ).getWorld( ) ).dropItem( event.getBlock( ).getLocation( ), apple);

        }

        event.getBlock().setType( Material.AIR );

    }

/*  public void restoreBlocks() {
        ArrayList <BlockMetaData> tempMeta = new ArrayList<>();
        plugin.blockMetaData.keySet().forEach(metaData -> {
            long old = plugin.blockMetaData.get(metaData);
            long current = System.currentTimeMillis();
            long seconds = TimeUnit.MILLISECONDS.toSeconds(current - old);
            if (seconds >= 2) {
                Location blockLoc = new Location(metaData.getWorld(), metaData.getX(), metaData.getY(), metaData.getZ());
                metaData.getWorld().getBlockAt(blockLoc).setType(metaData.getMaterial());
                metaData.getWorld().playEffect(blockLoc, Effect.EXTINGUISH, 1, 10);
                tempMeta.add(metaData);
            }
        });
        tempMeta.forEach(data -> {
            plugin.blockMetaData.remove(data);
        });
    }*/

}
