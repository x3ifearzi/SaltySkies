package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand {

    final private SaltySkies plugin;

    private Material material;

    public GiveCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command ( name = "give", aliases = "i", description = "§6Gebe dir eine Anzahl beliebiger Gegenstände.", usage = "§6/give <item> [<amount>] [<player>]", permission = "SaltySkies.give" )
    public void giveItems ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length > 3 || arg.length == 0 ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "give-command", "syntax" ) );
            return;

        }

        if ( arg.length == 1 ) {

            if ( arg[ 0 ].matches( "(^[a-zA-Z_]*$)" ) ) {

                if ( isItemValid( arg[ 0 ].toUpperCase( ) ) ) {

                    player.sendMessage( ReplaceHolder.replaceHolderString( arg[ 0 ], plugin.getMsgDE( ).getMessageErrorDE( "give-command", "itemNotExists" ) ) );
                    return;

                }

                if ( player.getInventory( ).firstEmpty( ) == -1 ) {

                    player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "give-command", "notEnoughSpaceInv" ) );
                    return;

                }

                player.getInventory( ).addItem( new ItemStack( material ) );
                player.sendMessage( ReplaceHolder.replaceHolderStringInt( arg[ 0 ], "1", plugin.getMsgDE( ).getMessageSuccessDE( "give-command", "itemExists" ) ) );

            } else {

                player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "give-command", "regex" ) );
                player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "give-command", "syntax" ) );

            }
        } else if ( arg.length == 2 ) {

            if ( arg[ 0 ].matches( "(^[a-zA-Z_]*$)" ) && arg[ 1 ].matches( "^[0-9]*$" ) ) {

                if ( isItemValid( arg[ 0 ].toUpperCase( ) ) ) {

                    player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "give-command", "itemNotExists" ) );
                    return;

                }

                ItemStack iS = new ItemStack( material );

                iS.setAmount( Integer.parseInt( arg[ 1 ] ) );

                player.getInventory( ).addItem( new ItemStack( material ) );
                player.sendMessage( ReplaceHolder.replaceHolderStringInt( arg[ 0 ], arg[ 1 ], plugin.getMsgDE( ).getMessageSuccessDE( "give-command", "itemExists" ) ) );

            } else {

                player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "give-command", "syntax" ) );

            }
        } else {

            if ( arg[ 0 ].matches( ( "^[a-zA-Z,_]*$" ) ) && arg[ 1 ].matches( "^[0-9]*$" ) ) {

                if ( isItemValid( arg[ 0 ].toUpperCase( ) ) ) {

                    player.sendMessage( ReplaceHolder.replaceHolderString( arg[ 0 ], plugin.getMsgDE( ).getMessageErrorDE( "give-command", "itemNotExists" ) ) );
                    return;

                }

                ItemStack iS = new ItemStack( material );

                iS.setAmount( Integer.parseInt( arg[ 1 ] ) );

                Player target = Bukkit.getServer( ).getPlayer( arg[2] );

                if ( target == null ) {

                    player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "systen", "noTargetOnline" ) );
                    return;

                }

                if ( target.getInventory( ).firstEmpty( ) == -1 ) {

                    player.sendMessage( ReplaceHolder.replaceHolderTarget( target, plugin.getMsgDE( ).getMessageInfoDE( "give-command", "notEnoughSpaceInvTarg" ) ) );
                    return;

                }

                target.getInventory( ).addItem( new ItemStack( material ) );
                target.sendMessage( ReplaceHolder.replaceHolderStringInt( arg[ 0 ], arg[ 1 ], plugin.getMsgDE( ).getMessageInfoDE( "give-command", "itemExists" ) ) );

                player.sendMessage( ReplaceHolder.replaceHolderStringIntTarget( arg[ 0 ], arg[ 1 ], target, plugin.getMsgDE().getMessageSuccessDE( "give-command", "giveItemToTarget" ) ) );

            }
        }
    }

    public boolean isItemValid ( final String materialName ) {

        material = Material.getMaterial( materialName.toUpperCase() );

        return material == null;
    }
}
