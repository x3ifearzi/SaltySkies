package de.saltyfearz.saltyskies.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableSQL {

  public static void createTableSQL (final String query, PreparedStatement pS, final Connection con ) throws SQLException {

    pS = con.prepareStatement( "CREATE TABLE IF NOT EXISTS " + query );

    pS.executeUpdate();

  }
}
