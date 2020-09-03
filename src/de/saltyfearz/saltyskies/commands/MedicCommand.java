package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MedicCommand {

    final private SaltySkies plugin;

    public MedicCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "heal", description = "§6Fülle deine Herzen auf.", usage = "§6/heal [<player>]", permission = "SaltySkies.medic.heal")
    public void heal ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length > 1 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "heal-command", "syntax" ) );
            return;

        }

        if ( arg.length == 0 ) {

            player.setHealth( Objects.requireNonNull( player.getAttribute( Attribute.GENERIC_MAX_HEALTH ) ).getDefaultValue() );

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "heal-command", "heal" ) );

        } else {

            Player target = Bukkit.getServer( ).getPlayer( arg[0] );

            if ( target == null ) {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "system", "noTargetOnline" ) );
                return;

            }

            target.setHealth( Objects.requireNonNull( player.getAttribute( Attribute.GENERIC_MAX_HEALTH ) ).getDefaultValue() );

            target.sendMessage( plugin.getMsgDE().getMessageInfoDE( "heal-command", "heal" ) );

            player.sendMessage( ReplaceHolder.replaceHolderTarget( target, plugin.getMsgDE().getMessageSuccessDE( "heal-command", "healOther" ) ) );

        }
    }

    @Command( name = "feed", description = "§6Stille deinen Hunger.", usage = "§6/feed [<player>]", permission = "SaltySkies.medic.feed")
    public void feed ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length > 1 ) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "feed-command", "syntax" ) );
            return;

        }

        if ( arg.length == 0 ) {

            player.setFoodLevel( 20 );

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "feed-command", "feed" ) );

        } else {

            Player target = Bukkit.getServer( ).getPlayer( arg[0] );

            if ( target == null ) {

                player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "system", "noTargetOnline" ) );
                return;

            }

            target.setHealth( 20 );

            target.sendMessage( plugin.getMsgDE().getMessageInfoDE( "feed-command", "feed" ) );

            player.sendMessage( ReplaceHolder.replaceHolderTarget( target, plugin.getMsgDE().getMessageSuccessDE( "feed-command", "feedOther" ) ) );

        }
    }
}
