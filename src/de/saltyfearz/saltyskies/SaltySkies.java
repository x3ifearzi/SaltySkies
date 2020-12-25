package de.saltyfearz.saltyskies;

import de.minnymin.command.CommandFramework;
import de.saltyfearz.saltyskies.commands.*;
import de.saltyfearz.saltyskies.configs.CustomConfigMessager;
import de.saltyfearz.saltyskies.configs.CustomConfigRegions;
import de.saltyfearz.saltyskies.enchantments.*;
import de.saltyfearz.saltyskies.events.chatevents.PlayerChatEvent;
import de.saltyfearz.saltyskies.events.jobevents.FarmingEvent;
import de.saltyfearz.saltyskies.events.jobevents.MiningEvent;
import de.saltyfearz.saltyskies.events.jobevents.SkillEvents;
import de.saltyfearz.saltyskies.events.jobevents.WoodcuttingEvent;
import de.saltyfearz.saltyskies.events.joinevents.FirstJoinEvent;
import de.saltyfearz.saltyskies.events.playerbuildevents.PlayerBuildEvent;
import de.saltyfearz.saltyskies.events.playerbuildevents.WorldGuardEvents;
import de.saltyfearz.saltyskies.handler.chathandler.MessageHandlerDE;
import de.saltyfearz.saltyskies.items.InventoryLayoutItems;
import de.saltyfearz.saltyskies.mysql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import de.saltyfearz.saltyskies.skyblock.IslandLogic;
import de.saltyfearz.saltyskies.skyblock.IslandTools;
import de.saltyfearz.saltyskies.utils.MotdModifier;
import de.saltyfearz.saltyskies.versioncontroller.VersionController;
import de.saltyfearz.saltyskies.worlds.EmptyWorldChunkGenerator;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SaltySkies extends JavaPlugin {

  public static SaltySkies instance;

  private CustomConfigMessager configMessenger;

  private InventoryLayoutItems iLI;

  private MessageHandlerDE msgDE;

  private FarmingEvent farmingEvent;
  private MiningEvent miningEvent;
  private WoodcuttingEvent woodcuttingEvent;

  public static Map<UUID, HashMap<Location, Location>> userRegions = new LinkedHashMap<>();

  public static World skyblockWorld;

  public HashMap < String, IslandLogic > playerIslands = new HashMap <>( );

  @Override
  public void onEnable ( ) {

    SaltySkies.instance = this;

    CreateConnectionSQL.connect( );
    CustomEnchantments.register( );

    try {
      executeTableCreations( );

      MoneySQL.executePlayerSQL();
    } catch ( SQLException exc ) {
      exc.printStackTrace( );
    }

    this.configMessenger = new CustomConfigMessager( this );

    this.farmingEvent = new FarmingEvent( this );
    this.miningEvent = new MiningEvent( this );
    this.woodcuttingEvent = new WoodcuttingEvent( this );

    this.iLI = new InventoryLayoutItems( this );

    this.msgDE = new MessageHandlerDE( this );

    registerConfigs( );

    registerListenerToServer( );
    registerCommandsToServer( );

    try {

      createSkyBlockWorld( skyblockWorld );

    } catch ( SQLException exc ) {

      exc.printStackTrace( );

    }

    userRegions.putAll(getUserRegions());
  }

  @Override
  public void onDisable ( ) {

  }

  private Map<UUID, HashMap<Location, Location>> getUserRegions() {

    userRegions.clear();

    try {
      final String query = "SELECT OWNERUUID, WORLDNAME, POSITIONX_1, POSITIONY_1, POSITIONZ_1, POSITIONX_2, POSITIONY_2, POSITIONZ_2 FROM REGIONS;";

      double posX_1, posY_1, posZ_1, posX_2, posY_2, posZ_2;

      Location locA, locB;

      World worldName;

      UUID ownerUUID;

      HashMap<Location, Location> locationMap = new HashMap<>();

      ResultSet results = ResultSQL.resultSQL(query, CreateConnectionSQL.getConnection());

      while ( results.next() ) {

        ownerUUID = UUID.fromString(results.getString(1));

        worldName = Bukkit.getServer().getWorld(results.getString(2));

        posX_1 = results.getDouble(3);
        posY_1 = results.getDouble(4);
        posZ_1 = results.getDouble(5);
        posX_2 = results.getDouble(6);
        posY_2 = results.getDouble(7);
        posZ_2 = results.getDouble(8);

        locA = new Location(worldName, posX_1, posY_1, posZ_1);
        locB = new Location(worldName, posX_2, posY_2, posZ_2);

        locationMap.put(locA, locB);

        userRegions.put(ownerUUID, locationMap);
      }

      return userRegions;

    } catch ( SQLException exc ) {

      return userRegions;

    }
  }

  private void createSkyBlockWorld ( World skyblockWorld ) throws SQLException {

    if ( skyblockWorld == null ) {

      WorldCreator wC = new WorldCreator( "Skyblock" );

      wC.generator( new EmptyWorldChunkGenerator( ) );

      wC.environment( World.Environment.NORMAL );

      wC.createWorld( );

      this.playerIslands = new HashMap <>( );

      skyblockWorld = this.getServer( ).getWorld( "Skyblock" );

      if ( skyblockWorld != null ) {

        if ( skyblockWorld.getBlockAt( 0, 64 - 1, 0 ).getType( ) == Material.AIR ) {

          for ( int i = -1; i < 2; i++ ) {

            for ( int j = -1; j < 2; j++ ) {

              Block blockToSet = skyblockWorld.getBlockAt( i, 64, j );

              blockToSet.setType( Material.STONE );

            }

          }

        }

        if ( ResultSQL.resultSQL( "SELECT * FROM SPAWN WHERE WorldName = '" + skyblockWorld.getName( ) + "';", CreateConnectionSQL.getConnection( ) ) == null ) {

          UpdateSQL.updateSQL( "INSERT INTO SPAWN (WORLDNAME, POSITIONX, POSITIONY, POSITIONZ, POSITIONPITCH, POSITIONYAW) VALUES ('Skyblock', 0.5, 65, 0.5, 0, 0)", CreateConnectionSQL.getConnection( ) );
          UpdateSQL.updateSQL( "INSERT INTO REGIONS (OWNERUUID, REGIONNAME, WORLDNAME, POSITIONX_1, POSITIONY_1, POSITIONZ_1, POSITIONX_2, POSITIONY_2, POSITIONZ_2) VALUES ('" + Objects.requireNonNull( this.getServer( ).getPlayer( "x3IFeaRzI" ) ).getUniqueId( ).toString( ) + "', 'SPAWN', 'Skyblock', -50, 0, -50, 50, 255, 50 );", CreateConnectionSQL.getConnection( ) );

        }
      }

    }


  }

  private void registerCommandsToServer ( ) {

    CommandFramework cfw = new CommandFramework( this );

    cfw.registerCommands( new TimeCommand( this ) );
    cfw.registerCommands( new SpawnCommand( this ) );
    cfw.registerCommands( new SkullCommand( this ) );
    cfw.registerCommands( new GiveCommand( this ) );
    cfw.registerCommands( new MoneyCommand( this ) );
    cfw.registerCommands( new BuildCommand( this ) );
    cfw.registerCommands( new ClearInventoryCommand( this ) );
    cfw.registerCommands( new MedicCommand( this ) );
    cfw.registerCommands( new EnchantCommand( this ) );
    cfw.registerCommands( new WorldGuardCommand( this ) );
    cfw.registerCommands( new TeleportCommand( this ) );
    cfw.registerCommands( new PunishCommand( this ) );

  }

  private void registerListenerToServer ( ) {

    PluginManager plManager = Bukkit.getPluginManager( );

    plManager.registerEvents( new PlayerBuildEvent( ), this );
    plManager.registerEvents( new WorldGuardEvents( this, userRegions), this);
    plManager.registerEvents( new PlayerChatEvent( ), this );
    plManager.registerEvents( new FirstJoinEvent( this ), this );
    plManager.registerEvents( new WorldGuardCommand( this ), this );

    plManager.registerEvents( new ExplosionEvent( this ), this );
    plManager.registerEvents( new TelepathyEvent( this ), this );
    plManager.registerEvents( new HemorrhageEvent( this ), this );
    plManager.registerEvents( new ExhaustEvent( this ), this );

    plManager.registerEvents( new MotdModifier( ), this );

    plManager.registerEvents( new SkillEvents( this ), this );
  }

  private void registerConfigs ( ) {

    configMessenger.generateMessageFile( );
    configMessenger.getMessageFileConfiguration( ).options( ).copyDefaults( true );
    configMessenger.setMessageFile( );

  }

  private void executeTableCreations ( ) throws SQLException {

    final String createSpawnTable = "SPAWN (WORLDNAME varchar ( 32 ), POSITIONX varchar ( 20 ), POSITIONY varchar ( 20 ), POSITIONZ varchar ( 20 ), POSITIONPITCH varchar ( 20 ), POSITIONYAW varchar ( 20 ), PRIMARY KEY ( WORLDNAME ))";

    final String createPlayerTable = "PLAYERDATA (PLAYERUUID varchar ( 48 ), PLAYERNAME varchar ( 16 ), IP varchar ( 16 ), PRIMARY KEY ( PLAYERUUID ));";

    final String createPlayerRankTable = "PLAYERRANKS ( PLAYERUUID varchar ( 48 ), RANK varchar ( 32 ), FOREIGN KEY ( PLAYERUUID ) REFERENCES PLAYERDATA ( PLAYERUUID ));";

    final String createPlayerPunishTable = "PLAYERPUNISH ( ID int (255) NOT NULL AUTO_INCREMENT, IS_BANNED boolean, IS_BANNED_UNTIL bigint, IS_MUTED boolean, IS_MUTED_UNTIL bigint, PLAYERUUID varchar ( 48 ), PRIMARY KEY ( ID ), FOREIGN KEY (PLAYERUUID) REFERENCES PLAYERDATA(PLAYERUUID));";

    final String createPlayerMoneyTable = "PLAYERMONEY ( ID int ( 255 ) NOT NULL AUTO_INCREMENT, MONEY double, PLAYERUUID varchar ( 48 ), PRIMARY KEY ( ID ), FOREIGN KEY (PLAYERUUID) REFERENCES PLAYERDATA(PLAYERUUID));";

    final String createRegionTable = "REGIONS ( ID int ( 255 ) AUTO_INCREMENT, OWNERUUID varchar ( 48 ), REGIONNAME varchar ( 32 ), WORLDNAME varchar ( 32 ), POSITIONX_1 double, POSITIONY_1 double, POSITIONZ_1 double, POSITIONX_2 double, POSITIONY_2 double, POSITIONZ_2 double, PRIMARY KEY ( ID ) );";

    final String createRegionMemberTable = "REGIONMEMBERS ( ID int ( 255 ) NOT NULL AUTO_INCREMENT, OWNERUUID varchar ( 48 ), MEMBERUUID varchar ( 48 ), PRIMARY KEY ( ID ), FOREIGN KEY ( OWNERUUID ) REFERENCES REGIONS ( OWNERUUID ) );";

    Connection con = CreateConnectionSQL.getConnection( );

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

  public MessageHandlerDE getMsgDE ( ) {
    return msgDE;
  }

  public InventoryLayoutItems getiLI ( ) {
    return iLI;
  }

  public FarmingEvent getFarmingEvent ( ) { return farmingEvent; }

  public MiningEvent getMiningEvent ( ) { return miningEvent; }

  public WoodcuttingEvent getWoodcuttingEvent ( ) { return woodcuttingEvent; }

}
