//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "Z:\home\aaron\Files\Minecraft Stuff\Mods\Mod Code Stuff\mcp811\conf"!

//Decompiled by Procyon!

package net.netherX.worldgen;

import java.util.*;
import net.minecraft.util.*;

public class NetherDungeonHooks
{
    private static ArrayList<DungeonMob> dungeonMobs;
    
    public static float addDungeonMob(final String name, final int rarity) {
        if (rarity <= 0) {
            throw new IllegalArgumentException("Rarity must be greater then zero");
        }
        for (final DungeonMob mob : NetherDungeonHooks.dungeonMobs) {
            if (name.equals(mob.type)) {
                final DungeonMob dungeonMob = mob;
                final int itemWeight = dungeonMob.itemWeight + rarity;
                dungeonMob.itemWeight = itemWeight;
                return (float)itemWeight;
            }
        }
        NetherDungeonHooks.dungeonMobs.add(new DungeonMob(rarity, name));
        return (float)rarity;
    }
    
    public static int removeDungeonMob(final String name) {
        for (final DungeonMob mob : NetherDungeonHooks.dungeonMobs) {
            if (name.equals(mob.type)) {
                NetherDungeonHooks.dungeonMobs.remove(mob);
                return mob.itemWeight;
            }
        }
        return 0;
    }
    
    public static String getRandomDungeonMob(final Random rand) {
        final DungeonMob mob = (DungeonMob)WeightedRandom.getRandomItem(rand, (Collection)NetherDungeonHooks.dungeonMobs);
        if (mob == null) {
            return "";
        }
        return mob.type;
    }
    
    static {
        NetherDungeonHooks.dungeonMobs = new ArrayList<DungeonMob>();
    }
    
    public static class DungeonMob extends WeightedRandomItem
    {
        public String type;
        
        public DungeonMob(final int weight, final String type) {
            super(weight);
            this.type = type;
        }
        
        public boolean equals(final Object target) {
            return target instanceof DungeonMob && this.type.equals(((DungeonMob)target).type);
        }
    }
}
