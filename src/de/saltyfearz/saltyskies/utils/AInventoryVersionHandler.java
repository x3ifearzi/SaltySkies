package de.saltyfearz.saltyskies.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public interface AInventoryVersionHandler {

    public Inventory getInventory();

    public void loadClasses();

    public void open(Player player, HashMap<AInventory.AnvilSlot, ItemStack> items);
}
