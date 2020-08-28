package de.saltyfearz.saltyskies;

import de.minnymin.command.CommandFramework;
import de.saltyfearz.saltyskies.commands.*;
import de.saltyfearz.saltyskies.configs.CustomConfigMessager;
import de.saltyfearz.saltyskies.events.chatevents.PlayerChatEvent;
import de.saltyfearz.saltyskies.events.joinevents.FirstJoinEvent;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.CreateTableSQL;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SaltySkies extends JavaPlugin {

  public static SaltySkies instance;

  private CustomConfigMessager configMessenger;
  private MessageHandlerDE msgDE;

  @Override
  public void onEnable ( ) {

    SaltySkies.instance = this;

    CreateConnectionSQL.connect( );
    try {
      executeTableCreations( );
    } catch ( SQLException exc ) {
      exc.printStackTrace( );
    }

    this.configMessenger = new CustomConfigMessager( this );
    this.msgDE = new MessageHandlerDE( this );

    registerConfigs( );

    registerListenerToServer( );
    registerCommandsToServer( );


  }

  @Override
  public void onDisable ( ) {

  }

  public void registerCommandsToServer ( ) {

    CommandFramework cfw = new CommandFramework( this );

    cfw.registerCommands( new TimeCommand( this ) );
    cfw.registerCommands( new SpawnCommand( this ) );
    cfw.registerCommands( new SkullCommand( this ) );
    cfw.registerCommands( new GiveCommand( this ) );
    cfw.registerCommands( new MoneyCommand( this ) );
    cfw.registerCommands( new BuildCommand( this ) );
    cfw.registerCommands( new ClearInventoryCommand( this ));

  }

  public void registerListenerToServer ( ) {

    PluginManager plManager = Bukkit.getPluginManager( );

    plManager.registerEvents( new PlayerChatEvent( ), this );
    plManager.registerEvents( new FirstJoinEvent( this ), this );
  }

  public void registerConfigs ( ) {

    configMessenger.generateMessageFile( );
    configMessenger.getMessageFileConfiguration( ).options( ).copyDefaults( true );
    configMessenger.setMessageFile( );

  }

  public void executeTableCreations ( ) throws SQLException {

    final String createSpawnTable = "SPAWN (WORLDNAME varchar ( 32 ), POSITIONX varchar ( 20 ), POSITIONY varchar ( 3 ), POSITIONZ varchar ( 20 ), POSITIONPITCH varchar ( 10 ), POSITIONYAW varchar ( 10 ), PRIMARY KEY ( WORLDNAME ))";

    final String createPlayerTable = "PLAYERDATA (PLAYERUUID varchar ( 48 ), PLAYERNAME varchar ( 16 ), IP varchar ( 16 ), PRIMARY KEY ( PLAYERUUID ));";

    final String createPlayerPunishTable = "PLAYERPUNISH ( ID int (255) NOT NULL AUTO_INCREMENT, IS_BANNED boolean, IS_BANNED_UNTIL bigint, IS_MUTED boolean, IS_MUTED_UNTIL bigint, PRIMARY KEY ( ID ), FOREIGN KEY ( PLAYERUUID ) REFERENCES PLAYERDATA ( PLAYERUUID ));";

    final String createPlayerMoneyTable = "PLAYERMONEY ( ID int ( 255 ) NOT NULL AUTO_INCREMENT, MONEY double, FOREIGN KEY ( PLAYERUUID ) REFERENCES PLAYERDATA ( PLAYERUUID ));";

    CreateTableSQL.createTableSQL( createSpawnTable, CreateConnectionSQL.getConnection() );
    CreateTableSQL.createTableSQL( createPlayerTable, CreateConnectionSQL.getConnection() );
    CreateTableSQL.createTableSQL( createPlayerPunishTable, CreateConnectionSQL.getConnection() );
    CreateTableSQL.createTableSQL( createPlayerMoneyTable, CreateConnectionSQL.getConnection() );

  }


  public CustomConfigMessager getConfigMessenger ( ) {
    return configMessenger;
  }

  public MessageHandlerDE getMsgDE ( ) {
    return msgDE;
  }
}
