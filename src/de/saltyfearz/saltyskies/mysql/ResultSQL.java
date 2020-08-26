package de.saltyfearz.saltyskies.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSQL {

  public static ResultSet resultSQL (final String query, final Connection con ) throws SQLException {

    final ResultSet result;

    PreparedStatement pS = con.prepareStatement( query );

    return pS.executeQuery();

  }
}
