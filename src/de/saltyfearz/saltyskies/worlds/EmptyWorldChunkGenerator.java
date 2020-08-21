package de.saltyfearz.saltyskies.worlds;

import static org.bukkit.Bukkit.createChunkData;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public class EmptyWorldChunkGenerator extends ChunkGenerator {

  @Override
  public ChunkData generateChunkData (final World world, final Random random, final int posX, final int posZ, final BiomeGrid biomes ) { return createChunkData(world); }
}
