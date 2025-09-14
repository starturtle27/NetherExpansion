package net.netherX.biomes;

import net.minecraft.world.biome.*;

public class BiomeGenBaseNether
{
    public static final BiomeGenBase souldesert;
    public static final BiomeGenBase vileswamp;
    public static final BiomeGenBase stalactite;
    
    static {
        souldesert = new BiomeGenSoulDesert(100).setColor(16711680).setBiomeName("Soulsand Desert").setDisableRain().setTemperatureRainfall(1.8f, 0.0f);
        vileswamp = new BiomeGenVileswamp(101).setColor(16711680).setBiomeName("Mushroom Swamp").setDisableRain().setTemperatureRainfall(1.8f, 0.0f);
        stalactite = new BiomeGenStalactite(102).setColor(16711680).setBiomeName("Spiked Caverns").setDisableRain().setTemperatureRainfall(1.8f, 0.0f);
    }
}
