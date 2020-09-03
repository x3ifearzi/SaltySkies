package de.saltyfearz.saltyskies.items;

import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class InventoryLayoutItems {

    final private SaltySkies plugin;

    public InventoryLayoutItems ( final SaltySkies plugin ) { this.plugin = plugin; }

    public ItemStack grayStainedLayout = createItemStack( "GRAY_STAINED_GLASS_PANE", "§f§l» §cx §f§l«", null );

    public ItemStack createItemStack ( final String materialName, final String displayName, final String lore) {

        final ItemStack iS = new ItemStack( Objects.requireNonNull( Material.getMaterial( materialName ) ) );

        final ItemMeta iM = iS.getItemMeta();

        Objects.requireNonNull( iM ).setDisplayName( displayName );

        if ( lore != null ) {

            iM.setLore( Arrays.asList( lore.split( "\\n" ) ) );

        }

        iS.setItemMeta( iM );

        return iS;
    }
}
