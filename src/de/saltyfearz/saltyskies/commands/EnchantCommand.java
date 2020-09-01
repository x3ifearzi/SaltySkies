package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.enchantments.CustomEnchantments;
import de.saltyfearz.saltyskies.enums.PICKAXES;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class EnchantCommand {

    final private SaltySkies plugin;

    public EnchantCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "enchant+", aliases = "enchantplus", description = "§6Verzaubere deine Gegenstände.", usage = "§6/enchant+ <enchantment> [<level>]", permission = "SaltySkies.enchant.plus")
    public void enchant ( CommandArgs args ) {

        Player player = args.getPlayer( );

        String[] arg = args.getArgs( );

        if ( arg.length < 1 || arg.length > 2) {

            player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "enchant-command", "syntax" ) );
            return;

        } else if ( arg[0].equalsIgnoreCase( "help" ) || arg[0].equalsIgnoreCase( "list" ) || arg[0].equalsIgnoreCase( "enchantments" ) ) {

            player.sendMessage( "§cDer Befehl ist in Arbeit." );
            return;

        }

        enchant ( player, arg[0], arg[1]);



    }

    private void enchant ( final Player player, final String enchantment, final String level ) {

        List< Enchantment > enchantments = CustomEnchantments.enchantList;

        Enchantment isEnchantment = null;

        for ( Enchantment ench : enchantments ) {

            if ( ench.toString().contains( enchantment ) ) isEnchantment = ench;

        }

        if ( enchantment == null ) return;
        //TODO

        if ( isEnchantment == CustomEnchantments.TELEPATHY ) {

            if ( player.getInventory().getItemInMainHand().equals( Arrays.stream( PICKAXES.values() ).iterator().next() ) );

        }

    }
}
