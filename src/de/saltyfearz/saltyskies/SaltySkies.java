package de.saltyfearz.saltyskies;

import de.minnymin.command.CommandFramework;
import de.saltyfearz.saltyskies.commands.*;
import de.saltyfearz.saltyskies.configs.CustomConfigMessager;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import de.saltyfearz.saltyskies.enchantments.*;
import de.saltyfearz.saltyskies.events.chatevents.PlayerChatEvent;
import de.saltyfearz.saltyskies.events.joinevents.FirstJoinEvent;
import de.saltyfearz.saltyskies.events.playerbuildevents.PlayerBuildEvent;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import de.saltyfearz.saltyskies.mysql.CreateConnectionSQL;
import de.saltyfearz.saltyskies.mysql.CreateTableSQL;

import java.sql.Connection;
import java.sql.SQLException;

import de.saltyfearz.saltyskies.utils.MotdModifier;
import de.saltyfearz.saltyskies.worlds.EmptyWorldChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SaltySkies extends JavaPlugin {

  public static SaltySkies instance;

  private CustomConfigMessager configMessenger;
  private CustomConfigRegions configRegions;

  private MessageHandlerDE msgDE;

  public static World skyblockWorld;

  @Override
  public void onEnable ( ) {

    SaltySkies.instance = this;

    CreateConnectionSQL.connect( );
    CustomEnchantments.register( );

    try {
      executeTableCreations( );
    } catch ( SQLException exc ) {
      exc.printStackTrace( );
    }

    this.configMessenger = new CustomConfigMessager( this );
    this.configRegions = new CustomConfigRegions( this );

    this.msgDE = new MessageHandlerDE( this );

    registerConfigs( );

    registerListenerToServer( );
    registerCommandsToServer( );

    createSkyBlockWorld( skyblockWorld );

  }

  @Override
  public void onDisable ( ) {

  }

  public void createSkyBlockWorld ( World skyblockWorld ) {

    if ( skyblockWorld == null ) {

      WorldCreator wC = new WorldCreator( "Skyblock" );

      wC.generator( new EmptyWorldChunkGenerator() );

      wC.environment( World.Environment.NORMAL );

      wC.createWorld();

    }

  }

  public void registerCommandsToServer ( ) {

    CommandFramework cfw = new CommandFramework( this );

    cfw.registerCommands( new TimeCommand( this ) );
    cfw.registerCommands( new SpawnCommand( this ) );
    cfw.registerCommands( new SkullCommand( this ) );
    cfw.registerCommands( new GiveCommand( this ) );
    cfw.registerCommands( new MoneyCommand( this ) );
    cfw.registerCommands( new BuildCommand( this ) );
    cfw.registerCommands( new ClearInventoryCommand( this ) );
    cfw.registerCommands( new MedicCommand( this ) );
    cfw.registerCommands( new EnchantCommand( this ));
    cfw.registerCommands( new WorldGuardCommand( this ) );
    cfw.registerCommands( new TeleportCommand( this ));

  }

  public void registerListenerToServer ( ) {

    PluginManager plManager = Bukkit.getPluginManager( );

    plManager.registerEvents( new PlayerChatEvent( ), this );
    plManager.registerEvents( new FirstJoinEvent( this ), this );
    plManager.registerEvents( new WorldGuardCommand( this ), this );
    plManager.registerEvents( new PlayerBuildEvent(), this);

    plManager.registerEvents( new ExplosionEvent( this ), this );
    plManager.registerEvents( new TelepathyEvent( this ), this );
    plManager.registerEvents( new HemorrhageEvent( this ), this );
    plManager.registerEvents( new ExhaustEvent( this ), this );

    plManager.registerEvents( new MotdModifier( ), this );
  }

  public void registerConfigs ( ) {

    configMessenger.generateMessageFile( );
    configMessenger.getMessageFileConfiguration( ).options( ).copyDefaults( true );
    configMessenger.setMessageFile( );

  }

  public void executeTableCreations ( ) throws SQLException {

    final String createSpawnTable = "SPAWN (WORLDNAME varchar ( 32 ), POSITIONX varchar ( 20 ), POSITIONY varchar ( 3 ), POSITIONZ varchar ( 20 ), POSITIONPITCH varchar ( 10 ), POSITIONYAW varchar ( 10 ), PRIMARY KEY ( WORLDNAME ))";

    final String createPlayerTable = "PLAYERDATA (PLAYERUUID varchar ( 48 ), PLAYERNAME varchar ( 16 ), IP varchar ( 16 ), PRIMARY KEY ( PLAYERUUID ));";

    final String createPlayerRankTable = "PLAYERRANKS ( PLAYERUUID varchar ( 48 ), RANK varchar ( 32 ), FOREIGN KEY ( PLAYERUUID ) REFERENCES PLAYERDATA ( PLAYERUUID ));";

    final String createPlayerPunishTable = "PLAYERPUNISH ( ID int (255) NOT NULL AUTO_INCREMENT, IS_BANNED boolean, IS_BANNED_UNTIL bigint, IS_MUTED boolean, IS_MUTED_UNTIL bigint, PLAYERUUID varchar ( 48 ), PRIMARY KEY ( ID ), FOREIGN KEY (PLAYERUUID) REFERENCES PLAYERDATA(PLAYERUUID));";

    final String createPlayerMoneyTable = "PLAYERMONEY ( ID int ( 255 ) NOT NULL AUTO_INCREMENT, MONEY double, PLAYERUUID varchar ( 48 ), PRIMARY KEY ( ID ), FOREIGN KEY (PLAYERUUID) REFERENCES PLAYERDATA(PLAYERUUID));";

    final String createRegionTable = "REGIONS ( ID int ( 255 ) AUTO_INCREMENT, OWNERUUID varchar ( 48 ), REGIONNAME varchar ( 32 ), WORLDNAME varchar ( 32 ), POSITIONX_1 double, POSITIONY_1 double, POSITIONZ_1 double, POSITIONX_2 double, POSITIONY_2 double, POSITIONZ_2 double, PRIMARY KEY ( ID ) );";

    final String createRegionMemberTable = "REGIONMEMBERS ( ID int ( 255 ) NOT NULL AUTO_INCREMENT, OWNERUUID varchar ( 48 ), MEMBERUUID varchar ( 48 ), PRIMARY KEY ( ID ), FOREIGN KEY ( OWNERUUID ) REFERENCES REGIONS ( OWNERUUID ) );";

    Connection con = CreateConnectionSQL.getConnection();

    CreateTableSQL.createTableSQL( createSpawnTable, con );
    CreateTableSQL.createTableSQL( createPlayerTable, con );
    CreateTableSQL.createTableSQL( createPlayerRankTable, con );
    CreateTableSQL.createTableSQL( createPlayerPunishTable, con );
    CreateTableSQL.createTableSQL( createPlayerMoneyTable, con );
    CreateTableSQL.createTableSQL( createRegionTable, con );

  }


  public CustomConfigMessager getConfigMessenger ( ) {
    return configMessenger;
  }

  public CustomConfigRegions getConfigRegions ( ) { return configRegions; }

  public MessageHandlerDE getMsgDE ( ) {
    return msgDE;
  }
}
