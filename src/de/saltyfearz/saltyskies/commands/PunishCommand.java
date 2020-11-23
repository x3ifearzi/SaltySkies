package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.items.InventoryLayoutItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PunishCommand {

    final private SaltySkies plugin;

    private InventoryLayoutItems invL = null;

    final private int[] viewPunishLayout = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27, 28, 30, 31, 32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};

    public PunishCommand( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "punish", aliases = {"unban", "ban", "kick", "mute", "ekick", "eban", "emute", "eunmute", "unmute", "eunban"}, description = "§6Eine GUI um das Fehlverhalten von Spielern zu behandeln.", usage = "§6/punish [<player>]", permission = "SaltySkies.punish.*")
    public void punishGUI( CommandArgs args ) {

        invL = plugin.getiLI();

        final Player player = args.getPlayer();

        final String[] arg = args.getArgs();

        final Player target;

        if ( args.length() < 1 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "punish-command", "syntax" ) );
            return;

        }

        target = Bukkit.getServer( ).getPlayer( arg[0] );

        if ( target == null ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "punish-command", "playerNotFound" ) );
            return;

        }

        Inventory inv = Bukkit.getServer().createInventory( null, 45, "§4§lPUNISH » §c" + target.getName());

        for ( int value : viewPunishLayout ) {

            inv.setItem( value, invL.GRAY_STAINED_GLASS_PANE );

        }

        inv.setItem( 11, invL.LIME_STAINED_GLASS_PANE__UNBAN );
        inv.setItem( 15, invL.RED_STAINED_GLASS_PANE__BAN );
        inv.setItem( 22, invL.PURPLE_STAINED_GLASS_PANE__KICK );
        inv.setItem( 29, invL.GREEN_STAINED_GLASS_PANE__UNMUTE );
        inv.setItem( 33, invL.YELLOW_STAINED_GLASS_PANE__MUTE );
        inv.setItem( 44, invL.ARROW_BACK_PUNISH );

        player.openInventory( inv );


    }

    public Inventory banGUI( final Player player, final String whichGUI, final Player target, String input ) {

        if ( whichGUI.equals( "BANGUI" ) ) {

            final int[] banLayout = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 21, 23, 25, 26, 27, 28, 30, 31, 32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};

            Inventory inv = Bukkit.getServer().createInventory( null, 45, "§4§lBAN » §c" + target.getName());

            for ( int value : banLayout ) {

                inv.setItem( value, invL.GRAY_STAINED_GLASS_PANE );

            }

            input = input.equals( "" ) || !input.matches( "^[a-zA-Z,. ]*" ) ? input = "§eGrund" : input;

            inv.setItem( 20, invL.PAPER_REASON );
            inv.setItem( 22, invL.NETHERSTAR_EXECUTE_PUNISH );
            inv.setItem( 24, invL.REDSTONE_DURATION );
            inv.setItem( 29, invL.BOOK_N_QUILL_INPUT_REASON );
            inv.setItem( 33, invL.BOOK_N_QUILL_INPUT_DURATION );
            inv.setItem( 44, invL.ARROW_BACK_PUNISH );

            return inv;

        } else if (whichGUI.equals( "MUTEGUI" )) {

            return null;
        }

        return null;

    }
}
