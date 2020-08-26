package de.saltyfearz.saltyskies;

import de.minnymin.command.Command;
import de.minnymin.command.CommandFramework;
import de.saltyfearz.saltyskies.commands.SkullCommand;
import de.saltyfearz.saltyskies.commands.SpawnCommand;
import de.saltyfearz.saltyskies.commands.TimeCommand;
import de.saltyfearz.saltyskies.configs.CustomConfigMessager;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.CreateTableSQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.plugin.java.JavaPlugin;

public class SaltySkies extends JavaPlugin {

  public static SaltySkies instance;

  private CustomConfigMessager configMessenger;
  private MessageHandlerDE msgDE;

  @Override
  public void onEnable () {

    SaltySkies.instance = this;

    CreateConnectionSQL.connect();
    try {
      executeTableCreations();
    } catch ( SQLException exc ) {
      //
    }

    this.configMessenger = new CustomConfigMessager(this);
    this.msgDE = new MessageHandlerDE(this);

    configMessenger.generateMessageFile();
    configMessenger.getMessageFileConfiguration().options().copyDefaults(true);
    configMessenger.setMessageFile();

    registerCommandsToServer();


  }

  @Override
  public void onDisable () {

  }

  public void registerCommandsToServer() {

    CommandFramework cfw = new CommandFramework(this);

    cfw.registerCommands(new TimeCommand(this));
    cfw.registerCommands(new SpawnCommand(this));
    cfw.registerCommands(new SkullCommand(this));

  }

  public void registerListenerToServer() {


  }

  public void executeTableCreations () throws SQLException {

    final String createSpawnTable   = "SPAWN (WORLDNAME varchar ( 32 ), POSITIONX varchar ( 20 ), POSITIONY varchar ( 3 ), POSITIONZ varchar ( 20 ), POSITIONPITCH varchar ( 10 ), POSITIONYAW varchar ( 10 ), PRIMARY KEY ( WORLDNAME ))";

    CreateTableSQL.createTableSQL(createSpawnTable, CreateConnectionSQL.con);

  }


  public CustomConfigMessager getConfigMessenger() {
    return configMessenger;
  }

  public MessageHandlerDE getMsgDE() {
    return msgDE;
  }
}
