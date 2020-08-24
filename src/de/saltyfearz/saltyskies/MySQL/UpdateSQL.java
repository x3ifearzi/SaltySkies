package de.saltyfearz.saltyskies.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSQL {

  public static int updateSQL (final String query, PreparedStatement pS, final Connection con ) throws SQLException {

    int changedRows = -1;

    pS = con.prepareStatement( query );

    changedRows = pS.executeUpdate();

    return changedRows;
  }

}
