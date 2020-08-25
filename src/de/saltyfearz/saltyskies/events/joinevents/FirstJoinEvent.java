package de.saltyfearz.saltyskies.events.joinevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinEvent implements Listener {

  @EventHandler
  public void onPlayerJoinEvent ( PlayerJoinEvent event ) {

    Player player = event.getPlayer();


  }
}
