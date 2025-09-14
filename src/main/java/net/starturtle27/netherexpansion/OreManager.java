//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX;

import cpw.mods.fml.common.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import net.minecraft.block.*;
import net.minecraft.world.gen.feature.*;
import net.netherX.biomes.*;

public class OreManager implements IWorldGenerator
{
    public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {
        if (world.provider.isHellWorld) {
            final WorldGenMinable worldgenminable = new WorldGenMinable(NetherX.oreMalite.blockID, 7, Block.netherrack.blockID);
            final int k = chunkX * 16;
            final int l = chunkZ * 16;
            if (NetherX.doGenerateVenomiteOre) {
                for (int x = 0; x < 7; ++x) {
                    final int posX = k + random.nextInt(16);
                    final int posY = random.nextInt(108) + 10;
                    final int posZ = l + random.nextInt(16);
                    worldgenminable.generate(world, random, posX, posY, posZ);
                }
                for (int x = 0; x < 7; ++x) {
                    final int posX = k + random.nextInt(16);
                    final int posY = random.nextInt(108) + 10;
                    final int posZ = l + random.nextInt(16);
                    if (world.getBiomeGenForCoords(posX, posZ) == BiomeGenBaseNether.vileswamp) {
                        worldgenminable.generate(world, random, posX, posY, posZ);
                    }
                }
            }
            final WorldGenMinable worldgenminable2 = new WorldGenMinable(NetherX.oreNecromite.blockID, 4, Block.netherrack.blockID);
            final int k2 = chunkX * 16;
            final int l2 = chunkZ * 16;
            if (NetherX.doGenerateNecromiteOre) {
                for (int n = 0; n < 2; ++n) {
                    final int posX2 = k2 + random.nextInt(16);
                    final int posY2 = random.nextInt(108) + 10;
                    final int posZ2 = l2 + random.nextInt(16);
                    worldgenminable2.generate(world, random, posX2, posY2, posZ2);
                }
            }
            final WorldGenMinable worldgenminable3 = new WorldGenMinable(NetherX.oreIchorite.blockID, 6, Block.netherrack.blockID);
            final int k3 = chunkX * 16;
            final int l3 = chunkZ * 16;
            if (NetherX.doGenerateIchoriteOre) {
                for (int n2 = 0; n2 < 15; ++n2) {
                    final int posX3 = k3 + random.nextInt(16);
                    final int posY3 = random.nextInt(78) + 30;
                    final int posZ3 = l3 + random.nextInt(16);
                    worldgenminable3.generate(world, random, posX3, posY3, posZ3);
                }
            }
            final WorldGenMinable worldgenmagma = new WorldGenMinable(NetherX.magma.blockID, 6, Block.netherrack.blockID);
            final int k4 = chunkX * 16;
            final int l4 = chunkZ * 16;
            if (NetherX.doGenerateMagma) {
                for (int x2 = 0; x2 < 14; ++x2) {
                    final int posX4 = k4 + random.nextInt(16);
                    final int posY4 = random.nextInt(108) + 10;
                    final int posZ4 = l4 + random.nextInt(16);
                    if (world.getBiomeGenForCoords(posX4, posZ4) != BiomeGenBaseNether.vileswamp) {
                        worldgenmagma.generate(world, random, posX4, posY4, posZ4);
                    }
                }
            }
            final WorldGenMinable worldgencursedsoulsand = new WorldGenMinable(NetherX.enchantedSlowSand.blockID, 6, Block.slowSand.blockID);
            final int k5 = chunkX * 16;
            final int l5 = chunkZ * 16;
            if (NetherX.doGenerateCursedSoulSand) {
                for (int x3 = 0; x3 < 14; ++x3) {
                    final int posX5 = k5 + random.nextInt(16);
                    final int posY5 = random.nextInt(108) + 10;
                    final int posZ5 = l5 + random.nextInt(16);
                    worldgencursedsoulsand.generate(world, random, posX5, posY5, posZ5);
                }
            }
        }
    }
}
