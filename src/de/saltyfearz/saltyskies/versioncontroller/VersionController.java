package de.saltyfearz.saltyskies.versioncontroller;

import de.saltyfearz.saltyskies.interfaces.differentVersionSystem;
import de.saltyfearz.saltyskies.items.v_1_16_1;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class VersionController {

    private String version;

    private differentVersionSystem dVS;

    private static VersionController instance;

    public VersionController() { this.setupVersion(); }

    private void setupVersion() {

        this.version = Bukkit.getServer( ).getClass().getPackage().getName().replace( ".", "," ).split( "," )[3];

        final String version = this.version;

        switch ( version ) {

            case "v_1_16_1" : {

                this.dVS = new v_1_16_1();

            }

            default: {

                this.dVS = new v_1_16_1();

            }

        }

    }

    public differentVersionSystem getdVS() {

        if ( this.version == null ) this.setupVersion();

        return this.dVS;

    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {

        String versionName = Bukkit.getServer().getClass().getPackage().getName();

        return versionName.substring( versionName.lastIndexOf( '.' ) + 1 ) + ".";

    }

    /**
     * Gets the specified class.
     *
     * @param className
     *            the class name
     * @return the specified class
     */
    public Class<?> getClass( final String className ) {

        String fullVersionName = "net.minecraft.server." + getVersion() + className;

        Class<?> clazz = null;

        try {

            clazz = Class.forName( fullVersionName );

        } catch ( final ClassNotFoundException exc ) {

            exc.printStackTrace();

        }

        return clazz;

    }

    /**
     * Gets the handle.
     *
     * @param obj
     *            the obj
     * @return the handle
     */
    public Object getHandle( final Object obj ) {

        try {

            return getMethod( "getHandle", obj.getClass() ).invoke( obj );

        } catch ( final IllegalAccessException | InvocationTargetException exc) {

            exc.printStackTrace();
            return null;

        }
    }

    /**
     * Class list equal.
     *
     * @param list1
     *            the classList 1
     * @param list2
     *            the classList 2
     * @return true, if successful
     */
    public boolean ClassListEqual( final Class<?>[] list1, final Class<?>[] list2 ) {

        boolean equal = true;

        if ( list1.length != list2.length ) {

            return false;

        }

        for ( int i = 0; i < list1.length; i++ ) {

            if ( list1[ i ] != list2[ i ] ) {

                equal = false;
                break;

            }
        }

        return equal;

    }

    /**
     * Gets the method.
     *
     * @param clazz
     *            the clazz
     * @param methodName
     *            the name
     * @param args
     *            the args
     * @return the method
     */
    public Method getMethod( final Class<?> clazz, final String methodName, final Class<?> ... args ) {

        for ( Method method : clazz.getMethods() ) {

            if ( method.getName().equals( methodName ) && args.length == 0 || ClassListEqual( args, method.getParameterTypes() ) ) {

                method.setAccessible( true );
                return method;

            }
        }

        return null;

    }

    /**
     * Gets the method.
     *
     * @param methodName
     *            the name
     * @param clazz
     *            the clazz
     * @param paramTypes
     *            the param types
     * @return the method
     */
    public Method getMethod( final String methodName, final Class<?> clazz, final Class<?>... paramTypes ) {

        Class<?>[] t = toPrimitiveTypeArray(paramTypes);

        for ( Method m : clazz.getMethods() ) {

            Class<?>[] types = toPrimitiveTypeArray( m.getParameterTypes() );

            if (m.getName().equals( methodName ) && equalsTypeArray( types, t )) {

                return m;

            }
        }

        return null;

    }

    /**
     * Gets the primitive type.
     *
     * @param clazz
     *            the clazz
     * @return the primitive type
     */
    public Class<?> getPrimitiveType( final Class<?> clazz ) {
        return CORRESPONDING_TYPES.getOrDefault( clazz, clazz );
    }

    /**
     * Gets the field.
     *
     * @param clazz
     *            the clazz
     * @param name
     *            the name
     * @return the field
     */
    public Field getField( final Class<?> clazz, final String name) {

        try {

            Field field = clazz.getDeclaredField( name );
            field.setAccessible( true );

            return field;

        } catch ( SecurityException | NoSuchFieldException exc ) {

            exc.printStackTrace( );
            return null;

        }
    }

    /**
     * To primitive type array.
     *
     * @param classes
     *            the classes
     * @return the class[]
     */
    public Class<?>[] toPrimitiveTypeArray( final Class<?>[] classes ) {

        if ( classes == null ) {

            return null;

        }

        int a = classes.length;
        Class<?>[] types = new Class<?>[a];
        for (int i = 0; i < a; i++) {
            types[i] = getPrimitiveType(classes[i]);
        }
        return types;
    }


    /**
     * Gets the player field.
     *
     * @param player
     *            the player
     * @param name
     *            the name
     * @return the player field
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws NoSuchFieldException
     *             the no such field exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    public Object getPlayerField( final Player player, final String name) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        final Method getHandle = player.getClass( ).getMethod( "getHandle" );

        final Object nmsPlayer = getHandle.invoke( player );

        final Field field = nmsPlayer.getClass( ).getField( name );

        return field.get( nmsPlayer );

    }

    /**
     * Invoke method.
     *
     * @param method
     *            the method
     * @param obj
     *            the obj
     * @return the object
     */
    public Object invokeMethod( final String method, final Object obj ) {

        try {

            return getMethod( method, obj.getClass( ) ).invoke( obj );

        } catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException exc ) {

            exc.printStackTrace( );

            return null;

        }
    }

    /**
     * Equals type array.
     *
     * @param a
     *            the a
     * @param o
     *            the o
     * @return true, if successful
     */
    public static boolean equalsTypeArray( final Class<?>[] a, final Class<?>[] o) {

        if ( a.length != o.length ) {

            return false;

        }

        for ( int i = 0; i < a.length; i++ ) {

            if ( !a[ i ].equals( o[ i ] ) && !a[ i ].isAssignableFrom( o[ i ] ) ) {

                return false;

            }
        }

        return true;

    }

    /**
     * Sets the.
     *
     * @param object
     *            the object
     * @param fieldName
     *            the field name
     * @param fieldValue
     *            the field value
     * @return true, if successful
     */
    public static boolean set( final Object object, final String fieldName, final Object fieldValue ) {

        Class < ? > clazz = object.getClass( );

        while ( clazz != null ) {

            try {

                Field field = clazz.getDeclaredField( fieldName );

                field.setAccessible( true );
                field.set( object, fieldValue );

                return true;

            } catch ( NoSuchFieldException exc ) {

                clazz = clazz.getSuperclass( );

            } catch ( Exception exc ) {

                throw new IllegalStateException( exc );

            }
        }

        return false;

    }

    /** The Constant CORRESPONDING_TYPES. */
    public static final Map <Class<?>, Class<?>> CORRESPONDING_TYPES = new HashMap <>( );

    /** returns the VersionManager as Instance */
    public static VersionController getInstance() {

        if ( instance == null ) instance = new VersionController();

        return instance;

    }
}
