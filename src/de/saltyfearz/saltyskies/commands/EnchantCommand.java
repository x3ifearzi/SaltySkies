package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.enchantments.CustomEnchantments;
import de.saltyfearz.saltyskies.enums.AXES;
import de.saltyfearz.saltyskies.enums.PICKAXES;
import de.saltyfearz.saltyskies.enums.SHOVELS;
import de.saltyfearz.saltyskies.enums.SWORDS;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnchantCommand {

    final private SaltySkies plugin;

    List < Enchantment > enchantments = CustomEnchantments.enchantList;

    List < String > toolsTelepathy_toolsSmelter = new ArrayList <>( );
    List < String > toolsHemorrhage_toolsExhaust_toolsExplosion = new ArrayList <>( );
    List < String > toolsExphunter = new ArrayList <>( );
    // TODO List < String > toolsFireball = new ArrayList <>( );

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

        initListsTools();

        enchant ( player, arg[0], arg[1]);

    }

    private void enchant ( final Player player, final String enchantment, String level ) {

        Enchantment isEnchantment = null;

        for ( Enchantment ench : enchantments ) {

            if ( ench.toString( ).contains( enchantment ) ) isEnchantment = ench;

        }

        if ( enchantment == null ) return;
        //TODO

        if ( isEnchantment == CustomEnchantments.TELEPATHY ) {

            if ( player.getInventory( ).getItemInMainHand( ).getType( ).name( ).equals( toolsTelepathy_toolsSmelter.iterator( ).next( ) ) ) {

                addEnchantment( player, isEnchantment, "Telepathy", level( level, "1" ) );

            }

        } else if ( isEnchantment == CustomEnchantments.HEMORRHAGE ) {

            if ( player.getInventory( ).getItemInMainHand( ).getType( ).name( ).equals( toolsHemorrhage_toolsExhaust_toolsExplosion.iterator( ).next( ) ) ) {

                addEnchantment( player, isEnchantment, "Hemorrhage", level( level, "5" ) );

            }

        } else if ( isEnchantment == CustomEnchantments.EXHAUST ) {

            if ( player.getInventory( ).getItemInMainHand( ).getType( ).name( ).equals( toolsHemorrhage_toolsExhaust_toolsExplosion.iterator( ).next( ) ) ) {

                addEnchantment( player, isEnchantment, "Exhaust", level( level, "2" ) );

            }

        } else if ( isEnchantment == CustomEnchantments.EXPHUNTER ) {

            if ( player.getInventory( ).getItemInMainHand( ).getType( ).name( ).equals( toolsExphunter.iterator( ).next( ) ) ) {

                addEnchantment( player, isEnchantment, "XP-Hunter", level( level, "5" ) );

            }

        } else if ( isEnchantment == CustomEnchantments.EXPLOSION ) {

            if ( player.getInventory( ).getItemInMainHand( ).getType( ).name( ).equals( toolsHemorrhage_toolsExhaust_toolsExplosion.iterator( ).next( ) ) ) {

                addEnchantment( player, isEnchantment, "Explosion", level( level, "3" ) );

            }

        } else if ( isEnchantment == CustomEnchantments.SMELTER ) {

            if ( player.getInventory( ).getItemInMainHand( ).getType( ).name( ).equals( toolsHemorrhage_toolsExhaust_toolsExplosion.iterator( ).next( ) ) ) {

                addEnchantment( player, isEnchantment, "Smelter", level( level, "2" ) );

            }

        } else if ( isEnchantment == CustomEnchantments.FIREBALL ) {

            if ( player.getInventory( ).getItemInMainHand( ).getType( ).name( ).equals( toolsHemorrhage_toolsExhaust_toolsExplosion.iterator( ).next( ) ) ) {

                addEnchantment( player, isEnchantment, "Fireball", level( level, "3" ) );

            }
        }
    }

    private String level ( String level, final String maxLevel ) {

        if ( Integer.parseInt( level ) > Integer.parseInt( maxLevel ) ) {

            level = maxLevel;

        }

        switch ( level ) {

            case "2": return "II";
            case "3": return "III";
            case "4": return "IV";
            case "5": return "V";

            default: return "I";

        }
    }

    public void addEnchantment ( final Player player, final Enchantment enchantment, final String loreName, final String level ) {

        final int intLevel = level.equals( "I" ) ? 1 : level.equals("II") ? 2 : level.equals("III") ? 3 : level.equals("IV") ? 4 : level.equals("V") ? 5 : 1;

        final String lore = "§7" + loreName + " " + level;

        player.getInventory().getItemInMainHand().addEnchantment( enchantment, intLevel );

        ItemStack iS = player.getInventory().getItemInMainHand();

        ItemMeta iM = iS.getItemMeta();

        if ( iM == null ) return;

        if ( iM.getLore( ) == null ) {

            iM.setLore( Collections.singletonList( lore ) );

        } else {

            iM.setLore( iM.getLore() );
            iM.getLore().add( lore );
        }

        player.getInventory().getItemInMainHand().setItemMeta( iM );

    }

    private void initListsTools ( ) {

        toolsTelepathy_toolsSmelter.add( Arrays.toString( PICKAXES.values( ) ) );
        toolsTelepathy_toolsSmelter.add( Arrays.toString( SHOVELS.values( ) ) );
        toolsTelepathy_toolsSmelter.add( Arrays.toString( AXES.values( ) ) );
        toolsHemorrhage_toolsExhaust_toolsExplosion.add( Arrays.toString( AXES.values( ) ) );
        toolsHemorrhage_toolsExhaust_toolsExplosion.add( Arrays.toString( SWORDS.values( ) ) );
        toolsExphunter.add( Arrays.toString( PICKAXES.values( ) ) );
        toolsExphunter.add( Arrays.toString( SWORDS.values( ) ) );
        toolsExphunter.add( Arrays.toString( SHOVELS.values( ) ) );
        toolsExphunter.add( Arrays.toString( AXES.values( ) ) );

    }
}
