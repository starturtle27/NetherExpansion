//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.layer.*;

public class GenLayerNXBiomes extends GenLayer
{
    protected BiomeGenBase[] allowedBiomes;
    
    public GenLayerNXBiomes(final long seed, final GenLayer genlayer) {
        super(seed);
        this.allowedBiomes = new BiomeGenBase[] { BiomeGenBaseNether.vileswamp, BiomeGenBaseNether.stalactite, BiomeGenBaseNether.souldesert, BiomeGenBase.hell, BiomeGenBase.hell, BiomeGenBase.hell };
        this.parent = genlayer;
    }
    
    public GenLayerNXBiomes(final long seed) {
        super(seed);
        this.allowedBiomes = new BiomeGenBase[] { BiomeGenBaseNether.vileswamp, BiomeGenBaseNether.stalactite, BiomeGenBaseNether.souldesert, BiomeGenBase.hell, BiomeGenBase.hell, BiomeGenBase.hell };
    }
    
    public int[] getInts(final int x, final int z, final int width, final int depth) {
        final int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.initChunkSeed((long)(dx + x), (long)(dz + z));
                dest[dx + dz * width] = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;
            }
        }
        return dest;
    }
}
