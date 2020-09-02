package de.saltyfearz.saltyskies.utils;

import org.bukkit.entity.Player;

public class ReplaceHolder {

    public static String replaceHolderTarget ( final Player target, final String message ) {

        return message.replace( "%target", target.getName() );

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
}
