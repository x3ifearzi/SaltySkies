package de.saltyfearz.saltyskies.configs;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.WorldGuardCommand;
import de.saltyfearz.saltyskies.regions.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CustomConfigRegions {

    final private SaltySkies plugin;

    private File regionsFile;

    private FileConfiguration regionsFileConfiguration;

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

    public void addRegion( final Location loc1, final Location loc2 ) {

        final int regionID = regionsFileConfiguration.getKeys( false ).size();

        regionsFileConfiguration.set( regionID + ".pos1.world", loc1.getWorld().getName() );
        regionsFileConfiguration.set( regionID + ".pos1.x", loc1.getBlockX() );
        regionsFileConfiguration.set( regionID + ".pos1.y", loc1.getBlockY() );
        regionsFileConfiguration.set( regionID + ".pos1.z", loc1.getBlockZ() );

        regionsFileConfiguration.set( regionID + ".pos2.world", loc2.getWorld().getName() );
        regionsFileConfiguration.set( regionID + ".pos2.x", loc2.getBlockX() );
        regionsFileConfiguration.set( regionID + ".pos2.y", loc2.getBlockY() );
        regionsFileConfiguration.set( regionID + ".pos2.z", loc2.getBlockZ() );

        setRegionsFile();

    }

    public void registerRegions( ) {

        for ( int i = 0; i < regionsFileConfiguration.getKeys( false ).size(); i++ ) {

            WorldGuardCommand.regions.add( new Cuboid( getLocation( i, "pos1" ), getLocation( i, "pos2" ) ) );
        }
    }

    public Location getLocation ( final int regionID, final String name ) {

        return new Location( Bukkit.getWorld( Objects.requireNonNull( regionsFileConfiguration.getString( regionID + "." + name + ".world" ) ) ), regionsFileConfiguration.getDouble( regionID + "." + name + ".x"), regionsFileConfiguration.getDouble( regionID + "." + name + ".y"), regionsFileConfiguration.getDouble( regionID + "." + name + ".z") );

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