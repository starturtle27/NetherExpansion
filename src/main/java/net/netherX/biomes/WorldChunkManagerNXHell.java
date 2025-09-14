//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.layer.*;
import cpw.mods.fml.relauncher.*;
import java.util.*;
import net.minecraft.world.*;

public class WorldChunkManagerNXHell extends WorldChunkManager
{
    private GenLayer nxGenBiomes;
    private GenLayer nxBiomeIndexLayer;
    private BiomeCache nxBiomeCache;
    private List<BiomeGenBase> nxBiomesToSpawnIn;
    
    protected WorldChunkManagerNXHell() {
        this.nxBiomeCache = new BiomeCache((WorldChunkManager)this);
        (this.nxBiomesToSpawnIn = new ArrayList<BiomeGenBase>()).add(BiomeGenBase.hell);
    }
    
    public WorldChunkManagerNXHell(final long seed, final WorldType worldtype) {
        this();
        final GenLayer[] agenlayer = GenLayerNXHell.genWorld(seed);
        this.nxGenBiomes = agenlayer[0];
        this.nxBiomeIndexLayer = agenlayer[1];
    }
    
    public WorldChunkManagerNXHell(final World world) {
        this(world.getSeed(), world.provider.terrainType);
    }
    
    public List<BiomeGenBase> getBiomesToSpawnIn() {
        return this.nxBiomesToSpawnIn;
    }
    
    public BiomeGenBase getBiomeGenAt(final int x, final int z) {
        final BiomeGenBase biome = this.nxBiomeCache.getBiomeGenAt(x, z);
        if (biome == null) {
            return BiomeGenBase.hell;
        }
        return biome;
    }
    
    public float[] getRainfall(float[] par1ArrayOfFloat, final int par2, final int par3, final int par4, final int par5) {
        IntCache.resetIntCache();
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
            par1ArrayOfFloat = new float[par4 * par5];
        }
        final int[] aint = this.nxBiomeIndexLayer.getInts(par2, par3, par4, par5);
        for (int i1 = 0; i1 < par4 * par5; ++i1) {
            float f = BiomeGenBase.biomeList[aint[i1]].getIntRainfall() / 65536.0f;
            if (f > 1.0f) {
                f = 1.0f;
            }
            par1ArrayOfFloat[i1] = f;
        }
        return par1ArrayOfFloat;
    }
    
    @SideOnly(Side.CLIENT)
    public float getTemperatureAtHeight(final float par1, final int par2) {
        return par1;
    }
    
    public float[] getTemperatures(float[] par1ArrayOfFloat, final int par2, final int par3, final int par4, final int par5) {
        IntCache.resetIntCache();
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
            par1ArrayOfFloat = new float[par4 * par5];
        }
        final int[] aint = this.nxBiomeIndexLayer.getInts(par2, par3, par4, par5);
        for (int i1 = 0; i1 < par4 * par5; ++i1) {
            float f = BiomeGenBase.biomeList[aint[i1]].getIntTemperature() / 65536.0f;
            if (f > 1.0f) {
                f = 1.0f;
            }
            par1ArrayOfFloat[i1] = f;
        }
        return par1ArrayOfFloat;
    }
    
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, final int par2, final int par3, final int par4, final int par5) {
        IntCache.resetIntCache();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
        final int[] aint = this.nxGenBiomes.getInts(par2, par3, par4, par5);
        for (int i = 0; i < par4 * par5; ++i) {
            if (aint[i] >= 0) {
                par1ArrayOfBiomeGenBase[i] = BiomeGenBase.biomeList[aint[i]];
            }
            else {
                par1ArrayOfBiomeGenBase[i] = BiomeGenBase.hell;
            }
        }
        return par1ArrayOfBiomeGenBase;
    }
    
    public BiomeGenBase[] loadBlockGeneratorData(final BiomeGenBase[] par1ArrayOfBiomeGenBase, final int par2, final int par3, final int par4, final int par5) {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }
    
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, final int x, final int y, final int width, final int length, final boolean cacheFlag) {
        IntCache.resetIntCache();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < width * length) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[width * length];
        }
        if (cacheFlag && width == 16 && length == 16 && (x & 0xF) == 0x0 && (y & 0xF) == 0x0) {
            final BiomeGenBase[] abiomegenbase1 = this.nxBiomeCache.getCachedBiomes(x, y);
            System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, width * length);
            return par1ArrayOfBiomeGenBase;
        }
        final int[] aint = this.nxBiomeIndexLayer.getInts(x, y, width, length);
        for (int i = 0; i < width * length; ++i) {
            if (aint[i] >= 0) {
                par1ArrayOfBiomeGenBase[i] = BiomeGenBase.biomeList[aint[i]];
            }
            else {
                par1ArrayOfBiomeGenBase[i] = BiomeGenBase.hell;
            }
        }
        return par1ArrayOfBiomeGenBase;
    }
    
    public boolean areBiomesViable(final int par1, final int par2, final int par3, final List par4List) {
        IntCache.resetIntCache();
        final int l = par1 - par3 >> 2;
        final int i1 = par2 - par3 >> 2;
        final int j1 = par1 + par3 >> 2;
        final int k1 = par2 + par3 >> 2;
        final int l2 = j1 - l + 1;
        final int i2 = k1 - i1 + 1;
        final int[] aint = this.nxGenBiomes.getInts(l, i1, l2, i2);
        for (int j2 = 0; j2 < l2 * i2; ++j2) {
            final BiomeGenBase biomegenbase = BiomeGenBase.biomeList[aint[j2]];
            if (!par4List.contains(biomegenbase)) {
                return false;
            }
        }
        return true;
    }
    
    public ChunkPosition findBiomePosition(final int par1, final int par2, final int par3, final List par4List, final Random par5Random) {
        IntCache.resetIntCache();
        final int l = par1 - par3 >> 2;
        final int i1 = par2 - par3 >> 2;
        final int j1 = par1 + par3 >> 2;
        final int k1 = par2 + par3 >> 2;
        final int l2 = j1 - l + 1;
        final int i2 = k1 - i1 + 1;
        final int[] aint = this.nxGenBiomes.getInts(l, i1, l2, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;
        for (int k2 = 0; k2 < l2 * i2; ++k2) {
            final int l3 = l + k2 % l2 << 2;
            final int i3 = i1 + k2 / l2 << 2;
            final BiomeGenBase biomegenbase = BiomeGenBase.biomeList[aint[k2]];
            if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(j2 + 1) == 0)) {
                chunkposition = new ChunkPosition(l3, 0, i3);
                ++j2;
            }
        }
        return chunkposition;
    }
    
    public void cleanupCache() {
        this.nxBiomeCache.cleanupCache();
    }
}
