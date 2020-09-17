package de.saltyfearz.saltyskies.utils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdModifier implements Listener {

    @EventHandler
    public void onServerListPing( ServerListPingEvent event ) {

        event.setMotd( event.getMotd() );
    }
}
