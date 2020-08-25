package de.saltyfearz.saltyskies.events.chatevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {

  @EventHandler
  public void onPlayerChat ( AsyncPlayerChatEvent event ) {

    event.setFormat( "§4§lS§c§lalty§4§lS§c§lkies §8¤ §7" + event.getPlayer().getName() + " §b§l» §7" + event.getMessage() );

    event.setMessage( event.getMessage().replaceAll( "&", "§" ) );

  }

}
