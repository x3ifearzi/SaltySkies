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

    final private int[] viewStartShopLayout = new int[]{1, 2, 3, 5, 6, 7, 9, 11, 13, 15, 17, 18, 19, 21, 23, 25, 26, 27, 28, 30, 32, 34, 35, 36, 38, 40, 42, 44, 46, 47, 48, 50, 51, 52};

    final private SaltySkies plugin;

    private double money;

    public ShopCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "shop", description = "§6Öffne den Shop.", usage = "§6/Shop", permission = "SaltySkies.shop.use")
    public void shop ( CommandArgs args ) {

        final Player player = args.getPlayer();

    }

    public void viewStartShop( final Player player ) {

        Inventory inv = Bukkit.createInventory( null, 54, "§4§lSHOP" );

        inv.setItem( 0, plugin.getiLI( ).SAND_SHOP );

        for ( int value : viewStartShopLayout ) {

            inv.setItem( value, plugin.getiLI( ).GRAY_STAINED_GLASS_PANE );

        }

        inv.setItem( 4, plugin.getiLI( ).NETHERSTAR_SHOP );
        inv.setItem( 8, plugin.getiLI( ).CLAY_SHOP );
        inv.setItem( 10, plugin.getiLI( ).DIRT_SHOP );
        inv.setItem( 12, plugin.getiLI( ).OAK_LOG_SHOP );
        inv.setItem( 14, plugin.getiLI( ).GLASS_SHOP );
        inv.setItem( 16, plugin.getiLI( ).WHITE_WOOL_SHOP );
        inv.setItem( 20, plugin.getiLI( ).BRICK_SHOP );
        inv.setItem( 22, plugin.getiLI( ).ROSE_BUSH_SHOP );
        inv.setItem( 24, plugin.getiLI( ).DIAMOND_SHOP );
        inv.setItem( 29, plugin.getiLI( ).GOLDEN_CHESTPLATE_SHOP );
        inv.setItem( 31, plugin.getiLI( ).CHEST_SHOP );
        inv.setItem( 33, plugin.getiLI( ).POTION_SHOP );
        inv.setItem( 37, plugin.getiLI( ).GOLDEN_PICKAXE_SHOP );
        inv.setItem( 39, plugin.getiLI( ).BREAD_SHOP );
        inv.setItem( 41, plugin.getiLI( ).REDSTONE_SHOP );
        inv.setItem( 43, plugin.getiLI( ).SPAWNER_SHOP );
        inv.setItem( 45, plugin.getiLI( ).COBBLESTONE_SHOP );
        inv.setItem( 49, plugin.getiLI( ).LAVA_BUCKET_SHOP );
        inv.setItem( 53, plugin.getiLI( ).NETHERRACK_SHOP );

        player.openInventory( inv );

    }
}
