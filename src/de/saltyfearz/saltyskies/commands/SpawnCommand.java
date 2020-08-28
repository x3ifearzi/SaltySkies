package de.saltyfearz.saltyskies.commands;

import de.minnymin.command.Command;
import de.minnymin.command.CommandArgs;
import de.saltyfearz.saltyskies.SaltySkies;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.ResultSQL;
import de.saltyfearz.saltyskies.mysql.UpdateSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnCommand {

  final private SaltySkies plugin;

  public SpawnCommand ( final SaltySkies plugin ) { this.plugin = plugin; }

  @Command(name = "setspawn", description = "§6Setze den Einstiegspunkt in der Welt.", usage = "§6/setspawn", permission = "SaltySkies.setspawn")
  public void setSpawn(CommandArgs args) {

    Player player = args.getPlayer();

    int changedRows = setSpawnPointSQL(

        player.getWorld().getName(),
        String.valueOf(player.getLocation().getX()),
        String.valueOf(player.getLocation().getY()),
        String.valueOf(player.getLocation().getZ()),
        String.valueOf(player.getLocation().getPitch()),
        String.valueOf(player.getLocation().getY())

    );

    if (changedRows == -1) {
        player.sendMessage(plugin.getMsgDE().getMessageErrorDE( "spawn-command", "createSpawnFailure"));

    } else {
      player.sendMessage(plugin.getMsgDE().getMessageSuccessDE("spawn-command", "createSpawnSuccess"));
    }
  }

  @Command(name = "spawn", description = "§6Teleportiere dich zum Einstiegspunkt der Welt.", usage = "§6/spawn [<player>]")
  public void teleportSpawn(CommandArgs args) {

    Player player = args.getPlayer();

    String[] arg = args.getArgs();

    HashMap<String, String> spawn = fetchSpawn(player.getWorld()
                                                     .getName());

    if (spawn == null) {

      player.sendMessage(plugin.getMsgDE().getMessageErrorDE( "spawn-command", "spawnNotExists"));
      return;

    }

    World world = Bukkit.getServer()
                        .getWorld(spawn.get("WORLDNAME"));
    double x    = Double.parseDouble(spawn.get("POSITIONX"));
    double y    = Double.parseDouble(spawn.get("POSITIONY"));
    double z    = Double.parseDouble(spawn.get("POSITIONZ"));
    float pitch = Float.parseFloat(spawn.get("POSITIONPITCH"));
    float yaw   = Float.parseFloat(spawn.get("POSITIONYAW"));

    if (arg.length > 1) {

      player.sendMessage(plugin.getMsgDE().getMessageInfoDE( "spawn-command", "syntax"));
      return;

    }

    if (arg.length == 0) {

      player.teleport( new Location ( world, x, y, z, yaw, pitch ));
      player.sendMessage(plugin.getMsgDE().getMessageSuccessDE( "spawn-command", "spawntp"));

    } else {

      Player target = Bukkit.getServer().getPlayer(arg[0]);

      if ( target == null ) {

        player.sendMessage(plugin.getMsgDE().getMessageInfoDE( "system", "noTargetOnline" ));
        return;

      }

      target.teleport( new Location ( world, x, y, z, yaw, pitch ));
      target.sendMessage(plugin.getMsgDE().getMessageInfoDE( "spawn-command", "spawntp"));

    }


  }

  public int setSpawnPointSQL(final String worldName, final String posX, final String posY, final String posZ, final String posPitch, final String posYaw) {

    final String initializeSpawn =
        "INSERT INTO SPAWN ( WORLDNAME, POSITIONX, POSITIONY, POSITIONZ, POSITIONPITCH, POSITIONYAW ) VALUES ('" + worldName.toLowerCase() + "', '" + posX
            + "', '" + posY + "', '" + posZ + "', '" + posPitch + "', '" + posYaw + "') ON DUPLICATE KEY UPDATE WorldName = '" + worldName.toLowerCase()
            + "', PositionX = '" + posX + "', PositionY = '"
            + posY + "', PositionZ = '" + posZ + "', PositionPitch = '" + posPitch + "', PositionYaw = '" + posYaw + "'";

    try {

      return UpdateSQL.updateSQL(initializeSpawn, CreateConnectionSQL.getConnection());

    } catch (SQLException exc) {

      exc.printStackTrace();

      return -1;
    }
  }

  public static HashMap<String, String> fetchSpawn ( final String worldName ) {

    final String fetchSpawn = "SELECT * FROM SPAWN WHERE WORLDNAME = '" + worldName.toLowerCase() + "';";

    HashMap<String, String> resultSpawn = new HashMap<>();

    try {

      ResultSet result = ResultSQL.resultSQL(fetchSpawn, CreateConnectionSQL.getConnection());

      if (result.next()) {

        resultSpawn.put( "WORLDNAME", result.getString( 1 ));
        resultSpawn.put( "POSITIONX", result.getString( 2 ));
        resultSpawn.put( "POSITIONY", result.getString( 3 ));
        resultSpawn.put( "POSITIONZ", result.getString( 4 ));
        resultSpawn.put( "POSITIONPITCH", result.getString( 5 ));
        resultSpawn.put( "POSITIONYAW", result.getString( 6 ));

      }

      return resultSpawn;

    } catch ( SQLException exc ) {

      exc.printStackTrace();
      return null;
    }
  }
}
