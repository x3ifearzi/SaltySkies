/*
package de.saltyfearz.saltyskies.utils;

import me.x3ifearzi.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class AInventory implements Listener{
  
  private Main plugin;
  
  public AInventory(final Main plugin) { this.plugin = plugin; }
  
  public static class AnvilClickEvent {
    
    private AnvilSlot slot;
    
    private String name;
    
    private boolean close = true;
    
    private boolean destroy = true;
    
    private Player player;
    
    public AnvilClickEvent(AnvilSlot slot, String name, Player player) {
      this.slot = slot;
      this.name = name;
      this.player = player;
    }
    
    public String getName() {
      return name;
    }
    
    public Player getPlayer() {
      return player;
    }
    
    public AnvilSlot getSlot() {
      return slot;
    }
    
    public boolean getWillClose() {
      return close;
    }

    public boolean getWillDestroy() {
      return destroy;
    }
    
    public void setWillClose(boolean close) {
      this.close = close;
    }

    public void setWillDestroy(boolean destroy) {
      this.destroy = destroy;
    }
  }

  public interface AnvilClickEventHandler {
    
    void onAnvilClick(AnvilClickEvent event);
  }
  public enum AnvilSlot {
    INPUT_LEFT(0),
    INPUT_RIGHT(1),
    OUTPUT(2);
    
    public static AnvilSlot bySlot(int slot) {
      for (AnvilSlot anvilSlot : values()) {
        if (anvilSlot.getSlot() == slot) {
          return anvilSlot;
        }
      }
      
      return null;
    }
    
    private int slot;
    
    private AnvilSlot(int slot) {
      this.slot = slot;
    }
    
    public int getSlot() {
      return slot;
    }
  }
  
  private AInventoryVersionHandler versionHandle;
  
  private Player player;
  
  private AnvilClickEventHandler handler;
  
  private HashMap<AnvilSlot, ItemStack> items = new HashMap<>();
  
  private Listener listener;
  
  public AInventory(final Player player, final AnvilClickEventHandler anvilClickEventHandler) {
    
    versionHandle = new AInventoryReflectionHandler(player, anvilClickEventHandler);
    
    this.player = player;
    handler = anvilClickEventHandler;
    PlayerUtils.getInstance().setPlayerMeta(player, "AInventory", anvilClickEventHandler);
    
    listener = new Listener() {
      @EventHandler
      public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
          
          if (event.getInventory().equals(versionHandle.getInventory())) {
            event.setCancelled(true);
            
            ItemStack item = event.getCurrentItem();
            int slot = event.getRawSlot();
            String name = "";
            
            if (item != null) {
              if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                
                if (meta.hasDisplayName()) {
                  name = meta.getDisplayName();
                }
              }
            }
            
            AnvilClickEvent clickEvent = new AnvilClickEvent( AnvilSlot.bySlot(slot), name, (Player) event.getWhoClicked());
            
            if (clickEvent.getSlot() == AnvilSlot.OUTPUT) {
              event.getWhoClicked().closeInventory();
              if (handler == null) {
                handler = (AnvilClickEventHandler) PlayerUtils.getInstance().getPlayerMeta(player, "AInventory");
              }
              
              Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                if (handler == null) {
                  handler = (AnvilClickEventHandler) PlayerUtils.getInstance()
                          .getPlayerMeta(player, "AInventory");
                }
                handler.onAnvilClick(clickEvent);
              });
              
              destroy();
            }
            
          }
        }
      }
      
      @EventHandler
      public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
          Inventory inv = event.getInventory();
          player.setLevel(player.getLevel() - 1);
          if (inv.equals(versionHandle.getInventory())) {
            inv.clear();
            destroy();
          }
        }
      }
      
      @EventHandler
      public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().equals(getPlayer())) {
          player.setLevel(player.getLevel() - 1);
          destroy();
        }
      }
    };
  }
  
  public void destroy() {
    player = null;
    handler = null;
    items = null;
    
    HandlerList.unregisterAll(listener);
    
    listener = null;
  }
  
  public Player getPlayer() {
    return player;
  }
  
  public void open () {
    versionHandle.open(getPlayer(), items);
  }
  
  public void setSlot (AnvilSlot slot, ItemStack item) {
    items.put(slot, item);
  }
  
}*/
