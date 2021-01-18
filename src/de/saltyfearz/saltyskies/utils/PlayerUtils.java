package de.saltyfearz.saltyskies.utils;

import com.google.common.collect.Iterables;
import de.saltyfearz.saltyskies.SaltySkies;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerUtils {

  private static SaltySkies plugin = SaltySkies.instance;

  static PlayerUtils instance = new PlayerUtils(plugin);
  public static PlayerUtils getInstance() {
    return instance;
  }

  private PlayerUtils ( final SaltySkies plugin ) {
    PlayerUtils.plugin = plugin;
  }
  public Object getPlayerMeta(Player player, String str) {
    for (MetadataValue meta : player.getMetadata(str)) {
      if (meta.getOwningPlugin().equals(plugin)) {
        return meta.value();
      }
    }
    return null;
  }

  public BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
    if (useSubCardinalDirections)
      return radial[Math.round(yaw / 45f) & 0x7].getOppositeFace();

    return axis[Math.round(yaw / 90f) & 0x3].getOppositeFace();
  }

  private final BlockFace[] axis   = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
  private final BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST,
      BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };


  public Player getRandomOnlinePlayer() {
    @SuppressWarnings("unchecked")
    ArrayList<Player> players = (ArrayList<Player>) Bukkit.getOnlinePlayers();
    if (!players.isEmpty()) {
      return players.get(ThreadLocalRandom.current().nextInt(players.size()));
    } else {
      return null;
    }
  }

  public Player getRandomPlayer() {
    return Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
  }

  public void setPlayerMeta(Player player, String str, Object value) {
    player.removeMetadata(str, plugin);
    player.setMetadata(str, new MetadataValue() {

      @Override
      public boolean asBoolean() {

        return false;
      }

      @Override
      public byte asByte() {

        return 0;
      }

      @Override
      public double asDouble() {

        return 0;
      }

      @Override
      public float asFloat() {

        return 0;
      }

      @Override
      public int asInt() {

        return 0;
      }

      @Override
      public long asLong() {

        return 0;
      }

      @Override
      public short asShort() {

        return 0;
      }

      @Override
      public String asString() {

        return null;
      }

      @Override
      public SaltySkies getOwningPlugin() {
        return plugin;
      }

      @Override
      public void invalidate() {
      }

      @Override
      public Object value() {
        return value;
      }

    });
  }

}
