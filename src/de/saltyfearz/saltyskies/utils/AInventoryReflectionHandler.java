package de.saltyfearz.saltyskies.utils;

import de.saltyfearz.saltyskies.versioncontroller.VersionController;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AInventoryReflectionHandler implements AInventoryVersionHandler {

    private Class < ? > BlockPosition;
    private Class < ? > PacketPlayOutOpenWindow;
    private Class < ? > ContainerAnvil;
    private Class < ? > ChatMessage;
    private Class < ? > EntityHuman;

    private Inventory inv;

    public AInventoryReflectionHandler ( final Player player, final AInventory.AnvilClickEventHandler anvilClickEventHandler ) {

        loadClasses( );
        PlayerUtils.getInstance( ).setPlayerMeta( player, "AInventory", anvilClickEventHandler );

    }

    @Override
    public Inventory getInventory ( ) {

        return inv;

    }

    @Override
    public void loadClasses ( ) {

        BlockPosition = VersionController.getInstance( ).getClass( "BlockPosition" );
        PacketPlayOutOpenWindow = VersionController.getInstance( ).getClass( "PacketPlayOutOpenWindow" );
        ContainerAnvil = VersionController.getInstance( ).getClass( "ContainerAnvil" );
        EntityHuman = VersionController.getInstance( ).getClass( "EntityHuman" );
        ChatMessage = VersionController.getInstance( ).getClass( "ChatMessage" );

    }

    @Override
    public void open ( final Player player, final HashMap < AInventory.AnvilSlot, ItemStack > items ) {

        player.setLevel( player.getLevel( ) + 1 );

        try {

            Object p = VersionController.getInstance( ).getHandle( player );

            Object container = ContainerAnvil.getConstructor( VersionController.getInstance( ).getClass( "PlayerInventory" ), VersionController.getInstance( ).getClass( "World" ), BlockPosition, EntityHuman ).newInstance( VersionController.getInstance( ).getPlayerField( player, "inventory" ), VersionController.getInstance( ).getPlayerField( player, "world" ), BlockPosition.getConstructor( int.class, int.class, int.class ).newInstance( 0, 0, 0 ), p );

            VersionController.getInstance( ).getField( VersionController.getInstance( ).getClass( "Container" ), "checkReachable" ).set( container, false );

            Object bukkitView = VersionController.getInstance( ).invokeMethod( "getBukkitView", container );

            inv = ( Inventory ) VersionController.getInstance( ).invokeMethod( "getTopInventory", bukkitView );

            for ( AInventory.AnvilSlot slot : items.keySet( ) ) {

                inv.setItem( slot.getSlot( ), items.get( slot ) );

            }

            int c = ( int ) VersionController.getInstance( ).invokeMethod( "nextContainerCounter", p );

            Constructor < ? > chatMessageConstructor = ChatMessage.getConstructor( String.class, Object[].class );

            Object playerConnection = VersionController.getInstance( ).getPlayerField( player, "playerConnection" );

            Object packet = PacketPlayOutOpenWindow.getConstructor( int.class, String.class, VersionController.getInstance( ).getClass( "IChatBaseComponent" ), int.class ).newInstance( c, "minecraft:anvil", chatMessageConstructor.newInstance( "Repairing", new Object[] { } ), 0 );

            Method sendPacket = VersionController.getInstance( ).getMethod( "sendPacket", playerConnection.getClass( ), PacketPlayOutOpenWindow );

            sendPacket.invoke( playerConnection, packet );

            Field activeContainerField = VersionController.getInstance( ).getField( EntityHuman, "activeContainer" );

            if ( activeContainerField != null ) {

                activeContainerField.set( p, container );

                VersionController.getInstance( ).getField( VersionController.getInstance( ).getClass( "Container" ), "windowId" ).set( activeContainerField.get( p ), c );
                VersionController.getInstance( ).getMethod( "addSlotListener", activeContainerField.get( p ).getClass( ), p.getClass( ) ).invoke( activeContainerField.get( p ), p );

            }

        } catch ( Exception exc ) {

            exc.printStackTrace();

        }
    }
}
