package de.saltyfearz.saltyskies.configs;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.WorldGuardCommand;
import de.saltyfearz.saltyskies.regions.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CustomConfigRegions {

    final private SaltySkies plugin;

    private static File regionsFile;

    private static FileConfiguration regionsFileConfiguration;

    public CustomConfigRegions(final SaltySkies plugin) {
        this.plugin = plugin;
    }

    public void generateRegionsFile() {

        regionsFile = new File( Bukkit.getServer().getPluginManager()
                .getPlugin("SaltySkies")
                .getDataFolder(), "regions.yml");

        if (!regionsFile.exists())
            return;

        try {

            regionsFile.mkdir();

            regionsFile.createNewFile();

        } catch ( IOException exc) {

            exc.printStackTrace();

        }
        reloadRegionsFile();
    }

    public void addRegion( final Location loc1, final Location loc2, final Player owner, String regionName ) {

        regionName = regionName.equals( " " ) ? owner.getName() : regionName;

        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos1.world", owner.getWorld().getName() );
        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos1.x", loc1.getBlockX() );
        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos1.y", loc1.getBlockY() );
        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos1.z", loc1.getBlockZ() );

        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos2.world", owner.getWorld().getName() );
        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos2.x", loc2.getBlockX() );
        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos2.y", loc2.getBlockY() );
        regionsFileConfiguration.set( regionName + "." + owner.getUniqueId().toString() + ".pos2.z", loc2.getBlockZ() );

        setRegionsFile();

    }

    public void registerRegions( final Player player ) {

        WorldGuardCommand command = new WorldGuardCommand( plugin );

        for ( int i = 0; i < regionsFileConfiguration.getKeys( false ).size(); i++ ) {

            command.regions.add( new Cuboid( getLocation( player, "pos1" ), getLocation( player, "pos2" ) ) );

        }
    }

    public Location getLocation ( final Player player, final String name ) {

        return new Location( Bukkit.getWorld( ( regionsFileConfiguration.getString(     "*." + player.getUniqueId().toString() + "." + name + ".world" ) ) ), regionsFileConfiguration.getDouble( "*." + player.getUniqueId().toString() + "." + name + ".x"), regionsFileConfiguration.getDouble( "*." + player.getUniqueId().toString() + "." + name + ".y"), regionsFileConfiguration.getDouble( "*." + player.getUniqueId().toString() + "." + name + ".z") );

    }

    public boolean isInRegion ( final Location loc, final Location locA, final Location locB ) {

        double maxX = ( Math.max( locA.getX( ), locB.getX( ) ) );
        double minX = ( Math.min( locA.getX( ), locB.getX( ) ) );

        double maxY = ( Math.max( locA.getY( ), locB.getY( ) ) );
        double minY = ( Math.min( locA.getY( ), locB.getY( ) ) );

        double maxZ = ( Math.max( locA.getZ( ), locB.getZ( ) ) );
        double minZ = ( Math.min( locA.getZ( ), locB.getZ( ) ) );

        return ( ( !( loc.getX( ) <= maxX ) ) || ( !( loc.getX( ) >= minX ) ) || ( !( loc.getZ( ) <= maxZ ) ) || ( !( loc.getZ( ) >= minZ ) ) || ( !( loc.getY( ) <= maxY ) ) || ( !( loc.getY( ) >= minY ) ) );

    }

    public void setRegionsFile() {

        try {

            regionsFileConfiguration.save(regionsFile);

        } catch (IOException exc) {

            exc.printStackTrace();
        }

    }

    public void reloadRegionsFile() {

        regionsFileConfiguration = YamlConfiguration.loadConfiguration(regionsFile);

    }

    public FileConfiguration getRegionsFileConfiguration() {
        return regionsFileConfiguration;
    }

}