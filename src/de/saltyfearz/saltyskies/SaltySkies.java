package de.saltyfearz.saltyskies;

import de.saltyfearz.saltyskies.MySQL.CreateConnectionSQL;
import org.bukkit.plugin.java.JavaPlugin;

public class SaltySkies extends JavaPlugin {

  public static SaltySkies instance;

  @Override
  public void onEnable () {

    SaltySkies.instance = this;

    CreateConnectionSQL.connect();

  }

  @Override
  public void onDisable () {

  }
}
