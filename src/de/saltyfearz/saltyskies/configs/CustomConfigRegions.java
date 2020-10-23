package de.saltyfearz.saltyskies.configs;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.ResultSQL;
import de.saltyfearz.saltyskies.mysql.UpdateSQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CustomConfigRegions {

    final private SaltySkies plugin;

    public CustomConfigRegions(final SaltySkies plugin) {
        this.plugin = plugin;
    }

    public static void addRegion ( final Location loc1, final Location loc2, final Player owner, String regionName ) {

        regionName = regionName.equals( " " ) ? owner.getName() : regionName;


        final String insertRegion = "INSERT INTO REGIONS ( OWNERUUID, REGIONNAME, WORLDNAME, POSITIONX_1, POSITIONY_1, POSITIONZ_1, POSITIONX_2, POSITIONY_2, POSITIONZ_2 ) VALUES ('" + owner.getUniqueId().toString() + "', '" + regionName + "', '" + loc1.getX() + "', '" + loc1.getY() + "', '" + loc1.getZ() + "', '" + loc2.getX() + "', '" + loc2.getY() + "', '" + loc2.getZ() + "');";

        try {

            UpdateSQL.updateSQL( insertRegion, CreateConnectionSQL.getConnection( ) );

        } catch ( SQLException exc ) {

            exc.printStackTrace();

        }

    }

    public ArrayList<Location> getLocation ( final Player player ) {

        final String getRegion = "SELECT WORLDNAME, POSITIONX_1, POSITIONY_1, POSITIONZ_1, POSITIONX_2, POSITIONY_2, POSITIONZ_2 FROM REGIONS WHERE ( " + player.getLocation().getX() + " BETWEEN POSITIONX_1 AND POSITIONX_2 ) AND ( " + player.getLocation().getY() + " BETWEEN POSITIONY_1 AND POSITIONY_2 ) AND ( " + player.getLocation().getZ() + " BETWEEN POSITIONZ_1 AND POSITIONZ_2 ) AND WORLD = '" + player.getWorld().getName() + "';";

        try {

            ResultSet result = ResultSQL.resultSQL( getRegion, CreateConnectionSQL.getConnection( ) );

            final World world = Bukkit.getWorld( result.getString( 2 ) );

            final double posX_1 = result.getDouble( 3 );
            final double posY_1 = result.getDouble( 4 );
            final double posZ_1 = result.getDouble( 5 );

            final double posX_2 = result.getDouble( 6 );
            final double posY_2 = result.getDouble( 7 );
            final double posZ_2 = result.getDouble( 8 );

            ArrayList <Location> locations = new ArrayList<>();

            locations.add( 0, new Location( world, posX_1, posY_1, posZ_1 ) );
            locations.add( 1, new Location( world, posX_2, posY_2, posZ_2 ) );


            return locations;

        } catch ( SQLException exc ) {

            exc.printStackTrace();

            return null;
        }

    }

    public boolean isInRegion ( final Location loc, final Location locA, final Location locB ) {

        double maxX = ( Math.max( locA.getX( ), locB.getX( ) ) );
        double minX = ( Math.min( locA.getX( ), locB.getX( ) ) );

        double maxY = ( Math.max( locA.getY( ), locB.getY( ) ) );
        double minY = ( Math.min( locA.getY( ), locB.getY( ) ) );

        double maxZ = ( Math.max( locA.getZ( ), locB.getZ( ) ) );
        double minZ = ( Math.min( locA.getZ( ), locB.getZ( ) ) );


        return ( ( !( loc.getX( ) >= minX ) ) && ( ( loc.getX( ) <= maxX ) ) && ( !( loc.getZ( ) <= maxZ ) ) && ( !( loc.getY( ) <= maxY ) ) || ( !( loc.getY( ) >= minY ) ) );

    }
}