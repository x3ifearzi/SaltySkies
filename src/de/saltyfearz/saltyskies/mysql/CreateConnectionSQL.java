package de.saltyfearz.saltyskies.mysql;

import de.saltyfearz.saltyskies.SaltySkies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnectionSQL {

  final private SaltySkies plugin;

  private static Connection con;

  public CreateConnectionSQL ( final SaltySkies plugin ) { this.plugin = plugin; }

  public static void connect () {

/*    String hostAddress = "localhost";
    String database = "vaince";
    String username = "root";
    String password = "";
    int portAddress = 3306;*/

    String hostAddress = "sql7.freemysqlhosting.net";
    String database = "sql7388332";
    String username = "sql7388332";
    String password = "zmwW4gqgfF";
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

  public static Connection getConnection () { return con; }
}
