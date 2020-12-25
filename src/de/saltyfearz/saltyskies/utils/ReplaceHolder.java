package de.saltyfearz.saltyskies.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ReplaceHolder {

    public static String replaceHolderTarget ( final Player target, final String message ) {

        return message.replace( "%target", target.getName() );

    }

    public static String replaceHolderSender ( final Player sender, final String message ) {

        return message.replace( "%sender", sender.getName() );

    }

    public static String replaceHolderOwnFearzys ( final double fearzys, final String message ) {

        return message.replace( "%fearzys", String.valueOf( fearzys ) );

    }

    public static String replaceHolderTargetFearzys ( final double fearzys, final Player target, final String message ) {

        return message.replace( "%fearzys", String.valueOf( fearzys ) ).replace( "%target", target.getName() );

    }

    public static String replaceHolderSenderFearzys ( final double fearzys, final Player sender, final String message ) {

        return message.replace( "%fearzys", String.valueOf( fearzys ) ).replace( "%sender", sender.getName() );

    }

    public static String replaceHolderString ( final String string, final String message ) {

        return message.replace( "%string", string );

    }

    public static String replaceHolderStringInt ( final String string, final String amount, final String message ) {

        return message.replace( "%string", string ).replace( "%int", amount );

    }

    public static String replaceHolderStringIntTarget ( final String string, final String amount, final Player target, final String message ) {

        return message.replace( "%string", string ).replace( "%int", amount ).replace( "%target", target.getName() );

    }

    public static String replaceHolderStringBlock ( final String message, final String block ) {

        return message.replace( "%minerblock", block);

    }

    public static String replaceHolderLocationXYZ ( final Location location, final String message ) {

        final String x = String.valueOf( location.getX( ) );

        final String y = String.valueOf( location.getY( ) );

        final String z = String.valueOf( location.getZ( ) );

        return message.replace( "%x", x) .replace( "%y", y).replace( "%z", z );

    }

    public static String replaceHolderRegionowner ( final String owner, final String message ) {

        return message.replace( "%regionowner", owner );

    }
}
