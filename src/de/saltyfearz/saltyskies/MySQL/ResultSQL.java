package de.saltyfearz.saltyskies.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSQL {

  //changedRows ? ?

  public static ResultSet resultSQL (final String query, PreparedStatement pS, final Connection con ) throws SQLException {

    final ResultSet result;

    pS = con.prepareStatement( query );

    return pS.executeQuery();

  }
}
