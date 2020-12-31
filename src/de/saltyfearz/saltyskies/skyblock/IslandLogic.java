package de.saltyfearz.saltyskies.skyblock;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.WorldGuardCommand;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.ResultSQL;
import de.saltyfearz.saltyskies.mysql.UpdateSQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class IslandLogic {

    final private SaltySkies plugin;

    private Player playerOwner;

    private String uuidOwner;
    private String islandName;

    private int positionX;
    private int positionZ;
    private int islandID;

    private int spawnX = 0;
    private int spawnZ = 0;

    private ArrayList < String > uuidMember;

    public IslandLogic ( final SaltySkies plugin, final String uuidOwner ) {

        this.plugin = plugin;

        this.uuidOwner = uuidOwner;

    }

    public void create ( final String islandName) {

        try {

            Coordinates last = getLastIsland();

            IslandTools iTools = new IslandTools( plugin );

            Coordinates crds = iTools.nextIslandLocation(last.posX, last.posZ);

            this.positionX = (int) crds.posX;
            this.positionZ = (int) crds.posZ;
            this.islandName = islandName;

            String sql = "INSERT INTO SKYBLOCKISLANDS (OWNERUUID, ISLANDNAME, POSITIONX, POSITIONY, POSITIONZ) VALUES ('" + this.uuidOwner + "', '" + islandName + "', " + this.positionX + ", " + 64 + ", " + this.positionZ + ");";

            UpdateSQL.updateSQL( sql, CreateConnectionSQL.getConnection() );

        } catch ( SQLException exc ) {

            exc.printStackTrace();

        }
    }

    public void teleportToIsland( final Player player ) {

        int height = 64;

        while ( SaltySkies.skyblockWorld.getBlockAt( this.positionX, height, this.positionZ ).getType() != Material.AIR || SaltySkies.skyblockWorld.getBlockAt( this.positionX, height + 1, this.positionZ ).getType() != Material.AIR ) {

            height++;

        }

        SaltySkies.skyblockWorld.loadChunk( this.positionX, this.positionZ );

        player.teleport( new Location(SaltySkies.skyblockWorld, ( this.positionX + .5 ), ( height + 3 ), ( this.positionZ + .5 ) ) );
    }

    public void saveIsland( ) {

        plugin.playerIslands.remove(this.uuidOwner);

        plugin.playerIslands.put(this.uuidOwner, this);

    }

    public void load() {

        if ( !this.loadData() ) {

            this.loadSQL();
            plugin.playerIslands.put( uuidOwner, this );

        }
    }

    public void tpHome( final Player player ) {

        int height = 64;

        while (SaltySkies.skyblockWorld.getBlockAt(this.positionX, height, this.positionZ).getType() != Material.AIR || SaltySkies.skyblockWorld.getBlockAt(this.positionX, height + 1, this.positionZ).getType() != Material.AIR) {

            height++;

        }

        SaltySkies.skyblockWorld.loadChunk( this.positionX, this.positionZ );

        player.teleport( new Location( SaltySkies.skyblockWorld, (this.positionX + .5), (height + 3), (this.positionZ + .5) ) );

    }

    private Coordinates getLastIsland() throws SQLException {

        final String sql = "SELECT * FROM SKYBLOCKISLANDS ORDER BY ID DESC LIMIT 1;";

        ResultSet result = ResultSQL.resultSQL( sql, CreateConnectionSQL.getConnection( ) );

        result.next();

        if ( result.getRow() == 1 ) {

            return new Coordinates( result.getInt( "POSITIONX" ), result.getInt( "POSITIONZ" ) );

        }

        return new Coordinates( spawnX, spawnZ );

    }

    private boolean loadData() {

        IslandLogic tmp = plugin.playerIslands.get( this.uuidOwner );

        if ( tmp == null ) {

            return false;

        } else {

            this.positionX = tmp.positionX;
            this.positionZ = tmp.positionZ;
            this.islandID = tmp.islandID;
            this.islandName = tmp.islandName;

        }
        this.uuidOwner = null;

        for ( Player player : plugin.getServer().getOnlinePlayers() ) {

            if ( player.getUniqueId().toString().equals( this.uuidOwner )) {

                this.playerOwner = player;
                break;

            }
        }
        return true;
    }

    private void loadSQL() {

        String query = "SELECT * FROM SKYBLOCKISLANDS WHERE OWNERUUID = '" + this.uuidOwner + "';";

        try {

            ResultSet result = ResultSQL.resultSQL( query, CreateConnectionSQL.getConnection() );

            if ( !result.next() ) return;

            this.positionX = ( int ) result.getDouble( "POSITIONX" );
            this.positionZ = ( int ) result.getDouble( "POSITIONZ" );
            this.islandID  = result.getInt( "ID" );
            this.playerOwner = Bukkit.getServer().getPlayer( UUID.fromString( result.getString( "OWNERUUID" ) ) );
            this.islandName = result.getString( "ISLANDNAME" );

        } catch ( SQLException exc ) {

            exc.printStackTrace();

        }
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

    public String getIslandName() { return islandName; }

    public ArrayList < String > getUuidMember ( ) {
        return uuidMember;
    }


}
