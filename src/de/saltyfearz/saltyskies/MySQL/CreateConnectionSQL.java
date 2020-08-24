package de.saltyfearz.saltyskies.MySQL;

import static de.saltyfearz.saltyskies.MySQL.PlayerSQL.executePlayerSQL;

import de.saltyfearz.saltyskies.SaltySkies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnectionSQL {

  final private SaltySkies plugin;

  public static Connection con;

  public CreateConnectionSQL ( final SaltySkies plugin ) { this.plugin = plugin; }

  public static void connect () {

    String hostAddress = "";
    String database    = "";
    String username    = "";
    String password    = "";

    int portAddress = 3306;


    try {

      con = DriverManager.getConnection("jdbc:mysql://" + hostAddress + ":" + portAddress + "/" + database, username, password);

      System.out.println("MYSQL - VERBINDUNG HERGESTELLT!");

    } catch ( SQLException exc ) {

      exc.printStackTrace();

    }
  }

  public static void disconnect () {

    try {

      con.close();

      System.out.println("MYSQL - VERBINDUNG GESCHLOSSEN!");

    } catch ( SQLException exc ) {

      exc.printStackTrace();

    }
  }

  public static void startSQLQueryies () throws SQLException {
    executePlayerSQL();

  }

  public static Connection getConnection () { return con; }

  public static boolean isConnected () { return ( con != null ); }

}
