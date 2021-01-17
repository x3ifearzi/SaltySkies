package de.saltyfearz.saltyskies.mysql;

import de.saltyfearz.saltyskies.SaltySkies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoneySQL {

  final private SaltySkies plugin;

  private static Connection con = CreateConnectionSQL.getConnection();

  public MoneySQL( final SaltySkies plugin ) {
    this.plugin = plugin;
  }

  public static void executePlayerSQL () throws SQLException {

    final String createMoneyTable = "PLAYERMONEY ( ID int ( 255 ) NOT NULL AUTO_INCREMENT, MONEY double, PLAYERUUID varchar( 48 ), PRIMARY KEY ( ID ));";

    CreateTableSQL.createTableSQL(createMoneyTable, con);

  }
}
