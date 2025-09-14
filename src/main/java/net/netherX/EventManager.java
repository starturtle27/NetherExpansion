//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import cpw.mods.fml.common.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import net.netherX.biomes.*;
import net.minecraft.block.*;
import net.netherX.worldgen.*;
import net.minecraft.world.gen.feature.*;

public class EventManager implements IWorldGenerator
{
    public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {
        if (world.provider.isHellWorld) {
            final int k = chunkX * 16;
            final int l = chunkZ * 16;
            if (NetherX.doGenerateNetherthorns) {
                for (int x = 0; x < 1; ++x) {
                    final int j1 = k + random.nextInt(16) + 8;
                    final int k2 = random.nextInt(128);
                    final int l2 = l + random.nextInt(16) + 8;
                    new WorldGenFlowers(NetherX.netherthorn.blockID).generate(world, random, j1, k2, l2);
                }
            }
            for (int x = 0; x < 6; ++x) {
                final int j1 = k + random.nextInt(16);
                final int k2 = random.nextInt(60) + 10;
                final int l2 = l + random.nextInt(16);
                if (world.getBiomeGenForCoords(j1, k2) == BiomeGenBaseNether.stalactite) {
                    new WorldGenStalagmite().generate(world, random, j1, k2, l2);
                }
            }
            if (NetherX.doGenerateObsidianBeaches) {
                for (int x = 0; x < 1; ++x) {
                    final int m1 = k + random.nextInt(16) + 8;
                    final int n1 = random.nextInt(128);
                    final int o1 = l + random.nextInt(16) + 8;
                    new WorldGenObsidian(Block.obsidian.blockID).generate(world, random, m1, n1, o1);
                }
            }
            if (NetherX.doGenerateTrees) {
                for (int x = 0; x < 4; ++x) {
                    final int i41 = k;
                    final int j2 = random.nextInt(10) + 42;
                    final int j3 = l;
                    new WorldGenDeadTree(false).generate(world, random, i41, j2, j3);
                }
            }
            final int j4 = k + random.nextInt(16) + 8;
            final int i42 = random.nextInt(random.nextInt(120) + 8);
            final int j5 = l + random.nextInt(16) + 8;
            if (NetherX.doGeneratePoisonLakes && (i42 < 64 || random.nextInt(10) == 0)) {
                new WorldGenLakes(NetherX.poisonLiquid.blockID).generate(world, random, j4, i42, j5);
            }
            for (int x2 = 0; x2 < 60; ++x2) {
                final int j6 = k + random.nextInt(16) + 8;
                final int i43 = random.nextInt(random.nextInt(120) + 8);
                final int j7 = l + random.nextInt(16) + 8;
                if (NetherX.doGeneratePoisonLakes && (i43 < 64 || random.nextInt(10) == 0) && world.getBiomeGenForCoords(j6, j7) == BiomeGenBaseNether.vileswamp) {
                    new WorldGenLakes(NetherX.poisonLiquid.blockID).generate(world, random, j6, i43, j7);
                }
            }
            if (NetherX.doGenerateNetherthorns) {
                for (int x2 = 0; x2 < 5; ++x2) {
                    final int j6 = k + random.nextInt(16) + 8;
                    final int k3 = random.nextInt(128);
                    final int l3 = l + random.nextInt(16) + 8;
                    if (world.getBiomeGenForCoords(j6, l3) == BiomeGenBaseNether.vileswamp) {
                        new WorldGenFlowers(NetherX.netherthorn.blockID).generate(world, random, j6, k3, l3);
                    }
                }
            }
            if (NetherX.doGenerateTrees) {
                for (int x2 = 0; x2 < 5; ++x2) {
                    final int i44 = k;
                    final int j8 = random.nextInt(10) + 42;
                    final int j9 = l;
                    if (world.getBiomeGenForCoords(i44, j9) == BiomeGenBaseNether.vileswamp) {
                        new WorldGenDeadTree(false).generate(world, random, i44, j8, j9);
                    }
                }
            }
            final WorldGenerator redbigMushroomGen = new WorldGenNetherMushroom(1);
            if (NetherX.doGenerateGiantMushrooms) {
                for (int x3 = 0; x3 < 140; ++x3) {
                    final int i45 = k;
                    final int j10 = random.nextInt(120) + 20;
                    final int j11 = l;
                    if (world.getBiomeGenForCoords(i45, j11) == BiomeGenBaseNether.vileswamp) {
                        redbigMushroomGen.generate(world, random, i45, j10, j11);
                    }
                }
            }
            final WorldGenerator brownbigMushroomGen = new WorldGenNetherMushroom(0);
            if (NetherX.doGenerateGiantMushrooms) {
                for (int x4 = 0; x4 < 220; ++x4) {
                    final int i46 = k;
                    final int j12 = random.nextInt(120) + 20;
                    final int j13 = l;
                    if (world.getBiomeGenForCoords(i46, j13) == BiomeGenBaseNether.vileswamp) {
                        brownbigMushroomGen.generate(world, random, i46, j12, j13);
                    }
                }
            }
            final WorldGenFungalStrands worldgenfungus = new WorldGenFungalStrands();
            for (int x5 = 0; x5 < 120; ++x5) {
                final int i47 = k;
                final int j14 = random.nextInt(120) + 20;
                final int j15 = l;
                if (world.getBiomeGenForCoords(i47, j15) == BiomeGenBaseNether.vileswamp) {
                    worldgenfungus.generate(world, random, i47, j14, j15);
                }
            }
        }
    }
}
