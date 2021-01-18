package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.items.InventoryLayoutItems;
import de.saltyfearz.saltyskies.mysql.MoneySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.SQLException;
import java.util.Objects;

public class ShopCommand {

    final private int[] viewStartShopLayout = new int[] { 1, 2, 3, 5, 6, 7, 9, 11, 13, 15, 17, 18, 19, 21, 23, 25, 26, 27, 28, 30, 32, 34, 35, 36, 38, 40, 42, 44, 46, 47, 48, 50, 51, 52 };
    final private int[] viewShopItemsLayout = new int[] { 0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 26, 27, 36, 44, 46, 47, 48, 49, 50, 52 };

    public String pageBack, pageForward = "";

    private static String STARTSITE = "startShop";

    final private SaltySkies plugin;

    private double money;

    private InventoryLayoutItems invL = null;


    public ShopCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

    @Command ( name = "shop", description = "§6Öffne den Shop.", usage = "§6/Shop", permission = "SaltySkies.shop.use" )
    public void shop ( CommandArgs args ) {

        invL = plugin.getiLI( );

        final Player player = args.getPlayer( );

        try {

            money = new MoneySQL().getMoneyFromPlayer( player );

        } catch ( SQLException exc ) {

            exc.printStackTrace();

            player.getOpenInventory().close();

        }

        viewStartShop( player );
    }

