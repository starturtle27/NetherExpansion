//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import net.minecraft.world.gen.layer.*;

public abstract class GenLayerNXHell extends GenLayer
{
    public GenLayerNXHell(final long seed) {
        super(seed);
    }
    
    public static GenLayer[] genWorld(final long seed) {
        GenLayer biomes = (GenLayer)new GenLayerNXBiomes(1L);
        biomes = (GenLayer)new GenLayerZoom(1000L, biomes);
        biomes = (GenLayer)new GenLayerZoom(1001L, biomes);
        biomes = (GenLayer)new GenLayerZoom(1002L, biomes);
        biomes = (GenLayer)new GenLayerZoom(1003L, biomes);
        biomes = (GenLayer)new GenLayerZoom(1004L, biomes);
        biomes = (GenLayer)new GenLayerZoom(1005L, biomes);
        final GenLayer genlayervoronoizoom = (GenLayer)new GenLayerVoronoiZoom(1L, biomes);
        biomes.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        return new GenLayer[] { biomes, genlayervoronoizoom };
    }
}
