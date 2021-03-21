package net.gegy1000.overworldtwo.mixin;

import net.gegy1000.overworldtwo.config.OverworldTwoConfig;
import net.gegy1000.overworldtwo.generator.OverworldTwoChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(DimensionType.class)
public class MixinDimensionType {

	/**
	 * @reason nether-two :D
	 *
	 * @author SuperCoder79
	 */
	@Overwrite
	public static ChunkGenerator createNetherGenerator(Registry<Biome> biomes, Registry<ChunkGeneratorSettings> chunkgens, long seed) {
		if (OverworldTwoConfig.get().generateNether) {
			return new OverworldTwoChunkGenerator(MultiNoiseBiomeSource.Preset.NETHER.getBiomeSource(biomes, seed), seed, OverworldTwoChunkGenerator.NETHER);
		}

		// Vanilla generator
		return new NoiseChunkGenerator(MultiNoiseBiomeSource.Preset.NETHER.getBiomeSource(biomes, seed), seed, () -> chunkgens.getOrThrow(ChunkGeneratorSettings.NETHER));
	}
}
