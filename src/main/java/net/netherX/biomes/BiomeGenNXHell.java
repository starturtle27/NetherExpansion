//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.biomes;

import net.minecraft.block.*;
import net.minecraft.world.biome.*;
import net.minecraft.entity.monster.*;

public class BiomeGenNXHell extends BiomeGenBase
{
    public BiomeGenNXHell(final int par1) {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = (byte)Block.netherrack.blockID;
        this.fillerBlock = (byte)Block.netherrack.blockID;
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry((Class)EntityMagmaCube.class, 1, 4, 4));
    }
}
