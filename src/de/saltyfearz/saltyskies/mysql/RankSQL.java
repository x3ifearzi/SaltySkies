package de.saltyfearz.saltyskies.mysql;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankSQL {

    private static final SaltySkies plugin = JavaPlugin.getPlugin( SaltySkies.class );

    private RankSQL() { }

    public static List <String> getRanks() {

        List<String> rankList = new ArrayList <>();

        final String query = "SELECT DISTINCT(RANK) FROM RANKS;";

        try {

            ResultSet result = ResultSQL.resultSQL( query, CreateConnectionSQL.getConnection( ) );

            while ( result.next() ) {

                rankList.add( result.getString( "RANK" ) );
            }

            return rankList;

        } catch ( SQLException exc ) {

            exc.printStackTrace();
            return rankList;

        }
    }

    public static void setRank( final Player player, final String rank ) {

        final String query = "INSERT INTO PLAYERRANKS (PLAYERUUID, RANK) VALUES ( '" + player.getUniqueId().toString() + "', '(SELECT RANK FROM RANKS WHERE RANK = '" + rank + "')';";

        try {

            UpdateSQL.updateSQL( query, CreateConnectionSQL.getConnection() );

        } catch ( SQLException exc ) {

            exc.printStackTrace();

            player.sendMessage( plugin.getMsgDE().getMessageErrorDE( "rank-command", "specifiedRankDoesNotExist" ));

        }
    }
}