    public void viewStartShop ( final Player player ) {


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

    public void viewShopItemList ( final Player player, final String shopPage ) {

        Inventory inv = Bukkit.createInventory( null, 54, "§4§lSHOP" );

        for ( int value : viewShopItemsLayout ) {

            inv.setItem( value, invL.GRAY_STAINED_GLASS_PANE );

        }

        Bukkit.getScheduler( ).scheduleSyncRepeatingTask( plugin, ( ) -> inv.setItem( 4, invL.getPlayerMoneySkull( player, money ) ), 1, 20 );

        inv.setItem( 35, invL.HOPPER_MINECART_FILTER_SHOP );
        inv.setItem( 45, invL.ARROW_BACK_SHOP );
        inv.setItem( 51, invL.BOOK_N_QUILL_SEARCH_SHOP );
        inv.setItem( 53, invL.ARROW_FORWARD_SHOP );

        switch ( shopPage ) {

            case "§4E§crde":

                pageBack = STARTSITE;

                inv.setItem( 19, plugin.getiCS( ).getItem( "Erde" ) );
                inv.setItem( 21, plugin.getiCS( ).getItem( "Podzol" ) );
                inv.setItem( 23, plugin.getiCS( ).getItem( "Grobe_Erde" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Grasblock" ) );
                inv.setItem( 31, plugin.getiCS( ).getItem( "Myzel" ) );

                pageForward = "";

                break;

            case "§4S§cand":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Sand" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Sandstein" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Sandsteintreppe" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Sandsteinstufe" ) );
                inv.setItem( 19, plugin.getiCS( ).getItem( "Roter_Sand" ) );
                inv.setItem( 21, plugin.getiCS( ).getItem( "Roter_Sandstein" ) );
                inv.setItem( 23, plugin.getiCS( ).getItem( "Rote_Sandsteintreppe" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Rote_Sandsteinstufe" ) );

                pageForward = "";

                break;

            case "§4B§cesonderes":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Enderportalrahmen" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Bedrock" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Barriere" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Drachenei" ) );
                inv.setItem( 29, plugin.getiCS( ).getItem( "XP-Flaschen" ) );
                inv.setItem( 31, plugin.getiCS( ).getItem( "Leuchtfeuer" ) );
                inv.setItem( 33, plugin.getiCS( ).getItem( "Netherstern" ) );

                pageForward = "";

                break;

            case "§4H§colz":

                pageBack = STARTSITE;

                inv.setItem( 11, plugin.getiCS( ).getItem( "Fichtenholz" ) );
                inv.setItem( 15, plugin.getiCS( ).getItem( "Schwarzeichenholz" ) );
                inv.setItem( 19, plugin.getiCS( ).getItem( "Birkenholz" ) );
                inv.setItem( 21, plugin.getiCS( ).getItem( "Jungelholz" ) );
                inv.setItem( 23, plugin.getiCS( ).getItem( "Eichenholz" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Akazienholz" ) );
                inv.setItem( 28, plugin.getiCS( ).getItem( "Birkenholzbretter" ) );
                inv.setItem( 30, plugin.getiCS( ).getItem( "Jungelholzbretter" ) );
                inv.setItem( 32, plugin.getiCS( ).getItem( "Eichenholzbretter" ) );
                inv.setItem( 34, plugin.getiCS( ).getItem( "Akazienholzbretter" ) );
                inv.setItem( 38, plugin.getiCS( ).getItem( "Fichtenholzbretter" ) );
                inv.setItem( 42, plugin.getiCS( ).getItem( "Schwarzeichenholzbretter" ) );

                pageForward = "";

                break;

            case "§4S§cteine":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Bruchstein" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Stein" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Bemooste_Steinziegel" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Gemeißelte_Steinziegel" ) );
                inv.setItem( 20, plugin.getiCS( ).getItem( "Steinziegel" ) );
                inv.setItem( 24, plugin.getiCS( ).getItem( "Rissige_Steinziegel" ) );
                inv.setItem( 31, plugin.getiCS( ).getItem( "Bemooster_Bruchstein" ) );
                inv.setItem( 39, plugin.getiCS( ).getItem( "Bemooste_Bruchsteinmauer" ) );
                inv.setItem( 41, plugin.getiCS( ).getItem( "Bruchsteinmauer" ) );

                pageForward = "";

                break;

            case "§4E§crze":
                pageBack = STARTSITE;
                inv.setItem( 10, plugin.getiCS( ).getItem( "Golderz" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Eisenerz" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Steinkohle" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Lapiserz" ) );
                inv.setItem( 19, plugin.getiCS( ).getItem( "Goldbarren" ) );
                inv.setItem( 21, plugin.getiCS( ).getItem( "Eisenbarren" ) );
                inv.setItem( 23, plugin.getiCS( ).getItem( "Kohle" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Lapis" ) );
                inv.setItem( 29, plugin.getiCS( ).getItem( "Diamantenerz" ) );
                inv.setItem( 31, plugin.getiCS( ).getItem( "Redstoneerz" ) );
                inv.setItem( 33, plugin.getiCS( ).getItem( "Smaragterz" ) );
                inv.setItem( 38, plugin.getiCS( ).getItem( "Diamant" ) );
                inv.setItem( 40, plugin.getiCS( ).getItem( "Redstone" ) );
                inv.setItem( 42, plugin.getiCS( ).getItem( "Smaragt" ) );

                pageForward = "";

                break;

            case "§4R§cüstungen":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Lederhelm" ) );
                inv.setItem( 11, plugin.getiCS( ).getItem( "Goldhelm" ) );
                inv.setItem( 13, plugin.getiCS( ).getItem( "Kettenhelm" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Eisenhelm" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Diamantenhelm" ) );
                inv.setItem( 19, plugin.getiCS( ).getItem( "Lederbrustplatte" ) );
                inv.setItem( 20, plugin.getiCS( ).getItem( "Goldbrustplatte" ) );
                inv.setItem( 22, plugin.getiCS( ).getItem( "Kettenbrustplatte" ) );
                inv.setItem( 23, plugin.getiCS( ).getItem( "Eisenbrustplatte" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Diamantenbrustplatte" ) );
                inv.setItem( 28, plugin.getiCS( ).getItem( "Lederhose" ) );
                inv.setItem( 29, plugin.getiCS( ).getItem( "Goldhose" ) );
                inv.setItem( 31, plugin.getiCS( ).getItem( "Kettenhose" ) );
                inv.setItem( 32, plugin.getiCS( ).getItem( "Eisenhose" ) );
                inv.setItem( 34, plugin.getiCS( ).getItem( "Diamantenhose" ) );
                inv.setItem( 37, plugin.getiCS( ).getItem( "Lederschuhe" ) );
                inv.setItem( 38, plugin.getiCS( ).getItem( "Goldschuhe" ) );
                inv.setItem( 40, plugin.getiCS( ).getItem( "Kettenschuhe" ) );
                inv.setItem( 41, plugin.getiCS( ).getItem( "Eisenschuhe" ) );
                inv.setItem( 43, plugin.getiCS( ).getItem( "Diamantenschuhe" ) );

                pageForward = "";

                break;

            case "§4N§cether":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Netherrack" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Barriere_" ) );
                //inv.setItem(12, plugin.getiCS()().getItem("Feuerresistenz_§e3:00"));
                inv.setItem( 14, plugin.getiCS( ).getItem( "Feuerball" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Quartzerz" ) );
                inv.setItem( 19, plugin.getiCS( ).getItem( "Seelensand" ) );
                inv.setItem( 21, plugin.getiCS( ).getItem( "Barriere_" ) );
                //inv.setItem(21, plugin.getiCS()().getItem("Feuerresistenz_§e8:00"));
                inv.setItem( 23, plugin.getiCS( ).getItem( "Lohenrute" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Quartz" ) );
                inv.setItem( 28, plugin.getiCS( ).getItem( "Glowstone" ) );
                inv.setItem( 34, plugin.getiCS( ).getItem( "Glowstonestaub" ) );
                inv.setItem( 37, plugin.getiCS( ).getItem( "Netherziegelbarren" ) );
                inv.setItem( 39, plugin.getiCS( ).getItem( "Netherziegel" ) );
                inv.setItem( 41, plugin.getiCS( ).getItem( "Netherziegeltreppe" ) );
                inv.setItem( 43, plugin.getiCS( ).getItem( "Netherziegelzaun" ) );

                pageForward = "";

                break;

            case "§4V§cerschiedenes":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Eimer" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Wassereimer" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Lavaeimer" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Milcheimer" ) );
                inv.setItem( 20, plugin.getiCS( ).getItem( "Schleimball" ) );
                inv.setItem( 22, plugin.getiCS( ).getItem( "Schallplatten" ) );
                inv.setItem( 24, plugin.getiCS( ).getItem( "Enderauge" ) );
                inv.setItem( 30, plugin.getiCS( ).getItem( "Verzauberte_Bücher" ) );

                pageForward = "";

                break;

            case "§4D§ceko":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Truhe" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Werkbank" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Ofen" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Endertruhe" ) );
                inv.setItem( 20, plugin.getiCS( ).getItem( "Plattenspieler" ) );
                inv.setItem( 22, plugin.getiCS( ).getItem( "Amboss" ) );
                inv.setItem( 24, plugin.getiCS( ).getItem( "Verzauberungstisch" ) );
                inv.setItem( 28, plugin.getiCS( ).getItem( "Blumentopf" ) );
                inv.setItem( 30, plugin.getiCS( ).getItem( "Leiter" ) );
                inv.setItem( 32, plugin.getiCS( ).getItem( "Rüstungsständer" ) );
                inv.setItem( 34, plugin.getiCS( ).getItem( "Rahmen" ) );
                inv.setItem( 38, plugin.getiCS( ).getItem( "Bett" ) );
                inv.setItem( 40, plugin.getiCS( ).getItem( "Gemälde" ) );
                inv.setItem( 42, plugin.getiCS( ).getItem( "Schild" ) );

                pageForward = "";

                break;

            case "§4N§catur":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Brauner_Pilz" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Roter_Pilz" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Kürbiskerne" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Kürbis" ) );
                inv.setItem( 20, plugin.getiCS( ).getItem( "Melonenkerne" ) );
                inv.setItem( 22, plugin.getiCS( ).getItem( "Melonenscheiben" ) );
                inv.setItem( 24, plugin.getiCS( ).getItem( "Melone" ) );
                inv.setItem( 28, plugin.getiCS( ).getItem( "Weizensamen" ) );
                inv.setItem( 30, plugin.getiCS( ).getItem( "Weizen" ) );
                inv.setItem( 32, plugin.getiCS( ).getItem( "Strohballen" ) );
                inv.setItem( 34, plugin.getiCS( ).getItem( "Kaktus" ) );
                inv.setItem( 38, plugin.getiCS( ).getItem( "Zuckerrohr" ) );
                inv.setItem( 42, plugin.getiCS( ).getItem( "Kakaobohnen" ) );

                pageForward = "";

                break;

            case "§4B§causteine":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Endstein" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Bücherregal" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Andesite" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Diorit" ) );
                inv.setItem( 19, plugin.getiCS( ).getItem( "Obsidian" ) );
                inv.setItem( 21, plugin.getiCS( ).getItem( "Gebrannter_Ton" ) );
                inv.setItem( 23, plugin.getiCS( ).getItem( "Polierter_Andesite" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Polierter_Diorit" ) );
                inv.setItem( 28, plugin.getiCS( ).getItem( "Dunkler_Prismarin" ) );
                inv.setItem( 30, plugin.getiCS( ).getItem( "Seelaterne" ) );
                inv.setItem( 32, plugin.getiCS( ).getItem( "Ziegelstein" ) );
                inv.setItem( 34, plugin.getiCS( ).getItem( "Polierter_Granit" ) );
                inv.setItem( 37, plugin.getiCS( ).getItem( "Prismarin" ) );
                inv.setItem( 39, plugin.getiCS( ).getItem( "Prismarinziegel" ) );
                inv.setItem( 41, plugin.getiCS( ).getItem( "Ton" ) );
                inv.setItem( 43, plugin.getiCS( ).getItem( "Granit" ) );

                pageForward = "";

                break;

            case "§4W§cerkzeuge & §4W§caffen":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Holzspitzhacke" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Steinspitzhacke" ) );
                inv.setItem( 13, plugin.getiCS( ).getItem( "Goldspitzhacke" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Eisenspitzhacke" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Diamantspitzhacke" ) );
                inv.setItem( 19, plugin.getiCS( ).getItem( "Holzaxt" ) );
                inv.setItem( 21, plugin.getiCS( ).getItem( "Steinaxt" ) );
                inv.setItem( 22, plugin.getiCS( ).getItem( "Goldaxt" ) );
                inv.setItem( 23, plugin.getiCS( ).getItem( "Eisenaxt" ) );
                inv.setItem( 25, plugin.getiCS( ).getItem( "Diamantaxt" ) );
                inv.setItem( 28, plugin.getiCS( ).getItem( "Holzschaufel" ) );
                inv.setItem( 30, plugin.getiCS( ).getItem( "Steinschaufel" ) );
                inv.setItem( 31, plugin.getiCS( ).getItem( "Goldschaufel" ) );
                inv.setItem( 32, plugin.getiCS( ).getItem( "Eisenschaufel" ) );
                inv.setItem( 34, plugin.getiCS( ).getItem( "Diamantschaufel" ) );
                inv.setItem( 37, plugin.getiCS( ).getItem( "Holzhacke" ) );
                inv.setItem( 39, plugin.getiCS( ).getItem( "Steinhacke" ) );
                inv.setItem( 40, plugin.getiCS( ).getItem( "Goldhacke" ) );
                inv.setItem( 41, plugin.getiCS( ).getItem( "Eisenhacke" ) );
                inv.setItem( 43, plugin.getiCS( ).getItem( "Diamanthacke" ) );

                pageForward = "§4W§cerkzeuge & §4W§caffen2";

                break;

            case "§4E§cssen":

                pageBack = STARTSITE;

                inv.setItem( 10, plugin.getiCS( ).getItem( "Apfel" ) );
                inv.setItem( 12, plugin.getiCS( ).getItem( "Goldener_Apfel" ) );
                inv.setItem( 14, plugin.getiCS( ).getItem( "Super_Goldener_Apfel" ) );
                inv.setItem( 16, plugin.getiCS( ).getItem( "Steak" ) );
                inv.setItem( 37, plugin.getiCS( ).getItem( "Kartoffel" ) );
                inv.setItem( 39, plugin.getiCS( ).getItem( "Karotte" ) );
                inv.setItem( 41, plugin.getiCS( ).getItem( "Kuchen" ) );
                inv.setItem( 43, plugin.getiCS( ).getItem( "Gebratenes_Schweinefleisch" ) );

                pageForward = "";

                break;

        }

        player.openInventory( inv );

    }

    public void viewShopItemList2 ( final Player player, final String page ) {
        Inventory inv = Bukkit.createInventory( null, 54, "§4§lSHOP" );

        for ( int value : viewShopItemsLayout ) {
            inv.setItem( value, plugin.getiLI( ).GRAY_STAINED_GLASS_PANE );
        }

        Bukkit.getScheduler( ).scheduleSyncRepeatingTask( plugin, ( ) -> inv.setItem( 4, plugin.getiLI( ).getPlayerMoneySkull( player, money ) ), 1, 20 );

        money = Double.parseDouble( Objects.requireNonNull( Objects.requireNonNull( Objects.requireNonNull( inv.getItem( 4 ) ).getItemMeta( ) ).getLore( ) ).get( 0 ) );

        inv.setItem( 35, plugin.getiLI( ).HOPPER_MINECART_FILTER_SHOP );
        inv.setItem( 45, plugin.getiLI( ).ARROW_BACK_SHOP );
        inv.setItem( 51, plugin.getiLI( ).BOOK_N_QUILL_SEARCH_SHOP );
        inv.setItem( 53, plugin.getiLI( ).ARROW_FORWARD_SHOP );

        switch ( page ) {

            case "§4S§cand2":

                pageBack = "§4S§cand";

                pageForward = "";

                break;

            case "§4H§colz2":

                pageBack = "§4H§colz";

                pageForward = "";

                break;

            case "§4W§cerkzeuge & §4W§caffen2":

                pageBack = "§4W§cerkzeuge & §4W§caffen";

                pageForward = "";

                break;
        }

        player.openInventory( inv );

    }

    private double buyItems ( String item ) {

        return Double.parseDouble( Objects.requireNonNull( plugin.getiCS( ).getFile( ).get( "shop.items." + item + ".item_buy_price" ) ).toString( ) );

    }

    private double sellItems ( String item ) {

        return Double.parseDouble( Objects.requireNonNull( plugin.getiCS( ).getFile( ).get( "shop.items." + item + ".item_sell_price" ) ).toString( ) );

    }


    public double getMoney ( ) {
        return money;
    }

    private boolean reduceMoney ( final double costs, final Player player ) {

        if ( money >= costs ) {

            money = money - costs;

            setMoney( money, player );

        } else {

            return false;

        }

        return true;

    }

    private void addMoney ( final double win, final Player player ) {

        money = money + win;

        setMoney( money, player );

    }

    private void setMoney ( final double money, final Player player ) {

        try {

            new MoneySQL( ).setMoneyFromPlayer( money, player );

        } catch ( SQLException exc ) {

            exc.printStackTrace();

        }
    }
}
