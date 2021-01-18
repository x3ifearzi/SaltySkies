package de.saltyfearz.saltyskies.items;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.versioncontroller.VersionController;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class InventoryLayoutItems {

    final private VersionController vc = new VersionController( );

    public ItemStack GRAY_STAINED_GLASS_PANE = vc.getdVS( ).item( Material.GRAY_STAINED_GLASS_PANE, "§f§l» §cx §f§l«" );

    public ItemStack SAND = vc.getdVS( ).item( Material.SAND, "§4S§cand", "§7Klicke hier, um zu den verschiedenen Sandarten zu gelangen." );

    public ItemStack LIME_STAINED_GLASS_PANE__UNBAN = vc.getdVS( ).item( Material.LIME_STAINED_GLASS_PANE, "§2Entbannen" );
    public ItemStack RED_STAINED_GLASS_PANE__BAN = vc.getdVS( ).item( Material.RED_STAINED_GLASS_PANE, "§2Bannen" );
    public ItemStack PURPLE_STAINED_GLASS_PANE__KICK = vc.getdVS( ).item( Material.PURPLE_STAINED_GLASS_PANE, "§2Kicken" );
    public ItemStack GREEN_STAINED_GLASS_PANE__UNMUTE = vc.getdVS( ).item( Material.GREEN_STAINED_GLASS_PANE, "§2Entstummen" );
    public ItemStack YELLOW_STAINED_GLASS_PANE__MUTE = vc.getdVS( ).item( Material.YELLOW_STAINED_GLASS_PANE, "§2Stummen" );
    public ItemStack PAPER_REASON = vc.getdVS( ).item( Material.PAPER, "§eGrund" );
    public ItemStack NETHERSTAR_EXECUTE_PUNISH = vc.getdVS( ).itemWithFlags( Material.NETHER_STAR, "§aAusführen" );
    public ItemStack REDSTONE_DURATION = vc.getdVS( ).item( Material.REDSTONE, "§eDauer" );
    public ItemStack BOOK_N_QUILL_INPUT_REASON = vc.getdVS( ).item( Material.WRITABLE_BOOK, "§cEingabe in Wörtern [§6§la-Z§c]:" );
    public ItemStack BOOK_N_QUILL_INPUT_DURATION = vc.getdVS( ).item( Material.WRITABLE_BOOK, "§cEingabe in Minuten [§6§l0-9§c]:" );
    public ItemStack ARROW_BACK_PUNISH = vc.getdVS( ).item( Material.ARROW, "§4S§cchließen", "§7Klicke hier um das Menü zu schließen." );

    public ItemStack SAND_SHOP = vc.getdVS( ).item( Material.SAND, "§4S§cand", "§7Klicke hier, um zu den verschiedenen Sandarten zu gelangen." );
    public ItemStack NETHERSTAR_SHOP = vc.getdVS( ).item( Material.NETHER_STAR, "§4B§cesonderes", "§7Klicke hier, um zu den besonderen Items zu gelangen." );
    public ItemStack CLAY_SHOP = vc.getdVS( ).item( Material.CLAY, "§4G§cehärteter Lehm", "§7Klicke hier, um zu den verschiedenen Lehmarten zu gelangen." );
    public ItemStack DIRT_SHOP = vc.getdVS( ).item( Material.DIRT, "§4E§crde", "§7Klicke hier, um zu den verschiedenen Erdarten zu gelangen." );
    public ItemStack OAK_LOG_SHOP = vc.getdVS( ).item( Material.OAK_LOG, "§4H§colz", "§7Klicke hier, um zu den verschiedenen Holzarten zu gelangen." );
    public ItemStack GLASS_SHOP = vc.getdVS( ).item( Material.GLASS, "§4G§clas", "§7Klicke hier, um zu den verschiedenen Glasarten zu gelangen." );
    public ItemStack WHITE_WOOL_SHOP = vc.getdVS( ).item( Material.WHITE_WOOL, "§4W§colle", "§7Klicke hier, um zu den verschiedenen Woolearten zu gelangen." );
    public ItemStack BRICK_SHOP = vc.getdVS( ).item( Material.BRICK, "§4B§causteine", "§7Klicke hier, um zu den verschiedenen Bausteine zu gelangen." );
    public ItemStack ROSE_BUSH_SHOP = vc.getdVS( ).item( Material.ROSE_BUSH, "§4N§catur", "§7Klicke hier, um zu den verschiedenen Naturplanzen zu gelangen." );
    public ItemStack DIAMOND_SHOP = vc.getdVS( ).item( Material.DIAMOND, "§4E§crze", "§7Klicke hier, um zu den verschiedenen Erzen zu gelangen." );
    public ItemStack GOLDEN_CHESTPLATE_SHOP = vc.getdVS( ).item( Material.GOLDEN_CHESTPLATE, "§4R§cüstungen", "§7Klicke hier, um zu den verschiedenen Rüstungen zu gelangen." );
    public ItemStack CHEST_SHOP = vc.getdVS( ).item( Material.CHEST, "§4D§ceko", "§7Klicke hier, um zu den verschiedenen Dekorationen zu gelangen." );
    public ItemStack POTION_SHOP = vc.getdVS( ).item( Material.POTION, "§4T§cränke", "§7Klicke hier, um zu den verschiedenen Tränke zu gelangen." );
    public ItemStack GOLDEN_PICKAXE_SHOP = vc.getdVS( ).item( Material.GOLDEN_PICKAXE, "§4W§cerkzeuge & §4W§caffen", "§7Klicke hier, um zu den verschiedenen Werkzeugen & Waffen zu gelangen." );
    public ItemStack BREAD_SHOP = vc.getdVS( ).item( Material.BREAD, "§4E§cssen", "§7Klicke hier, um zu den verschiedenen Essen zu gelangen." );
    public ItemStack REDSTONE_SHOP = vc.getdVS( ).item( Material.REDSTONE, "§4R§cedstone", "§7Klicke hier, um zu den verschiedenen Redstoneartikel zu gelangen." );
    public ItemStack SPAWNER_SHOP = vc.getdVS( ).item( Material.SPAWNER, "§4M§cobs", "§7Klicke hier, um zu den verschiedenen Mobartikel zu gelangen." );
    public ItemStack COBBLESTONE_SHOP = vc.getdVS( ).item( Material.COBBLESTONE, "§4S§cteine", "§7Klicke hier, um zu den verschiedenen Steinartikel zu gelangen." );
    public ItemStack LAVA_BUCKET_SHOP = vc.getdVS( ).item( Material.LAVA_BUCKET, "§4V§cerschiedenes", "§7Klicke hier, um zu den verschiedenen Gegenständen zu gelangen." );
    public ItemStack NETHERRACK_SHOP = vc.getdVS( ).item( Material.NETHERRACK, "§4N§cether", "§7Klicke hier, um zu den verschiedenen Nethergegenständen zu gelangen." );
    public ItemStack ARROW_BACK_SHOP = vc.getdVS( ).item( Material.ARROW, "§4Z§curück", "§7Klicke hier um zur voherigen Seite zu gelangen." );
    public ItemStack ARROW_FORWARD_SHOP = vc.getdVS( ).item( Material.ARROW, "§4V§corwärts", "§7Klicke hier um zur nächsten Seite zu gelangen." );
    public ItemStack BOOK_N_QUILL_SEARCH_SHOP = vc.getdVS( ).item( Material.WRITABLE_BOOK, "§4S§cuchen..", "§7Klicke hier um nach einem speziellen Gegenstand zu suchen." );
    public ItemStack HOPPER_MINECART_FILTER_SHOP = vc.getdVS( ).item( Material.HOPPER_MINECART, "§4F§ciltern nach:", "§7Klicke hier um nach folgendem zu filtern:\n" + "§7Günstigsten\n" + "§7Teueresten\n" + "§7Alphabetisch\n" + "§7reAlphabetisch\n" + "§7Anzahl\n" );

    public ItemStack ERROR_BARRIER = vc.getdVS( ).item( Material.BARRIER, "§4§lFEHLER BEIM PARSEN", Collections.singletonList( "§4§lFEHLER BEIM PARSEN SIEHE ShopCommand.java" ) );

    public ItemStack getPlayerMoneySkull ( final Player player, final double money ) {

        return vc.getdVS( ).skull( Material.PLAYER_HEAD, "§eTokenübersicht", "§7Deine Tokens: §e" + money + "§7 Tokens.", player );
    }
}
