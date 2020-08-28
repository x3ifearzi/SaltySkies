package de.saltyfearz.saltyskies.mysql;

import static de.saltyfearz.saltyskies.mysql.PlayerSQL.executePlayerSQL;

import de.saltyfearz.saltyskies.SaltySkies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnectionSQL {

  final private SaltySkies plugin;

  public static Connection con;

  public CreateConnectionSQL ( final SaltySkies plugin ) { this.plugin = plugin; }

  public static void connect () {

    //"45.95.52.50", "skypvp", "philtest", "JCtZG3FzYnqcNWyT"
    String hostAddress = "45.95.52.50";
    String database    = "vaince";
    String username    = "vaince_login";
    String password    = "vaince";
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
