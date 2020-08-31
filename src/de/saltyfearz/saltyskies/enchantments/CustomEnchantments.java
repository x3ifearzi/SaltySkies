package de.saltyfearz.saltyskies.enchantments;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomEnchantments {

    final public static Enchantment TELEPATHY = new EnchantmentWrapper( "telepathy", "TELEPATY", 1 );
    final public static Enchantment HEMORRHAGE = new EnchantmentWrapper( "hemorrhage", "HEMORRHAGE", 5 );
    final public static Enchantment EXPLOSION = new EnchantmentWrapper( "explosion", "EXPLOSION", 3 );
    final public static Enchantment EXHAUST = new EnchantmentWrapper( "exhaust", "EXHAUST", 2 );
    final public static Enchantment EXPHUNTER = new EnchantmentWrapper( "exphunter", "EXPHUNTER", 5 );
    final public static Enchantment SMELTER = new EnchantmentWrapper( "smelter", "SMELTER", 2 );
    final public static Enchantment FIREBALL = new EnchantmentWrapper( "fireball", "FIREBALL", 3 );

    public static final List < Enchantment > enchantList = new ArrayList <>( );

    public static void register ( ) {
        registerEnchantments( TELEPATHY );
        registerEnchantments( HEMORRHAGE );
        registerEnchantments( EXPLOSION );
        registerEnchantments( EXHAUST );
        registerEnchantments( EXPHUNTER );
        registerEnchantments( SMELTER );
        registerEnchantments( FIREBALL );

        addConflictsWith();

        enchantList.add( TELEPATHY );
        enchantList.add( HEMORRHAGE );
        enchantList.add( EXPLOSION );
        enchantList.add( EXHAUST );
        enchantList.add( EXPHUNTER );
        enchantList.add( SMELTER );
    }

    public static void addConflictsWith ( ) {

        TELEPATHY.conflictsWith( SMELTER );

    }

    public static void registerEnchantments ( Enchantment enchantment ) {

        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField( "acceptingNew" );
            f.setAccessible( true );
            f.set( null, true );
            Enchantment.registerEnchantment( enchantment );
        } catch ( Exception e ) {
            registered = false;
        }
    }
}
