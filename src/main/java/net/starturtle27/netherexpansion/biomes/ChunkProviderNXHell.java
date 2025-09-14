//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import net.minecraft.world.gen.structure.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.*;
import net.minecraft.world.chunk.*;
import net.minecraft.block.*;
import net.minecraftforge.event.terraingen.*;
import net.minecraft.world.gen.feature.*;
import net.netherX.worldgen.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.world.*;

public class ChunkProviderNXHell implements IChunkProvider
{
    private Random hellRNG;
    private NoiseGeneratorOctaves netherNoiseGen1;
    private NoiseGeneratorOctaves netherNoiseGen2;
    private NoiseGeneratorOctaves netherNoiseGen3;
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves netherNoiseGen6;
    public NoiseGeneratorOctaves netherNoiseGen7;
    private World worldObj;
    private double[] noiseField;
    public MapGenNetherBridge genNetherBridge;
    private BiomeGenBase[] biomesForGeneration;
    private double[] slowsandNoise;
    private double[] gravelNoise;
    private double[] netherrackExclusivityNoise;
    private MapGenBase netherCaveGenerator;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    
    public ChunkProviderNXHell(final World par1World, final long par2) {
        this.genNetherBridge = new MapGenNetherBridge();
        this.slowsandNoise = new double[256];
        this.gravelNoise = new double[256];
        this.netherrackExclusivityNoise = new double[256];
        this.netherCaveGenerator = (MapGenBase)new MapGenCavesHell();
        this.worldObj = par1World;
        this.hellRNG = new Random(par2);
        this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
        this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        NoiseGeneratorOctaves[] noiseGens = { this.netherNoiseGen1, this.netherNoiseGen2, this.netherNoiseGen3, this.slowsandGravelNoiseGen, this.netherrackExculsivityNoiseGen, this.netherNoiseGen6, this.netherNoiseGen7 };
        noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.hellRNG, noiseGens);
        this.netherNoiseGen1 = noiseGens[0];
        this.netherNoiseGen2 = noiseGens[1];
        this.netherNoiseGen3 = noiseGens[2];
        this.slowsandGravelNoiseGen = noiseGens[3];
        this.netherrackExculsivityNoiseGen = noiseGens[4];
        this.netherNoiseGen6 = noiseGens[5];
        this.netherNoiseGen7 = noiseGens[6];
    }
    
    public void generateNetherTerrain(final int par1, final int par2, final byte[] par3ArrayOfByte) {
        final byte b0 = 4;
        final byte b2 = 32;
        final int k = b0 + 1;
        final byte b3 = 17;
        final int l = b0 + 1;
        this.noiseField = this.initializeNoiseField(this.noiseField, par1 * b0, 0, par2 * b0, k, b3, l);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, k + 5, l + 5);
        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k2 = 0; k2 < 16; ++k2) {
                    final double d0 = 0.125;
                    double d2 = this.noiseField[((i1 + 0) * l + j1 + 0) * b3 + k2 + 0];
                    double d3 = this.noiseField[((i1 + 0) * l + j1 + 1) * b3 + k2 + 0];
                    double d4 = this.noiseField[((i1 + 1) * l + j1 + 0) * b3 + k2 + 0];
                    double d5 = this.noiseField[((i1 + 1) * l + j1 + 1) * b3 + k2 + 0];
                    final double d6 = (this.noiseField[((i1 + 0) * l + j1 + 0) * b3 + k2 + 1] - d2) * d0;
                    final double d7 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b3 + k2 + 1] - d3) * d0;
                    final double d8 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b3 + k2 + 1] - d4) * d0;
                    final double d9 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b3 + k2 + 1] - d5) * d0;
                    for (int l2 = 0; l2 < 8; ++l2) {
                        final double d10 = 0.25;
                        double d11 = d2;
                        double d12 = d3;
                        final double d13 = (d4 - d2) * d10;
                        final double d14 = (d5 - d3) * d10;
                        for (int i2 = 0; i2 < 4; ++i2) {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k2 * 8 + l2;
                            final short short1 = 128;
                            final double d15 = 0.25;
                            double d16 = d11;
                            final double d17 = (d12 - d11) * d15;
                            for (int k3 = 0; k3 < 4; ++k3) {
                                int l3 = 0;
                                if (k2 * 8 + l2 < b2) {
                                    l3 = Block.lavaStill.blockID;
                                }
                                if (d16 > 0.0) {
                                    l3 = Block.netherrack.blockID;
                                }
                                par3ArrayOfByte[j2] = (byte)l3;
                                j2 += short1;
                                d16 += d17;
                            }
                            d11 += d13;
                            d12 += d14;
                        }
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                        d5 += d9;
                    }
                }
            }
        }
    }
    
    public void replaceBlocksForBiome(final int par1, final int par2, final byte[] par3ArrayOfByte, final BiomeGenBase[] par4ArrayOfBiomeGenBase) {
        final ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks((IChunkProvider)this, par1, par2, par3ArrayOfByte, (BiomeGenBase[])null);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return;
        }
        final byte b0 = 64;
        final double d0 = 0.03125;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0, d0, 1.0);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, par1 * 16, 109, par2 * 16, 16, 1, 16, d0, 1.0, d0);
        this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0, d0 * 2.0, d0 * 2.0);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                final BiomeGenBase biomegenbase = par4ArrayOfBiomeGenBase[l + k * 16];
                final boolean flag = this.slowsandNoise[k + l * 16] + this.hellRNG.nextDouble() * 0.2 > 0.0;
                final boolean flag2 = this.gravelNoise[k + l * 16] + this.hellRNG.nextDouble() * 0.2 > 0.0;
                final int i1 = (int)(this.netherrackExclusivityNoise[k + l * 16] / 3.0 + 3.0 + this.hellRNG.nextDouble() * 0.25);
                int j1 = -1;
                byte b2 = biomegenbase.topBlock;
                byte b3 = biomegenbase.fillerBlock;
                for (int k2 = 127; k2 >= 0; --k2) {
                    final int l2 = (l * 16 + k) * 128 + k2;
                    if (k2 < 127 - this.hellRNG.nextInt(5) && k2 > 0 + this.hellRNG.nextInt(5)) {
                        final byte b4 = par3ArrayOfByte[l2];
                        if (b4 == 0) {
                            j1 = -1;
                        }
                        else if (b4 == Block.netherrack.blockID) {
                            if (j1 == -1) {
                                if (i1 <= 0) {
                                    b2 = 0;
                                    b3 = (byte)Block.netherrack.blockID;
                                }
                                else if (k2 >= b0 - 4 && k2 <= b0 + 1) {
                                    b2 = biomegenbase.topBlock;
                                    b3 = biomegenbase.fillerBlock;
                                    if (flag2) {
                                        b2 = (byte)Block.gravel.blockID;
                                    }
                                    if (flag2) {
                                        b3 = (byte)Block.netherrack.blockID;
                                    }
                                    if (flag) {
                                        b2 = (byte)Block.slowSand.blockID;
                                    }
                                    if (flag) {
                                        b3 = (byte)Block.slowSand.blockID;
                                    }
                                }
                                if (k2 < b0 && b2 == 0) {
                                    b2 = (byte)Block.lavaStill.blockID;
                                }
                                j1 = i1;
                                if (k2 >= b0 - 1) {
                                    par3ArrayOfByte[l2] = b2;
                                }
                                else {
                                    par3ArrayOfByte[l2] = b3;
                                }
                            }
                            else if (j1 > 0) {
                                --j1;
                                par3ArrayOfByte[l2] = b3;
                            }
                        }
                    }
                    else {
                        par3ArrayOfByte[l2] = (byte)Block.bedrock.blockID;
                    }
                }
            }
        }
    }
    
    public Chunk loadChunk(final int par1, final int par2) {
        return this.provideChunk(par1, par2);
    }
    
    public Chunk provideChunk(final int par1, final int par2) {
        this.hellRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        final byte[] abyte = new byte[32768];
        this.generateNetherTerrain(par1, par2, abyte);
        this.replaceBlocksForBiome(par1, par2, abyte, this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16));
        this.netherCaveGenerator.generate((IChunkProvider)this, this.worldObj, par1, par2, abyte);
        this.genNetherBridge.generate((IChunkProvider)this, this.worldObj, par1, par2, abyte);
        final Chunk chunk = new Chunk(this.worldObj, abyte, par1, par2);
        final BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
        final byte[] abyte2 = chunk.getBiomeArray();
        for (int k = 0; k < abyte2.length; ++k) {
            abyte2[k] = (byte)abiomegenbase[k].biomeID;
        }
        chunk.resetRelightChecks();
        return chunk;
    }
    
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, final int par2, final int par3, final int par4, final int par5, final int par6, final int par7) {
        final ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField((IChunkProvider)this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return event.noisefield;
        }
        if (par1ArrayOfDouble == null) {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }
        final double d0 = 684.412;
        final double d2 = 2053.236;
        this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0, 0.0, 1.0);
        this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0, 0.0, 100.0);
        this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, d0 / 80.0, d2 / 60.0, d0 / 80.0);
        this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, d0, d2, d0);
        this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, d0, d2, d0);
        int k1 = 0;
        int l1 = 0;
        final double[] adouble1 = new double[par6];
        for (int i2 = 0; i2 < par6; ++i2) {
            adouble1[i2] = Math.cos(i2 * 3.141592653589793 * 6.0 / par6) * 2.0;
            double d3 = i2;
            if (i2 > par6 / 2) {
                d3 = par6 - 1 - i2;
            }
            if (d3 < 4.0) {
                d3 = 4.0 - d3;
                final double[] array = adouble1;
                final int n = i2;
                array[n] -= d3 * d3 * d3 * 10.0;
            }
        }
        for (int i2 = 0; i2 < par5; ++i2) {
            for (int j2 = 0; j2 < par7; ++j2) {
                double d4 = (this.noiseData4[l1] + 256.0) / 512.0;
                if (d4 > 1.0) {
                    d4 = 1.0;
                }
                final double d5 = 0.0;
                double d6 = this.noiseData5[l1] / 8000.0;
                if (d6 < 0.0) {
                    d6 = -d6;
                }
                d6 = d6 * 3.0 - 3.0;
                if (d6 < 0.0) {
                    d6 /= 2.0;
                    if (d6 < -1.0) {
                        d6 = -1.0;
                    }
                    d6 /= 1.4;
                    d6 /= 2.0;
                    d4 = 0.0;
                }
                else {
                    if (d6 > 1.0) {
                        d6 = 1.0;
                    }
                    d6 /= 6.0;
                }
                d4 += 0.5;
                d6 = d6 * par6 / 16.0;
                ++l1;
                for (int k2 = 0; k2 < par6; ++k2) {
                    double d7 = 0.0;
                    final double d8 = adouble1[k2];
                    final double d9 = this.noiseData2[k1] / 512.0;
                    final double d10 = this.noiseData3[k1] / 512.0;
                    final double d11 = (this.noiseData1[k1] / 10.0 + 1.0) / 2.0;
                    if (d11 < 0.0) {
                        d7 = d9;
                    }
                    else if (d11 > 1.0) {
                        d7 = d10;
                    }
                    else {
                        d7 = d9 + (d10 - d9) * d11;
                    }
                    d7 -= d8;
                    if (k2 > par6 - 4) {
                        final double d12 = (k2 - (par6 - 4)) / 3.0f;
                        d7 = d7 * (1.0 - d12) + -10.0 * d12;
                    }
                    if (k2 < d5) {
                        double d12 = (d5 - k2) / 4.0;
                        if (d12 < 0.0) {
                            d12 = 0.0;
                        }
                        if (d12 > 1.0) {
                            d12 = 1.0;
                        }
                        d7 = d7 * (1.0 - d12) + -10.0 * d12;
                    }
                    par1ArrayOfDouble[k1] = d7;
                    ++k1;
                }
            }
        }
        return par1ArrayOfDouble;
    }
    
    public boolean chunkExists(final int par1, final int par2) {
        return true;
    }
    
    public void populate(final IChunkProvider par1IChunkProvider, final int par2, final int par3) {
        BlockSand.fallInstantly = true;
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Pre(par1IChunkProvider, this.worldObj, this.hellRNG, par2, par3, false));
        final int k = par2 * 16;
        final int l = par3 * 16;
        this.genNetherBridge.generateStructuresInChunk(this.worldObj, this.hellRNG, par2, par3);
        boolean doGen = TerrainGen.populate(par1IChunkProvider, this.worldObj, this.hellRNG, par2, par3, false, PopulateChunkEvent.Populate.EventType.NETHER_LAVA);
        for (int i1 = 0; doGen && i1 < 8; ++i1) {
            final int j1 = k + this.hellRNG.nextInt(16) + 8;
            final int k2 = this.hellRNG.nextInt(120) + 4;
            final int l2 = l + this.hellRNG.nextInt(16) + 8;
            if (this.worldObj.getBiomeGenForCoords(j1, l2) != BiomeGenBaseNether.vileswamp) {
                new WorldGenHellLava(Block.lavaMoving.blockID, false).generate(this.worldObj, this.hellRNG, j1, k2, l2);
            }
        }
        for (int i1 = 0; doGen && i1 < 8; ++i1) {
            final int j1 = k + this.hellRNG.nextInt(16) + 8;
            final int k2 = this.hellRNG.nextInt(120) + 4;
            final int l2 = l + this.hellRNG.nextInt(16) + 8;
            if (this.worldObj.getBiomeGenForCoords(j1, l2) == BiomeGenBaseNether.stalactite) {
                new WorldGenHellLava(Block.lavaMoving.blockID, false).generate(this.worldObj, this.hellRNG, j1, k2, l2);
            }
        }
        int i1;
        int j1;
        int k2;
        int l2;
        int i2;
        for (i1 = this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1) + 1, doGen = TerrainGen.populate(par1IChunkProvider, this.worldObj, this.hellRNG, par2, par3, false, PopulateChunkEvent.Populate.EventType.FIRE), j1 = 0; doGen && j1 < i1; ++j1) {
            k2 = k + this.hellRNG.nextInt(16) + 8;
            l2 = this.hellRNG.nextInt(120) + 4;
            i2 = l + this.hellRNG.nextInt(16) + 8;
            if (this.worldObj.getBiomeGenForCoords(k2, i2) != BiomeGenBaseNether.vileswamp) {
                new WorldGenFire().generate(this.worldObj, this.hellRNG, k2, l2, i2);
            }
        }
        for (i1 = this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1), doGen = TerrainGen.populate(par1IChunkProvider, this.worldObj, this.hellRNG, par2, par3, false, PopulateChunkEvent.Populate.EventType.GLOWSTONE), j1 = 0; doGen && j1 < i1; ++j1) {
            k2 = k + this.hellRNG.nextInt(16) + 8;
            l2 = this.hellRNG.nextInt(120) + 4;
            i2 = l + this.hellRNG.nextInt(16) + 8;
            new WorldGenGlowStone1().generate(this.worldObj, this.hellRNG, k2, l2, i2);
        }
        for (j1 = 0; doGen && j1 < 10; ++j1) {
            k2 = k + this.hellRNG.nextInt(16) + 8;
            l2 = this.hellRNG.nextInt(128);
            i2 = l + this.hellRNG.nextInt(16) + 8;
            new WorldGenGlowStone2().generate(this.worldObj, this.hellRNG, k2, l2, i2);
        }
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Pre(this.worldObj, this.hellRNG, k, l));
        doGen = TerrainGen.decorate(this.worldObj, this.hellRNG, k, l, DecorateBiomeEvent.Decorate.EventType.SHROOM);
        if (doGen && this.hellRNG.nextInt(1) == 0) {
            j1 = k + this.hellRNG.nextInt(16) + 8;
            k2 = this.hellRNG.nextInt(128);
            l2 = l + this.hellRNG.nextInt(16) + 8;
            new WorldGenFlowers(Block.mushroomBrown.blockID).generate(this.worldObj, this.hellRNG, j1, k2, l2);
        }
        if (doGen && this.hellRNG.nextInt(1) == 0) {
            j1 = k + this.hellRNG.nextInt(16) + 8;
            k2 = this.hellRNG.nextInt(128);
            l2 = l + this.hellRNG.nextInt(16) + 8;
            new WorldGenFlowers(Block.mushroomRed.blockID).generate(this.worldObj, this.hellRNG, j1, k2, l2);
        }
        doGen = TerrainGen.decorate(this.worldObj, this.hellRNG, k, l, DecorateBiomeEvent.Decorate.EventType.SHROOM);
        if (doGen && this.hellRNG.nextInt(1) == 0) {
            j1 = k + this.hellRNG.nextInt(16) + 8;
            k2 = this.hellRNG.nextInt(128);
            l2 = l + this.hellRNG.nextInt(16) + 8;
            if (this.worldObj.getBiomeGenForCoords(j1, l2) == BiomeGenBaseNether.vileswamp) {
                new WorldGenFlowers(Block.mushroomBrown.blockID).generate(this.worldObj, this.hellRNG, j1, k2, l2);
            }
        }
        if (doGen && this.hellRNG.nextInt(1) == 0) {
            j1 = k + this.hellRNG.nextInt(16) + 8;
            k2 = this.hellRNG.nextInt(128);
            l2 = l + this.hellRNG.nextInt(16) + 8;
            if (this.worldObj.getBiomeGenForCoords(j1, l2) == BiomeGenBaseNether.vileswamp) {
                new WorldGenFlowers(Block.mushroomRed.blockID).generate(this.worldObj, this.hellRNG, j1, k2, l2);
            }
        }
        final WorldGenMinable worldgenminable = new WorldGenMinable(Block.oreNetherQuartz.blockID, 13, Block.netherrack.blockID);
        for (k2 = 0; k2 < 16; ++k2) {
            l2 = k + this.hellRNG.nextInt(16);
            i2 = this.hellRNG.nextInt(108) + 10;
            final int j2 = l + this.hellRNG.nextInt(16);
            worldgenminable.generate(this.worldObj, this.hellRNG, l2, i2, j2);
        }
        for (k2 = 0; k2 < 16; ++k2) {
            l2 = k + this.hellRNG.nextInt(16);
            i2 = this.hellRNG.nextInt(108) + 10;
            final int j2 = l + this.hellRNG.nextInt(16);
            if (this.worldObj.getBiomeGenForCoords(l2, j2) != BiomeGenBaseNether.vileswamp) {
                new WorldGenHellLava(Block.lavaMoving.blockID, true).generate(this.worldObj, this.hellRNG, l2, i2, j2);
            }
        }
        for (k2 = 0; k2 < 16; ++k2) {
            l2 = k + this.hellRNG.nextInt(16);
            i2 = this.hellRNG.nextInt(108) + 10;
            final int j2 = l + this.hellRNG.nextInt(16);
            if (this.worldObj.getBiomeGenForCoords(l2, j2) == BiomeGenBaseNether.stalactite) {
                new WorldGenHellLava(Block.lavaMoving.blockID, true).generate(this.worldObj, this.hellRNG, l2, i2, j2);
            }
        }
        for (k2 = 0; k2 < 70; ++k2) {
            l2 = k + this.hellRNG.nextInt(16) + 8;
            i2 = this.hellRNG.nextInt(128);
            final int j3 = l + this.hellRNG.nextInt(16) + 8;
            new WorldGenNetherDungeons().generate(this.worldObj, this.hellRNG, l2, i2, j3);
        }
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Post(this.worldObj, this.hellRNG, k, l));
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(par1IChunkProvider, this.worldObj, this.hellRNG, par2, par3, false));
        BlockSand.fallInstantly = false;
    }
    
    public boolean saveChunks(final boolean par1, final IProgressUpdate par2IProgressUpdate) {
        return true;
    }
    
    public void saveExtraData() {
    }
    
    public boolean unloadQueuedChunks() {
        return false;
    }
    
    public boolean canSave() {
        return true;
    }
    
    public String makeString() {
        return "HellRandomLevelSource";
    }
    
    public List getPossibleCreatures(final EnumCreatureType par1EnumCreatureType, final int par2, final int par3, final int par4) {
        if (par1EnumCreatureType == EnumCreatureType.monster) {
            if (this.genNetherBridge.hasStructureAt(par2, par3, par4)) {
                return this.genNetherBridge.getSpawnList();
            }
            if (this.genNetherBridge.func_142038_b(par2, par3, par4) && this.worldObj.getBlockId(par2, par3 - 1, par4) == Block.netherBrick.blockID) {
                return this.genNetherBridge.getSpawnList();
            }
        }
        final BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
        return (biomegenbase == null) ? null : biomegenbase.getSpawnableList(par1EnumCreatureType);
    }
    
    public ChunkPosition findClosestStructure(final World par1World, final String par2Str, final int par3, final int par4, final int par5) {
        return null;
    }
    
    public int getLoadedChunkCount() {
        return 0;
    }
    
    public void recreateStructures(final int par1, final int par2) {
        this.genNetherBridge.generate((IChunkProvider)this, this.worldObj, par1, par2, (byte[])null);
    }
}
