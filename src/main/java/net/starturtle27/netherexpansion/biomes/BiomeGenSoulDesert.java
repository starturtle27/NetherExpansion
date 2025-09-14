//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import net.minecraft.block.*;
import net.minecraft.world.biome.*;
import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.world.gen.feature.*;

public class BiomeGenSoulDesert extends BiomeGenBase
{
    public BiomeGenSoulDesert(final int par1) {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = (byte)Block.slowSand.blockID;
        this.fillerBlock = (byte)Block.slowSand.blockID;
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityGhast.class, 30, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityMagmaCube.class, 10, 4, 4));
    }
    
    public void decorate(final World par1World, final Random par2Random, final int par3, final int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        if (par2Random.nextInt(1000) == 0) {
            final int k = par3 + par2Random.nextInt(16) + 8;
            final int l = par4 + par2Random.nextInt(16) + 8;
            final WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
            worldgendesertwells.generate(par1World, par2Random, k, par1World.getHeightValue(k, l) + 1, l);
        }
    }
}
