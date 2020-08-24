package de.saltyfearz.saltyskies.worlds;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class EmptyWorldChunkGenerator extends ChunkGenerator {

  @Override
  public ChunkData generateChunkData (final World world, final Random random, final int posX, final int posZ, final BiomeGrid biomes ) { return createChunkData(world); }
}
