package de.saltyfearz.saltyskies.events.inventoryclickevents;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.ShopCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryShopClickEvent implements Listener {

    private final SaltySkies plugin;

    public InventoryShopClickEvent( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler
    public void onShopClickEvent( final InventoryClickEvent event ) {

        if ( ! event.getView().getTitle().startsWith( "§4§lSHOP" ) ) return;

        final Player player = ( Player ) event.getWhoClicked();

        final ShopCommand shopObj = new ShopCommand( plugin );

        event.setCancelled( true );
        player.updateInventory();

        double money = shopObj.getMoney();

        final ItemStack clickedI = event.getCurrentItem();

        if ( clickedI == null || clickedI.getItemMeta() == null ) return;

        final String nameClickedI = clickedI.getItemMeta().getDisplayName();

        if (nameClickedI.equals("§4S§cand") || nameClickedI.equals("§4H§colz") || nameClickedI.equals("§4B§cesonderes") || nameClickedI.equals("§4E§crde") || nameClickedI.equals("§4R§cüstungen") || nameClickedI.equals("§4N§cether") || nameClickedI.equals("§4V§cerschiedenes") || nameClickedI.equals("§4R§cedstone") || nameClickedI.equals("§4D§ceko") || nameClickedI.equals("§4N§catur") || nameClickedI.equals("§4S§cteine") || nameClickedI.equals("§4B§causteine") || nameClickedI.equals("§4E§crze") || nameClickedI.equals("§4W§cerkzeuge & §4W§caffen") || nameClickedI.equals("§4W§colle") || nameClickedI.equals("§4G§cglas") || nameClickedI.equals("§4E§cssen") || nameClickedI.equals("§4N§catur") || nameClickedI.equals("§4N§cether") || nameClickedI.equals("§4V§cerschiedenes") || nameClickedI.equals("§4D§ceko") || nameClickedI.equals("§4B§causteine") || nameClickedI.equals("§4E§cssen") || nameClickedI.equals("§4E§crze") || nameClickedI.equals("§4G§clas") || nameClickedI.equals("§4G§cehärteter Lehm")) {

            shopObj.viewShopItemList( player, nameClickedI );

        } else if ( nameClickedI.equals( "§4Z§curück" ) ) {

            if ( shopObj.pageBack.equals( "startShop" ) ) {

                shopObj.viewStartShop( player );

            } else {

                shopObj.viewShopItemList( player, shopObj.pageBack );

            }

        } else if ( nameClickedI.equals( "§4V§corwärts" ) ) {

            if ( shopObj.pageForward != null ) {

                shopObj.viewShopItemList2( player, shopObj.pageForward );

            }
        }
    }
}
