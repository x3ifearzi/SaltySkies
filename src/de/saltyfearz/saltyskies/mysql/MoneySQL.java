package de.saltyfearz.saltyskies.mysql;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneySQL {

  private static final Connection con = CreateConnectionSQL.getConnection( );

  public void executeMoneySQL ( ) throws SQLException {

    final String createMoneyTable = "PLAYERMONEY ( MONEY double, PLAYERUUID varchar( 48 ), PRIMARY KEY ( PLAYERUUID ));";

    CreateTableSQL.createTableSQL( createMoneyTable, con );

  }

  public double getMoneyFromPlayer ( final Player player ) throws SQLException {

    PreparedStatement pS = null;

    try {

      pS = con.prepareStatement( "SELECT MONEY FROM PLAYERMONEY WHERE PLAYERUUID = ?;" );

      String uuid = player.getUniqueId().toString();

      pS.setString( 1, uuid );

      ResultSet result = pS.executeQuery( );

      if ( !result.next( ) ) return -1;

      return result.getDouble( 1 );

    } catch ( SQLException exc ) {

      exc.printStackTrace( );

      return -1;

    } finally {

      if ( pS != null ) pS.close();

    }
  }

  public void setMoneyFromPlayer ( final double tokens, final Player player ) throws SQLException {

    PreparedStatement pS = null;

    try {

      String uuid = player.getUniqueId().toString();

      if ( getMoneyFromPlayer( player ) == -1 ) {
        pS = con.prepareStatement( "INSERT INTO PLAYERMONEY (MONEY, PLAYERUUID) VALUES (?, ?);" );

        pS.setDouble( 1, tokens );
        pS.setString( 2, uuid );

        pS.executeUpdate( );

      } else {

        pS = con.prepareStatement( "UPDATE PLAYERMONEY SET MONEY = ? WHERE PLAYERUUID = ?;" );

        pS.setDouble( 1, tokens );
        pS.setString( 2, uuid );

        pS.executeUpdate( );

      }

    } catch ( SQLException exc ) {

      exc.printStackTrace( );

    } finally {

      if ( pS != null ) pS.close();

    }
  }
}
