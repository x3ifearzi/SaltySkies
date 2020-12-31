package de.saltyfearz.saltyskies.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface differentVersionSystem {

    public ItemStack item( final Material material, final String displayName, final String singleLore );

    public ItemStack item( final Material material, final String displayName, final List <String> lore );

    public ItemStack item( final Material material, final String displayName );

    public ItemStack itemWithFlags( final Material material, final String displayName );

    public void addItemFlags( final ItemMeta iM );


}
