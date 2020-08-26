package de.saltyfearz.saltyskies.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableSQL {

  public static void createTableSQL (final String query, final Connection con ) throws SQLException {

    PreparedStatement pS = con.prepareStatement( "CREATE TABLE IF NOT EXISTS " + query );

    pS.executeUpdate();

  }

}
