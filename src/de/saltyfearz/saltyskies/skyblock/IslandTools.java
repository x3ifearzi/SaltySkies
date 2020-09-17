package de.saltyfearz.saltyskies.skyblock;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class IslandTools {

    final private SaltySkies plugin;

    public IslandTools ( final SaltySkies plugin ) { this.plugin = plugin; }

    public Coordinates nextIslandLocation ( final double posX, final double posZ ) {

        Coordinates coords = new Coordinates( posX, posZ );

        final int placeBetweenIslands = 400;

        if ( posX < posZ ) {

            if ( ( ( -1 ) * posX ) < posZ ) {

                coords.posX = coords.posX + placeBetweenIslands;
                return coords;

            }

            coords.posZ = posZ + placeBetweenIslands;
            return coords;

        }
        if ( posX > posZ ) {

            if ( ( ( -1 ) * posX ) >= posZ ) {

                coords.posX = coords.posX - placeBetweenIslands;
                return coords;

            }

            coords.posZ = coords.posZ - placeBetweenIslands;

        }
        if ( posX <= 0 ) {

            coords.posZ = coords.posZ + placeBetweenIslands;
            return coords;

        }

        coords.posZ = coords.posZ - placeBetweenIslands;
        return coords;

    }

    public void generateIsland ( final int posX, final int posZ ) {

        int posY = 64;

        org.bukkit.World worldNormal = Bukkit.getServer( ).getWorld( "Skyblock" );

        Location loc = new Location( worldNormal, posX, posY, posZ );

/*        WorldEditPlugin wEPl = ( WorldEditPlugin ) Bukkit.getPluginManager( ).getPlugin( "WorldEdit" ); //TODO

        File schematic = new File( "plugins/WorldEdit/schematics/SkyBlockNormal.schem" );

        ClipboardFormat format = ClipboardFormats.findByFile( schematic );

        World world = BukkitAdapter.adapt( worldNormal );

        Clipboard clipboard = null;

        try {

            ClipboardReader reader = format.getReader( new FileInputStream( schematic ) );

            clipboard = reader.read( );

            EditSession editSession = wEPl.getWorldEdit( ).getEditSessionFactory( ).getEditSession( world, -1 );

            Operation operation = new ClipboardHolder( clipboard ).createPaste( editSession ).to( BlockVector3.at( posX, posY, posZ ) ).ignoreAirBlocks( true ).build( );

            Operations.complete( operation );

        } catch ( IOException | WorldEditException exc ) {

            exc.printStackTrace( );

        }*/

        Block blockToChange = worldNormal.getBlockAt( posX + 2, posY, posZ );

        blockToChange.getRelative( BlockFace.WEST_NORTH_WEST );
        blockToChange.setType( Material.CHEST );

        Chest chest = ( Chest ) blockToChange.getState( );

        Inventory inv = chest.getInventory( );

        inv.clear( );

        ArrayList < ItemStack > items = new ArrayList <>( );

        items.add( new ItemStack( Material.LAVA_BUCKET, 2 ) );
        items.add( new ItemStack( Material.WATER_BUCKET, 2 ) );
        items.add( new ItemStack( Material.COBBLESTONE, 16 ) );
        items.add( new ItemStack( Material.OAK_LOG, 16 ) );
        items.add( new ItemStack( Material.SAND, 16 ) );
        items.add( new ItemStack( Material.OAK_SAPLING, 4 ) );
        items.add( new ItemStack( Material.BONE, 2 ) );
        items.add( new ItemStack( Material.SUGAR_CANE, 2 ) );
        items.add( new ItemStack( Material.BROWN_MUSHROOM, 2 ) );
        items.add( new ItemStack( Material.RED_MUSHROOM, 2 ) );
        items.add( new ItemStack( Material.WHEAT_SEEDS, 2 ) );
        items.add( new ItemStack( Material.MELON_SEEDS, 2 ) );
        items.add( new ItemStack( Material.PUMPKIN, 1 ) );
        items.add( new ItemStack( Material.CACTUS, 2 ) );

        for ( ItemStack item : items ) {

            inv.addItem( item );

        }

        blockToChange = worldNormal.getBlockAt( posX, posY - 1, posZ );

        blockToChange.setType( Material.GLOWSTONE );

    }

/*    public void deleteIsland ( final int centerX, final int centerZ ) { //TODO

        for ( int x = centerX - ( Island.getIslandSize( ) / 2 ); x <= centerX + ( Island.getIslandSize( ) / 2 ); x++ ) {

            for ( int y = 0; y < plugin.Skyblock.getMaxHeight( ); y++ ) {

                for ( int z = centerZ - ( Island.getIslandSize( ) / 2 ); z <= centerZ + ( Island.getIslandSize( ) / 2 ); z++ ) {

                    Block block = plugin.Skyblock.getBlockAt( x, y, z );

                    if ( block.getType( ) != Material.AIR ) {

                        block.setType( Material.AIR );

                    }
                }
            }
        }
    }*/
}
