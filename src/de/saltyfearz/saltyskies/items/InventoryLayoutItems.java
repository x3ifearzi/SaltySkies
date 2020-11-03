package de.saltyfearz.saltyskies.items;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.versioncontroller.VersionController;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class InventoryLayoutItems {

    final private SaltySkies plugin;

    final private VersionController vc = new VersionController();

    public InventoryLayoutItems ( final SaltySkies plugin ) { this.plugin = plugin; }

    public ItemStack GRAY_STAINED_GLASS_PANE = vc.getdVS().item( Material.GRAY_STAINED_GLASS_PANE, "§f§l» §cx §f§l«" );

    public ItemStack SAND = vc.getdVS().item( Material.SAND, "§4S§cand", "§7Klicke hier, um zu den verschiedenen Sandarten zu gelangen." );

    public ItemStack LIME_STAINED_GLASS_PANE__UNBAN = vc.getdVS().item(Material.LIME_STAINED_GLASS_PANE, "§2Entbannen");
    public ItemStack RED_STAINED_GLASS_PANE__BAN = vc.getdVS().item(Material.RED_STAINED_GLASS_PANE, "§2Bannen");
    public ItemStack PURPLE_STAINED_GLASS_PANE__KICK = vc.getdVS().item(Material.PURPLE_STAINED_GLASS_PANE, "§2Kicken");
    public ItemStack GREEN_STAINED_GLASS_PANE__UNMUTE = vc.getdVS().item(Material.GREEN_STAINED_GLASS_PANE, "§2Entstummen");
    public ItemStack YELLOW_STAINED_GLASS_PANE__MUTE = vc.getdVS().item(Material.YELLOW_STAINED_GLASS_PANE, "§2Stummen");
    public ItemStack reason                   = vc.getdVS().item(Material.PAPER, 1, "§eGrund");
    public ItemStack executepunish            = vc.getdVS().item(Material.NETHER_STAR, 1, "§aAusführen");
    public ItemStack duration                 = vc.getdVS().item(Material.REDSTONE, 1, "§eDauer");
    public ItemStack bookreason               = vc.getdVS().item(Material.WRITABLE_BOOK, 1, "§cEingabe in Wörtern [§6§la-Z§c]:";
    public ItemStack bookduration             = vc.getdVS().item(Material.WRITABLE_BOOK, 1, "§cEingabe in Minuten [§6§l0-9§c]:";
    public ItemStack punishbackarrow          = vc.getdVS().item(Material.ARROW, "§4S§cchließen", "§7Klicke hier um das Menü zu schließen.");
    public ItemStack sand                     = vc.getdVS().item(Material.SAND, "§4S§cand", "§7Klicke hier, um zu den verschiedenen Sandarten zu gelangen.");
    public ItemStack netherstar               = vc.getdVS().item(Material.NETHER_STAR, "§4B§cesonderes", "§7Klicke hier, um zu den besonderen Items zu gelangen.");
    public ItemStack hardenedclay             = vc.getdVS().item(Material.CLAY, "§4G§cehärteter Lehm", "§7Klicke hier, um zu den verschiedenen Lehmarten zu gelangen.");
    public ItemStack dirt                     = vc.getdVS().item(Material.DIRT, "§4E§crde", "§7Klicke hier, um zu den verschiedenen Erdarten zu gelangen.");
    public ItemStack wood                     = vc.getdVS().item(Material.OAK_LOG, "§4H§colz", "§7Klicke hier, um zu den verschiedenen Holzarten zu gelangen.");
    public ItemStack glass                    = vc.getdVS().item(Material.GLASS, "§4G§clas", "§7Klicke hier, um zu den verschiedenen Glasarten zu gelangen.");
    public ItemStack wool                     = vc.getdVS().item(Material.WHITE_WOOL, "§4W§colle", "§7Klicke hier, um zu den verschiedenen Woolearten zu gelangen.");
    public ItemStack brick                    = vc.getdVS().item(Material.BRICK, "§4B§causteine", "§7Klicke hier, um zu den verschiedenen Bausteine zu gelangen.");
    public ItemStack redflower                = vc.getdVS().item(Material.ROSE_BUSH, 1, (short) 0, "§4N§catur", "§7Klicke hier, um zu den verschiedenen Naturplanzen zu gelangen.");
    public ItemStack diamond                  = vc.getdVS().item(Material.DIAMOND, "§4E§crze", "§7Klicke hier, um zu den verschiedenen Erzen zu gelangen.");
    public ItemStack goldenchestplate         = vc.getdVS().item(Material.GOLDEN_CHESTPLATE, "§4R§cüstungen", "§7Klicke hier, um zu den verschiedenen Rüstungen zu gelangen.");
    public ItemStack chest                    = vc.getdVS().item(Material.CHEST, "§4D§ceko", "§7Klicke hier, um zu den verschiedenen Dekorationen zu gelangen.");
    public ItemStack bottle                   = vc.getdVS().item(Material.POTION, 1, (short) 0, "§4T§cränke", "§7Klicke hier, um zu den verschiedenen Tränke zu gelangen.");
    public ItemStack goldenpickaxe            = vc.getdVS().item(Material.GOLDEN_PICKAXE, "§4W§cerkzeuge & §4W§caffen", "§7Klicke hier, um zu den verschiedenen Werkzeugen & Waffen zu gelangen.");
    public ItemStack bread                    = vc.getdVS().item(Material.BREAD, "§4E§cssen", "§7Klicke hier, um zu den verschiedenen Essen zu gelangen.");
    public ItemStack redstone                 = vc.getdVS().item(Material.REDSTONE, "§4R§cedstone", "§7Klicke hier, um zu den verschiedenen Redstoneartikel zu gelangen.");
    public ItemStack spawner                  = vc.getdVS().item(Material.SPAWNER, "§4M§cobs", "§7Klicke hier, um zu den verschiedenen Mobartikel zu gelangen.");
    public ItemStack cobblestone              = vc.getdVS().item(Material.COBBLESTONE, "§4S§cteine", "§7Klicke hier, um zu den verschiedenen Steinartikel zu gelangen.");
    public ItemStack lavabucket               = vc.getdVS().item(Material.LAVA_BUCKET, "§4V§cerschiedenes", "§7Klicke hier, um zu den verschiedenen Gegenständen zu gelangen.");
    public ItemStack netherrack               = vc.getdVS().item(Material.NETHERRACK, "§4N§cether", "§7Klicke hier, um zu den verschiedenen Nethergegenständen zu gelangen.");
    public ItemStack arrowheadleft            = vc.getdVS().item(Material.ARROW, "§4Z§curück", "§7Klicke hier um zur voherigen Seite zu gelangen.");
    public ItemStack arrowheadright           = vc.getdVS().item(Material.ARROW, "§4V§corwärts", "§7Klicke hier um zur nächsten Seite zu gelangen.");
    public ItemStack writable_book            = vc.getdVS().item(Material.WRITABLE_BOOK, "§4S§cuchen..", "§7Klicke hier um nach einem speziellen Gegenstand zu suchen.");
    public ItemStack hopper_minecart          = vc.getdVS().item(Material.HOPPER_MINECART, "§4F§ciltern nach:", "§7Klicke hier um nach folgendem zu filtern:\n" + "§7Günstigsten\n" + "§7Teueresten\n" + "§7Alphabetisch\n" + "§7reAlphabetisch\n" + "§7Anzahl\n");


}
