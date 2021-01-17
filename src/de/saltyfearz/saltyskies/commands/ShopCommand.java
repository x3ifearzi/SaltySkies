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
    final private int[] viewShopItemsLayout = new int[]{0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 26, 27, 36, 44, 46, 47, 48, 49, 50, 52};

    private String pageBack     = "";
    private String pageForward  = "";

    final private SaltySkies plugin;

    private double money;

    private InventoryLayoutItems invL = null;


    public ShopCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command( name = "shop", description = "§6Öffne den Shop.", usage = "§6/Shop", permission = "SaltySkies.shop.use")
    public void shop ( CommandArgs args ) {

        invL = plugin.getiLI();

        final Player player = args.getPlayer();

        viewStartShop( player );
    }

    public void viewStartShop( final Player player ) {


        Inventory inv = Bukkit.createInventory( null, 54, "§4§lSHOP" );

        inv.setItem( 0, invL.SAND_SHOP );

        for ( int value : viewStartShopLayout ) {

            inv.setItem( value, invL.GRAY_STAINED_GLASS_PANE );

        }

        inv.setItem( 4, invL.NETHERSTAR_SHOP );
        inv.setItem( 8, invL.CLAY_SHOP );
        inv.setItem( 10, invL.DIRT_SHOP );
        inv.setItem( 12, invL.OAK_LOG_SHOP );
        inv.setItem( 14, invL.GLASS_SHOP );
        inv.setItem( 16, invL.WHITE_WOOL_SHOP );
        inv.setItem( 20, invL.BRICK_SHOP );
        inv.setItem( 22, invL.ROSE_BUSH_SHOP );
        inv.setItem( 24, invL.DIAMOND_SHOP );
        inv.setItem( 29, invL.GOLDEN_CHESTPLATE_SHOP );
        inv.setItem( 31, invL.CHEST_SHOP );
        inv.setItem( 33, invL.POTION_SHOP );
        inv.setItem( 37, invL.GOLDEN_PICKAXE_SHOP );
        inv.setItem( 39, invL.BREAD_SHOP );
        inv.setItem( 41, invL.REDSTONE_SHOP );
        inv.setItem( 43, invL.SPAWNER_SHOP );
        inv.setItem( 45, invL.COBBLESTONE_SHOP );
        inv.setItem( 49, invL.LAVA_BUCKET_SHOP );
        inv.setItem( 53, invL.NETHERRACK_SHOP );

        player.openInventory( inv );

    }

    public void viewShopItemList( final Player player, final String shopPage ) {

        Inventory inv = Bukkit.createInventory( null, 54, "§4§lSHOP" );

        for ( int value : viewShopItemsLayout ) {

            inv.setItem( value, invL.GRAY_STAINED_GLASS_PANE );

        }

        //Bukkit.getScheduler().scheduleSyncRepeatingTask( plugin, () -> inv.setItem( 4, invL.getPlayerMoneySkull( player, money )), 1, 1);

        inv.setItem( 35, invL.HOPPER_MINECART_FILTER_SHOP );
        inv.setItem( 45, invL.ARROW_BACK_SHOP );
        inv.setItem( 51, invL.BOOK_N_QUILL_SEARCH_SHOP );
        inv.setItem( 53, invL.ARROW_FORWARD_SHOP );

        switch ( shopPage ) {

            case "§4E§crde":

                pageBack = "startShop";

                inv.setItem( 19, null );
                inv.setItem( 21, null );
                inv.setItem( 23, null );
                inv.setItem( 25, null );
                inv.setItem( 31, null );

                pageForward = "";

                break;

            case "§4S§cand":

                pageBack = "startShop";

                inv.setItem( 10, null );
                inv.setItem( 12, null );
                inv.setItem( 14, null );
                inv.setItem( 16, null );
                inv.setItem( 19, null );
                inv.setItem( 21, null );
                inv.setItem( 23, null );
                inv.setItem( 25, null );

                pageForward = "";

        }

        player.openInventory( inv );

    }
}
