package de.saltyfearz.saltyskies.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class v_1_16_2 {

    public ItemStack item( final Material material, final String displayName, final String singleLore ) {

        final ItemStack iS = new ItemStack( material );

        final ItemMeta iM = iS.getItemMeta();

        if ( iM == null ) return null;

        iM.setDisplayName( displayName );

        if ( singleLore != null ) {

            iM.setLore( ( singleLore.isEmpty() ? new ArrayList<>() : Arrays.asList( singleLore.split( "\\n" ) ) ) );

        }

        this.addItemFlags( iM );

        iS.setItemMeta( iM );

        return iS;

    }

    public void addItemFlags( final ItemMeta iM ) {

        iM.addItemFlags( ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

    }

}
