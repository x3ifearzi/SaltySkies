package de.saltyfearz.saltyskies.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSQL {

  //changedRows ? ?

  public static int updateSQL (final String query, final Connection con ) throws SQLException {

    int changedRows = -1;

    PreparedStatement pS = con.prepareStatement( query );

    changedRows = pS.executeUpdate();

    return changedRows;
  }

}
