//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import java.util.*;
import net.minecraft.block.*;
import net.minecraft.world.biome.*;
import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

public class BiomeGenVileswamp extends BiomeGenBase
{
    Random random;
    
    public BiomeGenVileswamp(final int par1) {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.theBiomeDecorator.mushroomsPerChunk = 6;
        this.topBlock = (byte)Block.netherrack.blockID;
        this.fillerBlock = (byte)Block.netherrack.blockID;
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityGhast.class, 15, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityPigZombie.class, 25, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityMagmaCube.class, 10, 4, 4));
    }
    
    public void decorate(final World par1World, final Random par2Random, final int par3, final int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        final WorldGenVines worldgenvines = new WorldGenVines();
        for (int k = 0; k < 50; ++k) {
            final int l = par3 + par2Random.nextInt(16) + 8;
            final byte b0 = 64;
            final int i1 = par4 + par2Random.nextInt(16) + 8;
            worldgenvines.generate(par1World, par2Random, l, (int)b0, i1);
        }
    }
}
