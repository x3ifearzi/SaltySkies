package de.saltyfearz.saltyskies.events.joinevents;

import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.commands.SpawnCommand;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.ResultSQL;
import de.saltyfearz.saltyskies.mysql.UpdateSQL;
import de.saltyfearz.saltyskies.utils.ReplaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public class FirstJoinEvent implements Listener {

  final private SaltySkies plugin;

  public FirstJoinEvent ( final SaltySkies plugin ) { this.plugin = plugin; }

  @EventHandler
  public void onPlayerJoinEvent ( PlayerJoinEvent event ) {

    Player player = event.getPlayer();

    if ( SpawnCommand.fetchSpawn( "SkyBlock" ) == null ) {

      HashMap<String, String> spawn = SpawnCommand.fetchSpawn( Objects.requireNonNull( player.getLocation( ).getWorld( ) ).getName() );

      if ( ( spawn ).size( ) != 0 ) {

        World world = Bukkit.getWorld( spawn.get( "WORLDNAME" ));

        double x = Double.parseDouble( spawn.get( "POSITIONX" ) );
        double y = Double.parseDouble( spawn.get( "POSITIONY" ) );
        double z = Double.parseDouble( spawn.get( "POSITIONZ" ) );

        float pitch = Float.parseFloat( spawn.get( "POSITIONPITCH" ) );
        float yaw = Float.parseFloat( spawn.get( "POSITIONYAW" ) );

        player.teleport( new Location(world, x, y, z, yaw, pitch) );

      } else {

        Bukkit.getServer().getWorld( "Skyblock" ).getBlockAt( 0, 64, 0 ).setType( Material.BEDROCK );

        player.teleport( new Location ( Bukkit.getServer().getWorld( "Skyblock" ), 0, 65, 0, 0F, 0F) );
      }


    }

    if ( checkIfPlayerExists( player ) ) {

      player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "system", "welcomeBack" ) );
      return;

    }

    player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "system", "welcome" ) );
    player.sendMessage( plugin.getMsgDE().getMessageInfoDE( "system", "loadingData" ) );

    setPlayerData( player );
    setPlayerMoney( player );

    player.sendMessage( plugin.getMsgDE().getMessageSuccessDE( "system", "finishedLoadingData" ) );

  }

  public boolean checkIfPlayerExists( final Player player ) {

    final String searchPlayer = "SELECT * FROM PLAYERDATA WHERE PLAYERUUID = '" + player.getUniqueId( ).toString( ) + "'";

    try {

      ResultSet result = ResultSQL.resultSQL( searchPlayer, CreateConnectionSQL.getConnection() );

      return result.next( );

    } catch ( SQLException exc ) {

      return false;

    }
  }

  public void setPlayerData ( final Player player ) {

    final String insertPlayer = "INSERT INTO PLAYERDATA ( PLAYERUUID, PLAYERNAME, IP ) VALUES ( '" + player.getUniqueId().toString().toLowerCase() + "', '" + player.getName().toLowerCase() + "', '" + player.getAddress().toString() + "' );";

    try {

      UpdateSQL.updateSQL( insertPlayer, CreateConnectionSQL.getConnection() );

    } catch ( SQLException exc ) {
      //
    }
  }

  public void setPlayerMoney ( final Player player ) {

    final String insertPlayerMoney = "INSERT INTO PLAYERMONEY ( MONEY, PLAYERUUID ) VALUES ( " + 1000.00 + ", '" + player.getUniqueId( ).toString( ).toLowerCase( ) + "');";

    try {

      UpdateSQL.updateSQL( insertPlayerMoney, CreateConnectionSQL.getConnection( ) );

    } catch ( SQLException exc ) {
      //
    }
  }

  @EventHandler
  public void onPlayerJoin ( PlayerJoinEvent event ) {

    Player target = event.getPlayer();

    event.setJoinMessage( ReplaceHolder.replaceHolderTarget( target, plugin.getMsgDE().getMessageInfoDE( "system", "joinmessage" ) ) );
  }
}
