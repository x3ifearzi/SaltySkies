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

    final String createMoneyTable = "BALANCE ( ID int ( 255 ) NOT NULL AUTO_INCREMENT, TOKENS double, PRIMARY KEY ( ID ), FOREIGN KEY ( PLAYERUUID ) REFERENCES PLAYERDATA ( PLAYERUUID ));";

    CreateTableSQL.createTableSQL(createMoneyTable);

  }
}
