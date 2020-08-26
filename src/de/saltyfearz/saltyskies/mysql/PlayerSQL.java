package de.saltyfearz.saltyskies.mysql;

import de.saltyfearz.saltyskies.SaltySkies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerSQL {

  final private SaltySkies plugin;

  private static Connection con = CreateConnectionSQL.getConnection();

  public PlayerSQL( final SaltySkies plugin ) {
    this.plugin = plugin;
  }

  public static void executePlayerSQL () throws SQLException{

    final String createPlayerTable = "PLAYERDATA ( PLAYERUUID varchar ( 48 ), PLAYERNAME varchar ( 16 ), IP varchar ( 16 ), PRIMARY KEY ( PLAYERUUID ));";

    final String createPlayerPunishTable = "PLAYERPUNISH ( ID int (255) NOT NULL AUTO_INCREMENT, IS_BANNED boolean, IS_BANNED_UNTIL bigint, IS_MUTED boolean, IS_MUTED_UNTIL bigint, PRIMARY KEY ( ID ), FOREIGN KEY ( PLAYERUUID ) REFERENCES PLAYERDATA ( PLAYERUUID ));";

    CreateTableSQL.createTableSQL(createPlayerTable, con);

    CreateTableSQL.createTableSQL(createPlayerPunishTable, con);
  }

}
