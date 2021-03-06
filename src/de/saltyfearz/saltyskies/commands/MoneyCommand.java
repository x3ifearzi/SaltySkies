package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.ResultSQL;
import de.saltyfearz.saltyskies.mysql.UpdateSQL;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyCommand {

    final private SaltySkies plugin;

    public MoneyCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command ( name = "money",
            aliases = { "balance", "bal" },
            description = "§6Sieh dir deinen Fearzybetrag an.",
            usage = "§6/money [<player>]",
            permission = "SaltySkies.balance" )
    public void getBalance ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length > 1 ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "fearzy-system", "syntax-bal" ) );
            return;
        }

        if ( arg.length == 0 ) {

            player.sendMessage( ReplaceHolder.replaceHolderOwnFearzys( getFearzy( player ), plugin.getMsgDE( ).getMessageInfoDE( "fearzy-system", "ownFearzys" ) ) );

        } else {

            Player target = Bukkit.getServer( ).getPlayer( arg[ 0 ] );

            if ( target == null ) {

                player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "system", "noTargetOnline" ) );
                return;
            }

            player.sendMessage( ReplaceHolder.replaceHolderTargetFearzys( getFearzy( target ), target, plugin.getMsgDE( ).getMessageInfoDE( "fearzy-system", "targetFearzys" ) ) );
        }
    }

    @Command ( name = "pay", description = "§6Sende einem Spieler deine Fearzy's.", usage = "§6/pay <player> <fearzys>", permission = "SaltySkies.pay" )
    public void sendBalance ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        double sendBalance = 0.00;

        if ( arg.length != 2 ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "fearzy-system", "syntax-pay" ) );
            return;

        }

        Player target = Bukkit.getServer( ).getPlayer( arg[ 0 ] );

        if ( target == null ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "system", "noTargetOnline" ) );
            return;

        }

        if ( player.getName( ).equals( target.getName( ) ) ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "fearzy-system", "noSelfPaying" ) );
            return;

        }

        if ( !arg[ 1 ].matches( "^[0-9]*[.]?[0-9]*$" ) ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "fearzy-system", "regex" ) );
            return;

        }

        if ( getFearzy( player ) < sendBalance ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "fearzy-system", "notEnoughFearzys" ) );
            return;

        }

        removeFearzys( player, Double.parseDouble( arg[ 1 ] ) );

        addFearzys( target, Double.parseDouble( arg[ 1 ] ) );

        player.sendMessage( ReplaceHolder.replaceHolderTargetFearzys( Double.parseDouble( arg[ 1 ] ), target, plugin.getMsgDE( ).getMessageSuccessDE( "fearzy-system", "sendFearzySuccess" ) ) );

        target.sendMessage( ReplaceHolder.replaceHolderSenderFearzys( Double.parseDouble( arg[ 1 ] ), player, plugin.getMsgDE( ).getMessageInfoDE( "fearzy-system", "getFearzyFromSend" ) ) );

    }

    @Command ( name = "setmoney", aliases = { "setbal" }, description = "§6Setze bei einem Spieler den Fearzystand zu einem beliebigen Wert.", permission = "SaltySkies.setmoney" )
    public void setBalance ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length != 2 ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageInfoDE( "fearzy-system", "syntax-setbal" ) );
            return;

        }

        Player target = Bukkit.getServer( ).getPlayer( arg[ 0 ] );

        if ( target == null ) {

            player.sendMessage( plugin.getMsgDE( ).getMessageErrorDE( "system", "noTargetOnline" ) );
            return;

        }

        setFearzys( target, Double.parseDouble( arg[1] ) );

        target.sendMessage( ReplaceHolder.replaceHolderSenderFearzys( Double.parseDouble( arg[ 1 ] ), player, plugin.getMsgDE().getMessageInfoDE( "fearzy-system", "setFearzyFromSend" ) ) );

        player.sendMessage( ReplaceHolder.replaceHolderTargetFearzys( Double.parseDouble( arg[ 1 ] ), player, plugin.getMsgDE().getMessageSuccessDE( "fearzy-system", "setFearzyToTarget" ) ) );


    }

    public double getFearzy ( final Player player ) {

        final String getBalance = "SELECT MONEY FROM PLAYERMONEY WHERE PLAYERUUID = '" + player.getUniqueId( ).toString( ) + "';";

        try {

            ResultSet result = ResultSQL.resultSQL( getBalance, CreateConnectionSQL.getConnection( ) );

            if ( result.next() ) return result.getDouble("MONEY");

            return -1;

        } catch ( SQLException exc ) {

            return -1;

        }

    }

    public void removeFearzys ( final Player player, final double removingBalance ) {

        double getMoney = getFearzy( player );

        getMoney = getMoney - removingBalance;

        final String removeBalance = "UPDATE PLAYERMONEY SET MONEY = " + getMoney + " WHERE PLAYERUUID = '" + player.getUniqueId( ).toString( ).toLowerCase( ) + "';";

        try {

            UpdateSQL.updateSQL( removeBalance, CreateConnectionSQL.getConnection( ) );

        } catch ( SQLException exc ) {

            exc.printStackTrace( );

        }
    }

    public void addFearzys ( final Player player, final double addingBalance ) {

        double getMoney = getFearzy( player );

        getMoney = getMoney + addingBalance;

        final String addBalance = "UPDATE PLAYERMONEY SET MONEY = " + getMoney + " WHERE PLAYERUUID = '" + player.getUniqueId( ).toString( ).toLowerCase( ) + "';";

        try {

            UpdateSQL.updateSQL( addBalance, CreateConnectionSQL.getConnection( ) );

        } catch ( SQLException exc ) {

            exc.printStackTrace( );

        }
    }

    public void setFearzys ( final Player player, final double setBal ) {

        final String setBalance = "UPDATE PLAYERMONEY SET MONEY = " + setBal + " WHERE PLAYERUUID = " + player.getUniqueId().toString() + "'";
        final String insertPlayerMoney = "INSERT INTO PLAYERMONEY ( MONEY, PLAYERUUID ) VALUES ( " + 1000.00 + ", '" + player.getUniqueId().toString() + "');";
        try {

             UpdateSQL.updateSQL( setBalance, CreateConnectionSQL.getConnection( ) );

        } catch ( SQLException exc ) {

            try {

                UpdateSQL.updateSQL( insertPlayerMoney, CreateConnectionSQL.getConnection( ) );

            } catch ( SQLException exc2 ) {

                exc2.printStackTrace();

            }
        }
    }
}
