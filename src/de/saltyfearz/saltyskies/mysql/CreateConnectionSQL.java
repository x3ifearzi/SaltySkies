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

 /*"45.95.52.50", "skypvp", "philtest", "JCtZG3FzYnqcNWyT"
    String hostAddress = "45.95.52.50";
    String database    = "vaince";
    String username    = "vaince_login";
    String password    = "vaince";
    int portAddress = 3306;
*/
    String hostAddress = "localhost";
    String database = "vaince";
    String username = "root";
    String password = "";
    int portAddress = 3306;

 /*
    String hostAddress = "localhost";
    String database = "u15729-1147_Vaince";
    String username = "x3IFeaRzI";
    String password = "1INJV6yA";
    int portAddress = 3306;
*/

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
