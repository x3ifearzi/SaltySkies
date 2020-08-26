package de.saltyfearz.saltyskies.configs;

import de.saltyfearz.saltyskies.SaltySkies;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfigMessager {

  final private SaltySkies plugin;

  private File messageFile;

  private FileConfiguration messageFileConfiguration;

  public CustomConfigMessager(final SaltySkies plugin) {
    this.plugin = plugin;
  }

  public void generateMessageFile() {

    messageFile = new File(Bukkit.getServer().getPluginManager()
            .getPlugin("SaltySkies")
            .getDataFolder(), "messagesDE.yml");

    if (!messageFile.exists())
      return;

    try {

      messageFile.mkdir();

      messageFile.createNewFile();

    } catch (IOException exc) {

      exc.printStackTrace();

    }
    reloadMessageFile();
  }

  public void setMessageFile() {

    try {

      messageFileConfiguration.save(messageFile);

    } catch (IOException exc) {

      exc.printStackTrace();
    }

  }

  public void reloadMessageFile() {

    messageFileConfiguration = YamlConfiguration.loadConfiguration(messageFile);

  }

  public FileConfiguration getMessageFileConfiguration() {
    return messageFileConfiguration;
  }

}
