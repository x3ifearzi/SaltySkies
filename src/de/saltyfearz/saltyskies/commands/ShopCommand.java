package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.items.InventoryLayoutItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ShopCommand {

    final private SaltySkies plugin;

    private double money;

    public ShopCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "shop", description = "§6Öffne den Shop.", usage = "§6/Shop", permission = "SaltySkies.shop.use")
    public void shop ( CommandArgs args ) {

        final Player player = args.getPlayer();

    }

    public void viewStartShop( final Player player ) {

        Inventory inv = Bukkit.createInventory( null, 54, "§4§lSHOP" );

        inv.setItem( 0, plugin.getiLI().GRAY_STAINED_GLASS_PANE );


    }
}
