package de.saltyfearz.saltyskies.configs;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.versioncontroller.VersionController;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ItemConfigShop {

    private final SaltySkies plugin;

    private File file;
    private FileConfiguration fileConfiguration;

    public ItemConfigShop ( final SaltySkies plugin ) { this.plugin = plugin; }

    //Finds or Generates the Config File
    public void setupFile ( ) {
        file = new File( Objects.requireNonNull( Bukkit.getServer( ).getPluginManager( ).getPlugin( "SaltySkies" ) ).getDataFolder( ), "shopitems.yml" );

        if ( !file.exists( ) ) {
            try {
                file.mkdir( );
                file.createNewFile( );
            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration( file );
    }

    public ItemStack getItem ( final String nameOfItem ) {

        if ( nameOfItem.equals( "" ) ) return plugin.getiLI( ).ERROR_BARRIER;

        LinkedHashMap < String, String > hashMap = new LinkedHashMap <>( );

        String shopKey = "shop.items.";

        hashMap.put( "item", fileConfiguration.get( shopKey + nameOfItem + ".item" ).toString( ) );
        hashMap.put( "buy", fileConfiguration.get( shopKey + nameOfItem + ".item_buy_price" ).toString( ) );
        hashMap.put( "sell", fileConfiguration.get( shopKey + nameOfItem + ".item_sell_price" ).toString( ) );
        hashMap.put( "displayName", fileConfiguration.get( shopKey + nameOfItem + ".item_displayname" ).toString( ) );

        Material item = Material.valueOf( hashMap.get( "item" ).toUpperCase( ) );

        ArrayList < String > itemLore = new ArrayList <>( );

        itemLore.add( "§6Kaufpreis     x1: " + hashMap.get( "buy" ) );
        itemLore.add( "§6Verkaufspreis x1: " + hashMap.get( "sell" ) );

        String displayname = hashMap.get( "displayName" );

        return VersionController.getInstance( ).getdVS( ).item( item, displayname, itemLore );

    }

    public FileConfiguration getFile ( ) {
        return fileConfiguration;
    }

    public void setFile ( ) {
        try {

            fileConfiguration.save( file );

        } catch ( IOException e ) {

            e.printStackTrace( );

        }
    }
}