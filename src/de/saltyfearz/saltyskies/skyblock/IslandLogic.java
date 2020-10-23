package de.saltyfearz.saltyskies.skyblock;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.ResultSQL;
import de.saltyfearz.saltyskies.mysql.UpdateSQL;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IslandLogic {

    final private SaltySkies plugin;

    private Player playerOwner;

    private String uuidOwner;

    private int positionX;
    private int positionZ;
    private int islandID;

    private int spawnX;
    private int spawnZ;

    private ArrayList < String > uuidMember;

    public IslandLogic ( final SaltySkies plugin, final String uuidOwner ) {

        this.plugin = plugin;

        this.uuidOwner = uuidOwner;

    }

    public Player getPlayerOwner ( ) {
        return playerOwner;
    }

    public String getUuidOwner ( ) {
        return uuidOwner;
    }

    public int getPositionX ( ) {
        return positionX;
    }

    public int getPositionZ ( ) {
        return positionZ;
    }

    public int getIslandID ( ) {
        return islandID;
    }

    public ArrayList < String > getUuidMember ( ) {
        return uuidMember;
    }

    public void create ( final String islandName ) throws SQLException {

        IslandLogic emptyIsland = getEmptyIsland( );

        Coordinates last = getLastIsland( );

        IslandTools iTools = new IslandTools( plugin );

        Coordinates crds = iTools.nextIslandLocation( last.posX, last.posZ );

        this.positionX = (int) crds.posX;
        this.positionZ = (int) crds.posZ;

        //TODO ISLANDNAME

        String sql = "INSERT INTO Island (OWNERUUID, ISLANDNAME, POSITIONX, POSITIONZ) VALUES ('" + this.uuidOwner + "', " + islandName + "', " + this.positionX + ", " + this.positionZ + ");";

        UpdateSQL.updateSQL( sql, CreateConnectionSQL.getConnection() );

    }

    public void teleportToIsland( final Player player ) {

        int height = 65;

        while ( SaltySkies.skyblockWorld.getBlockAt( this.positionX, height, this.positionZ ).getType() != Material.AIR || SaltySkies.skyblockWorld.getBlockAt( this.positionX, height + 1, this.positionZ ).getType() != Material.AIR ) {

            height++;

        }

        SaltySkies.skyblockWorld.loadChunk( this.positionX, this.positionZ );

        player.teleport( new Location( SaltySkies.skyblockWorld, ( this.positionX + .5 ), ( height + 3 ), ( this.positionZ + .5 ) ) );
    }

    public void saveIsland( ) {

        plugin.playerIslands.remove(this.uuidOwner);

        plugin.playerIslands.put(this.uuidOwner, this);

    }

    private IslandLogic getEmptyIsland ( ) { return null; }

    private Coordinates getLastIsland ( ) throws SQLException {

        final String sql = "SELECT ISLANDID, OWNERUUID, POSITIONX, POSITIONZ FROM Island ORDER BY ID DESC LIMIT 1;";

        ResultSet result = ResultSQL.resultSQL( sql, CreateConnectionSQL.getConnection( ) );

        result.next();

        if ( result.getRow() == 1 ) {

            return new Coordinates( result.getInt( "POSITIONX" ), result.getInt( "POSITIONZ" ) );

        }

        return new Coordinates( this.spawnX, this.spawnZ );


    }
}
