package de.saltyfearz.saltyskies.items;

import de.saltyfearz.saltyskies.interfaces.differentVersionSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class v_1_16_1 implements differentVersionSystem {

    @Override
    public ItemStack item( final Material material, final String displayName, final String singleLore ) {

        final ItemStack iS = new ItemStack( material );

        final ItemMeta iM = iS.getItemMeta();

        if ( iM == null ) return null;

        iM.setDisplayName( displayName );

        if ( singleLore != null ) {

            iM.setLore( ( singleLore.isEmpty() ? new ArrayList<>() : Arrays.asList( singleLore.split( "\\n" ) ) ) );

        }

        iS.setItemMeta( iM );

        return iS;

    }

    @Override
    public ItemStack item( final Material material, final String displayName, final List<String> lore ) {

        final ItemStack iS = new ItemStack( material );

        final ItemMeta iM = iS.getItemMeta();

        if ( iM == null ) return null;

        iM.setDisplayName( displayName );

        if ( lore != null ) {

            if ( iM.hasLore() ) {

                Objects.requireNonNull( iM.getLore( ) ).clear();

            }

            iM.setLore( lore );

        }

        iS.setItemMeta( iM );

        return iS;


    }

    @Override
    public ItemStack item( final Material material, final String displayName ) {

        final ItemStack iS = new ItemStack( material );

        final ItemMeta iM = iS.getItemMeta();

        if ( iM == null ) return null;

        iM.setDisplayName( displayName );

        iS.setItemMeta( iM );

        return iS;


    }

    @Override
    public ItemStack itemWithFlags( final Material material, final String displayName ) {

        final ItemStack iS = new ItemStack( material );

        final ItemMeta iM = iS.getItemMeta();

        if ( iM == null ) return null;

        addItemFlags( iM );

        iM.setDisplayName( displayName );

        iS.setItemMeta( iM );

        return iS;


    }
    
    @Override
    public ItemStack skull( final Material material, final String displayName, final String lore, final Player player ) {

        final ItemStack iS = new ItemStack( material );

        final SkullMeta sM = ( SkullMeta ) iS.getItemMeta();

        if ( sM == null ) return null;

        sM.setOwningPlayer( Bukkit.getOfflinePlayer( player.getUniqueId() ) );

        sM.setDisplayName( player.getName() );

        sM.setLore( Collections.singletonList( lore ) );

        iS.setItemMeta( sM );

        return iS;

    }

    @Override
    public void addItemFlags( final ItemMeta iM ) {

        iM.addItemFlags( ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

    }

}
