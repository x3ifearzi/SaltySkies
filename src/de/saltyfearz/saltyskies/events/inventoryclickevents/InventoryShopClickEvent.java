package de.saltyfearz.saltyskies.events.inventoryclickevents;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.ShopCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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

    @EventHandler
    public void onShopBuyEvent ( final InventoryClickEvent event ) {

        if (! event.getView().getTitle().startsWith("§4§lSHOP")) return;

        final Player    player    = (Player) event.getWhoClicked();

        final ClickType clickType = event.getClick();

        final ItemStack clickedI = event.getCurrentItem();

        if ( clickedI == null || clickedI.getItemMeta() == null ) return;

        final String nameClickedI = clickedI.getItemMeta().getDisplayName();

        final ShopCommand shopObj = new ShopCommand( plugin );

        event.setCancelled( true );
        player.updateInventory();

        if ( clickType.isLeftClick() && nameClickedI.startsWith( "§6" ) ) {

            double iCosts = shopObj.buyItems( nameClickedI.replace( "§6", "" ).replace( " ", "_" ) );

            if ( player.getInventory().firstEmpty() <= - 1 ) {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "shop-command", "notEnoughSpace" ) );
                return;

            }

            if ( shopObj.reduceMoney( iCosts, player ) ) {

                ItemMeta iM = clickedI.getItemMeta();

                iM.setDisplayName(  iM.getDisplayName().replace( "&6", "&d" ) );

                iM.setLore( null );

                clickedI.setItemMeta( iM );

                player.getInventory().addItem( clickedI );

                player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "shop-command", "successItemBought" ) ); //TODO MIT KONTOSTAND NOCH

                shopObj.addMoney( iCosts, player );

                player.updateInventory();

            } else {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "shop-command", "notEnoughFearzys" ) );

            }
        } else if ( clickType.isRightClick() && nameClickedI.startsWith( "&6" ) ) {

            double iSellPrice = shopObj.sellItems( nameClickedI.replace( "§6", "" ).replace( " ", "_" ) );

            for ( ItemStack iS : player.getOpenInventory().getBottomInventory().getContents() ) {

                if ( clickedI == iS ) {

                    final int amountOfSoldI = player.getOpenInventory().getBottomInventory().all( iS ).size();

                    player.getOpenInventory().getBottomInventory().removeItem( iS );

                    player.updateInventory();

                    shopObj.addMoney( ( iSellPrice * amountOfSoldI ), player );

                    player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "shop-command", "successItemSold" ) ); //TODO MIT KONTOSTAND NOCH
                    break;

                }

            }
        }

    }
}
