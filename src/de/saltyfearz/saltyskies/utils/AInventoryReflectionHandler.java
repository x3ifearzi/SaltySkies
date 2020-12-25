/*
package de.saltyfearz.saltyskies.utils;

import me.x3ifearzi.skyblock.controller.VersionController;
import me.x3ifearzi.skyblock.utils.AInventory.AnvilClickEventHandler;
import me.x3ifearzi.skyblock.utils.AInventory.AnvilSlot;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AInventoryReflectionHandler implements me.x3ifearzi.skyblock.utils.AInventoryVersionHandler {
    
    private static Class<?>  BlockPosition;
    private static Class<?>  PacketPlayOutOpenWindow;
    private static Class<?>  ContainerAnvil;
    private static Class<?>  ChatMessage;
    private static Class<?>  EntityHuman;
    private        Inventory inv;
    
    public AInventoryReflectionHandler (final Player player, final AnvilClickEventHandler anvilClickEventHandler) {
        loadClasses();
        PlayerUtils.getInstance().setPlayerMeta(player, "AInventory", anvilClickEventHandler);
    }
    
    @Override
    public Inventory getInventory () {
        return inv;
    }
    
    @Override
    public void loadClasses () {
        BlockPosition           = VersionController.get().getNMSClass("BlockPosition");
        PacketPlayOutOpenWindow = VersionController.get().getNMSClass("PacketPlayOutOpenWindow");
        ContainerAnvil          = VersionController.get().getNMSClass("ContainerAnvil");
        EntityHuman             = VersionController.get().getNMSClass("EntityHuman");
        ChatMessage             = VersionController.get().getNMSClass("ChatMessage");
    }
    
    @Override
    public void open (Player player, HashMap<AnvilSlot, ItemStack> items) {
        player.setLevel(player.getLevel() + 1);
        
        try {
            Object p = VersionController.get().getHandle(player);
            
            Object container = ContainerAnvil.getConstructor(VersionController.get().getNMSClass("PlayerInventory"), VersionController.get().getNMSClass("World"), BlockPosition, EntityHuman).newInstance(VersionController.get().getPlayerField(player, "inventory"), VersionController.get().getPlayerField(player, "world"), BlockPosition.getConstructor(int.class, int.class, int.class).newInstance(0, 0, 0), p);
            VersionController.get().getField(VersionController.get().getNMSClass("Container"), "checkReachable").set(container, false);
            
            Object bukkitView = VersionController.get().invokeMethod("getBukkitView", container);
            inv = (Inventory) VersionController.get().invokeMethod("getTopInventory", bukkitView);
            
            for (AnvilSlot slot : items.keySet()) {
                inv.setItem(slot.getSlot(), items.get(slot));
            }
            
            int c = (int) VersionController.get().invokeMethod("nextContainerCounter", p);
            
            Constructor<?> chatMessageConstructor = ChatMessage.getConstructor(String.class, Object[].class);
            Object         playerConnection       = VersionController.get().getPlayerField(player, "playerConnection");
            Object         packet                 = PacketPlayOutOpenWindow.getConstructor(int.class, String.class, VersionController.get().getNMSClass("IChatBaseComponent"), int.class).newInstance(c, "minecraft:anvil", chatMessageConstructor.newInstance("Repairing", new Object[]{}), 0);
            
            Method sendPacket = VersionController.get().getMethod("sendPacket", playerConnection.getClass(), PacketPlayOutOpenWindow);
            sendPacket.invoke(playerConnection, packet);
            
            Field activeContainerField = VersionController.get().getField(EntityHuman, "activeContainer");
            if (activeContainerField != null) {
                activeContainerField.set(p, container);
                VersionController.get().getField(VersionController.get().getNMSClass("Container"), "windowId").set(activeContainerField.get(p), c);
                VersionController.get().getMethod("addSlotListener", activeContainerField.get(p).getClass(), p.getClass()).invoke(activeContainerField.get(p), p);
            }
        } catch (Exception e) {
            //
        }
    }
    
}
*/
