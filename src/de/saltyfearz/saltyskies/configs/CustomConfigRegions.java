package de.saltyfearz.saltyskies.configs;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.ResultSQL;
import de.saltyfearz.saltyskies.mysql.UpdateSQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomConfigRegions {

    public static void addRegion ( final Location loc1, final Location loc2, final Player owner, String regionName ) {

        regionName = regionName.equals( " " ) ? owner.getName() : regionName;


        final String insertRegion = "INSERT INTO REGIONS ( OWNERUUID, REGIONNAME, WORLDNAME, POSITIONX_1, POSITIONY_1, POSITIONZ_1, POSITIONX_2, POSITIONY_2, POSITIONZ_2 ) VALUES ('" + owner.getUniqueId().toString() + "', '" + regionName + "', '" + owner.getWorld().getName() + "','" + loc1.getX() + "', '" + loc1.getY() + "', '" + loc1.getZ() + "', '" + loc2.getX() + "', '" + loc2.getY() + "', '" + loc2.getZ() + "');";

        try {

            UpdateSQL.updateSQL( insertRegion, CreateConnectionSQL.getConnection( ) );

        } catch ( SQLException exc ) {

            exc.printStackTrace();

        }

    }

    public static ArrayList<Location> getLocation(final Player player, final Block eventBlock) {

        if (eventBlock == null ) return new ArrayList<>();

            final String getRegion = "SELECT `WORLDNAME`,`POSITIONX_1`,`POSITIONY_1`,`POSITIONZ_1`,`POSITIONX_2`,`POSITIONY_2`,`POSITIONZ_2` FROM `REGIONS` WHERE `POSITIONX_1` >= " + eventBlock.getX() + " AND `POSITIONX_2` <= " + eventBlock.getX() + " AND `POSITIONZ_1` >= " + eventBlock.getZ() + " AND `POSITIONZ_2` <= " + eventBlock.getZ() + " AND WORLDNAME = '" + player.getWorld().getName() + "';";

        try {

            ResultSet result = ResultSQL.resultSQL( getRegion, CreateConnectionSQL.getConnection( ) );

            if (result.next()) {
                final World world = Bukkit.getServer().getWorld(result.getString(1));

                final double posX_1 = result.getDouble(2);
                final double posY_1 = result.getDouble(3);
                final double posZ_1 = result.getDouble(4);

                final double posX_2 = result.getDouble(5);
                final double posY_2 = result.getDouble(6);
                final double posZ_2 = result.getDouble(7);

                ArrayList<Location> locations = new ArrayList<>();

                locations.add(0, new Location(world, posX_1, posY_1, posZ_1));
                locations.add(1, new Location(world, posX_2, posY_2, posZ_2));

                return locations;
            } else {
                return null;
            }
        } catch ( SQLException exc ) {

            exc.printStackTrace();
            return null;

        }

    }

    public static boolean isInRegion(final Player player, final Block block) {

        final ArrayList<Location> locations = CustomConfigRegions.getLocation(player, block);
        final Location loc  = block.getLocation();

        if (locations != null) {

            final Location locA = locations.get(0);
            final Location locB = locations.get(1);

            double maxX = (Math.max(locA.getX(), locB.getX()));
            double minX = (Math.min(locA.getX(), locB.getX()));

            double maxZ = (Math.max(locA.getZ(), locB.getZ()));
            double minZ = (Math.min(locA.getZ(), locB.getZ()));

            return ((loc.getX() >= minX && loc.getX() <= maxX) && (loc.getZ() >= minZ && loc.getZ() <= maxZ));

        } else {

            return false;

        }
    }
}