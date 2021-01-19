package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import de.saltyfearz.saltyskies.regions.Cuboid;
import de.saltyfearz.saltyskies.skyblock.IslandLogic;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class WorldGuardCommand implements Listener {

    final private SaltySkies plugin;

    public static ArrayList< Cuboid > regions = new ArrayList <>();

    public static final HashMap < Player, Location > pos1 = new HashMap <>();

    public static final HashMap < Player, Location > pos2 = new HashMap <>();

    public WorldGuardCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "rg", aliases = "region", description = "§6Erstelle dir eine Region", usage = "§6/region <create> <text>", permission = "SaltySkies.region.create")
    public void createRegion ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length != 2 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "region-command", "syntax" ) );
            return;

        }

        if ( arg[ 0 ].equalsIgnoreCase( "create" ) ) {

            if ( pos1.containsKey( player ) && pos2.containsKey( player ) ) {

                regions.add( new Cuboid( pos1.get( player ), pos2.get( player ) ) );

                CustomConfigRegions.addRegion( pos1.get( player ), pos2.get( player ), player, arg [ 1 ] == null ? "region_" + player.getName() : arg[ 1 ]);

                player.sendMessage( ReplaceHolder.replaceHolderString( arg[ 1 ] == null ? "" : arg[ 1 ], plugin.getMsgDE().getMessageSuccessDE( "region-command", "regionSuccessfully" ) ) );

            } else {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "region-command", "noRegionAvailable" ) );

            }
        }
    }

    @EventHandler
    public void onDefine ( PlayerInteractEvent event ) {

        Player player = event.getPlayer();

        if ( player.getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE && Objects.equals(event.getHand(), EquipmentSlot.HAND)) {

            if (player.hasPermission("SaltySkies.region.create")) {

                event.setCancelled(true);

                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

                    pos1.put(player, Objects.requireNonNull(event.getClickedBlock()).getLocation());

                    player.sendMessage(ReplaceHolder.replaceHolderLocationXYZ(event.getClickedBlock().getLocation(), plugin.getMsgDE().getMessageInfoDE("region-command", "pos1")));

                } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                    pos2.put(player, Objects.requireNonNull(event.getClickedBlock()).getLocation());

                    player.sendMessage(ReplaceHolder.replaceHolderLocationXYZ(event.getClickedBlock().getLocation(), plugin.getMsgDE().getMessageInfoDE("region-command", "pos2")));

                }

                checkLocations(event, player);
            }
        }
    }

    private void checkLocations( final PlayerInteractEvent event, final Player player ) {

        if ( pos1.containsKey( player ) && pos2.containsKey( player ) ) {

            if ( CustomConfigRegions.isInRegion( player, event.getClickedBlock() )) {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "region-command", "regionAlreadyExists" ) );

                pos1.remove( player );
                pos2.remove( player );

            } else {

                player.sendMessage(plugin.getMsgDE().getMessageSuccessDE("region-command", "posSuccessfully"));

            }
        }
    }

    public void onDefine ( final Player player, final IslandLogic island ) {

        pos1.put( player, player.getLocation().add( island.getPositionX() + 50, 255, island.getPositionZ() + 50 ) );

        pos2.put( player, player.getLocation().subtract( island.getPositionX() + 50, 255, island.getPositionZ() + 50 ) );

    }
}