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

import java.util.ArrayList;
import java.util.Objects;

public class InventoryShopClickEvent implements Listener {

    private final SaltySkies plugin;

    public InventoryShopClickEvent( final SaltySkies plugin ) { this.plugin = plugin; }

    @EventHandler
    public void onShopClickEvent( final InventoryClickEvent event ) {

        final ArrayList<Object> dataList = new ArrayList <>( checkData( event ) );

        if ( dataList.isEmpty() ) return;

        Player player       = ( Player )        dataList.get( 0 );
        String nameClickedI = ( String )        dataList.get( 2 );
        ShopCommand shopObj = ( ShopCommand )   dataList.get( 3 );


        switch ( nameClickedI ) {
            case "§4S§cand":
            case "§4H§colz":
            case "§4B§cesonderes":
            case "§4E§crde":
            case "§4R§cüstungen":
            case "§4W§cerkzeuge & §4W§caffen":
            case "§4W§colle":
            case "§4G§cglas":
            case "§4N§catur":
            case "§4N§cether":
            case "§4V§cerschiedenes":
            case "§4D§ceko":
            case "§4B§causteine":
            case "§4E§cssen":
            case "§4E§crze":
            case "§4G§clas":
            case "§4G§cehärteter Lehm":

                shopObj.viewShopItemList( player, nameClickedI );

                break;
            case "§4Z§curück":

                if ( ShopCommand.pageBack.equals( "startShop" ) ) {

                    shopObj.viewStartShop( player );

                } else {

                    shopObj.viewShopItemList( player, ShopCommand.pageBack );

                }

                break;
            case "§4V§corwärts":

                if ( ShopCommand.pageForward != null ) {

                    shopObj.viewShopItemList2( player, ShopCommand.pageForward );

                }
                break;
        }
    }

    @EventHandler
    public void onShopBuyEvent ( final InventoryClickEvent event ) {

        final ArrayList<Object> dataList = new ArrayList <>( checkData( event ) );

        if ( dataList.isEmpty() ) return;

        final ClickType clickType = event.getClick();

        Player player       = ( Player )        dataList.get( 0 );
        ItemStack clickedI  = ( ItemStack )     dataList.get( 1 );
        String nameClickedI = ( String )        dataList.get( 2 );
        ShopCommand shopObj = ( ShopCommand )   dataList.get( 3 );

        if ( clickType.isLeftClick() && nameClickedI.startsWith( "§6" ) ) {

            double iCosts = shopObj.buyItems( nameClickedI.replace( "§6", "" ).replace( " ", "_" ) );

            if ( player.getInventory().firstEmpty() <= - 1 ) {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "shop-command", "notEnoughFearzys" ) );
                return;

            }

            if ( shopObj.reduceMoney( iCosts, player ) ) {

                ItemStack newI = new ItemStack( clickedI );

                ItemMeta iM = newI.getItemMeta();

                Objects.requireNonNull( iM ).setDisplayName(  iM.getDisplayName().replace( "§6", "§d" ) );

                iM.setLore( null );

                newI.setItemMeta( iM );

                player.getInventory().addItem( newI );

                player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "shop-command", "successItemBought" ) );

                player.updateInventory();

            } else {

                player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "shop-command", "notEnoughFearzys" ) );

            }
        } else if ( clickType.isRightClick() && nameClickedI.startsWith( "§6" ) ) {

            double iSellPrice = shopObj.sellItems( nameClickedI.replace( "§6", "" ).replace( " ", "_" ) );

            for ( ItemStack iS : player.getOpenInventory().getBottomInventory().getContents() ) {

                if ( iS == null ) continue;
                if ( clickedI.getType() == iS.getType() ) {

                    final int amountOfSoldI = iS.getAmount(); //checkmaxamount

                    player.getOpenInventory().getBottomInventory().removeItem( iS );

                    player.updateInventory();

                    shopObj.addMoney( ( iSellPrice * amountOfSoldI ), player );

                    player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "shop-command", "successItemSold" ) );
                    break;

                }

            }
        }

    }

    /**
     * Checks if data is valid to execute she Shopevent
     * @param event
     *
     * Returns a List of Objects( Player, ItemStack, String, ShopCommandObject )
     * @return ArrayList<Object>
     *
     **/
    private ArrayList<Object> checkData( InventoryClickEvent event ) {

        if ( ! event.getView().getTitle().startsWith( "§4§lSHOP" ) ) return new ArrayList <>( );

        final Player player = ( Player ) event.getWhoClicked();

        event.setCancelled( true );
        player.updateInventory();

        final ItemStack clickedI = event.getCurrentItem();

        if ( clickedI == null || clickedI.getItemMeta() == null ) return new ArrayList <>( );

        final String nameClickedI = clickedI.getItemMeta().getDisplayName();

        final ShopCommand shopObj = new ShopCommand( plugin );

        ArrayList <Object> dataList = new ArrayList<>(  );

        dataList.add( player );
        dataList.add( clickedI );
        dataList.add( nameClickedI );
        dataList.add( shopObj );

        return dataList;

    }
}
