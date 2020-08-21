package de.saltyfearz.saltyskies;

import org.bukkit.plugin.java.JavaPlugin;

public class SaltySkies extends JavaPlugin {

  public static SaltySkies instance;

  @Override
  public void onEnable () {

    SaltySkies.instance = this;

  }

  @Override
  public void onDisable () {

  }
}
